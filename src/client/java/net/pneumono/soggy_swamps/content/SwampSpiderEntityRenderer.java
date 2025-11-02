package net.pneumono.soggy_swamps.content;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.client.renderer.entity.layers.SpiderEyesLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.pneumono.soggy_swamps.SoggySwamps;
import net.pneumono.soggy_swamps.SoggySwampsClient;
import org.jetbrains.annotations.NotNull;

public class SwampSpiderEntityRenderer extends SpiderRenderer<SwampSpiderEntity> {
    private static final ResourceLocation TEXTURE = SoggySwamps.id("textures/entity/swamp_spider/spider.png");

    public SwampSpiderEntityRenderer(EntityRendererProvider.Context context) {
        super(context, SoggySwampsClient.SWAMP_SPIDER);
        this.layers.removeIf(obj -> obj instanceof SpiderEyesLayer<?>);
        this.addLayer(new SwampSpiderEyesFeatureRenderer<>(this));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(LivingEntityRenderState state) {
        return TEXTURE;
    }
}
