package nameless.classicraft.api.event;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraftforge.event.level.BlockEvent;

/**
 * A event fired when block was destroyed
 * this event can get the block's pos while break
 */
public class BlockDropEvent extends BlockEvent.BreakEvent {

    private final BlockPos pos;
    private final BlockState state;
    private final PlayerEntity player;
    private final World world;

    public BlockDropEvent(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super(world, pos, state, player);
        this.pos = pos;
        this.state = state;
        this.player = player;
        this.world = world;
    }

    @Override
    public BlockPos getPos() {
        return pos;
    }

    @Override
    public BlockState getState() {
        return state;
    }

    @Override
    public PlayerEntity getPlayer() {
        return player;
    }

    @Override
    public WorldAccess getLevel() {
        return world;
    }

    public World getWorld() {
        return world;
    }
}
