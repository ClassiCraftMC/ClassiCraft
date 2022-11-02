package nameless.classicraft.common.block.entity.attach;

import nameless.classicraft.common.block.entity.ModBlockEntities;
import nameless.classicraft.common.block.entity.RotAbleBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @author DustW
 */
public class TurtleEggBlockEntity extends RotAbleBlockEntity {
    public TurtleEggBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.TURTLE_EGG.get(), pWorldPosition, pBlockState);
    }
}
