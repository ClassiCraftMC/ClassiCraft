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

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import nameless.classicraft.entity.LivingDeadEntity;
import nameless.classicraft.init.ModItems;
import nameless.classicraft.network.SimpleNetworkHandler;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SetupEvents {

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        SimpleNetworkHandler.init();
        event.enqueueWork(LivingDeadEntity::init);
        boolean hasRottenFood = ComposterBlock.COMPOSTABLES.containsKey(ModItems.ROTTEN_FOOD.get());

        if (!hasRottenFood) {
            ComposterBlock.COMPOSTABLES.put(ModItems.ROTTEN_FOOD.get(), .3F);
        }
    }
}