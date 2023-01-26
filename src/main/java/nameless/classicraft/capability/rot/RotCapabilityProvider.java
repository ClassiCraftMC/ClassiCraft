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

import nameless.classicraft.init.ModCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RotCapabilityProvider extends CapabilityProvider<RotCapabilityProvider> implements INBTSerializable<CompoundTag> {

    LazyOptional<AbstractRot> cap;

    public RotCapabilityProvider(LazyOptional<AbstractRot> cap) {
        super(RotCapabilityProvider.class);

        this.cap = cap;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return ModCapabilities.ROT.orEmpty(cap, this.cap.cast());
    }

    @Override
    public CompoundTag serializeNBT() {
        return cap.map(abstractRot -> {
            CompoundTag compoundTag = new CompoundTag();
            compoundTag.putFloat("rot", abstractRot.getRotValue());
            compoundTag.putFloat("fs", abstractRot.getFinalSpeed());
            return compoundTag;
        }).orElse(new CompoundTag());
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        cap.ifPresent(abstractRot -> {
            abstractRot.setRotValue(nbt.getFloat("rot"));
            abstractRot.setFinalSpeed(nbt.getFloat("fs"));
        });
    }
}
