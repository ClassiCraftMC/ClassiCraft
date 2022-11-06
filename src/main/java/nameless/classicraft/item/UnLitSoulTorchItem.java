package nameless.classicraft.item;

import nameless.classicraft.init.ModBlocks;
import net.minecraft.world.item.StandingAndWallBlockItem;

public class UnLitSoulTorchItem extends StandingAndWallBlockItem {

    public UnLitSoulTorchItem(Properties pProperties) {
        super(ModBlocks.SOUL_TORCH.get(), ModBlocks.SOUL_WALL_TORCH.get(), pProperties);
    }
}
