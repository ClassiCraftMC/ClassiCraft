package org.bedracket.classicraft.block;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.bedracket.classicraft.init.ModBlockProperties;
import org.bedracket.classicraft.util.LightUtils;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.ToIntFunction;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class RealSoulTorchBlock extends AbstractLightBlock {

    protected static final VoxelShape AABB = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);
    protected static final IntegerProperty SOUL_TORCH_BURN_TIME = ModBlockProperties.SOUL_TORCH_BURN_TIME;
    protected static final int SOUL_TORCH_INITIAL_BURN_TIME = ModBlockProperties.SOUL_TORCH_INITIAL_BURN_TIME;

    public RealSoulTorchBlock() {
        super(BlockBehaviour.Properties.of(Material.ICE).noCollission().instabreak().lightLevel(getLightFromState()).sound(SoundType.WOOD));
        this.registerDefaultState(defaultBlockState().setValue(LIT, false).setValue(SOUL_TORCH_BURN_TIME, 0));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return AABB;
    }

    /**
     * Update the provided state given the provided neighbor direction and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific direction passed in.
     */
    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return pFacing == Direction.DOWN && !this.canSurvive(pState, pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return canSupportCenter(pLevel, pPos.below(), Direction.UP);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(LIT)) {
            double d0 = (double)pPos.getX() + 0.5D;
            double d1 = (double)pPos.getY() + 0.7D;
            double d2 = (double)pPos.getZ() + 0.5D;
            pLevel.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            pLevel.addParticle(ParticleTypes.SOUL_FIRE_FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    private static ToIntFunction<BlockState> getLightFromState(){
        return (state) -> {
            if (state.getValue(LIT)) {
                return 10;
            }else {
                return 0;
            }
        };
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        LightUtils.torchPlace(pState, pLevel, pPos, pOldState, pIsMoving, this);
        super.onPlace(pState, pLevel, pPos, pOldState, pIsMoving);
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        LightUtils.tickTorch(pState, pLevel, pPos, pRandom, this, SOUL_TORCH_BURN_TIME);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        return LightUtils.interactTorch(pLevel, pPos, pPlayer, pHand, this, Items.FLINT_AND_STEEL, SOUL_TORCH_BURN_TIME, SOUL_TORCH_INITIAL_BURN_TIME);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LIT, SOUL_TORCH_BURN_TIME);
    }

    public static IntegerProperty getBurnTime() {
        return SOUL_TORCH_BURN_TIME;
    }

    public static int getInitialBurnTime() {
        return SOUL_TORCH_INITIAL_BURN_TIME;
    }
}
