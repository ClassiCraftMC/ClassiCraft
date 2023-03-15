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

import nameless.classicraft.block.CattailBlock;
import nameless.classicraft.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraft.world.level.material.FluidState;

public class CattailFeature extends Feature<ProbabilityFeatureConfiguration> {

    public CattailFeature() {
        super(ProbabilityFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<ProbabilityFeatureConfiguration> context) {
        boolean placedCattail = false;
        RandomSource randomSource = context.random();
        WorldGenLevel worldGenLevel = context.level();
        BlockPos pos = context.origin();
        ProbabilityFeatureConfiguration probabilityfeatureconfiguration = context.config();
        int xOffset = randomSource.nextInt(6) - randomSource.nextInt(6);
        int zOffset = randomSource.nextInt(6) - randomSource.nextInt(6);
        int heightInWorld = worldGenLevel.getHeight(Heightmap.Types.OCEAN_FLOOR, pos.getX() + xOffset, pos.getZ() + zOffset);
        BlockPos posAtTarget = new BlockPos(pos.getX() + xOffset, heightInWorld, pos.getZ() + zOffset);

        if (worldGenLevel.getBlockState(posAtTarget).is(Blocks.WATER)) {
            boolean probabilityCheck = randomSource.nextDouble() < (double)probabilityfeatureconfiguration.probability;
            if (isValidCattailPosition(worldGenLevel, posAtTarget) && probabilityCheck) {
                BlockState state = ModBlocks.CATTAIL.get().defaultBlockState();
                boolean isTallCattail = worldGenLevel.getBlockState(posAtTarget.above(2)).is(Blocks.AIR) && worldGenLevel.getBlockState(posAtTarget.above()).is(Blocks.WATER);
                if (isTallCattail) {
                    worldGenLevel.setBlock(posAtTarget, state, 2);
                    worldGenLevel.setBlock(posAtTarget.above(), state.setValue(CattailBlock.LOWER, false), 2);
                    worldGenLevel.setBlock(posAtTarget.above(2), state.setValue(BlockStateProperties.WATERLOGGED, false), 2);
                    worldGenLevel.setBlock(posAtTarget.above(3), state.setValue(BlockStateProperties.WATERLOGGED, false).setValue(CattailBlock.LOWER, false), 2);
                } else {
                    worldGenLevel.setBlock(posAtTarget, state, 2);
                    worldGenLevel.setBlock(posAtTarget.above(), state.setValue(BlockStateProperties.WATERLOGGED, false), 2);
                    worldGenLevel.setBlock(posAtTarget.above(2), state.setValue(BlockStateProperties.WATERLOGGED, false).setValue(CattailBlock.LOWER, false), 2);
                }
                placedCattail = true;
            }
        }
        return placedCattail;
    }

    private boolean isValidCattailPosition(WorldGenLevel level, BlockPos pos) {
        FluidState fluidstate = level.getFluidState(pos);
        BlockState blockStateBelow1 = level.getBlockState(pos.below());
        BlockState blockStateAbove1 = level.getBlockState(pos.above());
        BlockState blockStateAbove2 = level.getBlockState(pos.above(2));
        BlockState blockStateAbove3 = level.getBlockState(pos.above(3));

        if(fluidstate.is(FluidTags.WATER) && fluidstate.getAmount() == FluidState.AMOUNT_FULL) {
            return (!blockStateBelow1.is(ModBlocks.CATTAIL.get()) && blockStateAbove1.is(Blocks.WATER) && blockStateAbove2.is(Blocks.AIR) && blockStateAbove3.is(Blocks.AIR)) ||
                    (!blockStateBelow1.is(ModBlocks.CATTAIL.get()) && blockStateAbove1.is(Blocks.AIR) && blockStateAbove2.is(Blocks.AIR));
        }
        return false;
    }
}