package nameless.classicraft.mixin;

import nameless.classicraft.api.common.block.RotBlock;
import nameless.classicraft.api.common.block.entity.TickAble;
import nameless.classicraft.common.block.entity.ModBlockEntities;
import nameless.classicraft.common.block.entity.attach.PumpkinBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.PumpkinBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author DustW
 */
@Mixin(PumpkinBlock.class)
public class MixinPumpkinBlock implements EntityBlock, RotBlock {
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new PumpkinBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pBlockEntityType == ModBlockEntities.PUMPKIN.get() ? TickAble.ticker() : null;
    }

    @Override
    public boolean needDropSelf() {
        return true;
    }
}
