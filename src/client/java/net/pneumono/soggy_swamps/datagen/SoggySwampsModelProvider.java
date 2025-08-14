package net.pneumono.soggy_swamps.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.minecraft.client.data.TexturedModel;
import net.pneumono.soggy_swamps.registry.SoggySwampsBlocks;
import net.pneumono.soggy_swamps.registry.SoggySwampsItems;

public class SoggySwampsModelProvider extends FabricModelProvider {
    public SoggySwampsModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        generator.registerBrushableBlock(SoggySwampsBlocks.SUSPICIOUS_MUD);
        generator.registerSimpleState(SoggySwampsBlocks.ROT_CAP);
        generator.registerFlowerPotPlantAndItem(
                SoggySwampsBlocks.VIBRANT_SPROUT,
                SoggySwampsBlocks.POTTED_VIBRANT_SPROUT,
                BlockStateModelGenerator.CrossType.NOT_TINTED
        );

        generator.registerFlowerPotPlantAndItem(
                SoggySwampsBlocks.SWAMP_OAK_SAPLING,
                SoggySwampsBlocks.POTTED_SWAMP_OAK_SAPLING,
                BlockStateModelGenerator.CrossType.NOT_TINTED
        );

        generator.registerTintedBlockAndItem(SoggySwampsBlocks.SWAMP_OAK_LEAVES, TexturedModel.LEAVES, 0x6A7039 - 0xFFFFFF);

        generator.createLogTexturePool(SoggySwampsBlocks.SWAMP_OAK_LOG)
                .log(SoggySwampsBlocks.SWAMP_OAK_LOG)
                .wood(SoggySwampsBlocks.SWAMP_OAK_WOOD);
        generator.createLogTexturePool(SoggySwampsBlocks.STRIPPED_SWAMP_OAK_LOG)
                .log(SoggySwampsBlocks.STRIPPED_SWAMP_OAK_LOG)
                .wood(SoggySwampsBlocks.STRIPPED_SWAMP_OAK_WOOD);

        generator.registerCubeAllModelTexturePool(SoggySwampsBlocks.SWAMP_OAK_PLANKS).family(SoggySwampsBlockFamilies.SWAMP_OAK);

        generator.registerHangingSign(
                SoggySwampsBlocks.STRIPPED_SWAMP_OAK_LOG,
                SoggySwampsBlocks.SWAMP_OAK_HANGING_SIGN,
                SoggySwampsBlocks.SWAMP_OAK_WALL_HANGING_SIGN
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        generator.register(SoggySwampsItems.SWAMP_SPIDER_SPAWN_EGG, Models.GENERATED);
        generator.register(SoggySwampsItems.SWAMP_SPIDER_EYE, Models.GENERATED);
        generator.register(SoggySwampsItems.SWAMP_STEW, Models.GENERATED);
        generator.register(SoggySwampsItems.ROASTED_ROT_CAP, Models.GENERATED);
        generator.register(SoggySwampsItems.SWAMP_OAK_BOAT, Models.GENERATED);
        generator.register(SoggySwampsItems.SWAMP_OAK_CHEST_BOAT, Models.GENERATED);
        generator.register(SoggySwampsItems.HAT_POTTERY_SHERD, Models.GENERATED);
        generator.register(SoggySwampsItems.SLIME_POTTERY_SHERD, Models.GENERATED);
        generator.register(SoggySwampsItems.DECAY_POTTERY_SHERD, Models.GENERATED);
    }
}
