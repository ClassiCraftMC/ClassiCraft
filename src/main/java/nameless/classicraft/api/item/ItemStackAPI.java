package nameless.classicraft.api.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemStackAPI {

    public static ItemStack replaceItemWitchNoNBT(ItemStack oldStack, Item newItem)
    {
        ItemStack itemStack = new ItemStack(newItem);
        itemStack.setCount(oldStack.getCount());
        return itemStack;
    }

}
