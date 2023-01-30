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
package nameless.classicraft.event;

import nameless.classicraft.api.event.BlockDropEvent;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.util.EventUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DeadBushBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BlockEvents {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void mossyBlock(PlayerInteractEvent.RightClickBlock event) {
        EventUtils.mossyAll(event, Blocks.COBBLESTONE, Blocks.MOSSY_COBBLESTONE);
        EventUtils.mossyAll(event, Blocks.COBBLESTONE_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB);
        EventUtils.mossyAll(event, Blocks.COBBLESTONE_STAIRS, Blocks.MOSSY_COBBLESTONE_STAIRS);
        EventUtils.mossyAll(event, Blocks.COBBLESTONE_WALL, Blocks.MOSSY_COBBLESTONE_WALL);
        EventUtils.mossyAll(event, Blocks.STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS);
        EventUtils.mossyAll(event, Blocks.STONE_BRICK_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB);
        EventUtils.mossyAll(event, Blocks.STONE_BRICK_STAIRS, Blocks.MOSSY_STONE_BRICK_STAIRS);
        EventUtils.mossyAll(event, Blocks.STONE_BRICK_WALL, Blocks.MOSSY_STONE_BRICK_WALL);
    }

    @SubscribeEvent
    public static void pebbleTool(PlayerInteractEvent.RightClickBlock event) {
        EventUtils.pebbleToolByStoneVanilla(event, Items.FLINT);
        EventUtils.pebbleToolByStoneVanilla(event, Items.PRISMARINE_SHARD);
        EventUtils.pebbleToolByStoneVanilla(event, Items.QUARTZ);
    }

    @SubscribeEvent
    public static void putAddonVanilla(PlayerInteractEvent.RightClickBlock event) {
        EventUtils.putAddonBlock(event, Items.STICK, ModBlocks.TWIGS.get());
        EventUtils.putAddonBlock(event, Items.FLINT, ModBlocks.FLINT.get());
        EventUtils.putAddonBlock(event, Items.PRISMARINE_SHARD, ModBlocks.PRISMARINE.get());
        EventUtils.putAddonBlock(event, Items.QUARTZ, ModBlocks.QUARTZ_PEBBLE.get());
    }

    @SubscribeEvent
    public static void blockDropItems(BlockDropEvent event) {
        Block block = event.getState().getBlock();
        if (!event.getPlayer().isCreative()) {
            if (block instanceof LeavesBlock
                    || block instanceof DeadBushBlock) {
                EventUtils.blockdropRandom(event, block, Items.STICK);
            }
        }
    }
}
