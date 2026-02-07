package net.pneumono.soggy_swamps.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalEntityTypeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;
import net.pneumono.soggy_swamps.registry.SoggySwampsEntities;

import java.util.concurrent.CompletableFuture;

public class SoggySwampsEntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {
    public SoggySwampsEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        valueLookupBuilder(EntityTypeTags.BOAT).add(
                SoggySwampsEntities.SWAMP_OAK_BOAT
        );

        valueLookupBuilder(ConventionalEntityTypeTags.BOATS).add(
                SoggySwampsEntities.SWAMP_OAK_CHEST_BOAT
        );
    }
}
