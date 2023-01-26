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
package nameless.classicraft;

import com.mojang.logging.LogUtils;
import nameless.classicraft.glm.ModLootModifiers;
import nameless.classicraft.init.*;
import nameless.classicraft.util.FoodUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.slf4j.Logger;

@Mod(ClassiCraftMod.MOD_ID)
public class ClassiCraftMod {

    public static final String MOD_ID = "classicraft";
    public static final String NETWORK_VERSION = "1.0";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ClassiCraftMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigurations.SPEC, "classicraft.toml");
        ModConfigurations.loadConfig(ModConfigurations.SPEC, FMLPaths.CONFIGDIR.get().resolve("classicraft.toml"));
        FoodUtils.handleFood();
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        modEventBus.addListener(ModCreativeModeTabs::registerCreativeModeTab);
        ModLootModifiers.REGISTER.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);
    }
}
