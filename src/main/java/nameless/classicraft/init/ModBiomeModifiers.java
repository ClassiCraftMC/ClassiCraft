package nameless.classicraft.init;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.levelgen.ReplaceAllFeatureModifier;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBiomeModifiers {

    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, ClassiCraftMod.MOD_ID);

    public static final RegistryObject<Codec<ReplaceAllFeatureModifier>> REPLACE_ALL_CODEC =
            BIOME_MODIFIER_SERIALIZERS.register("replace_all",
                    () -> RecordCodecBuilder.create(builder ->
                            builder.group(PlacedFeature.CODEC.fieldOf("feature")
                                    .forGetter(ReplaceAllFeatureModifier::feature))
                                    .apply(builder, ReplaceAllFeatureModifier::new)));
}
