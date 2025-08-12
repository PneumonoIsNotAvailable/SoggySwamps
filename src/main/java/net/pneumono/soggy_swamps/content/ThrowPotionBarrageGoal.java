package net.pneumono.soggy_swamps.content;

import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.LingeringPotionEntity;
import net.minecraft.entity.projectile.thrown.SplashPotionEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.pneumono.soggy_swamps.registry.SoggySwampsRegistry;
import net.pneumono.soggy_swamps.registry.SoggySwampsSounds;

import java.util.List;

public class ThrowPotionBarrageGoal extends Goal {
    private final AlchemistEntity entity;

    private LivingEntity target;
    private ItemStack[] barrage = new ItemStack[0];
    private int intervalTicks = 0;
    private int potionsThrown = 0;
    private boolean active = false;

    public ThrowPotionBarrageGoal(AlchemistEntity entity) {
        this.entity = entity;
    }

    @Override
    public boolean canStart() {
        LivingEntity livingEntity = this.entity.getTarget();
        if (livingEntity != null && livingEntity.isAlive() && this.entity.distanceTo(livingEntity) > 6) {
            this.target = livingEntity;
        } else {
            return false;
        }

        this.barrage = getPotion(this.entity, this.target);
        return this.barrage.length > 0;
    }

    @Override
    public boolean canStop() {
        return this.potionsThrown == 0;
    }

    @Override
    public boolean shouldContinue() {
        return canStart() || this.target.isAlive() && !this.entity.getNavigation().isIdle();
    }

    @Override
    public void stop() {
        this.target = null;
        this.barrage = new ItemStack[0];
        this.intervalTicks = 0;
        this.potionsThrown = 0;
    }

    @Override
    public void tick() {
        if (!this.active && !this.entity.isFreeToAct()) return;

        if (this.intervalTicks > 0) {
            this.intervalTicks--;
        }

        if (this.entity.getThrowCooldown() != 0 || this.intervalTicks != 0) return;

        this.entity.setActing(true);
        this.active = true;

        World world = this.entity.getWorld();

        Vec3d vec3d = this.target.getVelocity();
        double xDistance = this.target.getX() + vec3d.x - this.entity.getX();
        double yDistance = this.target.getEyeY() - 1.1F - this.entity.getY();
        double zDistance = this.target.getZ() + vec3d.z - this.entity.getZ();
        double horizontalDistance = Math.sqrt(xDistance * xDistance + zDistance * zDistance);

        ItemStack potionStack = this.barrage[this.potionsThrown];
        if (world instanceof ServerWorld serverWorld) {
            ProjectileEntity.spawnWithVelocity(
                    potionStack.isOf(Items.LINGERING_POTION) ? LingeringPotionEntity::new : SplashPotionEntity::new,
                    serverWorld,
                    potionStack, this.entity,
                    xDistance,
                    yDistance + horizontalDistance * 0.2,
                    zDistance,
                    0.75F,
                    8.0F
            );
        }

        if (!this.entity.isSilent()) {
            world.playSound(
                    null,
                    this.entity.getX(), this.entity.getY(), this.entity.getZ(),
                    SoggySwampsSounds.ENTITY_ALCHEMIST_THROW, this.entity.getSoundCategory(),
                    1.0F, 0.8F + world.getRandom().nextFloat() * 0.4F
            );
        }

        this.potionsThrown++;

        if (this.potionsThrown >= this.barrage.length) {
            this.barrage = new ItemStack[0];
            this.entity.setThrowCooldown(60);
            this.potionsThrown = 0;
            this.entity.setActing(false);
            this.active = false;
        } else {
            this.intervalTicks = 5;
        }
    }

    public static ItemStack[] getPotion(AlchemistEntity entity, LivingEntity target) {
        double distance = entity.distanceTo(target);

        return new ItemStack[]{
                getRandomPotion(entity, target, distance),
                getRandomPotion(entity, target, distance),
                getRandomPotion(entity, target, distance)
        };
    }

    public static ItemStack getRandomPotion(AlchemistEntity entity, LivingEntity target, double distance) {
        boolean undead = target.hasInvertedHealingAndHarm();

        List<RegistryEntry<Potion>> potions = List.of(
                SoggySwampsRegistry.VENOM_POTION,
                SoggySwampsRegistry.LONG_VENOM_POTION,
                Potions.POISON,
                Potions.STRONG_POISON,
                Potions.SLOWNESS,
                Potions.STRONG_SLOWNESS,
                undead ? Potions.HEALING : Potions.HARMING,
                undead ? Potions.STRONG_HEALING : Potions.STRONG_HARMING,
                Potions.WEAKNESS,
                Potions.LONG_WEAKNESS
        );

        Item item = distance > 6 && entity.getRandom().nextFloat() > 0.25 ? Items.LINGERING_POTION : Items.SPLASH_POTION;
        return PotionContentsComponent.createStack(item, potions.get(entity.getRandom().nextInt(potions.size())));
    }
}
