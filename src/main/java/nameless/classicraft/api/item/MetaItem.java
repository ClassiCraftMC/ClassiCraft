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

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.util.SafeTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a list of repeatability items<p></p>
 * it could be auto registry item models (MetaModelRegistry)<p></p>
 *
 * @see nameless.classicraft.client.meta.MetaModelRegistry
 */
public interface MetaItem extends ItemLike {
    List<MetaItem> META_ITEMS = new ArrayList<>();

    static List<MetaItem> getMetaItems() {
        return META_ITEMS;
    }

    static ItemStack setMetaItem(ItemStack stack, String meta) {
        MetaItem.setMeta(stack, meta);
        return stack;
    }

    List<String> getMetas();

    static String getMeta(ItemStack stack) {
        return new SafeTag(stack, ClassiCraftMod.MOD_ID, false).getString("Meta").orElse("");
    }

    static void setMeta(ItemStack stack, String meta) {
        new SafeTag(stack, ClassiCraftMod.MOD_ID, true).putString("Meta", meta);
    }

    static boolean equals(ItemStack i1, ItemStack i2) {
        return i1.getItem() == i2.getItem() && Objects.equals(getMeta(i1), getMeta(i2));
    }

    static void copyMeta(ItemStack source, ItemStack target) {
        MetaItem.setMeta(target, MetaItem.getMeta(source));
    }

    /**
     * Put all metas to Creative mode tabs
     *
     * @param output output
     */
    default void acceptToCreativeModeTab(CreativeModeTab.Output output) {
        getMetaItemStacks().forEach(output::accept);
    }

    default List<ItemStack> getMetaItemStacks() {
        List<ItemStack> result = new ArrayList<>();
        ItemStack itemStack = new ItemStack(asItem());

        for (String meta : getMetas()) {
            setMeta(itemStack, meta);
            result.add(itemStack.copy());
        }

        return result;
    }

    default ResourceLocation metaResLoc(ItemStack stack) {
        return metaResLoc(getMeta(stack));
    }

    default ResourceLocation metaResLoc(String meta) {
        ResourceLocation key = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(asItem()));
        return new ResourceLocation(key.getNamespace(), key.getPath() + "/" + meta);
    }

    @Override
    default @NotNull Item asItem() {
        return (Item) this;
    }
}
