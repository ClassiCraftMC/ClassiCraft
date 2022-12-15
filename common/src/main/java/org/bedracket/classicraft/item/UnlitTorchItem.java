package org.bedracket.classicraft.item;

import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import org.bedracket.classicraft.init.ModBlocks;
import org.bedracket.classicraft.init.ModCreativeModeTabs;

public class UnlitTorchItem extends StandingAndWallBlockItem {

    public UnlitTorchItem() {
        super(ModBlocks.REAL_TORCH.get(),
                ModBlocks.REAL_WALL_TORCH.get(),
                new Item.Properties().stacksTo(64).arch$tab(ModCreativeModeTabs.COMMON),
                Direction.DOWN);
    }

    @Override
    public String getDescriptionId() {
        return "item.classicraft.torch_unlit";
    }
}
