package org.bedracket.classicraft.event;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import org.bedracket.classicraft.init.ModItems;

public class ItemEventsFabric {

    public static void registerItemEvents() {
        ItemTooltipCallback.EVENT.register((stack, context, lines) -> {
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
            addPebbleTooltip(ModItems.ANDESITE_PEBBLE.get());
            addPebbleTooltip(ModItems.COBBLESTONE_PEBBLE.get());
            addPebbleTooltip(ModItems.DIORTIE_PEBBLE.get());
            addPebbleTooltip(ModItems.GRANITE_PEBBLE.get());
            addPebbleTooltip(ModItems.RED_SANDSTONE_PEBBLE.get());
            addPebbleTooltip(ModItems.SANDSTONE_PEBBLE.get());
        });
    }

    public static void addPebbleTooltip(Item pebble) {
        ItemTooltipCallback.EVENT.register((stack, context, lines) -> {
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
        });
    }
}
