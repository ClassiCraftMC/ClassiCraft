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
package nameless.classicraft.capability.rot;

import nameless.classicraft.rot.RotHolder;
import nameless.classicraft.rot.RotItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class NormalRot extends AbstractRot {

    public static int getSecond(Item item) {
        return RotItems.MAP.get(item);
    }

    public static boolean canUse(ItemStack stack) {
        return RotItems.MAP.containsKey(stack.getItem());
    }

    ItemStack food;

    public NormalRot(ItemStack food) {
        super(new RotHolder(getSecond(food.getItem()), getSecond(food.getItem())), true, rot ->
                List.of(Component.translatable("info.classicraft.rot", String.format("%.2f", rot.getHolder().getCurrent() / 24000)),
                        Component.translatable("info.classicraft.rotting_speed", (int) (rot.getFinalSpeed() * 100) + "%")), 1);

        this.food = food;
    }

    @Override
    public boolean equals(Object o) {
        return o == this || o instanceof NormalRot rot && rot.food.equals(this.food);
    }

    @Override
    public int hashCode() {
        return food.hashCode();
    }

}