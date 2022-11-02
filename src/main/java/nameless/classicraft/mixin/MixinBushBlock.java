package nameless.classicraft.mixin;

import nameless.classicraft.api.common.block.entity.RotAbleEntityBlock;
import nameless.classicraft.common.block.entity.ModBlockEntities;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author DustW
 */
@Mixin(BushBlock.class)
public class MixinBushBlock implements RotAbleEntityBlock {

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntities.BUSH.get();
    }
}
