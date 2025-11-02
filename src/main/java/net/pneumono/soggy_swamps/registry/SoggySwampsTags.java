package net.pneumono.soggy_swamps.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.pneumono.soggy_swamps.SoggySwamps;

public class SoggySwampsTags {
    public static final TagKey<Block> BLOCK_SWAMP_OAK_LOGS = block("swamp_oak_logs");
    public static final TagKey<Block> BLOCK_ALLOWS_SWAMP_RUINS = block("allows_swamp_ruins");

    public static final TagKey<Item> ITEM_SWAMP_OAK_LOGS = item("swamp_oak_logs");

    private static TagKey<Item> item(String name) {
        return TagKey.create(Registries.ITEM, SoggySwamps.id(name));
    }

    private static TagKey<Block> block(String name) {
        return TagKey.create(Registries.BLOCK, SoggySwamps.id(name));
    }
}
