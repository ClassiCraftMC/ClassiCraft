package org.bedracket.classicraft.event;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.bedracket.classicraft.init.ModItems;

import java.util.List;

@Mod.EventBusSubscriber
public class ItemEventsForge {

    public static void addToolTip(ItemTooltipEvent event) {
        List<Component> lines = event.getToolTip();
        ItemStack stack = event.getItemStack();
        FoodProperties foodData = stack.getItem().getFoodProperties();
        if (stack.isEdible()) {
            if (!Screen.hasShiftDown()) {
                lines.add(Component.translatable("info.classicraft.shift_press").withStyle(ChatFormatting.ITALIC));
            } else {
                if (foodData != null) {
                    int nutrition = foodData.getNutrition();
                    float satur = foodData.getSaturationModifier();
                    lines.add(Component.translatable("info.classicraft.food.nutrition").append(":" + nutrition).withStyle(ChatFormatting.WHITE));
                    lines.add(Component.translatable("info.classicraft.food.saturation").append(":" + satur).withStyle(ChatFormatting.WHITE));
                }
            }
        }
        addPebbleTooltip(event, ModItems.ANDESITE_PEBBLE.get());
        addPebbleTooltip(event, ModItems.COBBLESTONE_PEBBLE.get());
        addPebbleTooltip(event, ModItems.DIORTIE_PEBBLE.get());
        addPebbleTooltip(event, ModItems.GRANITE_PEBBLE.get());
        addPebbleTooltip(event, ModItems.RED_SANDSTONE_PEBBLE.get());
        addPebbleTooltip(event, ModItems.SANDSTONE_PEBBLE.get());
    }

    public static void addPebbleTooltip(ItemTooltipEvent event, Item pebble) {
        ItemStack stack = event.getItemStack();
        List<Component> lines = event.getToolTip();
        if (stack.getTag() != null) {
            if (stack.is(pebble)) {
                if (stack.getTag().contains("classsicraft:stone_heap", 1)) {
                    lines.add(Component.translatable("info.classicraft,pebble_1"));
                }
                if (stack.getTag().contains("classsicraft:stone_heap", 2)) {
                    lines.add(Component.translatable("info.classicraft,pebble_2"));
                }
                if (stack.getTag().contains("classsicraft:stone_heap", 3)) {
                    lines.add(Component.translatable("info.classicraft,pebble_3"));
                }
                if (stack.getTag().contains("classsicraft:stone_heap", 4)) {
                    lines.add(Component.translatable("info.classicraft,pebble_4"));
                }
                if (stack.getTag().contains("classsicraft:stone_heap", 5)) {
                    lines.add(Component.translatable("info.classicraft,pebble_5"));
                }
                if (stack.getTag().contains("classsicraft:stone_heap", 6)) {
                    lines.add(Component.translatable("info.classicraft,pebble_6"));
                }
            }
        }
    }
}
