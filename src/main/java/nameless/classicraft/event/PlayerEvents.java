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

import com.mojang.datafixers.util.Pair;
import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.block.CactusBallBlock;
import nameless.classicraft.block.CattailBlock;
import nameless.classicraft.block.ReedBlock;
import nameless.classicraft.capability.san.SanCapabilityProvider;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModCapabilities;
import nameless.classicraft.init.ModItems;
import nameless.classicraft.util.EventUtils;
import nameless.classicraft.util.Helpers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.EndPortalFrameBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ClassiCraftMod.MOD_ID)
public class PlayerEvents {

    @SubscribeEvent
    public static void onCattailAndReedDrop(PlayerEvent.BreakSpeed event) {
        var state = event.getState();
        var player = event.getEntity();
        var stack = player.getMainHandItem();
        if (!player.getAbilities().instabuild) {
            if (state.getBlock() instanceof CattailBlock || state.getBlock() instanceof ReedBlock) {
                if (stack.getItem() instanceof ShearsItem) {
                    event.setNewSpeed(event.getOriginalSpeed() + (float) 4);
                }
            }
        }
    }

    @SubscribeEvent
    public static void rightEndPortal(PlayerInteractEvent.RightClickBlock event) {
        var level = event.getLevel();
        var pos = event.getPos();
        var state = level.getBlockState(pos);
        var player = event.getEntity();
        var stack = player.getMainHandItem();
        if (state.is(Blocks.END_PORTAL_FRAME)) {
            if (state.getValue(EndPortalFrameBlock.HAS_EYE) && stack.isEmpty()) {
                level.setBlock(pos, Blocks.END_PORTAL_FRAME.defaultBlockState(), 11);
                level.levelEvent(1503, pos, 0);
                level.playSound(null, pos, SoundEvents.END_PORTAL_FRAME_FILL,
                        SoundSource.BLOCKS,1, level.random.nextFloat() * 0.1F + 0.9F);
                player.getInventory().add(new ItemStack(Items.ENDER_EYE));
            }
        }
    }

