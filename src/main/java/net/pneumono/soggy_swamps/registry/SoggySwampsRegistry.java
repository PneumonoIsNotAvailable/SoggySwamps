package net.pneumono.soggy_swamps.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.WoodType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.pneumono.soggy_swamps.SoggySwamps;
import net.pneumono.soggy_swamps.content.VenomStatusEffect;

public class SoggySwampsRegistry {
    public static RegistryEntry<StatusEffect> VENOM = Registry.registerReference(
            Registries.STATUS_EFFECT,
            SoggySwamps.id("venom"),
            new VenomStatusEffect(StatusEffectCategory.HARMFUL, 0xb3bf43)
    );
    public static RegistryEntry<Potion> VENOM_POTION = Registry.registerReference(
            Registries.POTION,
            SoggySwamps.id("venom"),
            new Potion(
                    "venom",
                    new StatusEffectInstance(VENOM, 900)
            )
    );
    public static RegistryEntry<Potion> LONG_VENOM_POTION = Registry.registerReference(
            Registries.POTION,
            SoggySwamps.id("long_venom"),
            new Potion(
                    "venom",
                    new StatusEffectInstance(VENOM, 1800)
            )
    );

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

        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerRecipes(SoggySwampsItems.SWAMP_SPIDER_EYE, VENOM_POTION);
            builder.registerPotionRecipe(VENOM_POTION, Items.REDSTONE, LONG_VENOM_POTION);
        });
    }
}
