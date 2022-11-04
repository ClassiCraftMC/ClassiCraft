package nameless.classicraft.common.worldgen;

import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceKey;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.core.Holder;
import net.minecraft.world.level.material.Fluids;

import java.util.Set;

public class WildRiceFeature extends RandomPatchFeature {
    public static WildRiceFeature FEATURE = null;
    public static Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> CONFIGURED_FEATURE = null;

    public static Feature<?> feature() {
        FEATURE = new WildRiceFeature();
        CONFIGURED_FEATURE = FeatureUtils.register("classicraft:wild_rice", Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(20, 4, 0, PlacementUtils.inlinePlaced(Feature.BLOCK_COLUMN, BlockColumnConfiguration.simple(BiasedToBottomInt.of(2, 4), BlockStateProvider.simple(Blocks.SUGAR_CANE)), BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.wouldSurvive(Blocks.SUGAR_CANE.defaultBlockState(), BlockPos.ZERO), BlockPredicate.anyOf(BlockPredicate.matchesFluids(new BlockPos(1, -1, 0), Fluids.WATER, Fluids.FLOWING_WATER), BlockPredicate.matchesFluids(new BlockPos(-1, -1, 0), Fluids.WATER, Fluids.FLOWING_WATER), BlockPredicate.matchesFluids(new BlockPos(0, -1, 1), Fluids.WATER, Fluids.FLOWING_WATER), BlockPredicate.matchesFluids(new BlockPos(0, -1, -1), Fluids.WATER, Fluids.FLOWING_WATER)))))));
        return FEATURE;
    }

    private final Set<ResourceKey<Level>> generate_dimensions = Set.of(Level.OVERWORLD);

    public WildRiceFeature() {
        super(RandomPatchConfiguration.CODEC);
    }

    public boolean place(FeaturePlaceContext<RandomPatchConfiguration> context) {
        WorldGenLevel world = context.level();
        if (!generate_dimensions.contains(world.getLevel().dimension()))
            return false;
        return super.place(context);
    }
}
