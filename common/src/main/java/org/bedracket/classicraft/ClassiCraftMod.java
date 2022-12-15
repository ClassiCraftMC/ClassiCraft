package org.bedracket.classicraft;

import com.mojang.logging.LogUtils;
import org.bedracket.classicraft.config.Configuration;
import org.bedracket.classicraft.event.ItemTickEvents;
import org.bedracket.classicraft.init.*;
import org.slf4j.Logger;

public class ClassiCraftMod {

    public static final String MOD_ID = "classicraft";
    public static Logger LOGGER = LogUtils.getLogger();

    public static void init() {
        new Configuration(ModConfigs.class, MOD_ID);
        ModBlocks.BLOCKS.register();
        ModItems.ITEMS.register();
        ModBlockRenderLayers.registerBlockRenderLayers();
        ModItemProperties.registerItemProperties();
        ItemTickEvents.registerItemTickEvents();
    }
}
