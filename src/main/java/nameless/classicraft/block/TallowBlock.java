package nameless.classicraft.block;

import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;

public class TallowBlock extends SlimeBlock {

    public TallowBlock() {
        super(AbstractBlock.Settings
                .of(Material.ORGANIC_PRODUCT, MapColor.YELLOW)
                .slipperiness(0.8F)
                .sounds(BlockSoundGroup.SLIME).nonOpaque());
    }

    @Override
    public BlockSoundGroup getSoundGroup(BlockState state) {
        return BlockSoundGroup.SLIME;
    }

}
