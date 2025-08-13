package net.pneumono.soggy_swamps.registry;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.pneumono.soggy_swamps.SoggySwamps;
import net.pneumono.soggy_swamps.content.NonFallingBrushableBlock;
import net.pneumono.soggy_swamps.content.RotCapBlock;
import net.pneumono.soggy_swamps.worldgen.SoggySwampsWorldgen;

import java.util.Optional;
import java.util.function.Function;

public class SoggySwampsBlocks {
    public static final RotCapBlock ROT_CAP = register(
            "rot_cap",
            RotCapBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .luminance(state -> 1)
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.WET_GRASS)
                    .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final FlowerBlock VIBRANT_SPROUT = register(
            "vibrant_sprout",
            settings -> new FlowerBlock(StatusEffects.REGENERATION, 10.0F, settings),
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.WET_GRASS)
                    .offset(AbstractBlock.OffsetType.XZ)
                    .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final FlowerPotBlock POTTED_VIBRANT_SPROUT = register(
            "potted_vibrant_sprout",
            settings -> new FlowerPotBlock(VIBRANT_SPROUT, settings),
            Blocks.createFlowerPotSettings()
    );
    public static final NonFallingBrushableBlock SUSPICIOUS_MUD = register(
            "suspicious_mud",
            settings -> new NonFallingBrushableBlock(Blocks.MUD, SoggySwampsSounds.ITEM_BRUSH_BRUSHING_MUD, SoggySwampsSounds.ITEM_BRUSH_BRUSHING_MUD, settings),
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.TERRACOTTA_CYAN)
                    .strength(0.3F)
                    .sounds(SoggySwampsSounds.GROUP_SUSPICIOUS_MUD)
                    .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final SaplingBlock SWAMP_OAK_SAPLING = register(
            "swamp_oak_sapling",
            settings -> new SaplingBlock(
                    new SaplingGenerator(
                            "swamp_oak",
                            Optional.empty(),
                            Optional.of(SoggySwampsWorldgen.SWAMP_OAK),
                            Optional.empty()
                    ),
                    settings
            ),
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.GRASS)
                    .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final FlowerPotBlock POTTED_SWAMP_OAK_SAPLING = register(
            "potted_swamp_oak_sapling",
            settings -> new FlowerPotBlock(SWAMP_OAK_SAPLING, settings),
            Blocks.createFlowerPotSettings()
    );
    public static final LeavesBlock SWAMP_OAK_LEAVES = register(
            "swamp_oak_leaves",
            settings -> new TintedParticleLeavesBlock(0.01F, settings),
            Blocks.createLeavesSettings(BlockSoundGroup.GRASS)
    );
    public static final PillarBlock SWAMP_OAK_LOG = register(
            "swamp_oak_log",
            PillarBlock::new,
            Blocks.createLogSettings(MapColor.OAK_TAN, MapColor.SPRUCE_BROWN, BlockSoundGroup.WOOD)
    );
    public static final PillarBlock SWAMP_OAK_WOOD = register(
            "swamp_oak_wood",
            PillarBlock::new,
            createSwampOak()
                    .strength(2.0F)
    );
    public static final PillarBlock STRIPPED_SWAMP_OAK_LOG = register(
            "stripped_swamp_oak_log",
            PillarBlock::new,
            createSwampOak()
                    .strength(2.0F)
    );
    public static final PillarBlock STRIPPED_SWAMP_OAK_WOOD = register(
            "stripped_swamp_oak_wood",
            PillarBlock::new,
            AbstractBlock.Settings.copy(SWAMP_OAK_WOOD)
    );
    public static final Block SWAMP_OAK_PLANKS = register(
            "swamp_oak_planks",
            Block::new,
            createSwampOak()
                    .strength(2.0F, 3.0F)
    );
    public static final StairsBlock SWAMP_OAK_STAIRS = register(
            "swamp_oak_stairs",
            settings -> new StairsBlock(SWAMP_OAK_PLANKS.getDefaultState(), settings),
            AbstractBlock.Settings.copy(SWAMP_OAK_PLANKS)
    );
    public static final SlabBlock SWAMP_OAK_SLAB = register(
            "swamp_oak_slab",
            SlabBlock::new,
            AbstractBlock.Settings.copy(SWAMP_OAK_PLANKS)
    );
    public static final SignBlock SWAMP_OAK_SIGN = register(
            "swamp_oak_sign",
            settings -> new SignBlock(SoggySwampsRegistry.SWAMP_OAK_WOOD_TYPE, settings),
            createSwampOak()
                    .strength(1.0F)
                    .solid()
                    .noCollision()
    );
    public static final WallSignBlock SWAMP_OAK_WALL_SIGN = register(
            "swamp_oak_wall_sign",
            settings -> new WallSignBlock(SoggySwampsRegistry.SWAMP_OAK_WOOD_TYPE, settings),
            AbstractBlock.Settings.copy(SWAMP_OAK_SIGN)
                    .lootTable(SWAMP_OAK_SIGN.getLootTableKey())
                    .overrideTranslationKey(SWAMP_OAK_SIGN.getTranslationKey())
    );
    public static final HangingSignBlock SWAMP_OAK_HANGING_SIGN = register(
            "swamp_oak_hanging_sign",
            settings -> new HangingSignBlock(SoggySwampsRegistry.SWAMP_OAK_WOOD_TYPE, settings),
            AbstractBlock.Settings.copy(SWAMP_OAK_SIGN)
    );
    public static final WallHangingSignBlock SWAMP_OAK_WALL_HANGING_SIGN = register(
            "swamp_oak_wall_hanging_sign",
            settings -> new WallHangingSignBlock(SoggySwampsRegistry.SWAMP_OAK_WOOD_TYPE, settings),
            AbstractBlock.Settings.copy(SWAMP_OAK_HANGING_SIGN)
                    .lootTable(SWAMP_OAK_HANGING_SIGN.getLootTableKey())
                    .overrideTranslationKey(SWAMP_OAK_HANGING_SIGN.getTranslationKey())
    );
    public static final DoorBlock SWAMP_OAK_DOOR = register(
            "swamp_oak_door",
            settings -> new DoorBlock(SoggySwampsRegistry.SWAMP_OAK_BLOCK_SET_TYPE, settings),
            AbstractBlock.Settings.copy(SWAMP_OAK_PLANKS)
                    .strength(3.0F)
                    .nonOpaque()
    );
    public static final TrapdoorBlock SWAMP_OAK_TRAPDOOR = register(
            "swamp_oak_trapdoor",
            settings -> new TrapdoorBlock(SoggySwampsRegistry.SWAMP_OAK_BLOCK_SET_TYPE, settings),
            AbstractBlock.Settings.copy(SWAMP_OAK_PLANKS)
                    .strength(3.0F)
                    .nonOpaque()
                    .allowsSpawning(Blocks::never)
    );
    public static final FenceBlock SWAMP_OAK_FENCE = register(
            "swamp_oak_fence",
            FenceBlock::new,
            AbstractBlock.Settings.copy(SWAMP_OAK_PLANKS)
    );
    public static final FenceGateBlock SWAMP_OAK_FENCE_GATE = register(
            "swamp_oak_fence_gate",
            settings -> new FenceGateBlock(SoggySwampsRegistry.SWAMP_OAK_WOOD_TYPE, settings),
            AbstractBlock.Settings.copy(SWAMP_OAK_PLANKS).solid()
    );
    public static final PressurePlateBlock SWAMP_OAK_PRESSURE_PLATE = register(
            "swamp_oak_pressure_plate",
            settings -> new PressurePlateBlock(SoggySwampsRegistry.SWAMP_OAK_BLOCK_SET_TYPE, settings),
            AbstractBlock.Settings.copy(SWAMP_OAK_PLANKS)
                    .solid()
                    .noCollision()
                    .strength(0.5F)
                    .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final ButtonBlock SWAMP_OAK_BUTTON = register(
            "swamp_oak_button",
            settings -> new ButtonBlock(SoggySwampsRegistry.SWAMP_OAK_BLOCK_SET_TYPE, 30, settings),
            Blocks.createButtonSettings()
    );

    private static AbstractBlock.Settings createSwampOak() {
        return AbstractBlock.Settings.create()
                .mapColor(MapColor.OAK_TAN)
                .instrument(NoteBlockInstrument.BASS)
                .sounds(BlockSoundGroup.WOOD)
                .burnable();
    }

    protected static <T extends Block> T register(String name, Function<AbstractBlock.Settings, T> factory, AbstractBlock.Settings settings) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, SoggySwamps.id(name));
        return Registry.register(Registries.BLOCK, key, factory.apply(settings.registryKey(key)));
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
