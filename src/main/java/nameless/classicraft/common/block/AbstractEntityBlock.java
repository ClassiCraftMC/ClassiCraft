package nameless.classicraft.common.block;

import nameless.classicraft.common.block.entity.BasicBlockEntity;
import nameless.classicraft.common.block.entity.BasicMenuBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author DustW
 **/
@ParametersAreNonnullByDefault
@SuppressWarnings("deprecation")
public class AbstractEntityBlock<T extends BasicBlockEntity> extends BaseEntityBlock {
    Supplier<BlockEntityType<T>> supplier;

    protected AbstractEntityBlock(Properties properties, Supplier<BlockEntityType<T>> supplier) {
        super(properties);
        this.supplier = supplier;
    }

    public BlockEntityType<T> getBlockEntity() {
        return supplier.get();
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return getBlockEntity().create(pPos, pState);
    }

    @Override
    public List<ItemStack> getDrops(BlockState pState, LootContext.Builder pBuilder) {
        return Collections.singletonList(new ItemStack(this));
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock() && pLevel.getBlockEntity(pPos) instanceof BasicMenuBlockEntity kk) {
            for (ItemStack drop : kk.drops()) {
                Containers.dropItemStack(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), drop);
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Nullable
    @Override
    public <F extends BlockEntity> BlockEntityTicker<F> getTicker(Level pLevel, BlockState pState, BlockEntityType<F> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, getBlockEntity(), BasicBlockEntity::tick);
    }
}
