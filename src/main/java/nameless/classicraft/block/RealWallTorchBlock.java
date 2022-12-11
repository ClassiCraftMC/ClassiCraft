package nameless.classicraft.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.annotation.MethodsReturnNonnullByDefault;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import javax.annotation.Nullable;
import java.util.Map;

@MethodsReturnNonnullByDefault
public class RealWallTorchBlock extends RealTorchBlock {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    private static final Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH,
            Block.createCuboidShape(5.5D, 3.0D, 11.0D,
                    10.5D, 13.0D, 16.0D),
            Direction.SOUTH, Block.createCuboidShape(5.5D, 3.0D, 0.0D,
                    10.5D, 13.0D, 5.0D), Direction.WEST,
            Block.createCuboidShape(11.0D, 3.0D, 5.5D,
                    16.0D, 13.0D, 10.5D), Direction.EAST,
            Block.createCuboidShape(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D)));

    public RealWallTorchBlock() {
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(AbstractLightBlock.getLitState(), false));
    }

    @Override
    public String getTranslationKey() {
        return this.asItem().getTranslationKey();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getShape(state);
    }

    public static VoxelShape getShape(BlockState pState) {
        return AABBS.get(pState.get(FACING));
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = (Direction)state.get(FACING);
        BlockPos blockPos = pos.offset(direction.getOpposite());
        BlockState blockState = world.getBlockState(blockPos);
        return blockState.isSideSolidFullSquare(world, blockPos, direction);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockstate = this.getDefaultState();
        WorldView world = ctx.getWorld();
        BlockPos blockpos = ctx.getBlockPos();
        Direction[] adirection = ctx.getPlacementDirections();

        for(Direction direction : adirection) {
            if (direction.getAxis().isHorizontal()) {
                Direction direction1 = direction.getOpposite();
                blockstate = blockstate.with(FACING, direction1);
                if (blockstate.canPlaceAt(world, blockpos)) {
                    return blockstate;
                }
            }
        }

        return null;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : state;
    }

    /**
     * Called periodically clientside on blocks near the player to show effects (like furnace fire particles).
     */
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(LIT)) {
            Direction direction = state.get(FACING);
            double d0 = (double)pos.getX() + 0.5D;
            double d1 = (double)pos.getY() + 0.7D;
            double d2 = (double)pos.getZ() + 0.5D;
            Direction direction1 = direction.getOpposite();
            world.addParticle(ParticleTypes.SMOKE, d0 + 0.27D * (double)direction1.getOffsetX(), d1 + 0.22D, d2 + 0.27D * (double)direction1.getOffsetZ(), 0.0D, 0.0D, 0.0D);
            world.addParticle(ParticleTypes.SOUL_FIRE_FLAME, d0 + 0.27D * (double)direction1.getOffsetX(), d1 + 0.22D, d2 + 0.27D * (double)direction1.getOffsetZ(), 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation((Direction)state.get(FACING)));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
    }
}
