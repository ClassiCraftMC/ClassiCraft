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

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import nameless.classicraft.rot.RotHolder;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractRot {

    private final RotHolder holder;
    private boolean hasExMsg;
    private Function<AbstractRot, List<Component>> exMsg;
    private float finalSpeed;

    @NotNull
    public List<Component> getMsg() {
        if (hasExMsg)
            return exMsg.apply(this);
        return List.of();
    }

    public RotHolder.RotLevel getLevel() {
        return getHolder().getLevel();
    }

    public MutableComponent getLevelName() {
        return switch (getLevel()) {
            case FRESH ->   Component.translatable("level.classicraft.fresh");
            case STALE ->   Component.translatable("level.classicraft.stale");
            case SPOILED -> Component.translatable("level.classicraft.spoiled");
            case ROT ->     Component.translatable("level.classicraft.rotten");
        };
    }

    public ChatFormatting getLevelNameColor() {
        return switch (getLevel()) {
            case FRESH ->   ChatFormatting.GREEN;
            case STALE ->   ChatFormatting.YELLOW;
            case SPOILED -> ChatFormatting.GOLD;
            case ROT ->     ChatFormatting.RED;
        };
    }

    public float getRotValue() {
        return holder.getCurrent();
    }

    public void setRotValue(float v) {
        holder.setCurrent(v);
    }

}