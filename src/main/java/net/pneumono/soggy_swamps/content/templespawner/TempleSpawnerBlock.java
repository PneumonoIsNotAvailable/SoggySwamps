package net.pneumono.soggy_swamps.content.templespawner;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pneumono.soggy_swamps.registry.SoggySwampsRegistry;
import org.jetbrains.annotations.Nullable;

public class TempleSpawnerBlock extends BlockWithEntity {
    public static final MapCodec<TempleSpawnerBlock> CODEC = createCodec(TempleSpawnerBlock::new);
    public static final EnumProperty<TempleSpawnerState> STATE = EnumProperty.of("state", TempleSpawnerState.class);

    @Override
    protected MapCodec<TempleSpawnerBlock> getCodec() {
        return CODEC;
    }

    public TempleSpawnerBlock(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(STATE, TempleSpawnerState.INACTIVE));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STATE);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TempleSpawnerBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(
                type,
                SoggySwampsRegistry.TEMPLE_SPAWNER,
                world.isClient ? TempleSpawnerBlockEntity::clientTick : TempleSpawnerBlockEntity::serverTick
        );
    }
}
