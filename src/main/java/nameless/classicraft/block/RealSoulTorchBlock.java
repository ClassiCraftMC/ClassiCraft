package nameless.classicraft.block;

import nameless.classicraft.init.ModBlockProperties;
import nameless.classicraft.util.LightUtils;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.annotation.MethodsReturnNonnullByDefault;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.ToIntFunction;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class RealSoulTorchBlock extends AbstractLightBlock {

    protected static final VoxelShape AABB =
            Block.createCuboidShape(6.0D, 0.0D, 6.0D,
                    10.0D, 10.0D, 10.0D);
    protected static final IntProperty SOUL_TORCH_BURN_TIME = ModBlockProperties.SOUL_TORCH_BURN_TIME;
    protected static final int SOUL_TORCH_INITIAL_BURN_TIME = ModBlockProperties.SOUL_TORCH_INITIAL_BURN_TIME;

    public RealSoulTorchBlock() {
        super(AbstractBlock.Settings.of(Material.ICE).noCollision().breakInstantly().luminance(getLightFromState()).sounds(BlockSoundGroup.WOOD));
        this.setDefaultState(getDefaultState().with(LIT, false).with(SOUL_TORCH_BURN_TIME, 0));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AABB;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.DOWN && !this.canPlaceAt(state, world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return sideCoversSmallSquare(world, pos.down(), Direction.UP);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(LIT)) {
            double d0 = (double)pos.getX() + 0.5D;
            double d1 = (double)pos.getY() + 0.7D;
            double d2 = (double)pos.getZ() + 0.5D;
            world.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            world.addParticle(ParticleTypes.SOUL_FIRE_FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    private static ToIntFunction<BlockState> getLightFromState(){
        return (state) -> {
            if (state.get(LIT)) {
                return 10;
            }else {
                return 0;
            }
        };
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        LightUtils.torchPlace(state, world, pos, newState, moved, this);
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        LightUtils.tickTorch(state, world, pos, random, this, SOUL_TORCH_BURN_TIME);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return LightUtils.interactTorch(world, pos, player, hand, this, Items.FLINT_AND_STEEL, SOUL_TORCH_BURN_TIME, SOUL_TORCH_INITIAL_BURN_TIME);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT, SOUL_TORCH_BURN_TIME);
    }

    public static IntProperty getBurnTime() {
        return SOUL_TORCH_BURN_TIME;
    }

    public static int getInitialBurnTime() {
        return SOUL_TORCH_INITIAL_BURN_TIME;
    }
}
