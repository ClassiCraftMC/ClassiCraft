package nameless.classicraft.common.block;

import net.minecraft.world.level.block.AmethystBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class SaltRockBlock extends AmethystBlock {

    public SaltRockBlock() {
        super(BlockBehaviour.Properties.of(Material.STONE));
    }
}
