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
package nameless.classicraft.api.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemStackAPI {

    public static ItemStack replaceItemWitchNoNBT(ItemStack oldStack, Item newItem) {
        ItemStack itemStack = new ItemStack(newItem);
        itemStack.setCount(oldStack.getCount());
        return itemStack;
    }

}
