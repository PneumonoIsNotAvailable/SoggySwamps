package net.pneumono.soggy_swamps.content;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BogsproutBlock extends FlowerBlock implements BonemealableBlock {
    private static final VoxelShape SHAPE = Block.column(12.0, 0.0, 12.0);

    public BogsproutBlock(Holder<MobEffect> stewEffect, float effectLengthInSeconds, Properties settings) {
        super(stewEffect, effectLengthInSeconds, settings);
    }

    @Override
    protected @NotNull VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE.move(state.getOffset(pos));
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
        List<BlockPos> validPositions = new ArrayList<>();
        forValidPos(world, pos, defaultBlockState(), validPositions::add, true);

        return !validPositions.isEmpty();
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        List<BlockPos> validPositions = new ArrayList<>();

        BlockState placeState = defaultBlockState();

        forValidPos(world, pos, placeState, validPositions::add, false);

        Util.shuffle(validPositions, world.getRandom());

        int count = world.getRandom().nextIntBetweenInclusive(1, 2);
        for (int i = 0; i < count && i < validPositions.size(); ++i) {
            BlockPos placePos = validPositions.get(i);
            world.setBlockAndUpdate(placePos, placeState);
        }
    }

    public void forValidPos(LevelReader world, BlockPos origin, BlockState placeState, Consumer<BlockPos> consumer, boolean shouldBreak) {
        for (BlockPos checkedPos : BlockPos.betweenClosed(origin.offset(-2, -1, -2), origin.offset(2, 1, 2))) {
            if (world.isEmptyBlock(checkedPos) && placeState.canSurvive(world, checkedPos)) {
                consumer.accept(new BlockPos(checkedPos));
                if (shouldBreak) break;
            }
        }
    }
}
