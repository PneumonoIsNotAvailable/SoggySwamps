package net.pneumono.soggy_swamps;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.pneumono.soggy_swamps.content.FlyParticle;
import net.pneumono.soggy_swamps.registry.SoggySwampsBlocks;
import net.pneumono.soggy_swamps.registry.SoggySwampsEntities;
import net.pneumono.soggy_swamps.registry.SoggySwampsRegistry;

public class SoggySwampsClient implements ClientModInitializer {
	public static final ModelLayerLocation SWAMP_SPIDER = new ModelLayerLocation(
			SoggySwamps.id("swamp_spider"), "main"
	);
	public static final ModelLayerLocation SWAMP_OAK_BOAT = new ModelLayerLocation(
			SoggySwamps.id("boat/swamp_oak"), "main"
	);
	public static final ModelLayerLocation SWAMP_OAK_CHEST_BOAT = new ModelLayerLocation(
			SoggySwamps.id("chest_boat/swamp_oak"), "main"
	);

	@Override
	public void onInitializeClient() {
		ParticleFactoryRegistry.getInstance().register(SoggySwampsRegistry.FLY, FlyParticle.Factory::new);

		BlockRenderLayerMap.putBlock(SoggySwampsBlocks.CATTAIL, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(SoggySwampsBlocks.POTTED_ROT_CAP, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(SoggySwampsBlocks.BOGSPROUT, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(SoggySwampsBlocks.POTTED_BOGSPROUT, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(SoggySwampsBlocks.SWAMP_OAK_SAPLING, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(SoggySwampsBlocks.POTTED_SWAMP_OAK_SAPLING, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(SoggySwampsBlocks.SWAMP_OAK_DOOR, ChunkSectionLayer.TRANSLUCENT);
		BlockRenderLayerMap.putBlock(SoggySwampsBlocks.SWAMP_OAK_TRAPDOOR, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(SoggySwampsBlocks.SWAMP_OAK_LEAVES, ChunkSectionLayer.CUTOUT_MIPPED);

		ColorProviderRegistry.BLOCK.register(
				(state, level, pos, tintIndex) -> level != null && pos != null ? BiomeColors.getAverageFoliageColor(level, pos) : -12012264,
				SoggySwampsBlocks.SWAMP_OAK_LEAVES
		);

		EntityModelLayerRegistry.registerModelLayer(SWAMP_SPIDER, SpiderModel::createSpiderBodyLayer);
		EntityModelLayerRegistry.registerModelLayer(SWAMP_OAK_BOAT, BoatModel::createBoatModel);
		EntityModelLayerRegistry.registerModelLayer(SWAMP_OAK_CHEST_BOAT, BoatModel::createChestBoatModel);

		EntityRenderers.register(
				SoggySwampsEntities.SWAMP_OAK_BOAT,
				context -> new BoatRenderer(context, SWAMP_OAK_BOAT)
		);
		EntityRenderers.register(
				SoggySwampsEntities.SWAMP_OAK_CHEST_BOAT,
				context -> new BoatRenderer(context, SWAMP_OAK_CHEST_BOAT)
		);
		addSherd("hat");
		addSherd("slime");
		addSherd("decay");
		addSherd("wealth");
	}

	private static void addSherd(String name) {
		Sheets.DECORATED_POT_MATERIALS.put(
				ResourceKey.create(Registries.DECORATED_POT_PATTERN, SoggySwamps.id(name)),
				Sheets.DECORATED_POT_MAPPER.apply(SoggySwamps.id(name + "_pottery_pattern"))
		);
	}
}