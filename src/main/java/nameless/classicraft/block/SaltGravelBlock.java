package nameless.classicraft.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GravelBlock;

public class SaltGravelBlock extends GravelBlock {

    public SaltGravelBlock() {
        super(Properties.copy(Blocks.GRASS_BLOCK));
    }
}
