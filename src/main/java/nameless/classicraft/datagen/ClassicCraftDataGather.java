package nameless.classicraft.datagen;

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
                new CCBlockStatesProvider(event.getGenerator(), event.getExistingFileHelper()));
        event.getGenerator().addProvider(
                event.includeClient(),
                new CCItemModelProvider(event.getGenerator(), event.getExistingFileHelper()));
        event.getGenerator().addProvider(
                event.includeClient(),
                new CCLootTables(event.getGenerator()));
    }
}
