package nameless.classicraft.common.capability.rot;

import nameless.classicraft.common.rot.RotHolder;
import nameless.classicraft.common.rot.RotItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

/**
 * @author DustW
 */
public class NormalRot extends AbstractRot {

    public static int getSecond(Item item) {
        return RotItems.MAP.get(item);
    }

    public static boolean canUse(ItemStack stack) {
        return RotItems.MAP.containsKey(stack.getItem());
    }

    ItemStack food;

    public NormalRot(ItemStack food) {
        super(new RotHolder(getSecond(food.getItem()), getSecond(food.getItem())), true, rot ->
                List.of(Component.translatable("info.classicraft.rot", String.format("%.2f", rot.getHolder().getCurrent() / 24000)),
                        Component.translatable("info.classicraft.rotting_speed", (int) (rot.getFinalSpeed() * 100) + "%")), 1);

        this.food = food;
    }

    @Override
    public boolean equals(Object o) {
        return o == this || o instanceof NormalRot rot && rot.food.equals(this.food);
    }

    @Override
    public int hashCode() {
        return food.hashCode();
    }

}