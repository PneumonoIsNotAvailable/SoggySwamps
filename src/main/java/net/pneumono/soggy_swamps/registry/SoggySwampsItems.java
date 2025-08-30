package net.pneumono.soggy_swamps.registry;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.DecoratedPotPattern;
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
import net.minecraft.util.Rarity;
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
    public static final Item SWAMP_STEW = register(
            "swamp_stew",
            Item::new,
            new Item.Settings().useRemainder(Items.BOWL).maxCount(1).food(
                    new FoodComponent.Builder().nutrition(10).saturationModifier(0.8F).build(),
                    ConsumableComponents.food()
                            .consumeEffect(new ApplyEffectsConsumeEffect(
                                    new StatusEffectInstance(SoggySwampsRegistry.VENOM, 200, 0)
                            ))
                            .build()
            )
    );
    public static final Item ROASTED_ROT_CAP = register(
            "roasted_rot_cap",
            Item::new,
            new Item.Settings().food(
                    new FoodComponent.Builder().nutrition(6).saturationModifier(0.3F).build(),
                    ConsumableComponents.food()
                            .consumeEffect(new ApplyEffectsConsumeEffect(
                                    new StatusEffectInstance(StatusEffects.HUNGER, 600, 0), 0.7F
                            ))
                            .build()
            )
    );

    public static final BlockItem SUSPICIOUS_MUD = registerBlockItem(SoggySwampsBlocks.SUSPICIOUS_MUD);
    public static final Item HAT_POTTERY_SHERD = registerPotterySherd("hat");
    public static final Item SLIME_POTTERY_SHERD = registerPotterySherd("slime");
    public static final Item DECAY_POTTERY_SHERD = registerPotterySherd("decay");
    public static final Item WEALTH_POTTERY_SHERD = registerPotterySherd("wealth");
    public static final SmithingTemplateItem SPORE_ARMOR_TRIM_SMITHING_TEMPLATE = register(
            "spore_armor_trim_smithing_template",
            SmithingTemplateItem::of,
            new Item.Settings().rarity(Rarity.UNCOMMON)
    );

    public static final BlockItem CATTAIL = registerBlockItem(SoggySwampsBlocks.CATTAIL);
    public static final BlockItem ROT_CAP = registerBlockItem(SoggySwampsBlocks.ROT_CAP);
    public static final BlockItem VIBRANT_SPROUT = registerBlockItem(SoggySwampsBlocks.VIBRANT_SPROUT);
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

    public static final BlockItem MOSSY_MUD_BRICKS = registerBlockItem(SoggySwampsBlocks.MOSSY_MUD_BRICKS);
    public static final BlockItem MOSSY_MUD_BRICK_STAIRS = registerBlockItem(SoggySwampsBlocks.MOSSY_MUD_BRICK_STAIRS);
    public static final BlockItem MOSSY_MUD_BRICK_SLAB = registerBlockItem(SoggySwampsBlocks.MOSSY_MUD_BRICK_SLAB);
    public static final BlockItem MOSSY_MUD_BRICK_WALL = registerBlockItem(SoggySwampsBlocks.MOSSY_MUD_BRICK_WALL);
    public static final BlockItem CHISELED_MUD_BRICKS = registerBlockItem(SoggySwampsBlocks.CHISELED_MUD_BRICKS);
    public static final BlockItem TEMPLE_SPAWNER = registerBlockItem(SoggySwampsBlocks.TEMPLE_SPAWNER);

    @SuppressWarnings("deprecation")
    protected static BlockItem registerBlockItem(Block block) {
        return SoggySwampsItems.register(
                block.getRegistryEntry().registryKey().getValue().getPath(),
                settings -> new BlockItem(block, settings),
                new Item.Settings().useBlockPrefixedTranslationKey()
        );
    }

    protected static Item registerPotterySherd(String name) {
        RegistryKey<DecoratedPotPattern> key = RegistryKey.of(RegistryKeys.DECORATED_POT_PATTERN, SoggySwamps.id(name));
        Registry.register(Registries.DECORATED_POT_PATTERN, key, new DecoratedPotPattern(SoggySwamps.id(name + "_pottery_pattern")));

        Item item = register(name + "_pottery_sherd", Item::new, new Item.Settings().rarity(Rarity.UNCOMMON));

        SoggySwampsRegistry.SHERD_TO_PATTERN.put(item, key);

        return item;
    }

    protected static <T extends Item> T register(String name, Function<Item.Settings, T> factory, Item.Settings settings) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, SoggySwamps.id(name));
        return Registry.register(Registries.ITEM, key, factory.apply(settings.registryKey(key)));
    }

    public static void registerSoggySwampsItems() {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerRecipes(SWAMP_SPIDER_EYE, SoggySwampsRegistry.VENOM_POTION);
            builder.registerPotionRecipe(SoggySwampsRegistry.VENOM_POTION, Items.REDSTONE, SoggySwampsRegistry.LONG_VENOM_POTION);
        });

        CompostingChanceRegistry composting = CompostingChanceRegistry.INSTANCE;
        composting.add(ROT_CAP, 0.65F);
        composting.add(ROASTED_ROT_CAP, 0.5F);
        composting.add(VIBRANT_SPROUT, 0.85F);
        composting.add(CATTAIL, 0.65F);
        composting.add(SWAMP_OAK_SAPLING, 0.3F);
        composting.add(SWAMP_OAK_LEAVES, 0.3F);
    }
}
