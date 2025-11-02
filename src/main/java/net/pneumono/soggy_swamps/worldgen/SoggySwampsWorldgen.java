package net.pneumono.soggy_swamps.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.pneumono.soggy_swamps.SoggySwamps;
import net.pneumono.soggy_swamps.registry.SoggySwampsEntities;

import java.util.List;
import java.util.function.Predicate;

public class SoggySwampsWorldgen {
    public static final ResourceKey<ConfiguredFeature<?, ?>> SWAMP_OAK = ResourceKey.create(Registries.CONFIGURED_FEATURE, SoggySwamps.id("swamp_oak"));

    public static void registerSoggySwampsWorldgen() {
        registerWorldgen();
        modifySwamp();
    }

    private static void registerWorldgen() {
        Registry.register(
                BuiltInRegistries.FEATURE,
                SoggySwamps.id("swamp_ruin"),
                new SwampRuinFeature(SwampRuinFeature.Config.CODEC)
        );
    }

    private static void modifySwamp() {
        Predicate<BiomeSelectionContext> swamp = BiomeSelectors.includeByKey(Biomes.SWAMP);
        Predicate<BiomeSelectionContext> swampAndMangrove = BiomeSelectors.includeByKey(List.of(Biomes.SWAMP, Biomes.MANGROVE_SWAMP));

        BiomeModifications.create(SoggySwamps.id("witch")).add(
                ModificationPhase.REPLACEMENTS,
                swampAndMangrove,
                context -> {
                    context.getSpawnSettings().removeSpawnsOfEntityType(EntityType.WITCH);
                    context.getSpawnSettings().addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.WITCH, 1, 2), 15);

                    context.getSpawnSettings().removeSpawnsOfEntityType(EntityType.SPIDER);
                    context.getSpawnSettings().addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 4, 4), 50);
                    context.getSpawnSettings().addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(SoggySwampsEntities.SWAMP_SPIDER, 4, 4), 75);
                }
        );

        BiomeModifications.create(SoggySwamps.id("trees")).add(
                ModificationPhase.REPLACEMENTS,
                swamp,
                context -> {
                    context.getGenerationSettings().removeFeature(VegetationPlacements.TREES_SWAMP);
                    context.getGenerationSettings().addFeature(
                            GenerationStep.Decoration.VEGETAL_DECORATION,
                            placedFeature("trees_soggy_swamp")
                    );
                }
        );

        BiomeModifications.addFeature(
                swamp,
                GenerationStep.Decoration.UNDERGROUND_ORES,
                placedFeature("disk_mud")
        );

        BiomeModifications.addFeature(
                swamp,
                GenerationStep.Decoration.UNDERGROUND_ORES,
                placedFeature("disk_mud_land")
        );

        BiomeModifications.addFeature(
                swampAndMangrove,
                GenerationStep.Decoration.LOCAL_MODIFICATIONS,
                placedFeature("swamp_ruin")
        );

        BiomeModifications.addFeature(
                swampAndMangrove,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                placedFeature("patch_cattail")
        );

        BiomeModifications.addFeature(
                swamp,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                placedFeature("patch_rot_cap")
        );

        BiomeModifications.addFeature(
                swampAndMangrove,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                placedFeature("patch_bogsprout")
        );
    }

    protected static ResourceKey<PlacedFeature> placedFeature(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, SoggySwamps.id(name));
    }
}
