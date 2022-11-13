package nameless.classicraft;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.StringUtil;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ClassiCraftHooks {

    public static void addFoodComponentEffectTooltip(ItemStack stack, List<Component> tooltip) {
        FoodProperties foodComponent = stack.getItem().getFoodProperties();
        if (foodComponent != null) {
            buildFoodEffectTooltip(tooltip, foodComponent.getEffects());
        }
    }

    public static void buildFoodEffectTooltip(List<Component> tooltip, List<Pair<MobEffectInstance, Float>> effectsWithChance) {
        if (!effectsWithChance.isEmpty()) {
            return;
        }
        List<Pair<Attribute, AttributeModifier>> modifiersList = Lists.newArrayList();
        MutableComponent translatableText;
        MobEffect statusEffect;
        for(Iterator<Pair<MobEffectInstance, Float>> var5 = effectsWithChance.iterator(); var5.hasNext(); tooltip.add(translatableText.withStyle(statusEffect.getCategory().getTooltipFormatting()))) {
            Pair<MobEffectInstance, Float> entry = var5.next();
            MobEffectInstance statusEffectInstance = entry.getFirst();
            Float chance = entry.getSecond();

            translatableText = Component.translatable(statusEffectInstance.getDescriptionId());
            statusEffect = statusEffectInstance.getEffect();
            Map<Attribute, AttributeModifier> map = statusEffect.getAttributeModifiers();
            if (!map.isEmpty()) {
                for (Map.Entry<Attribute, AttributeModifier> entityAttributeEntityAttributeModifierEntry : map.entrySet()) {
                    AttributeModifier entityAttributeModifier = entityAttributeEntityAttributeModifierEntry.getValue();
                    AttributeModifier entityAttributeModifier2 = new AttributeModifier(entityAttributeModifier.getName(), statusEffect.getAttributeModifierValue(statusEffectInstance.getAmplifier(), entityAttributeModifier), entityAttributeModifier.getOperation());
                    modifiersList.add(new Pair<>(entityAttributeEntityAttributeModifierEntry.getKey(), entityAttributeModifier2));
                }
            }

            if (statusEffectInstance.getAmplifier() > 0) {
                translatableText = Component.translatable("potion.withAmplifier", translatableText, Component.translatable("potion.potency." + statusEffectInstance.getAmplifier()));
            }
            if (statusEffectInstance.getDuration() > 20) {
                translatableText = Component.translatable("potion.withDuration", translatableText, StringUtil.formatTickDuration(statusEffectInstance.getDuration()));
            }
            if(chance < 1.0F) {
                translatableText = Component.translatable("foodeffecttooltips.food.withChance", translatableText, Math.round(chance * 100));
            }
        }

        if (!modifiersList.isEmpty()) {
            tooltip.add(CommonComponents.EMPTY);
            tooltip.add((Component.translatable("foodeffecttooltips.food.whenEaten")).withStyle(ChatFormatting.DARK_PURPLE));

            for (Pair<Attribute, AttributeModifier> entityAttributeEntityAttributeModifierPair : modifiersList) {
                AttributeModifier entityAttributeModifier3 = entityAttributeEntityAttributeModifierPair.getSecond();
                double d = entityAttributeModifier3.getAmount();
                double e;
                if (entityAttributeModifier3.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE && entityAttributeModifier3.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL) {
                    e = entityAttributeModifier3.getAmount();
                } else {
                    e = entityAttributeModifier3.getAmount() * 100.0D;
                }

                if (d > 0.0D) {
                    tooltip.add((Component.translatable("attribute.modifier.plus." + entityAttributeModifier3.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(e), Component.translatable((entityAttributeEntityAttributeModifierPair.getFirst()).getDescriptionId()))).withStyle(ChatFormatting.BLUE));
                } else if (d < 0.0D) {
                    e *= -1.0D;
                    tooltip.add((Component.translatable("attribute.modifier.take." + entityAttributeModifier3.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(e), Component.translatable((entityAttributeEntityAttributeModifierPair.getFirst()).getDescriptionId()))).withStyle(ChatFormatting.RED));
                }
            }
        }
    }

    public static void handleWoodenItemBurnTime(FurnaceFuelBurnTimeEvent event, Item item) {
        int woodenItemBurnTime = ClassiCraftConfiguration.woodenItemBurnTime.get();
        ItemStack itemstack = event.getItemStack();
        if (itemstack.getItem() == item)
            event.setBurnTime(woodenItemBurnTime);
    }

    @SuppressWarnings("deprecation")
    public static void handleFood() {
        if (Items.DRIED_KELP.getFoodProperties() != null) {
            Items.DRIED_KELP.getFoodProperties().canAlwaysEat = true;
            Items.DRIED_KELP.getFoodProperties().nutrition = 0;
            Items.DRIED_KELP.getFoodProperties().saturationModifier = 0.0F;
        }
        if (Items.CHORUS_FRUIT.getFoodProperties() != null) {
            Items.CHORUS_FRUIT.getFoodProperties().canAlwaysEat = true;
            Items.CHORUS_FRUIT.getFoodProperties().nutrition = 0;
            Items.CHORUS_FRUIT.getFoodProperties().saturationModifier = 0.0F;
        }
        if (Items.ROTTEN_FLESH.getFoodProperties() != null) {
            Items.ROTTEN_FLESH.getFoodProperties().canAlwaysEat = true;
            Items.ROTTEN_FLESH.getFoodProperties().nutrition = 0;
            Items.ROTTEN_FLESH.getFoodProperties().saturationModifier = 0.0F;
        }
    }

}
