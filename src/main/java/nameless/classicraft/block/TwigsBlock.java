package nameless.classicraft.block;

import net.minecraft.block.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class TwigsBlock extends Block {

    public static final VoxelShape SHAPE = Block.createCuboidShape(6, 0, 6, 10, 1, 10);

    public TwigsBlock() {
        super(AbstractBlock.Settings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(0.15f).noCollision().nonOpaque());
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (!state.canPlaceAt(world, pos) && !world.isClient) {
            world.breakBlock(pos, true);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        giveItemToPlayer(world, player);
        world.removeBlock(pos, false);
        return ActionResult.SUCCESS;
    }


    public static void giveItemToPlayer(World world, PlayerEntity player) {
        if (!world.isClient) {
            final ItemEntity entity = new ItemEntity(world, player.getX(), player.getY() + 0.5, player.getZ(), Items.STICK.getDefaultStack());
            entity.setPickupDelay(0);
            world.spawnEntity(entity);
        }
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState stateUnder = world.getBlockState(pos.down());
        return stateUnder.isSideSolidFullSquare(world, pos.down(), Direction.UP);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public boolean canMobSpawnInside() {
        return true;
    }

}
