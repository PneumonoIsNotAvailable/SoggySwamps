package net.pneumono.soggy_swamps.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.pneumono.soggy_swamps.SoggySwamps;

import java.util.stream.Stream;

public class SoggySwampsItemGroups {
    public static final ResourceKey<CreativeModeTab> ITEM_GROUP = ResourceKey.create(Registries.CREATIVE_MODE_TAB, SoggySwamps.id("soggy_swamps"));

    public static void registerSoggySwampsItemGroups() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ITEM_GROUP, FabricItemGroup.builder()
                .icon(SoggySwampsItems.SWAMP_OAK_SAPLING::getDefaultInstance)
                .title(Component.translatable("itemGroup.soggy_swamps.soggy_swamps"))
                .displayItems((displayContext, entries) -> {
                    entries.acceptAll(Stream.of(
                            SoggySwampsItems.SWAMP_OAK_SAPLING,
                            SoggySwampsItems.SWAMP_OAK_LEAVES,
                            SoggySwampsItems.SWAMP_OAK_LOG,
                            SoggySwampsItems.SWAMP_OAK_WOOD,
                            SoggySwampsItems.STRIPPED_SWAMP_OAK_LOG,
                            SoggySwampsItems.STRIPPED_SWAMP_OAK_WOOD,
                            SoggySwampsItems.SWAMP_OAK_PLANKS,
                            SoggySwampsItems.SWAMP_OAK_STAIRS,
                            SoggySwampsItems.SWAMP_OAK_SLAB,
                            SoggySwampsItems.SWAMP_OAK_SIGN,
                            SoggySwampsItems.SWAMP_OAK_HANGING_SIGN,
                            SoggySwampsItems.SWAMP_OAK_DOOR,
                            SoggySwampsItems.SWAMP_OAK_TRAPDOOR,
                            SoggySwampsItems.SWAMP_OAK_FENCE,
                            SoggySwampsItems.SWAMP_OAK_FENCE_GATE,
                            SoggySwampsItems.SWAMP_OAK_PRESSURE_PLATE,
                            SoggySwampsItems.SWAMP_OAK_BUTTON,
                            SoggySwampsItems.SWAMP_OAK_BOAT,
                            SoggySwampsItems.SWAMP_OAK_CHEST_BOAT,
                            SoggySwampsItems.CATTAIL,
                            SoggySwampsItems.ROT_CAP,
                            SoggySwampsItems.ROASTED_ROT_CAP,
                            SoggySwampsItems.BOGSPROUT,
                            SoggySwampsItems.SWAMP_STEW,
                            SoggySwampsItems.SUSPICIOUS_MUD,
                            SoggySwampsItems.DECAY_POTTERY_SHERD,
                            SoggySwampsItems.HAT_POTTERY_SHERD,
                            SoggySwampsItems.SLIME_POTTERY_SHERD,
                            SoggySwampsItems.WEALTH_POTTERY_SHERD,
                            SoggySwampsItems.SPORE_ARMOR_TRIM_SMITHING_TEMPLATE
                    ).map(ItemStack::new).toList());
                })
                .build()
        );

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries ->
                entries.addBefore(Items.BAMBOO_BLOCK,
                        SoggySwampsItems.SWAMP_OAK_LOG,
                        SoggySwampsItems.SWAMP_OAK_WOOD,
                        SoggySwampsItems.STRIPPED_SWAMP_OAK_LOG,
                        SoggySwampsItems.STRIPPED_SWAMP_OAK_WOOD,
                        SoggySwampsItems.SWAMP_OAK_PLANKS,
                        SoggySwampsItems.SWAMP_OAK_STAIRS,
                        SoggySwampsItems.SWAMP_OAK_SLAB,
                        SoggySwampsItems.SWAMP_OAK_FENCE,
                        SoggySwampsItems.SWAMP_OAK_FENCE_GATE,
                        SoggySwampsItems.SWAMP_OAK_DOOR,
                        SoggySwampsItems.SWAMP_OAK_TRAPDOOR,
                        SoggySwampsItems.SWAMP_OAK_PRESSURE_PLATE,
                        SoggySwampsItems.SWAMP_OAK_BUTTON
                )
        );
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> {
            entries.addBefore(Items.MUSHROOM_STEM, SoggySwampsItems.SWAMP_OAK_LOG);
            entries.addBefore(Items.AZALEA_LEAVES, SoggySwampsItems.SWAMP_OAK_LEAVES);
            entries.addBefore(Items.AZALEA, SoggySwampsItems.SWAMP_OAK_SAPLING);
            entries.addAfter(Items.FIREFLY_BUSH,
                    SoggySwampsItems.ROT_CAP,
                    SoggySwampsItems.BOGSPROUT,
                    SoggySwampsItems.CATTAIL
            );
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> {
            entries.addBefore(Items.BAMBOO_SIGN,
                    SoggySwampsItems.SWAMP_OAK_SIGN,
                    SoggySwampsItems.SWAMP_OAK_HANGING_SIGN
            );
            entries.addAfter(Items.SUSPICIOUS_GRAVEL, SoggySwampsItems.SUSPICIOUS_MUD);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries ->
                entries.addBefore(Items.BAMBOO_RAFT,
                        SoggySwampsItems.SWAMP_OAK_BOAT,
                        SoggySwampsItems.SWAMP_OAK_CHEST_BOAT
                )
        );
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(entries -> {
            entries.addAfter(Items.RABBIT_STEW, SoggySwampsItems.SWAMP_STEW);
            entries.addAfter(Items.ROTTEN_FLESH, SoggySwampsItems.ROASTED_ROT_CAP);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(entries -> {
            entries.addAfter(Items.DANGER_POTTERY_SHERD, SoggySwampsItems.DECAY_POTTERY_SHERD);
            entries.addAfter(Items.GUSTER_POTTERY_SHERD, SoggySwampsItems.HAT_POTTERY_SHERD);
            entries.addAfter(Items.SKULL_POTTERY_SHERD, SoggySwampsItems.SLIME_POTTERY_SHERD);
            entries.addAfter(Items.SNORT_POTTERY_SHERD, SoggySwampsItems.WEALTH_POTTERY_SHERD);
            entries.addAfter(Items.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE,
                    SoggySwampsItems.SPORE_ARMOR_TRIM_SMITHING_TEMPLATE
            );
        });
    }
}
