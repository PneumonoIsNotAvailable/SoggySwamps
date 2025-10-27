package net.pneumono.soggy_swamps.content;

import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BogsproutBlock extends FlowerBlock implements Fertilizable {
    private static final VoxelShape SHAPE = Block.createColumnShape(12.0, 0.0, 12.0);

    public BogsproutBlock(RegistryEntry<StatusEffect> stewEffect, float effectLengthInSeconds, Settings settings) {
        super(stewEffect, effectLengthInSeconds, settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE.offset(state.getModelOffset(pos));
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        List<BlockPos> validPositions = new ArrayList<>();
        forValidPos(world, pos, getDefaultState(), validPositions::add, true);

        return !validPositions.isEmpty();
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        List<BlockPos> validPositions = new ArrayList<>();

        BlockState placeState = getDefaultState();

        forValidPos(world, pos, placeState, validPositions::add, false);

        Util.shuffle(validPositions, world.getRandom());

        int count = world.getRandom().nextBetween(1, 2);
        for (int i = 0; i < count && i < validPositions.size(); ++i) {
            BlockPos placePos = validPositions.get(i);
            world.setBlockState(placePos, placeState);
        }
    }

    public void forValidPos(WorldView world, BlockPos origin, BlockState placeState, Consumer<BlockPos> consumer, boolean shouldBreak) {
        for (BlockPos checkedPos : BlockPos.iterate(origin.add(-2, -1, -2), origin.add(2, 1, 2))) {
            if (world.isAir(checkedPos) && placeState.canPlaceAt(world, checkedPos)) {
                consumer.accept(new BlockPos(checkedPos));
                if (shouldBreak) break;
            }
        }
    }
}
