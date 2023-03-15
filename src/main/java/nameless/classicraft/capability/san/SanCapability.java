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
package nameless.classicraft.capability.san;

import nameless.classicraft.util.Helpers;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public class SanCapability implements INBTSerializable<CompoundTag> {

    private int maxSan = 20;
    private float san = 0;

    public int getMaxSan() {
        return maxSan;
    }

    public float getSan() {
        return san;
    }

    public void setSan(float level) {
        this.san = level;
    }

    public void regenSan(float level) {
        this.setSan(this.getSan() + level);
    }

    public void reduceSan(float level) {
        this.setSan(this.getSan() - level);
    }

    public void setMaxSan(int level) {
        this.maxSan = level;
    }

    public void reduceMaxSan(int level) {
        this.setMaxSan(this.getMaxSan() - level);
    }

    public void addMaxSan(int level) {
        this.setMaxSan(this.getMaxSan() + level);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt(Helpers.namespace("MaxSan"), this.getMaxSan());
        tag.putFloat(Helpers.namespace("San"), this.getSan());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.setMaxSan(nbt.getInt(Helpers.namespace("MaxSan")));
        this.setSan(nbt.getFloat(Helpers.namespace("San")));
    }
}
