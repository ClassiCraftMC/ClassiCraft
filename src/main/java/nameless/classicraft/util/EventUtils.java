package nameless.classicraft.util;

import nameless.classicraft.api.event.BlockDropEvent;
import nameless.classicraft.api.event.ItemEntityTickEvent;
import nameless.classicraft.block.AbstractLightBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class EventUtils {

    public static void blockdropAtOnce(BlockDropEvent event, Block dropBlock, Item dropItem) {
        BlockState blockState = event.getState();
        World level = event.getWorld();
        BlockPos pos = event.getPos();
        if (blockState.isOf(dropBlock)) {
            ItemEntity itemEntity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), dropItem.getDefaultStack());
            event.getLevel().spawnEntity(itemEntity);
        }
    }

    public static void blockdropRandom(BlockDropEvent event, Block dropBlock, Item dropItem) {
        BlockState blockState = event.getState();
        World level = event.getWorld();
        BlockPos pos = event.getPos();
        if (blockState.isOf(dropBlock)) {
            ItemStack itemStack = new ItemStack(dropItem, event.getLevel().getRandom().nextBetween(1, 2));
            ItemEntity itemEntity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), itemStack);
            event.getLevel().spawnEntity(itemEntity);
        }
    }

    public static void shiftRightItem(PlayerInteractEvent.RightClickItem event, Item torchItem, ItemStack newItem) {
        PlayerEntity player = event.getEntity();
        ItemStack itemStack = player.getMainHandStack();
        if (itemStack.isOf(torchItem) && player.isSneaking()) {
            int oldCount = itemStack.getCount();
            player.getInventory().removeOne(itemStack);
            newItem.setCount(oldCount);
            player.getInventory().insertStack(newItem);
        }
    }

    public static void litBlock(PlayerInteractEvent.RightClickBlock event, Block blockChange, Item needItem, Block newBlock) {
        BlockState blockState = event.getLevel().getBlockState(event.getPos());
        ItemStack itemStack = event.getEntity().getMainHandStack();
        if (blockState.isOf(blockChange)
                && itemStack.isOf(needItem)
                && blockState.get(AbstractLightBlock.getLitState())) {
            itemStack.decrement(1);
            event.getLevel().setBlockState(event.getPos(), newBlock.getDefaultState());
            event.getLevel().updateNeighbors(event.getPos(), newBlock);
        }
    }

    public static void litItem(PlayerInteractEvent.RightClickBlock event, Block litBlock, Item needLit, Item finalItem) {
        BlockState blockState = event.getLevel().getBlockState(event.getPos());
        ItemStack itemStack = event.getEntity().getMainHandStack();
        if (blockState.isOf(litBlock)
                && blockState.get(AbstractLightBlock.getLitState())
                && itemStack.isOf(needLit)) {
            int oldCount = itemStack.getCount();
            event.getEntity().getInventory().removeOne(itemStack);
            finalItem.getDefaultStack().setCount(oldCount);
            event.getEntity().getInventory().insertStack(finalItem.getDefaultStack());
        }
    }

    public static void litItem(PlayerInteractEvent.RightClickItem event, Item fireStarter, Item needLit, Item finalItem) {
        PlayerEntity player = event.getEntity();
        if (player != null) {
            ItemStack firstItem = player.getOffHandStack();
            ItemStack itemStack = event.getItemStack();
            World level = event.getLevel();
            BlockPos pos = event.getPos();
            if (itemStack.isOf(fireStarter)
                    && firstItem.isOf(needLit)
                    && !event.getLevel().hasRain(event.getPos().up(2))) {
                level.playSound(null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
                itemStack.setDamage(1);
                int oldCount = firstItem.getCount();
                player.getInventory().removeOne(firstItem);
                finalItem.getDefaultStack().setCount(oldCount);
                player.getInventory().insertStack(finalItem.getDefaultStack());
            }
            if (itemStack.isOf(needLit)
                    && firstItem.isOf(fireStarter)
                    && !event.getLevel().hasRain(event.getPos().up(2))) {
                level.playSound(null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
                firstItem.setDamage(1);
                int oldCount = itemStack.getCount();
                player.getInventory().removeOne(itemStack);
                finalItem.getDefaultStack().setCount(oldCount);
                player.getInventory().insertStack(finalItem.getDefaultStack());
            }
        }
    }

    public static void resetFuel(PlayerInteractEvent.RightClickItem event, Item needFuel, Item fuelItem, ItemStack fullFuelItem) {
        PlayerEntity player = event.getEntity();
        ItemStack mainHandItem = player.getMainHandStack();
        ItemStack offHandItem = player.getOffHandStack();
        if (mainHandItem.isOf(needFuel) && offHandItem.isOf(fuelItem)) {
            if (mainHandItem.getCount() == 1) {
                offHandItem.decrement(1);
                player.equipStack(EquipmentSlot.MAINHAND, fullFuelItem);
            }
        }
    }

    public static void tickItemInWater(ItemEntityTickEvent event, Item tickItem, Item changedItem) {
        ItemEntity itemEntity = event.getEntity();
        World level = itemEntity.getWorld();
        if (itemEntity.getStack().isOf(tickItem)
                && itemEntity.isTouchingWater()) {
            int oldCount = itemEntity.getStack().getCount();
            itemEntity.remove(Entity.RemovalReason.KILLED);
            ItemEntity newItem = new ItemEntity(
                    itemEntity.getWorld(),
                    itemEntity.getX(), itemEntity.getY(),
                    itemEntity.getZ(),
                    changedItem.getDefaultStack());
            newItem.getStack().setCount(oldCount);
            level.spawnEntity(newItem);
        }
    }

    public static void tickItemInRaining(ItemEntityTickEvent event, Item tickItem, Item changedItem) {
        ItemEntity itemEntity = event.getEntity();
        World level = itemEntity.getWorld();
        if (itemEntity.getStack().isOf(tickItem)
                && level.hasRain(itemEntity.getBlockPos().up(2))) {
            int oldCount = itemEntity.getStack().getCount();
            itemEntity.remove(Entity.RemovalReason.KILLED);
            ItemEntity newItem = new ItemEntity(
                    itemEntity.getWorld(),
                    itemEntity.getX(), itemEntity.getY(),
                    itemEntity.getZ(),
                    changedItem.getDefaultStack());
            newItem.getStack().setCount(oldCount);
            level.spawnEntity(newItem);
        }
    }

    public static void tickItemToUnlit(ItemEntityTickEvent event, Item tickItem, Item changedItem) {
        ItemEntity itemEntity = event.getEntity();
        ItemStack itemStack = itemEntity.getStack();
        if (itemStack.isOf(tickItem)
                && itemEntity.getItemAge()
                == 2 * 1200) {
            int oldCount = itemEntity.getStack().getCount();
            itemEntity.remove(Entity.RemovalReason.KILLED);
            ItemEntity newItem = new ItemEntity(
                    itemEntity.getWorld(),
                    itemEntity.getX(), itemEntity.getY(),
                    itemEntity.getZ(),
                    changedItem.getDefaultStack());
            newItem.getStack().setCount(oldCount);
            itemEntity.getWorld().spawnEntity(newItem);
        }
    }
}
