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

import java.util.List;

public class MetaItemImpl extends Item implements MetaItem {
    public final List<String> metas;

    public MetaItemImpl(Properties pProperties, List<String> metas) {
        super(pProperties);

        this.metas = metas;

        META_ITEMS.add(this);
    }

    @Override
    public String getDescriptionId(ItemStack pStack) {
        return super.getDescriptionId() + "." + MetaItem.getMeta(pStack);
    }

    @Override
    public List<String> getMetas() {
        return metas;
    }
}
