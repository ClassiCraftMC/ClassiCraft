package nameless.classicraft.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

/**
 * @author wdog5
 */
public class SandStoneBlock extends Block {

    public SandStoneBlock(SoundType soundType, MaterialColor color) {
        super(BlockBehaviour.Properties.of(Material.STONE
                ).sound(soundType)
                .color(color).requiresCorrectToolForDrops()
                .strength(0.8F));
    }
}
