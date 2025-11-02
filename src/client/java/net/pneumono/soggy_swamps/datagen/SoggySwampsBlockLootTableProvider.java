package net.pneumono.soggy_swamps.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.registry.RegistryWrapper;
import net.pneumono.soggy_swamps.registry.SoggySwampsBlocks;

import java.util.concurrent.CompletableFuture;

public class SoggySwampsBlockLootTableProvider extends FabricBlockLootTableProvider {
    public SoggySwampsBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(
                SoggySwampsBlocks.CATTAIL,
                block -> dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER)
        );
        addDrop(SoggySwampsBlocks.ROT_CAP);
        addPottedPlantDrops(SoggySwampsBlocks.POTTED_ROT_CAP);
        addDrop(SoggySwampsBlocks.BOGSPROUT);
        addPottedPlantDrops(SoggySwampsBlocks.POTTED_BOGSPROUT);
        addDrop(SoggySwampsBlocks.SWAMP_OAK_SAPLING);
        addPottedPlantDrops(SoggySwampsBlocks.POTTED_SWAMP_OAK_SAPLING);
        addDrop(
                SoggySwampsBlocks.SWAMP_OAK_LEAVES,
                block -> oakLeavesDrops(block, SoggySwampsBlocks.SWAMP_OAK_SAPLING, SAPLING_DROP_CHANCE)
        );
        addDrop(SoggySwampsBlocks.SWAMP_OAK_LOG);
        addDrop(SoggySwampsBlocks.SWAMP_OAK_WOOD);
        addDrop(SoggySwampsBlocks.STRIPPED_SWAMP_OAK_LOG);
        addDrop(SoggySwampsBlocks.STRIPPED_SWAMP_OAK_WOOD);
        addDrop(SoggySwampsBlocks.SWAMP_OAK_PLANKS);
        addDrop(SoggySwampsBlocks.SWAMP_OAK_STAIRS);
        addDrop(SoggySwampsBlocks.SWAMP_OAK_SLAB, this::slabDrops);
        addDrop(SoggySwampsBlocks.SWAMP_OAK_SIGN);
        addDrop(SoggySwampsBlocks.SWAMP_OAK_HANGING_SIGN);
        addDrop(SoggySwampsBlocks.SWAMP_OAK_DOOR, this::doorDrops);
        addDrop(SoggySwampsBlocks.SWAMP_OAK_TRAPDOOR);
        addDrop(SoggySwampsBlocks.SWAMP_OAK_FENCE);
        addDrop(SoggySwampsBlocks.SWAMP_OAK_FENCE_GATE);
        addDrop(SoggySwampsBlocks.SWAMP_OAK_PRESSURE_PLATE);
        addDrop(SoggySwampsBlocks.SWAMP_OAK_BUTTON);
        addDrop(SoggySwampsBlocks.MOSSY_MUD_BRICKS);
        addDrop(SoggySwampsBlocks.MOSSY_MUD_BRICK_STAIRS);
        addDrop(SoggySwampsBlocks.MOSSY_MUD_BRICK_SLAB, this::slabDrops);
        addDrop(SoggySwampsBlocks.MOSSY_MUD_BRICK_WALL);
        addDrop(SoggySwampsBlocks.CHISELED_MUD_BRICKS);
    }
}
