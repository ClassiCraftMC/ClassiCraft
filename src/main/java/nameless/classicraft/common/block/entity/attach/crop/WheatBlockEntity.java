package nameless.classicraft.common.block.entity.attach.crop;

import nameless.classicraft.common.block.entity.ModBlockEntities;
import nameless.classicraft.common.block.entity.RotAbleCropBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @author DustW
 */
public class WheatBlockEntity extends RotAbleCropBlockEntity {
    public WheatBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.WHEAT.get(), pWorldPosition, pBlockState);
    }
}
