package net.pneumono.soggy_swamps.content.templespawner;

import net.minecraft.util.StringIdentifiable;

public enum TempleSpawnerState implements StringIdentifiable {
    INACTIVE("inactive"),
    ACTIVE("active"),
    COOLDOWN("cooldown");

    private final String id;

    TempleSpawnerState(String id) {
        this.id = id;
    }

    @Override
    public String asString() {
        return this.id;
    }
}
