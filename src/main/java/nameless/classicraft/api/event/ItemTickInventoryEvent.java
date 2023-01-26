/*
 * ClassiCraft - ClassiCraftMC
 * Copyright (C) 2018-2022.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package nameless.classicraft.api.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.Event;

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
