package net.pneumono.soggy_swamps.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EntityTypeTags;
import net.pneumono.soggy_swamps.registry.SoggySwampsEntities;

import java.util.concurrent.CompletableFuture;

public class SoggySwampsEntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {
    public SoggySwampsEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(EntityTypeTags.ARTHROPOD).add(
                SoggySwampsEntities.SWAMP_SPIDER
        );
        valueLookupBuilder(EntityTypeTags.NO_ANGER_FROM_WIND_CHARGE).add(
                SoggySwampsEntities.SWAMP_SPIDER
        );
        valueLookupBuilder(EntityTypeTags.BOAT).add(
                SoggySwampsEntities.SWAMP_OAK_BOAT
        );
    }
}
