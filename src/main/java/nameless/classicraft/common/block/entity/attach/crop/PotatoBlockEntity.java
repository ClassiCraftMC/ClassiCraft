package nameless.classicraft.common.block.entity.attach.crop;

import lombok.Getter;
import nameless.classicraft.common.block.entity.ModBlockEntities;
import nameless.classicraft.common.block.entity.RotAbleCropBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @author DustW
 */
public class PotatoBlockEntity extends RotAbleCropBlockEntity {
    @Getter
    boolean poison;

    public PotatoBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.POTATO.get(), pWorldPosition, pBlockState);
    }

    @Override
    public void tick() {
        super.tick();

        if (!isMax() && poison) {
            poison = false;
        }
    }

    @Override
    protected void replaceOnRot(Level level) {
        poison = true;
    }
}
