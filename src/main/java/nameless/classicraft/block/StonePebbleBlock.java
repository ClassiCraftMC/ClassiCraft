package nameless.classicraft.block;

import nameless.classicraft.item.PebbleItem;
import nameless.classicraft.util.BlockUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

/**
 * @author wdog5
 */
public class StonePebbleBlock extends AbstractPickableBlock {
    public StonePebbleBlock() {
        super(Properties.of(BlockUtils.from(Material.DIRT).noCollider().build())
                .sound(SoundType.STONE).strength(0.15f).noCollission().noOcclusion());
    }

    @Override
    ItemStack getItem() {
        return PebbleItem.fromBlock(this);
    }
}
