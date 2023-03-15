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

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

public class NiterOreWithSandStoneFeature extends OreFeature {

    public NiterOreWithSandStoneFeature() {
        super(OreConfiguration.CODEC);
    }

    @Override
    public boolean place(OreConfiguration pConfig, WorldGenLevel pLevel, ChunkGenerator pChunkGenerator, RandomSource pRandom, BlockPos pOrigin) {
        BlockPos eastPos = pOrigin.east();
        BlockPos westPos = pOrigin.west();
        BlockPos northPos = pOrigin.north();
        BlockPos southPos = pOrigin.south();
        BlockPos upperPos = pOrigin.above();
        BlockPos belowPos = pOrigin.below();
        if (pLevel.getBlockState(eastPos).is(Blocks.WATER)
                || pLevel.getBlockState(westPos).is(Blocks.WATER)
                || pLevel.getBlockState(northPos).is(Blocks.WATER)
                || pLevel.getBlockState(southPos).is(Blocks.WATER)
                || pLevel.getBlockState(upperPos).is(Blocks.WATER)
                || pLevel.getBlockState(belowPos).is(Blocks.WATER)) {
            return false;
        }
        if (pLevel.getBiome(pOrigin).is(Biomes.FLOWER_FOREST)) {
            return false;
        }
        return super.place(pConfig, pLevel, pChunkGenerator, pRandom, pOrigin);
    }
}
