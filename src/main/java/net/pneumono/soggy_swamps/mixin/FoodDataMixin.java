package net.pneumono.soggy_swamps.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.GameRules;
import net.pneumono.soggy_swamps.registry.SoggySwampsRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FoodData.class)
public abstract class FoodDataMixin {
    @WrapOperation(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/GameRules;getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z"
            )
    )
    private boolean preventNaturalRegenWithVenom(GameRules instance, GameRules.Key<GameRules.BooleanValue> gameRule, Operation<Boolean> original, @Local(argsOnly = true) ServerPlayer player) {
        if (player.hasEffect(SoggySwampsRegistry.VENOM)) {
            return false;
        } else {
            return original.call(instance, gameRule);
        }
    }
}
