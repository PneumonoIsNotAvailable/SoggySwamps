package net.pneumono.soggy_swamps.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.pneumono.soggy_swamps.registry.SoggySwampsBlocks;

import java.util.concurrent.CompletableFuture;

public class SoggySwampsBlockLootTableProvider extends FabricBlockLootTableProvider {
    public SoggySwampsBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        add(
                SoggySwampsBlocks.CATTAIL,
                block -> createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
        );
        dropSelf(SoggySwampsBlocks.ROT_CAP);
        dropPottedContents(SoggySwampsBlocks.POTTED_ROT_CAP);
        dropSelf(SoggySwampsBlocks.BOGSPROUT);
        dropPottedContents(SoggySwampsBlocks.POTTED_BOGSPROUT);
        dropSelf(SoggySwampsBlocks.SWAMP_OAK_SAPLING);
        dropPottedContents(SoggySwampsBlocks.POTTED_SWAMP_OAK_SAPLING);
        add(
                SoggySwampsBlocks.SWAMP_OAK_LEAVES,
                block -> createOakLeavesDrops(block, SoggySwampsBlocks.SWAMP_OAK_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES)
        );
        dropSelf(SoggySwampsBlocks.SWAMP_OAK_LOG);
        dropSelf(SoggySwampsBlocks.SWAMP_OAK_WOOD);
        dropSelf(SoggySwampsBlocks.STRIPPED_SWAMP_OAK_LOG);
        dropSelf(SoggySwampsBlocks.STRIPPED_SWAMP_OAK_WOOD);
        dropSelf(SoggySwampsBlocks.SWAMP_OAK_PLANKS);
        dropSelf(SoggySwampsBlocks.SWAMP_OAK_STAIRS);
        add(SoggySwampsBlocks.SWAMP_OAK_SLAB, this::createSlabItemTable);
        dropSelf(SoggySwampsBlocks.SWAMP_OAK_SIGN);
        dropSelf(SoggySwampsBlocks.SWAMP_OAK_HANGING_SIGN);
        add(SoggySwampsBlocks.SWAMP_OAK_DOOR, this::createDoorTable);
        dropSelf(SoggySwampsBlocks.SWAMP_OAK_TRAPDOOR);
        dropSelf(SoggySwampsBlocks.SWAMP_OAK_FENCE);
        dropSelf(SoggySwampsBlocks.SWAMP_OAK_FENCE_GATE);
        dropSelf(SoggySwampsBlocks.SWAMP_OAK_PRESSURE_PLATE);
        dropSelf(SoggySwampsBlocks.SWAMP_OAK_BUTTON);
    }
}
