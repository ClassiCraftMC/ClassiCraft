package nameless.classicraft.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class EventUtils {

    public static void shiftRightTorch(PlayerInteractEvent.RightClickItem event, Item torchItem) {
        Player player = event.getEntity();
        ItemStack itemStack = player.getMainHandItem();
        if (itemStack.is(torchItem) && player.isShiftKeyDown()) {
            ItemStack newItem = new ItemStack(Items.STICK);
            int oldCount = itemStack.getCount();
            player.getInventory().removeItem(itemStack);
            newItem.setCount(oldCount);
            player.getInventory().add(newItem);
        }
    }
}
