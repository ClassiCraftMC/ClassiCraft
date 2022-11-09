package nameless.classicraft.item;

import nameless.classicraft.init.ModBlocks;
import net.minecraft.world.item.BlockItem;

public class LitLanternItem extends BlockItem {

    public LitLanternItem() {
        super(ModBlocks.LANTERN.get(), new Properties().stacksTo(64));
    }

}
