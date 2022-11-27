package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.levelgen.ReplaceAllFeature;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;

public class ModPlacedFeatures {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURE_REGISTRY =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, ClassiCraftMod.MOD_ID);

    public static final RegistryObject<PlacedFeature> REPLACE_ALL_PLACED_FEATURE =
            PLACED_FEATURE_REGISTRY.register("replace_all",
                    () -> new PlacedFeature(Holder.hackyErase(ModConfiguredFeatures.REPLACE_ALL_CONFIG_FEATURE.getHolder().get()),
                            new ArrayList<>()));
}
