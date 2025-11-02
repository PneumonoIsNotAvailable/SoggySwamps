package net.pneumono.soggy_swamps.mixin;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.minecraft.world.level.block.entity.DecoratedPotPatterns;
import net.pneumono.soggy_swamps.registry.SoggySwampsRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DecoratedPotPatterns.class)
public abstract class DecoratedPotPatternsMixin {
    @Inject(
            method = "getPatternFromItem",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void fromModdedSherd(Item sherd, CallbackInfoReturnable<ResourceKey<DecoratedPotPattern>> cir) {
        if (SoggySwampsRegistry.SHERD_TO_PATTERN.containsKey(sherd)) {
            cir.setReturnValue(SoggySwampsRegistry.SHERD_TO_PATTERN.get(sherd));
        }
    }
}
