package nameless.classicraft.levelgen;

import com.mojang.serialization.Codec;
import nameless.classicraft.init.ModBiomeModifiers;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public record ReplaceAllFeatureModifier(Holder<PlacedFeature> feature) implements BiomeModifier
{
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder)
    {
        if (phase == Phase.AFTER_EVERYTHING) {
            builder.getGenerationSettings().addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, feature);
        }
    }

    public Codec<? extends BiomeModifier> codec()
    {
        return ModBiomeModifiers.REPLACE_ALL_CODEC.get();
    }
}