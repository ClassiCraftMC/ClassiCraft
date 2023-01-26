package nameless.classicraft.block;

import net.minecraft.world.level.block.SlimeBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

/**
 * @author wdog5
 */
public class TallowBlock extends SlimeBlock {

    public TallowBlock() {
        super(BlockBehaviour.Properties
                .of(Material.CLAY, MaterialColor.GRASS)
                .friction(0.8F)
                .sound(SoundType.SLIME_BLOCK).noOcclusion());
    }

    @Override
    public SoundType getSoundType(BlockState pState) {
        return SoundType.SLIME_BLOCK;
    }
}
