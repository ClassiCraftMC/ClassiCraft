package nameless.classicraft.util;

import nameless.classicraft.api.event.BlockDropEvent;
import nameless.classicraft.api.event.ItemEntityTickEvent;
import nameless.classicraft.block.AbstractLightBlock;
import nameless.classicraft.item.PebbleItem;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class EventUtils {

    public static void pebbleToolByStoneVanilla(PlayerInteractEvent.RightClickBlock event, Item vanillaItem) {
        Player player = event.getEntity();
        BlockState state = player.getBlockStateOn();
        ItemStack itemStack = event.getItemStack();
        if (itemStack.is(vanillaItem)) {
            if (state.getMaterial() == Material.STONE) {
                if (PebbleItem.addItem(player, vanillaItem.getDefaultInstance()))
                    vanillaItem.getDefaultInstance().shrink(1);
            }
        }
    }

    public static void pebbleToolByHandVanilla(PlayerInteractEvent.RightClickItem event, Item vanillaItem) {
        InteractionHand hand = event.getHand();
        Player player = event.getEntity();
        ItemStack held = event.getItemStack();
        if (held.is(vanillaItem)) {
            if (hand == InteractionHand.MAIN_HAND) {
                ItemStack off = player.getOffhandItem();

                if (held.getItem() == vanillaItem
                        && off.getItem() == vanillaItem
                        && PebbleItem.addItem(player, held)) {
                    held.shrink(1);
                    off.shrink(1);
                }
            }
        }
    }

    public static void putPebbleBlock(PlayerInteractEvent.RightClickBlock event, Item vanillaItem, Block pebbleBlock) {
        Level level = event.getLevel();
        ItemStack itemStack = event.getItemStack();
        BlockPos pos = event.getPos();
        Player player = event.getEntity();
        BlockPos abovePos = pos.above();
        if (itemStack.is(vanillaItem)) {
            BlockState stateUnder = level.getBlockState(pos);
            if (stateUnder.isFaceSturdy(level, pos, Direction.UP)
                    && level.getBlockState(abovePos).getBlock() == Blocks.AIR
                    && level.getBlockState(pos).getMaterial() != Material.STONE) {
                event.getEntity().swing(event.getHand());
                level.setBlockAndUpdate(abovePos, pebbleBlock.defaultBlockState());
                pebbleBlock.setPlacedBy(level, abovePos, pebbleBlock.defaultBlockState(), player, itemStack);
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, pos, itemStack);
                }
                level.gameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Context.of(player, pebbleBlock.defaultBlockState()));
                level.playSound(null, pos, SoundEvents.STONE_PLACE, SoundSource.BLOCKS,1, level.random.nextFloat() * 0.1F + 0.9F);
                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }
                player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
                event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
            }
        }
    }

    public static void blockdropAtOnce(BlockDropEvent event, Block dropBlock, Item dropItem) {
        BlockState blockState = event.getState();
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        if (blockState.is(dropBlock)) {
            ItemEntity itemEntity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), dropItem.getDefaultInstance());
            event.getLevel().addFreshEntity(itemEntity);
        }
    }

    public static void blockdropRandom(BlockDropEvent event, Block dropBlock, Item dropItem) {
        BlockState blockState = event.getState();
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        if (blockState.is(dropBlock)) {
            ItemStack itemStack = new ItemStack(dropItem, event.getLevel().getRandom().nextInt(1, 2));
            ItemEntity itemEntity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), itemStack);
            event.getLevel().addFreshEntity(itemEntity);
        }
    }

    public static void shiftRightItem(PlayerInteractEvent.RightClickItem event, Item torchItem, ItemStack newItem) {
        Player player = event.getEntity();
        ItemStack itemStack = player.getMainHandItem();
        if (itemStack.is(torchItem) && player.isShiftKeyDown()) {
            int oldCount = itemStack.getCount();
            player.getInventory().removeItem(itemStack);
            newItem.setCount(oldCount);
            player.getInventory().add(newItem);
        }
    }

    public static void litBlock(PlayerInteractEvent.RightClickBlock event, Block blockChange, Item needItem, Block newBlock) {
        BlockState blockState = event.getLevel().getBlockState(event.getPos());
        ItemStack itemStack = event.getEntity().getMainHandItem();
        if (blockState.is(blockChange)
                && itemStack.is(needItem)
                && blockState.getValue(AbstractLightBlock.getLitState())) {
            itemStack.shrink(1);
            event.getLevel().setBlockAndUpdate(event.getPos(), newBlock.defaultBlockState());
            event.getLevel().updateNeighborsAt(event.getPos(), newBlock);
        }
    }

    public static void litItem(PlayerInteractEvent.RightClickBlock event, Block litBlock, Item needLit, Item finalItem) {
        BlockState blockState = event.getLevel().getBlockState(event.getPos());
        ItemStack itemStack = event.getEntity().getMainHandItem();
        if (blockState.is(litBlock)
                && blockState.getValue(AbstractLightBlock.getLitState())
                && itemStack.is(needLit)) {
            int oldCount = itemStack.getCount();
            event.getEntity().getInventory().removeItem(itemStack);
            finalItem.getDefaultInstance().setCount(oldCount);
            event.getEntity().getInventory().add(finalItem.getDefaultInstance());
        }
    }

    public static void litItem(PlayerInteractEvent.RightClickItem event, Item fireStarter, Item needLit, Item finalItem) {
        Player player = event.getEntity();
        if (player != null) {
            ItemStack firstItem = player.getOffhandItem();
            ItemStack itemStack = event.getItemStack();
            Level level = event.getLevel();
            BlockPos pos = event.getPos();
            if (itemStack.is(fireStarter)
                    && firstItem.is(needLit)
                    && !event.getLevel().isRainingAt(event.getPos().above(2))) {
                level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
                itemStack.setDamageValue(1);
                int oldCount = firstItem.getCount();
                player.getInventory().removeItem(firstItem);
                finalItem.getDefaultInstance().setCount(oldCount);
                player.getInventory().add(finalItem.getDefaultInstance());
            }
            if (itemStack.is(needLit)
                    && firstItem.is(fireStarter)
                    && !event.getLevel().isRainingAt(event.getPos().above(2))) {
                level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
                firstItem.setDamageValue(1);
                int oldCount = itemStack.getCount();
                player.getInventory().removeItem(itemStack);
                finalItem.getDefaultInstance().setCount(oldCount);
                player.getInventory().add(finalItem.getDefaultInstance());
            }
        }
    }

    public static void resetFuel(PlayerInteractEvent.RightClickItem event, Item needFuel, Item fuelItem, ItemStack fullFuelItem) {
        Player player = event.getEntity();
        ItemStack mainHandItem = player.getMainHandItem();
        ItemStack offHandItem = player.getOffhandItem();
        if (mainHandItem.is(needFuel) && offHandItem.is(fuelItem)) {
            if (mainHandItem.getCount() == 1) {
                offHandItem.shrink(1);
                player.setItemSlot(EquipmentSlot.MAINHAND, fullFuelItem);
            }
        }
    }

    public static void tickItemInWater(ItemEntityTickEvent event, Item tickItem, Item changedItem) {
        ItemEntity itemEntity = event.getEntity();
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
    }

    public static void tickItemInRaining(ItemEntityTickEvent event, Item tickItem, Item changedItem) {
        ItemEntity itemEntity = event.getEntity();
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
    }

    public static void tickItemToUnlit(ItemEntityTickEvent event, Item tickItem, Item changedItem) {
        ItemEntity itemEntity = event.getEntity();
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
    }
}
