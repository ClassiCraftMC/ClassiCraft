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
import nameless.classicraft.network.PlayerSanMessage;
import nameless.classicraft.network.SanChangeMessage;
import nameless.classicraft.network.SimpleNetworkHandler;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = ClassiCraftMod.MOD_ID)
public class SetupEvents {

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SimpleNetworkHandler.registerMessage(PlayerSanMessage.class,
                    PlayerSanMessage::buffer, PlayerSanMessage::new, PlayerSanMessage::handler);
            SimpleNetworkHandler.registerMessage(SanChangeMessage.class,
                    SanChangeMessage::buffer, SanChangeMessage::new, SanChangeMessage::handler);
            handleCompostableItem(ModBlocks.THATCH.get(), 0.65F);
            handleCompostableItem(ModBlocks.DRIED_THATCH.get(), 0.65F);
            handleCompostableItem(ModBlocks.THATCH_STAIRS.get(), 0.65F);
            handleCompostableItem(ModBlocks.DRIED_THATCH_STAIRS.get(), 0.65F);
            handleCompostableItem(ModBlocks.THATCH_SLAB.get(), 0.55F);
            handleCompostableItem(ModBlocks.DRIED_THATCH_SLAB.get(), 0.55F);
            handleCompostableItem(ModBlocks.THATCH_CARPET.get(), 0.50F);
            handleCompostableItem(ModBlocks.DRIED_THATCH_CARPET.get(), 0.50F);
            handleCompostableItem(ModItems.ROTTEN_FOOD.get(), 0.3F);
            handleCompostableItem(ModBlocks.REED.get(), 0.45F);
            handleCompostableItem(ModBlocks.CATTAIL.get(), 0.45F);
        });
    }

    private static void handleCompostableItem(ItemLike item, float chance) {
        boolean hasPut = ComposterBlock.COMPOSTABLES.containsKey(item.asItem());
        if (!hasPut) {
            ComposterBlock.COMPOSTABLES.put(item.asItem(), chance);
        }
    }
}