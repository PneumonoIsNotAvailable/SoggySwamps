package net.pneumono.soggy_swamps.content;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class AlchemistEntityModel extends EntityModel<AlchemistEntityRenderState> implements ModelWithHead, ModelWithHat {
    protected final ModelPart nose;
    private final ModelPart head;
    private final ModelPart hat;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart arms;

    public AlchemistEntityModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild(EntityModelPartNames.HEAD);
        this.hat = this.head.getChild(EntityModelPartNames.HAT);
        this.nose = this.head.getChild(EntityModelPartNames.NOSE);
        this.rightLeg = modelPart.getChild(EntityModelPartNames.RIGHT_LEG);
        this.leftLeg = modelPart.getChild(EntityModelPartNames.LEFT_LEG);
        this.arms = modelPart.getChild(EntityModelPartNames.ARMS);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = VillagerResemblingModel.getModelData();
        ModelPartData root = modelData.getRoot();
        ModelPartData head = root.addChild(
                EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F), ModelTransform.NONE
        );
        head.addChild(
                EntityModelPartNames.HAT, ModelPartBuilder.create().uv(0, 64).cuboid(0.0F, 0.0F, 0.0F, 10.0F, 2.0F, 10.0F), ModelTransform.origin(-5.0F, -10.03125F, -5.0F)
        );
        head.addChild(
                EntityModelPartNames.HAT, ModelPartBuilder.create().uv(0, 64).cuboid(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new Dilation(0.75F)), ModelTransform.NONE
        );
        ModelPartData nose = head.getChild(EntityModelPartNames.NOSE);
        nose.addChild(
                "mole", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, 3.0F, -6.75F, 1.0F, 1.0F, 1.0F, new Dilation(-0.25F)), ModelTransform.origin(0.0F, -2.0F, 0.0F)
        );
        return TexturedModelData.of(modelData, 64, 128);
    }

    public void setAngles(AlchemistEntityRenderState alchemistEntityRenderState) {
        super.setAngles(alchemistEntityRenderState);
        this.head.yaw = alchemistEntityRenderState.relativeHeadYaw * (float) (Math.PI / 180.0);
        this.head.pitch = alchemistEntityRenderState.pitch * (float) (Math.PI / 180.0);
        this.rightLeg.pitch = MathHelper.cos(alchemistEntityRenderState.limbSwingAnimationProgress * 0.6662F) * 1.4F * alchemistEntityRenderState.limbSwingAmplitude * 0.5F;
        this.leftLeg.pitch = MathHelper.cos(alchemistEntityRenderState.limbSwingAnimationProgress * 0.6662F + (float) Math.PI)
                * 1.4F
                * alchemistEntityRenderState.limbSwingAmplitude
                * 0.5F;
        float f = 0.01F * (alchemistEntityRenderState.id % 10);
        this.nose.pitch = MathHelper.sin(alchemistEntityRenderState.age * f) * 4.5F * (float) (Math.PI / 180.0);
        this.nose.roll = MathHelper.cos(alchemistEntityRenderState.age * f) * 2.5F * (float) (Math.PI / 180.0);
        if (alchemistEntityRenderState.holdingItem) {
            this.nose.setOrigin(0.0F, 1.0F, -1.5F);
            this.nose.pitch = -0.9F;
        }
    }

    public ModelPart getNose() {
        return this.nose;
    }

    @Override
    public ModelPart getHead() {
        return this.head;
    }

    @Override
    public void setHatVisible(boolean visible) {
        this.head.visible = visible;
        this.hat.visible = visible;
    }

    @Override
    public void rotateArms(MatrixStack stack) {
        this.root.applyTransform(stack);
        this.arms.applyTransform(stack);
    }
}
