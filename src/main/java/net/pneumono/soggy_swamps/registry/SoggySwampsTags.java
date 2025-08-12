package net.pneumono.soggy_swamps.registry;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.pneumono.soggy_swamps.SoggySwamps;

public class SoggySwampsTags {
    public static final TagKey<Block> BLOCK_SWAMP_OAK_LOGS = block("swamp_oak_logs");
    public static final TagKey<Block> BLOCK_ALLOWS_SWAMP_RUINS = block("allows_swamp_ruins");

    public static final TagKey<Item> ITEM_SWAMP_OAK_LOGS = item("swamp_oak_logs");

    public static final TagKey<EntityType<?>> ENTITY_ALCHEMIST_ALLIES = entity("alchemist_allies");

    private static TagKey<Item> item(String name) {
        return TagKey.of(RegistryKeys.ITEM, SoggySwamps.id(name));
    }

    private static TagKey<Block> block(String name) {
        return TagKey.of(RegistryKeys.BLOCK, SoggySwamps.id(name));
    }

    private static TagKey<EntityType<?>> entity(String name) {
        return TagKey.of(RegistryKeys.ENTITY_TYPE, SoggySwamps.id(name));
    }
}
