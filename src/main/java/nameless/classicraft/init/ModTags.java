package nameless.classicraft.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {

    public static final TagKey<Item> ATTACH_ITEMS =
            ItemTags.create(new ResourceLocation("classicraft:attach_items"));
}
