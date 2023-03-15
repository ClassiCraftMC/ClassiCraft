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

import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModFeatures;
import nameless.classicraft.util.Helpers;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatureProvider {

    public static final ResourceKey<ConfiguredFeature<?,?>> CACTUS_BALL =
          register("cactus_ball");
    public static final ResourceKey<ConfiguredFeature<?,?>> ROSE =
            register("rose");
    public static final ResourceKey<ConfiguredFeature<?,?>> LEVEL_SURFACE =
            register("level_surface");
    public static final ResourceKey<ConfiguredFeature<?,?>> TWIG_SURFACE =
            register("twig_surface");
    public static final ResourceKey<ConfiguredFeature<?,?>> REPLACE_ALL =
            register("replace_all");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SULFUR_ORE_OVER_WORLD =
            register("sulfur_ore_over_world");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SULFUR_ORE_NETHER =
            register("sulfur_ore_over_nether");

    public static final ResourceKey<ConfiguredFeature<?, ?>> NITER_ORE_WITH_SANDSTONE =
            register("niter_ore_with_sandstone");

    public static final ResourceKey<ConfiguredFeature<?, ?>> NITER_ORE_WITH_RED_SANDSTONE =
            register("niter_ore_with_red_sandstone");

    public static final ResourceKey<ConfiguredFeature<?, ?>> NITER_ORE_WITH_SOUL_SANDSTONE =
            register("niter_ore_with_soul_sandstone");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SOUL_SANDSTONE =
            register("soul_sandstone");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SOUL_SAND =
            register("soul_sand");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SOUL_SOIL =
            register("soul_soil");

    public static final ResourceKey<ConfiguredFeature<?, ?>> NITER_ORE_VEIN =
            register("niter_ore_vein");

    public static final ResourceKey<ConfiguredFeature<?, ?>> CATTAIL =
            register("cattail");
    public static final ResourceKey<ConfiguredFeature<?, ?>> REED =
            register("reed");

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
        context.register(SULFUR_ORE_OVER_WORLD,
                new ConfiguredFeature<>(ModFeatures.SULFUR_ORE_FEATURE.get(),
                        new OreConfiguration(
                                List.of(OreConfiguration.target(
                                        new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                                ModBlocks.DEEPSLATE_SULFUR_ORE.get().defaultBlockState())), 16)));
        context.register(SULFUR_ORE_NETHER,
                new ConfiguredFeature<>(Feature.ORE,
                        new OreConfiguration(
                                List.of(OreConfiguration.target(
                                        new BlockMatchTest(Blocks.BLACKSTONE),
                                        ModBlocks.NETHER_SULFUR_ORE.get().defaultBlockState())), 16)));
        context.register(NITER_ORE_WITH_SANDSTONE,
                new ConfiguredFeature<>(ModFeatures.NITER_ORE_WITH_SANDSTONE.get(),
                        new OreConfiguration(
                                List.of(OreConfiguration.target(
                                        new BlockMatchTest(Blocks.SANDSTONE),
                                        ModBlocks.SANDSTONE_NITER_ORE.get().defaultBlockState())), 8)));
        context.register(NITER_ORE_WITH_RED_SANDSTONE,
                new ConfiguredFeature<>(ModFeatures.NITER_ORE_WITH_SANDSTONE.get(),
                        new OreConfiguration(
                                List.of(OreConfiguration.target(
                                        new BlockMatchTest(Blocks.RED_SANDSTONE),
                                        ModBlocks.RED_SANDSTONE_NITER_ORE.get().defaultBlockState())), 8)));
        FeatureUtils.register(context, SOUL_SANDSTONE, Feature.DISK,
                new DiskConfiguration(new RuleBasedBlockStateProvider(BlockStateProvider.simple(Blocks.SOUL_SAND),
                        List.of(new RuleBasedBlockStateProvider.
                                Rule(BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(),
                                Blocks.AIR), BlockStateProvider.simple(ModBlocks.SOUL_SANDSTONE.get())))),
                        BlockPredicate.matchesBlocks(Blocks.SOUL_SAND, Blocks.SOUL_SOIL),
                        UniformInt.of(4, 8), 2));
        FeatureUtils.register(context, NITER_ORE_WITH_SOUL_SANDSTONE, Feature.DISK,
                new DiskConfiguration(new RuleBasedBlockStateProvider(BlockStateProvider.simple(Blocks.SOUL_SAND),
                        List.of(new RuleBasedBlockStateProvider.
                                Rule(BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(),
                                Blocks.AIR), BlockStateProvider.simple(ModBlocks.SOUL_SANDSTONE_NITER_ORE.get())))),
                        BlockPredicate.matchesBlocks(ModBlocks.SOUL_SANDSTONE.get()),
                        UniformInt.of(2, 4), 2));
        context.register(SOUL_SAND,
                new ConfiguredFeature<>(Feature.ORE,
                        new OreConfiguration(
                                List.of(OreConfiguration.target(
                                        new BlockMatchTest(ModBlocks.SOUL_SANDSTONE.get()),
                                        Blocks.SOUL_SAND.defaultBlockState())), 8)));
        context.register(SOUL_SOIL,
                new ConfiguredFeature<>(Feature.ORE,
                        new OreConfiguration(
                                List.of(OreConfiguration.target(
                                        new BlockMatchTest(ModBlocks.SOUL_SANDSTONE_NITER_ORE.get()),
                                        Blocks.SOUL_SOIL.defaultBlockState())), 8)));
        context.register(NITER_ORE_VEIN,
                new ConfiguredFeature<>(Feature.NO_OP,
                        FeatureConfiguration.NONE));
        context.register(CATTAIL,
                new ConfiguredFeature<>(ModFeatures.CATTAIL_FEATURE.get(),
                        new ProbabilityFeatureConfiguration(0.5F)));
        context.register(REED,
                new ConfiguredFeature<>(ModFeatures.REED_FEATURE.get(),
                        new ProbabilityFeatureConfiguration(0.5F)));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> register(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Helpers.identifier(name));
    }
}
