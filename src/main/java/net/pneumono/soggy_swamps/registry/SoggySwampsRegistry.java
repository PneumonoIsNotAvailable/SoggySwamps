package net.pneumono.soggy_swamps.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.pneumono.soggy_swamps.SoggySwamps;

import java.util.HashMap;
import java.util.Map;

public class SoggySwampsRegistry {
    public static final Map<Item, ResourceKey<DecoratedPotPattern>> SHERD_TO_PATTERN = new HashMap<>();

    public static final BlockSetType SWAMP_OAK_BLOCK_SET_TYPE = BlockSetTypeBuilder
            .copyOf(BlockSetType.OAK)
            .register(SoggySwamps.id("swamp_oak"));
    public static final WoodType SWAMP_OAK_WOOD_TYPE = WoodTypeBuilder
            .copyOf(WoodType.OAK)
            .register(SoggySwamps.id("swamp_oak"), SWAMP_OAK_BLOCK_SET_TYPE);

    public static final SimpleParticleType FLY = Registry.register(
            BuiltInRegistries.PARTICLE_TYPE,
            SoggySwamps.id("fly"),
            FabricParticleTypes.simple()
    );

    public static void registerSoggySwampsContent() {
        SoggySwampsBlocks.registerSoggySwampsBlocks();
        SoggySwampsItems.registerSoggySwampsItems();
        SoggySwampsEntities.registerSoggySwampsEntities();
        SoggySwampsItemGroups.registerSoggySwampsItemGroups();
        SoggySwampsSounds.registerSoggySwampsSounds();

        TradeOfferHelper.registerWanderingTraderOffers(builder -> {
            builder.addOffersToPool(
                    TradeOfferHelper.WanderingTraderOffersBuilder.SELL_SPECIAL_ITEMS_POOL,
                    new VillagerTrades.ItemsForEmeralds(SoggySwampsItems.SWAMP_OAK_LOG, 1, 8, 4, 1)
            );
            builder.addOffersToPool(
                    TradeOfferHelper.WanderingTraderOffersBuilder.SELL_COMMON_ITEMS_POOL,
                    new VillagerTrades.ItemsForEmeralds(SoggySwampsItems.SWAMP_OAK_SAPLING, 5, 1, 8, 1),
                    new VillagerTrades.ItemsForEmeralds(SoggySwampsItems.ROT_CAP, 2, 1, 8, 1),
                    new VillagerTrades.ItemsForEmeralds(SoggySwampsItems.BOGSPROUT, 1, 1, 8, 1),
                    new VillagerTrades.ItemsForEmeralds(SoggySwampsItems.CATTAIL, 1, 1, 8, 1)
            );
        });
    }
}
