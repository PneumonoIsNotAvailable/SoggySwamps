package net.pneumono.soggy_swamps;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.pneumono.soggy_swamps.datagen.*;

public class SoggySwampsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(SoggySwampsEnUsLangProvider::new);
		pack.addProvider(SoggySwampsModelProvider::new);
		pack.addProvider(SoggySwampsBlockLootTableProvider::new);
		pack.addProvider(SoggySwampsRecipeGenerator.RecipeProvider::new);
		pack.addProvider(SoggySwampsBlockTagProvider::new);
		pack.addProvider(SoggySwampsItemTagProvider::new);
		pack.addProvider(SoggySwampsEntityTypeTagProvider::new);
	}
}
