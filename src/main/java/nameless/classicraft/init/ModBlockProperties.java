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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import java.util.Optional;
import java.util.function.Supplier;

public class ModBlockProperties {

    public static final int TICK_INTERVAL = 1200;
    public static final int TORCH_INITIAL_BURN_TIME = 17;
    public static final int SOUL_TORCH_INITIAL_BURN_TIME = 16;
    public static final IntegerProperty TORCH_BURN_TIME = IntegerProperty.create("burntime", 0, TORCH_INITIAL_BURN_TIME);
    public static final IntegerProperty SOUL_TORCH_BURN_TIME = IntegerProperty.create("burntime", 0, SOUL_TORCH_INITIAL_BURN_TIME);
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
                            .put(Blocks.BRICKS, ModBlocks.MOSSY_BRICKS.get())
                            .put(Blocks.BRICK_SLAB, ModBlocks.MOSSY_BRICKS_SLAB.get())
                            .put(Blocks.BRICK_STAIRS, ModBlocks.MOSSY_BRICKS_STAIRS.get())
                            .put(Blocks.BRICK_WALL, ModBlocks.MOSSY_BRICKS_WALL.get())
                            .put(ModBlocks.ANDESITE_BRICKS.get(), ModBlocks.MOSSY_ANDESITE_BRICKS.get())
                            .put(ModBlocks.ANDESITE_BRICKS_WALL.get(), ModBlocks.MOSSY_ANDESITE_BRICKS_WALL.get())
                            .put(ModBlocks.ANDESITE_BRICKS_STAIRS.get(), ModBlocks.MOSSY_ANDESITE_BRICKS_STAIRS.get())
                            .put(ModBlocks.ANDESITE_BRICKS_SLAB.get(), ModBlocks.MOSSY_ANDESITE_BRICKS_SLAB.get())
                            .put(ModBlocks.DIORITE_BRICKS.get(), ModBlocks.MOSSY_DIORITE_BRICKS.get())
                            .put(ModBlocks.DIORITE_BRICKS_WALL.get(), ModBlocks.MOSSY_DIORITE_BRICKS_WALL.get())
                            .put(ModBlocks.DIORITE_BRICKS_STAIRS.get(), ModBlocks.MOSSY_DIORITE_BRICKS_STAIRS.get())
                            .put(ModBlocks.DIORITE_BRICKS_SLAB.get(), ModBlocks.MOSSY_DIORITE_BRICKS_SLAB.get())
                            .put(ModBlocks.GRANITE_BRICKS.get(), ModBlocks.MOSSY_GRANITE_BRICKS.get())
                            .put(ModBlocks.GRANITE_BRICKS_WALL.get(), ModBlocks.MOSSY_GRANITE_BRICKS_WALL.get())
                            .put(ModBlocks.GRANITE_BRICKS_STAIRS.get(), ModBlocks.MOSSY_GRANITE_BRICKS_STAIRS.get())
                            .put(ModBlocks.GRANITE_BRICKS_SLAB.get(), ModBlocks.MOSSY_GRANITE_BRICKS_SLAB.get())
                            .put(Blocks.COBBLED_DEEPSLATE, ModBlocks.MOSSY_COBBLED_DEEPSLATE.get())
                            .put(Blocks.COBBLED_DEEPSLATE_SLAB, ModBlocks.MOSSY_COBBLED_DEEPSLATE_SLAB.get())
                            .put(Blocks.COBBLED_DEEPSLATE_STAIRS, ModBlocks.MOSSY_COBBLED_DEEPSLATE_STAIRS.get())
                            .put(Blocks.COBBLED_DEEPSLATE_WALL, ModBlocks.MOSSY_COBBLED_DEEPSLATE_WALL.get())
                            .put(Blocks.DEEPSLATE_BRICKS, ModBlocks.MOSSY_DEEPSLATE_BRICKS.get())
                            .put(Blocks.DEEPSLATE_BRICK_STAIRS, ModBlocks.MOSSY_DEEPSLATE_BRICKS_STAIRS.get())
                            .put(Blocks.DEEPSLATE_BRICK_SLAB, ModBlocks.MOSSY_DEEPSLATE_BRICKS_SLAB.get())
                            .put(Blocks.DEEPSLATE_BRICK_WALL, ModBlocks.MOSSY_DEEPSLATE_BRICKS_WALL.get())
                            .put(Blocks.DEEPSLATE_TILES, ModBlocks.MOSSY_DEEPSLATE_TILES.get())
                            .put(Blocks.DEEPSLATE_TILE_SLAB, ModBlocks.MOSSY_DEEPSLATE_TILES_SLAB.get())
                            .put(Blocks.DEEPSLATE_TILE_STAIRS, ModBlocks.MOSSY_DEEPSLATE_TILES_STAIRS.get())
                            .put(Blocks.DEEPSLATE_TILE_WALL, ModBlocks.MOSSY_DEEPSLATE_TILES_WALL.get())
                            .build());

    public static final Supplier<BiMap<Block, Block>> MOSSY_OFF_BY_BLOCK =
            Suppliers.memoize(() -> MOSSY_ABLES.get().inverse());

    public static Optional<BlockState> getMossed(BlockState pState) {
        return Optional.ofNullable(MOSSY_ABLES.get().get(pState.getBlock()))
                .map((mossed) -> mossed.withPropertiesOf(pState));
    }

    public static Optional<BlockState> getMossedOFF(BlockState pState) {
        return Optional.ofNullable(MOSSY_OFF_BY_BLOCK.get().get(pState.getBlock()))
                .map((mossed) -> mossed.withPropertiesOf(pState));
    }
}
