package nameless.classicraft.block.entity;

import nameless.classicraft.init.ModBlockEntities;
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
