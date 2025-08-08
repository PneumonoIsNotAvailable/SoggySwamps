package net.pneumono.soggy_swamps.content;

import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.WoodType;
import net.pneumono.soggy_swamps.SoggySwamps;

public class SoggySwampsRegistry {
    public static final BlockSetType SWAMP_OAK_BLOCK_SET_TYPE = BlockSetTypeBuilder
            .copyOf(BlockSetType.OAK)
            .register(SoggySwamps.id("swamp_oak"));
    public static final WoodType SWAMP_OAK_WOOD_TYPE = WoodTypeBuilder
            .copyOf(WoodType.OAK)
            .register(SoggySwamps.id("swamp_oak"), SWAMP_OAK_BLOCK_SET_TYPE);

    public static void registerSoggySwampsContent() {
        SoggySwampsBlocks.registerSoggySwampsBlocks();
        SoggySwampsItems.registerSoggySwampsItems();
        SoggySwampsEntities.registerSoggySwampsEntities();
    }
}
