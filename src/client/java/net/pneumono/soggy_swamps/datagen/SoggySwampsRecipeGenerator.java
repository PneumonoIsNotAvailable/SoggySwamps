package net.pneumono.soggy_swamps.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.pneumono.soggy_swamps.registry.SoggySwampsBlocks;
import net.pneumono.soggy_swamps.registry.SoggySwampsItems;
import net.pneumono.soggy_swamps.registry.SoggySwampsTags;

import java.util.concurrent.CompletableFuture;

public class SoggySwampsRecipeGenerator extends RecipeGenerator {
    public SoggySwampsRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        super(registries, exporter);
    }

    @Override
    public void generate() {
        offerSingleOutputShapelessRecipe(Items.GREEN_DYE,  SoggySwampsItems.VIBRANT_SPROUT, "green_dye");
        offerShapelessRecipe(Items.PURPLE_DYE, SoggySwampsItems.ROT_CAP, "purple_dye", 2);

        generateFamily(SoggySwampsBlockFamilies.SWAMP_OAK, FeatureSet.of(FeatureFlags.VANILLA));

        offerPlanksRecipe(SoggySwampsBlocks.SWAMP_OAK_PLANKS, SoggySwampsTags.ITEM_SWAMP_OAK_LOGS, 4);

        offerBarkBlockRecipe(SoggySwampsBlocks.SWAMP_OAK_WOOD, SoggySwampsBlocks.SWAMP_OAK_LOG);
        offerBarkBlockRecipe(SoggySwampsBlocks.STRIPPED_SWAMP_OAK_WOOD, SoggySwampsBlocks.STRIPPED_SWAMP_OAK_LOG);

        offerHangingSignRecipe(SoggySwampsItems.SWAMP_OAK_HANGING_SIGN, SoggySwampsBlocks.STRIPPED_SWAMP_OAK_LOG);

        offerBoatRecipe(SoggySwampsItems.SWAMP_OAK_BOAT, SoggySwampsBlocks.SWAMP_OAK_PLANKS);
        offerChestBoatRecipe(SoggySwampsItems.SWAMP_OAK_CHEST_BOAT, SoggySwampsItems.SWAMP_OAK_BOAT);
    }

    public static class RecipeProvider extends FabricRecipeProvider {
        public RecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
            return new SoggySwampsRecipeGenerator(registryLookup, exporter);
        }

        @Override
        public String getName() {
            return "Recipes";
        }
    }
}
