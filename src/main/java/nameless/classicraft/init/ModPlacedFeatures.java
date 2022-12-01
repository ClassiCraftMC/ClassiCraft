package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

public class ModPlacedFeatures {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURE_REGISTRY =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, ClassiCraftMod.MOD_ID);

    public static final RegistryObject<PlacedFeature> REPLACE_ALL_PLACED_FEATURE =
            register("replace_all",
                    () -> new PlacedFeature(Holder.hackyErase(ModConfiguredFeatures.REPLACE_ALL_CONFIG_FEATURE.getHolder().get()),
                            new ArrayList<>()));

    public static final RegistryObject<PlacedFeature> CACTUS_BALL_PLACED = register("cactus_ball_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.CACTUS_BALL.getHolder().get(),
                    List.of(RarityFilter.onAverageOnceEvery(16),
                            InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    private static <T extends PlacedFeature> RegistryObject<T> register(String name, Supplier<T> feature) {
        return  PLACED_FEATURE_REGISTRY.register(name.toLowerCase(Locale.ROOT), feature);
    }
}
