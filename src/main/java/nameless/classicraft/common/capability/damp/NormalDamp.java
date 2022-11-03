package nameless.classicraft.common.capability.damp;

import nameless.classicraft.common.damp.DampHolder;
import nameless.classicraft.common.damp.DampItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class NormalDamp extends AbstractDamp {

    ItemStack damp_item;

    public NormalDamp(ItemStack damp_item) {
        super(new DampHolder(getSecond(damp_item.getItem()), getSecond(damp_item.getItem())), true, damp ->
                List.of(Component.translatable("info.classicraft.damp", String.format("%.2f", damp.getHolder().getCurrent() / 24000)),
                        Component.translatable("info.classicraft.dampping_speed", (int) (damp.getFinalSpeed() * 100) + "%")), 1);
    }

    public static int getSecond(Item item) {
        return DampItems.MAP.get(item);
    }

    public static boolean canUse(ItemStack stack) {
        return DampItems.MAP.containsKey(stack.getItem());
    }

    @Override
    public boolean equals(Object o) {
        return o == this || o instanceof NormalDamp damp && damp.damp_item.equals(this.damp_item);
    }

    @Override
    public int hashCode() {
        return damp_item.hashCode();
    }
}
