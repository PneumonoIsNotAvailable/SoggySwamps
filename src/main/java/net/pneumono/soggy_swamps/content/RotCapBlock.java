package net.pneumono.soggy_swamps.content;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.PlantBlock;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.pneumono.soggy_swamps.SoggySwamps;
import net.pneumono.soggy_swamps.registry.SoggySwampsRegistry;

public class RotCapBlock extends PlantBlock implements Fertilizable {
    public static final MapCodec<RotCapBlock> CODEC = createCodec(RotCapBlock::new);

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
            double x = pos.getX() + random.nextDouble() * 10.0 - 5.0;
            double y = pos.getY() + random.nextDouble() * 5.0;
            double z = pos.getZ() + random.nextDouble() * 10.0 - 5.0;
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
}
