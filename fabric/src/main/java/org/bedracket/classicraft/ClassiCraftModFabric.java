package org.bedracket.classicraft;

import net.fabricmc.api.ModInitializer;

public class ClassiCraftModFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ClassiCraftMod.init();
    }
}
