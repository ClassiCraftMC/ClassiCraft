package org.bedracket.classicraft;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.bedracket.classicraft.init.ModCreativeTabs;

@Mod(ClassiCraftMod.MOD_ID)
public class ClassiCraftModForge {

    public ClassiCraftModForge() {
        final IEventBus forgeBus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(ClassiCraftMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        ClassiCraftMod.init();
        forgeBus.addListener(ModCreativeTabs::registerCreativeModeTab);
    }
}
