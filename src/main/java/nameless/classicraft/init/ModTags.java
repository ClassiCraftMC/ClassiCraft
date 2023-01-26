/*
 * ClassiCraft - ClassiCraftMC
 * Copyright (C) 2018-2022.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> LEVEL_SURFACE_PLACEABLE_ON = register("level_surface_placeable_on");

        private static TagKey<Block> register(String name) {
            return BlockTags.create(new ResourceLocation(ClassiCraftMod.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> STONE_PEBBLES = register("stone_pebbles");
        public static final TagKey<Item> VANILLA_PEBBLES = register("vanilla_pebbles");
        public static final TagKey<Item> TAG_DRINKABLE = register("drinkable");
        public static final TagKey<Item> TAG_THICK = register("thick");

        private static TagKey<Item> register(String name) {
            return ItemTags.create(new ResourceLocation(ClassiCraftMod.MOD_ID, name));
        }
    }
}
