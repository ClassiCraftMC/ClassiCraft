package nameless.classicraft.mixin.event;

import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BaseEntityBlock.class)
public abstract class MixinEntityBlock extends Block implements EntityBlock {

    public MixinEntityBlock(Properties pProperties) {
        super(pProperties);
    }
}
