package org.bedracket.classicraft;

import org.bedracket.classicraft.init.ModFlammableContentsQuilt;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class ClassiCraftModQuilt implements ModInitializer {

    @Override
    public void onInitialize(ModContainer mod) {
        ClassiCraftMod.initialize();
        ModFlammableContentsQuilt.registerFlammableContents();
    }
}
