package net.pneumono.soggy_swamps;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import net.pneumono.soggy_swamps.registry.SoggySwampsRegistry;
import net.pneumono.soggy_swamps.worldgen.SoggySwampsWorldgen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoggySwamps implements ModInitializer {
	public static final String MOD_ID = "soggy_swamps";

	public static final Logger LOGGER = LoggerFactory.getLogger("Soggy Swamps");

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Soggy Swamps");

		SoggySwampsRegistry.registerSoggySwampsContent();
		SoggySwampsWorldgen.registerSoggySwampsWorldgen();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}