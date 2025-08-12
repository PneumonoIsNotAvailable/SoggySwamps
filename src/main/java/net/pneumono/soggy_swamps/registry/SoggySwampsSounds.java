package net.pneumono.soggy_swamps.registry;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.pneumono.soggy_swamps.SoggySwamps;

public class SoggySwampsSounds {
    public static final SoundEvent BLOCK_SUSPICIOUS_MUD_BREAK = register("block.suspicious_mud.break");
    public static final SoundEvent BLOCK_SUSPICIOUS_MUD_STEP = register("block.suspicious_mud.step");
    public static final SoundEvent BLOCK_SUSPICIOUS_MUD_PLACE = register("block.suspicious_mud.place");
    public static final SoundEvent BLOCK_SUSPICIOUS_MUD_HIT = register("block.suspicious_mud.hit");
    public static final SoundEvent BLOCK_SUSPICIOUS_MUD_FALL = register("block.suspicious_mud.fall");
    public static final SoundEvent BLOCK_ROT_CAP_IDLE = register("block.rot_cap.idle");

    public static final SoundEvent ITEM_BRUSH_BRUSHING_MUD =  register("item.brush.brushing.mud");

    public static final SoundEvent ENTITY_ALCHEMIST_AMBIENT = register("entity.alchemist.ambient");
    public static final SoundEvent ENTITY_ALCHEMIST_CELEBRATE = register("entity.alchemist.celebrate");
    public static final SoundEvent ENTITY_ALCHEMIST_DEATH = register("entity.alchemist.death");
    public static final SoundEvent ENTITY_ALCHEMIST_DRINK = register("entity.alchemist.drink");
    public static final SoundEvent ENTITY_ALCHEMIST_HURT = register("entity.alchemist.hurt");
    public static final SoundEvent ENTITY_ALCHEMIST_THROW = register("entity.alchemist.throw");

    public static final BlockSoundGroup GROUP_SUSPICIOUS_MUD = new BlockSoundGroup(
            1.0F,
            1.0F,
            BLOCK_SUSPICIOUS_MUD_BREAK,
            BLOCK_SUSPICIOUS_MUD_STEP,
            BLOCK_SUSPICIOUS_MUD_PLACE,
            BLOCK_SUSPICIOUS_MUD_HIT,
            BLOCK_SUSPICIOUS_MUD_FALL
    );

    private static SoundEvent register(String name) {
        Identifier id = SoggySwamps.id(name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSoggySwampsSounds() {

    }
}
