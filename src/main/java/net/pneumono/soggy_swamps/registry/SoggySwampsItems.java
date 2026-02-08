package net.pneumono.soggy_swamps.registry;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.pneumono.soggy_swamps.SoggySwamps;

import java.util.function.Function;

public class SoggySwampsItems {
    public static final Item SWAMP_STEW = register(
            "swamp_stew",
            Item::new,
            new Item.Properties().usingConvertsTo(Items.BOWL).stacksTo(1).food(
                    new FoodProperties.Builder().nutrition(10).saturationModifier(0.8F).build(),
                    Consumables.defaultFood()
                            .onConsume(new ApplyStatusEffectsConsumeEffect(
                                    new MobEffectInstance(MobEffects.HUNGER, 200, 0)
                            ))
                            .build()
            )
    );
    public static final Item ROASTED_ROT_CAP = register(
            "roasted_rot_cap",
            Item::new,
            new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(6).saturationModifier(0.3F).build(),
                    Consumables.defaultFood()
                            .onConsume(new ApplyStatusEffectsConsumeEffect(
                                    new MobEffectInstance(MobEffects.HUNGER, 200, 0), 0.7F
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
            SmithingTemplateItem::createArmorTrimTemplate,
            new Item.Properties().rarity(Rarity.UNCOMMON)
    );

    public static final BlockItem CATTAIL = registerBlockItem(SoggySwampsBlocks.CATTAIL);
    public static final BlockItem ROT_CAP = registerBlockItem(SoggySwampsBlocks.ROT_CAP);
    public static final BlockItem BOGSPROUT = registerBlockItem(SoggySwampsBlocks.BOGSPROUT);
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
            new Item.Properties().useBlockDescriptionPrefix().stacksTo(16)
    );
    public static final SignItem SWAMP_OAK_HANGING_SIGN = register(
            "swamp_oak_hanging_sign",
            settings -> new SignItem(SoggySwampsBlocks.SWAMP_OAK_HANGING_SIGN, SoggySwampsBlocks.SWAMP_OAK_WALL_HANGING_SIGN, settings),
            new Item.Properties().useBlockDescriptionPrefix().stacksTo(16)
    );
    public static final DoubleHighBlockItem SWAMP_OAK_DOOR = register(
            "swamp_oak_door",
            settings -> new DoubleHighBlockItem(SoggySwampsBlocks.SWAMP_OAK_DOOR, settings),
            new Item.Properties().useBlockDescriptionPrefix()
    );
    public static final BlockItem SWAMP_OAK_TRAPDOOR = registerBlockItem(SoggySwampsBlocks.SWAMP_OAK_TRAPDOOR);
    public static final BlockItem SWAMP_OAK_FENCE = registerBlockItem(SoggySwampsBlocks.SWAMP_OAK_FENCE);
    public static final BlockItem SWAMP_OAK_FENCE_GATE = registerBlockItem(SoggySwampsBlocks.SWAMP_OAK_FENCE_GATE);
    public static final BlockItem SWAMP_OAK_PRESSURE_PLATE = registerBlockItem(SoggySwampsBlocks.SWAMP_OAK_PRESSURE_PLATE);
    public static final BlockItem SWAMP_OAK_BUTTON = registerBlockItem(SoggySwampsBlocks.SWAMP_OAK_BUTTON);
    public static final BoatItem SWAMP_OAK_BOAT = register(
            "swamp_oak_boat",
            settings -> new BoatItem(SoggySwampsEntities.SWAMP_OAK_BOAT, settings),
            new Item.Properties().stacksTo(1)
    );
    public static final BoatItem SWAMP_OAK_CHEST_BOAT = register(
            "swamp_oak_chest_boat",
            settings -> new BoatItem(SoggySwampsEntities.SWAMP_OAK_CHEST_BOAT, settings),
            new Item.Properties().stacksTo(1)
    );

    public static final BlockItem CHISELED_MUD_BRICKS = registerBlockItem(SoggySwampsBlocks.CHISELED_MUD_BRICKS);
    public static final BlockItem MOSSY_MUD_BRICKS = registerBlockItem(SoggySwampsBlocks.MOSSY_MUD_BRICKS);
    public static final BlockItem MOSSY_MUD_BRICK_STAIRS = registerBlockItem(SoggySwampsBlocks.MOSSY_MUD_BRICK_STAIRS);
    public static final BlockItem MOSSY_MUD_BRICK_SLAB = registerBlockItem(SoggySwampsBlocks.MOSSY_MUD_BRICK_SLAB);
    public static final BlockItem MOSSY_MUD_BRICK_WALL = registerBlockItem(SoggySwampsBlocks.MOSSY_MUD_BRICK_WALL);

    @SuppressWarnings("deprecation")
    protected static BlockItem registerBlockItem(Block block) {
        return SoggySwampsItems.register(
                block.builtInRegistryHolder().key().identifier().getPath(),
                settings -> new BlockItem(block, settings),
                new Item.Properties().useBlockDescriptionPrefix()
        );
    }

    protected static Item registerPotterySherd(String name) {
        ResourceKey<DecoratedPotPattern> key = ResourceKey.create(Registries.DECORATED_POT_PATTERN, SoggySwamps.id(name));
        Registry.register(BuiltInRegistries.DECORATED_POT_PATTERN, key, new DecoratedPotPattern(SoggySwamps.id(name + "_pottery_pattern")));

        Item item = register(name + "_pottery_sherd", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON));

        SoggySwampsRegistry.SHERD_TO_PATTERN.put(item, key);

        return item;
    }

    protected static <T extends Item> T register(String name, Function<Item.Properties, T> factory, Item.Properties settings) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, SoggySwamps.id(name));
        return Registry.register(BuiltInRegistries.ITEM, key, factory.apply(settings.setId(key)));
    }

    public static void registerSoggySwampsItems() {
        CompostingChanceRegistry composting = CompostingChanceRegistry.INSTANCE;
        composting.add(ROT_CAP, 0.65F);
        composting.add(ROASTED_ROT_CAP, 0.5F);
        composting.add(BOGSPROUT, 0.85F);
        composting.add(CATTAIL, 0.65F);
        composting.add(SWAMP_OAK_SAPLING, 0.3F);
        composting.add(SWAMP_OAK_LEAVES, 0.3F);
    }
}
