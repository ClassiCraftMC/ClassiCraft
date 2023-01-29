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
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BlockEvents {

    @SubscribeEvent
    public static void mossyBlock(PlayerInteractEvent.RightClickBlock event) {
        EventUtils.mossyOn(event, Blocks.COBBLESTONE, Blocks.MOSSY_COBBLESTONE, Items.MOSS_BLOCK);
        EventUtils.mossyOff(event, Blocks.MOSSY_COBBLESTONE, Blocks.COBBLESTONE);
    }

    @SubscribeEvent
    public static void pebbleTool(PlayerInteractEvent.RightClickBlock event) {
        EventUtils.pebbleToolByStoneVanilla(event, Items.FLINT);
        EventUtils.pebbleToolByStoneVanilla(event, Items.PRISMARINE_SHARD);
        EventUtils.pebbleToolByStoneVanilla(event, Items.QUARTZ);
    }

    @SubscribeEvent
    public static void putPebbleVanilla(PlayerInteractEvent.RightClickBlock event) {
        EventUtils.putPebbleBlock(event, Items.FLINT, ModBlocks.FLINT.get());
        EventUtils.putPebbleBlock(event, Items.PRISMARINE_SHARD, ModBlocks.PRISMARINE.get());
        EventUtils.putPebbleBlock(event, Items.QUARTZ, ModBlocks.QUARTZ_PEBBLE.get());
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
