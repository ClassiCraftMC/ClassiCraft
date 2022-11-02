package nameless.classicraft.common.block.entity.attach.crop;

import nameless.classicraft.common.block.entity.ModBlockEntities;
import nameless.classicraft.common.block.entity.RotAbleCropBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @author DustW
 */
public class CarrotBlockEntity extends RotAbleCropBlockEntity {
    public CarrotBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.CARROT.get(), pWorldPosition, pBlockState);
    }
}
