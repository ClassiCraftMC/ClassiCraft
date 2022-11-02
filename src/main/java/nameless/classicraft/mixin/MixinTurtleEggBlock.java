package nameless.classicraft.mixin;

import nameless.classicraft.api.common.block.entity.RotAbleEntityBlock;
import nameless.classicraft.common.block.entity.ModBlockEntities;
import net.minecraft.world.level.block.TurtleEggBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author DustW
 */
@Mixin(TurtleEggBlock.class)
public class MixinTurtleEggBlock implements RotAbleEntityBlock {
    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntities.TURTLE_EGG.get();
    }
}
