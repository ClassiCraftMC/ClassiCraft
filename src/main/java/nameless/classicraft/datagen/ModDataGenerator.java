package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.datagen.language.EmptyLangProvider;
import nameless.classicraft.datagen.language.ModLanguageProvider;
import nameless.classicraft.datagen.language.ModLanguageProviderZh;
import nameless.classicraft.datagen.levelgen.ModConfiguredFeatureProvider;
import nameless.classicraft.datagen.levelgen.ModPlacedFeatureProvider;
import nameless.classicraft.datagen.loot.ModLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

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
        PackOutput pack = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookup = event.getLookupProvider();
        generator.addProvider(event.includeClient(),
                new ModBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(),
                new ModItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(),
                new ModLanguageProvider(generator));
        generator.addProvider(event.includeClient(),
                new ModLanguageProviderZh(generator));
        generator.addProvider(event.includeClient(),
                new EmptyLangProvider(pack, ClassiCraftMod.MOD_ID));

        generator.addProvider(event.includeServer(),
                new ModLootTableProvider(pack));
        generator.addProvider(event.includeServer(),
                new ModGlobalModifierProvider(pack, ClassiCraftMod.MOD_ID));
        generator.addProvider(event.includeServer(),
                new DatapackBuiltinEntriesProvider(pack,
                        lookup, BUILDER,
                        Set.of(ClassiCraftMod.MOD_ID)));
        generator.addProvider(event.includeClient(),
                new ModBlockTagsProvider(pack, lookup, helper));
        generator.addProvider(event.includeServer(),
                new ModRecipeProvider(pack));
        generator.addProvider(event.includeClient(),
                new ModItemTagsProvider(pack, lookup,
                        new ModBlockTagsProvider(pack, lookup, helper), helper));
    }
}
