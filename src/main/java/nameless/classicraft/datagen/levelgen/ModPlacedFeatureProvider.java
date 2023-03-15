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

import nameless.classicraft.util.Helpers;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.ArrayList;
import java.util.List;

public class ModPlacedFeatureProvider {

    public static final ResourceKey<PlacedFeature> CACTUS_BALL_PLACED =
            register("cactus_ball_placed");
    public static final ResourceKey<PlacedFeature> ROSE_PLACED =
            register("rose_placed");
    public static final ResourceKey<PlacedFeature> LEVEL_SURFACE_PLACED =
            register("level_surface_placed");
    public static final ResourceKey<PlacedFeature> TWIG_SURFACE_PLACED =
            register("twig_surface_placed");
    public static final ResourceKey<PlacedFeature> REPLACE_ALL_PLACED =
            register("replace_all_placed");
    public static final ResourceKey<PlacedFeature> SULFUR_ORE_OVER_WORLD_PLACED =
            register("sulfur_ore_over_world_placed");

    public static final ResourceKey<PlacedFeature> SULFUR_ORE_NETHER_PLACED =
           register("sulfur_ore_nether_placed");

    public static final ResourceKey<PlacedFeature> NITER_ORE_WITH_SANDSTONE_PLACED =
            register("niter_ore_with_sandstone_placed");

    public static final ResourceKey<PlacedFeature> NITER_ORE_WITH_RED_SANDSTONE_PLACED =
            register("niter_ore_with_red_sandstone_placed");

    public static final ResourceKey<PlacedFeature> NITER_ORE_WITH_SOUL_SANDSTONE_PLACED =
            register("niter_ore_with_soul_sandstone_placed");

    public static final ResourceKey<PlacedFeature> SOUL_SANDSTONE_PLACED =
            register("soul_sandstone_placed");

    public static final ResourceKey<PlacedFeature> SOUL_SAND_PLACED =
            register("soul_sand_placed");

    public static final ResourceKey<PlacedFeature> SOUL_SOIL_PLACED =
            register("soul_soil_placed");

    public static final ResourceKey<PlacedFeature> CATTAIL_PLACED =
            register("cattail_placed");
    public static final ResourceKey<PlacedFeature> REED_PLACED =
            register("reed_placed");

