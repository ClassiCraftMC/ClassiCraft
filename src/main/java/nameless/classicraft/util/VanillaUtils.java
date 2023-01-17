package nameless.classicraft.util;

import nameless.classicraft.item.PebbleToolItem;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;

public class VanillaUtils {

    public static void addMeta(ItemStack pebble, RandomSource random) {
        PebbleToolItem tool = PebbleToolItem.TOOLS.get(random.nextInt(PebbleToolItem.TOOLS.size()));
        if (tool.metas.contains(pebble.getDescriptionId())) {

        }
    }
}
