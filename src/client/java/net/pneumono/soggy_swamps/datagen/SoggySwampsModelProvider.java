package net.pneumono.soggy_swamps.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.world.level.block.Block;
import net.pneumono.soggy_swamps.registry.SoggySwampsBlocks;
import net.pneumono.soggy_swamps.registry.SoggySwampsItems;

public class SoggySwampsModelProvider extends FabricModelProvider {
    public SoggySwampsModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {
        generator.createBrushableBlock(SoggySwampsBlocks.SUSPICIOUS_MUD);
        generator.createDoublePlantWithDefaultItem(SoggySwampsBlocks.CATTAIL, BlockModelGenerators.PlantType.TINTED);
        registerSimpleStateWithYRotation(generator, SoggySwampsBlocks.ROT_CAP);
        generator.createNonTemplateModelBlock(SoggySwampsBlocks.POTTED_ROT_CAP);
        generator.createPlantWithDefaultItem(
                SoggySwampsBlocks.BOGSPROUT,
                SoggySwampsBlocks.POTTED_BOGSPROUT,
                BlockModelGenerators.PlantType.NOT_TINTED
        );

        generator.createPlantWithDefaultItem(
                SoggySwampsBlocks.SWAMP_OAK_SAPLING,
                SoggySwampsBlocks.POTTED_SWAMP_OAK_SAPLING,
                BlockModelGenerators.PlantType.NOT_TINTED
        );

        generator.createTintedLeaves(SoggySwampsBlocks.SWAMP_OAK_LEAVES, TexturedModel.LEAVES, 0x6A7039 - 0xFFFFFF);

        generator.woodProvider(SoggySwampsBlocks.SWAMP_OAK_LOG)
                .log(SoggySwampsBlocks.SWAMP_OAK_LOG)
                .wood(SoggySwampsBlocks.SWAMP_OAK_WOOD);
        generator.woodProvider(SoggySwampsBlocks.STRIPPED_SWAMP_OAK_LOG)
                .log(SoggySwampsBlocks.STRIPPED_SWAMP_OAK_LOG)
                .wood(SoggySwampsBlocks.STRIPPED_SWAMP_OAK_WOOD);

        generator.family(SoggySwampsBlocks.SWAMP_OAK_PLANKS).generateFor(SoggySwampsBlockFamilies.SWAMP_OAK);

        generator.createHangingSign(
                SoggySwampsBlocks.STRIPPED_SWAMP_OAK_LOG,
                SoggySwampsBlocks.SWAMP_OAK_HANGING_SIGN,
                SoggySwampsBlocks.SWAMP_OAK_WALL_HANGING_SIGN
        );
    }

    public void registerSimpleStateWithYRotation(BlockModelGenerators generator, Block block) {
        generator.blockStateOutput.accept(
                BlockModelGenerators.createSimpleBlock(
                        block,
                        BlockModelGenerators.createRotatedVariants(
                                BlockModelGenerators.plainModel(ModelLocationUtils.getModelLocation(block))
                        )
                )
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerators generator) {
        generator.generateFlatItem(SoggySwampsItems.SWAMP_STEW, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(SoggySwampsItems.ROASTED_ROT_CAP, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(SoggySwampsItems.SWAMP_OAK_BOAT, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(SoggySwampsItems.SWAMP_OAK_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(SoggySwampsItems.HAT_POTTERY_SHERD, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(SoggySwampsItems.SLIME_POTTERY_SHERD, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(SoggySwampsItems.DECAY_POTTERY_SHERD, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(SoggySwampsItems.WEALTH_POTTERY_SHERD, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(SoggySwampsItems.SPORE_ARMOR_TRIM_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
    }
}
