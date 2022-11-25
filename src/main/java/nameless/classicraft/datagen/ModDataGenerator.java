package nameless.classicraft.datagen;

import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * ClassicCraft 数据生成类
 * 用于自动生成模组json文件
 * 运用GatherDataEvent类
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        ExistingFileHelper helper = event.getExistingFileHelper();
        DataGenerator generator = event.getGenerator();
        generator.addProvider(event.includeClient(),
                new ModItemModelProvider(generator, helper, ModItems.ITEMS));
        generator.addProvider(event.includeClient(),
                new ModBlockStateProvider(generator, helper, ModBlocks.BLOCKS));
        generator.addProvider(event.includeClient(),
                new ModLanguageProvider(generator));
    }
}
