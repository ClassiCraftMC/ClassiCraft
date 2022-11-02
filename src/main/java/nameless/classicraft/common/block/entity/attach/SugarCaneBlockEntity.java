package nameless.classicraft.common.block.entity.attach;

import nameless.classicraft.common.block.entity.ModBlockEntities;
import nameless.classicraft.common.block.entity.RotAbleBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @author DustW
 */
public class SugarCaneBlockEntity extends RotAbleBlockEntity {
    public SugarCaneBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.SUGAR_CANE.get(), pWorldPosition, pBlockState);
    }

    @Override
    protected void replaceOnRot(Level level) {
        if (!level.getBlockState(getBlockPos().below(1)).is(getBlockState().getBlock()))
            super.replaceOnRot(level);
    }
}
