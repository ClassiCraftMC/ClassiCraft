package org.bedracket.event.entity.item;

import net.minecraft.world.entity.item.ItemEntity;
import org.jetbrains.annotations.NotNull;

public class ItemTickEvent extends ItemEvent {

    public ItemTickEvent(@NotNull ItemEntity itemEntity) {
        super(itemEntity);
    }
}
