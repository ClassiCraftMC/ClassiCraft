package nameless.classicraft.worldgen;

import com.mojang.serialization.Codec;
import nameless.classicraft.block.SaltStalactiteBlock;
import nameless.classicraft.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.Optional;
import java.util.function.Consumer;

public class SaltStalactiteFeature extends Feature<SaltStalactiteConfiguration> {

    public SaltStalactiteFeature(Codec<SaltStalactiteConfiguration> pCodec) {
        super(pCodec);
    }

    /**
     * Places the given feature at the given location.
     * During world generation, features are provided with a 3x3 region of chunks, centered on the chunk being generated,
     * that they can safely generate into.
     * @param pContext A context object with a reference to the level and the position the feature is being placed at
     */
    public boolean place(FeaturePlaceContext<SaltStalactiteConfiguration> pContext) {
        LevelAccessor levelaccessor = pContext.level();
        BlockPos blockpos = pContext.origin();
        RandomSource randomsource = pContext.random();
        SaltStalactiteConfiguration pointeddripstoneconfiguration = pContext.config();
        Optional<Direction> optional = getTipDirection(levelaccessor, blockpos, randomsource);
        if (optional.isEmpty()) {
            return false;
        } else {
            BlockPos blockpos1 = blockpos.relative(optional.get().getOpposite());
            createPatchOfDripstoneBlocks(levelaccessor, randomsource, blockpos1, pointeddripstoneconfiguration);
            int i = randomsource.nextFloat() < pointeddripstoneconfiguration.chanceOfTallerDripstone && isEmptyOrWater(levelaccessor.getBlockState(blockpos.relative(optional.get()))) ? 2 : 1;
            growPointedDripstone(levelaccessor, blockpos, optional.get(), i, false);
            return true;
        }
    }

    private static Optional<Direction> getTipDirection(LevelAccessor pLevel, BlockPos pPos, RandomSource pRandom) {
        boolean flag = isDripstoneBase(pLevel.getBlockState(pPos.above()));
        boolean flag1 = isDripstoneBase(pLevel.getBlockState(pPos.below()));
        if (flag && flag1) {
            return Optional.of(pRandom.nextBoolean() ? Direction.DOWN : Direction.UP);
        } else if (flag) {
            return Optional.of(Direction.DOWN);
        } else {
            return flag1 ? Optional.of(Direction.UP) : Optional.empty();
        }
    }

    private static void createPatchOfDripstoneBlocks(LevelAccessor pLevel, RandomSource pRandom, BlockPos pPos, SaltStalactiteConfiguration pConfig) {
       placeDripstoneBlockIfPossible(pLevel, pPos);

        for(Direction direction : Direction.Plane.HORIZONTAL) {
            if (!(pRandom.nextFloat() > pConfig.chanceOfDirectionalSpread)) {
                BlockPos blockpos = pPos.relative(direction);
                placeDripstoneBlockIfPossible(pLevel, blockpos);
                if (!(pRandom.nextFloat() > pConfig.chanceOfSpreadRadius2)) {
                    BlockPos blockpos1 = blockpos.relative(Direction.getRandom(pRandom));
                    placeDripstoneBlockIfPossible(pLevel, blockpos1);
                    if (!(pRandom.nextFloat() > pConfig.chanceOfSpreadRadius3)) {
                        BlockPos blockpos2 = blockpos1.relative(Direction.getRandom(pRandom));
                        placeDripstoneBlockIfPossible(pLevel, blockpos2);
                    }
                }
            }
        }

    }

    protected static boolean placeDripstoneBlockIfPossible(LevelAccessor pLevel, BlockPos pPos) {
        BlockState blockstate = pLevel.getBlockState(pPos);
        if (blockstate.is(BlockTags.DRIPSTONE_REPLACEABLE)) {
            pLevel.setBlock(pPos, ModBlocks.SALT_STALACTITE.get().defaultBlockState(), 2);
            return true;
        } else {
            return false;
        }
    }

    public static boolean isDripstoneBase(BlockState pState) {
        return pState.is(ModBlocks.SALT_STALACTITE.get()) || pState.is(BlockTags.DRIPSTONE_REPLACEABLE);
    }

    protected static void buildBaseToTipColumn(Direction pDirection, int pHeight, boolean pMergeTip, Consumer<BlockState> pBlockSetter) {
        if (pHeight >= 3) {
            pBlockSetter.accept(createPointedDripstone(pDirection, DripstoneThickness.BASE));

            for(int i = 0; i < pHeight - 3; ++i) {
                pBlockSetter.accept(createPointedDripstone(pDirection, DripstoneThickness.MIDDLE));
            }
        }

        if (pHeight >= 2) {
            pBlockSetter.accept(createPointedDripstone(pDirection, DripstoneThickness.FRUSTUM));
        }

        if (pHeight >= 1) {
            pBlockSetter.accept(createPointedDripstone(pDirection, pMergeTip ? DripstoneThickness.TIP_MERGE : DripstoneThickness.TIP));
        }

    }

    private static BlockState createPointedDripstone(Direction pDirection, DripstoneThickness pDripstoneThickness) {
        return ModBlocks.SALT_STALACTITE.get().defaultBlockState().setValue(SaltStalactiteBlock.TIP_DIRECTION, pDirection).setValue(SaltStalactiteBlock.THICKNESS, pDripstoneThickness);
    }


    protected static void growPointedDripstone(LevelAccessor pLevel, BlockPos pPos, Direction pDirection, int pHeight, boolean pMergeTip) {
        if (isDripstoneBase(pLevel.getBlockState(pPos.relative(pDirection.getOpposite())))) {
            BlockPos.MutableBlockPos blockpos$mutableblockpos = pPos.mutable();
            buildBaseToTipColumn(pDirection, pHeight, pMergeTip, (p_190846_) -> {
                if (p_190846_.is(ModBlocks.SALT_STALACTITE.get())) {
                    p_190846_ = p_190846_.setValue(SaltStalactiteBlock.WATERLOGGED, Boolean.valueOf(pLevel.isWaterAt(blockpos$mutableblockpos)));
                }

                pLevel.setBlock(blockpos$mutableblockpos, p_190846_, 2);
                blockpos$mutableblockpos.move(pDirection);
            });
        }
    }

    public static boolean isEmptyOrWater(BlockState p_159665_) {
        return p_159665_.isAir() || p_159665_.is(Blocks.WATER);
    }
}