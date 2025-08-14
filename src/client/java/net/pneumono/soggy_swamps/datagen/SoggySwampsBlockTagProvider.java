package net.pneumono.soggy_swamps.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.tag.ProvidedTagBuilder;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.pneumono.soggy_swamps.registry.SoggySwampsBlocks;
import net.pneumono.soggy_swamps.registry.SoggySwampsTags;

import java.util.concurrent.CompletableFuture;

public class SoggySwampsBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public SoggySwampsBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        tag(SoggySwampsTags.BLOCK_SWAMP_OAK_LOGS).add(
                SoggySwampsBlocks.SWAMP_OAK_LOG,
                SoggySwampsBlocks.SWAMP_OAK_WOOD,
                SoggySwampsBlocks.STRIPPED_SWAMP_OAK_LOG,
                SoggySwampsBlocks.STRIPPED_SWAMP_OAK_WOOD
        );
        tag(SoggySwampsTags.BLOCK_ALLOWS_SWAMP_RUINS).add(
                Blocks.GRASS_BLOCK,
                Blocks.DIRT,
                Blocks.MUD
        );

        tag(BlockTags.LOGS_THAT_BURN).forceAddTag(SoggySwampsTags.BLOCK_SWAMP_OAK_LOGS);

        tag(BlockTags.FROGS_SPAWNABLE_ON).add(SoggySwampsBlocks.SUSPICIOUS_MUD);
        tag(BlockTags.REPLACEABLE_BY_MUSHROOMS).add(SoggySwampsBlocks.ROT_CAP);

        tag(BlockTags.SMALL_FLOWERS).add(SoggySwampsBlocks.VIBRANT_SPROUT);
        tag(BlockTags.FLOWER_POTS).add(
                SoggySwampsBlocks.POTTED_SWAMP_OAK_SAPLING,
                SoggySwampsBlocks.POTTED_VIBRANT_SPROUT,
                SoggySwampsBlocks.POTTED_ROT_CAP
        );
        tag(BlockTags.SAPLINGS).add(SoggySwampsBlocks.SWAMP_OAK_SAPLING);
        tag(BlockTags.LEAVES).add(SoggySwampsBlocks.SWAMP_OAK_LEAVES);
        tag(BlockTags.OVERWORLD_NATURAL_LOGS).add(SoggySwampsBlocks.SWAMP_OAK_LOG);
        tag(BlockTags.PLANKS).add(SoggySwampsBlocks.SWAMP_OAK_PLANKS);
        tag(BlockTags.WOODEN_STAIRS).add(SoggySwampsBlocks.SWAMP_OAK_STAIRS);
        tag(BlockTags.WOODEN_SLABS).add(SoggySwampsBlocks.SWAMP_OAK_SLAB);
        tag(BlockTags.STANDING_SIGNS).add(SoggySwampsBlocks.SWAMP_OAK_SIGN);
        tag(BlockTags.WALL_SIGNS).add(SoggySwampsBlocks.SWAMP_OAK_WALL_SIGN);
        tag(BlockTags.CEILING_HANGING_SIGNS).add(SoggySwampsBlocks.SWAMP_OAK_HANGING_SIGN);
        tag(BlockTags.WALL_HANGING_SIGNS).add(SoggySwampsBlocks.SWAMP_OAK_WALL_HANGING_SIGN);
        tag(BlockTags.WOODEN_DOORS).add(SoggySwampsBlocks.SWAMP_OAK_DOOR);
        tag(BlockTags.WOODEN_TRAPDOORS).add(SoggySwampsBlocks.SWAMP_OAK_TRAPDOOR);
        tag(BlockTags.WOODEN_FENCES).add(SoggySwampsBlocks.SWAMP_OAK_FENCE);
        tag(BlockTags.FENCE_GATES).add(SoggySwampsBlocks.SWAMP_OAK_FENCE_GATE);
        tag(BlockTags.WOODEN_PRESSURE_PLATES).add(SoggySwampsBlocks.SWAMP_OAK_PRESSURE_PLATE);
        tag(BlockTags.WOODEN_BUTTONS).add(SoggySwampsBlocks.SWAMP_OAK_BUTTON);

        tag(BlockTags.SHOVEL_MINEABLE).add(SoggySwampsBlocks.SUSPICIOUS_MUD);

        tag(ConventionalBlockTags.SMALL_FLOWERS).add(SoggySwampsBlocks.VIBRANT_SPROUT);
        tag(ConventionalBlockTags.STRIPPED_LOGS).add(SoggySwampsBlocks.STRIPPED_SWAMP_OAK_LOG);
        tag(ConventionalBlockTags.STRIPPED_WOODS).add(SoggySwampsBlocks.STRIPPED_SWAMP_OAK_WOOD);
        tag(ConventionalBlockTags.WOODEN_FENCES).add(SoggySwampsBlocks.SWAMP_OAK_FENCE);
        tag(ConventionalBlockTags.WOODEN_FENCE_GATES).add(SoggySwampsBlocks.SWAMP_OAK_FENCE_GATE);
    }

    private ProvidedTagBuilder<Block, Block> tag(TagKey<Block> tag) {
        return valueLookupBuilder(tag);
    }
}
