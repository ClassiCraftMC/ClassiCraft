package org.bedracket.classicraft.api.event;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import dev.architectury.event.events.common.TickEvent;
import net.minecraft.world.entity.item.ItemEntity;

public interface ItemTickEvent {

    Event<ItemTick> ITEM_ENTITY_POST = EventFactory.createLoop();

    interface ItemTick extends TickEvent<ItemEntity> {
    }
}
