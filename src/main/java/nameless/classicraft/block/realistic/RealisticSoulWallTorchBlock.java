package nameless.classicraft.block.realistic;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Map;

public class RealisticSoulWallTorchBlock extends RealisticSoulTorchBlock {

    public static final int TICK_RATE = 1200;

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private static final Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(ImmutableMap.of(
            Direction.NORTH, box(5.5D, 3.0D, 11.0D, 10.5D, 13.0D, 16.0D),
            Direction.SOUTH, box(5.5D, 3.0D, 0.0D, 10.5D, 13.0D, 5.0D),
            Direction.WEST, box(11.0D, 3.0D, 5.5D, 16.0D, 13.0D, 10.5D),
            Direction.EAST, box(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D)));

    public RealisticSoulWallTorchBlock() {
        super();
        stateDefinition.any().setValue(FACING, Direction.NORTH);
    }

    @Override
    public void animateTick(BlockState state, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (state.getValue(LITSTATE) == LIT || (state.getValue(LITSTATE) == SMOLDERING && pLevel.getRandom().nextInt(2) == 1)) {
            Direction direction = state.getValue(FACING);
            double d0 = (double) pPos.getX() + 0.5D;
            double d1 = (double) pPos.getY() + 0.7D;
            double d2 = (double) pPos.getZ() + 0.5D;
            Direction direction1 = direction.getOpposite();
            pLevel.addParticle(ParticleTypes.SOUL_FIRE_FLAME, d0 + 0.27D * (double) direction1.getStepX(), d1 + 0.22D,
                    d2 + 0.27D * (double) direction1.getStepZ(), 0.0D, 0.0D, 0.0D);
            pLevel.addParticle(ParticleTypes.SOUL_FIRE_FLAME, d0 + 0.27D * (double) direction1.getStepX(), d1 + 0.22D,
                    d2 + 0.27D * (double) direction1.getStepZ(), 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return getShape(pState);
    }

    public static VoxelShape getShape(BlockState state) {
        return AABBS.get(state.getValue(FACING));
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        Direction direction = pState.getValue(FACING);
        BlockPos blockpos = pPos.relative(direction.getOpposite());
        BlockState blockstate = pLevel.getBlockState(blockpos);
        return blockstate.isFaceSturdy(pLevel, blockpos, direction);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(FACING);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return pFacing.getOpposite() == pState.getValue(FACING) && !pState.canSurvive(pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : pState;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public boolean isValidSpawn(BlockState state, BlockGetter level, BlockPos pos, SpawnPlacements.Type type, EntityType<?> entityType) {
        return Blocks.WALL_TORCH.isValidSpawn(state, level, pos, type, entityType);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        getTorchStateForPlacement(pContext, Blocks.WALL_TORCH, this);
        return super.getStateForPlacement(pContext);
    }
}
