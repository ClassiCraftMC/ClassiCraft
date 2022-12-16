package org.bedracket.classicraft;

import com.mojang.logging.LogUtils;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.bedracket.classicraft.config.Configuration;
import org.bedracket.classicraft.event.*;
import org.bedracket.classicraft.init.*;
import org.slf4j.Logger;

public class ClassiCraftMod {

    public static final String MOD_ID = "classicraft";
    public static Logger LOGGER = LogUtils.getLogger();

    public static void initialize() {
        new Configuration(ModConfigs.class, MOD_ID);
        ModBlocks.BLOCKS.register();
        ModItems.ITEMS.register();
        ModFuels.registerFuels();
        ItemTickEvents.registerItemTickEvents();
        BlockEvents.registerBlockEvents();
        EntityEvents.registerEntityEvents();
        LivingEntityEvents.registerLivingEntityEvents();
        PlayerEvents.registerPlayerEvents();
        EnvExecutor.runInEnv(Env.CLIENT, () -> ClassiCraftMod::initializeClient);
    }

    @Environment(EnvType.CLIENT)
    public static void initializeClient() {
        ModBlockRenderLayers.registerBlockRenderLayers();
        ModItemProperties.registerItemProperties();
    }
}
