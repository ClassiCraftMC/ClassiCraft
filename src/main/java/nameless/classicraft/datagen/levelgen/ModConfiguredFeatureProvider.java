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
package nameless.classicraft.datagen.levelgen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ModConfiguredFeatureProvider {

    public static final ResourceKey<ConfiguredFeature<?,?>> CACTUS_BALL =
            ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(ClassiCraftMod.MOD_ID,
                    "cactus_ball"));
    public static final ResourceKey<ConfiguredFeature<?,?>> ROSE =
            ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(ClassiCraftMod.MOD_ID,
                    "rose"));

    public static final ResourceKey<ConfiguredFeature<?,?>> LEVEL_SURFACE =
            ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(ClassiCraftMod.MOD_ID,
                    "level_surface"));
    public static final ResourceKey<ConfiguredFeature<?,?>> TWIG_SURFACE =
            ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(ClassiCraftMod.MOD_ID,
                    "twig_surface"));
    public static final ResourceKey<ConfiguredFeature<?,?>> REPLACE_ALL =
            ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(ClassiCraftMod.MOD_ID,
                    "replace_all"));

    public static void configuredFeatures(BootstapContext<ConfiguredFeature<?, ?>> context) {
        context.register(CACTUS_BALL, new ConfiguredFeature<>(Feature.FLOWER,
                new RandomPatchConfiguration(16, 3, 2,
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(
                                BlockStateProvider.simple(ModBlocks.CACTUS_BALL.get()))))));
        context.register(ROSE, new ConfiguredFeature<>(Feature.FLOWER,
                new RandomPatchConfiguration(16, 3, 2,
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBlocks.ROSE.get()))))));
        context.register(LEVEL_SURFACE,
                new ConfiguredFeature<>(ModFeatures.LEVEL_SURFACE_FEATURE.get(),
                        NoneFeatureConfiguration.INSTANCE));
        context.register(TWIG_SURFACE,
                new ConfiguredFeature<>(ModFeatures.TWIG_SURFACE_FEATURE.get(),
                        NoneFeatureConfiguration.INSTANCE));
        context.register(REPLACE_ALL,
                new ConfiguredFeature<>(ModFeatures.REPLACE_ALL_FEATURE.get(),
                        NoneFeatureConfiguration.INSTANCE));
    }

}
