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
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryWrapper;
import net.pneumono.soggy_swamps.registry.SoggySwampsEntities;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class SoggySwampsEntityLootTableProvider extends FabricEntityLootTableProvider {
    public SoggySwampsEntityLootTableProvider(FabricDataOutput output, @NotNull CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generate() {
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
