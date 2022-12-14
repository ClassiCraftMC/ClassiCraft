package org.bedracket.event.entity;

import net.minecraft.world.entity.Entity;
import org.bedracket.eventbus.Event;
import org.jetbrains.annotations.NotNull;

public class EntityEvent extends Event {

    protected final Entity entity;

    public EntityEvent(@NotNull final Entity entity) {
        this.entity = entity;
    }

    @NotNull
    public Entity getEntity() {
        return entity;
    }
}
