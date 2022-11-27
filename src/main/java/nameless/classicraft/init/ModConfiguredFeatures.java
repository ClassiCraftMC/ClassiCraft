package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.levelgen.ReplaceAllFeature;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURE_REGISTRY =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, ClassiCraftMod.MOD_ID);

    public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ReplaceAllFeature>> REPLACE_ALL_CONFIG_FEATURE =
            CONFIGURED_FEATURE_REGISTRY.register("replace_all",
                    () -> new ConfiguredFeature<>(ModFeatures.REPLACE_ALL_FEATURE.get(),
                            NoneFeatureConfiguration.INSTANCE));


}