    @SubscribeEvent
    public static void addCap(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer)) {
            event.addCapability(Helpers.identifier("player_san_level"),
                    new SanCapabilityProvider());
        }
    }

    @SubscribeEvent
    public static void onPlayerJoinLevel(EntityJoinLevelEvent event) {
        var entity = event.getEntity();
        if (entity instanceof final Player player
                && ! (entity instanceof FakePlayer)) {
            player.getCapability(ModCapabilities.PLAYER_SAN_LEVEL).ifPresent(capability -> {
                capability.setSan((float) capability.getMaxSan());
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        var player = event.player;
        if (!(player instanceof FakePlayer)) {
            player.getCapability(ModCapabilities.PLAYER_SAN_LEVEL)
                    .ifPresent(capability -> {
                        if (capability.getSan() < capability.getMaxSan()
                                && player.tickCount % 20 == 0) {
                            capability.regenSan(0.05f);
                        }
                    });
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer && !(event.getEntity() instanceof FakePlayer)) {
            serverPlayer.getCapability(ModCapabilities.PLAYER_SAN_LEVEL).ifPresent(t -> t.setSan((float) t.getMaxSan()));
        }
    }

    @SubscribeEvent
    public static void cactusDigSpeed(PlayerEvent.BreakSpeed event) {
        var state = event.getState();
        var player = event.getEntity();
        var stack = player.getMainHandItem();
        if (stack.getItem() instanceof ShearsItem) {
            if (state.getBlock() instanceof CactusBlock
                    || state.getBlock() instanceof CactusBallBlock) {
                event.setNewSpeed(event.getOriginalSpeed() + (float) 2);
            }
        }
        if (stack.isEmpty() && state.getBlock() instanceof CactusBlock
                || stack.isEmpty() && state.getBlock() instanceof CactusBallBlock) {
            if (!player.getAbilities().instabuild) {
                player.hurt(player.damageSources().cactus(), 1.0F);
            }
        }
        if (stack.getItem() instanceof HoeItem) {
            if (state.getBlock() instanceof CattailBlock
                    || state.getBlock() instanceof ReedBlock) {
                event.setNewSpeed(event.getOriginalSpeed() + (float) 2);
            }
        }
    }

    @SubscribeEvent
    public static void pebbleTool(PlayerInteractEvent.RightClickItem event) {
        EventUtils.pebbleToolByHandVanilla(event, Items.FLINT);
        EventUtils.pebbleToolByHandVanilla(event, Items.PRISMARINE_SHARD);
        EventUtils.pebbleToolByHandVanilla(event, Items.QUARTZ);
    }

    @SubscribeEvent
    public static void rightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        EventUtils.litItem(event, ModBlocks.REAL_TORCH.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(event, ModBlocks.REAL_SOUL_TORCH.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(event, ModBlocks.REAL_WALL_TORCH.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(event, ModBlocks.REAL_SOUL_WALL_TORCH.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(event, ModBlocks.REAL_TORCH.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(event, ModBlocks.REAL_SOUL_TORCH.get(), ModItems.SOUL_TORCH_UNLIT.get(), ModItems.SOUL_TORCH_LIT.get());
        EventUtils.litItem(event, ModBlocks.REAL_WALL_TORCH.get(), ModItems.SOUL_TORCH_UNLIT.get(), ModItems.SOUL_TORCH_LIT.get());
        EventUtils.litItem(event, ModBlocks.REAL_SOUL_WALL_TORCH.get(), ModItems.SOUL_TORCH_UNLIT.get(), ModItems.SOUL_TORCH_LIT.get());
        EventUtils.litBlock(event, ModBlocks.REAL_TORCH.get(), Items.SOUL_SOIL, ModBlocks.REAL_SOUL_TORCH.get());
    }

    @SubscribeEvent
    public static void rightClickItem(PlayerInteractEvent.RightClickItem event) {
        EventUtils.shiftRightItem(event, ModItems.TORCH_LIT.get(), Items.STICK.getDefaultInstance());
        EventUtils.shiftRightItem(event, ModItems.SOUL_TORCH_LIT.get(), Items.STICK.getDefaultInstance());
       /**
        EventUtils.litItem(event, Items.FLINT_AND_STEEL, ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(event, Items.FLINT_AND_STEEL, ModItems.SOUL_TORCH_UNLIT.get(), ModItems.SOUL_TORCH_LIT.get());
        */
        EventUtils.litItem(event, ModItems.TORCH_LIT.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(event, ModItems.SOUL_TORCH_LIT.get(), ModItems.SOUL_TORCH_UNLIT.get(), ModItems.SOUL_TORCH_LIT.get());
        EventUtils.litItem(event, ModItems.SOUL_TORCH_LIT.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(event, ModItems.TORCH_LIT.get(), ModItems.SOUL_TORCH_UNLIT.get(), ModItems.SOUL_TORCH_LIT.get());
        EventUtils.litItem(event, Items.SOUL_SOIL.asItem(), ModItems.TORCH_UNLIT.get(), ModItems.SOUL_TORCH_UNLIT.get());
        EventUtils.litItem(event, Items.SOUL_SOIL.asItem(), ModItems.TORCH_LIT.get(), ModItems.SOUL_TORCH_LIT.get());
    }

    @SubscribeEvent
    public static void onPlayerUsingItem(PlayerInteractEvent.RightClickItem event) {
        var player = event.getEntity();
        var level = player.getLevel();
        var itemStack = player.getItemInHand(player.getUsedItemHand());
        if (itemStack.is(Items.EGG)) {
            if (player.isShiftKeyDown()) {
                float pitch = 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F);
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, pitch);
                if (!level.isClientSide) {
                    ThrownEgg thrownEgg = new ThrownEgg(level, player);
                    thrownEgg.setItem(itemStack);
                    thrownEgg.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
                    level.addFreshEntity(thrownEgg);
                }

                player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }

                player.swing(player.getUsedItemHand());
                event.setCanceled(true);
            } else {
                if (player.getFoodData().needsFood()) {
                    player.startUsingItem(player.getUsedItemHand());
                }
                event.setCanceled(true);
            }
        }
        if (itemStack.is(Items.CAKE)) {
            event.setCanceled(true);
        }
        if (itemStack.is(Items.TURTLE_EGG) && player.isShiftKeyDown()) {
            float pitch = 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F);
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, pitch);
            if (!level.isClientSide) {
                var thrownEgg = new ThrownEgg(level, player) {
                    @Override
                    protected void onHit(HitResult result) {
                        var hitResult$type = result.getType();
                        if (hitResult$type == HitResult.Type.ENTITY) {
                            this.onHitEntity((EntityHitResult)result);
                        } else if (hitResult$type == HitResult.Type.BLOCK) {
                            this.onHitBlock((BlockHitResult)result);
                        }

                        if (hitResult$type != HitResult.Type.MISS) {
                            this.gameEvent(GameEvent.PROJECTILE_LAND, this.getOwner());
                        }

                        if (!this.level.isClientSide) {
                            if (this.random.nextInt(8) == 0) {
                                int i = 1;
                                if (this.random.nextInt(32) == 0) {
                                    i = 4;
                                }

                                for(int j = 0; j < i; ++j) {
                                    var turtle = EntityType.TURTLE.create(this.level);
                                    if (turtle != null) {
                                        turtle.setAge(-24000);
                                        turtle.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
                                        this.level.addFreshEntity(turtle);
                                    }
                                }
                            }

                            this.level.broadcastEntityEvent(this, (byte)3);
                            this.discard();
                        }
                    }
                };
                thrownEgg.setItem(itemStack);
                thrownEgg.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
                level.addFreshEntity(thrownEgg);
            }

            player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
            if (!player.getAbilities().instabuild) {
                itemStack.shrink(1);
            }
            player.swing(player.getUsedItemHand());
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onPlayerEatingFoods(LivingEntityUseItemEvent.Finish event) {
        var itemStack = event.getResultStack();
        var entity = event.getEntity();
        if (entity instanceof Player player) {
            if (itemStack.is(Items.GLISTERING_MELON_SLICE)) {
                player.heal(4.0F);
            } else if (itemStack.is(ModBlocks.GLISTERING_MELON.get().asItem())) {
                player.heal(8.0F);
            } else if (itemStack.is(Items.GOLDEN_CARROT)) {
                entity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 80, 0));
            }
        }

        itemStack.getCapability(ModCapabilities.ROT).ifPresent(rot -> {
            var properties = itemStack.getFoodProperties(entity);
            if (properties != null) {
                if (!entity.level.isClientSide) {
                    for (Pair<MobEffectInstance, Float> pair : properties.getEffects()) {
                        if (pair.getFirst() != null && entity.level.random.nextFloat() < pair.getSecond()){
                            entity.addEffect(new MobEffectInstance(pair.getFirst()));
                        }
                    }

                    int foodLevelModifier;
                    float saturationLevelModifier;
                    if (itemStack.getItem() == Items.ROTTEN_FLESH || itemStack.getItem() == ModItems.ROTTEN_FOOD.get()) {
                        entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 45 * 20, 2));
                        entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 45 * 20, 1));
                        entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 45 * 20, 0));
                        foodLevelModifier = 0;
                        saturationLevelModifier = 0;
                    } else {
                        switch (rot.getLevel()) {
                            case FRESH -> {
                                foodLevelModifier = properties.getNutrition();
                                saturationLevelModifier = properties.getSaturationModifier();
                            }
                            case STALE -> {
                                entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 15 * 20));
                                foodLevelModifier = (int) (properties.getNutrition() * .75);
                                saturationLevelModifier = properties.getSaturationModifier() * .75F;
                            }
                            case SPOILED -> {
                                entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 30 * 20, 1));
                                entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 30 * 20));
                                foodLevelModifier = (int) (properties.getNutrition() * .5);
                                saturationLevelModifier = properties.getSaturationModifier() * .5F;
                            }
                            case ROT -> {
                                entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 45 * 20, 2));
                                entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 45 * 20, 1));
                                entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 45 * 20));
                                foodLevelModifier = (int) (properties.getNutrition() * .25);
                                saturationLevelModifier = properties.getSaturationModifier() * .25F;
                            }
                            default -> {
                                foodLevelModifier = 0;
                                saturationLevelModifier = 0;
                            }
                        }
                    }

                    if (entity instanceof Player player && foodLevelModifier != 0 && saturationLevelModifier != 0) {
                        player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() - properties.getNutrition());
                        player.getFoodData().eat(foodLevelModifier, saturationLevelModifier);
                    }
                }

                entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                        SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, 0.5F,
                        entity.level.random.nextFloat() * 0.1F + 0.9F);

                entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                        entity.getEatingSound(itemStack), SoundSource.NEUTRAL,
                        1.0F, 1.0F + (entity.level.random.nextFloat()
                                - entity.level.random.nextFloat()) * 0.4F);
            }
        });
    }
}
