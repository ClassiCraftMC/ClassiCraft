package org.bedracket.classicraft;

import net.fabricmc.api.ModInitializer;
import org.bedracket.classicraft.init.ModFlammableContents;
import org.bedracket.classicraft.init.ModModelPredicates;

public class ClassiCraftModFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ClassiCraftMod.init();
        ModFlammableContents.registerFlammableContents();
        ModModelPredicates.registerModelPredicates();
    }
}
