package nameless.classicraft.block.entity;

import nameless.classicraft.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @author DustW
 */
public class GlisteringMelonBlockEntity extends RotAbleBlockEntity {
    public GlisteringMelonBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.GLISTERING_MELON.get(), pWorldPosition, pBlockState);
    }
}
