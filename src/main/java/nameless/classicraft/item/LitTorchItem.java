package nameless.classicraft.item;

import nameless.classicraft.init.ModBlocks;
import net.minecraft.world.item.StandingAndWallBlockItem;

public class LitTorchItem extends StandingAndWallBlockItem {

    public LitTorchItem() {
        super(ModBlocks.REAL_TORCH.get(), ModBlocks.REAL_WALL_TORCH.get(), new Properties().stacksTo(64));
    }
}
