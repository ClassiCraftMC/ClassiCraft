package nameless.classicraft.api.block;

import nameless.classicraft.api.block.entity.DampAbleBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

/**
 * @author Thaumstrial
 *
 */
public interface DampBlock {
    default DampAbleBlockEntity getBlockEntity(Level level, BlockPos pos) {
        return (DampAbleBlockEntity) level.getBlockEntity(pos);
    }

    default boolean needDropSelf() {
        return false;
    }
}
