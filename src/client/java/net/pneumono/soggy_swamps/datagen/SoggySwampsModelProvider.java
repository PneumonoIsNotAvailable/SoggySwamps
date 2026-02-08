package net.pneumono.soggy_swamps.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.pneumono.soggy_swamps.registry.SoggySwampsBlocks;
import net.pneumono.soggy_swamps.registry.SoggySwampsItems;

public class SoggySwampsModelProvider extends FabricModelProvider {
    public SoggySwampsModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generators) {
        generators.createBrushableBlock(SoggySwampsBlocks.SUSPICIOUS_MUD);
        generators.createDoublePlantWithDefaultItem(SoggySwampsBlocks.CATTAIL, BlockModelGenerators.PlantType.TINTED);
        registerSimpleStateWithYRotation(generators, SoggySwampsBlocks.ROT_CAP);
        generators.createNonTemplateModelBlock(SoggySwampsBlocks.POTTED_ROT_CAP);
        generators.createPlantWithDefaultItem(
                SoggySwampsBlocks.BOGSPROUT,
                SoggySwampsBlocks.POTTED_BOGSPROUT,
                BlockModelGenerators.PlantType.NOT_TINTED
        );

        generators.createPlantWithDefaultItem(
                SoggySwampsBlocks.SWAMP_OAK_SAPLING,
                SoggySwampsBlocks.POTTED_SWAMP_OAK_SAPLING,
                BlockModelGenerators.PlantType.NOT_TINTED
        );

        generators.createTintedLeaves(SoggySwampsBlocks.SWAMP_OAK_LEAVES, TexturedModel.LEAVES, 0x6A7039 - 0xFFFFFF);

        generators.woodProvider(SoggySwampsBlocks.SWAMP_OAK_LOG)
                .log(SoggySwampsBlocks.SWAMP_OAK_LOG)
                .wood(SoggySwampsBlocks.SWAMP_OAK_WOOD);
        generators.woodProvider(SoggySwampsBlocks.STRIPPED_SWAMP_OAK_LOG)
                .log(SoggySwampsBlocks.STRIPPED_SWAMP_OAK_LOG)
                .wood(SoggySwampsBlocks.STRIPPED_SWAMP_OAK_WOOD);

        generators.family(SoggySwampsBlocks.SWAMP_OAK_PLANKS).generateFor(SoggySwampsBlockFamilies.SWAMP_OAK);

        generators.createHangingSign(
                SoggySwampsBlocks.STRIPPED_SWAMP_OAK_LOG,
                SoggySwampsBlocks.SWAMP_OAK_HANGING_SIGN,
                SoggySwampsBlocks.SWAMP_OAK_WALL_HANGING_SIGN
        );

        generators.createTrivialCube(SoggySwampsBlocks.CHISELED_MUD_BRICKS);

        // Awful awful awful, but like whatever
        registerMossyMudBricks(generators, SoggySwampsBlocks.MOSSY_MUD_BRICKS);
        registerMossyMudBrickStairs(generators, SoggySwampsBlocks.MOSSY_MUD_BRICKS, SoggySwampsBlocks.MOSSY_MUD_BRICK_STAIRS);
        registerMossyMudBrickSlab(generators, SoggySwampsBlocks.MOSSY_MUD_BRICKS, SoggySwampsBlocks.MOSSY_MUD_BRICK_SLAB);
        registerMossyMudBrickWall(generators, SoggySwampsBlocks.MOSSY_MUD_BRICKS, SoggySwampsBlocks.MOSSY_MUD_BRICK_WALL);
    }

    public void registerSimpleStateWithYRotation(BlockModelGenerators generators, Block block) {
        generators.blockStateOutput.accept(
                BlockModelGenerators.createSimpleBlock(
                        block,
                        BlockModelGenerators.createRotatedVariants(
                                BlockModelGenerators.plainModel(ModelLocationUtils.getModelLocation(block))
                        )
                )
        );
    }
    
    public void registerMossyMudBricks(BlockModelGenerators generators, Block block) {
        TextureMapping mapping = TextureMapping.cube(block);
        
        ModelTemplates.CUBE_ALL.create(block, mapping, generators.modelOutput);
        
        generators.blockStateOutput.accept(
                BlockModelGenerators.createSimpleBlock(
                        block,
                        BlockModelGenerators.plainVariant(
                                ModelTemplates.CUBE_NORTH_WEST_MIRRORED_ALL.create(
                                        block,
                                        mapping,
                                        generators.modelOutput
                                )
                        )
                )
        );
    }

    public void registerMossyMudBrickStairs(BlockModelGenerators generators, Block baseBlock, Block block) {
        TextureMapping mapping = TextureMapping.cube(baseBlock);

        Identifier identifier = ModelTemplates.STAIRS_STRAIGHT.create(block, mapping, generators.modelOutput);
        generators.blockStateOutput
                .accept(BlockModelGenerators.createStairs(
                        block,
                        BlockModelGenerators.plainVariant(ModelTemplates.STAIRS_INNER.create(block, mapping, generators.modelOutput)),
                        BlockModelGenerators.plainVariant(identifier),
                        BlockModelGenerators.plainVariant(ModelTemplates.STAIRS_OUTER.create(block, mapping, generators.modelOutput))
                ));
        generators.registerSimpleItemModel(block, identifier);
    }

    public void registerMossyMudBrickSlab(BlockModelGenerators generators, Block baseBlock, Block block) {
        TextureMapping mapping = TextureMapping.cube(baseBlock);

        Identifier bottom = ModelTemplates.SLAB_BOTTOM.create(block, mapping, generators.modelOutput);
        generators.blockStateOutput
                .accept(BlockModelGenerators.createSlab(
                        block,
                        BlockModelGenerators.plainVariant(bottom),
                        BlockModelGenerators.plainVariant(ModelTemplates.SLAB_TOP.create(block, mapping, generators.modelOutput)),
                        BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(baseBlock))
                ));
        generators.registerSimpleItemModel(block, bottom);
    }

    public void registerMossyMudBrickWall(BlockModelGenerators generators, Block baseBlock, Block block) {
        TextureMapping mapping = TextureMapping.cube(baseBlock);

        MultiVariant multiVariant = BlockModelGenerators.plainVariant(
                ModelTemplates.WALL_POST.create(block, mapping, generators.modelOutput)
        );
        MultiVariant multiVariant2 = BlockModelGenerators.plainVariant(
                ModelTemplates.WALL_LOW_SIDE.create(block, mapping, generators.modelOutput)
        );
        MultiVariant multiVariant3 = BlockModelGenerators.plainVariant(
                ModelTemplates.WALL_TALL_SIDE.create(block, mapping, generators.modelOutput)
        );
        generators.blockStateOutput.accept(BlockModelGenerators.createWall(
                block,
                multiVariant,
                multiVariant2,
                multiVariant3
        ));
        generators.registerSimpleItemModel(block, ModelTemplates.WALL_INVENTORY.create(block, mapping, generators.modelOutput));
    }

    @Override
    public void generateItemModels(ItemModelGenerators generators) {
        generators.generateFlatItem(SoggySwampsItems.SWAMP_STEW, ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(SoggySwampsItems.ROASTED_ROT_CAP, ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(SoggySwampsItems.SWAMP_OAK_BOAT, ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(SoggySwampsItems.SWAMP_OAK_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(SoggySwampsItems.HAT_POTTERY_SHERD, ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(SoggySwampsItems.SLIME_POTTERY_SHERD, ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(SoggySwampsItems.DECAY_POTTERY_SHERD, ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(SoggySwampsItems.WEALTH_POTTERY_SHERD, ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(SoggySwampsItems.SPORE_ARMOR_TRIM_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
    }
}
