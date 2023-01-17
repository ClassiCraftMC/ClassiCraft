package nameless.classicraft.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class StoneBricksBlock extends Block {

    public StoneBricksBlock(SoundType soundType, MaterialColor color) {
        super(BlockBehaviour.Properties
                .of(Material.STONE)
                .color(color)
                .requiresCorrectToolForDrops()
                .sound(soundType)
                .strength(1.5F, 6.0F));
    }
}
