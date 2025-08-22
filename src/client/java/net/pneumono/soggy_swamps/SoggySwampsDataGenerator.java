package net.pneumono.soggy_swamps;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.item.equipment.trim.ArmorTrimPattern;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.pneumono.soggy_swamps.datagen.*;

public class SoggySwampsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(SoggySwampsEnUsLangProvider::new);
		pack.addProvider(SoggySwampsModelProvider::new);
		pack.addProvider(SoggySwampsRecipeGenerator.RecipeProvider::new);
		pack.addProvider(SoggySwampsBlockLootTableProvider::new);
		pack.addProvider(SoggySwampsEntityLootTableProvider::new);
		pack.addProvider(SoggySwampsBlockTagProvider::new);
		pack.addProvider(SoggySwampsItemTagProvider::new);
		pack.addProvider(SoggySwampsEntityTypeTagProvider::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.TRIM_PATTERN, registerable -> {
			registerable.register(
					RegistryKey.of(RegistryKeys.TRIM_PATTERN, SoggySwamps.id("spore")),
					new ArmorTrimPattern(
							SoggySwamps.id("spore"),
							Text.translatable("trim_pattern.soggy_swamps.spore"),
							false
					)
			);
			registerable.register(
					RegistryKey.of(RegistryKeys.TRIM_PATTERN, SoggySwamps.id("bubble")),
					new ArmorTrimPattern(
							SoggySwamps.id("bubble"),
							Text.translatable("trim_pattern.soggy_swamps.bubble"),
							false
					)
			);
		});
	}
}
