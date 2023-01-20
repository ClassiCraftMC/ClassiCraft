package nameless.classicraft.api.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.Event;

/**
 * @author wdog5
 */
public class ItemTickInventoryEvent extends Event {

    private final Item item;
    private final Level level;
    private final Entity entity;
    private final int slotId;
    private final boolean isSelected;

    public ItemTickInventoryEvent(Item item, Level level, Entity entity, int slotId, boolean isSelected) {
        this.item = item;
        this.level = level;
        this.entity = entity;
        this.slotId = slotId;
        this.isSelected = isSelected;
    }

    public Item getItem() {
        return item;
    }

    public Level getLevel() {
        return level;
    }

    public Entity getEntity() {
        return entity;
    }

    public int getSlotId() {
        return slotId;
    }

    public boolean isSelected() {
        return isSelected;
    }
}
