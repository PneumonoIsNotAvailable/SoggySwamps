package net.pneumono.soggy_shrines.content;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.pneumono.soggy_shrines.SoggyShrines;

import java.util.function.Function;

public class SoggyShrinesRegistry {

    protected static <T extends Item> T registerItem(String name, Function<Item.Settings, T> factory, Item.Settings settings) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, SoggyShrines.id(name));
        return Registry.register(Registries.ITEM, key, factory.apply(settings.registryKey(key)));
    }

    protected static <T extends Block> T registerBlock(String name, Function<AbstractBlock.Settings, T> factory, AbstractBlock.Settings settings) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, SoggyShrines.id(name));
        return Registry.register(Registries.BLOCK, key, factory.apply(settings.registryKey(key)));
    }

    protected static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(
            String name,
            FabricBlockEntityTypeBuilder.Factory<T> factory,
            Block... blocks
    ) {
        return Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                SoggyShrines.id(name),
                FabricBlockEntityTypeBuilder.create(factory, blocks).build()
        );
    }

    public static void registerSoggyShrinesContent() {

    }
}
