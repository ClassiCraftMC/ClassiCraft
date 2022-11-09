package nameless.classicraft.item;

import nameless.classicraft.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.Level;

public class RealisticTorchItem extends StandingAndWallBlockItem {

    public RealisticTorchItem(Properties pProperties) {
        super(ModBlocks.TORCH.get(), ModBlocks.WALL_TORCH.get(), pProperties);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        Level level = pEntity.getLevel();
        BlockPos pos = pEntity.getOnPos();
    }
}
