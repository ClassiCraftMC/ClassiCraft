package nameless.classicraft.worldgen;

import nameless.classicraft.ClassiCraftConfiguration;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.block.WildRiceBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class WildRiceFeature extends RandomPatchFeature {

    public static WildRiceFeature FEATURE = null;
    public static Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> CONFIGURED_FEATURE = null;
    public static Holder<PlacedFeature> PLACED_FEATURE = null;

    public WildRiceFeature() {
        super(RandomPatchConfiguration.CODEC);
    }

    public static Feature<?> feature() {
        FEATURE = new WildRiceFeature();
        CONFIGURED_FEATURE = FeatureUtils.register("classicraft:wild_rice", FEATURE, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.WILD_RICE.get())), List.of(Blocks.DIRT), 20));
        PLACED_FEATURE = PlacementUtils.register("classicraft:wild_rice", CONFIGURED_FEATURE, List.of(CountPlacement.of(8),
                RarityFilter.onAverageOnceEvery(ClassiCraftConfiguration.wildRiceGenerateChance.get()), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        return FEATURE;
    }

    @Override
    public boolean place(FeaturePlaceContext<RandomPatchConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomPatchConfiguration config = context.config();
        RandomSource rand = context.random();

        BlockPos blockpos = level.getHeightmapPos(Heightmap.Types.OCEAN_FLOOR_WG, origin);

        int i = 0;
        BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();

        for (int j = 0; j < config.tries(); ++j) {
            blockpos$mutable.set(blockpos).move(
                    rand.nextInt(config.xzSpread() + 1) - rand.nextInt(config.xzSpread() + 1),
                    rand.nextInt(config.ySpread() + 1) - rand.nextInt(config.ySpread() + 1),
                    rand.nextInt(config.xzSpread() + 1) - rand.nextInt(config.xzSpread() + 1));

            if (level.getBlockState(blockpos$mutable).getBlock() == Blocks.WATER && level.getBlockState(blockpos$mutable.above()).getBlock() == Blocks.AIR) {
                BlockState bottomRiceState = ModBlocks.WILD_RICE.get().defaultBlockState().setValue(WildRiceBlock.HALF, DoubleBlockHalf.LOWER);
                if (bottomRiceState.canSurvive(level, blockpos$mutable)) {
                    DoublePlantBlock.placeAt(level, bottomRiceState, blockpos$mutable, 2);
                    ++i;
                }
            }
        }

        return i > 0;
    }
}
