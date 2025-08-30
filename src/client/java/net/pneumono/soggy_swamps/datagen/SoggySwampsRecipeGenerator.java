package net.pneumono.soggy_swamps.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.pneumono.soggy_swamps.SoggySwamps;
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
        offerShapelessRecipe(Items.BROWN_DYE, SoggySwampsItems.CATTAIL, "brown_dye", 2);

        this.createShapeless(RecipeCategory.FOOD, SoggySwampsItems.SWAMP_STEW)
                .input(Items.BOWL)
                .input(SoggySwampsItems.VIBRANT_SPROUT)
                .input(SoggySwampsItems.SWAMP_SPIDER_EYE)
                .input(SoggySwampsItems.ROT_CAP)
                .input(Items.BROWN_MUSHROOM)
                .group("swamp_stew")
                .criterion("has_swamp_spider_eye", this.conditionsFromItem(SoggySwampsItems.SWAMP_SPIDER_EYE))
                .offerTo(this.exporter, convertBetween(SoggySwampsItems.SWAMP_STEW, Items.BROWN_MUSHROOM));
        this.createShapeless(RecipeCategory.FOOD, SoggySwampsItems.SWAMP_STEW)
                .input(Items.BOWL)
                .input(SoggySwampsItems.VIBRANT_SPROUT)
                .input(SoggySwampsItems.SWAMP_SPIDER_EYE)
                .input(SoggySwampsItems.ROT_CAP)
                .input(Items.RED_MUSHROOM)
                .group("swamp_stew")
                .criterion("has_swamp_spider_eye", this.conditionsFromItem(SoggySwampsItems.SWAMP_SPIDER_EYE))
                .offerTo(this.exporter, convertBetween(SoggySwampsItems.SWAMP_STEW, Items.RED_MUSHROOM));

        CookingRecipeJsonBuilder.createSmelting(
                Ingredient.ofItem(SoggySwampsItems.ROT_CAP),
                RecipeCategory.FOOD,
                SoggySwampsItems.ROASTED_ROT_CAP,
                0.35F, 200
        )
                .criterion("has_rot_cap", this.conditionsFromItem(SoggySwampsItems.ROT_CAP))
                .offerTo(this.exporter);

        generateFamily(SoggySwampsBlockFamilies.SWAMP_OAK, FeatureSet.of(FeatureFlags.VANILLA));
        offerPlanksRecipe(SoggySwampsBlocks.SWAMP_OAK_PLANKS, SoggySwampsTags.ITEM_SWAMP_OAK_LOGS, 4);
        offerBarkBlockRecipe(SoggySwampsBlocks.SWAMP_OAK_WOOD, SoggySwampsBlocks.SWAMP_OAK_LOG);
        offerBarkBlockRecipe(SoggySwampsBlocks.STRIPPED_SWAMP_OAK_WOOD, SoggySwampsBlocks.STRIPPED_SWAMP_OAK_LOG);
        offerHangingSignRecipe(SoggySwampsItems.SWAMP_OAK_HANGING_SIGN, SoggySwampsBlocks.STRIPPED_SWAMP_OAK_LOG);
        offerBoatRecipe(SoggySwampsItems.SWAMP_OAK_BOAT, SoggySwampsBlocks.SWAMP_OAK_PLANKS);
        offerChestBoatRecipe(SoggySwampsItems.SWAMP_OAK_CHEST_BOAT, SoggySwampsItems.SWAMP_OAK_BOAT);

        generateFamily(SoggySwampsBlockFamilies.MOSSY_MUD_BRICK, FeatureSet.of(FeatureFlags.VANILLA));
        createShapeless(RecipeCategory.BUILDING_BLOCKS, SoggySwampsBlocks.MOSSY_MUD_BRICKS)
                .input(SoggySwampsBlocks.MOSSY_MUD_BRICKS)
                .input(Blocks.VINE)
                .group("mossy_mud_bricks")
                .criterion("has_vine", this.conditionsFromItem(Blocks.VINE))
                .offerTo(this.exporter, convertBetween(SoggySwampsBlocks.MOSSY_MUD_BRICKS, Blocks.VINE));
        createShapeless(RecipeCategory.BUILDING_BLOCKS, SoggySwampsBlocks.MOSSY_MUD_BRICKS)
                .input(SoggySwampsBlocks.MOSSY_MUD_BRICKS)
                .input(Blocks.MOSS_BLOCK)
                .group("mossy_mud_bricks")
                .criterion("has_moss_block", this.conditionsFromItem(Blocks.MOSS_BLOCK))
                .offerTo(this.exporter, convertBetween(SoggySwampsBlocks.MOSSY_MUD_BRICKS, Blocks.MOSS_BLOCK));
        createChiseledBlockRecipe(RecipeCategory.BUILDING_BLOCKS, SoggySwampsBlocks.CHISELED_MUD_BRICKS, Ingredient.ofItem(Blocks.MUD_BRICK_SLAB))
                .criterion("has_tag", this.conditionsFromTag(SoggySwampsTags.ITEM_MUD_BRICKS))
                .offerTo(this.exporter);
        offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, SoggySwampsBlocks.MOSSY_MUD_BRICK_SLAB, SoggySwampsBlocks.MOSSY_MUD_BRICKS, 2);
        offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, SoggySwampsBlocks.MOSSY_MUD_BRICK_STAIRS, SoggySwampsBlocks.MOSSY_MUD_BRICKS);
        offerStonecuttingRecipe(RecipeCategory.DECORATIONS, SoggySwampsBlocks.MOSSY_MUD_BRICK_WALL, SoggySwampsBlocks.MOSSY_MUD_BRICKS);
        offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, SoggySwampsBlocks.CHISELED_MUD_BRICKS, Items.MUD_BRICKS);

        offerSmithingTemplateCopyingRecipe(SoggySwampsItems.SPORE_ARMOR_TRIM_SMITHING_TEMPLATE, Items.MUD_BRICKS);
        offerSmithingTrimRecipe(
                SoggySwampsItems.SPORE_ARMOR_TRIM_SMITHING_TEMPLATE,
                RegistryKey.of(RegistryKeys.TRIM_PATTERN, SoggySwamps.id("spore")),
                RegistryKey.of(RegistryKeys.RECIPE, SoggySwamps.id("spore_armor_trim_smithing_template_smithing_trim"))
        );
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
