package net.pneumono.soggy_swamps.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import net.pneumono.pneumonocore.datagen.PneumonoCoreTranslationBuilder;
import net.pneumono.soggy_swamps.SoggySwamps;
import net.pneumono.soggy_swamps.registry.*;

import java.util.concurrent.CompletableFuture;

public class SoggySwampsEnUsLangProvider extends FabricLanguageProvider {
    public SoggySwampsEnUsLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        PneumonoCoreTranslationBuilder builder = new PneumonoCoreTranslationBuilder(translationBuilder, SoggySwamps.MOD_ID);

        builder.add(SoggySwampsBlocks.SUSPICIOUS_MUD, "Suspicious Mud");
        builder.add(SoggySwampsBlocks.CATTAIL, "Cattail");
        builder.add(SoggySwampsBlocks.ROT_CAP, "Rot Cap");
        builder.add(SoggySwampsBlocks.POTTED_ROT_CAP, "Potted Rot Cap");
        builder.add(SoggySwampsBlocks.BOGSPROUT, "Bogsprout");
        builder.add(SoggySwampsBlocks.POTTED_BOGSPROUT, "Potted Bogsprout");
        builder.add(SoggySwampsBlocks.SWAMP_OAK_SAPLING, "Swamp Oak Sapling");
        builder.add(SoggySwampsBlocks.POTTED_SWAMP_OAK_SAPLING, "Potted Swamp Oak Sapling");
        builder.add(SoggySwampsBlocks.SWAMP_OAK_LEAVES, "Swamp Oak Leaves");
        builder.add(SoggySwampsBlocks.SWAMP_OAK_LOG, "Swamp Oak Log");
        builder.add(SoggySwampsBlocks.SWAMP_OAK_WOOD, "Swamp Oak Wood");
        builder.add(SoggySwampsBlocks.STRIPPED_SWAMP_OAK_LOG, "Stripped Swamp Oak Log");
        builder.add(SoggySwampsBlocks.STRIPPED_SWAMP_OAK_WOOD, "Stripped Swamp Oak Wood");
        builder.add(SoggySwampsBlocks.SWAMP_OAK_PLANKS, "Swamp Oak Planks");
        builder.add(SoggySwampsBlocks.SWAMP_OAK_STAIRS, "Swamp Oak Stairs");
        builder.add(SoggySwampsBlocks.SWAMP_OAK_SLAB, "Swamp Oak Slab");
        builder.add(SoggySwampsBlocks.SWAMP_OAK_SIGN, "Swamp Oak Sign");
        builder.add(SoggySwampsBlocks.SWAMP_OAK_HANGING_SIGN, "Swamp Oak Hanging Sign");
        builder.add(SoggySwampsBlocks.SWAMP_OAK_DOOR, "Swamp Oak Door");
        builder.add(SoggySwampsBlocks.SWAMP_OAK_TRAPDOOR, "Swamp Oak Trapdoor");
        builder.add(SoggySwampsBlocks.SWAMP_OAK_FENCE, "Swamp Oak Fence");
        builder.add(SoggySwampsBlocks.SWAMP_OAK_FENCE_GATE, "Swamp Oak Fence Gate");
        builder.add(SoggySwampsBlocks.SWAMP_OAK_PRESSURE_PLATE, "Swamp Oak Pressure Plate");
        builder.add(SoggySwampsBlocks.SWAMP_OAK_BUTTON, "Swamp Oak Button");

        builder.add(SoggySwampsItems.SWAMP_SPIDER_SPAWN_EGG, "Swamp Spider Spawn Egg");
        builder.add(SoggySwampsItems.SWAMP_SPIDER_EYE, "Swamp Spider Eye");
        builder.add(SoggySwampsItems.SWAMP_STEW, "Swamp Stew");
        builder.add(SoggySwampsItems.ROASTED_ROT_CAP, "Roasted Rot Cap");
        builder.add(SoggySwampsItems.SWAMP_OAK_BOAT, "Swamp Oak Boat");
        builder.add(SoggySwampsItems.SWAMP_OAK_CHEST_BOAT, "Swamp Oak Chest Boat");
        builder.add(SoggySwampsItems.HAT_POTTERY_SHERD, "Hat Pottery Sherd");
        builder.add(SoggySwampsItems.SLIME_POTTERY_SHERD, "Slime Pottery Sherd");
        builder.add(SoggySwampsItems.DECAY_POTTERY_SHERD, "Decay Pottery Sherd");
        builder.add(SoggySwampsItems.WEALTH_POTTERY_SHERD, "Wealth Pottery Sherd");
        builder.add(SoggySwampsItems.SPORE_ARMOR_TRIM_SMITHING_TEMPLATE, "Spore Armor Trim");
        builder.add("trim_pattern.soggy_swamps.spore", "Spore Armor Trim");
        builder.add("trim_pattern.soggy_swamps.bubble", "Bubble Armor Trim");
        builder.add("item.minecraft.potion.effect.venom", "Potion of Venom");
        builder.add("item.minecraft.splash_potion.effect.venom", "Splash Potion of Venom");
        builder.add("item.minecraft.lingering_potion.effect.venom", "Lingering Potion of Venom");
        builder.add("item.minecraft.tipped_arrow.effect.venom", "Arrow of Venom");

        builder.add(SoggySwampsEntities.SWAMP_SPIDER, "Swamp Spider");
        builder.add(SoggySwampsEntities.SWAMP_OAK_BOAT, "Swamp Oak Boat");
        builder.add(SoggySwampsEntities.SWAMP_OAK_CHEST_BOAT, "Swamp Oak Chest Boat");

        builder.add(SoggySwampsRegistry.VENOM.value(), "Venom");

        builder.add(SoggySwampsSounds.BLOCK_ROT_CAP_IDLE, "Flies buzz");
        builder.add(SoggySwampsSounds.ITEM_BRUSH_BRUSHING_MUD, "Brushing Mud");
        builder.add(SoggySwampsSounds.ENTITY_ALCHEMIST_AMBIENT, "Alchemist giggles");
        builder.add(SoggySwampsSounds.ENTITY_ALCHEMIST_CELEBRATE, "Alchemist cheers");
        builder.add(SoggySwampsSounds.ENTITY_ALCHEMIST_DEATH, "Alchemist dies");
        builder.add(SoggySwampsSounds.ENTITY_ALCHEMIST_DRINK, "Alchemist drinks");
        builder.add(SoggySwampsSounds.ENTITY_ALCHEMIST_HURT, "Alchemist hurts");
        builder.add(SoggySwampsSounds.ENTITY_ALCHEMIST_THROW, "Alchemist throws");

        builder.add(SoggySwampsTags.BLOCK_SWAMP_OAK_LOGS, "Swamp Oak Logs");
        builder.add(SoggySwampsTags.BLOCK_ALLOWS_SWAMP_RUINS, "Allows Swamp Ruins");
        builder.add(SoggySwampsTags.ITEM_SWAMP_OAK_LOGS, "Swamp Oak Logs");

        builder.addItemGroup(SoggySwampsItemGroups.ITEM_GROUP, "Soggy Swamps Items");
        builder.addConfigScreenTitle("Soggy Swamps Configs");

        builder.addModMenuTexts(
                "Soggy Swamps",
                "Adds swamp-related items, blocks, and structures"
        );
    }
}
