package nameless.classicraft;

import com.mojang.logging.LogUtils;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModConfigs;
import nameless.classicraft.init.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.slf4j.Logger;

@Mod(ClassiCraftMod.MOD_ID)
public class ClassiCraftMod {

    public static final String MOD_ID = "classicraft";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ClassiCraftMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigs.SPEC, "classicraft.toml");
        ModConfigs.loadConfig(ModConfigs.SPEC, FMLPaths.CONFIGDIR.get().resolve("classicraft.toml"));
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
    }
}
