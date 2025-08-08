package net.pneumono.soggy_swamps.datagen;

import net.minecraft.data.family.BlockFamily;
import net.pneumono.pneumonocore.datagen.ModdedBlockFamilies;
import net.pneumono.soggy_swamps.content.SoggySwampsBlocks;

public class SoggySwampsBlockFamilies extends ModdedBlockFamilies {
    public static final BlockFamily SWAMP_OAK = new BlockFamily.Builder(SoggySwampsBlocks.SWAMP_OAK_PLANKS)
            .stairs(SoggySwampsBlocks.SWAMP_OAK_STAIRS)
            .slab(SoggySwampsBlocks.SWAMP_OAK_SLAB)
            .sign(SoggySwampsBlocks.SWAMP_OAK_SIGN, SoggySwampsBlocks.SWAMP_OAK_WALL_SIGN)
            .door(SoggySwampsBlocks.SWAMP_OAK_DOOR)
            .trapdoor(SoggySwampsBlocks.SWAMP_OAK_TRAPDOOR)
            .fence(SoggySwampsBlocks.SWAMP_OAK_FENCE)
            .fenceGate(SoggySwampsBlocks.SWAMP_OAK_FENCE_GATE)
            .pressurePlate(SoggySwampsBlocks.SWAMP_OAK_PRESSURE_PLATE)
            .button(SoggySwampsBlocks.SWAMP_OAK_BUTTON)
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build();
}
