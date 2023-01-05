package nameless.classicraft.block;

import nameless.classicraft.util.BlockUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class TwigsBlock extends AbstractPickableBlock {
    public TwigsBlock() {
        super(Properties.of(BlockUtils.from(Material.DIRT).noCollider().build())
                .sound(SoundType.WOOD).strength(0.15f).noCollission().noOcclusion());
    }

    @Override
    ItemStack getItem() {
        return new ItemStack(Items.STICK);
    }
}
