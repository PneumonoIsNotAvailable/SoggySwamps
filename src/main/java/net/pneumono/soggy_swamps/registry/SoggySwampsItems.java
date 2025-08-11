package net.pneumono.soggy_swamps.registry;

import net.minecraft.block.Block;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.pneumono.soggy_swamps.SoggySwamps;

import java.util.List;
import java.util.function.Function;

public class SoggySwampsItems {
    public static final SpawnEggItem SWAMP_SPIDER_SPAWN_EGG = register(
            "swamp_spider_spawn_egg",
            settings -> new SpawnEggItem(SoggySwampsEntities.SWAMP_SPIDER, settings),
            new Item.Settings()
    );
    public static final Item SWAMP_SPIDER_EYE = register(
            "swamp_spider_eye",
            Item::new,
            new Item.Settings().food(
                    new FoodComponent.Builder().nutrition(3).saturationModifier(0.8F).build(),
                    ConsumableComponents.food()
                            .consumeEffect(new ApplyEffectsConsumeEffect(List.of(
                                    new StatusEffectInstance(SoggySwampsRegistry.VENOM, 160, 0),
                                    new StatusEffectInstance(StatusEffects.POISON, 100, 0))
                            ))
                            .build()
            )
    );

    public static final BlockItem SUSPICIOUS_MUD = registerBlockItem(SoggySwampsBlocks.SUSPICIOUS_MUD);

    public static final BlockItem ROT_CAP = registerBlockItem(SoggySwampsBlocks.ROT_CAP);

    public static final BlockItem SWAMP_OAK_SAPLING = registerBlockItem(SoggySwampsBlocks.SWAMP_OAK_SAPLING);
    public static final BlockItem SWAMP_OAK_LEAVES = registerBlockItem(SoggySwampsBlocks.SWAMP_OAK_LEAVES);
    public static final BlockItem SWAMP_OAK_LOG = registerBlockItem(SoggySwampsBlocks.SWAMP_OAK_LOG);
    public static final BlockItem SWAMP_OAK_WOOD = registerBlockItem(SoggySwampsBlocks.SWAMP_OAK_WOOD);
    public static final BlockItem STRIPPED_SWAMP_OAK_LOG = registerBlockItem(SoggySwampsBlocks.STRIPPED_SWAMP_OAK_LOG);
    public static final BlockItem STRIPPED_SWAMP_OAK_WOOD = registerBlockItem(SoggySwampsBlocks.STRIPPED_SWAMP_OAK_WOOD);
    public static final BlockItem SWAMP_OAK_PLANKS = registerBlockItem(SoggySwampsBlocks.SWAMP_OAK_PLANKS);
    public static final BlockItem SWAMP_OAK_STAIRS = registerBlockItem(SoggySwampsBlocks.SWAMP_OAK_STAIRS);
    public static final BlockItem SWAMP_OAK_SLAB = registerBlockItem(SoggySwampsBlocks.SWAMP_OAK_SLAB);
    public static final SignItem SWAMP_OAK_SIGN = register(
            "swamp_oak_sign",
            settings -> new SignItem(SoggySwampsBlocks.SWAMP_OAK_SIGN, SoggySwampsBlocks.SWAMP_OAK_WALL_SIGN, settings),
            new Item.Settings().useBlockPrefixedTranslationKey().maxCount(16)
    );
    public static final SignItem SWAMP_OAK_HANGING_SIGN = register(
            "swamp_oak_hanging_sign",
            settings -> new SignItem(SoggySwampsBlocks.SWAMP_OAK_HANGING_SIGN, SoggySwampsBlocks.SWAMP_OAK_WALL_HANGING_SIGN, settings),
            new Item.Settings().useBlockPrefixedTranslationKey().maxCount(16)
    );
    public static final TallBlockItem SWAMP_OAK_DOOR = register(
            "swamp_oak_door",
            settings -> new TallBlockItem(SoggySwampsBlocks.SWAMP_OAK_DOOR, settings),
            new Item.Settings().useBlockPrefixedTranslationKey()
    );
    public static final BlockItem SWAMP_OAK_TRAPDOOR = registerBlockItem(SoggySwampsBlocks.SWAMP_OAK_TRAPDOOR);
    public static final BlockItem SWAMP_OAK_FENCE = registerBlockItem(SoggySwampsBlocks.SWAMP_OAK_FENCE);
    public static final BlockItem SWAMP_OAK_FENCE_GATE = registerBlockItem(SoggySwampsBlocks.SWAMP_OAK_FENCE_GATE);
    public static final BlockItem SWAMP_OAK_PRESSURE_PLATE = registerBlockItem(SoggySwampsBlocks.SWAMP_OAK_PRESSURE_PLATE);
    public static final BlockItem SWAMP_OAK_BUTTON = registerBlockItem(SoggySwampsBlocks.SWAMP_OAK_BUTTON);
    public static final BoatItem SWAMP_OAK_BOAT = register(
            "swamp_oak_boat",
            settings -> new BoatItem(SoggySwampsEntities.SWAMP_OAK_BOAT, settings),
            new Item.Settings().maxCount(1)
    );
    public static final BoatItem SWAMP_OAK_CHEST_BOAT = register(
            "swamp_oak_chest_boat",
            settings -> new BoatItem(SoggySwampsEntities.SWAMP_OAK_CHEST_BOAT, settings),
            new Item.Settings().maxCount(1)
    );

    @SuppressWarnings("deprecation")
    protected static BlockItem registerBlockItem(Block block) {
        return SoggySwampsItems.register(
                block.getRegistryEntry().registryKey().getValue().getPath(),
                settings -> new BlockItem(block, settings),
                new Item.Settings().useBlockPrefixedTranslationKey()
        );
    }

    protected static <T extends Item> T register(String name, Function<Item.Settings, T> factory, Item.Settings settings) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, SoggySwamps.id(name));
        return Registry.register(Registries.ITEM, key, factory.apply(settings.registryKey(key)));
    }

    public static void registerSoggySwampsItems() {

    }
}
