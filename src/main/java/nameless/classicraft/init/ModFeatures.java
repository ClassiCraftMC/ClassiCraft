package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.worldgen.QuickSandFeature;
import nameless.classicraft.worldgen.SaltStalactiteConfiguration;
import nameless.classicraft.worldgen.SaltStalactiteFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures {

    public static final DeferredRegister<Feature<?>>  FEATURES =
            DeferredRegister.create(ForgeRegistries.FEATURES, ClassiCraftMod.MODID);

    public static final RegistryObject<Feature<?>> SALT_STALACTITE_FEATRUE
            = FEATURES.register("salt_stalactite_feature",
            () -> new SaltStalactiteFeature(SaltStalactiteConfiguration.CODEC));

    public static final RegistryObject<Feature<?>> QUICKSAND_FEATRUE
            = FEATURES.register("quicksand_lake", () -> new QuickSandFeature(QuickSandFeature.Configuration.CODEC));
}
