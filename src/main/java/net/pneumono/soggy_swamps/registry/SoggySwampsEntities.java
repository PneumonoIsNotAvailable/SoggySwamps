package net.pneumono.soggy_swamps.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.pneumono.soggy_swamps.SoggySwamps;
import net.pneumono.soggy_swamps.content.SwampSpiderEntity;

import java.util.function.Supplier;

public class SoggySwampsEntities {
    public static EntityType<SwampSpiderEntity> SWAMP_SPIDER = register(
            "swamp_spider",
            EntityType.Builder.of(SwampSpiderEntity::new, MobCategory.MONSTER)
                    .sized(1.4F, 0.9F)
                    .eyeHeight(0.65F)
                    .passengerAttachments(0.765F)
                    .clientTrackingRange(12)
    );

    public static EntityType<Boat> SWAMP_OAK_BOAT = register(
            "swamp_oak_boat",
            EntityType.Builder.of(getBoatFactory(() -> SoggySwampsItems.SWAMP_OAK_BOAT), MobCategory.MISC)
                    .noLootTable()
                    .sized(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .clientTrackingRange(10)
    );

    public static EntityType<ChestBoat> SWAMP_OAK_CHEST_BOAT = register(
            "swamp_oak_chest_boat",
            EntityType.Builder.of(getChestBoatFactory(() -> SoggySwampsItems.SWAMP_OAK_CHEST_BOAT), MobCategory.MISC)
                    .noLootTable()
                    .sized(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .clientTrackingRange(10)
    );

    private static EntityType.EntityFactory<Boat> getBoatFactory(Supplier<Item> itemSupplier) {
        return (type, world) -> new Boat(type, world, itemSupplier);
    }

    private static EntityType.EntityFactory<ChestBoat> getChestBoatFactory(Supplier<Item> itemSupplier) {
        return (type, world) -> new ChestBoat(type, world, itemSupplier);
    }

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> type) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, SoggySwamps.id(name));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, type.build(key));
    }

    public static void registerSoggySwampsEntities() {
        FabricDefaultAttributeRegistry.register(SWAMP_SPIDER, SwampSpiderEntity.createSwampSpiderAttributes());
    }
}
