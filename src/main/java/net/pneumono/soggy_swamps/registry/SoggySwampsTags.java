package net.pneumono.soggy_swamps.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.pneumono.soggy_swamps.SoggySwamps;

public class SoggySwampsTags {
    public static final TagKey<Block> BLOCK_SWAMP_OAK_LOGS = block("swamp_oak_logs");

    public static final TagKey<Item> ITEM_SWAMP_OAK_LOGS = item("swamp_oak_logs");

    private static TagKey<Item> item(String name) {
        return TagKey.of(RegistryKeys.ITEM, SoggySwamps.id(name));
    }

    private static TagKey<Block> block(String name) {
        return TagKey.of(RegistryKeys.BLOCK, SoggySwamps.id(name));
    }
}
