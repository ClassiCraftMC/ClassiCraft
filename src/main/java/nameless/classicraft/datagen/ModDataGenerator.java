package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.datagen.levelgen.ModConfiguredFeatureProvider;
import nameless.classicraft.datagen.levelgen.ModPlacedFeatureProvider;
import nameless.classicraft.datagen.loot.ModLootTableProvider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

/**
 * ClassicCraft 数据生成类
 * 用于自动生成模组json文件
 * 运用GatherDataEvent类
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGenerator {

    public static RegistrySetBuilder BUILDER =
            new RegistrySetBuilder()
                    .add(Registries.PLACED_FEATURE,
                            ModPlacedFeatureProvider::placedFeature)
                    .add(Registries.CONFIGURED_FEATURE,
                            ModConfiguredFeatureProvider::configuredFeatures);

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        ExistingFileHelper helper = event.getExistingFileHelper();
        DataGenerator generator = event.getGenerator();
        generator.addProvider(event.includeClient(),
                new ModBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(),
                new ModItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(),
                new ModLanguageProvider(generator));
        generator.addProvider(event.includeClient(),
                new ModLanguageProviderZh(generator));
        generator.addProvider(event.includeClient(),
                new EmptyLangProvider(generator.getPackOutput(), ClassiCraftMod.MOD_ID));

        generator.addProvider(event.includeServer(),
                new ModLootTableProvider(generator.getPackOutput()));
        generator.addProvider(event.includeServer(),
                new ModGlobalModifierProvider(generator.getPackOutput(), ClassiCraftMod.MOD_ID));
        generator.addProvider(event.includeServer(),
                new DatapackBuiltinEntriesProvider(generator.getPackOutput(),
                        event.getLookupProvider(), BUILDER,
                        Set.of(ClassiCraftMod.MOD_ID)));
    }
}
