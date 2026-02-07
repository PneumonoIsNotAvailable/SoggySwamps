package net.pneumono.soggy_swamps.content;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class CattailBlock extends DoublePlantBlock implements LiquidBlockContainer {
    public static final MapCodec<CattailBlock> CODEC = simpleCodec(CattailBlock::new);
    private static final VoxelShape SHAPE = Block.column(12.0, 0.0, 16.0);

    @Override
    public @NonNull MapCodec<CattailBlock> codec() {
        return CODEC;
    }

    public CattailBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected @NonNull VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected boolean canSurvive(BlockState state, @NonNull LevelReader level, @NonNull BlockPos pos) {
        if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            BlockState blockState = level.getBlockState(pos.below());
            return blockState.is(this) && blockState.getValue(HALF) == DoubleBlockHalf.LOWER;
        } else {
            FluidState fluidState = level.getFluidState(pos);
            return super.canSurvive(state, level, pos) &&
                    fluidState.is(FluidTags.WATER) &&
                    fluidState.getAmount() == 8 &&
                    level.getFluidState(pos.above()).isEmpty();
        }
    }

    @Override
    protected @NonNull BlockState updateShape(
            @NonNull BlockState blockState,
            @NonNull LevelReader levelReader,
            @NonNull ScheduledTickAccess scheduledTickAccess,
            @NonNull BlockPos blockPos,
            @NonNull Direction direction,
            @NonNull BlockPos blockPos2,
            @NonNull BlockState blockState2,
            @NonNull RandomSource randomSource
    ) {
        BlockState blockState3 = super.updateShape(blockState, levelReader, scheduledTickAccess, blockPos, direction, blockPos2, blockState2, randomSource);
        if (!blockState3.isAir()) {
            scheduledTickAccess.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelReader));
        }

        return blockState3;
    }

    @Override
    protected @NonNull FluidState getFluidState(BlockState state) {
        return state.getValue(HALF) == DoubleBlockHalf.LOWER ? Fluids.WATER.getSource(false) : Fluids.EMPTY.defaultFluidState();
    }

    @Override
    public boolean canPlaceLiquid(@Nullable LivingEntity filler, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull BlockState state, @NonNull Fluid fluid) {
        return false;
    }

    @Override
    public boolean placeLiquid(@NonNull LevelAccessor level, @NonNull BlockPos pos, @NonNull BlockState state, @NonNull FluidState fluidState) {
        return false;
    }
}
