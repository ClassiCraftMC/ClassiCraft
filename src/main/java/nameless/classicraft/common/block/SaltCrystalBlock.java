package nameless.classicraft.common.block;

import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class SaltCrystalBlock extends PointedDripstoneBlock {

    public SaltCrystalBlock() {
        super(BlockBehaviour.Properties.of(Material.STONE));
    }

}
