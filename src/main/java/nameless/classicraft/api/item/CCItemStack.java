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

import nameless.classicraft.init.ModCapabilities;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.extensions.IForgeItemStack;
import org.jetbrains.annotations.Nullable;

public interface CCItemStack extends IForgeItemStack {

    @Nullable
    @Override
    default CompoundTag getShareTag() {
        CompoundTag superValue = IForgeItemStack.super.getShareTag();

        if (superValue == null) {
            superValue = new CompoundTag();
        }

        CompoundTag returnValue = superValue;

        getCapability(ModCapabilities.ROT).ifPresent(rot -> {
            returnValue.putFloat("final_speed", rot.getFinalSpeed());
            returnValue.putFloat("rot", rot.getRotValue());
        });

        return returnValue;
    }

    @Override
    default void readShareTag(@Nullable CompoundTag nbt) {
        IForgeItemStack.super.readShareTag(nbt);

        if (nbt != null && nbt.contains("rot")) {
            getCapability(ModCapabilities.ROT).ifPresent(rot -> {
                rot.setFinalSpeed(nbt.getFloat("final_speed"));
                rot.setRotValue(nbt.getFloat("rot"));
            });
        }
    }
}
