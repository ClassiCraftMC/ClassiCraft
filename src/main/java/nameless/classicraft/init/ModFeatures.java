package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.levelgen.LevelSurfaceFeature;
import nameless.classicraft.levelgen.ReplaceAllFeature;
import nameless.classicraft.levelgen.TwigSurfaceFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author wdog5
 */
public class ModFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(ForgeRegistries.FEATURES, ClassiCraftMod.MOD_ID);

    public static final RegistryObject<ReplaceAllFeature> REPLACE_ALL_FEATURE =
            FEATURES.register("replace_all", () ->
                    new ReplaceAllFeature(NoneFeatureConfiguration.CODEC.stable()));

    public static final RegistryObject<LevelSurfaceFeature> LEVEL_SURFACE_FEATURE =
            FEATURES.register("level_surface", LevelSurfaceFeature::new);

    public static final RegistryObject<TwigSurfaceFeature> TWIG_SURFACE_FEATURE =
            FEATURES.register("twig_surface", TwigSurfaceFeature::new);
}
