package net.pneumono.soggy_swamps.content;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.pneumono.soggy_swamps.SoggySwamps;
import net.pneumono.soggy_swamps.registry.SoggySwampsRegistry;

public class RotCapBlock extends PlantBlock implements Fertilizable {
    public static final MapCodec<RotCapBlock> CODEC = createCodec(RotCapBlock::new);
    private static final VoxelShape SHAPE = VoxelShapes.union(Block.createColumnShape(12.0, 4.0, 8.0), Block.createColumnShape(4.0, 0.0, 4.0));

    @Override
    protected MapCodec<RotCapBlock> getCodec() {
        return CODEC;
    }

    public RotCapBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (random.nextInt(30) == 0) {
            world.playSoundAtBlockCenterClient(pos, SoundEvents.BLOCK_FIREFLY_BUSH_IDLE, SoundCategory.AMBIENT, 1.0F, 1.0F, false);
        }

        if (random.nextDouble() <= 0.7) {
            double x = pos.getX() + random.nextDouble() * 5.0 - 2.5;
            double y = pos.getY() + random.nextDouble() * 3.0 + (world.getBlockState(pos.up()).isSolidBlock(world, pos) ? 2 : 0);
            double z = pos.getZ() + random.nextDouble() * 5.0 - 2.5;
            world.addParticleClient(SoggySwampsRegistry.FLY, x, y, z, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return Fertilizable.canSpread(world, pos, state);
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        world.getRegistryManager()
                .getOptional(RegistryKeys.CONFIGURED_FEATURE)
                .flatMap((registry) ->
                        registry.getOptional(RegistryKey.of(
                                RegistryKeys.CONFIGURED_FEATURE, SoggySwamps.id("patch_rot_cap_bonemeal")
                        ))
                )
                .ifPresent((entry) ->
                        entry.value().generate(world, world.getChunkManager().getChunkGenerator(), random, pos.up())
                );
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
