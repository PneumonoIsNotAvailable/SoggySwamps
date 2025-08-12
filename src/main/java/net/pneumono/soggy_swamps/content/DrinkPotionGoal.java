package net.pneumono.soggy_swamps.content;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.event.GameEvent;
import net.pneumono.soggy_swamps.registry.SoggySwampsRegistry;
import net.pneumono.soggy_swamps.registry.SoggySwampsSounds;

public class DrinkPotionGoal extends Goal {
    private final AlchemistEntity entity;

    private ItemStack potionStack = ItemStack.EMPTY;
    private int drinkingTicks = -1;

    public DrinkPotionGoal(AlchemistEntity entity) {
        this.entity = entity;
    }

    public boolean isDrinking() {
        return drinkingTicks >= 0;
    }

    @Override
    public boolean canStart() {
        this.potionStack = getPotion();
        return !this.potionStack.isEmpty();
    }

    @Override
    public boolean canStop() {
        return !this.isDrinking();
    }

    @Override
    public boolean shouldContinue() {
        return this.isDrinking();
    }

    @Override
    public void stop() {
        this.potionStack = ItemStack.EMPTY;
        this.drinkingTicks = -1;
    }

    @Override
    public void tick() {
        if (this.drinkingTicks > 0) {
            this.drinkingTicks--;
            if (this.drinkingTicks % 3 == 0) {
                sound();
            }
        }

        if (this.drinkingTicks == 0) {
            ItemStack stack = this.entity.getEquippedStack(EquipmentSlot.MAINHAND);
            this.entity.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            PotionContentsComponent potionContentsComponent = stack.get(DataComponentTypes.POTION_CONTENTS);
            if (potionContentsComponent != null) {
                potionContentsComponent.forEachEffect(this.entity::addStatusEffect, stack.getOrDefault(DataComponentTypes.POTION_DURATION_SCALE, 1.0F));

                if (potionContentsComponent.matches(SoggySwampsRegistry.VENOM_POTION)) {
                    this.entity.setVenomTicks(160);
                }
            } else if (stack.isOf(Items.MILK_BUCKET)) {
                this.entity.clearStatusEffects();
            }
            this.potionStack = getPotion();
            this.drinkingTicks = -1;

            this.entity.emitGameEvent(GameEvent.DRINK);
            this.entity.setActing(false);
            this.entity.setDrinking(false);

        } else if (this.drinkingTicks == -1 && this.entity.isFreeToAct()) {
            ItemStack stack = this.potionStack.copy();
            this.entity.equipStack(EquipmentSlot.MAINHAND, stack);
            this.drinkingTicks = (int) (stack.getMaxUseTime(this.entity) * 1.5);

            sound();

            this.entity.setActing(true);
            this.entity.setDrinking(true);
            this.entity.setThrowCooldown(5);
        }
    }

    public ItemStack getPotion() {
        AlchemistEntity entity = this.entity;

        if (!entity.isAlive()) return ItemStack.EMPTY;

        Random random = entity.getRandom();
        LivingEntity target = entity.getTarget();

        RegistryEntry<Potion> potion = null;

        if (entity.getVenomTicks() <= 0) {
            potion = SoggySwampsRegistry.VENOM_POTION;

        } else if (entity.isSubmergedIn(FluidTags.WATER) && !entity.hasStatusEffect(StatusEffects.WATER_BREATHING)) {
            potion = random.nextFloat() < 0.5 ? Potions.WATER_BREATHING : Potions.LONG_WATER_BREATHING;

        } else if (
                (entity.isOnFire() || entity.getRecentDamageSource() != null && entity.getRecentDamageSource().isIn(DamageTypeTags.IS_FIRE))
                && !entity.hasStatusEffect(StatusEffects.FIRE_RESISTANCE)
        ) {
            potion = random.nextFloat() < 0.5 ? Potions.FIRE_RESISTANCE : Potions.LONG_FIRE_RESISTANCE;

        } else if (!entity.getStatusEffects().stream().filter(instance -> !instance.getEffectType().equals(SoggySwampsRegistry.VENOM) && instance.getEffectType().value().getCategory() == StatusEffectCategory.HARMFUL).toList().isEmpty()) {
            return new ItemStack(Items.MILK_BUCKET);

        } else if (entity.getHealth() <= 10.0 || entity.getHealth() <= entity.getMaxHealth() / 3) {
            potion = random.nextFloat() < 0.5 ? Potions.HEALING : Potions.STRONG_HEALING;

        } else if (!entity.hasStatusEffect(StatusEffects.REGENERATION) && target != null && entity.distanceTo(target) > 30) {
            potion = random.nextFloat() < 0.66 ? Potions.REGENERATION : Potions.LONG_REGENERATION;

        } else if (!entity.hasStatusEffect(StatusEffects.SPEED) && target != null && entity.distanceTo(target) > 20) {
            potion = random.nextFloat() < 0.66 ? Potions.SWIFTNESS : Potions.STRONG_SWIFTNESS;

        } else if (entity.getHealth() < entity.getMaxHealth() && (target == null || entity.distanceTo(target) > 20)) {
            potion = Potions.HEALING;
        }

        return potion == null ? ItemStack.EMPTY : PotionContentsComponent.createStack(Items.POTION, potion);
    }

    private void sound() {
        if (!this.entity.isSilent()) {
            this.entity.getWorld().playSound(
                    null,
                    this.entity.getX(), this.entity.getY(), this.entity.getZ(),
                    SoggySwampsSounds.ENTITY_ALCHEMIST_DRINK,
                    this.entity.getSoundCategory(), 1.0F, 0.8F + this.entity.getRandom().nextFloat() * 0.4F
            );
        }
    }
}
