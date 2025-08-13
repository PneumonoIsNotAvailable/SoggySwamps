package net.pneumono.soggy_swamps.content;

import net.minecraft.block.*;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class NonFallingBrushableBlock extends BrushableBlock {
    public NonFallingBrushableBlock(Block baseBlock, SoundEvent brushingSound, SoundEvent brushingCompleteSound, Settings settings) {
        super(baseBlock, brushingSound, brushingCompleteSound, settings);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBlockEntity(pos) instanceof BrushableBlockEntity brushableBlockEntity) {
            brushableBlockEntity.scheduledTick(world);
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {

    }
}
