package net.pneumono.soggy_swamps;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.trim.TrimPattern;
import net.pneumono.soggy_swamps.datagen.*;

public class SoggySwampsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(SoggySwampsEnUsLangProvider::new);
		pack.addProvider(SoggySwampsModelProvider::new);
		pack.addProvider(SoggySwampsRecipeGenerator.SoggySwampsRecipeProvider::new);
		pack.addProvider(SoggySwampsBlockLootTableProvider::new);
		pack.addProvider(SoggySwampsEntityLootTableProvider::new);
		pack.addProvider(SoggySwampsBlockTagProvider::new);
		pack.addProvider(SoggySwampsItemTagProvider::new);
		pack.addProvider(SoggySwampsEntityTypeTagProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.TRIM_PATTERN, registerable -> {
			registerable.register(
					ResourceKey.create(Registries.TRIM_PATTERN, SoggySwamps.id("spore")),
					new TrimPattern(
							SoggySwamps.id("spore"),
							Component.translatable("trim_pattern.soggy_swamps.spore"),
							false
					)
			);
			registerable.register(
					ResourceKey.create(Registries.TRIM_PATTERN, SoggySwamps.id("bubble")),
					new TrimPattern(
							SoggySwamps.id("bubble"),
							Component.translatable("trim_pattern.soggy_swamps.bubble"),
							false
					)
			);
		});
	}
}
