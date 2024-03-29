/*
 * ClassiCraft - ClassiCraftMC
 * Copyright (C) 2018-2022.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
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
import java.util.Random;
import java.util.function.Supplier;

public class LevelSurfaceFeature extends Feature<NoneFeatureConfiguration> {
    static Random random = new Random();

    static Supplier<Block> randomPebble() {
        return () -> switch (random.nextInt(7)) {
            case 1 -> ModBlocks.ANDESITE_PEBBLE.get();
            case 2 -> ModBlocks.DIORITE_PEBBLE.get();
            case 3 -> ModBlocks.GRANITE_PEBBLE.get();
            case 4 -> ModBlocks.FLINT.get();
            case 5 -> ModBlocks.DEEPSLATE_PEBBLE.get();
            case 6 -> ModBlocks.BLACKSTONE_PEBBLE.get();
            default -> ModBlocks.COBBLESTONE_PEBBLE.get();
        };
    }

    private static final Supplier<Map<Block, Supplier<? extends Block>>> LEVEL_SURFACE_PLACE_LOOKUP = Suppliers.memoize(() -> new ImmutableMap.Builder<Block, Supplier<? extends Block>>()
            .put(Blocks.STONE, randomPebble())
            .put(Blocks.ANDESITE, randomPebble())
            .put(Blocks.DIORITE, randomPebble())
            .put(Blocks.GRANITE, randomPebble())

            .put(Blocks.SANDSTONE, ModBlocks.SANDSTONE_PEBBLE)
            .put(Blocks.RED_SANDSTONE, ModBlocks.RED_SANDSTONE_PEBBLE)
            .put(Blocks.TERRACOTTA, ModBlocks.RED_SANDSTONE_PEBBLE)
            .put(Blocks.SAND, ModBlocks.SANDSTONE_PEBBLE)
            .put(Blocks.RED_SAND, ModBlocks.RED_SANDSTONE_PEBBLE)
            .build()
    );

    public LevelSurfaceFeature() {
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
                if (LEVEL_SURFACE_PLACE_LOOKUP.get().containsKey(stoneState.getBlock())) {
                    final Block placeBlock = LEVEL_SURFACE_PLACE_LOOKUP.get().get(stoneState.getBlock()).get();
                    level.setBlock(pos, placeBlock.defaultBlockState(), 3);
                    return true;
                }
            }
        }
        return false;
    }
}
