package nameless.classicraft;

import com.mojang.logging.LogUtils;
import nameless.classicraft.glm.ModLootModifiers;
import nameless.classicraft.init.*;
import nameless.classicraft.util.FoodUtils;
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
    public static final String NETWORK_VERSION = "1.0";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ClassiCraftMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigurations.SPEC, "classicraft.toml");
        ModConfigurations.loadConfig(ModConfigurations.SPEC, FMLPaths.CONFIGDIR.get().resolve("classicraft.toml"));
        FoodUtils.handleFood();
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        modEventBus.addListener(ModCreativeModeTabs::registerCreativeModeTab);
        ModLootModifiers.REGISTER.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);
    }
}
