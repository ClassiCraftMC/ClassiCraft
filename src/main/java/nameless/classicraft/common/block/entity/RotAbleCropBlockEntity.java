package nameless.classicraft.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

/**
 * @author DustW
 */
public class RotAbleCropBlockEntity extends RotAbleBlockEntity {
    public RotAbleCropBlockEntity(BlockEntityType<?> pType, BlockPos pWorldPosition, BlockState pBlockState) {
        super(pType, pWorldPosition, pBlockState);
    }

    protected IntegerProperty age() {
        return ((CropBlock) getBlockState().getBlock()).getAgeProperty();
    }

    protected boolean isMax() {
        return ((CropBlock) getBlockState().getBlock()).isMaxAge(getBlockState());
    }

    @Override
    public void tick() {
        if (isMax()) {
            super.tick();
        }
    }

    @Override
    protected void replaceOnRot(Level level) {
        level.setBlock(getBlockPos(), getBlockState().setValue(age(), 0), Block.UPDATE_ALL_IMMEDIATE);

        getHolder().setCurrent(getHolder().getMax());
    }
}
