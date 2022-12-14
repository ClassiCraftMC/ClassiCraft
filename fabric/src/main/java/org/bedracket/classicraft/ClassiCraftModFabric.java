package org.bedracket.classicraft;

import net.fabricmc.api.ModInitializer;
import org.bedracket.classicraft.init.ModItemGroups;

public class ClassiCraftModFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ClassiCraftMod.init();
        new ModItemGroups();
    }
}
