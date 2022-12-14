package org.bedracket.classicraft.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.bedracket.classicraft.ClassiCraftMod;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> LEVEL_SURFACE_PLACEABLE_ON = register("level_surface_placeable_on");

        private static TagKey<Block> register(String name) {
            return TagKey.create(Registries.BLOCK, new ResourceLocation(ClassiCraftMod.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> STONE_PEBBLES = register("stone_pebbles");

        private static TagKey<Item> register(String name) {
            return TagKey.create(Registries.ITEM, new ResourceLocation(ClassiCraftMod.MOD_ID, name));
        }
    }
}
