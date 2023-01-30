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

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import java.util.function.Supplier;

public class ModBlockProperties {

    public static final int TICK_INTERVAL = 1200;
    public static final int TORCH_INITIAL_BURN_TIME = 17;
    public static final int SOUL_TORCH_INITIAL_BURN_TIME = 16;
    public static final IntegerProperty TORCH_BURN_TIME = IntegerProperty.create("burntime", 0, TORCH_INITIAL_BURN_TIME);
    public static final IntegerProperty SOUL_TORCH_BURN_TIME = IntegerProperty.create("burntime", 0, 16);
    public static final IntegerProperty LANTERN_BURN_TIME = IntegerProperty.create("burntime", 0, 9);
    public static final IntegerProperty SOUL_LANTERN_BURN_TIME = IntegerProperty.create("burntime", 0, 8);
    public static final IntegerProperty FULE_LEVEL = IntegerProperty.create("fuel_level", 0, 4);

    public static final Item[] MOSSY_OFF_SCRAPING_TOOLS =
            new Item[]{Items.WOODEN_AXE,
                    Items.GOLDEN_AXE,
                    Items.STONE_AXE,
                    Items.IRON_AXE,
                    Items.DIAMOND_AXE,
                    Items.NETHERITE_AXE};

    public static final Supplier<BiMap<Block, Block>> MOSSY_ABLES =
            Suppliers.memoize(() ->
                    ImmutableBiMap.<Block, Block>builder()
                            .put(Blocks.COBBLESTONE, Blocks.MOSSY_COBBLESTONE)
                            .put(Blocks.COBBLESTONE_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB)
                            .put(Blocks.COBBLESTONE_STAIRS, Blocks.MOSSY_COBBLESTONE_STAIRS)
                            .put(Blocks.COBBLESTONE_WALL, Blocks.MOSSY_COBBLESTONE_WALL)
                            .put(Blocks.STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS)
                            .put(Blocks.STONE_BRICK_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB)
                            .put(Blocks.STONE_BRICK_STAIRS, Blocks.MOSSY_STONE_BRICK_STAIRS)
                            .put(Blocks.STONE_BRICK_WALL, Blocks.MOSSY_STONE_BRICK_WALL)
                            .build());

    public static final Supplier<BiMap<Block, Block>> MOSSY_OFF_BY_BLOCK =
            Suppliers.memoize(() -> MOSSY_ABLES.get().inverse());
}
