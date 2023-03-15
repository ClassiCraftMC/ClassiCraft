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
package nameless.classicraft.item;

import nameless.classicraft.api.item.MetaItem;
import nameless.classicraft.api.item.MetaItemImpl;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PebbleToolItem extends MetaItemImpl {
    public static final List<PebbleToolItem> TOOLS = new ArrayList<>();

    public PebbleToolItem(Properties pProperties, List<String> metas) {
        super(pProperties, metas);

        TOOLS.add(this);
    }

    public static ItemStack randomFrom(ItemStack pebble, RandomSource random) {
        PebbleToolItem tool = TOOLS.get(random.nextInt(TOOLS.size()));
        String meta = PebbleItem.getPebbleType(pebble) + "_" + Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(tool)).getPath();

        if (tool.metas.contains(meta)) {
            ItemStack result = new ItemStack(tool);
            MetaItem.setMeta(result, meta);
            return result;
        } else {
            return ItemStack.EMPTY;
        }
    }
}
