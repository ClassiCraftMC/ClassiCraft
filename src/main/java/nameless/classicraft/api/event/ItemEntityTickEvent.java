package nameless.classicraft.api.event;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.eventbus.api.Cancelable;

/**
 * Called when an item tick in the world
 * @author wdog5
 */
@Cancelable
public class ItemEntityTickEvent extends ItemEvent {

    /**
     * Creates a new event for an {@link ItemEntity}.
     *
     * @param itemEntity The ItemEntity for this event
     */
    public ItemEntityTickEvent(ItemEntity itemEntity) {
        super(itemEntity);
    }

}
