package org.bedracket.event.entity.item;

import net.minecraft.world.entity.item.ItemEntity;
import org.bedracket.event.entity.EntityEvent;
import org.jetbrains.annotations.NotNull;

public class ItemEvent extends EntityEvent {

    public ItemEvent(@NotNull final ItemEntity itemEntity) {
        super(itemEntity);
    }

    @NotNull
    @Override
    public ItemEntity getEntity() {
        return (ItemEntity) entity;
    }
}
