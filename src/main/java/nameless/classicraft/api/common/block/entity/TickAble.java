package nameless.classicraft.api.common.block.entity;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;

/**
 * @author DustW
 */
public interface TickAble {
    void tick();

    static <T extends BlockEntity> BlockEntityTicker<T> ticker() {
        return (pLevel, pPos, pState, pBlockEntity) -> ((TickAble) pBlockEntity).tick();
    }
}
