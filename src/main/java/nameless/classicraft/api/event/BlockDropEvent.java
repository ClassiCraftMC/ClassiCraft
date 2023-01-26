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
package nameless.classicraft.api.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;

/**
 * A event fired when block was destroyed
 * this event can get the block's pos while break
 */
public class BlockDropEvent extends BlockEvent.BreakEvent {

    private final BlockPos pos;
    private final BlockState state;
    private final Player player;
    private final Level level;

    public BlockDropEvent(Level level, BlockPos pos, BlockState state, Player player) {
        super(level, pos, state, player);
        this.pos = pos;
        this.state = state;
        this.player = player;
        this.level = level;
    }

    @Override
    public BlockPos getPos() {
        return pos;
    }

    @Override
    public BlockState getState() {
        return state;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Level getLevel() {
        return level;
    }
}
