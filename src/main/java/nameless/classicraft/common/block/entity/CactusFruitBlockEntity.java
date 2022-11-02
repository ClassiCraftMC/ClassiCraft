package nameless.classicraft.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @author DustW
 */
public class CactusFruitBlockEntity extends RotAbleBlockEntity {
    public CactusFruitBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.CACTUS_FRUIT.get(), pWorldPosition, pBlockState);
    }
}
