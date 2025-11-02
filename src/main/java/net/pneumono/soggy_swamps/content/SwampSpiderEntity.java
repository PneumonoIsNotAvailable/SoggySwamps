package net.pneumono.soggy_swamps.content;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.monster.Bogged;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.pneumono.soggy_swamps.registry.SoggySwampsRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class SwampSpiderEntity extends Spider {
    public SwampSpiderEntity(EntityType<? extends Spider> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder createSwampSpiderAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.4F)
                .add(Attributes.ATTACK_DAMAGE, 3F);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Frog.class, 6.0F, 0.8F, 1.0F));
    }

    @Override
    public boolean doHurtTarget(ServerLevel world, Entity target) {
        if (!super.doHurtTarget(world, target)) return false;

        if (target instanceof LivingEntity living) {
            int time = 0;
            if (world.getDifficulty() == Difficulty.NORMAL) {
                time = 5;
            } else if (world.getDifficulty() == Difficulty.HARD) {
                time = 10;
            }

            if (time > 0) {
                living.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, time * 20, 1), this);
                living.addEffect(new MobEffectInstance(SoggySwampsRegistry.VENOM, time * 40, 0), this);
            }
        }

        return true;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData entityData) {
        RandomSource random = world.getRandom();

        AttributeInstance entityAttributeInstance = Objects.requireNonNull(this.getAttribute(Attributes.FOLLOW_RANGE));
        if (!entityAttributeInstance.hasModifier(RANDOM_SPAWN_BONUS_ID)) {
            entityAttributeInstance.addPermanentModifier(
                    new AttributeModifier(
                            RANDOM_SPAWN_BONUS_ID, random.triangle(0.0, 0.11485000000000001), AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                    )
            );
        }

        if (random.nextInt(100) == 0) {
            Bogged boggedEntity = EntityType.BOGGED.create(level(), EntitySpawnReason.JOCKEY);
            if (boggedEntity != null) {
                boggedEntity.snapTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
                boggedEntity.finalizeSpawn(world, difficulty, spawnReason, null);
                boggedEntity.startRiding(this);
            }
        }

        if (entityData == null) {
            entityData = new Spider.SpiderEffectsGroupData();
            if (world.getDifficulty() == Difficulty.HARD && random.nextFloat() < 0.1F * difficulty.getSpecialMultiplier()) {
                ((Spider.SpiderEffectsGroupData)entityData).setRandomEffect(random);
            }
        }

        if (entityData instanceof Spider.SpiderEffectsGroupData spiderData) {
            Holder<MobEffect> registryEntry = spiderData.effect;
            if (registryEntry != null) {
                this.addEffect(new MobEffectInstance(registryEntry, -1));
            }
        }

        return entityData;
    }
}
