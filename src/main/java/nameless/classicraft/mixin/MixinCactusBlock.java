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
package nameless.classicraft.mixin;

import nameless.classicraft.block.CactusFruitBlock;
import nameless.classicraft.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CactusBlock.class)
public class MixinCactusBlock extends Block implements BonemealableBlock {

    public MixinCactusBlock(Properties pProperties) {
        super(pProperties);
    }

    @Inject(method = "randomTick", at = @At("HEAD"))
    private void randomTickCC(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom, CallbackInfo ci) {
        BlockPos eastUpperPos = pPos.east().north();
        BlockPos eastUpperPosBelow = eastUpperPos.below();
        BlockPos westUpperPos = pPos.west().north();
        BlockPos westUpperPosPosBelow = westUpperPos.below();
        BlockPos eastDownPos = pPos.east().south();
        BlockPos eastDownPosBelow = eastDownPos.below();
        BlockPos westDownPos = pPos.west().south();
        BlockPos westDownPosPosBelow = westDownPos.below();
        if (pLevel.getBlockState(pPos.below()).getBlock() instanceof SandBlock) {
            if (pLevel.getBlockState(pPos.above(2)).getBlock() instanceof CactusBlock) {
                if (!pLevel.getBlockState(pPos.above(3)).is(ModBlocks.CACTUS_FRUIT.get())) {
                    BlockPos eastPos = pPos.east();
                    BlockPos westPos = pPos.west();
                    BlockPos northPos = pPos.north();
                    BlockPos southPos = pPos.south();
                    if (pLevel.isRainingAt(pPos.above(4))
                            || pLevel.getBlockState(eastPos).is(Blocks.WATER)
                            || pLevel.getBlockState(westPos).is(Blocks.WATER)
                            || pLevel.getBlockState(northPos).is(Blocks.WATER)
                            || pLevel.getBlockState(southPos).is(Blocks.WATER)) {
                        if (!pLevel.isAreaLoaded(pPos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
                        if (pLevel.getRawBrightness(pPos, 0) >= 9) {
                            if (Math.random() <= 0.25) {
                                pLevel.setBlock(pPos.above(3),
                                        ModBlocks.CACTUS_FRUIT.get().defaultBlockState(), 11);
                                pLevel.neighborChanged(pLevel.getBlockState(pPos.above(2)),
                                        pPos.above(2), this, pPos, false);
                            }
                        }
                    }
                }
                BlockState fruitState = pLevel.getBlockState(pPos.above(3));
                if (fruitState.is(ModBlocks.CACTUS_FRUIT.get())) {
                    if (((CactusFruitBlock) fruitState.getBlock()).isMaxAge(fruitState)) {
                        if (Math.random() <= 0.25) {
                            if (pLevel.getBlockState(eastUpperPos).isAir()
                                    && pLevel.getBlockState(eastUpperPos.east()).isAir()
                                    && pLevel.getBlockState(eastUpperPos.west()).isAir()
                                    && pLevel.getBlockState(eastUpperPos.north()).isAir()
                                    && pLevel.getBlockState(eastUpperPos.south()).isAir()
                                    && pLevel.getBlockState(eastUpperPosBelow).getBlock() instanceof SandBlock) {
                                pLevel.setBlockAndUpdate(eastUpperPos, ModBlocks.CACTUS_BALL.get().defaultBlockState());
                                pLevel.neighborChanged(ModBlocks.CACTUS_BALL.get().defaultBlockState(),
                                        eastUpperPosBelow, ((CactusBlock) (Object) this), pPos, false);
                            }
                        }
                        if (Math.random() > 0.25
                                && Math.random() <= 0.5) {
                            if (pLevel.getBlockState(westUpperPos).isAir()
                                    && pLevel.getBlockState(westUpperPos.east()).isAir()
                                    && pLevel.getBlockState(westUpperPos.west()).isAir()
                                    && pLevel.getBlockState(westUpperPos.north()).isAir()
                                    && pLevel.getBlockState(westUpperPos.south()).isAir()
                                    && pLevel.getBlockState(westUpperPosPosBelow).getBlock() instanceof SandBlock) {
                                pLevel.setBlockAndUpdate(westUpperPos, ModBlocks.CACTUS_BALL.get().defaultBlockState());
                                pLevel.neighborChanged(ModBlocks.CACTUS_BALL.get().defaultBlockState(),
                                        westUpperPosPosBelow, ((CactusBlock) (Object) this), pPos, false);
                            }
                        }
                        if (Math.random() > 0.5
                                && Math.random() <= 0.75) {
                            if (pLevel.getBlockState(eastDownPos).isAir()
                                    && pLevel.getBlockState(eastDownPos.east()).isAir()
                                    && pLevel.getBlockState(eastDownPos.west()).isAir()
                                    && pLevel.getBlockState(eastDownPos.north()).isAir()
                                    && pLevel.getBlockState(eastDownPos.south()).isAir()
                                    && pLevel.getBlockState(eastDownPosBelow).getBlock() instanceof SandBlock) {
                                pLevel.setBlockAndUpdate(eastDownPos, ModBlocks.CACTUS_BALL.get().defaultBlockState());
                                pLevel.neighborChanged(ModBlocks.CACTUS_BALL.get().defaultBlockState(),
                                        eastDownPos, ((CactusBlock) (Object) this), pPos, false);
                            }
                        }
                        if (Math.random() > 0.75) {
                            if (pLevel.getBlockState(westDownPos).isAir()
                                    && pLevel.getBlockState(westDownPos.east()).isAir()
                                    && pLevel.getBlockState(westDownPos.west()).isAir()
                                    && pLevel.getBlockState(westDownPos.north()).isAir()
                                    && pLevel.getBlockState(westDownPos.south()).isAir()
                                    && pLevel.getBlockState(westDownPosPosBelow).getBlock() instanceof SandBlock) {
                                pLevel.setBlockAndUpdate(westDownPos, ModBlocks.CACTUS_BALL.get().defaultBlockState());
                                pLevel.neighborChanged(ModBlocks.CACTUS_BALL.get().defaultBlockState(),
                                        westDownPos, ((CactusBlock) (Object) this), pPos, false);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        BlockPos blockpos = pPos.above();
        if (pLevel.isEmptyBlock(blockpos)) {
            int i;
            for (i = 1; pLevel.getBlockState(pPos.below(i)).is(this); ++i) {
            }
            if (i < 3) {
                int j = pState.getValue(CactusBlock.AGE);
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(pLevel, blockpos, pState, true)) {
                    if (j == 15) {
                        pLevel.setBlockAndUpdate(blockpos, this.defaultBlockState());
                        BlockState blockstate = pState.setValue(CactusBlock.AGE, 0);
                        pLevel.setBlock(pPos, blockstate, 4);
                        pLevel.neighborChanged(blockstate, blockpos, this, pPos, false);
                    } else {
                        pLevel.setBlock(pPos, pState.setValue(CactusBlock.AGE, j + 1), 4);
                    }
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(pLevel, pPos, pState);
                }
            }
        }
        BlockPos eastUpperPos = pPos.east().north();
        BlockPos eastUpperPosBelow = eastUpperPos.below();
        BlockPos westUpperPos = pPos.west().north();
        BlockPos westUpperPosPosBelow = westUpperPos.below();
        BlockPos eastDownPos = pPos.east().south();
        BlockPos eastDownPosBelow = eastDownPos.below();
        BlockPos westDownPos = pPos.west().south();
        BlockPos westDownPosPosBelow = westDownPos.below();
        if (pLevel.getBlockState(pPos.below()).getBlock() instanceof SandBlock) {
            if (pLevel.getBlockState(pPos.above(2)).getBlock() instanceof CactusBlock) {
                if (!pLevel.getBlockState(pPos.above(3)).is(ModBlocks.CACTUS_FRUIT.get())) {
                    if (Math.random() <= 0.25) {
                        pLevel.setBlock(pPos.above(3),
                                ModBlocks.CACTUS_FRUIT.get().defaultBlockState(), 11);
                        pLevel.neighborChanged(pLevel.getBlockState(pPos.above(2)),
                                pPos.above(2), this, pPos, false);
                    }
                }
                BlockState fruitState = pLevel.getBlockState(pPos.above(3));
                if (fruitState.is(ModBlocks.CACTUS_FRUIT.get())) {
                    if (((CactusFruitBlock) fruitState.getBlock()).isMaxAge(fruitState)) {
                        if (Math.random() <= 0.25) {
                            if (pLevel.getBlockState(eastUpperPos).isAir()
                                    && pLevel.getBlockState(eastUpperPos.east()).isAir()
                                    && pLevel.getBlockState(eastUpperPos.west()).isAir()
                                    && pLevel.getBlockState(eastUpperPos.north()).isAir()
                                    && pLevel.getBlockState(eastUpperPos.south()).isAir()
                                    && pLevel.getBlockState(eastUpperPosBelow).getBlock() instanceof SandBlock) {
                                pLevel.setBlockAndUpdate(eastUpperPos, ModBlocks.CACTUS_BALL.get().defaultBlockState());
                                pLevel.neighborChanged(ModBlocks.CACTUS_BALL.get().defaultBlockState(),
                                        eastUpperPosBelow, ((CactusBlock) (Object) this), pPos, false);
                            }
                        }
                        if (Math.random() > 0.25
                                && Math.random() <= 0.5) {
                            if (pLevel.getBlockState(westUpperPos).isAir()
                                    && pLevel.getBlockState(westUpperPos.east()).isAir()
                                    && pLevel.getBlockState(westUpperPos.west()).isAir()
                                    && pLevel.getBlockState(westUpperPos.north()).isAir()
                                    && pLevel.getBlockState(westUpperPos.south()).isAir()
                                    && pLevel.getBlockState(westUpperPosPosBelow).getBlock() instanceof SandBlock) {
                                pLevel.setBlockAndUpdate(westUpperPos, ModBlocks.CACTUS_BALL.get().defaultBlockState());
                                pLevel.neighborChanged(ModBlocks.CACTUS_BALL.get().defaultBlockState(),
                                        westUpperPosPosBelow, ((CactusBlock) (Object) this), pPos, false);
                            }
                        }
                        if (Math.random() > 0.5
                                && Math.random() <= 0.75) {
                            if (pLevel.getBlockState(eastDownPos).isAir()
                                    && pLevel.getBlockState(eastDownPos.east()).isAir()
                                    && pLevel.getBlockState(eastDownPos.west()).isAir()
                                    && pLevel.getBlockState(eastDownPos.north()).isAir()
                                    && pLevel.getBlockState(eastDownPos.south()).isAir()
                                    && pLevel.getBlockState(eastDownPosBelow).getBlock() instanceof SandBlock) {
                                pLevel.setBlockAndUpdate(eastDownPos, ModBlocks.CACTUS_BALL.get().defaultBlockState());
                                pLevel.neighborChanged(ModBlocks.CACTUS_BALL.get().defaultBlockState(),
                                        eastDownPos, ((CactusBlock) (Object) this), pPos, false);
                            }
                        }
                        if (Math.random() > 0.75) {
                            if (pLevel.getBlockState(westDownPos).isAir()
                                    && pLevel.getBlockState(westDownPos.east()).isAir()
                                    && pLevel.getBlockState(westDownPos.west()).isAir()
                                    && pLevel.getBlockState(westDownPos.north()).isAir()
                                    && pLevel.getBlockState(westDownPos.south()).isAir()
                                    && pLevel.getBlockState(westDownPosPosBelow).getBlock() instanceof SandBlock) {
                                pLevel.setBlockAndUpdate(westDownPos, ModBlocks.CACTUS_BALL.get().defaultBlockState());
                                pLevel.neighborChanged(ModBlocks.CACTUS_BALL.get().defaultBlockState(),
                                        westDownPos, ((CactusBlock) (Object) this), pPos, false);
                            }
                        }
                    }
                }
            }
        }
    }
}
