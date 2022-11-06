package nameless.classicraft.api.rot;

import net.minecraft.world.item.ItemStack;

public interface DampReduceListener {
    float onDampReduce(ItemStack rotItem, float originRotValue);
}