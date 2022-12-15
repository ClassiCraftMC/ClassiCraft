package org.bedracket.classicraft;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ClassiCraftMod.MOD_ID)
public class ClassiCraftModForge {

    public ClassiCraftModForge() {
        EventBuses.registerModEventBus(ClassiCraftMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        ClassiCraftMod.initialize();
    }
}
