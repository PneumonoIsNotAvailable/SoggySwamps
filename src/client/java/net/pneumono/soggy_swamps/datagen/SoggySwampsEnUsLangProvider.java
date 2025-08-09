package net.pneumono.soggy_swamps.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import net.pneumono.pneumonocore.datagen.PneumonoCoreTranslationBuilder;
import net.pneumono.soggy_swamps.SoggySwamps;
import net.pneumono.soggy_swamps.registry.SoggySwampsBlocks;
import net.pneumono.soggy_swamps.registry.SoggySwampsEntities;
import net.pneumono.soggy_swamps.registry.SoggySwampsItems;
import net.pneumono.soggy_swamps.registry.SoggySwampsTags;

import java.util.concurrent.CompletableFuture;

public class SoggySwampsEnUsLangProvider extends FabricLanguageProvider {
    public SoggySwampsEnUsLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        PneumonoCoreTranslationBuilder builder = new PneumonoCoreTranslationBuilder(translationBuilder, SoggySwamps.MOD_ID);

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

        builder.add(SoggySwampsItems.SWAMP_OAK_BOAT, "Swamp Oak Boat");
        builder.add(SoggySwampsItems.SWAMP_OAK_CHEST_BOAT, "Swamp Oak Chest Boat");

        builder.add(SoggySwampsEntities.SWAMP_SPIDER, "Swamp Spider");
        builder.add(SoggySwampsEntities.SWAMP_OAK_BOAT, "Swamp Oak Boat");
        builder.add(SoggySwampsEntities.SWAMP_OAK_CHEST_BOAT, "Swamp Oak Chest Boat");

        builder.add(SoggySwampsTags.ITEM_SWAMP_OAK_LOGS, "Swamp Oak Logs");
        builder.add(SoggySwampsTags.BLOCK_SWAMP_OAK_LOGS, "Swamp Oak Logs");

        builder.addItemGroup(SoggySwampsItems.ITEM_GROUP, "Soggy Swamps Items");
        builder.addConfigScreenTitle("Soggy Swamps Configs");

        builder.addModMenuTexts(
                "Soggy Swamps",
                "Adds swamp-related items, blocks, and structures"
        );
    }
}
