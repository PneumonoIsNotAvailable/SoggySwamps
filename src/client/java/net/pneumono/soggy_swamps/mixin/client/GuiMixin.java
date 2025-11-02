package net.pneumono.soggy_swamps.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.pneumono.soggy_swamps.SoggySwamps;
import net.pneumono.soggy_swamps.registry.SoggySwampsRegistry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class GuiMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @Shadow
    @Final
    private RandomSource random;

    @ModifyVariable(
            method = "renderHearts",
            at = @At("STORE"),
            ordinal = 14
    )
    private int bounceVenomHearts(int height) {
        if (
                this.minecraft.player != null &&
                this.minecraft.player.hasEffect(SoggySwampsRegistry.VENOM)
        ) {
            return height + this.random.nextInt(2);
        } else {
            return height;
        }
    }

    @Inject(
            method = "renderHeart",
            at = @At("HEAD"),
            cancellable = true
    )
    private void drawVenomHeart(GuiGraphics context, Gui.HeartType type, int x, int y, boolean hardcore, boolean blinking, boolean half, CallbackInfo info) {
        if (
                type == Gui.HeartType.NORMAL &&
                this.minecraft.player != null &&
                this.minecraft.player.hasEffect(SoggySwampsRegistry.VENOM)
        ) {
            context.blitSprite(RenderPipelines.GUI_TEXTURED, getVenomHeartTexture(hardcore, half, blinking), x, y, 9, 9);
            info.cancel();
        }
    }

    @Unique
    public ResourceLocation getVenomHeartTexture(boolean hardcore, boolean half, boolean blinking) {
        ResourceLocation fullTexture = SoggySwamps.id("full");
        ResourceLocation fullBlinkingTexture = SoggySwamps.id("full_blinking");
        ResourceLocation halfTexture = SoggySwamps.id("half");
        ResourceLocation halfBlinkingTexture = SoggySwamps.id("half_blinking");
        ResourceLocation hardcoreFullTexture = SoggySwamps.id("hardcore_full");
        ResourceLocation hardcoreFullBlinkingTexture = SoggySwamps.id("hardcore_full_blinking");
        ResourceLocation hardcoreHalfTexture = SoggySwamps.id("hardcore_half");
        ResourceLocation hardcoreHalfBlinkingTexture = SoggySwamps.id("hardcore_half_blinking");

        if (!hardcore) {
            if (half) {
                return blinking ? halfBlinkingTexture : halfTexture;
            } else {
                return blinking ? fullBlinkingTexture : fullTexture;
            }
        } else if (half) {
            return blinking ? hardcoreHalfBlinkingTexture : hardcoreHalfTexture;
        } else {
            return blinking ? hardcoreFullBlinkingTexture : hardcoreFullTexture;
        }
    }
}
