package net.pneumono.soggy_swamps.content;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.pneumono.soggy_swamps.content.templespawner.TempleSpawnerBlockEntity;
import net.pneumono.soggy_swamps.content.templespawner.TempleSpawnerLogic;

public class TempleSpawnerBlockEntityRenderer implements BlockEntityRenderer<TempleSpawnerBlockEntity> {
    private final EntityRenderDispatcher entityRenderDispatcher;

    public TempleSpawnerBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.entityRenderDispatcher = ctx.getEntityRenderDispatcher();
    }

    @Override
    public void render(
            TempleSpawnerBlockEntity blockEntity, float tickProgress, MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider, int light, int overlay, Vec3d vec3d
    ) {
        World world = blockEntity.getWorld();
        if (world != null) {
            TempleSpawnerLogic logic = blockEntity.getLogic();
            Entity entity = logic.getRenderedEntity(world, blockEntity.getPos());
            if (entity != null) {
                render(
                        tickProgress, matrixStack, vertexConsumerProvider, light, entity, this.entityRenderDispatcher,
                        logic.getLastRotation(), logic.getRotation()
                );
            }
        }
    }

    public static void render(
            float tickProgress,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            Entity entity,
            EntityRenderDispatcher entityRenderDispatcher,
            double lastRotation,
            double rotation
    ) {
        matrices.push();
        matrices.translate(0.5F, 0.0F, 0.5F);
        float scale = 0.53125F;
        float sizeModifier = Math.max(entity.getWidth(), entity.getHeight());
        if (sizeModifier > 1.0) {
            scale /= sizeModifier;
        }

        matrices.translate(0.0F, 0.4F, 0.0F);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float) MathHelper.lerp(tickProgress, lastRotation, rotation) * 10.0F));
        matrices.translate(0.0F, -0.2F, 0.0F);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-30.0F));
        matrices.scale(scale, scale, scale);
        entityRenderDispatcher.render(entity, 0.0, 0.0, 0.0, tickProgress, matrices, vertexConsumers, light);
        matrices.pop();
    }
}
