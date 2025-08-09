package net.pneumono.soggy_swamps.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
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

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    @Final
    private Random random;

    @ModifyVariable(
            method = "renderHealthBar",
            at = @At("STORE"),
            ordinal = 14
    )
    private int bounceVenomHearts(
            int height
    ) {
        if (
                this.client.player != null &&
                this.client.player.hasStatusEffect(SoggySwampsRegistry.VENOM)
        ) {
            return height + this.random.nextInt(2);
        } else {
            return height;
        }
    }

    @Inject(
            method = "drawHeart",
            at = @At("HEAD"),
            cancellable = true
    )
    private void drawVenomHeart(DrawContext context, InGameHud.HeartType type, int x, int y, boolean hardcore, boolean blinking, boolean half, CallbackInfo info) {
        if (
                type == InGameHud.HeartType.NORMAL &&
                this.client.player != null &&
                this.client.player.hasStatusEffect(SoggySwampsRegistry.VENOM)
        ) {
            context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, getVenomHeartTexture(hardcore, half, blinking), x, y, 9, 9);
            info.cancel();
        }
    }

    @Unique
    public Identifier getVenomHeartTexture(boolean hardcore, boolean half, boolean blinking) {
        Identifier fullTexture = SoggySwamps.id("full");
        Identifier fullBlinkingTexture = SoggySwamps.id("full_blinking");
        Identifier halfTexture = SoggySwamps.id("half");
        Identifier halfBlinkingTexture = SoggySwamps.id("half_blinking");
        Identifier hardcoreFullTexture = SoggySwamps.id("hardcore_full");
        Identifier hardcoreFullBlinkingTexture = SoggySwamps.id("hardcore_full_blinking");
        Identifier hardcoreHalfTexture = SoggySwamps.id("hardcore_half");
        Identifier hardcoreHalfBlinkingTexture = SoggySwamps.id("hardcore_half_blinking");

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
