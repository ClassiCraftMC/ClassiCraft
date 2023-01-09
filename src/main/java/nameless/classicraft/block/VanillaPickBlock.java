package nameless.classicraft.block;

import nameless.classicraft.util.BlockUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class VanillaPickBlock extends AbstractPickableBlock {

    final Item vanillaItem;

    public VanillaPickBlock(Item vanillaItem) {
        super(Properties.of(BlockUtils.from(Material.DIRT).noCollider().build())
                .sound(SoundType.STONE).strength(0.15f).noCollission().noOcclusion());
        this.vanillaItem = vanillaItem;
    }

    @Override
    ItemStack getItem() {
        return vanillaItem.getDefaultInstance();
    }
}
