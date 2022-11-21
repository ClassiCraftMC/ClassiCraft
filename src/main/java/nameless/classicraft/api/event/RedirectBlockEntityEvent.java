package nameless.classicraft.api.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.Event;

public class RedirectBlockEntityEvent extends Event {

    private final BlockPos pos;
    private final BlockState state;
    private final Block block;
    private final BlockEntity blockEntity;

    public RedirectBlockEntityEvent(BlockPos pos, BlockState state, Block block, BlockEntity blockEntity) {
       this.state = state;
       this.pos = pos;
       this.block = block;
       this.blockEntity = blockEntity;
    }

    public BlockState getState() {
        return state;
    }

    public BlockPos getPos() {
        return pos;
    }

    public Block getBlock() {
        return block;
    }

    public BlockEntity getBlockEntity() {
        return blockEntity;
    }
}
