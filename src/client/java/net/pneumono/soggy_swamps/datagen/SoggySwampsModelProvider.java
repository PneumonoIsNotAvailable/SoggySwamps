package net.pneumono.soggy_swamps.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.util.Identifier;
import net.pneumono.soggy_swamps.registry.SoggySwampsBlocks;
import net.pneumono.soggy_swamps.registry.SoggySwampsItems;

public class SoggySwampsModelProvider extends FabricModelProvider {
    public SoggySwampsModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        generator.registerBrushableBlock(SoggySwampsBlocks.SUSPICIOUS_MUD);
        generator.registerDoubleBlockAndItem(SoggySwampsBlocks.CATTAIL, BlockStateModelGenerator.CrossType.TINTED);
        registerSimpleStateWithYRotation(generator, SoggySwampsBlocks.ROT_CAP);
        generator.registerSimpleState(SoggySwampsBlocks.POTTED_ROT_CAP);
        generator.registerFlowerPotPlantAndItem(
                SoggySwampsBlocks.BOGSPROUT,
                SoggySwampsBlocks.POTTED_BOGSPROUT,
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

        generator.registerCubeAllModelTexturePool(SoggySwampsBlocks.MOSSY_MUD_BRICKS).family(SoggySwampsBlockFamilies.MOSSY_MUD_BRICK);
        generator.registerSimpleCubeAll(SoggySwampsBlocks.CHISELED_MUD_BRICKS);

        registerTempleSpawner(generator);
    }

    public void registerSimpleStateWithYRotation(BlockStateModelGenerator generator, Block block) {
        generator.blockStateCollector.accept(
                BlockStateModelGenerator.createSingletonBlockState(
                        block,
                        BlockStateModelGenerator.modelWithYRotation(
                                BlockStateModelGenerator.createModelVariant(ModelIds.getBlockModelId(block))
                        )
                )
        );
    }

    private void registerTempleSpawner(BlockStateModelGenerator generator) {
        Block block = SoggySwampsBlocks.TEMPLE_SPAWNER;

        TextureMap textureMap = new TextureMap()
                .put(TextureKey.SIDE, TextureMap.getSubId(block, "_side"))
                .put(TextureKey.TOP, TextureMap.getSubId(block, "_top"))
                .put(TextureKey.BOTTOM, TextureMap.getSubId(block, "_bottom"));

        Identifier identifier =
                Models.CUBE_BOTTOM_TOP_INNER_FACES.upload(block, textureMap, generator.modelCollector);
        WeightedVariant variant = BlockStateModelGenerator.createWeightedVariant(identifier);

        generator.registerParentedItemModel(block, identifier);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, variant));
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
        generator.register(SoggySwampsItems.WEALTH_POTTERY_SHERD, Models.GENERATED);
        generator.register(SoggySwampsItems.SPORE_ARMOR_TRIM_SMITHING_TEMPLATE, Models.GENERATED);
    }
}
