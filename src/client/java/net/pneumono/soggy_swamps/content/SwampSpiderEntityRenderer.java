package net.pneumono.soggy_swamps.content;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.SpiderEntityRenderer;
import net.minecraft.client.render.entity.feature.SpiderEyesFeatureRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;
import net.pneumono.soggy_swamps.SoggySwamps;
import net.pneumono.soggy_swamps.SoggySwampsClient;

public class SwampSpiderEntityRenderer extends SpiderEntityRenderer<SwampSpiderEntity> {
    private static final Identifier TEXTURE = SoggySwamps.id("textures/entity/swamp_spider/spider.png");

    public SwampSpiderEntityRenderer(EntityRendererFactory.Context context) {
        super(context, SoggySwampsClient.SWAMP_SPIDER);
        this.features.removeIf(obj -> obj instanceof SpiderEyesFeatureRenderer<?>);
        this.addFeature(new SwampSpiderEyesFeatureRenderer<>(this));
    }

    @Override
    public Identifier getTexture(LivingEntityRenderState state) {
        return TEXTURE;
    }
}
