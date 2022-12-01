package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.levelgen.ReplaceAllFeature;
import net.minecraft.core.Registry;
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

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURE_REGISTRY =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, ClassiCraftMod.MOD_ID);

    public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ReplaceAllFeature>> REPLACE_ALL_CONFIG_FEATURE =
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

    private static <T extends ConfiguredFeature<?, ?>> RegistryObject<T> register(String name, Supplier<T> feature) {
        return  CONFIGURED_FEATURE_REGISTRY.register(name.toLowerCase(Locale.ROOT), feature);
    }
}
