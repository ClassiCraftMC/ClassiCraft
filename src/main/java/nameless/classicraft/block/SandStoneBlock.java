package nameless.classicraft.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class SandStoneBlock extends Block {

    public SandStoneBlock() {
        super(BlockBehaviour.Properties.of(Material.STONE,
                MaterialColor.COLOR_ORANGE).requiresCorrectToolForDrops()
                .strength(0.8F));
    }
}
