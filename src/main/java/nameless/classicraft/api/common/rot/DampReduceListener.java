package nameless.classicraft.api.common.rot;

import net.minecraft.world.item.ItemStack;

public interface DampReduceListener {
    float onDampReduce(ItemStack rotItem, float originRotValue);
}