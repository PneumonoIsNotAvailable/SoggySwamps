package net.pneumono.soggy_swamps.content;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.PlantBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.pneumono.soggy_swamps.registry.SoggySwampsRegistry;

public class RotCapBlock extends PlantBlock implements Fertilizable {
    public static final MapCodec<RotCapBlock> CODEC = createCodec(RotCapBlock::new);
    public static final int MAX_SIZE = 2;
    public static final IntProperty SIZE = IntProperty.of("size", 0, MAX_SIZE);

    @Override
    protected MapCodec<RotCapBlock> getCodec() {
        return CODEC;
    }

    public RotCapBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(SIZE, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SIZE);
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
        return state.get(SIZE) < MAX_SIZE;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int size = state.get(SIZE);
        if (size < MAX_SIZE) size++;
        world.setBlockState(pos, state.with(SIZE, size));
    }
}
