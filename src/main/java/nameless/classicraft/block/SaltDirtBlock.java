package nameless.classicraft.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RootedDirtBlock;

public class SaltDirtBlock extends RootedDirtBlock {

    public SaltDirtBlock() {
        super(Properties.copy(Blocks.GRASS_BLOCK));
    }
}
