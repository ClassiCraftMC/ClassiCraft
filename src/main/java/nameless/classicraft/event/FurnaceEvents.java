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
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ClassiCraftMod.MOD_ID)
public class FurnaceEvents {

    @SubscribeEvent
    public static void setFuelValue(FurnaceFuelBurnTimeEvent event) {
        handleFuelValue(event, ModItems.TALLOW.get(), 20);
        handleFuelValue(event, ItemTags.SAPLINGS, 100);
        handleFuelValue(event, ItemTags.LEAVES, 200);
        handleFuelValue(event, ModBlocks.THATCH.get(), 200);
        handleFuelValue(event, ModBlocks.DRIED_THATCH.get(), 200);
        handleFuelValue(event, ModBlocks.THATCH_STAIRS.get(), 200);
        handleFuelValue(event, ModBlocks.DRIED_THATCH_STAIRS.get(), 200);
        handleFuelValue(event, ModBlocks.THATCH_SLAB.get(), 50);
        handleFuelValue(event, ModBlocks.DRIED_THATCH_SLAB.get(), 50);
        handleFuelValue(event, ModBlocks.THATCH_CARPET.get(), 20);
        handleFuelValue(event, ModBlocks.DRIED_THATCH_CARPET.get(), 20);
        handleFuelValue(event, Items.WHEAT, 200);
        handleFuelValue(event, ModBlocks.REED.get(), 200);
        handleFuelValue(event, ModBlocks.CATTAIL.get(), 200);
        handleFuelValue(event, Items.HAY_BLOCK, 1800);
        handleFuelValue(event, ModBlocks.THATCH.get(), 1800);
        handleFuelValue(event, ModBlocks.DRIED_THATCH.get(), 1800);
        handleFuelValue(event, ModBlocks.THATCH_SLAB.get(), 900);
        handleFuelValue(event, ModBlocks.DRIED_THATCH_SLAB.get(), 900);
        handleFuelValue(event, ModBlocks.THATCH_STAIRS.get(), 1800);
        handleFuelValue(event, ModBlocks.DRIED_THATCH_STAIRS.get(), 1800);
        handleFuelValue(event, ModBlocks.THATCH_CARPET.get(), 450);
        handleFuelValue(event, ModBlocks.DRIED_THATCH_CARPET.get(), 450);
    }

    private static void handleFuelValue(FurnaceFuelBurnTimeEvent event, ItemLike item, int burnTime) {
        var itemStack = event.getItemStack();
        if (itemStack.is(item.asItem())) {
            event.setBurnTime(burnTime);
        }
    }

    private static void handleFuelValue(FurnaceFuelBurnTimeEvent event, TagKey<Item> item, int burnTime) {
        var itemStack = event.getItemStack();
        if (itemStack.is(item)) {
            event.setBurnTime(burnTime);
        }
    }
}
