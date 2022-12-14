package org.bedracket.classicraft.event;

import net.minecraft.world.item.Items;
import org.bedracket.classicraft.init.ModItems;
import org.bedracket.classicraft.util.EventUtils;
import org.bedracket.event.entity.item.ItemTickEvent;
import org.bedracket.eventbus.EventHandler;
import org.bedracket.eventbus.Listener;

public class ItemTickEvents implements Listener {

    @EventHandler
    public static void onItemTicking(ItemTickEvent event) {
        EventUtils.tickItemToUnlit(event, ModItems.TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemToUnlit(event, ModItems.SOUL_TORCH_LIT.get(), Items.STICK);
    }

    @EventHandler
    public static void onTorchInRaining(ItemTickEvent event) {
        EventUtils.tickItemInRaining(event, ModItems.TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemInRaining(event, ModItems.SOUL_TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemInRaining(event, ModItems.TORCH_UNLIT.get(), Items.STICK);
        EventUtils.tickItemInRaining(event, ModItems.SOUL_TORCH_UNLIT.get(), Items.STICK);
    }

    @EventHandler
    public static void onTorchInWater(ItemTickEvent event) {
        EventUtils.tickItemInWater(event, ModItems.TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemInWater(event, ModItems.SOUL_TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemInWater(event, ModItems.TORCH_UNLIT.get(), Items.STICK);
        EventUtils.tickItemInWater(event, ModItems.SOUL_TORCH_UNLIT.get(), Items.STICK);
    }
}
