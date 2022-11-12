package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftConfiguration;
import nameless.classicraft.ClassiCraftMod;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

public class ModPlacedFeatures {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, ClassiCraftMod.MODID);

    public static final RegistryObject<PlacedFeature> SALT_CAVE_PLACED =
            register("salt_cave_placed",
                    ModConfiguredFeatures.SALT_CAVE.getHolder().get(),
                    6, 30, ClassiCraftConfiguration.saltCaveGenerateChance.get());

    public static final RegistryObject<PlacedFeature> SALT_STALACTITE_CAVE =
            register("salt_stalactite_cave",
                    ModConfiguredFeatures.SALT_STALACTITE_CAVE.getHolder().get(),
                    6, 30, 60);

    private static RegistryObject<PlacedFeature> register(String name, Holder<ConfiguredFeature<?, ?>> feature, int aboveBottem, int absolute, int chance) {
        return register(name,
                () -> new PlacedFeature(feature,
                        List.of(HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(aboveBottem),
                                VerticalAnchor.absolute(absolute)), InSquarePlacement.spread(),
                                RarityFilter.onAverageOnceEvery(chance))));
    }

    private static <T extends PlacedFeature> RegistryObject<T> register(String name, Supplier<T> feature) {
        return  PLACED_FEATURES.register(name.toLowerCase(Locale.ROOT), feature);
    }
}
