package org.bedracket.classicraft.event;

import net.minecraft.world.item.Items;
import org.bedracket.classicraft.init.ModItems;
import org.bedracket.classicraft.util.EventUtils;

public class ItemTickEvents {

    public static void registerItemTickEvents() {
        EventUtils.tickItemToUnlit(ModItems.TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemToUnlit(ModItems.SOUL_TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemInRaining(ModItems.TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemInRaining(ModItems.SOUL_TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemInRaining(ModItems.TORCH_UNLIT.get(), Items.STICK);
        EventUtils.tickItemInRaining(ModItems.SOUL_TORCH_UNLIT.get(), Items.STICK);
        EventUtils.tickItemInWater(ModItems.TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemInWater(ModItems.SOUL_TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemInWater(ModItems.TORCH_UNLIT.get(), Items.STICK);
        EventUtils.tickItemInWater(ModItems.SOUL_TORCH_UNLIT.get(), Items.STICK);
    }
}
