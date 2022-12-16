package org.bedracket.classicraft.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.bedracket.classicraft.api.event.ItemTickEvent;
import org.bedracket.classicraft.init.ModItems;
import org.bedracket.classicraft.util.EventUtils;

public class ItemTickEvents {

    public static void registerItemTickEvents() {
        EventUtils.tickItemToUnlit(ModItems.TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemToUnlit(ModItems.SOUL_TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemInRaining(ModItems.TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemInRaining(ModItems.SOUL_TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemInRaining(ModItems.TORCH_UNLIT.get(), Items.STICK);
        EventUtils.tickItemInRaining(ModItems.SOUL_TORCH_UNLIT.get(), Items.STICK);
        EventUtils.tickItemInWater(ModItems.TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemInWater(ModItems.SOUL_TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemInWater(ModItems.TORCH_UNLIT.get(), Items.STICK);
        EventUtils.tickItemInWater(ModItems.SOUL_TORCH_UNLIT.get(), Items.STICK);
        ItemTickEvent.ITEM_ENTITY_POST.register(itemEntity -> {
            ItemStack stack = itemEntity.getItem();
            int oldCount = stack.getCount();
            ItemEntity newItem =
                    new ItemEntity(
                            itemEntity.getLevel(),
                            itemEntity.getX(),
                            itemEntity.getY(),
                            itemEntity.getZ(),
                            Items.WET_SPONGE.getDefaultInstance()
                    );
            if (stack.is(Items.SPONGE)) {
                if (itemEntity.isInWater()) {
                    itemEntity.remove(Entity.RemovalReason.KILLED);
                    newItem.getItem().setCount(oldCount);
                    itemEntity.getLevel().addFreshEntity(newItem);
                }
                if (itemEntity.getLevel().isRainingAt(itemEntity.getOnPos().above(2))
                        && itemEntity.getAge() == 500) {
                    itemEntity.remove(Entity.RemovalReason.KILLED);
                    newItem.getItem().setCount(oldCount);
                    itemEntity.getLevel().addFreshEntity(newItem);
                }
            }
        });
    }
}
