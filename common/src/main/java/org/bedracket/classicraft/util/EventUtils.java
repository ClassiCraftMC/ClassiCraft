package org.bedracket.classicraft.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.bedracket.classicraft.api.event.ItemTickEvent;

public class EventUtils {

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
