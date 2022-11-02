package nameless.classicraft.api.common.block;

import nameless.classicraft.api.common.block.entity.DampAbleBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

/**
 * @author Thaumstrial
 */
public interface DampBlock {
    default DampAbleBlockEntity getBlockEntity(Level level, BlockPos pos) {
        return (DampAbleBlockEntity) level.getBlockEntity(pos);
    }

    default boolean needDropSelf() {
        return false;
    }
}
