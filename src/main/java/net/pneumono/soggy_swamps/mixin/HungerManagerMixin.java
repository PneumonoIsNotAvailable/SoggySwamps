package net.pneumono.soggy_swamps.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameRules;
import net.pneumono.soggy_swamps.registry.SoggySwampsRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HungerManager.class)
public abstract class HungerManagerMixin {
    @WrapOperation(
            method = "update",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"
            )
    )
    private boolean preventNaturalRegenWithVenom(GameRules instance, GameRules.Key<GameRules.BooleanRule> gameRule, Operation<Boolean> original, @Local ServerPlayerEntity player) {
        if (player.hasStatusEffect(SoggySwampsRegistry.VENOM)) {
            return false;
        } else {
            return original.call(instance, gameRule);
        }
    }
}
