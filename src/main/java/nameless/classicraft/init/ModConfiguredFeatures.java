package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.worldgen.SaltStalactiteConfiguration;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ClampedNormalFloat;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DripstoneClusterConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.PointedDripstoneConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.EnvironmentScanPlacement;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

public class ModConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, ClassiCraftMod.MODID);

    public static final RegistryObject<ConfiguredFeature<?, ?>> SALT_STALACTITE_CAVE =
            register("salt_stalactite_cave",
                    () -> new ConfiguredFeature<>( Feature.SIMPLE_RANDOM_SELECTOR,
                            new SimpleRandomFeatureConfiguration(HolderSet.direct(PlacementUtils.inlinePlaced(Feature.POINTED_DRIPSTONE,
                                    new PointedDripstoneConfiguration(0.2F, 0.7F, 0.5F, 0.5F),
                                    EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(),
                                            BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                                    RandomOffsetPlacement.vertical(ConstantInt.of(1))),
                                    PlacementUtils.inlinePlaced(Feature.POINTED_DRIPSTONE,
                                            new PointedDripstoneConfiguration(0.2F, 0.7F, 0.5F, 0.5F),
                                            EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(),
                                                    BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                                            RandomOffsetPlacement.vertical(ConstantInt.of(-1)))))));

    public static final RegistryObject<ConfiguredFeature<?, ?>> SALT_CAVE =
            register("salt_cave",
            () -> new ConfiguredFeature<>(Feature.GEODE,
                    new GeodeConfiguration
                            (new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                                    BlockStateProvider.simple(ModBlocks.SALT_ROCK_BLOCK.get()),
                                    BlockStateProvider.simple(ModBlocks.SALT_ROCK_BLOCK.get()),
                                    BlockStateProvider.simple(ModBlocks.SALT_ROCK_BLOCK.get()),
                                    BlockStateProvider.simple(ModBlocks.SALT_ROCK_BLOCK.get()),
                                    List.of(ModBlocks.SMALL_SALT_BUD.get().defaultBlockState(),
                                            ModBlocks.MEDIUM_SALT_BUD.get().defaultBlockState(),
                                            ModBlocks.LARGE_SALT_BUD.get().defaultBlockState(),
                                            ModBlocks.SALT_CLUSTER.get().defaultBlockState()),
                                    BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS),
                                    new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                                    new GeodeCrackSettings(0.95D, 2.0D, 2), 0.35D, 0.083D, true,
                                    UniformInt.of(5, 10),
                                    UniformInt.of(5, 10),
                                    UniformInt.of(5, 10), -16, 16, 0.05D, 1)));

    private static <T extends ConfiguredFeature<?, ?>> RegistryObject<T> register(String name, Supplier<T> feature) {
        return  CONFIGURED_FEATURES.register(name.toLowerCase(Locale.ROOT), feature);
    }
}
