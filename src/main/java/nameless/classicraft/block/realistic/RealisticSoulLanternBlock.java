package nameless.classicraft.block.realistic;

import nameless.classicraft.ClassiCraftConfiguration;
import nameless.classicraft.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.function.ToIntFunction;

public class RealisticSoulLanternBlock extends Block implements SimpleWaterloggedBlock {

    public static final int TICK_INTERVAL = 1200;
    protected static final int INITIAL_BURN_TIME = ClassiCraftConfiguration.lanternBurnoutTime.get();
    protected static final boolean SHOULD_BURN_OUT = INITIAL_BURN_TIME > 0;
    protected static final IntegerProperty BURNTIME = IntegerProperty.create("burntime", 0, SHOULD_BURN_OUT ? INITIAL_BURN_TIME : 1);
    protected static final IntegerProperty LITSTATE = IntegerProperty.create("litstate", 0, 2);
    public static final int LIT = 2;
    public static final int SMOLDERING = 1;
    public static final int UNLIT = 0;
    public static final BooleanProperty HANGING = BlockStateProperties.HANGING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape AABB = Shapes.or(
            Block.box(5.0D, 0.0D, 5.0D, 11.0D, 7.0D, 11.0D),
            Block.box(6.0D, 7.0D, 6.0D, 10.0D, 9.0D, 10.0D));
    protected static final VoxelShape HANGING_AABB = Shapes.or(
            Block.box(5.0D, 1.0D, 5.0D, 11.0D, 8.0D, 11.0D),
            Block.box(6.0D, 8.0D, 6.0D, 10.0D, 10.0D, 10.0D));

    public RealisticSoulLanternBlock() {
        super(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel(getLightValueFromState()));
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(HANGING, Boolean.FALSE)
                .setValue(WATERLOGGED, Boolean.FALSE)
                .setValue(LITSTATE, 0).setValue(BURNTIME, 0));
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pPlayer.getItemInHand(pHand).getItem() == Items.FLINT_AND_STEEL) {
            playLightingSound(pLevel, pPos);
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

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
        for(Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis() == Direction.Axis.Y) {
                BlockState state = this.defaultBlockState().setValue(HANGING, direction == Direction.UP);
                if (state.canSurvive(context.getLevel(), context.getClickedPos())) {
                    return state.setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
                }
            }
        }

        return null;
    }

    @Override
    public VoxelShape getShape(BlockState p_153474_, BlockGetter p_153475_, BlockPos p_153476_, CollisionContext p_153477_) {
        return p_153474_.getValue(HANGING) ? HANGING_AABB : AABB;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState>  pBuilder) {
        pBuilder.add(HANGING, WATERLOGGED);
        pBuilder.add(BURNTIME);
        pBuilder.add(LITSTATE);
    }

    @Override
    public boolean canSurvive(BlockState p_153479_, LevelReader p_153480_, BlockPos p_153481_) {
        Direction direction = getConnectedDirection(p_153479_).getOpposite();
        return Block.canSupportCenter(p_153480_, p_153481_.relative(direction), direction.getOpposite());
    }

    protected static Direction getConnectedDirection(BlockState state) {
        return state.getValue(HANGING) ? Direction.DOWN : Direction.UP;
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    @Override
    public BlockState updateShape(BlockState p_153483_, Direction p_153484_, BlockState p_153485_, LevelAccessor p_153486_, BlockPos p_153487_, BlockPos p_153488_) {
        if (p_153483_.getValue(WATERLOGGED)) {
            p_153486_.scheduleTick(p_153487_, Fluids.WATER, Fluids.WATER.getTickDelay(p_153486_));
        }

        return getConnectedDirection(p_153483_).getOpposite() == p_153484_ && !p_153483_.canSurvive(p_153486_, p_153487_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_153483_, p_153484_, p_153485_, p_153486_, p_153487_, p_153488_);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public boolean isPathfindable(BlockState p_153469_, BlockGetter p_153470_, BlockPos p_153471_, PathComputationType p_153472_) {
        return false;
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
        level.setBlockAndUpdate(pos, ModBlocks.SOUL_LANTERN.get().defaultBlockState().setValue(LITSTATE, LIT).setValue(BURNTIME, getInitialBurnTime()));
        if (SHOULD_BURN_OUT) {
            level.scheduleTick(pos, this, TICK_INTERVAL);
        }
    }

    public void changeToSmoldering(Level level, BlockPos pos, BlockState state, int newBurnTime) {
        if (SHOULD_BURN_OUT) {
            level.setBlockAndUpdate(pos, ModBlocks.SOUL_LANTERN.get().defaultBlockState().setValue(LITSTATE, SMOLDERING).setValue(BURNTIME, newBurnTime));
            level.scheduleTick(pos, this, TICK_INTERVAL);
        }
    }

    public void changeToUnlit(Level level, BlockPos pos, BlockState state) {
        if (SHOULD_BURN_OUT) {
            level.setBlockAndUpdate(pos, ModBlocks.SOUL_LANTERN.get().defaultBlockState());
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
            if (state.getValue(RealisticSoulLanternBlock.LITSTATE) == RealisticSoulLanternBlock.LIT) {
                return 16;
            } else if (state.getValue(RealisticSoulLanternBlock.LITSTATE) == RealisticSoulLanternBlock.SMOLDERING) {
                return 12;
            }
            return 0;
        };
    }
}

