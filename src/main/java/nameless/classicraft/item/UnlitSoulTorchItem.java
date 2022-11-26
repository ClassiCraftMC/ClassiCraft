package nameless.classicraft.item;

import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModCreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;

public class UnlitSoulTorchItem extends StandingAndWallBlockItem {

    public UnlitSoulTorchItem() {
        super(ModBlocks.REAL_SOUL_TORCH.get(),
                ModBlocks.REAL_SOUL_WALL_TORCH.get(),
                new Item.Properties().stacksTo(64).tab(ModCreativeModeTabs.COMMON));
    }

    @Override
    public String getDescriptionId() {
        return "item.classicraft.soul_torch_unlit";
    }
}
