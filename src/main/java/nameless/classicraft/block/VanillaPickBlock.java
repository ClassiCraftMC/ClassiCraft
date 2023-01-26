package nameless.classicraft.block;

import nameless.classicraft.util.BlockUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.HitResult;

/**
 * @author wdog5
 */
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

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return getItem();
    }
}
