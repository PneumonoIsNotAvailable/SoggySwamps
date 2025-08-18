package net.pneumono.soggy_swamps.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.entity.SpawnGroup;
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
import net.pneumono.soggy_swamps.registry.SoggySwampsEntities;

import java.util.List;
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
        Predicate<BiomeSelectionContext> swamp = BiomeSelectors.includeByKey(BiomeKeys.SWAMP);
        Predicate<BiomeSelectionContext> swampAndMangrove = BiomeSelectors.includeByKey(List.of(BiomeKeys.SWAMP, BiomeKeys.MANGROVE_SWAMP));

        BiomeModifications.addSpawn(
                swampAndMangrove,
                SpawnGroup.MONSTER,
                SoggySwampsEntities.SWAMP_SPIDER,
                100, 2, 3
        );

        BiomeModifications.create(SoggySwamps.id("trees")).add(
                ModificationPhase.REPLACEMENTS,
                swamp,
                context -> {
                    context.getGenerationSettings().removeFeature(VegetationPlacedFeatures.TREES_SWAMP);
                    context.getGenerationSettings().addFeature(
                            GenerationStep.Feature.VEGETAL_DECORATION,
                            placedFeature("trees_soggy_swamp")
                    );
                }
        );

        BiomeModifications.addFeature(
                swamp,
                GenerationStep.Feature.UNDERGROUND_ORES,
                placedFeature("disk_mud")
        );

        BiomeModifications.addFeature(
                swamp,
                GenerationStep.Feature.UNDERGROUND_ORES,
                placedFeature("disk_mud_land")
        );

        BiomeModifications.addFeature(
                swampAndMangrove,
                GenerationStep.Feature.LOCAL_MODIFICATIONS,
                placedFeature("swamp_ruin")
        );

        BiomeModifications.addFeature(
                swamp,
                GenerationStep.Feature.VEGETAL_DECORATION,
                placedFeature("patch_cattail")
        );

        BiomeModifications.addFeature(
                swamp,
                GenerationStep.Feature.VEGETAL_DECORATION,
                placedFeature("patch_rot_cap")
        );

        BiomeModifications.addFeature(
                swampAndMangrove,
                GenerationStep.Feature.VEGETAL_DECORATION,
                placedFeature("patch_vibrant_sprout")
        );
    }

    protected static RegistryKey<PlacedFeature> placedFeature(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, SoggySwamps.id(name));
    }
}
