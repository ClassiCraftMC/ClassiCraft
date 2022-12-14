package org.bedracket.classicraft.item;

import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import org.bedracket.classicraft.init.ModBlocks;
import org.bedracket.classicraft.init.ModCreativeModeTabs;

public class UnlitSoulTorchItem extends StandingAndWallBlockItem {

    public UnlitSoulTorchItem() {
        super(ModBlocks.REAL_SOUL_TORCH.get(),
                ModBlocks.REAL_SOUL_WALL_TORCH.get(),
                new Item.Properties().stacksTo(64).arch$tab(ModCreativeModeTabs.COMMON),
                Direction.DOWN);
    }

    @Override
    public String getDescriptionId() {
        return "item.classicraft.soul_torch_unlit";
    }
}
