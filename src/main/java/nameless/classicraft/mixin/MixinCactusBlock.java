package nameless.classicraft.mixin;

import nameless.classicraft.common.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author DustW
 */
@Mixin(CactusBlock.class)
public class MixinCactusBlock {

    @Inject(method = "randomTick", at = @At("HEAD"))
    private void randomTickCC(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom, CallbackInfo ci) {
        BlockPos above = pPos.above();
        CactusBlock self = (CactusBlock) (Object) this;

        if (pLevel.isEmptyBlock(above)) {
            int i;
            for (i = 1; pLevel.getBlockState(pPos.below(i)).is(self); ++i) {
            }

            if (i >= 3 && ModBlocks.CACTUS_FRUIT.get().canSurvive(pState, pLevel, pPos)) {
                pLevel.setBlock(pPos, ModBlocks.CACTUS_FRUIT.get().defaultBlockState(), Block.UPDATE_ALL_IMMEDIATE);
            }
        }
    }
}
