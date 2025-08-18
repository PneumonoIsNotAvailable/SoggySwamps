package net.pneumono.soggy_swamps;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SpiderEntityModel;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.pneumono.soggy_swamps.content.FlyParticle;
import net.pneumono.soggy_swamps.content.SwampSpiderEntityRenderer;
import net.pneumono.soggy_swamps.registry.SoggySwampsBlocks;
import net.pneumono.soggy_swamps.registry.SoggySwampsEntities;
import net.pneumono.soggy_swamps.registry.SoggySwampsRegistry;

public class SoggySwampsClient implements ClientModInitializer {
	public static final EntityModelLayer SWAMP_SPIDER = new EntityModelLayer(
			SoggySwamps.id("swamp_spider"), "main"
	);
	public static final EntityModelLayer SWAMP_OAK_BOAT = new EntityModelLayer(
			SoggySwamps.id("boat/swamp_oak"), "main"
	);
	public static final EntityModelLayer SWAMP_OAK_CHEST_BOAT = new EntityModelLayer(
			SoggySwamps.id("chest_boat/swamp_oak"), "main"
	);

	@Override
	public void onInitializeClient() {
		ParticleFactoryRegistry.getInstance().register(SoggySwampsRegistry.FLY, FlyParticle.Factory::new);

		BlockRenderLayerMap.putBlock(SoggySwampsBlocks.POTTED_ROT_CAP, BlockRenderLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(SoggySwampsBlocks.VIBRANT_SPROUT, BlockRenderLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(SoggySwampsBlocks.POTTED_VIBRANT_SPROUT, BlockRenderLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(SoggySwampsBlocks.SWAMP_OAK_SAPLING, BlockRenderLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(SoggySwampsBlocks.POTTED_SWAMP_OAK_SAPLING, BlockRenderLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(SoggySwampsBlocks.SWAMP_OAK_DOOR, BlockRenderLayer.TRANSLUCENT);
		BlockRenderLayerMap.putBlock(SoggySwampsBlocks.SWAMP_OAK_TRAPDOOR, BlockRenderLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(SoggySwampsBlocks.SWAMP_OAK_LEAVES, BlockRenderLayer.CUTOUT_MIPPED);

		ColorProviderRegistry.BLOCK.register(
				(state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : -12012264,
				SoggySwampsBlocks.SWAMP_OAK_LEAVES
		);

		EntityModelLayerRegistry.registerModelLayer(SWAMP_SPIDER, SpiderEntityModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(SWAMP_OAK_BOAT, BoatEntityModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(SWAMP_OAK_CHEST_BOAT, BoatEntityModel::getChestTexturedModelData);

		EntityRendererRegistry.register(
				SoggySwampsEntities.SWAMP_SPIDER,
				SwampSpiderEntityRenderer::new
		);
		EntityRendererRegistry.register(
				SoggySwampsEntities.SWAMP_OAK_BOAT,
				context -> new BoatEntityRenderer(context, SWAMP_OAK_BOAT)
		);
		EntityRendererRegistry.register(
				SoggySwampsEntities.SWAMP_OAK_CHEST_BOAT,
				context -> new BoatEntityRenderer(context, SWAMP_OAK_CHEST_BOAT)
		);

		TexturedRenderLayers.DECORATED_POT_PATTERN_TEXTURES.put(
				RegistryKey.of(RegistryKeys.DECORATED_POT_PATTERN, SoggySwamps.id("hat")),
				TexturedRenderLayers.DECORATED_POT_SPRITE_MAPPER.map(SoggySwamps.id("hat_pottery_pattern"))
		);
		TexturedRenderLayers.DECORATED_POT_PATTERN_TEXTURES.put(
				RegistryKey.of(RegistryKeys.DECORATED_POT_PATTERN, SoggySwamps.id("slime")),
				TexturedRenderLayers.DECORATED_POT_SPRITE_MAPPER.map(SoggySwamps.id("slime_pottery_pattern"))
		);
		TexturedRenderLayers.DECORATED_POT_PATTERN_TEXTURES.put(
				RegistryKey.of(RegistryKeys.DECORATED_POT_PATTERN, SoggySwamps.id("decay")),
				TexturedRenderLayers.DECORATED_POT_SPRITE_MAPPER.map(SoggySwamps.id("decay_pottery_pattern"))
		);
	}
}