package net.pneumono.soggy_swamps.content;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.pneumono.soggy_swamps.registry.SoggySwampsRegistry;
import net.pneumono.soggy_swamps.registry.SoggySwampsSounds;

public class PottedRotCapBlock extends FlowerPotBlock {
    public PottedRotCapBlock(Block content, Settings settings) {
        super(content, settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (random.nextInt(30) == 0) {
            world.playSoundAtBlockCenterClient(pos, SoggySwampsSounds.BLOCK_ROT_CAP_IDLE, SoundCategory.AMBIENT, 1.0F, 1.0F, false);
        }

        if (random.nextDouble() <= 0.3) {
            double x = pos.getX() + random.nextDouble() * 3.0 - 1.5;
            double y = pos.getY() + random.nextDouble();
            double z = pos.getZ() + random.nextDouble() * 3.0 - 1.5;
            world.addParticleClient(SoggySwampsRegistry.FLY, x, y, z, 0.0, 0.0, 0.0);
        }
    }
}
