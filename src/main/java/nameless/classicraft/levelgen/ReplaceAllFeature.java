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

import com.mojang.serialization.Codec;
import nameless.classicraft.block.*;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModConfigurations;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class ReplaceAllFeature extends Feature<NoneFeatureConfiguration> {

    public ReplaceAllFeature(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(NoneFeatureConfiguration pConfig, WorldGenLevel pLevel, ChunkGenerator pChunkGenerator, RandomSource pRandom, BlockPos pOrigin) {
        return super.place(pConfig, pLevel, pChunkGenerator, pRandom, pOrigin);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        if (!ModConfigurations.replaceVanillaBlock.get()) return false;
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        ChunkAccess chunk = level.getChunk(origin);

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < chunk.getHeight(); y++) {
                    BlockPos pos = new BlockPos(origin.getX() + x, origin.getY() + y, origin.getZ() + z);
                    BlockState state = chunk.getBlockState(pos);
                    if (state.is(Blocks.TORCH)) {
                        chunk.setBlockState(pos, ModBlocks.REAL_TORCH.get().defaultBlockState()
                                        .setValue(AbstractLightBlock.getLitState(), true)
                                        .setValue(RealTorchBlock.getBurnTime(),
                                                RealTorchBlock.getInitialBurnTime()),
                                false);
                        level.scheduleTick(pos, state.getBlock(), 1200);
                    } else if (state.is(Blocks.WALL_TORCH)) {
                        chunk.setBlockState(pos, ModBlocks.REAL_WALL_TORCH.get().defaultBlockState()
                                        .setValue(AbstractLightBlock.getLitState(), true)
                                        .setValue(RealWallTorchBlock.getBurnTime(), RealWallTorchBlock.getInitialBurnTime())
                                        .setValue(RealWallTorchBlock.FACING,
                                                state.getValue(RealWallTorchBlock.FACING)),
                                false);
                        level.scheduleTick(pos, state.getBlock(), 1200);
                    } else if (state.is(Blocks.SOUL_TORCH)) {
                        chunk.setBlockState(pos, ModBlocks.REAL_SOUL_TORCH.get().defaultBlockState()
                                .setValue(AbstractLightBlock.getLitState(), true)
                                .setValue(RealSoulTorchBlock.getBurnTime(),
                                        RealSoulTorchBlock.getInitialBurnTime()), true);
                        level.scheduleTick(pos, state.getBlock(), 1200);
                    } else if (state.is(Blocks.SOUL_WALL_TORCH)) {
                        chunk.setBlockState(pos, ModBlocks.REAL_SOUL_WALL_TORCH.get().defaultBlockState()
                                        .setValue(AbstractLightBlock.getLitState(), true)
                                        .setValue(RealSoulWallTorchBlock.getBurnTime(), RealSoulWallTorchBlock.getInitialBurnTime())
                                        .setValue(RealSoulWallTorchBlock.FACING,
                                                state.getValue(RealSoulWallTorchBlock.FACING)),
                                false);
                        level.scheduleTick(pos, state.getBlock(), 1200);
                    }
                }
            }
        }
        return true;
    }
}
