package net.pneumono.soggy_swamps.content.templespawner;

import com.mojang.logging.LogUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.spawner.MobSpawnerEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.NbtReadView;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.ErrorReporter;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.Optional;
import java.util.function.Function;

public class TempleSpawnerLogic {
    private static final Logger LOGGER = LogUtils.getLogger();

    private int spawnDelay = 20;
    private Pool<MobSpawnerEntry> spawnPotentials = Pool.empty();
    @Nullable
    private MobSpawnerEntry spawnEntry;
    private double rotation;
    private double lastRotation;
    private int minSpawnDelay = 200;
    private int maxSpawnDelay = 800;
    private int spawnCount = 4;
    @Nullable
    private Entity renderedEntity;
    private int maxNearbyEntities = 6;
    private int requiredPlayerXZRange = 16;
    private int requiredPlayerYRange = 16;
    private int spawnRange = 4;

    public void readData(@Nullable World world, BlockPos pos, ReadView view) {
        this.spawnDelay = view.getShort("Delay", (short)20);
        view.read("SpawnData", MobSpawnerEntry.CODEC).ifPresent(
                mobSpawnerEntry -> this.setSpawnEntry(world, pos, mobSpawnerEntry));
        this.spawnPotentials = view.read("SpawnPotentials", MobSpawnerEntry.DATA_POOL_CODEC)
                .orElseGet(() -> Pool.of(this.spawnEntry != null ? this.spawnEntry : new MobSpawnerEntry()));
        this.minSpawnDelay = view.getInt("MinSpawnDelay", 200);
        this.maxSpawnDelay = view.getInt("MaxSpawnDelay", 800);
        this.spawnCount = view.getInt("SpawnCount", 4);
        this.maxNearbyEntities = view.getInt("MaxNearbyEntities", 6);
        this.requiredPlayerXZRange = view.getInt("RequiredPlayerXZRange", 16);
        this.requiredPlayerYRange = view.getInt("RequiredPlayerYRange", 2);
        this.spawnRange = view.getInt("SpawnRange", 4);
        this.renderedEntity = null;
    }

    public void writeData(WriteView view) {
        view.putShort("Delay", (short)this.spawnDelay);
        view.putShort("MinSpawnDelay", (short)this.minSpawnDelay);
        view.putShort("MaxSpawnDelay", (short)this.maxSpawnDelay);
        view.putShort("SpawnCount", (short)this.spawnCount);
        view.putShort("MaxNearbyEntities", (short)this.maxNearbyEntities);
        view.putShort("RequiredPlayerXZRange", (short)this.requiredPlayerXZRange);
        view.putShort("RequiredPlayerYRange", (short)this.requiredPlayerYRange);
        view.putShort("SpawnRange", (short)this.spawnRange);
        view.putNullable("SpawnData", MobSpawnerEntry.CODEC, this.spawnEntry);
        view.put("SpawnPotentials", MobSpawnerEntry.DATA_POOL_CODEC, this.spawnPotentials);
    }

    public void setEntityId(EntityType<?> type, @Nullable World world, Random random, BlockPos pos) {
        this.getSpawnEntry(world, random, pos).getNbt().putString("id", Registries.ENTITY_TYPE.getId(type).toString());
    }

    private boolean isPlayerInRange(World world, BlockPos pos) {
        int horizontalRange = this.requiredPlayerXZRange;
        if (horizontalRange < 0) return true;

        for (PlayerEntity playerEntity : world.getPlayers()) {
            if (EntityPredicates.EXCEPT_SPECTATOR.test(playerEntity) && EntityPredicates.VALID_LIVING_ENTITY.test(playerEntity)) {
                double verticalDistance = Math.abs(playerEntity.getY() - (pos.getY() + 0.5));
                if (verticalDistance >= this.requiredPlayerYRange) continue;

                double horizontalSquaredDistance = Math.pow(
                        playerEntity.getX() - (pos.getX() + 0.5),
                        2
                ) + Math.pow(
                        playerEntity.getZ() - (pos.getZ() + 0.5),
                        2
                );
                if (horizontalSquaredDistance < horizontalRange * horizontalRange) {
                    return true;
                }
            }
        }

        return false;
    }

