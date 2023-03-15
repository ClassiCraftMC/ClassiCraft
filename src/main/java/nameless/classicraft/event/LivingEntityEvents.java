/*
 * ClassiCraft - ClassiCraftMC
 * Copyright (C) 2018-2022.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package nameless.classicraft.event;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModItems;
import nameless.classicraft.item.DaggerItem;
import nameless.classicraft.item.HammerItem;
import nameless.classicraft.util.Helpers;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ClassiCraftMod.MOD_ID)
public class LivingEntityEvents {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onLivingKnockBack(LivingKnockBackEvent event) {
        var hurtMob = event.getEntity();
        var attacker = hurtMob.getLastHurtByMob();
        if (attacker != null) {
            var heldStack = attacker.getMainHandItem();
            if (heldStack.getItem() instanceof HammerItem) {
                int level = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.KNOCKBACK, heldStack);
                if (level > 0) {
                    event.setStrength(event.getStrength() + 1);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onLivingHurt(LivingHurtEvent event) {
        var hurtMob = event.getEntity();
        var attacker = hurtMob.getLastHurtByMob();
        if (attacker != null) {
            var heldStack = attacker.getMainHandItem();
            if (heldStack.getItem() instanceof HammerItem) {
                hurtMob.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 10, 2));
                hurtMob.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 10, 2));
            }
            if (heldStack.getItem() instanceof DaggerItem) {
                double targetYaw = hurtMob.getYHeadRot() % 360;
                double attackerYaw = attacker.getYHeadRot() % 360;
                if (targetYaw < 0) targetYaw += 360;
                if(Math.abs(targetYaw - attackerYaw) < 45.0 || 360
                        - Math.abs(targetYaw - attackerYaw) < 45.0) {
                    event.setAmount(event.getAmount() * 2);
                    if (attacker instanceof final Player player
                            && !(attacker instanceof FakePlayer)
                            && !attacker.isSpectator()) {
                        player.sendSystemMessage(Component.translatable("info.classicraft.backstab")
                                .append(hurtMob.getName()).append(Component.translatable("info.classicraft.cause"))
                                .append(String.valueOf(event.getAmount() * 2))
                                .append(Component.translatable("info.classicraft.damage")));
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEntityDeath(LivingDropsEvent event) {
        var entity = event.getEntity();
        var level = entity.getLevel();
        var pos = entity.getOnPos();
        var drops = event.getDrops();
        int looting = event.getLootingLevel();
        ItemStack stack;
        if (entity.getType() == EntityType.PIG) {
            stack = Helpers.randomStack(level, ModItems.TALLOW.get(), 0, 1, looting);
            drops.add(Helpers.itemEntity(level, pos, stack));
        }
        if (entity.getType() == EntityType.HOGLIN) {
            stack = Helpers.randomStack(level,
                    ModItems.TALLOW.get(), 1, 2, looting);
            drops.add(Helpers.itemEntity(level, pos, stack));
        }
        if (entity.getType() == EntityType.PIGLIN) {
            stack = Helpers.randomStack(level,
                    ModItems.TALLOW.get(), 1, 2, looting);
            drops.add(Helpers.itemEntity(level, pos, stack));
        }
        if (entity.getType() == EntityType.PIGLIN_BRUTE) {
            stack = Helpers.randomStack(level,
                    ModItems.TALLOW.get(), 1, 2, looting);
            drops.add(Helpers.itemEntity(level, pos, stack));
        }
        if (entity.getType() == EntityType.WITHER) {
            if (Math.random() <= 0.1) {
                stack = Helpers.stack(level,
                        ModItems.PHOBOS_OUTPOST_DISC.get(), 1, looting);
                drops.add(Helpers.itemEntity(level,
                        pos, stack));
            }
        }
        if (entity.getType() == EntityType.ELDER_GUARDIAN) {
            if (Math.random() <= 0.1) {
                stack = Helpers.stack(level,
                        ModItems.DRAGON_FISH_DISC.get(), 1, looting);
                drops.add(Helpers.itemEntity(level,
                        pos, stack));
            }
        }
        if (entity.getType() == EntityType.GHAST) {
            drops.clear();
            stack = Helpers.stack(level,
                    ModItems.PLAINTIVE_SOUL.get(), 1, looting);
            drops.add(Helpers.itemEntity(level,
                    pos, stack));
            stack = Helpers.stack(level, Items.GHAST_TEAR, 1, looting);
            drops.add(Helpers.itemEntity(level, pos, stack));
            if (Math.random() >= 0.025) {
                stack = Helpers.stack(level,
                        ModItems.NITER.get(), 1, looting);
                drops.add(Helpers.itemEntity(level,
                        pos, stack));
            }
            if (Math.random() >= 0.025) {
                stack = Helpers.stack(level,
                        Items.CHARCOAL, 1, looting);
                drops.add(Helpers.itemEntity(level,
                        pos, stack));
            }
            if (Math.random() >= 0.025) {
                stack = Helpers.stack(level,
                        ModItems.SULFUR.get(), 1, looting);
                drops.add(Helpers.itemEntity(level,
                        pos, stack));
            }
        }
        if (entity.getType() == EntityType.ZOMBIE) {
            if (Math.random() <= 0.01) {
                stack = Helpers.stack(level,
                        Items.FEATHER, 1, looting);
                drops.add(Helpers.itemEntity(level,
                        pos, stack));
            }
        }
        if (entity.getType() == EntityType.CREEPER) {
            drops.clear();
            if (Math.random() >= 0.025) {
                stack = Helpers.stack(level,
                        ModItems.NITER.get(), 1, looting);
                drops.add(Helpers.itemEntity(level,
                        pos, stack));
            }
            if (Math.random() >= 0.025) {
                stack = Helpers.stack(level,
                        Items.CHARCOAL, 1, looting);
                drops.add(Helpers.itemEntity(level,
                        pos, stack));
            }
            if (Math.random() >= 0.025) {
                stack = Helpers.stack(level,
                        ModItems.SULFUR.get(), 1, looting);
                drops.add(Helpers.itemEntity(level,
                        pos, stack));
            }
        }
        if (entity.getType() == EntityType.STRAY) {
            drops.clear();
            stack = Helpers.randomStack(level, ModItems.SHIVER_BONE.get(), 1, 2, looting);
            drops.add(Helpers.itemEntity(level,
                    pos, stack));
        }
        if (entity.getType() == EntityType.WITHER_SKELETON) {
            drops.clear();
            stack = Helpers.randomStack(level, ModItems.WITHER_BONE.get(), 1, 2, looting);
            drops.add(Helpers.itemEntity(level,
                    pos, stack));
        }
        if (entity.getType() == EntityType.VEX) {
            drops.clear();
            stack = Helpers.stack(level, ModItems.FURIOUS_SOUL.get(), 1, looting);
            drops.add(Helpers.itemEntity(level,
                    pos, stack));
        }
        if (entity.getType() == EntityType.ALLAY) {
            drops.clear();
            stack = Helpers.stack(level, ModItems.CAREFREE_SOUL.get(), 1, looting);
            drops.add(Helpers.itemEntity(level,
                    pos, stack));
        }
    }
}
