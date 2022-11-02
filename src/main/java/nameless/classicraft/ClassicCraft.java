package nameless.classicraft;

import nameless.classicraft.item.CCItems;
import com.mojang.logging.LogUtils;
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
@Mod(ClassicCraft.MODID)
public class ClassicCraft {

    public static final String MODID = "classicraft";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ClassicCraft() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        CCItems.ITEMS.register(modEventBus);
    }
}
