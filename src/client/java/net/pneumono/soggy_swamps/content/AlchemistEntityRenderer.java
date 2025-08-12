package net.pneumono.soggy_swamps.content;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.state.ItemHolderEntityRenderState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.pneumono.soggy_swamps.SoggySwamps;
import net.pneumono.soggy_swamps.SoggySwampsClient;

public class AlchemistEntityRenderer extends MobEntityRenderer<AlchemistEntity, AlchemistEntityRenderState, AlchemistEntityModel> {
    private static final Identifier TEXTURE = SoggySwamps.id("textures/entity/alchemist.png");

    public AlchemistEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new AlchemistEntityModel(context.getPart(SoggySwampsClient.ALCHEMIST)), 0.5F);
        this.addFeature(new AlchemistHeldItemFeatureRenderer(this));
    }

    @Override
    public Identifier getTexture(AlchemistEntityRenderState renderState) {
        return TEXTURE;
    }

    @Override
    public AlchemistEntityRenderState createRenderState() {
        return new AlchemistEntityRenderState();
    }

    public void updateRenderState(AlchemistEntity alchemistEntity, AlchemistEntityRenderState alchemistEntityRenderState, float tickProgress) {
        super.updateRenderState(alchemistEntity, alchemistEntityRenderState, tickProgress);
        ItemHolderEntityRenderState.update(alchemistEntity, alchemistEntityRenderState, this.itemModelResolver);
        alchemistEntityRenderState.id = alchemistEntity.getId();
        ItemStack itemStack = alchemistEntity.getMainHandStack();
        alchemistEntityRenderState.holdingItem = !itemStack.isEmpty();
        alchemistEntityRenderState.holdingPotion = itemStack.isOf(Items.POTION);
    }
}
