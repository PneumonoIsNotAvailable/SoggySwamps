package net.pneumono.soggy_swamps.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.pneumono.soggy_swamps.registry.SoggySwampsItems;
import net.pneumono.soggy_swamps.registry.SoggySwampsTags;

import java.util.concurrent.CompletableFuture;

public class SoggySwampsItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public SoggySwampsItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        tag(SoggySwampsTags.ITEM_SWAMP_OAK_LOGS).add(
                SoggySwampsItems.SWAMP_OAK_LOG,
                SoggySwampsItems.SWAMP_OAK_WOOD,
                SoggySwampsItems.STRIPPED_SWAMP_OAK_LOG,
                SoggySwampsItems.STRIPPED_SWAMP_OAK_WOOD
        );

        tag(ItemTags.LOGS_THAT_BURN).forceAddTag(SoggySwampsTags.ITEM_SWAMP_OAK_LOGS);

        tag(ItemTags.SMALL_FLOWERS).add(SoggySwampsItems.BOGSPROUT);
        tag(ItemTags.SAPLINGS).add(SoggySwampsItems.SWAMP_OAK_SAPLING);
        tag(ItemTags.LEAVES).add(SoggySwampsItems.SWAMP_OAK_LEAVES);
        tag(ItemTags.PLANKS).add(SoggySwampsItems.SWAMP_OAK_PLANKS);
        tag(ItemTags.WOODEN_STAIRS).add(SoggySwampsItems.SWAMP_OAK_STAIRS);
        tag(ItemTags.WOODEN_SLABS).add(SoggySwampsItems.SWAMP_OAK_SLAB);
        tag(ItemTags.SIGNS).add(SoggySwampsItems.SWAMP_OAK_SIGN);
        tag(ItemTags.HANGING_SIGNS).add(SoggySwampsItems.SWAMP_OAK_HANGING_SIGN);
        tag(ItemTags.WOODEN_DOORS).add(SoggySwampsItems.SWAMP_OAK_DOOR);
        tag(ItemTags.WOODEN_TRAPDOORS).add(SoggySwampsItems.SWAMP_OAK_TRAPDOOR);
        tag(ItemTags.WOODEN_FENCES).add(SoggySwampsItems.SWAMP_OAK_FENCE);
        tag(ItemTags.FENCE_GATES).add(SoggySwampsItems.SWAMP_OAK_FENCE_GATE);
        tag(ItemTags.WOODEN_PRESSURE_PLATES).add(SoggySwampsItems.SWAMP_OAK_PRESSURE_PLATE);
        tag(ItemTags.WOODEN_BUTTONS).add(SoggySwampsItems.SWAMP_OAK_BUTTON);
        tag(ItemTags.BOATS).add(SoggySwampsItems.SWAMP_OAK_BOAT);
        tag(ItemTags.CHEST_BOATS).add(SoggySwampsItems.SWAMP_OAK_CHEST_BOAT);

        tag(ItemTags.DECORATED_POT_SHERDS).add(
                SoggySwampsItems.HAT_POTTERY_SHERD,
                SoggySwampsItems.SLIME_POTTERY_SHERD,
                SoggySwampsItems.DECAY_POTTERY_SHERD,
                SoggySwampsItems.WEALTH_POTTERY_SHERD
        );

        tag(ConventionalItemTags.MUSHROOMS).add(SoggySwampsItems.ROT_CAP);
        tag(ConventionalItemTags.FENCES).add(SoggySwampsItems.SWAMP_OAK_FENCE);
        tag(ConventionalItemTags.FENCE_GATES).add(SoggySwampsItems.SWAMP_OAK_FENCE_GATE);
        tag(ConventionalItemTags.FOOD_POISONING_FOODS).add(
                SoggySwampsItems.SWAMP_STEW,
                SoggySwampsItems.ROASTED_ROT_CAP
        );
        tag(ConventionalItemTags.SOUP_FOODS).add(SoggySwampsItems.SWAMP_STEW);
        tag(ConventionalItemTags.STRIPPED_LOGS).add(SoggySwampsItems.STRIPPED_SWAMP_OAK_LOG);
        tag(ConventionalItemTags.STRIPPED_WOODS).add(SoggySwampsItems.STRIPPED_SWAMP_OAK_WOOD);
    }

    private TagAppender<Item, Item> tag(TagKey<Item> tag) {
        return valueLookupBuilder(tag);
    }
}
