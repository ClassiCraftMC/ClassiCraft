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
package nameless.classicraft.mixin;

import nameless.classicraft.api.item.MetaItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(value = MobBucketItem.class, remap = false)
public abstract class MixinMobBucketItem extends BucketItem implements MetaItem {

    public MixinMobBucketItem(Fluid pContent, Properties pProperties) {
        super(pContent, pProperties);
    }

    @Shadow protected abstract EntityType<?> getFishType();

    @Override
    public List<String> getMetas() {
        switch (getFishType().getDescriptionId()) {
            case "entity.minecraft.salmon",
                    "entity.minecraft.cod",
                    "entity.minecraft.tropical_fish",
                    "entity.minecraft.axolotl",
                    "entity.minecraft.pufferfish",
                    "entity.minecraft.tadpole",
                    "entity.classicraft.trout"
                    -> {
                return List.of("", "_wooden");
            }
        }
        return List.of("");
    }
}
