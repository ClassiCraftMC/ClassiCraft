package nameless.classicraft.block.realistic;

import nameless.classicraft.ClassiCraftConfiguration;
import nameless.classicraft.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
import org.jetbrains.annotations.Nullable;

import java.util.function.ToIntFunction;

public class RealisticLargeFireBowlBlock extends Block {

    public static final int TICK_INTERVAL = 1200;
    protected static final int INITIAL_BURN_TIME = ClassiCraftConfiguration.largeFireBowlBurnoutTime.get();
    protected static final boolean SHOULD_BURN_OUT = INITIAL_BURN_TIME > 0;
    protected static final IntegerProperty BURNTIME = IntegerProperty.create("burntime", 0, SHOULD_BURN_OUT ? INITIAL_BURN_TIME : 1);
    protected static final IntegerProperty LITSTATE = IntegerProperty.create("litstate", 0, 2);

    public static final int LIT = 2;
    public static final int SMOLDERING = 1;
    public static final int UNLIT = 0;

    //TODO 完善碰撞箱
    protected static final VoxelShape AABB = box(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);

    public RealisticLargeFireBowlBlock() {
        super(BlockBehaviour.Properties.of(Material.METAL).lightLevel(getLightValueFromState()).strength(1.5F, 6.0F).sound(SoundType.WOOD));
        this.stateDefinition.any().setValue(LITSTATE, 0).setValue(BURNTIME, 0);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(LITSTATE) == LIT || (pState.getValue(LITSTATE) == SMOLDERING && pLevel.getRandom().nextInt(2) == 1)) {
            double d0 = (double)pPos.getX() + 0.5D;
            double d1 = (double)pPos.getY() + 1.0D;
            double d2 = (double)pPos.getZ() + 0.5D;
            pLevel.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            pLevel.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pPlayer.getItemInHand(pHand).getItem() == Items.FLINT_AND_STEEL) {
            playLightingSound(pLevel, pPos);
            if (!pPlayer.isCreative()) {
                ItemStack heldStack = pPlayer.getItemInHand(pHand);
                heldStack.hurtAndBreak(1, pPlayer, (p_41300_) -> {
                    p_41300_.broadcastBreakEvent(pHand);
                });
            }
            if (pLevel.isRainingAt(pPos)) {
                playExtinguishSound(pLevel, pPos);
            } else {
                changeToLit(pLevel, pPos, pState);
            }
            return InteractionResult.SUCCESS;
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!pLevel.isClientSide && SHOULD_BURN_OUT && pState.getValue(LITSTATE) > UNLIT) {
            int newBurnTime = pState.getValue(BURNTIME) - 1;
            if (newBurnTime <= 0) {
                playExtinguishSound(pLevel, pPos);
                changeToUnlit(pLevel, pPos, pState);
                pLevel.updateNeighborsAt(pPos, this);
            } else if (pState.getValue(LITSTATE) == LIT && (newBurnTime <= INITIAL_BURN_TIME / 10 || newBurnTime <= 1)) {
                changeToSmoldering(pLevel, pPos, pState, newBurnTime);
                pLevel.updateNeighborsAt(pPos, this);
            }else {
                pLevel.setBlockAndUpdate(pPos, pState.setValue(BURNTIME, newBurnTime));
                pLevel.scheduleTick(pPos, this, TICK_INTERVAL);
            }
        }
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        pLevel.scheduleTick(pPos, this, TICK_INTERVAL);
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if (!pIsMoving && pOldState.getBlock() != pState.getBlock()) {
            defaultBlockState().updateNeighbourShapes(pLevel, pPos, 3);
        }
        super.onPlace(pState, pLevel, pPos, pOldState, pIsMoving);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState>  pBuilder) {
        pBuilder.add(BURNTIME);
        pBuilder.add(LITSTATE);
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return pFacing == Direction.DOWN && !this.canSurvive(pState, pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return canSupportCenter(pLevel, pPos.below(), Direction.UP);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return AABB;
    }

    public static IntegerProperty getBurnTime() {
        return BURNTIME;
    }

    public static IntegerProperty getLitState() {
        return LITSTATE;
    }

    public static int getInitialBurnTime() {
        return SHOULD_BURN_OUT ? INITIAL_BURN_TIME : 0;
    }

    public void changeToLit(Level level, BlockPos pos, BlockState state) {
        level.setBlockAndUpdate(pos, ModBlocks.LARGE_FIRE_BOWL.get().defaultBlockState().setValue(LITSTATE, LIT).setValue(BURNTIME, getInitialBurnTime()));
        if (SHOULD_BURN_OUT) {
            level.scheduleTick(pos, this, TICK_INTERVAL);
        }
    }

    public void changeToSmoldering(Level level, BlockPos pos, BlockState state, int newBurnTime) {
        if (SHOULD_BURN_OUT) {
            level.setBlockAndUpdate(pos, ModBlocks.LARGE_FIRE_BOWL.get().defaultBlockState().setValue(LITSTATE, SMOLDERING).setValue(BURNTIME, newBurnTime));
            level.scheduleTick(pos, this, TICK_INTERVAL);
        }
    }

    public void changeToUnlit(Level level, BlockPos pos, BlockState state) {
        if (SHOULD_BURN_OUT) {
            level.setBlockAndUpdate(pos, ModBlocks.LARGE_FIRE_BOWL.get().defaultBlockState());
            level.scheduleTick(pos, this, TICK_INTERVAL);
        }
    }

    public void playLightingSound(Level level, BlockPos pos) {
        level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
    }

    public void playExtinguishSound(Level level, BlockPos pos) {
        level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
    }

    private static ToIntFunction<BlockState> getLightValueFromState() {
        return (state) -> {
            if (state.getValue(RealisticFireBowlBlock.LITSTATE) == RealisticFireBowlBlock.LIT) {
                return 14;
            } else if (state.getValue(RealisticFireBowlBlock.LITSTATE) == RealisticFireBowlBlock.SMOLDERING) {
                return 12;
            }
            return 0;
        };
    }
}

