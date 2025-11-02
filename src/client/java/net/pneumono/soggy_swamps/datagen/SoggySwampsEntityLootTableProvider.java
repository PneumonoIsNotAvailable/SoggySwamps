package net.pneumono.soggy_swamps.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricEntityLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.pneumono.soggy_swamps.registry.SoggySwampsEntities;
import net.pneumono.soggy_swamps.registry.SoggySwampsItems;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class SoggySwampsEntityLootTableProvider extends FabricEntityLootTableProvider {
    public SoggySwampsEntityLootTableProvider(FabricDataOutput output, @NotNull CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generate() {
        this.add(
                SoggySwampsEntities.SWAMP_SPIDER,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(Items.STRING)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                                )
                        )
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(SoggySwampsItems.SWAMP_SPIDER_EYE)
                                        .setWeight(2)
                                )
                                .add(LootItem.lootTableItem(Items.SPIDER_EYE))
                                .add(EmptyLootItem.emptyItem()
                                        .setWeight(3)
                                )
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        )
                        .pool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(Items.SLIME_BALL)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(-1.0F, 1.0F)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                                )
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .build()
                        )
        );
    }
}
