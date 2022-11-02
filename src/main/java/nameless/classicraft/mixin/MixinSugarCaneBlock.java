package nameless.classicraft.mixin;

import nameless.classicraft.api.common.block.entity.RotAbleEntityBlock;
import nameless.classicraft.common.block.entity.ModBlockEntities;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author DustW
 */
@Mixin(SugarCaneBlock.class)
public class MixinSugarCaneBlock implements RotAbleEntityBlock {

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntities.SUGAR_CANE.get();
    }
}
