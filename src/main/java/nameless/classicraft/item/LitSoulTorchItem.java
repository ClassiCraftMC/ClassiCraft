package nameless.classicraft.item;

import nameless.classicraft.api.light.LitItemAPI;
import nameless.classicraft.init.ModBlocks;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class LitSoulTorchItem extends StandingAndWallBlockItem implements LitItemAPI {

    public LitSoulTorchItem() {
        super(ModBlocks.SOUL_TORCH.get(),ModBlocks.SOUL_WALL_TORCH.get(), new Properties());
    }

    @Override
    public String getDescriptionId() {
        return "item.classicraft.lit_soul_torch";
    }

    @Nullable
    @Override
    protected BlockState getPlacementState(BlockPlaceContext pContext) {
      return getTorchPlacementState(pContext, ModBlocks.SOUL_TORCH.get());
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        inventorySoulTorchTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }


    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return shouldTorchCauseReequipAnimation(oldStack, newStack, slotChanged);
    }
}
