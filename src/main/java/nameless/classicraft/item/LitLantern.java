package nameless.classicraft.item;

import nameless.classicraft.api.light.LitItemAPI;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class LitLantern extends BlockItem implements LitItemAPI {

    public LitLantern() {
        super(ModBlocks.LANTERN.get() , new Item.Properties());
    }

    @Override
    public String getDescriptionId() {
        return "item.classicraft.lit_lantern";
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        inventoryLanternTick(pStack, pLevel, pEntity, pSlotId, pIsSelected, ModItems.LANTERN.get());
    }

    @Nullable
    @Override
    protected BlockState getPlacementState(BlockPlaceContext pContext) {
        return getLanternPlacementState(pContext, this.getBlock());
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return shouldLanternCauseReequipAnimation(oldStack, newStack, slotChanged);
    }
}
