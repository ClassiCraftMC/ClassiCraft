package nameless.classicraft.api.common.block;

import nameless.classicraft.common.block.entity.RotAbleBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

/**
 * @author DustW
 */
public interface RotBlock {
    default RotAbleBlockEntity getBlockEntity(Level level, BlockPos pos) {
        return (RotAbleBlockEntity) level.getBlockEntity(pos);
    }

    default boolean needDropSelf() {
        return false;
    }
}
