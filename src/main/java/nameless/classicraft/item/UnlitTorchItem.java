package nameless.classicraft.item;

import nameless.classicraft.init.ModBlocks;
import net.minecraft.world.item.StandingAndWallBlockItem;

public class UnlitTorchItem extends StandingAndWallBlockItem {

    public UnlitTorchItem(Properties pProperties) {
        super(ModBlocks.TORCH.get(), ModBlocks.WALL_TORCH.get(), pProperties);
    }
}
