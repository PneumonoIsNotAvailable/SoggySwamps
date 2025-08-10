package net.pneumono.soggy_swamps.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.SwampHutGenerator;
import net.pneumono.soggy_swamps.registry.SoggySwampsBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SwampHutGenerator.class)
public class SwampHutGeneratorMixin {
    @WrapOperation(
            method = "generate",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;getDefaultState()Lnet/minecraft/block/BlockState;"
            )
    )
    private BlockState useSwampOak(Block block, Operation<BlockState> original) {
        if (block == Blocks.OAK_LOG) return original.call(SoggySwampsBlocks.SWAMP_OAK_LOG);
        if (block == Blocks.OAK_FENCE) return original.call(SoggySwampsBlocks.SWAMP_OAK_FENCE);

        return original.call(block);
    }
}
