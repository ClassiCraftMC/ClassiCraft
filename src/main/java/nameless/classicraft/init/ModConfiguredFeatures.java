package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

public class ModConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, ClassiCraftMod.MODID);

    public static final RegistryObject<ConfiguredFeature<?, ?>> SALT_CAVE =
            register("salt_cave",
            () -> new ConfiguredFeature<>(Feature.GEODE,
                    new GeodeConfiguration
                            (new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                                    BlockStateProvider.simple(ModBlocks.SALT_ROCK_BLOCK.get()),
                                    BlockStateProvider.simple(Blocks.BUDDING_AMETHYST),
                                    BlockStateProvider.simple(ModBlocks.SALT_ROCK_BLOCK.get()),
                                    BlockStateProvider.simple(ModBlocks.SALT_ROCK_BLOCK.get()),
                                    List.of(Blocks.SMALL_AMETHYST_BUD.defaultBlockState(),
                                            Blocks.DRIPSTONE_BLOCK.defaultBlockState(),
                                            Blocks.LARGE_AMETHYST_BUD.defaultBlockState(),
                                            Blocks.CALCITE.defaultBlockState()),
                                    BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS),
                                    new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                                    new GeodeCrackSettings(0.95D, 2.0D, 2), 0.35D, 0.083D, true,
                                    UniformInt.of(4, 6),
                                    UniformInt.of(3, 4),
                                    UniformInt.of(1, 2), -16, 16, 0.05D, 1)));

    private static <T extends ConfiguredFeature<?, ?>> RegistryObject<T> register(String name, Supplier<T> feature) {
        return  CONFIGURED_FEATURES.register(name.toLowerCase(Locale.ROOT), feature);
    }
}
