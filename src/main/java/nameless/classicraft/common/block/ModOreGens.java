package nameless.classicraft.common.block;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModOreGens {

    public static final List<OreConfiguration.TargetBlockState> ORE_SALT_TARGET_LIST =
            List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES,
                    ModBlocks.SALT_ORE.get().defaultBlockState()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_SALT =
            FeatureUtils.register("ore_salt",
                    Feature.ORE, new OreConfiguration(ORE_SALT_TARGET_LIST, 17));

    public static final Holder<PlacedFeature> ORE_SALT_PLACED =
            PlacementUtils.register("ore_salt_placed",
                    ORE_SALT,
                    commonOrePlacement(20,
                            HeightRangePlacement
                                    .triangle(VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(192))));

    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int pCount, PlacementModifier pHeightRange) {
        return orePlacement(CountPlacement.of(pCount), pHeightRange);
    }

}