    public void clientTick(World world, BlockPos pos) {
        this.lastRotation = this.rotation;
        if (this.renderedEntity == null) return;

        Random random = world.getRandom();
        double d = pos.getX() + random.nextDouble();
        double e = pos.getY() + random.nextDouble();
        double f = pos.getZ() + random.nextDouble();
        world.addParticleClient(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
        world.addParticleClient(ParticleTypes.FLAME, d, e, f, 0.0, 0.0, 0.0);
        if (this.spawnDelay > 0) {
            this.spawnDelay--;
        }

        this.rotation = (this.rotation + 1000.0F / (this.spawnDelay + 200.0F)) % 360.0;
    }

    public void serverTick(ServerWorld world, BlockPos pos) {
        if (!this.isPlayerInRange(world, pos)) return;

        if (this.spawnDelay == -1) {
            this.updateSpawns(world, pos);
        } else if (this.spawnDelay > 0) {
            this.spawnDelay--;
            return;
        }

        boolean shouldUpdateSpawns = false;
        Random random = world.getRandom();
        MobSpawnerEntry mobSpawnerEntry = this.getSpawnEntry(world, random, pos);

        for (int i = 0; i < this.spawnCount; i++) {
            ReadView readView;
            try (ErrorReporter.Logging logging = new ErrorReporter.Logging(this::toString, LOGGER)) {
                readView = NbtReadView.create(logging, world.getRegistryManager(), mobSpawnerEntry.getNbt());
            }

            Optional<EntityType<?>> optional = EntityType.fromData(readView);

            if (optional.isEmpty()) {
                shouldUpdateSpawns = true;
                break;
            }

            Vec3d vec3d = readView.read("Pos", Vec3d.CODEC).orElseGet(() -> new Vec3d(
                    pos.getX() + (random.nextDouble() - random.nextDouble()) * this.spawnRange + 0.5,
                    pos.getY() + random.nextInt(3) - 1,
                    pos.getZ() + (random.nextDouble() - random.nextDouble()) * this.spawnRange + 0.5
            ));

            if (!world.isSpaceEmpty(optional.get().getSpawnBox(vec3d.x, vec3d.y, vec3d.z))) {
                break;
            }

            BlockPos blockPos = BlockPos.ofFloored(vec3d);
            if (mobSpawnerEntry.getCustomSpawnRules().isPresent()) {
                if (!optional.get().getSpawnGroup().isPeaceful() && world.getDifficulty() == Difficulty.PEACEFUL) {
                    continue;
                }

                MobSpawnerEntry.CustomSpawnRules customSpawnRules = mobSpawnerEntry.getCustomSpawnRules().get();
                if (!customSpawnRules.canSpawn(blockPos, world)) {
                    continue;
                }
            } else if (!SpawnRestriction.canSpawn(optional.get(), world, SpawnReason.SPAWNER, blockPos, world.getRandom())) {
                continue;
            }

            Entity entity = EntityType.loadEntityWithPassengers(readView, world, SpawnReason.SPAWNER, newEntity -> {
                newEntity.refreshPositionAndAngles(vec3d.x, vec3d.y, vec3d.z, newEntity.getYaw(), newEntity.getPitch());
                return newEntity;
            });
            if (entity == null) {
                shouldUpdateSpawns = true;
                break;
            }

            int nearbyEntities = world.getEntitiesByType(
                            TypeFilter.equals(entity.getClass()),
                            new Box(
                                    pos.getX(), pos.getY(), pos.getZ(),
                                    pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1
                            ).expand(this.spawnRange),
                            EntityPredicates.EXCEPT_SPECTATOR
                    )
                    .size();
            if (nearbyEntities >= this.maxNearbyEntities) {
                shouldUpdateSpawns = true;
                break;
            }

            entity.refreshPositionAndAngles(entity.getX(), entity.getY(), entity.getZ(), random.nextFloat() * 360.0F, 0.0F);
            if (entity instanceof MobEntity mobEntity) {
                if (
                        mobSpawnerEntry.getCustomSpawnRules().isEmpty() &&
                        (!mobEntity.canSpawn(world, SpawnReason.SPAWNER) || !mobEntity.canSpawn(world))) {
                    continue;
                }

                boolean idOnly = mobSpawnerEntry.getNbt().getSize() == 1 && mobSpawnerEntry.getNbt().getString("id").isPresent();
                if (idOnly) {
                    mobEntity.initialize(world, world.getLocalDifficulty(entity.getBlockPos()), SpawnReason.SPAWNER, null);
                }

                mobSpawnerEntry.getEquipment().ifPresent(mobEntity::setEquipmentFromTable);
            }

            if (!world.spawnNewEntityAndPassengers(entity)) {
                shouldUpdateSpawns = true;
                break;
            }

            world.syncWorldEvent(WorldEvents.SPAWNER_SPAWNS_MOB, pos, 0);
            world.emitGameEvent(entity, GameEvent.ENTITY_PLACE, blockPos);
            if (entity instanceof MobEntity) {
                ((MobEntity)entity).playSpawnEffects();
            }

            shouldUpdateSpawns = true;
        }

        if (shouldUpdateSpawns) {
            this.updateSpawns(world, pos);
        }
    }

    private void updateSpawns(World world, BlockPos pos) {
        Random random = world.random;
        if (this.maxSpawnDelay <= this.minSpawnDelay) {
            this.spawnDelay = this.minSpawnDelay;
        } else {
            this.spawnDelay = this.minSpawnDelay + random.nextInt(this.maxSpawnDelay - this.minSpawnDelay);
        }

        this.spawnPotentials.getOrEmpty(random).ifPresent(spawnPotential -> this.setSpawnEntry(world, pos, spawnPotential));
        this.sendStatus(world, pos, 1);
    }

    @Nullable
    public Entity getRenderedEntity(World world, BlockPos pos) {
        if (this.renderedEntity == null) {
            NbtCompound nbtCompound = this.getSpawnEntry(world, world.getRandom(), pos).getNbt();
            if (nbtCompound.getString("id").isEmpty()) {
                return null;
            }

            this.renderedEntity = EntityType.loadEntityWithPassengers(
                    nbtCompound, world, SpawnReason.SPAWNER, Function.identity()
            );
        }

        return this.renderedEntity;
    }

    public boolean handleStatus(World world, int status) {
        if (status == 1) {
            if (world.isClient) {
                this.spawnDelay = this.minSpawnDelay;
            }

            return true;
        } else {
            return false;
        }
    }

    protected void setSpawnEntry(@Nullable World world, BlockPos pos, MobSpawnerEntry spawnEntry) {
        this.spawnEntry = spawnEntry;
        if (world != null) {
            BlockState blockState = world.getBlockState(pos);
            world.updateListeners(pos, blockState, blockState, Block.SKIP_REDRAW_AND_BLOCK_ENTITY_REPLACED_CALLBACK);
        }
    }

    private MobSpawnerEntry getSpawnEntry(@Nullable World world, Random random, BlockPos pos) {
        if (this.spawnEntry == null) {
            this.setSpawnEntry(world, pos, this.spawnPotentials.getOrEmpty(random).orElseGet(MobSpawnerEntry::new));
        }
        return this.spawnEntry;
    }

    public void sendStatus(World world, BlockPos pos, int status) {
        world.addSyncedBlockEvent(pos, Blocks.SPAWNER, status, 0);
    }

    public double getRotation() {
        return this.rotation;
    }

    public double getLastRotation() {
        return this.lastRotation;
    }
}
