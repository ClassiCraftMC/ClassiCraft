package nameless.classicraft.util;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import nameless.classicraft.api.event.ItemEntityTickEvent;
import nameless.classicraft.api.light.LightAPI;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.StringUtil;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EventUtils {

    public static boolean tryPercentage (double chance) {
        return Math.random() < chance;
    }

    public static void tickingBlockItemNeedFuel(ItemEntityTickEvent event, Item tickItem, Item changedItem) {
        ItemEntity itemEntity = event.getEntity();
        ItemStack itemStack = itemEntity.getItem();
        if (itemStack.is(tickItem)
                && itemEntity.getAge()
                == 4 * 1200) {
            int oldCount = itemEntity.getItem().getCount();
            itemEntity.remove(Entity.RemovalReason.KILLED);
            ItemEntity newItem = new ItemEntity(
                    itemEntity.getLevel(),
                    itemEntity.getX(), itemEntity.getY(),
                    itemEntity.getZ(),
                    changedItem.getDefaultInstance());
            newItem.getItem().setCount(oldCount);
            itemEntity.getLevel().addFreshEntity(newItem);
        }
    }

    public static void rightClickBlockNeedFuel(PlayerInteractEvent.RightClickBlock event, Block clickedBlock) {
        Block block = event.getLevel().getBlockState(event.getPos()).getBlock();
        if (event.getEntity().isShiftKeyDown()) {
            if (block.defaultBlockState().is(clickedBlock)) {
                event.getLevel().setBlockAndUpdate(event.getPos(), clickedBlock.defaultBlockState());
                event.getLevel().updateNeighborsAt(event.getPos(), block);
                LightAPI.playExtinguishSound(event.getLevel(), event.getPos());
            }
        }
    }

    public static void projectileTorchToFire(ProjectileImpactEvent event, Block hitBlock) {
        Projectile projectile = event.getProjectile();
        HitResult result = event.getRayTraceResult();
        Block block = projectile.getBlockStateOn().getBlock();
        BlockState state = block.defaultBlockState();
        if (result.getType() == HitResult.Type.BLOCK
                && projectile.isOnFire()
                && state.is(hitBlock)
                && state.getValue(LightAPI.getLitState())
                != LightAPI.LIT) {
            LightAPI.playLightingSound(projectile.getLevel(), projectile.getOnPos());
            projectile.getLevel().setBlockAndUpdate(projectile.getOnPos(),
                    hitBlock.defaultBlockState()
                            .setValue(LightAPI.getLitState(),2)
                            .setValue(LightAPI.TORCH_BURNTIME,
                                    LightAPI.getTorchInitialBurnTime()));
        }
    }

    public static void extinguishBlockNeedFuelByPotion(ProjectileImpactEvent event, Block potionedBlock, IntegerProperty burnTime, int totalBurnTime) {
        Projectile projectile = event.getProjectile();
        Block block = projectile.getBlockStateOn().getBlock();
        BlockState state = block.defaultBlockState();
        if ((projectile instanceof ThrownPotion && state.is(potionedBlock))) {
            LightAPI.playExtinguishSound(projectile.getLevel(), projectile.getOnPos());
            projectile.getLevel().setBlockAndUpdate(projectile.getOnPos(),
                    potionedBlock.defaultBlockState()
                            .setValue(LightAPI.getLitState(),0)
                            .setValue(burnTime,
                                    totalBurnTime)
                            .setValue(LightAPI.BE_HANGING,
                                    state.getValue(LightAPI.BE_HANGING))
                            .setValue(LightAPI.BE_WATERLOGGED,
                                    state.getValue(LightAPI.BE_WATERLOGGED)));
            projectile.getLevel().updateNeighborsAt(projectile.getOnPos(), block);
        }
    }

    public static void extinguishTorchItemByPotion(ProjectileImpactEvent event, Block potionedBlock) {
        Projectile projectile = event.getProjectile();
        Block block = projectile.getBlockStateOn().getBlock();
        BlockState state = block.defaultBlockState();
        Level level = event.getProjectile().getLevel();
        if (projectile instanceof ThrownPotion
                && state.is(potionedBlock)) {
            LightAPI.playExtinguishSound(projectile.getLevel(), projectile.getOnPos());
            projectile.getLevel().setBlockAndUpdate(projectile.getOnPos(),
                    Blocks.AIR.defaultBlockState());
            ItemEntity newItem = new ItemEntity(
                    projectile.getLevel(),
                    projectile.getX(), projectile.getY(),
                    projectile.getZ(),
                    Items.STICK.getDefaultInstance());
            projectile.getLevel().addFreshEntity(newItem);
            level.updateNeighborsAt(event.getProjectile().getOnPos(), block);
            projectile.gameEvent(GameEvent.BLOCK_CHANGE);
        }
    }

    public static void addFoodComponentEffectTooltip(ItemStack stack, List<Component> tooltip) {
        FoodProperties foodComponent = stack.getItem().getFoodProperties();
        if (foodComponent != null) {
            buildFoodEffectTooltip(tooltip, foodComponent.getEffects());
        }
    }

    public static void buildFoodEffectTooltip(List<Component> tooltip, List<Pair<MobEffectInstance, Float>> effectsWithChance) {
        if (effectsWithChance.isEmpty()) {
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
                translatableText = Component.translatable("classicraft.food.withChance", translatableText, Math.round(chance * 100));
            }
        }

        if (!modifiersList.isEmpty()) {
            tooltip.add(CommonComponents.EMPTY);
            tooltip.add((Component.translatable("classicraft.food.whenEaten")).withStyle(ChatFormatting.DARK_PURPLE));

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

}
