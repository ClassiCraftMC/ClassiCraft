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

import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import nameless.classicraft.util.EventUtils;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerEvents {

    @SubscribeEvent
    public static void pebbleTool(PlayerInteractEvent.RightClickItem event) {
        EventUtils.pebbleToolByHandVanilla(event, Items.FLINT);
        EventUtils.pebbleToolByHandVanilla(event, Items.PRISMARINE_SHARD);
        EventUtils.pebbleToolByHandVanilla(event, Items.QUARTZ);
    }

    @SubscribeEvent
    public static void rightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        EventUtils.litItem(event, ModBlocks.REAL_TORCH.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(event, ModBlocks.REAL_SOUL_TORCH.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(event, ModBlocks.REAL_WALL_TORCH.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(event, ModBlocks.REAL_SOUL_WALL_TORCH.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(event, ModBlocks.REAL_TORCH.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(event, ModBlocks.REAL_SOUL_TORCH.get(), ModItems.SOUL_TORCH_UNLIT.get(), ModItems.SOUL_TORCH_LIT.get());
        EventUtils.litItem(event, ModBlocks.REAL_WALL_TORCH.get(), ModItems.SOUL_TORCH_UNLIT.get(), ModItems.SOUL_TORCH_LIT.get());
        EventUtils.litItem(event, ModBlocks.REAL_SOUL_WALL_TORCH.get(), ModItems.SOUL_TORCH_UNLIT.get(), ModItems.SOUL_TORCH_LIT.get());
        EventUtils.litBlock(event, ModBlocks.REAL_TORCH.get(), Items.SOUL_SOIL, ModBlocks.REAL_SOUL_TORCH.get());
    }

    @SubscribeEvent
    public static void rightClickItem(PlayerInteractEvent.RightClickItem event) {
        EventUtils.shiftRightItem(event, ModItems.TORCH_LIT.get(), Items.STICK.getDefaultInstance());
        EventUtils.shiftRightItem(event, ModItems.SOUL_TORCH_LIT.get(), Items.STICK.getDefaultInstance());
       /**
        EventUtils.litItem(event, Items.FLINT_AND_STEEL, ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(event, Items.FLINT_AND_STEEL, ModItems.SOUL_TORCH_UNLIT.get(), ModItems.SOUL_TORCH_LIT.get());
        */
        EventUtils.litItem(event, ModItems.TORCH_LIT.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(event, ModItems.SOUL_TORCH_LIT.get(), ModItems.SOUL_TORCH_UNLIT.get(), ModItems.SOUL_TORCH_LIT.get());
        EventUtils.litItem(event, ModItems.SOUL_TORCH_LIT.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(event, ModItems.TORCH_LIT.get(), ModItems.SOUL_TORCH_UNLIT.get(), ModItems.SOUL_TORCH_LIT.get());
        EventUtils.litItem(event, Items.SOUL_SOIL.asItem(), ModItems.TORCH_UNLIT.get(), ModItems.SOUL_TORCH_UNLIT.get());
        EventUtils.litItem(event, Items.SOUL_SOIL.asItem(), ModItems.TORCH_LIT.get(), ModItems.SOUL_TORCH_LIT.get());
    }
}
