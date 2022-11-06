package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.worldgen.SaltOreFeature;
import nameless.classicraft.worldgen.WildRiceFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;
import java.util.function.Supplier;

public class ModBiomeFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(ForgeRegistries.FEATURES, ClassiCraftMod.MODID);

    public static final RegistryObject<Feature<?>> WILD_RICE =
            register("wild_rice", WildRiceFeature::feature);
    public static final RegistryObject<Feature<?>> SALT_ORE =
            register("salt_ore", SaltOreFeature::feature);

    private static <T extends Feature<?>> RegistryObject<T> register(String name, Supplier<T> feature) {
        return  FEATURES.register(name.toLowerCase(Locale.ROOT), feature);
    }
}
