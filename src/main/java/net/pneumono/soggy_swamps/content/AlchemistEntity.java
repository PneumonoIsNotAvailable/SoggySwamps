package net.pneumono.soggy_swamps.content;

import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import net.pneumono.soggy_swamps.registry.SoggySwampsEntities;
import net.pneumono.soggy_swamps.registry.SoggySwampsSounds;

public class AlchemistEntity extends RaiderEntity {
    private RaidGoal<RaiderEntity> raidGoal;
    private DisableableFollowTargetGoal<PlayerEntity> attackPlayerGoal;

    private int throwCooldown = 0;
    private boolean drinking = false;
    private boolean acting = false;
    private int venomTicks = 0;

    public AlchemistEntity(EntityType<AlchemistEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createAlchemistAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.MAX_HEALTH, 36.0).add(EntityAttributes.MOVEMENT_SPEED, 0.3);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.raidGoal = new RaidGoal<>(this, RaiderEntity.class, true, (target, world) -> this.hasActiveRaid() && target.getType() != SoggySwampsEntities.ALCHEMIST);
        this.attackPlayerGoal = new DisableableFollowTargetGoal<>(this, PlayerEntity.class, 10, true, false, null);

        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(1, new FleeEntityGoal<>(
                this,
                PlayerEntity.class,
                entity -> true,
                6.0F,
                1.0,
                1.2,
                entity -> EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR.test(entity) && this.isDrinking()
        ));
        this.goalSelector.add(2, new DrinkPotionGoal(this));
        this.goalSelector.add(2, new ThrowPotionBarrageGoal(this));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this, RaiderEntity.class));
        this.targetSelector.add(2, this.raidGoal);
        this.targetSelector.add(4, this.attackPlayerGoal);
    }

    public int getThrowCooldown() {
        return this.throwCooldown;
    }

    public void setThrowCooldown(int throwCooldown) {
        this.throwCooldown = throwCooldown;
    }

    public boolean isFreeToAct() {
        return !acting;
    }

    public void setActing(boolean acting) {
        this.acting = acting;
    }

    public boolean isDrinking() {
        return drinking;
    }

    public void setDrinking(boolean drinking) {
        this.drinking = drinking;
    }

    public int getVenomTicks() {
        return venomTicks;
    }

    public void setVenomTicks(int venomTicks) {
        this.venomTicks = venomTicks;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.throwCooldown > 0 && this.isFreeToAct()) {
            this.throwCooldown--;
        }
        this.venomTicks--;
        if (this.venomTicks < -160 && this.age % 20 == 0 && getWorld() instanceof ServerWorld serverWorld) {
            this.damage(serverWorld, this.getDamageSources().magic(), 1.0F);
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoggySwampsSounds.ENTITY_ALCHEMIST_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoggySwampsSounds.ENTITY_ALCHEMIST_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoggySwampsSounds.ENTITY_ALCHEMIST_DEATH;
    }

    @Override
    public void tickMovement() {
        if (this.getWorld().isClient || !this.isAlive()) {
            super.tickMovement();
            return;
        }

        this.raidGoal.decreaseCooldown();
        this.attackPlayerGoal.setEnabled(this.raidGoal.getCooldown() <= 0);

        if (this.random.nextFloat() < 7.5E-4F) {
            this.getWorld().sendEntityStatus(this, EntityStatuses.ADD_WITCH_PARTICLES);
        }

        super.tickMovement();
    }

    @Override
    public void handleStatus(byte status) {
        if (status == EntityStatuses.ADD_WITCH_PARTICLES) {
            for (int i = 0; i < this.random.nextInt(35) + 10; i++) {
                this.getWorld()
                        .addParticleClient(
                                ParticleTypes.WITCH,
                                this.getX() + this.random.nextGaussian() * 0.13F,
                                this.getBoundingBox().maxY + 0.5 + this.random.nextGaussian() * 0.13F,
                                this.getZ() + this.random.nextGaussian() * 0.13F,
                                0.0,
                                0.0,
                                0.0
                        );
            }
        } else {
            super.handleStatus(status);
        }
    }

    @Override
    protected float modifyAppliedDamage(DamageSource source, float amount) {
        amount = super.modifyAppliedDamage(source, amount);
        if (source.getAttacker() == this) {
            amount = 0.0F;
        }

        if (source.isIn(DamageTypeTags.WITCH_RESISTANT_TO)) {
            amount *= 0.15F;
        }

        return amount;
    }

    @Override
    public SoundEvent getCelebratingSound() {
        return SoggySwampsSounds.ENTITY_ALCHEMIST_CELEBRATE;
    }

    @Override
    public void addBonusForWave(ServerWorld world, int wave, boolean unused) {
    }

    @Override
    public boolean canLead() {
        return false;
    }
}