    public static void placedFeature(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holderGetter = context.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> cactus_ball_holder =
                holderGetter.getOrThrow(ModConfiguredFeatureProvider.CACTUS_BALL);
        Holder<ConfiguredFeature<?, ?>> rose_holder =
                holderGetter.getOrThrow(ModConfiguredFeatureProvider.ROSE);
        Holder<ConfiguredFeature<?, ?>> level_surface_holder =
                holderGetter.getOrThrow(ModConfiguredFeatureProvider.LEVEL_SURFACE);
        Holder<ConfiguredFeature<?, ?>> twig_surface_holder =
                holderGetter.getOrThrow(ModConfiguredFeatureProvider.TWIG_SURFACE);
        Holder<ConfiguredFeature<?, ?>> replace_all_holder =
                holderGetter.getOrThrow(ModConfiguredFeatureProvider.REPLACE_ALL);
        Holder<ConfiguredFeature<?, ?>> sulfur_ore_over_world_holder =
                holderGetter.getOrThrow(ModConfiguredFeatureProvider.SULFUR_ORE_OVER_WORLD);
        Holder<ConfiguredFeature<?, ?>> sulfur_ore_nether_holder =
                holderGetter.getOrThrow(ModConfiguredFeatureProvider.SULFUR_ORE_NETHER);
        Holder<ConfiguredFeature<?, ?>> niter_ore_with_sandstone_holder =
                holderGetter.getOrThrow(ModConfiguredFeatureProvider.NITER_ORE_WITH_SANDSTONE);
        Holder<ConfiguredFeature<?, ?>> niter_ore_with_soul_sandstone_holder =
                holderGetter.getOrThrow(ModConfiguredFeatureProvider.NITER_ORE_WITH_SOUL_SANDSTONE);
        Holder<ConfiguredFeature<?, ?>> niter_ore_with_red_sandstone_holder =
                holderGetter.getOrThrow(ModConfiguredFeatureProvider.NITER_ORE_WITH_RED_SANDSTONE);
        Holder<ConfiguredFeature<?, ?>> soul_sandstone_holder =
                holderGetter.getOrThrow(ModConfiguredFeatureProvider.SOUL_SANDSTONE);
        Holder<ConfiguredFeature<?, ?>> soul_sand_holder =
                holderGetter.getOrThrow(ModConfiguredFeatureProvider.SOUL_SAND);
        Holder<ConfiguredFeature<?, ?>> soul_soil_holder =
                holderGetter.getOrThrow(ModConfiguredFeatureProvider.SOUL_SOIL);
        Holder<ConfiguredFeature<?, ?>> cattail_holder =
                holderGetter.getOrThrow(ModConfiguredFeatureProvider.CATTAIL);
        Holder<ConfiguredFeature<?, ?>> reed_holder =
                holderGetter.getOrThrow(ModConfiguredFeatureProvider.REED);
        context.register(CACTUS_BALL_PLACED, new PlacedFeature(cactus_ball_holder,
                List.of(RarityFilter.onAverageOnceEvery(16),
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
        context.register(ROSE_PLACED, new PlacedFeature(rose_holder,
                List.of(RarityFilter.onAverageOnceEvery(14),
                        InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
        context.register(LEVEL_SURFACE_PLACED, new PlacedFeature(level_surface_holder,
                List.of(CountPlacement.of(5), InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE)));
        context.register(TWIG_SURFACE_PLACED, new PlacedFeature(twig_surface_holder,
                List.of(CountPlacement.of(5), InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE)));
        context.register(REPLACE_ALL_PLACED, new PlacedFeature(replace_all_holder,
                new ArrayList<>()));
        context.register(SULFUR_ORE_OVER_WORLD_PLACED, new PlacedFeature(sulfur_ore_over_world_holder,
                List.of(CountPlacement.of(8), InSquarePlacement.spread(),
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-64),
                        VerticalAnchor.absolute(0)), BiomeFilter.biome())));
        context.register(SULFUR_ORE_NETHER_PLACED, new PlacedFeature(sulfur_ore_nether_holder,
                List.of(CountPlacement.of(8), InSquarePlacement.spread(),
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(32),
                                VerticalAnchor.absolute(96)), BiomeFilter.biome())));
        context.register(NITER_ORE_WITH_SANDSTONE_PLACED, new PlacedFeature(niter_ore_with_sandstone_holder,
                List.of(CountPlacement.of(8), InSquarePlacement.spread(),
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(50),
                                VerticalAnchor.absolute(100)), BiomeFilter.biome())));
        context.register(NITER_ORE_WITH_SOUL_SANDSTONE_PLACED, new PlacedFeature(niter_ore_with_soul_sandstone_holder,
                List.of(CountPlacement.of(8), InSquarePlacement.spread(),
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(32),
                                VerticalAnchor.absolute(110)), BiomeFilter.biome())));
        context.register(NITER_ORE_WITH_RED_SANDSTONE_PLACED, new PlacedFeature(niter_ore_with_red_sandstone_holder,
                List.of(CountPlacement.of(8), InSquarePlacement.spread(),
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(50),
                                VerticalAnchor.absolute(100)), BiomeFilter.biome())));
        context.register(SOUL_SANDSTONE_PLACED, new PlacedFeature(soul_sandstone_holder,
                List.of(CountPlacement.of(8), InSquarePlacement.spread(),
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(32),
                                VerticalAnchor.absolute(110)), BiomeFilter.biome())));
        context.register(SOUL_SAND_PLACED, new PlacedFeature(soul_sand_holder,
                List.of(CountPlacement.of(6), InSquarePlacement.spread(),
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(32),
                                VerticalAnchor.absolute(110)), BiomeFilter.biome())));
        context.register(SOUL_SOIL_PLACED, new PlacedFeature(soul_soil_holder,
                List.of(CountPlacement.of(6), InSquarePlacement.spread(),
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(32),
                                VerticalAnchor.absolute(110)), BiomeFilter.biome())));
        context.register(CATTAIL_PLACED, new PlacedFeature(cattail_holder,
                List.of(CountPlacement.of(20), InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome())));
        context.register(REED_PLACED, new PlacedFeature(reed_holder,
                List.of(CountPlacement.of(35), InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome())));
    }

    private static ResourceKey<PlacedFeature> register(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Helpers.identifier(name));
    }
}
