package net.pneumono.soggy_swamps.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.pneumono.soggy_swamps.SoggySwamps;

import java.util.stream.Stream;

public class SoggySwampsItemGroups {
    public static final RegistryKey<ItemGroup> ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, SoggySwamps.id("soggy_swamps"));

    public static void registerSoggySwampsItemGroups() {
        Registry.register(Registries.ITEM_GROUP, ITEM_GROUP, FabricItemGroup.builder()
                .icon(SoggySwampsItems.SWAMP_OAK_SAPLING::getDefaultStack)
                .displayName(Text.translatable("itemGroup.soggy_swamps.soggy_swamps"))
                .entries((displayContext, entries) -> {
                    entries.addAll(Stream.of(
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
                            SoggySwampsItems.CHISELED_MUD_BRICKS,
                            SoggySwampsItems.MOSSY_MUD_BRICKS,
                            SoggySwampsItems.MOSSY_MUD_BRICK_STAIRS,
                            SoggySwampsItems.MOSSY_MUD_BRICK_SLAB,
                            SoggySwampsItems.MOSSY_MUD_BRICK_WALL,
                            SoggySwampsItems.CATTAIL,
                            SoggySwampsItems.ROT_CAP,
                            SoggySwampsItems.ROASTED_ROT_CAP,
                            SoggySwampsItems.VIBRANT_SPROUT,
                            SoggySwampsItems.SWAMP_SPIDER_EYE,
                            SoggySwampsItems.SWAMP_STEW,
                            SoggySwampsItems.SUSPICIOUS_MUD,
                            SoggySwampsItems.DECAY_POTTERY_SHERD,
                            SoggySwampsItems.HAT_POTTERY_SHERD,
                            SoggySwampsItems.SLIME_POTTERY_SHERD,
                            SoggySwampsItems.WEALTH_POTTERY_SHERD,
                            SoggySwampsItems.SPORE_ARMOR_TRIM_SMITHING_TEMPLATE
                    ).map(ItemStack::new).toList());

                    entries.add(PotionContentsComponent.createStack(Items.POTION, SoggySwampsRegistry.VENOM_POTION));
                    entries.add(PotionContentsComponent.createStack(Items.POTION, SoggySwampsRegistry.LONG_VENOM_POTION));
                    entries.add(PotionContentsComponent.createStack(Items.SPLASH_POTION, SoggySwampsRegistry.VENOM_POTION));
                    entries.add(PotionContentsComponent.createStack(Items.SPLASH_POTION, SoggySwampsRegistry.LONG_VENOM_POTION));
                    entries.add(PotionContentsComponent.createStack(Items.LINGERING_POTION, SoggySwampsRegistry.VENOM_POTION));
                    entries.add(PotionContentsComponent.createStack(Items.LINGERING_POTION, SoggySwampsRegistry.LONG_VENOM_POTION));
                    entries.add(PotionContentsComponent.createStack(Items.TIPPED_ARROW, SoggySwampsRegistry.VENOM_POTION));
                    entries.add(PotionContentsComponent.createStack(Items.TIPPED_ARROW, SoggySwampsRegistry.LONG_VENOM_POTION));

                    entries.addAll(Stream.of(
                            SoggySwampsItems.SWAMP_SPIDER_SPAWN_EGG
                    ).map(ItemStack::new).toList());
                })
                .build()
        );

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
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
                );
                entries.addAfter(Items.MUD_BRICK_WALL, SoggySwampsItems.CHISELED_MUD_BRICKS);
                entries.addBefore(Items.RESIN_BRICKS,
                        SoggySwampsItems.MOSSY_MUD_BRICKS,
                        SoggySwampsItems.MOSSY_MUD_BRICK_STAIRS,
                        SoggySwampsItems.MOSSY_MUD_BRICK_SLAB,
                        SoggySwampsItems.MOSSY_MUD_BRICK_WALL
                );
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addBefore(Items.MUSHROOM_STEM, SoggySwampsItems.SWAMP_OAK_LOG);
            entries.addBefore(Items.AZALEA_LEAVES, SoggySwampsItems.SWAMP_OAK_LEAVES);
            entries.addBefore(Items.AZALEA, SoggySwampsItems.SWAMP_OAK_SAPLING);
            entries.addAfter(Items.FIREFLY_BUSH,
                    SoggySwampsItems.ROT_CAP,
                    SoggySwampsItems.VIBRANT_SPROUT,
                    SoggySwampsItems.CATTAIL
            );
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.addBefore(Items.BAMBOO_SIGN,
                    SoggySwampsItems.SWAMP_OAK_SIGN,
                    SoggySwampsItems.SWAMP_OAK_HANGING_SIGN
            );
            entries.addAfter(Items.SUSPICIOUS_GRAVEL, SoggySwampsItems.SUSPICIOUS_MUD);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries ->
                entries.addBefore(Items.BAMBOO_RAFT,
                        SoggySwampsItems.SWAMP_OAK_BOAT,
                        SoggySwampsItems.SWAMP_OAK_CHEST_BOAT
                )
        );
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.addAfter(Items.SPIDER_EYE, SoggySwampsItems.SWAMP_SPIDER_EYE);
            entries.addAfter(Items.RABBIT_STEW, SoggySwampsItems.SWAMP_STEW);
            entries.addAfter(Items.ROTTEN_FLESH, SoggySwampsItems.ROASTED_ROT_CAP);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.addAfter(Items.SPIDER_EYE, SoggySwampsItems.SWAMP_SPIDER_EYE);
            entries.addAfter(Items.DANGER_POTTERY_SHERD, SoggySwampsItems.DECAY_POTTERY_SHERD);
            entries.addAfter(Items.GUSTER_POTTERY_SHERD, SoggySwampsItems.HAT_POTTERY_SHERD);
            entries.addAfter(Items.SKULL_POTTERY_SHERD, SoggySwampsItems.SLIME_POTTERY_SHERD);
            entries.addAfter(Items.SNORT_POTTERY_SHERD, SoggySwampsItems.WEALTH_POTTERY_SHERD);
            entries.addAfter(Items.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE,
                    SoggySwampsItems.SPORE_ARMOR_TRIM_SMITHING_TEMPLATE
            );
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries ->
                entries.addBefore(Items.TADPOLE_SPAWN_EGG, SoggySwampsItems.SWAMP_SPIDER_SPAWN_EGG));
    }
}
