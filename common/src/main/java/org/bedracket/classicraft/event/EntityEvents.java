package org.bedracket.classicraft.event;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import org.bedracket.classicraft.init.ModItems;
import org.bedracket.classicraft.util.EventUtils;

public class EntityEvents {

    public static void registerEntityEvents() {
        EntityEvent.ADD.register((entity, world) -> {
            EventUtils.heapPebble(ModItems.ANDESITE_PEBBLE.get());
            EventUtils.heapPebble(ModItems.COBBLESTONE_PEBBLE.get());
            EventUtils.heapPebble(ModItems.DIORTIE_PEBBLE.get());
            EventUtils.heapPebble(ModItems.GRANITE_PEBBLE.get());
            EventUtils.heapPebble(ModItems.RED_SANDSTONE_PEBBLE.get());
            EventUtils.heapPebble(ModItems.SANDSTONE_PEBBLE.get());
            return EventResult.pass();
        });
    }
}
