package nameless.classicraft.common.block.entity.attach;

import nameless.classicraft.common.block.entity.ModBlockEntities;
import nameless.classicraft.common.block.entity.RotAbleBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @author DustW
 */
public class PumpkinBlockEntity extends RotAbleBlockEntity {
    public PumpkinBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.PUMPKIN.get(), pWorldPosition, pBlockState);
    }
}
