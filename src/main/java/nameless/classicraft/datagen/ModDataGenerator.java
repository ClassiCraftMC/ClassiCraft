/*
 * ClassiCraft - ClassiCraftMC
 * Copyright (C) 2018-2022.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.datagen.lang.EmptyLangProvider;
import nameless.classicraft.datagen.lang.ModLanguageProvider;
import nameless.classicraft.datagen.lang.ModLanguageProviderZh;
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
        generator.addProvider(event.includeServer(),
                new ModBlockTagsProvider(pack, lookup, helper));
        generator.addProvider(event.includeServer(),
                new ModRecipeProvider(pack));
        generator.addProvider(event.includeServer(),
                new ModItemTagsProvider(pack, lookup,
                        new ModBlockTagsProvider(pack, lookup, helper), helper));
        generator.addProvider(event.includeClient(),
                new ModSoundDefinitions(pack, helper));
    }
}
