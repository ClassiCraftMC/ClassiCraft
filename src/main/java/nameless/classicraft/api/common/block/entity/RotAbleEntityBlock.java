package nameless.classicraft.api.common.block.entity;

import nameless.classicraft.api.common.block.RotBlock;
import nameless.classicraft.common.rot.RotBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

/**
 * @author DustW
 */
public interface RotAbleEntityBlock extends EntityBlock, RotBlock {

    BlockEntityType<?> getType();

    @Nullable
    @Override
    default BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return RotBlocks.MAP.containsKey(pState.getBlock()) ? getType().create(pPos, pState) : null;
    }

    @Nullable
    @Override
    default <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pBlockEntityType == getType() ? TickAble.ticker() : null;
    }
}
