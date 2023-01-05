package nameless.classicraft.datagen.levelgen;

import nameless.classicraft.ClassiCraftMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wdog5
 */
public class ModPlacedFeatureProvider {

    public static final ResourceKey<PlacedFeature> CACTUS_BALL_PLACED =
            ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(ClassiCraftMod.MOD_ID,
                    "cactus_ball_placed"));
    public static final ResourceKey<PlacedFeature> ROSE_PLACED =
            ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(ClassiCraftMod.MOD_ID,
                    "rose_placed"));
    public static final ResourceKey<PlacedFeature> LEVEL_SURFACE_PLACED =
            ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(ClassiCraftMod.MOD_ID,
                    "level_surface_placed"));
    public static final ResourceKey<PlacedFeature> TWIG_SURFACE_PLACED =
            ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(ClassiCraftMod.MOD_ID,
                    "twig_surface_placed"));
    public static final ResourceKey<PlacedFeature> REPLACE_ALL_PLACED =
            ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(ClassiCraftMod.MOD_ID,
                    "replace_all_placed"));

    public static void placedFeature(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holderGetter = context.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> cactus_ball_holder = holderGetter.getOrThrow(ModConfiguredFeatureProvider.CACTUS_BALL);
        Holder<ConfiguredFeature<?, ?>> rose_holder = holderGetter.getOrThrow(ModConfiguredFeatureProvider.ROSE);
        Holder<ConfiguredFeature<?, ?>> level_surface_holder = holderGetter.getOrThrow(ModConfiguredFeatureProvider.LEVEL_SURFACE);
        Holder<ConfiguredFeature<?, ?>> twig_surface_holder = holderGetter.getOrThrow(ModConfiguredFeatureProvider.TWIG_SURFACE);
        Holder<ConfiguredFeature<?, ?>> replace_all_holder = holderGetter.getOrThrow(ModConfiguredFeatureProvider.REPLACE_ALL);
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
    }
}
