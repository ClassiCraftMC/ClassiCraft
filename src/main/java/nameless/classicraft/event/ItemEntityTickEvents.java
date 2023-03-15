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

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.api.event.ItemEntityTickEvent;
import nameless.classicraft.init.ModItems;
import nameless.classicraft.util.EventUtils;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ClassiCraftMod.MOD_ID)
public class ItemEntityTickEvents {

    @SubscribeEvent
    public static void onItemTicking(ItemEntityTickEvent event) {
        EventUtils.tickItemToUnlit(event, ModItems.TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemToUnlit(event, ModItems.SOUL_TORCH_LIT.get(), Items.STICK);
    }

    @SubscribeEvent
    public static void onTorchInRaining(ItemEntityTickEvent event) {
        EventUtils.tickItemInRaining(event, ModItems.TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemInRaining(event, ModItems.SOUL_TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemInRaining(event, ModItems.TORCH_UNLIT.get(), Items.STICK);
        EventUtils.tickItemInRaining(event, ModItems.SOUL_TORCH_UNLIT.get(), Items.STICK);
    }

    @SubscribeEvent
    public static void onItemInWater(ItemEntityTickEvent event) {
        EventUtils.tickItemInWater(event, ModItems.TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemInWater(event, ModItems.SOUL_TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemInWater(event, ModItems.TORCH_UNLIT.get(), Items.STICK);
        EventUtils.tickItemInWater(event, ModItems.SOUL_TORCH_UNLIT.get(), Items.STICK);
        EventUtils.tickItemInWater(event, Items.SPONGE, Items.WET_SPONGE);
    }
}
