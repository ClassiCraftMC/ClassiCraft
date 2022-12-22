package nameless.classicraft.item;

import nameless.classicraft.init.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;

public class UnlitSoulTorchItem extends StandingAndWallBlockItem {

    public UnlitSoulTorchItem() {
        super(ModBlocks.REAL_SOUL_TORCH.get(),
                ModBlocks.REAL_SOUL_WALL_TORCH.get(),
                new Item.Properties().stacksTo(64),
                Direction.DOWN);
    }

    @Override
    public String getDescriptionId() {
        return "item.classicraft.soul_torch_unlit";
    }
}
