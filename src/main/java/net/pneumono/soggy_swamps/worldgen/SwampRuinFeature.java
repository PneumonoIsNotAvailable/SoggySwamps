package net.pneumono.soggy_swamps.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.material.Fluids;

public class SwampRuinFeature extends Feature<SwampRuinFeature.Config> {
    public SwampRuinFeature(Codec<Config> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<Config> context) {
        BlockPos origin = context.origin();
        WorldGenLevel world = context.level();
        RandomSource random = context.random();
        Config config = context.config();

        int height = config.heightProvider().sample(random);
        int xWidth = config.widthProvider().sample(random);
        int zWidth = config.forceSquare() ? xWidth : config.widthProvider().sample(random);

        BlockPos lowest = origin;
        for (int x = 0; x < xWidth; ++x) for (int z = 0; z < zWidth; ++z) {
            BlockPos checkedPos = origin.offset(x, 0, z);

            for (int i = 0; i < 3; ++i) {
                BlockPos loweredPos = checkedPos.below(i);

                if (!world.getBlockState(loweredPos.below(i)).canBeReplaced()) {
                    if (lowest.getY() > loweredPos.below(i).getY()) {
                        lowest = loweredPos;
                    }
                    break;
                }
            }
        }

        for (int y = -1; y < height; ++y) {
            boolean useTopState = height - y == 1;

            for (int x = 0; x < xWidth; ++x) for (int z = 0; z < zWidth; ++z) {
                BlockPos pos = lowest.offset(x, y, z);
                BlockState currentState = world.getBlockState(pos);
                BlockState newState = (useTopState ? config.topProvider() : config.baseProvider()).getState(random, pos);

                boolean shouldWaterlog = currentState.getFluidState().is(Fluids.WATER);
                if (newState.hasProperty(BlockStateProperties.WATERLOGGED)) {
                    newState = newState.setValue(BlockStateProperties.WATERLOGGED, shouldWaterlog);
                } else if (shouldWaterlog && newState.isAir()) {
                    newState = Blocks.WATER.defaultBlockState();
                }

                world.setBlock(pos, newState, Block.UPDATE_ALL);
            }
        }

        return true;
    }

    public record Config(IntProvider heightProvider, IntProvider widthProvider, BlockStateProvider baseProvider, BlockStateProvider topProvider, boolean forceSquare) implements FeatureConfiguration {
        public static final Codec<Config> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                IntProvider.codec(1, 64).fieldOf("height").forGetter(Config::heightProvider),
                IntProvider.codec(1, 8).fieldOf("width").forGetter(Config::widthProvider),
                BlockStateProvider.CODEC.fieldOf("base_state").forGetter(Config::baseProvider),
                BlockStateProvider.CODEC.fieldOf("top_state").forGetter(Config::topProvider),
                Codec.BOOL.optionalFieldOf("force_square", false).forGetter(Config::forceSquare)
        ).apply(instance, Config::new));
    }
}
