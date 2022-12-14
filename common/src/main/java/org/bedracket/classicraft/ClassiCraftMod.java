package org.bedracket.classicraft;

import com.mojang.logging.LogUtils;
import org.bedracket.classicraft.config.Configuration;
import org.bedracket.classicraft.event.ItemTickEvents;
import org.bedracket.classicraft.init.ModBlocks;
import org.bedracket.classicraft.init.ModConfigs;
import org.bedracket.classicraft.init.ModItems;
import org.bedracket.eventbus.BedRacket;
import org.slf4j.Logger;

public class ClassiCraftMod {

    public static final String MOD_ID = "classicraft";
    public static Logger LOGGER = LogUtils.getLogger();

    public static void init() {
        final BedRacket bus = BedRacket.EVENT_BUS;
        int a = bus.addListener(new ItemTickEvents());
        LOGGER.info("Registered " + a + " ItemTicking Events for ClassiCraft Mod.");
        new Configuration(ModConfigs.class, MOD_ID);
        ModBlocks.BLOCKS.register();
        ModItems.ITEMS.register();
    }
}
