package nameless.classicraft.levelgen;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Map;
import java.util.function.Supplier;

public class TwigSurfaceFeature extends Feature<NoneFeatureConfiguration> {

    private static final Supplier<Map<Block, Supplier<? extends Block>>> TWIGS_SURFACE_PLACE_LOOKUP =
            Suppliers.memoize(() -> new ImmutableMap.Builder<Block, Supplier<? extends Block>>()
            .put(Blocks.STONE, ModBlocks.TWIGS)
            .put(Blocks.ANDESITE, ModBlocks.TWIGS)
            .put(Blocks.DIORITE, ModBlocks.TWIGS)
            .put(Blocks.GRANITE, ModBlocks.TWIGS)
            .put(Blocks.DIRT, ModBlocks.TWIGS)
            .build()
    );

    public TwigSurfaceFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        final WorldGenLevel level = context.level();
        final BlockPos pos = context.origin();

        final BlockState stateAt = level.getBlockState(pos);
        final BlockState stateDown = level.getBlockState(pos.below());
        if (stateAt.isAir() && stateDown.is(ModTags.Blocks.LEVEL_SURFACE_PLACEABLE_ON)) {
            for (int y = 1; y <= 8; y++) {
                final BlockPos stonePos = pos.below(y);
                final BlockState stoneState = level.getBlockState(stonePos);
                if (TWIGS_SURFACE_PLACE_LOOKUP.get().containsKey(stoneState.getBlock())) {
                    final Block placeBlock = TWIGS_SURFACE_PLACE_LOOKUP.get().get(stoneState.getBlock()).get();
                    level.setBlock(pos, placeBlock.defaultBlockState(), 3);
                    return true;
                }
            }
        }
        return false;
    }
}
