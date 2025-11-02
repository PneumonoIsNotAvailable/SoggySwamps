package net.pneumono.soggy_swamps.content;

import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.SpiderEyesLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.pneumono.soggy_swamps.SoggySwamps;
import org.jetbrains.annotations.NotNull;

public class SwampSpiderEyesFeatureRenderer<M extends SpiderModel> extends SpiderEyesLayer<M> {
    private static final RenderType SKIN = RenderType.eyes(SoggySwamps.id("textures/entity/swamp_spider/eyes.png"));

    public SwampSpiderEyesFeatureRenderer(RenderLayerParent<LivingEntityRenderState, M> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public @NotNull RenderType renderType() {
        return SKIN;
    }
}
