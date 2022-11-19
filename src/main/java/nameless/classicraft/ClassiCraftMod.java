package nameless.classicraft;

import com.mojang.logging.LogUtils;
import nameless.classicraft.event.ClientEvents;
import nameless.classicraft.event.TestEvents;
import nameless.classicraft.init.*;
import nameless.classicraft.util.FoodUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.slf4j.Logger;

/**
 * ClassicCraft 模组主类
 * modEventBus 用于注册
 * 使用例子:
 * ExampeModItems.ITEMS.register(modEventBus);
 * ExampeModBlocks.BLOCKS.register(modEventBus);
 */
@Mod(ClassiCraftMod.MODID)
public class ClassiCraftMod {

    public static final String MODID = "classicraft";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ClassiCraftMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ClassiCraftConfiguration.SPEC, "classicraft.toml");
        ClassiCraftConfiguration.loadConfig(ClassiCraftConfiguration.SPEC, FMLPaths.CONFIGDIR.get().resolve("classicraft.toml"));
        FoodUtils.handleFood();
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModMenuTypes.MENUS_TYPES.register(modEventBus);
        ModRecipeTypes.RECIPE_SERIALIZERS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITY_TYPES.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        ModSounds.SOUNDS.register(modEventBus);
        ModBiomeFeatures.FEATURES.register(modEventBus);
        ModConfiguredFeatures.CONFIGURED_FEATURES.register(modEventBus);
        ModPlacedFeatures.PLACED_FEATURES.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);
        ClientEvents.initClient();
        TestEvents.init();
    }
}
