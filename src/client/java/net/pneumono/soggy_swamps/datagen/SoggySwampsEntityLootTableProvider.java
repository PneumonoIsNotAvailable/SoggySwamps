package net.pneumono.soggy_swamps.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricEntityLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.function.SetPotionLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.potion.Potions;
import net.minecraft.registry.RegistryWrapper;
import net.pneumono.soggy_swamps.registry.SoggySwampsEntities;
import net.pneumono.soggy_swamps.registry.SoggySwampsItems;
import net.pneumono.soggy_swamps.registry.SoggySwampsRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class SoggySwampsEntityLootTableProvider extends FabricEntityLootTableProvider {
    public SoggySwampsEntityLootTableProvider(FabricDataOutput output, @NotNull CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generate() {
        this.register(
                SoggySwampsEntities.ALCHEMIST,
                LootTable.builder()
                        .pool(
                                LootPool.builder()
                                        .rolls(UniformLootNumberProvider.create(1.0F, 3.0F))
                                        .with(
                                                ItemEntry.builder(Items.GLOWSTONE_DUST)
                                                        .weight(2)
                                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F)))
                                                        .apply(EnchantedCountIncreaseLootFunction.builder(this.registries, UniformLootNumberProvider.create(0.0F, 1.0F)))
                                        )
                                        .with(
                                                ItemEntry.builder(Items.GOLDEN_CARROT)
                                                        .weight(2)
                                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F)))
                                                        .apply(EnchantedCountIncreaseLootFunction.builder(this.registries, UniformLootNumberProvider.create(0.0F, 2.0F)))
                                        )
                                        .with(
                                                ItemEntry.builder(Items.FERMENTED_SPIDER_EYE)
                                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F)))
                                                        .apply(EnchantedCountIncreaseLootFunction.builder(this.registries, UniformLootNumberProvider.create(0.0F, 1.0F)))
                                        )
                                        .with(
                                                ItemEntry.builder(SoggySwampsItems.SWAMP_SPIDER_EYE)
                                                        .weight(3)
                                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F)))
                                                        .apply(EnchantedCountIncreaseLootFunction.builder(this.registries, UniformLootNumberProvider.create(0.0F, 1.0F)))
                                        )
                                        .with(
                                                ItemEntry.builder(Items.GLASS_BOTTLE)
                                                        .weight(3)
                                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                                                        .apply(EnchantedCountIncreaseLootFunction.builder(this.registries, UniformLootNumberProvider.create(0.0F, 1.0F)))
                                        )
                                        .with(
                                                ItemEntry.builder(Items.TURTLE_SCUTE)
                                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F)))
                                                        .apply(EnchantedCountIncreaseLootFunction.builder(this.registries, UniformLootNumberProvider.create(0.0F, 2.0F)))
                                        )
                                        .with(
                                                ItemEntry.builder(Items.RABBIT_FOOT)
                                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                                                        .apply(EnchantedCountIncreaseLootFunction.builder(this.registries, UniformLootNumberProvider.create(0.0F, 1.0F)))
                                        )
                        )
                        .pool(
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(
                                                ItemEntry.builder(Items.REDSTONE)
                                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(5.0F, 9.0F)))
                                                        .apply(EnchantedCountIncreaseLootFunction.builder(this.registries, UniformLootNumberProvider.create(0.0F, 1.0F)))
                                        )
                        )
                        .pool(
                                LootPool.builder()
                                        .rolls(UniformLootNumberProvider.create(1.0F, 2.0F))
                                        .with(
                                                ItemEntry.builder(Items.POTION)
                                                        .apply(SetPotionLootFunction.builder(Potions.STRONG_HEALING))
                                        )
                                        .with(
                                                ItemEntry.builder(Items.POTION)
                                                        .apply(SetPotionLootFunction.builder(Potions.STRONG_REGENERATION))
                                        )
                                        .with(
                                                ItemEntry.builder(Items.POTION)
                                                        .apply(SetPotionLootFunction.builder(Potions.STRONG_SWIFTNESS))
                                        )
                                        .with(
                                                ItemEntry.builder(Items.SPLASH_POTION)
                                                        .apply(SetPotionLootFunction.builder(Potions.STRONG_HARMING))
                                        )
                                        .with(
                                                ItemEntry.builder(Items.SPLASH_POTION)
                                                        .apply(SetPotionLootFunction.builder(Potions.STRONG_POISON))
                                        )
                                        .with(
                                                ItemEntry.builder(Items.SPLASH_POTION)
                                                        .apply(SetPotionLootFunction.builder(SoggySwampsRegistry.LONG_VENOM_POTION))
                                        )
                                        .with(
                                                ItemEntry.builder(Items.LINGERING_POTION)
                                                        .apply(SetPotionLootFunction.builder(Potions.STRONG_POISON))
                                        )
                                        .with(
                                                ItemEntry.builder(Items.LINGERING_POTION)
                                                        .apply(SetPotionLootFunction.builder(SoggySwampsRegistry.LONG_VENOM_POTION))
                                        )
                        )
        );
        this.register(
                SoggySwampsEntities.SWAMP_SPIDER,
                LootTable.builder()
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(Items.STRING)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
                                        .apply(EnchantedCountIncreaseLootFunction.builder(this.registries, UniformLootNumberProvider.create(0.0F, 1.0F)))
                                )
                        )
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(Items.SPIDER_EYE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(-1.0F, 1.0F)))
                                        .apply(EnchantedCountIncreaseLootFunction.builder(this.registries, UniformLootNumberProvider.create(0.0F, 1.0F)))
                                )
                                .conditionally(KilledByPlayerLootCondition.builder())
                        )
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(Items.SLIME_BALL)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(-1.0F, 1.0F)))
                                        .apply(EnchantedCountIncreaseLootFunction.builder(this.registries, UniformLootNumberProvider.create(0.0F, 1.0F)))
                                )
                                .conditionally(KilledByPlayerLootCondition.builder())
                        )
        );
    }
}
