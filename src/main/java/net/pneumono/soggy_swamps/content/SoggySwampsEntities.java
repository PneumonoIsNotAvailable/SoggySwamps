package net.pneumono.soggy_swamps.content;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.pneumono.soggy_swamps.SoggySwamps;

import java.util.function.Supplier;

public class SoggySwampsEntities {
    public static EntityType<BoatEntity> SWAMP_OAK_BOAT = register(
            "swamp_oak_boat",
            EntityType.Builder.create(getBoatFactory(() -> SoggySwampsItems.SWAMP_OAK_BOAT), SpawnGroup.MISC)
                    .dropsNothing()
                    .dimensions(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .maxTrackingRange(10)
    );

    public static EntityType<ChestBoatEntity> SWAMP_OAK_CHEST_BOAT = register(
            "swamp_oak_chest_boat",
            EntityType.Builder.create(getChestBoatFactory(() -> SoggySwampsItems.SWAMP_OAK_CHEST_BOAT), SpawnGroup.MISC)
                    .dropsNothing()
                    .dimensions(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .maxTrackingRange(10)
    );

    private static EntityType.EntityFactory<BoatEntity> getBoatFactory(Supplier<Item> itemSupplier) {
        return (type, world) -> new BoatEntity(type, world, itemSupplier);
    }

    private static EntityType.EntityFactory<ChestBoatEntity> getChestBoatFactory(Supplier<Item> itemSupplier) {
        return (type, world) -> new ChestBoatEntity(type, world, itemSupplier);
    }

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> type) {
        RegistryKey<EntityType<?>> key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, SoggySwamps.id(name));
        return Registry.register(Registries.ENTITY_TYPE, key, type.build(key));
    }

    public static void registerSoggySwampsEntities() {

    }
}
