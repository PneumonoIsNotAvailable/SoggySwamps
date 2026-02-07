package net.pneumono.soggy_swamps.content;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
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
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BogsproutBlock extends FlowerBlock implements BonemealableBlock {
    private static final VoxelShape SHAPE = Block.column(12.0, 0.0, 12.0);

    public BogsproutBlock(Holder<MobEffect> stewEffect, float effectLengthInSeconds, Properties settings) {
        super(stewEffect, effectLengthInSeconds, settings);
    }

    @Override
    protected @NonNull VoxelShape getShape(BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return SHAPE.move(state.getOffset(pos));
    }

    @Override
    public boolean isBonemealSuccess(@NonNull Level level, @NonNull RandomSource random, @NonNull BlockPos pos, @NonNull BlockState state) {
        return true;
    }

    @Override
    public boolean isValidBonemealTarget(@NonNull LevelReader level, @NonNull BlockPos pos, @NonNull BlockState state) {
        List<BlockPos> validPositions = new ArrayList<>();
        forValidPos(level, pos, defaultBlockState(), validPositions::add, true);

        return !validPositions.isEmpty();
    }

    @Override
    public void performBonemeal(@NonNull ServerLevel level, @NonNull RandomSource random, @NonNull BlockPos pos, @NonNull BlockState state) {
        List<BlockPos> validPositions = new ArrayList<>();

        BlockState placeState = defaultBlockState();

        forValidPos(level, pos, placeState, validPositions::add, false);

        Util.shuffle(validPositions, level.getRandom());

        int count = level.getRandom().nextIntBetweenInclusive(1, 2);
        for (int i = 0; i < count && i < validPositions.size(); ++i) {
            BlockPos placePos = validPositions.get(i);
            level.setBlockAndUpdate(placePos, placeState);
        }
    }

    public void forValidPos(LevelReader level, BlockPos origin, BlockState placeState, Consumer<BlockPos> consumer, boolean shouldBreak) {
        for (BlockPos checkedPos : BlockPos.betweenClosed(origin.offset(-2, -1, -2), origin.offset(2, 1, 2))) {
            if (level.isEmptyBlock(checkedPos) && placeState.canSurvive(level, checkedPos)) {
                consumer.accept(new BlockPos(checkedPos));
                if (shouldBreak) break;
            }
        }
    }
}
