package nameless.classicraft.common.block.entity.attach;

import nameless.classicraft.common.block.entity.ModBlockEntities;
import nameless.classicraft.common.block.entity.RotAbleBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @author DustW
 */
public class CakeBlockEntity extends RotAbleBlockEntity {

    public CakeBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.CAKE.get(), pWorldPosition, pBlockState);
    }
}
