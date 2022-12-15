package org.bedracket.classicraft;

import net.fabricmc.api.ModInitializer;
import org.bedracket.classicraft.init.ModFlammableContents;

public class ClassiCraftModFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ClassiCraftMod.initialize();
        ModFlammableContents.registerFlammableContents();
    }
}
