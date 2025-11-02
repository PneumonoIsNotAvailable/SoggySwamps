package net.pneumono.soggy_swamps.content;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.pneumono.soggy_swamps.registry.SoggySwampsRegistry;
import net.pneumono.soggy_swamps.registry.SoggySwampsSounds;

public class PottedRotCapBlock extends FlowerPotBlock {
    public PottedRotCapBlock(Block content, Properties settings) {
        super(content, settings);
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        if (random.nextInt(30) == 0) {
            world.playLocalSound(pos, SoggySwampsSounds.BLOCK_ROT_CAP_IDLE, SoundSource.AMBIENT, 1.0F, 1.0F, false);
        }

        if (random.nextDouble() <= 0.3) {
            double x = pos.getX() + random.nextDouble() * 3.0 - 1.5;
            double y = pos.getY() + random.nextDouble();
            double z = pos.getZ() + random.nextDouble() * 3.0 - 1.5;
            world.addParticle(SoggySwampsRegistry.FLY, x, y, z, 0.0, 0.0, 0.0);
        }
    }
}
