package net.pneumono.soggy_swamps.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.pneumono.soggy_swamps.SoggySwamps;

import java.util.stream.Stream;

public class SoggySwampsItemGroups {
    public static final RegistryKey<ItemGroup> ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, SoggySwamps.id("soggy_swamps"));

    public static void registerItemGroups() {
        Registry.register(Registries.ITEM_GROUP, ITEM_GROUP, FabricItemGroup.builder()
                .icon(SoggySwampsItems.SWAMP_OAK_SAPLING::getDefaultStack)
                .displayName(Text.translatable("itemGroup.soggy_swamps.soggy_swamps"))
                .entries((displayContext, entries) -> {
                    entries.addAll(Stream.of(
                            SoggySwampsItems.SWAMP_SPIDER_SPAWN_EGG,
                            SoggySwampsItems.SWAMP_SPIDER_EYE,
                            SoggySwampsItems.ROT_CAP,
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
                            SoggySwampsItems.SWAMP_OAK_CHEST_BOAT
                    ).map(Item::getDefaultStack).toList());

                    entries.add(PotionContentsComponent.createStack(Items.POTION, SoggySwampsRegistry.VENOM_POTION));
                    entries.add(PotionContentsComponent.createStack(Items.POTION, SoggySwampsRegistry.LONG_VENOM_POTION));
                    entries.add(PotionContentsComponent.createStack(Items.SPLASH_POTION, SoggySwampsRegistry.LONG_VENOM_POTION));
                    entries.add(PotionContentsComponent.createStack(Items.SPLASH_POTION, SoggySwampsRegistry.VENOM_POTION));
                    entries.add(PotionContentsComponent.createStack(Items.LINGERING_POTION, SoggySwampsRegistry.VENOM_POTION));
                    entries.add(PotionContentsComponent.createStack(Items.LINGERING_POTION, SoggySwampsRegistry.LONG_VENOM_POTION));
                    entries.add(PotionContentsComponent.createStack(Items.TIPPED_ARROW, SoggySwampsRegistry.VENOM_POTION));
                    entries.add(PotionContentsComponent.createStack(Items.TIPPED_ARROW, SoggySwampsRegistry.LONG_VENOM_POTION));

                })
                .build()
        );

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries ->
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
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addBefore(Items.MUSHROOM_STEM, SoggySwampsItems.SWAMP_OAK_LOG);
            entries.addBefore(Items.AZALEA_LEAVES, SoggySwampsItems.SWAMP_OAK_LEAVES);
            entries.addBefore(Items.AZALEA, SoggySwampsItems.SWAMP_OAK_SAPLING);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries ->
                entries.addBefore(Items.BAMBOO_SIGN,
                        SoggySwampsItems.SWAMP_OAK_SIGN,
                        SoggySwampsItems.SWAMP_OAK_HANGING_SIGN
                )
        );
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries ->
                entries.addBefore(Items.BAMBOO_RAFT,
                        SoggySwampsItems.SWAMP_OAK_BOAT,
                        SoggySwampsItems.SWAMP_OAK_CHEST_BOAT
                )
        );
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries ->
                entries.addAfter(Items.SPIDER_EYE, SoggySwampsItems.SWAMP_SPIDER_EYE)
        );
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries ->
                entries.addAfter(Items.SPIDER_EYE, SoggySwampsItems.SWAMP_SPIDER_EYE)
        );
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries ->
                entries.addBefore(Items.TADPOLE_SPAWN_EGG, SoggySwampsItems.SWAMP_SPIDER_SPAWN_EGG)
        );
    }
}
