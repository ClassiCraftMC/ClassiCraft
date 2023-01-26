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
package nameless.classicraft.datagen.language;

import nameless.classicraft.api.item.MetaItem;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class EmptyLangProvider extends LanguageProvider {
    public EmptyLangProvider(PackOutput output, String modid) {
        super(output, modid, "empty");
    }

    @Override
    protected void addTranslations() {
        // metas();
    }

    void metas() {
        for (MetaItem metaItem : MetaItem.getMetaItems()) {
            metaItem.getMetaItemStacks().forEach(s -> add(s.getDescriptionId(), " "));
        }
    }
}
