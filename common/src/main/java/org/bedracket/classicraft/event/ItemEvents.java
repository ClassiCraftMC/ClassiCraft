package org.bedracket.classicraft.event;

import dev.architectury.event.events.client.ClientTooltipEvent;
import dev.architectury.platform.Platform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;

public class ItemEvents {

    public static void registerItemEvents() {
        if (Platform.getEnv() == EnvType.CLIENT) {
            registerClient();
        }
    }

    @Environment(EnvType.CLIENT)
    public static void registerClient() {
        ClientTooltipEvent.ITEM.register((stack, lines, flag) -> {
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
        });
    }
}
