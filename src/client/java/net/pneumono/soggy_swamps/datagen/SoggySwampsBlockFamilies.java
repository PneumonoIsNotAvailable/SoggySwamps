package net.pneumono.soggy_swamps.datagen;

import net.minecraft.data.BlockFamily;
import net.pneumono.pneumonocore.datagen.ModdedBlockFamilies;
import net.pneumono.soggy_swamps.registry.SoggySwampsBlocks;

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
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();
    public static final BlockFamily MOSSY_MUD_BRICK = new BlockFamily.Builder(SoggySwampsBlocks.MOSSY_MUD_BRICKS)
            .stairs(SoggySwampsBlocks.MOSSY_MUD_BRICK_STAIRS)
            .slab(SoggySwampsBlocks.MOSSY_MUD_BRICK_SLAB)
            .wall(SoggySwampsBlocks.MOSSY_MUD_BRICK_WALL)
            .getFamily();
}
