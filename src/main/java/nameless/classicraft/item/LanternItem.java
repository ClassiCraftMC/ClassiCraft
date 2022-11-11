package nameless.classicraft.item;

import nameless.classicraft.init.ModBlocks;
import net.minecraft.world.item.BlockItem;

public class LanternItem extends BlockItem {

    public LanternItem() {
        super(ModBlocks.LANTERN.get(), new Properties().tab(ClassiCraftTab.DECORATION));
    }

    @Override
    public String getDescriptionId() {
        return "item.classicraft.lantern";
    }

}
