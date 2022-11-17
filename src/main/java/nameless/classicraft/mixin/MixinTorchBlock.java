package nameless.classicraft.mixin;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TorchBlock;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TorchBlock.class)
public class MixinTorchBlock extends Block {

    public MixinTorchBlock(Properties pProperties) {
        super(pProperties);
    }
}
