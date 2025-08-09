package net.pneumono.soggy_swamps.content;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.SpiderEyesFeatureRenderer;
import net.minecraft.client.render.entity.model.SpiderEntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.pneumono.soggy_swamps.SoggySwamps;

public class SwampSpiderEyesFeatureRenderer<M extends SpiderEntityModel> extends SpiderEyesFeatureRenderer<M> {
    private static final RenderLayer SKIN = RenderLayer.getEyes(SoggySwamps.id("textures/entity/swamp_spider/eyes.png"));

    public SwampSpiderEyesFeatureRenderer(FeatureRendererContext<LivingEntityRenderState, M> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public RenderLayer getEyesTexture() {
        return SKIN;
    }
}
