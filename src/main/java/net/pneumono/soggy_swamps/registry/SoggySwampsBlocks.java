package net.pneumono.soggy_swamps.registry;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.pneumono.soggy_swamps.SoggySwamps;
import net.pneumono.soggy_swamps.content.*;
import net.pneumono.soggy_swamps.worldgen.SoggySwampsWorldgen;

import java.util.Optional;
import java.util.function.Function;

public class SoggySwampsBlocks {
    public static final CattailBlock CATTAIL = register(
            "cattail",
            CattailBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GREEN)
                    .replaceable()
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.CROP)
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .pushReaction(PushReaction.DESTROY)
    );
    public static final RotCapBlock ROT_CAP = register(
            "rot_cap",
            RotCapBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .lightLevel(state -> 1)
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.WET_GRASS)
                    .pushReaction(PushReaction.DESTROY)
    );
    public static final FlowerPotBlock POTTED_ROT_CAP = register(
            "potted_rot_cap",
            properties -> new PottedRotCapBlock(ROT_CAP, properties),
            Blocks.flowerPotProperties()
    );
    public static final BogsproutBlock BOGSPROUT = register(
            "bogsprout",
            properties -> new BogsproutBlock(MobEffects.REGENERATION, 10.0F, properties),
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.WET_GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .pushReaction(PushReaction.DESTROY)
    );
    public static final FlowerPotBlock POTTED_BOGSPROUT = register(
            "potted_bogsprout",
            properties -> new FlowerPotBlock(BOGSPROUT, properties),
            Blocks.flowerPotProperties()
    );
    public static final BrushableMudBlock SUSPICIOUS_MUD = register(
            "suspicious_mud",
            properties -> new BrushableMudBlock(Blocks.MUD, SoggySwampsSounds.ITEM_BRUSH_BRUSHING_MUD, SoggySwampsSounds.ITEM_BRUSH_BRUSHING_MUD, properties),
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_CYAN)
                    .isValidSpawn(Blocks::always)
                    .isRedstoneConductor(Blocks::always)
                    .isViewBlocking(Blocks::always)
                    .isSuffocating(Blocks::always)
                    .strength(0.3F)
                    .sound(SoggySwampsSounds.GROUP_SUSPICIOUS_MUD)
                    .pushReaction(PushReaction.DESTROY)
    );
    public static final SaplingBlock SWAMP_OAK_SAPLING = register(
            "swamp_oak_sapling",
            properties -> new SaplingBlock(
                    new TreeGrower(
                            "swamp_oak",
                            Optional.empty(),
                            Optional.of(SoggySwampsWorldgen.SWAMP_OAK),
                            Optional.empty()
                    ),
                    properties
            ),
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollision()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .pushReaction(PushReaction.DESTROY)
    );
    public static final FlowerPotBlock POTTED_SWAMP_OAK_SAPLING = register(
            "potted_swamp_oak_sapling",
            properties -> new FlowerPotBlock(SWAMP_OAK_SAPLING, properties),
            Blocks.flowerPotProperties()
    );
    public static final LeavesBlock SWAMP_OAK_LEAVES = register(
            "swamp_oak_leaves",
            properties -> new TintedParticleLeavesBlock(0.01F, properties),
            Blocks.leavesProperties(SoundType.GRASS)
    );
    public static final RotatedPillarBlock SWAMP_OAK_LOG = register(
            "swamp_oak_log",
            RotatedPillarBlock::new,
            Blocks.logProperties(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD)
    );
    public static final RotatedPillarBlock SWAMP_OAK_WOOD = register(
            "swamp_oak_wood",
            RotatedPillarBlock::new,
            createSwampOak().strength(2.0F)
    );
    public static final RotatedPillarBlock STRIPPED_SWAMP_OAK_LOG = register(
            "stripped_swamp_oak_log",
            RotatedPillarBlock::new,
            createSwampOak().strength(2.0F)
    );
    public static final RotatedPillarBlock STRIPPED_SWAMP_OAK_WOOD = register(
            "stripped_swamp_oak_wood",
            RotatedPillarBlock::new,
            BlockBehaviour.Properties.ofFullCopy(SWAMP_OAK_WOOD)
    );
    public static final Block SWAMP_OAK_PLANKS = register(
            "swamp_oak_planks",
            Block::new,
            createSwampOak().strength(2.0F, 3.0F)
    );
    public static final StairBlock SWAMP_OAK_STAIRS = register(
            "swamp_oak_stairs",
            properties -> new StairBlock(SWAMP_OAK_PLANKS.defaultBlockState(), properties),
            BlockBehaviour.Properties.ofFullCopy(SWAMP_OAK_PLANKS)
    );
    public static final SlabBlock SWAMP_OAK_SLAB = register(
            "swamp_oak_slab",
            SlabBlock::new,
            BlockBehaviour.Properties.ofFullCopy(SWAMP_OAK_PLANKS)
    );
    public static final StandingSignBlock SWAMP_OAK_SIGN = register(
            "swamp_oak_sign",
            properties -> new StandingSignBlock(SoggySwampsRegistry.SWAMP_OAK_WOOD_TYPE, properties),
            createSwampOak()
                    .strength(1.0F)
                    .forceSolidOn()
                    .noCollision()
    );
    public static final WallSignBlock SWAMP_OAK_WALL_SIGN = register(
            "swamp_oak_wall_sign",
            properties -> new WallSignBlock(SoggySwampsRegistry.SWAMP_OAK_WOOD_TYPE, properties),
            BlockBehaviour.Properties.ofFullCopy(SWAMP_OAK_SIGN)
                    .overrideLootTable(SWAMP_OAK_SIGN.getLootTable())
                    .overrideDescription(SWAMP_OAK_SIGN.getDescriptionId())
    );
    public static final CeilingHangingSignBlock SWAMP_OAK_HANGING_SIGN = register(
            "swamp_oak_hanging_sign",
            properties -> new CeilingHangingSignBlock(SoggySwampsRegistry.SWAMP_OAK_WOOD_TYPE, properties),
            BlockBehaviour.Properties.ofFullCopy(SWAMP_OAK_SIGN)
    );
    public static final WallHangingSignBlock SWAMP_OAK_WALL_HANGING_SIGN = register(
            "swamp_oak_wall_hanging_sign",
            properties -> new WallHangingSignBlock(SoggySwampsRegistry.SWAMP_OAK_WOOD_TYPE, properties),
            BlockBehaviour.Properties.ofFullCopy(SWAMP_OAK_HANGING_SIGN)
                    .overrideLootTable(SWAMP_OAK_HANGING_SIGN.getLootTable())
                    .overrideDescription(SWAMP_OAK_HANGING_SIGN.getDescriptionId())
    );
    public static final DoorBlock SWAMP_OAK_DOOR = register(
            "swamp_oak_door",
            properties -> new DoorBlock(SoggySwampsRegistry.SWAMP_OAK_BLOCK_SET_TYPE, properties),
            BlockBehaviour.Properties.ofFullCopy(SWAMP_OAK_PLANKS)
                    .strength(3.0F)
                    .noOcclusion()
    );
    public static final TrapDoorBlock SWAMP_OAK_TRAPDOOR = register(
            "swamp_oak_trapdoor",
            properties -> new TrapDoorBlock(SoggySwampsRegistry.SWAMP_OAK_BLOCK_SET_TYPE, properties),
            BlockBehaviour.Properties.ofFullCopy(SWAMP_OAK_PLANKS)
                    .strength(3.0F)
                    .noOcclusion()
                    .isValidSpawn(Blocks::never)
    );
    public static final FenceBlock SWAMP_OAK_FENCE = register(
            "swamp_oak_fence",
            FenceBlock::new,
            BlockBehaviour.Properties.ofFullCopy(SWAMP_OAK_PLANKS)
    );
    public static final FenceGateBlock SWAMP_OAK_FENCE_GATE = register(
            "swamp_oak_fence_gate",
            properties -> new FenceGateBlock(SoggySwampsRegistry.SWAMP_OAK_WOOD_TYPE, properties),
            BlockBehaviour.Properties.ofFullCopy(SWAMP_OAK_PLANKS).forceSolidOn()
    );
    public static final PressurePlateBlock SWAMP_OAK_PRESSURE_PLATE = register(
            "swamp_oak_pressure_plate",
            properties -> new PressurePlateBlock(SoggySwampsRegistry.SWAMP_OAK_BLOCK_SET_TYPE, properties),
            BlockBehaviour.Properties.ofFullCopy(SWAMP_OAK_PLANKS)
                    .forceSolidOn()
                    .noCollision()
                    .strength(0.5F)
                    .pushReaction(PushReaction.DESTROY)
    );
    public static final ButtonBlock SWAMP_OAK_BUTTON = register(
            "swamp_oak_button",
            properties -> new ButtonBlock(SoggySwampsRegistry.SWAMP_OAK_BLOCK_SET_TYPE, 30, properties),
            Blocks.buttonProperties()
    );
    public static final Block CHISELED_MUD_BRICKS = register(
            "chiseled_mud_bricks",
            Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.MUD_BRICKS)
    );
    public static final Block MOSSY_MUD_BRICKS = register(
            "mossy_mud_bricks",
            Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.MUD_BRICKS)
    );
    public static final StairBlock MOSSY_MUD_BRICK_STAIRS = register(
            "mossy_mud_brick_stairs",
            properties -> new StairBlock(Blocks.MUD_BRICKS.defaultBlockState(), properties),
            BlockBehaviour.Properties.ofFullCopy(MOSSY_MUD_BRICKS)
    );
    public static final SlabBlock MOSSY_MUD_BRICK_SLAB = register(
            "mossy_mud_brick_slab",
            SlabBlock::new,
            BlockBehaviour.Properties.ofFullCopy(MOSSY_MUD_BRICKS)
    );
    public static final WallBlock MOSSY_MUD_BRICK_WALL = register(
            "mossy_mud_brick_wall",
            WallBlock::new,
            BlockBehaviour.Properties.ofFullCopy(MOSSY_MUD_BRICKS).forceSolidOn()
    );

    private static BlockBehaviour.Properties createSwampOak() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.WOOD)
                .instrument(NoteBlockInstrument.BASS)
                .sound(SoundType.WOOD)
                .ignitedByLava();
    }

    protected static <T extends Block> T register(String name, Function<BlockBehaviour.Properties, T> factory, BlockBehaviour.Properties properties) {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, SoggySwamps.id(name));
        return Registry.register(BuiltInRegistries.BLOCK, key, factory.apply(properties.setId(key)));
    }

    public static void registerSoggySwampsBlocks() {
        BlockEntityType.BRUSHABLE_BLOCK.addSupportedBlock(SUSPICIOUS_MUD);
        BlockEntityType.SIGN.addSupportedBlock(SWAMP_OAK_SIGN);
        BlockEntityType.SIGN.addSupportedBlock(SWAMP_OAK_WALL_SIGN);
        BlockEntityType.HANGING_SIGN.addSupportedBlock(SWAMP_OAK_HANGING_SIGN);
        BlockEntityType.HANGING_SIGN.addSupportedBlock(SWAMP_OAK_WALL_HANGING_SIGN);

        StrippableBlockRegistry.register(SWAMP_OAK_LOG, STRIPPED_SWAMP_OAK_LOG);
        StrippableBlockRegistry.register(SWAMP_OAK_WOOD, STRIPPED_SWAMP_OAK_WOOD);

        FlammableBlockRegistry flammables = FlammableBlockRegistry.getDefaultInstance();
        flammables.add(SWAMP_OAK_LEAVES, 30, 60);
        flammables.add(SWAMP_OAK_LOG, 5, 5);
        flammables.add(STRIPPED_SWAMP_OAK_LOG, 5, 5);
        flammables.add(SWAMP_OAK_WOOD, 5, 5);
        flammables.add(STRIPPED_SWAMP_OAK_WOOD, 5, 5);
        flammables.add(SWAMP_OAK_PLANKS, 5, 20);
        flammables.add(SWAMP_OAK_STAIRS, 5, 20);
        flammables.add(SWAMP_OAK_SLAB, 5, 20);
        flammables.add(SWAMP_OAK_FENCE, 5, 20);
        flammables.add(SWAMP_OAK_FENCE_GATE, 5, 20);
    }
}
