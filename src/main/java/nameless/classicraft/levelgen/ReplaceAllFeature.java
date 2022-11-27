package nameless.classicraft.levelgen;

import com.mojang.serialization.Codec;
import nameless.classicraft.block.*;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModConfigs;
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
    public boolean place(NoneFeatureConfiguration p_204741_, WorldGenLevel p_204742_, ChunkGenerator p_204743_, RandomSource p_204744_, BlockPos p_204745_) {
        return super.place(p_204741_, p_204742_, p_204743_, p_204744_, p_204745_);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        if (!ModConfigs.replaceVanillaBlock.get()) return false;
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
