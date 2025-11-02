package net.pneumono.soggy_swamps.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.pneumono.soggy_swamps.SoggySwamps;
import net.pneumono.soggy_swamps.registry.SoggySwampsBlocks;
import net.pneumono.soggy_swamps.registry.SoggySwampsItems;
import net.pneumono.soggy_swamps.registry.SoggySwampsTags;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class SoggySwampsRecipeGenerator extends net.minecraft.data.recipes.RecipeProvider {
    public SoggySwampsRecipeGenerator(HolderLookup.Provider registries, RecipeOutput exporter) {
        super(registries, exporter);
    }

    @Override
    public void buildRecipes() {
        oneToOneConversionRecipe(Items.GREEN_DYE,  SoggySwampsItems.VIBRANT_SPROUT, "green_dye");
        oneToOneConversionRecipe(Items.PURPLE_DYE, SoggySwampsItems.ROT_CAP, "purple_dye", 2);
        oneToOneConversionRecipe(Items.BROWN_DYE, SoggySwampsItems.CATTAIL, "brown_dye", 2);

        this.shapeless(RecipeCategory.FOOD, SoggySwampsItems.SWAMP_STEW)
                .requires(Items.BOWL)
                .requires(SoggySwampsItems.VIBRANT_SPROUT)
                .requires(SoggySwampsItems.SWAMP_SPIDER_EYE)
                .requires(SoggySwampsItems.ROT_CAP)
                .requires(Items.BROWN_MUSHROOM)
                .group("swamp_stew")
                .unlockedBy("has_swamp_spider_eye", this.has(SoggySwampsItems.SWAMP_SPIDER_EYE))
                .save(this.output, getConversionRecipeName(SoggySwampsItems.SWAMP_STEW, Items.BROWN_MUSHROOM));
        this.shapeless(RecipeCategory.FOOD, SoggySwampsItems.SWAMP_STEW)
                .requires(Items.BOWL)
                .requires(SoggySwampsItems.VIBRANT_SPROUT)
                .requires(SoggySwampsItems.SWAMP_SPIDER_EYE)
                .requires(SoggySwampsItems.ROT_CAP)
                .requires(Items.RED_MUSHROOM)
                .group("swamp_stew")
                .unlockedBy("has_swamp_spider_eye", this.has(SoggySwampsItems.SWAMP_SPIDER_EYE))
                .save(this.output, getConversionRecipeName(SoggySwampsItems.SWAMP_STEW, Items.RED_MUSHROOM));

        SimpleCookingRecipeBuilder.smelting(
                Ingredient.of(SoggySwampsItems.ROT_CAP),
                RecipeCategory.FOOD,
                SoggySwampsItems.ROASTED_ROT_CAP,
                0.35F, 200
        )
                .unlockedBy("has_rot_cap", this.has(SoggySwampsItems.ROT_CAP))
                .save(this.output);

        generateRecipes(SoggySwampsBlockFamilies.SWAMP_OAK, FeatureFlagSet.of(FeatureFlags.VANILLA));
        planksFromLogs(SoggySwampsBlocks.SWAMP_OAK_PLANKS, SoggySwampsTags.ITEM_SWAMP_OAK_LOGS, 4);
        woodFromLogs(SoggySwampsBlocks.SWAMP_OAK_WOOD, SoggySwampsBlocks.SWAMP_OAK_LOG);
        woodFromLogs(SoggySwampsBlocks.STRIPPED_SWAMP_OAK_WOOD, SoggySwampsBlocks.STRIPPED_SWAMP_OAK_LOG);
        hangingSign(SoggySwampsItems.SWAMP_OAK_HANGING_SIGN, SoggySwampsBlocks.STRIPPED_SWAMP_OAK_LOG);
        woodenBoat(SoggySwampsItems.SWAMP_OAK_BOAT, SoggySwampsBlocks.SWAMP_OAK_PLANKS);
        chestBoat(SoggySwampsItems.SWAMP_OAK_CHEST_BOAT, SoggySwampsItems.SWAMP_OAK_BOAT);

        copySmithingTemplate(SoggySwampsItems.SPORE_ARMOR_TRIM_SMITHING_TEMPLATE, Items.MUD_BRICKS);
        trimSmithing(
                SoggySwampsItems.SPORE_ARMOR_TRIM_SMITHING_TEMPLATE,
                ResourceKey.create(Registries.TRIM_PATTERN, SoggySwamps.id("spore")),
                ResourceKey.create(Registries.RECIPE, SoggySwamps.id("spore_armor_trim_smithing_template_smithing_trim"))
        );
    }

    public static class SoggySwampsRecipeProvider extends FabricRecipeProvider {
        public SoggySwampsRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
            return new SoggySwampsRecipeGenerator(registryLookup, exporter);
        }

        @Override
        public @NotNull String getName() {
            return "Recipes";
        }
    }
}
