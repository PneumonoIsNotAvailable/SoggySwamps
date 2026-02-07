package net.pneumono.soggy_swamps.content;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.pneumono.soggy_swamps.SoggySwamps;
import net.pneumono.soggy_swamps.registry.SoggySwampsRegistry;
import net.pneumono.soggy_swamps.registry.SoggySwampsSounds;
import org.jspecify.annotations.NonNull;

public class RotCapBlock extends VegetationBlock implements BonemealableBlock {
    public static final MapCodec<RotCapBlock> CODEC = simpleCodec(RotCapBlock::new);
    private static final VoxelShape SHAPE = Shapes.or(Block.column(12.0, 4.0, 8.0), Block.column(4.0, 0.0, 4.0));

    @Override
    protected @NonNull MapCodec<RotCapBlock> codec() {
        return CODEC;
    }

    public RotCapBlock(Properties settings) {
        super(settings);
    }

    @Override
    public void animateTick(@NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, RandomSource random) {
        if (random.nextInt(30) == 0) {
            level.playLocalSound(pos, SoggySwampsSounds.BLOCK_ROT_CAP_IDLE, SoundSource.AMBIENT, 1.0F, 1.0F, false);
        }

        if (random.nextDouble() <= 0.7) {
            double x = pos.getX() + random.nextDouble() * 5.0 - 2.5;
            double y = pos.getY() + random.nextDouble() * 3.0 + (level.getBlockState(pos.above()).isRedstoneConductor(level, pos) ? 2 : 0);
            double z = pos.getZ() + random.nextDouble() * 5.0 - 2.5;
            level.addParticle(SoggySwampsRegistry.FLY, x, y, z, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public boolean isValidBonemealTarget(@NonNull LevelReader level, @NonNull BlockPos pos, @NonNull BlockState state) {
        return BonemealableBlock.hasSpreadableNeighbourPos(level, pos, state);
    }

    @Override
    public boolean isBonemealSuccess(@NonNull Level level, @NonNull RandomSource random, @NonNull BlockPos pos, @NonNull BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, @NonNull RandomSource random, @NonNull BlockPos pos, @NonNull BlockState state) {
        level.registryAccess()
                .lookup(Registries.CONFIGURED_FEATURE)
                .flatMap((registry) ->
                        registry.get(ResourceKey.create(
                                Registries.CONFIGURED_FEATURE, SoggySwamps.id("patch_rot_cap_bonemeal")
                        ))
                )
                .ifPresent((entry) ->
                        entry.value().place(level, level.getChunkSource().getGenerator(), random, pos.above())
                );
    }

    @Override
    protected @NonNull VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return SHAPE;
    }
}
