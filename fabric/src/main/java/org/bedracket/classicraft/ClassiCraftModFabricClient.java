package org.bedracket.classicraft;

import net.fabricmc.api.ClientModInitializer;
import org.bedracket.classicraft.init.ModBlockRenderTypes;
import org.bedracket.classicraft.init.ModModelPredicates;

public class ClassiCraftModFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModModelPredicates.registerModelPredicates();
        ModBlockRenderTypes.registerBlockRenderTypes();
    }
}
