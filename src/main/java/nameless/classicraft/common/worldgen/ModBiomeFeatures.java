package nameless.classicraft.common.worldgen;

import nameless.classicraft.ClassiCraft;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBiomeFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, ClassiCraft.MODID);

    public static final RegistryObject<Feature<?>> WILD_RICE = FEATURES.register("wild_rice", WildRiceFeature::feature);
    public static final RegistryObject<Feature<?>> SALT_ORE = FEATURES.register("salt_ore", SaltOreFeature::feature);

}
