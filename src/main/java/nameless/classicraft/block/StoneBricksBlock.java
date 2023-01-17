package nameless.classicraft.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class StoneBricksBlock extends Block {

    public StoneBricksBlock() {
        super(BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(1.5F, 6.0F));
    }
}
