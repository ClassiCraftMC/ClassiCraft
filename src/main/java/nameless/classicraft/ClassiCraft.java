package nameless.classicraft;

import nameless.classicraft.common.block.ModBlocks;
import nameless.classicraft.common.block.entity.ModBlockEntities;
import nameless.classicraft.common.entity.ModEntities;
import nameless.classicraft.common.item.ModItems;
import com.mojang.logging.LogUtils;
import nameless.classicraft.common.menu.ModMenuTypes;
import nameless.classicraft.common.recipe.ModRecipeTypes;
import nameless.classicraft.common.sound.ModSounds;
import nameless.classicraft.common.worldgen.*;
import nameless.classicraft.event.ClassiCraftSubcriber;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

/**
 * ClassicCraft 模组主类
 * modEventBus 用于注册
 * 使用例子:
 * ExampeModItems.ITEMS.register(modEventBus);
 * ExampeModBlocks.BLOCKS.register(modEventBus);
 */
@Mod(ClassiCraft.MODID)
public class ClassiCraft {

    public static final String MODID = "classicraft";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ClassiCraft() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ClassiCraftHooks.handleFood();
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModMenuTypes.MENUS_TYPES.register(modEventBus);
        ModRecipeTypes.RECIPE_SERIALIZER.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITY_TYPES.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        ModSounds.SOUNDS.register(modEventBus);
        ClassiCraftSubcriber.init();
        ModBiomeFeatures.FEATURES.register(modEventBus);
        ModConfiguredFeatures.CONFIGURED_FEATURES.register(modEventBus);
        ModPlacedFeatures.PLACED_FEATURES.register(modEventBus);
        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ClassiCraftConfiguration.SPEC, "classicraft.toml");
    }
}
