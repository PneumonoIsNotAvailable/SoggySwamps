package net.pneumono.soggy_swamps;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.pneumono.soggy_swamps.registry.SoggySwampsBlocks;
import net.pneumono.soggy_swamps.registry.SoggySwampsEntities;

public class SoggySwampsClient implements ClientModInitializer {
	public static final EntityModelLayer SWAMP_OAK_BOAT = new EntityModelLayer(
			SoggySwamps.id("boat/swamp_oak"), "main"
	);
	public static final EntityModelLayer SWAMP_OAK_CHEST_BOAT = new EntityModelLayer(
			SoggySwamps.id("chest_boat/swamp_oak"), "main"
	);

	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.putBlock(SoggySwampsBlocks.SWAMP_OAK_SAPLING, BlockRenderLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(SoggySwampsBlocks.SWAMP_OAK_LEAVES, BlockRenderLayer.CUTOUT_MIPPED);

		ColorProviderRegistry.BLOCK.register(
				(state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : -12012264,
				SoggySwampsBlocks.SWAMP_OAK_LEAVES
		);

		EntityModelLayerRegistry.registerModelLayer(SWAMP_OAK_BOAT, BoatEntityModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(SWAMP_OAK_CHEST_BOAT, BoatEntityModel::getChestTexturedModelData);

		EntityRendererRegistry.register(
				SoggySwampsEntities.SWAMP_OAK_BOAT,
				context -> new BoatEntityRenderer(context, SWAMP_OAK_BOAT)
		);
		EntityRendererRegistry.register(
				SoggySwampsEntities.SWAMP_OAK_CHEST_BOAT,
				context -> new BoatEntityRenderer(context, SWAMP_OAK_CHEST_BOAT)
		);
	}
}