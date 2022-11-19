package nameless.classicraft.block.realistic;

import nameless.classicraft.api.light.LightAPI;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
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
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.function.ToIntFunction;

public class RealisticTorchBlock extends Block implements LightAPI {

    protected static final int AABB_STANDING_OFFSET = 2;
    protected static final VoxelShape AABB = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);

    public RealisticTorchBlock() {
        super(BlockBehaviour.Properties.of(Material.ICE).noCollission().instabreak().lightLevel(getLightLevelFromState()).sound(SoundType.WOOD));
        this.registerDefaultState(this.defaultBlockState().setValue(LITSTATE, 0).setValue(TORCH_BURNTIME, 0));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return AABB;
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return pFacing == Direction.DOWN && !this.canSurvive(pState, pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return canSupportCenter(pLevel, pPos.below(), Direction.UP);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        useTorch(pState, pLevel, pPos, pPlayer, pHand, pHit, this, ModItems.MATCHBOX.get(), ModBlocks.TORCH.get().asItem(), ModItems.LIT_TORCH.get());
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return CAUSE_FIRE &&pState.getValue(LITSTATE) == LIT  && new Random().nextInt(9) == 0;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        randomTickTorch(state, level, pos, random);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
       tickTorch(state, level, pos, random, this);
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if(!pIsMoving && pState.getBlock() != pOldState.getBlock())
        {
            defaultBlockState().updateIndirectNeighbourShapes(pLevel,pPos,3);
        }
        if(TORCH_SHOULD_BURN_OUT&&pState.getBlock() instanceof RealisticTorchBlock&&pState.getValue(LITSTATE) > UNLIT)
            pLevel.scheduleTick(pPos, this, TICK_INTERVAL);
        pLevel.updateNeighborsAt(pPos, this);
        super.onPlace(pState,pLevel,pPos,pOldState,pIsMoving);
    }

    public static IntegerProperty getBurnTime() {
        return TORCH_BURNTIME;
    }

    public static IntegerProperty getLitState() {
        return LITSTATE;
    }

    public static ToIntFunction<BlockState> getLightLevelFromState()
    {
        return (state) -> {
            if (state.getValue(RealisticLanternBlock.LITSTATE) == 0) {
                return 0;
            }else {
                return 12;
            }
        };
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
     getTorchStateForPlacement(pContext, Blocks.TORCH, this);
     return super.getStateForPlacement(pContext);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(LITSTATE) == LIT || (pState.getValue(LITSTATE) == SMOLDERING && pLevel.getRandom().nextInt(2) == 1)) {
            double d0 = (double)pPos.getX() + 0.5D;
            double d1 = (double)pPos.getY() + 0.7D;
            double d2 = (double)pPos.getZ() + 0.5D;
            pLevel.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            pLevel.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LITSTATE, TORCH_BURNTIME);
    }
}
