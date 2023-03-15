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
import nameless.classicraft.block.CactusBallBlock;
import nameless.classicraft.compat.WorldEditCompat;
import nameless.classicraft.init.*;
import nameless.classicraft.util.EventUtils;
import nameless.classicraft.util.Helpers;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DeadBushBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ClassiCraftMod.MOD_ID)
public class BlockEvents {

    @SubscribeEvent
    public static void onVineBreak(BlockEvent.BreakEvent event) {
        var state = event.getState();
        var level = (Level) event.getLevel();
        var pos = event.getPos();
        var player = event.getPlayer();
        ItemStack stack;
        if (!player.getAbilities().instabuild) {
            if (state.is(Blocks.VINE)) {
                stack = Helpers.stack(Items.VINE);
                level.addFreshEntity(Helpers.itemEntity(level, pos, stack));
            }
            if (state.is(Blocks.TWISTING_VINES)) {
                stack = Helpers.stack(Items.TWISTING_VINES);
                level.addFreshEntity(Helpers.itemEntity(level, pos, stack));
            }
            if (state.is(Blocks.WEEPING_VINES)) {
                stack = Helpers.stack(Items.WEEPING_VINES);
                level.addFreshEntity(Helpers.itemEntity(level, pos, stack));
            }
        }
    }

    @SubscribeEvent
    public static void harvestCheck(PlayerEvent.HarvestCheck event) {
        var state = event.getTargetBlock();
        var player = event.getEntity();
        if (ModConfigurations.noBlockDropsWithoutTools.get()) {
            if (!(player.getMainHandItem().getItem() instanceof AxeItem)) {
                if (state.is(BlockTags.LOGS) || state.getMaterial() == Material.WOOD) {
                    event.setCanHarvest(false);
                    event.setResult(Event.Result.DENY);
                }
            }
            if (state.getBlock() instanceof CactusBallBlock) {
                event.setCanHarvest(player.getMainHandItem().getItem() instanceof ShearsItem);
            }
        }
    }

    @SubscribeEvent
    public static void mossyAll(PlayerInteractEvent.RightClickBlock event) {
        var level = event.getLevel();
        var pos = event.getPos();
        var itemstack = event.getItemStack();
        var player = event.getEntity();
        var state =level.getBlockState(pos);
        if (itemstack.is(ModItems.MOSS_CLUMP.get())) {
            event.setCancellationResult(ModBlockProperties.getMossed(state).map((mossed) -> {
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, pos, itemstack);
                }

                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }
                level.setBlock(pos, mossed, 11);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, mossed));
                ParticleUtils.spawnParticlesOnBlockFaces(level, pos,
                        ParticleTypes.HAPPY_VILLAGER, UniformInt.of(3, 5));
                level.playSound(null, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS,1, level.random.nextFloat() * 0.1F + 0.9F);
                return InteractionResult.sidedSuccess(level.isClientSide);
            }).orElse(InteractionResult.PASS));
        }
        if (itemstack.is(Tags.Items.TOOLS_AXES) && WorldEditCompat.compatWE(itemstack)) {
            event.setCancellationResult(ModBlockProperties.getMossedOFF(state).map((mossedOff) -> {
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, pos, itemstack);
                }
                level.setBlock(pos, mossedOff, 11);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, mossedOff));
                ParticleUtils.spawnParticlesOnBlockFaces(level, pos,
                        ParticleTypes.WAX_OFF, UniformInt.of(3, 5));
                level.playSound(null, pos, ModSounds.MOSSY_OFF.get(), SoundSource.BLOCKS,1, level.random.nextFloat() * 0.1F + 0.9F);
                return InteractionResult.sidedSuccess(level.isClientSide);
            }).orElse(InteractionResult.PASS));
        }
    }

    @SubscribeEvent
    public static void pebbleTool(PlayerInteractEvent.RightClickBlock event) {
        EventUtils.pebbleToolByStoneVanilla(event, Items.FLINT);
        EventUtils.pebbleToolByStoneVanilla(event, Items.PRISMARINE_SHARD);
        EventUtils.pebbleToolByStoneVanilla(event, Items.QUARTZ);
    }

    @SubscribeEvent
    public static void putAddonVanilla(PlayerInteractEvent.RightClickBlock event) {
        EventUtils.putAddonBlock(event, Items.STICK, ModBlocks.TWIGS.get());
        EventUtils.putAddonBlock(event, Items.FLINT, ModBlocks.FLINT.get());
        EventUtils.putAddonBlock(event, Items.PRISMARINE_SHARD, ModBlocks.PRISMARINE.get());
        EventUtils.putAddonBlock(event, Items.QUARTZ, ModBlocks.QUARTZ_PEBBLE.get());
    }

    @SubscribeEvent
    public static void blockDropItems(BlockEvent.BreakEvent event) {
        var block = event.getState().getBlock();
        if (!event.getPlayer().isCreative()) {
            if (block instanceof LeavesBlock
                    || block instanceof DeadBushBlock) {
                if (Math.random() <= 0.7) {
                    EventUtils.blockDropRandom(event, block, Items.STICK);
                }
            }
        }
    }
}
