package nameless.classicraft.mixin;

import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.GravelBlock;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GravelBlock.class)
public class TestMixin extends FallingBlock {

    public TestMixin(Properties pProperties) {
        super(pProperties);
    }

}
