package nameless.classicraft.block.realistic;

import nameless.classicraft.block.entity.RealisticCampfireBlockEntity;
import nameless.classicraft.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.ToIntFunction;

import static nameless.classicraft.util.LightUtils.*;

public class RealisticCampFireBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {

    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D);
    public static final BooleanProperty SIGNAL_FIRE = BlockStateProperties.SIGNAL_FIRE;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private static final VoxelShape VIRTUAL_FENCE_POST = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);
    private static final int SMOKE_DISTANCE = 5;

    public RealisticCampFireBlock() {
        super(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2.0F).sound(SoundType.WOOD).lightLevel(getLightValueFromState()).noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(LITSTATE, UNLIT).setValue(SIGNAL_FIRE, Boolean.FALSE).setValue(BE_WATERLOGGED, Boolean.FALSE).setValue(FACING, Direction.NORTH));
    }

    private static ToIntFunction<BlockState> getLightValueFromState() {
        return (state) -> {
            if (state.getValue(LITSTATE) == LIT) {
                return 14;
            } else if (state.getValue(LITSTATE) == SMOLDERING) {
                return 12;
            }
            return 0;
        };
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        tickBlockNeedFuel(pState, pLevel, pPos, pRandom, this, CAMPFIRE_SHOULD_BURN_OUT, CAMPFIRE_BURNTIME, CAMPFIRE_INITIAL_BURN_TIME);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        useBlockNeedFuel(pState, pLevel, pPos, pPlayer, pHand, pHit, this, CAMPFIRE_BURNTIME, CAMPFIRE_INITIAL_BURN_TIME);
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity instanceof RealisticCampfireBlockEntity campfireblockentity) {
            ItemStack itemstack = pPlayer.getItemInHand(pHand);
            Optional<CampfireCookingRecipe> optional = campfireblockentity.getCookableRecipe(itemstack);
            if (optional.isPresent()) {
                if (!pLevel.isClientSide && campfireblockentity.placeFood(pPlayer, pPlayer.getAbilities().instabuild ? itemstack.copy() : itemstack, optional.get().getCookingTime())) {
                    pPlayer.awardStat(Stats.INTERACT_WITH_CAMPFIRE);
                    return InteractionResult.SUCCESS;
                }

                return InteractionResult.CONSUME;
            }
        }

        return InteractionResult.PASS;
    }

    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (pState.getValue(LITSTATE) == LIT && pEntity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)pEntity)) {
            pEntity.hurt(DamageSource.IN_FIRE, 2);
        }

        super.entityInside(pState, pLevel, pPos, pEntity);
    }

    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.is(pNewState.getBlock())) {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            if (blockentity instanceof RealisticCampfireBlockEntity) {
                Containers.dropContents(pLevel, pPos, ((RealisticCampfireBlockEntity)blockentity).getItems());
            }

            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        LevelAccessor levelaccessor = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        boolean flag = levelaccessor.getFluidState(blockpos).getType() == Fluids.WATER;
        return this.defaultBlockState().setValue(BE_WATERLOGGED, flag).setValue(SIGNAL_FIRE, Boolean.valueOf(this.isSmokeSource(levelaccessor.getBlockState(blockpos.below())))).setValue(LITSTATE, UNLIT).setValue(FACING, pContext.getHorizontalDirection());
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pState.getValue(BE_WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }

        return pFacing == Direction.DOWN ? pState.setValue(SIGNAL_FIRE, Boolean.valueOf(this.isSmokeSource(pFacingState))) : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    private boolean isSmokeSource(BlockState pState) {
        return pState.is(Blocks.HAY_BLOCK);
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(LITSTATE) == LIT || (pState.getValue(LITSTATE) == SMOLDERING && pLevel.getRandom().nextInt(2) == 1)) {
            if (pRandom.nextInt(10) == 0) {
                pLevel.playLocalSound((double)pPos.getX() + 0.5D, (double)pPos.getY() + 0.5D, (double)pPos.getZ() + 0.5D, SoundEvents.CAMPFIRE_CRACKLE, SoundSource.BLOCKS, 0.5F + pRandom.nextFloat(), pRandom.nextFloat() * 0.7F + 0.6F, false);
            }

            if (pRandom.nextInt(5) == 0) {
                for(int i = 0; i < pRandom.nextInt(1) + 1; ++i) {
                    pLevel.addParticle(ParticleTypes.LAVA, (double)pPos.getX() + 0.5D, (double)pPos.getY() + 0.5D, (double)pPos.getZ() + 0.5D, (double)(pRandom.nextFloat() / 2.0F), 5.0E-5D, (double)(pRandom.nextFloat() / 2.0F));
                }
            }

        }
    }

    public static void dowse(@javax.annotation.Nullable Entity pEntity, LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
        if (pLevel.isClientSide()) {
            for(int i = 0; i < 20; ++i) {
                makeParticles((Level)pLevel, pPos, pState.getValue(SIGNAL_FIRE), true);
            }
        }

        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity instanceof RealisticCampfireBlockEntity) {
            ((RealisticCampfireBlockEntity)blockentity).dowse();
        }

        pLevel.gameEvent(pEntity, GameEvent.BLOCK_CHANGE, pPos);
    }

    public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
        if (!pState.getValue(BlockStateProperties.WATERLOGGED) && pFluidState.getType() == Fluids.WATER) {
            boolean flag = pState.getValue(LITSTATE) == LIT;
            if (flag) {
                if (!pLevel.isClientSide()) {
                    pLevel.playSound((Player)null, pPos, SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.BLOCKS, 1.0F, 1.0F);
                }

                dowse((Entity)null, pLevel, pPos, pState);
            }

            pLevel.setBlock(pPos, pState.setValue(BE_WATERLOGGED, Boolean.TRUE).setValue(LITSTATE, UNLIT), 3);
            pLevel.scheduleTick(pPos, pFluidState.getType(), pFluidState.getType().getTickDelay(pLevel));
            return true;
        } else {
            return false;
        }
    }

    public static void makeParticles(Level pLevel, BlockPos pPos, boolean pIsSignalFire, boolean pSpawnExtraSmoke) {
        RandomSource randomsource = pLevel.getRandom();
        SimpleParticleType simpleparticletype = pIsSignalFire ? ParticleTypes.CAMPFIRE_SIGNAL_SMOKE : ParticleTypes.CAMPFIRE_COSY_SMOKE;
        pLevel.addAlwaysVisibleParticle(simpleparticletype, true, (double)pPos.getX() + 0.5D + randomsource.nextDouble() / 3.0D * (double)(randomsource.nextBoolean() ? 1 : -1), (double)pPos.getY() + randomsource.nextDouble() + randomsource.nextDouble(), (double)pPos.getZ() + 0.5D + randomsource.nextDouble() / 3.0D * (double)(randomsource.nextBoolean() ? 1 : -1), 0.0D, 0.07D, 0.0D);
        if (pSpawnExtraSmoke) {
            pLevel.addParticle(ParticleTypes.SMOKE, (double)pPos.getX() + 0.5D + randomsource.nextDouble() / 4.0D * (double)(randomsource.nextBoolean() ? 1 : -1), (double)pPos.getY() + 0.4D, (double)pPos.getZ() + 0.5D + randomsource.nextDouble() / 4.0D * (double)(randomsource.nextBoolean() ? 1 : -1), 0.0D, 0.005D, 0.0D);
        }

    }

    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(BE_WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    public BlockState rotate(BlockState pState, Rotation pRot) {
        return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
    }

    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LITSTATE, SIGNAL_FIRE, BE_WATERLOGGED, BE_HANGING, FACING, CAMPFIRE_BURNTIME, OIL);
    }

    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new RealisticCampfireBlockEntity(pPos, pState);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (pLevel.isClientSide) {
            return pState.getValue(LITSTATE) == LIT ? createTickerHelper(pBlockEntityType, ModBlockEntities.CAMPFIRE.get(), RealisticCampfireBlockEntity::particleTick) : null;
        } else {
            return pState.getValue(LITSTATE) == LIT ? createTickerHelper(pBlockEntityType, ModBlockEntities.CAMPFIRE.get(), RealisticCampfireBlockEntity::cookTick) : createTickerHelper(pBlockEntityType, ModBlockEntities.CAMPFIRE.get(), RealisticCampfireBlockEntity::cooldownTick);
        }
    }

    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }
}
