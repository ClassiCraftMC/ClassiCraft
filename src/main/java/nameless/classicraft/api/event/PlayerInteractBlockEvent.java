package nameless.classicraft.api.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * Called when a Block is clicked by player
 * Try to make a better event than Forge's ClickEvent
 */
public class PlayerInteractBlockEvent extends PlayerEvent {

    private final BlockHitResult hit;
    private final BlockState state;
    private final Level level;
    private final BlockPos pos;
    private final Block block;
    private final InteractionHand hand;

    public PlayerInteractBlockEvent(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        super(player);
        this.hit = hit;
        this.state = state;
        this.level = level;
        this.pos = pos;
        this.hand = hand;
        this.block = state.getBlock();
    }

    public Level getLevel() {
        return level;
    }

    public BlockPos getPos() {
        return pos;
    }

    public BlockState getState() {
        return state;
    }

    public BlockHitResult getHit() {
        return hit;
    }

    public Block getBlock() {
        return block;
    }

    public InteractionHand getHand() {
        return hand;
    }
}
