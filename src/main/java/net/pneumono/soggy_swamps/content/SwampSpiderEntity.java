package net.pneumono.soggy_swamps.content;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.BoggedEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.pneumono.soggy_swamps.registry.SoggySwampsRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class SwampSpiderEntity extends SpiderEntity {
    public SwampSpiderEntity(EntityType<? extends SpiderEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createSwampSpiderAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, 10.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.4F)
                .add(EntityAttributes.ATTACK_DAMAGE, 3F);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(2, new FleeEntityGoal<>(this, FrogEntity.class, 6.0F, 0.8F, 1.0F));
    }

    @Override
    public boolean tryAttack(ServerWorld world, Entity target) {
        if (!super.tryAttack(world, target)) return false;

        if (target instanceof LivingEntity living) {
            int time = 0;
            if (this.getWorld().getDifficulty() == Difficulty.NORMAL) {
                time = 5;
            } else if (this.getWorld().getDifficulty() == Difficulty.HARD) {
                time = 10;
            }

            if (time > 0) {
                living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, time * 20, 1), this);
                living.addStatusEffect(new StatusEffectInstance(SoggySwampsRegistry.VENOM, time * 40, 0), this);
            }
        }

        return true;
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        Random random = world.getRandom();

        EntityAttributeInstance entityAttributeInstance = Objects.requireNonNull(this.getAttributeInstance(EntityAttributes.FOLLOW_RANGE));
        if (!entityAttributeInstance.hasModifier(RANDOM_SPAWN_BONUS_MODIFIER_ID)) {
            entityAttributeInstance.addPersistentModifier(
                    new EntityAttributeModifier(
                            RANDOM_SPAWN_BONUS_MODIFIER_ID, random.nextTriangular(0.0, 0.11485000000000001), EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
                    )
            );
        }

        if (random.nextInt(100) == 0) {
            BoggedEntity boggedEntity = EntityType.BOGGED.create(this.getWorld(), SpawnReason.JOCKEY);
            if (boggedEntity != null) {
                boggedEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), 0.0F);
                boggedEntity.initialize(world, difficulty, spawnReason, null);
                boggedEntity.startRiding(this);
            }
        }

        if (entityData == null) {
            entityData = new SpiderEntity.SpiderData();
            if (world.getDifficulty() == Difficulty.HARD && random.nextFloat() < 0.1F * difficulty.getClampedLocalDifficulty()) {
                ((SpiderEntity.SpiderData)entityData).setEffect(random);
            }
        }

        if (entityData instanceof SpiderEntity.SpiderData spiderData) {
            RegistryEntry<StatusEffect> registryEntry = spiderData.effect;
            if (registryEntry != null) {
                this.addStatusEffect(new StatusEffectInstance(registryEntry, -1));
            }
        }

        return entityData;
    }
}
