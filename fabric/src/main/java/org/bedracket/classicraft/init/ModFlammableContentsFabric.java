package org.bedracket.classicraft.init;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;

public class ModFlammableContentsFabric {

    public static void registerFlammableContents() {
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.CHARCOAL_BLOCK.get(), 5, 60);
    }
}
