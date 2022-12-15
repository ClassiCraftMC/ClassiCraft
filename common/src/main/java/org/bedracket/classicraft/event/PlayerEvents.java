package org.bedracket.classicraft.event;

import net.minecraft.world.item.Items;
import org.bedracket.classicraft.init.ModBlocks;
import org.bedracket.classicraft.init.ModItems;
import org.bedracket.classicraft.util.EventUtils;

public class PlayerEvents {

    public static void registerPlayerEvents() {
        EventUtils.litItem(ModBlocks.REAL_TORCH.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(ModBlocks.REAL_SOUL_TORCH.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(ModBlocks.REAL_WALL_TORCH.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(ModBlocks.REAL_SOUL_WALL_TORCH.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(ModBlocks.REAL_TORCH.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(ModBlocks.REAL_SOUL_TORCH.get(), ModItems.SOUL_TORCH_UNLIT.get(), ModItems.SOUL_TORCH_LIT.get());
        EventUtils.litItem(ModBlocks.REAL_WALL_TORCH.get(), ModItems.SOUL_TORCH_UNLIT.get(), ModItems.SOUL_TORCH_LIT.get());
        EventUtils.litItem(ModBlocks.REAL_SOUL_WALL_TORCH.get(), ModItems.SOUL_TORCH_UNLIT.get(), ModItems.SOUL_TORCH_LIT.get());
        EventUtils.litBlock(ModBlocks.REAL_TORCH.get(), Items.SOUL_SOIL, ModBlocks.REAL_SOUL_TORCH.get());
        EventUtils.shiftRightItem(ModItems.TORCH_LIT.get(), Items.STICK.getDefaultInstance());
        EventUtils.shiftRightItem(ModItems.SOUL_TORCH_LIT.get(), Items.STICK.getDefaultInstance());
        /**
         EventUtils.litItem(event, Items.FLINT_AND_STEEL, ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
         EventUtils.litItem(event, Items.FLINT_AND_STEEL, ModItems.SOUL_TORCH_UNLIT.get(), ModItems.SOUL_TORCH_LIT.get());
         */
        EventUtils.litItem(ModItems.TORCH_LIT.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(ModItems.SOUL_TORCH_LIT.get(), ModItems.SOUL_TORCH_UNLIT.get(), ModItems.SOUL_TORCH_LIT.get());
        EventUtils.litItem(ModItems.SOUL_TORCH_LIT.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(ModItems.TORCH_LIT.get(), ModItems.SOUL_TORCH_UNLIT.get(), ModItems.SOUL_TORCH_LIT.get());
        EventUtils.litItem(Items.SOUL_SOIL.asItem(), ModItems.TORCH_UNLIT.get(), ModItems.SOUL_TORCH_UNLIT.get());
        EventUtils.litItem(Items.SOUL_SOIL.asItem(), ModItems.TORCH_LIT.get(), ModItems.SOUL_TORCH_LIT.get());
    }
}
