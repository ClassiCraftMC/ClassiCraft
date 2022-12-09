package nameless.classicraft.init;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModExtraRegistries {

    public static final ResourceKey<Registry<ConfiguredFeature<?, ?>>> CONFIGURED_FEATURE_REGISTRY =
            createRegistryKey("worldgen/configured_feature");
    public static final ResourceKey<Registry<PlacedFeature>> PLACED_FEATURE_REGISTRY =
            createRegistryKey("worldgen/placed_feature");

    private static <T> ResourceKey<Registry<T>> createRegistryKey(String pRegistryName) {
        return ResourceKey.createRegistryKey(new ResourceLocation(pRegistryName));
    }
}
