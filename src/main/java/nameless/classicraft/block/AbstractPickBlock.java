package nameless.classicraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * @author DustW
 */
public abstract class AbstractPickBlock extends Block {
    public static final VoxelShape SHAPE = box(0, 0, 0, 16, 1, 16);

    protected AbstractPickBlock(Properties pProperties) {
        super(pProperties);
    }

    abstract ItemStack getItem();

    @Override
    @SuppressWarnings("deprecation")
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!state.canSurvive(level, pos) && !level.isClientSide) {
            level.destroyBlock(pos, true);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            giveItemToPlayer(level, player, getItem());
            level.removeBlock(pos, false);
        }

        return InteractionResult.SUCCESS;
    }

    public static void giveItemToPlayer(Level level, Player player, ItemStack stack) {
        if (!stack.isEmpty() && !level.isClientSide) {
            final ItemEntity entity = new ItemEntity(level, player.getX(), player.getY() + 0.5, player.getZ(), stack);
            entity.setPickUpDelay(0);
            level.addFreshEntity(entity);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState stateUnder = level.getBlockState(pos.below());
        return stateUnder.isFaceSturdy(level, pos.below(), Direction.UP);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public boolean isPossibleToRespawnInThis() {
        return true;
    }
}
