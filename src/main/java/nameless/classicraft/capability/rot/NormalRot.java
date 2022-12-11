package nameless.classicraft.capability.rot;

import nameless.classicraft.rot.RotHolder;
import nameless.classicraft.rot.RotItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

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
                List.of(Text.translatable("info.classicraft.rot", String.format("%.2f", rot.getHolder().getCurrent() / 24000)),
                        Text.translatable("info.classicraft.rotting_speed", (int) (rot.getFinalSpeed() * 100) + "%")), 1);

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