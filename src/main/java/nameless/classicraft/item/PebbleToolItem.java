package nameless.classicraft.item;

import nameless.classicraft.api.item.MetaItem;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author DustW
 */
public class PebbleToolItem extends MetaItem {
    public static final List<PebbleToolItem> TOOLS = new ArrayList<>();

    public PebbleToolItem(Properties pProperties, List<String> metas) {
        super(pProperties, metas);

        TOOLS.add(this);
    }

    public static ItemStack randomFrom(ItemStack pebble, RandomSource random) {
        PebbleToolItem tool = TOOLS.get(random.nextInt(TOOLS.size()));
        String meta = PebbleItem.getPebbleType(pebble) + "_" + Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(tool)).getPath();

        if (tool.metas.contains(meta)) {
            ItemStack result = new ItemStack(tool);
            setMeta(result, meta);
            return result;
        } else {
            return ItemStack.EMPTY;
        }
    }
}
