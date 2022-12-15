package org.bedracket.classicraft.util;

import dev.architectury.event.CompoundEventResult;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.BlockEvent;
import dev.architectury.event.events.common.InteractionEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.bedracket.classicraft.api.event.ItemTickEvent;
import org.bedracket.classicraft.block.AbstractLightBlock;

public class EventUtils {

    public static void litItem(Block litBlock, Item needLit, Item finalItem) {
        InteractionEvent.RIGHT_CLICK_BLOCK.register((player, hand, pos, face) -> {
            BlockState blockState = player.getLevel().getBlockState(pos);
            ItemStack itemStack = player.getMainHandItem();
            if (blockState.is(litBlock)
                    && blockState.getValue(AbstractLightBlock.getLitState())
                    && itemStack.is(needLit)) {
                int oldCount = itemStack.getCount();
                player.getInventory().removeItem(itemStack);
                finalItem.getDefaultInstance().setCount(oldCount);
                player.getInventory().add(finalItem.getDefaultInstance());
            }
            return EventResult.pass();
        });
    }

    public static void litBlock(Block blockChange, Item needItem, Block newBlock) {
        InteractionEvent.RIGHT_CLICK_BLOCK.register((player, hand, pos, face) -> {
            BlockState blockState = player.getLevel().getBlockState(pos);
            ItemStack itemStack = player.getMainHandItem();
            if (blockState.is(blockChange)
                    && itemStack.is(needItem)
                    && blockState.getValue(AbstractLightBlock.getLitState())) {
                itemStack.shrink(1);
                player.getLevel().setBlockAndUpdate(pos, newBlock.defaultBlockState());
                player.getLevel().updateNeighborsAt(pos, newBlock);
            }
            return EventResult.pass();
        });
    }

    public static void shiftRightItem(Item torchItem, ItemStack newItem) {
        InteractionEvent.RIGHT_CLICK_ITEM.register((player, hand) -> {
            ItemStack itemStack = player.getMainHandItem();
            if (itemStack.is(torchItem) && player.isShiftKeyDown()) {
                int oldCount = itemStack.getCount();
                player.getInventory().removeItem(itemStack);
                newItem.setCount(oldCount);
                player.getInventory().add(newItem);
            }
            return CompoundEventResult.pass();
        });
    }

    public static void litItem(Item fireStarter, Item needLit, Item finalItem) {
        InteractionEvent.RIGHT_CLICK_ITEM.register((player, hand) -> {
            if (player != null) {
                ItemStack firstItem = player.getOffhandItem();
                ItemStack itemStack = player.getMainHandItem();
                Level level = player.getLevel();
                BlockPos pos = player.getOnPos();
                if (itemStack.is(fireStarter)
                        && firstItem.is(needLit)
                        && !player.getLevel().isRainingAt(player.getOnPos().above(2))) {
                    level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
                    itemStack.setDamageValue(1);
                    int oldCount = firstItem.getCount();
                    player.getInventory().removeItem(firstItem);
                    finalItem.getDefaultInstance().setCount(oldCount);
                    player.getInventory().add(finalItem.getDefaultInstance());
                }
                if (itemStack.is(needLit)
                        && firstItem.is(fireStarter)
                        && !player.getLevel().isRainingAt(player.getOnPos().above(2))) {
                    level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
                    firstItem.setDamageValue(1);
                    int oldCount = itemStack.getCount();
                    player.getInventory().removeItem(itemStack);
                    finalItem.getDefaultInstance().setCount(oldCount);
                    player.getInventory().add(finalItem.getDefaultInstance());
                }
            }
            return CompoundEventResult.pass();
        });
    }

    public static void blockDropAtOnce(Block dropBlock, Item dropItem) {
        BlockEvent.BREAK.register((level, pos, state, player, xp) -> {
            if (!player.isCreative()) {
                if (state.is(dropBlock)) {
                    ItemEntity itemEntity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), dropItem.getDefaultInstance());
                    level.addFreshEntity(itemEntity);
                }
            }
            return EventResult.pass();
        });
    }

    public static void blockDropRandom(Block dropBlock, Item dropItem, int min, int max) {
        BlockEvent.BREAK.register((level, pos, state, player, xp) -> {
            if (state.is(dropBlock)) {
                ItemStack itemStack = new ItemStack(dropItem, level.getRandom().nextInt(min, max));
                ItemEntity itemEntity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), itemStack);
                level.addFreshEntity(itemEntity);
            }
            return EventResult.pass();
        });
    }

    public static void tickItemInWater(Item tickItem, Item changedItem) {
        ItemTickEvent.ITEM_ENTITY_POST.register(itemEntity -> {
            Level level = itemEntity.getLevel();
            if (itemEntity.getItem().is(tickItem)
                    && itemEntity.isInWater()) {
                int oldCount = itemEntity.getItem().getCount();
                itemEntity.remove(Entity.RemovalReason.KILLED);
                ItemEntity newItem = new ItemEntity(
                        itemEntity.getLevel(),
                        itemEntity.getX(), itemEntity.getY(),
                        itemEntity.getZ(),
                        changedItem.getDefaultInstance());
                newItem.getItem().setCount(oldCount);
                level.addFreshEntity(newItem);
            }
        });
    }

    public static void tickItemInRaining(Item tickItem, Item changedItem) {
        ItemTickEvent.ITEM_ENTITY_POST.register(itemEntity -> {
            Level level = itemEntity.getLevel();
            if (itemEntity.getItem().is(tickItem)
                    && level.isRainingAt(itemEntity.getOnPos().above(2))) {
                int oldCount = itemEntity.getItem().getCount();
                itemEntity.remove(Entity.RemovalReason.KILLED);
                ItemEntity newItem = new ItemEntity(
                        itemEntity.getLevel(),
                        itemEntity.getX(), itemEntity.getY(),
                        itemEntity.getZ(),
                        changedItem.getDefaultInstance());
                newItem.getItem().setCount(oldCount);
                level.addFreshEntity(newItem);
            }
        });
    }

    public static void tickItemToUnlit(Item tickItem, Item changedItem) {
        ItemTickEvent.ITEM_ENTITY_POST.register(itemEntity -> {
            ItemStack itemStack = itemEntity.getItem();
            if (itemStack.is(tickItem)
                    && itemEntity.getAge()
                    == 2 * 1200) {
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
        });
    }
}
