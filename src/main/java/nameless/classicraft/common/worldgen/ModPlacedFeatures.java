package nameless.classicraft.common.worldgen;

import nameless.classicraft.ClassiCraft;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, ClassiCraft.MODID);

    public static final RegistryObject<PlacedFeature> SALT_CAVE_PLACED =
            PLACED_FEATURES.register("salt_cave_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.SALT_CAVE.getHolder().get(),
                    List.of(HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6),
                            VerticalAnchor.absolute(30)), InSquarePlacement.spread(),
                            RarityFilter.onAverageOnceEvery(24))));
}
