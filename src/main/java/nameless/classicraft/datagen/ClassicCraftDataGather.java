package nameless.classicraft.datagen;

import nameless.classicraft.item.CCItems;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * ClassicCraft 数据生成类
 * 用于自动生成模组json文件
 * 运用GatherDataEvent类
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClassicCraftDataGather {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        event.getGenerator().addProvider(
                event.includeClient(),
                new CCLanguageProvider(event.getGenerator(), "en_us"));
        event.getGenerator().addProvider(
                event.includeClient(),
                new CCLanguageZhProvider(event.getGenerator(), "zh_cn"));
        event.getGenerator().addProvider(
                event.includeClient(),
                new CCItemModelProvider(event.getGenerator(), event.getExistingFileHelper(), CCItems.ITEMS)
        );
    }
}
