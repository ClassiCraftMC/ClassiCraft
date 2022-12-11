package nameless.classicraft.block;

import net.minecraft.block.*;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class CharCoalBlock extends Block {

    public CharCoalBlock() {
        super(AbstractBlock.Settings.of(Material.STONE, MapColor.BLACK).requiresTool().strength(5.0F, 6.0F));
    }

    @Override
    public int getFlammability(BlockState state, BlockView level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockView level, BlockPos pos, @Nullable Direction direction) {
        return true;
    }

    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDroppedStacks(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(this, 1));
    }

}
