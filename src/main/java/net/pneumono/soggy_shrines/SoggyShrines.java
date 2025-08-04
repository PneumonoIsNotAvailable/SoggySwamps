package net.pneumono.soggy_shrines;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoggyShrines implements ModInitializer {
	public static final String MOD_ID = "soggy_shrines";

	public static final Logger LOGGER = LoggerFactory.getLogger("Soggy Shrines");

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Soggy Shrines");
	}

	public Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}