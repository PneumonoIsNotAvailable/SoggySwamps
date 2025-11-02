package net.pneumono.soggy_swamps.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.structures.SwampHutPiece;
import net.pneumono.soggy_swamps.registry.SoggySwampsBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SwampHutPiece.class)
public abstract class SwampHutPieceMixin {
    @WrapOperation(
            method = "postProcess",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/Block;defaultBlockState()Lnet/minecraft/world/level/block/state/BlockState;"
            )
    )
    private BlockState useSwampOak(Block block, Operation<BlockState> original) {
        if (block == Blocks.OAK_LOG) return original.call(SoggySwampsBlocks.SWAMP_OAK_LOG);
        if (block == Blocks.OAK_FENCE) return original.call(SoggySwampsBlocks.SWAMP_OAK_FENCE);

        return original.call(block);
    }
}
