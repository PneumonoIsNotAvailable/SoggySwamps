package net.pneumono.soggy_swamps.content;

import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.VillagerHeldItemFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;

public class AlchemistHeldItemFeatureRenderer extends VillagerHeldItemFeatureRenderer<AlchemistEntityRenderState, AlchemistEntityModel> {
    public AlchemistHeldItemFeatureRenderer(FeatureRendererContext<AlchemistEntityRenderState, AlchemistEntityModel> featureRendererContext) {
        super(featureRendererContext);
    }

    protected void applyTransforms(AlchemistEntityRenderState alchemistEntityRenderState, MatrixStack matrixStack) {
        if (alchemistEntityRenderState.holdingPotion) {
            this.getContextModel().getRootPart().applyTransform(matrixStack);
            this.getContextModel().getHead().applyTransform(matrixStack);
            this.getContextModel().getNose().applyTransform(matrixStack);
            matrixStack.translate(0.0625F, 0.25F, 0.0F);
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180.0F));
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(140.0F));
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(10.0F));
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180.0F));
        } else {
            super.applyTransforms(alchemistEntityRenderState, matrixStack);
        }
    }
}
