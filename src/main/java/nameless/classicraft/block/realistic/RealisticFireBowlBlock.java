package nameless.classicraft.block.realistic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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

import static nameless.classicraft.util.LightUtils.*;

public class RealisticFireBowlBlock extends Block {

    protected static final VoxelShape AABB = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 1.0D, 15.0D);

    public RealisticFireBowlBlock() {
        super(BlockBehaviour.Properties.of(Material.METAL).lightLevel(getLightValueFromState()).strength(1.5F, 6.0F).sound(SoundType.WOOD));
        registerDefaultState(this.stateDefinition.any()
                .setValue(LITSTATE, 0)
                .setValue(FUEL_LEVEL_I_BURNTIME, 0)
                .setValue(FUEL_LEVEL_II_BURNTIME, 0)
                .setValue(FUEL_LEVEL_IV_BURNTIME, 0)
                .setValue(FUEL_LEVEL_V_BURNTIME, 0));
    }

    @Override
    public void animateTick(BlockState state, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (state.getValue(LITSTATE) == LIT || (state.getValue(LITSTATE) == SMOLDERING && pLevel.getRandom().nextInt(2) == 1)) {
            double d0 = pPos.getX() + 0.5D;
            double d1 = pPos.getY() + 0.5D;
            double d2 = pPos.getZ() + 0.5D;
            pLevel.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            pLevel.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
       return useBlockNeedFuel(pState, pLevel, pPos, pPlayer, pHand, pHit, this);
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
       tickBlockNeedFuel(pState, pLevel, pPos, pRandom, this);
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
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(LITSTATE);
        pBuilder.add(OIL);
        pBuilder.add(BE_HANGING);
        pBuilder.add(BE_WATERLOGGED);
        pBuilder.add(FUEL_LEVEL_I_BURNTIME, FUEL_LEVEL_II_BURNTIME, FUEL_LEVEL_IV_BURNTIME, FUEL_LEVEL_V_BURNTIME);
    }

    public static IntegerProperty getLitState() {
        return LITSTATE;
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

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return AABB;
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return pFacing == Direction.DOWN && !this.canSurvive(pState, pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return canSupportCenter(pLevel, pPos.below(), Direction.UP);
    }

}

