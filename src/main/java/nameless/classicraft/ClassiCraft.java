package nameless.classicraft;

import nameless.classicraft.common.block.ModBlocks;
import nameless.classicraft.common.block.entity.ModBlockEntities;
import nameless.classicraft.common.item.ModItems;
import com.mojang.logging.LogUtils;
import nameless.classicraft.common.menu.ModMenuTypes;
import nameless.classicraft.event.ClassiCraftSubcriber;
import nameless.classicraft.event.ClassicCraftClientSubcriber;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
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
        modEventBus.addListener(ClassicCraftClientSubcriber::renderRegister);
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModMenuTypes.MENUS_TYPES.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITY_TYPES.register(modEventBus);
        ClassiCraftSubcriber.init();
    }

}
