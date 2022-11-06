package nameless.classicraft.api.block.entity;

import nameless.classicraft.util.ContainerUtils;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

/**
 * @author DustW
 */
public interface ItemStackContainer extends Container {
    ItemStackHandler getHandler();

    @Override
    default int getContainerSize() {
        return getHandler().getSlots();
    }

    @Override
    default boolean isEmpty() {
        return ContainerUtils.getItems(getHandler()).isEmpty();
    }

    @Override
    default ItemStack getItem(int pSlot) {
        return getHandler().getStackInSlot(pSlot);
    }

    @Override
    default ItemStack removeItem(int pSlot, int pAmount) {
        return getHandler().extractItem(pSlot, pAmount, false);
    }

    @Override
    default void setItem(int pSlot, ItemStack pStack) {
        getHandler().insertItem(pSlot, pStack, false);
    }

    @Override
    default ItemStack removeItemNoUpdate(int pSlot) {
        if (pSlot < getHandler().getSlots()) {
            ItemStack result = getHandler().getStackInSlot(pSlot);
            getHandler().setStackInSlot(pSlot, ItemStack.EMPTY);
            return result;
        }

        return ItemStack.EMPTY;
    }

    @Override
    default boolean stillValid(Player pPlayer) {
        return true;
    }

    @Override
    default void clearContent() {
        ContainerUtils.clearHandlers(getHandler());
    }
}
