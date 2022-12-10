package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;
import java.util.function.Supplier;

public class ModConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURE =
            DeferredRegister.create(Registries.CONFIGURED_FEATURE, ClassiCraftMod.MOD_ID);

    public static final RegistryObject<ConfiguredFeature<?, ?>> REPLACE_ALL_CONFIG_FEATURE =
           register("replace_all",
                    () -> new ConfiguredFeature<>(ModFeatures.REPLACE_ALL_FEATURE.get(),
                            NoneFeatureConfiguration.INSTANCE));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CACTUS_BALL = register("cactus_ball",
            () -> new ConfiguredFeature<>(Feature.FLOWER,
                    new RandomPatchConfiguration(16, 3, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                            new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.CACTUS_BALL.get()))))));

    public static final RegistryObject<ConfiguredFeature<?, ?>> ROSE = register("rose",
            () -> new ConfiguredFeature<>(Feature.FLOWER,
                    new RandomPatchConfiguration(16, 3, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                            new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.ROSE.get()))))));

    public static final RegistryObject<ConfiguredFeature<?, ?>> LEVEL_SURFACE =
            register("level_surface", () -> new ConfiguredFeature<>(ModFeatures.LEVEL_SURFACE_FEATURE.get(), NoneFeatureConfiguration.INSTANCE));

    public static final RegistryObject<ConfiguredFeature<?, ?>> TWIG_SURFACE =
            register("twig_surface", () -> new ConfiguredFeature<>(ModFeatures.TWIG_SURFACE_FEATURE.get(), NoneFeatureConfiguration.INSTANCE));

    private static <T extends ConfiguredFeature<?, ?>> RegistryObject<T> register(String name, Supplier<T> feature) {
        return CONFIGURED_FEATURE.register(name.toLowerCase(Locale.ROOT), feature);
    }
}
