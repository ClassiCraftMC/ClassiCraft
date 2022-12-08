package nameless.classicraft.event;

import nameless.classicraft.api.san.ISanHandler;
import nameless.classicraft.util.SanUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class ItemEvent {

    @SubscribeEvent
    public static void addTooltip(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        List<Component> toolTip = event.getToolTip();
        Item food = itemStack.getItem();
        FoodProperties foodData = food.getFoodProperties();
        if (itemStack.isEdible()) {
            if (!Screen.hasShiftDown()) {
                toolTip.add(Component.translatable("info.classicraft.shift_press").withStyle(ChatFormatting.ITALIC));
            } else {
                if (foodData != null) {
                    int nutrition = foodData.getNutrition();
                    float satur = foodData.getSaturationModifier();
                    toolTip.add(Component.translatable("info.classicraft.food.nutrition" + ":" + nutrition).withStyle(ChatFormatting.WHITE));
                    toolTip.add(Component.translatable("info.classicraft.food.saturation" + ":" + satur).withStyle(ChatFormatting.WHITE));
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void fireBow(LivingEntityUseItemEvent event) {
        LivingEntity entity = event.getEntity();
        ItemStack itemStack = event.getItem();
        if (entity instanceof Player) {
            if (SanUtils.checkSanExist()
                    && itemStack.is(Items.BOW)) {
                if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, itemStack) > 0) {
                    ((ISanHandler) entity).reduceSan(1.0F);
                }
            }
        }
    }
}
