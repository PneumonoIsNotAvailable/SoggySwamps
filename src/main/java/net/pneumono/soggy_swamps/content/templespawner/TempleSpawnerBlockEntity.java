package net.pneumono.soggy_swamps.content.templespawner;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.Spawner;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.pneumono.soggy_swamps.registry.SoggySwampsRegistry;

public class TempleSpawnerBlockEntity extends BlockEntity implements Spawner {
    private final TempleSpawnerLogic logic = new TempleSpawnerLogic();

    public TempleSpawnerBlockEntity(BlockPos pos, BlockState state) {
        super(SoggySwampsRegistry.TEMPLE_SPAWNER, pos, state);
    }

    @Override
    protected void readData(ReadView view) {
        super.readData(view);
        this.logic.readData(this.world, this.pos, view);
    }

    @Override
    protected void writeData(WriteView view) {
        super.writeData(view);
        this.logic.writeData(view);
    }

    public static void clientTick(World world, BlockPos pos, BlockState state, TempleSpawnerBlockEntity blockEntity) {
        blockEntity.logic.clientTick(world, pos);
    }

    public static void serverTick(World world, BlockPos pos, BlockState state, TempleSpawnerBlockEntity blockEntity) {
        blockEntity.logic.serverTick((ServerWorld)world, pos);
    }

    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registries) {
        NbtCompound nbtCompound = this.createComponentlessNbt(registries);
        nbtCompound.remove("SpawnPotentials");
        return nbtCompound;
    }

    @Override
    public boolean onSyncedBlockEvent(int type, int data) {
        return this.logic.handleStatus(this.world, type) || super.onSyncedBlockEvent(type, data);
    }

    @Override
    public void setEntityType(EntityType<?> type, Random random) {
        this.logic.setEntityId(type, this.world, random, this.pos);
        this.markDirty();
    }

    public TempleSpawnerLogic getLogic() {
        return this.logic;
    }
}
