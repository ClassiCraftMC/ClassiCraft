package nameless.classicraft.api.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemStackAPI {

    public static boolean doItemStacksMatchIgnoreNBT(ItemStack stack1, ItemStack stack2) {
        return stack1.is(stack2.getItem()) && stack1.getCount() == stack2.getCount();
    }

    public static ItemStack replaceItemWithCopyNBTTagAndCount(ItemStack oldStack, Item newItem)
    {
        CompoundTag compoundTag = oldStack.getOrCreateTag();
        ItemStack itemStack = new ItemStack(newItem);
        itemStack.setTag(compoundTag);
        itemStack.setCount(oldStack.getCount());
        return itemStack;
    }

    public static ItemStack replaceItemWitchNoNBT(ItemStack oldStack, Item newItem)
    {
        ItemStack itemStack = new ItemStack(newItem);
        itemStack.setCount(oldStack.getCount());
        return itemStack;
    }

    public static ItemStack replaceItemWithCopyNBTTagAndCountButResetBurnTime(ItemStack oldStack, Item newItem,int burnTime)
    {
        CompoundTag compoundTag = oldStack.getOrCreateTag();
        ItemStack itemStack = new ItemStack(newItem);
        itemStack.setTag(compoundTag);
        itemStack.setCount(oldStack.getCount());
        itemStack.getTag().putInt("burnTime",burnTime);
        return itemStack;
    }
}
