package net.pneumono.soggy_swamps.registry;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class CattailBlock extends TallPlantBlock implements FluidFillable {
    public static final MapCodec<CattailBlock> CODEC = createCodec(CattailBlock::new);
    private static final VoxelShape SHAPE = Block.createColumnShape(12.0, 0.0, 16.0);

    @Override
    public MapCodec<CattailBlock> getCodec() {
        return CODEC;
    }

    public CattailBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (state.get(HALF) == DoubleBlockHalf.UPPER) {
            BlockState blockState = world.getBlockState(pos.down());
            return blockState.isOf(this) && blockState.get(HALF) == DoubleBlockHalf.LOWER;
        } else {
            FluidState fluidState = world.getFluidState(pos);
            return super.canPlaceAt(state, world, pos) &&
                    fluidState.isIn(FluidTags.WATER) &&
                    fluidState.getLevel() == 8 &&
                    world.getFluidState(pos.up()).isEmpty();
        }
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(HALF) == DoubleBlockHalf.LOWER ? Fluids.WATER.getStill(false) : Fluids.EMPTY.getDefaultState();
    }

    @Override
    public boolean canFillWithFluid(@Nullable LivingEntity filler, BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return false;
    }

    @Override
    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        return false;
    }
}
