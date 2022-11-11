package nameless.classicraft.api.event;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraftforge.event.entity.item.ItemEvent;

/**
 * Called when an item is spawned into a world
 */
public class ItemSpawnEvent extends ItemEvent {

    /**
     * Creates a new event for an {@link ItemEntity}.
     *
     * @param itemEntity The ItemEntity for this event
     */
    public ItemSpawnEvent(ItemEntity itemEntity) {
        super(itemEntity);
    }

}
