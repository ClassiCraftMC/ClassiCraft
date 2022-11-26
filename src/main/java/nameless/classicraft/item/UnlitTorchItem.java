package nameless.classicraft.item;

import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModCreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;

public class UnlitTorchItem extends StandingAndWallBlockItem {

    public UnlitTorchItem() {
        super(ModBlocks.REAL_TORCH.get(),
                ModBlocks.REAL_WALL_TORCH.get(),
                new Item.Properties().stacksTo(64).tab(ModCreativeModeTabs.COMMON));
    }

    @Override
    public String getDescriptionId() {
        return "item.classicraft.torch_unlit";
    }
}
