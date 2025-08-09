package net.pneumono.soggy_swamps.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.pneumono.soggy_swamps.SoggySwamps;

import java.util.function.Predicate;

public class SoggySwampsWorldgen {
    public static final RegistryKey<ConfiguredFeature<?, ?>> SWAMP_OAK = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, SoggySwamps.id("swamp_oak"));

    public static void registerSoggySwampsWorldgen() {
        registerWorldgen();
        modifySwamp();
    }

    private static void registerWorldgen() {
        Registry.register(
                Registries.FEATURE,
                SoggySwamps.id("swamp_ruin"),
                new SwampRuinFeature(SwampRuinFeature.Config.CODEC)
        );
    }

    private static void modifySwamp() {
        Predicate<BiomeSelectionContext> selector = BiomeSelectors.includeByKey(BiomeKeys.SWAMP);

        BiomeModifications.create(SoggySwamps.id("trees")).add(
                ModificationPhase.REPLACEMENTS,
                selector,
                context -> {
                    context.getGenerationSettings().removeFeature(VegetationPlacedFeatures.TREES_SWAMP);
                    context.getGenerationSettings().addFeature(
                            GenerationStep.Feature.VEGETAL_DECORATION,
                            placedFeature("trees_soggy_swamp")
                    );
                }
        );

        BiomeModifications.addFeature(
                selector,
                GenerationStep.Feature.UNDERGROUND_ORES,
                placedFeature("disk_mud")
        );

        BiomeModifications.addFeature(
                selector,
                GenerationStep.Feature.UNDERGROUND_ORES,
                placedFeature("disk_mud_land")
        );

        BiomeModifications.addFeature(
                selector,
                GenerationStep.Feature.LOCAL_MODIFICATIONS,
                placedFeature("swamp_ruin")
        );
    }

    protected static RegistryKey<PlacedFeature> placedFeature(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, SoggySwamps.id(name));
    }
}
