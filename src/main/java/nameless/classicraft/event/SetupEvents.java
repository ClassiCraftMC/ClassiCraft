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
import nameless.classicraft.entity.LivingDead;
import nameless.classicraft.init.ModConfigurations;
import nameless.classicraft.init.ModItems;
import nameless.classicraft.network.SimpleNetworkHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SetupEvents {

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        SimpleNetworkHandler.init();
        event.enqueueWork(LivingDead::init);
        boolean hasRottenFood = ComposterBlock.COMPOSTABLES.containsKey(ModItems.ROTTEN_FOOD.get());

        if (!hasRottenFood) {
            ComposterBlock.COMPOSTABLES.put(ModItems.ROTTEN_FOOD.get(), .3F);
        }
    }

    @SubscribeEvent
    public static void onWorldSetup(LevelEvent.Load event) {
        if (event.getLevel() instanceof final ServerLevel level) {
            final MinecraftServer server = level.getServer();
            final GameRules rules = level.getGameRules();
            if (ModConfigurations.enableForcedGameRules.get()) {
                rules.getRule(GameRules.RULE_NATURAL_REGENERATION).set(false, server);

                ClassiCraftMod.LOGGER.info("Updating ClassiCraft Relevant Game Rules for level {}.",
                        level.dimension().location());
            }
        }
    }
}