package org.bedracket.classicraft.event;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import org.bedracket.classicraft.init.ModItems;

public class LivingEntityEvents {

    public static void registerLivingEntityEvents() {
        EntityEvent.LIVING_DEATH.register((entity, source) -> {
            Level level = entity.getLevel();
            BlockPos pos = entity.getOnPos();
            if (entity instanceof Animal) {
                ItemEntity itemEntity = new ItemEntity(level,
                        pos.getX(), pos.getY(), pos.getZ(), ModItems.TALLOW.get().getDefaultInstance());
                level.addFreshEntity(itemEntity);
            }
            return EventResult.pass();
        });
    }
}
