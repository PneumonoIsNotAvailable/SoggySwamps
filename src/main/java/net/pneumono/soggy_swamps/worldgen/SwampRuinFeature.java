package net.pneumono.soggy_swamps.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class SwampRuinFeature extends Feature<SwampRuinFeature.Config> {
    public SwampRuinFeature(Codec<Config> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<Config> context) {
        BlockPos origin = context.getOrigin();
        StructureWorldAccess world = context.getWorld();
        Random random = context.getRandom();
        SwampRuinFeature.Config config = context.getConfig();

        int height = config.heightProvider().get(random);
        int xWidth = config.widthProvider().get(random);
        int zWidth = config.forceSquare() ? xWidth : config.widthProvider().get(random);

        BlockPos lowest = origin;
        for (int x = 0; x < xWidth; ++x) for (int z = 0; z < zWidth; ++z) {
            BlockPos checkedPos = origin.add(x, 0, z);

            for (int i = 0; i < 3; ++i) {
                BlockPos loweredPos = checkedPos.down(i);

                if (!world.getBlockState(loweredPos.down(i)).isReplaceable()) {
                    if (lowest.getY() > loweredPos.down(i).getY()) {
                        lowest = loweredPos;
                    }
                    break;
                }
            }
        }

        for (int y = -1; y < height; ++y) {
            boolean useTopState = height - y == 1;

            for (int x = 0; x < xWidth; ++x) for (int z = 0; z < zWidth; ++z) {
                BlockPos pos = lowest.add(x, y, z);
                BlockState currentState = world.getBlockState(pos);
                BlockState newState = (useTopState ? config.topProvider() : config.baseProvider()).get(random, pos);

                boolean shouldWaterlog = currentState.getFluidState().isOf(Fluids.WATER);
                if (newState.contains(Properties.WATERLOGGED)) {
                    newState = newState.with(Properties.WATERLOGGED, shouldWaterlog);
                } else if (shouldWaterlog && newState.isAir()) {
                    newState = Blocks.WATER.getDefaultState();
                }

                world.setBlockState(pos, newState, Block.NOTIFY_ALL);
            }
        }

        return true;
    }

    public record Config(IntProvider heightProvider, IntProvider widthProvider, BlockStateProvider baseProvider, BlockStateProvider topProvider, boolean forceSquare) implements FeatureConfig {
        public static final Codec<Config> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                IntProvider.createValidatingCodec(1, 64).fieldOf("height").forGetter(Config::heightProvider),
                IntProvider.createValidatingCodec(1, 8).fieldOf("width").forGetter(Config::widthProvider),
                BlockStateProvider.TYPE_CODEC.fieldOf("base_state").forGetter(Config::baseProvider),
                BlockStateProvider.TYPE_CODEC.fieldOf("top_state").forGetter(Config::topProvider),
                Codec.BOOL.optionalFieldOf("force_square", false).forGetter(Config::forceSquare)
        ).apply(instance, Config::new));
    }
}
