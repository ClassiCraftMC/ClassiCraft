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

import nameless.classicraft.block.RealSoulTorchBlock;
import nameless.classicraft.block.RealTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class DebugBurnTimeStick extends Item {

    public DebugBurnTimeStick() {
        super(new Properties());
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        BlockState blockState = level.getBlockState(pos);
        Block block = blockState.getBlock();
        Player player = pContext.getPlayer();
        if (!level.isClientSide) {
            if (player != null) {
                if (block instanceof RealTorchBlock) {
                    player.displayClientMessage(Component.translatable("info.classicraft.burntime")
                            .append(blockState.getValue(RealTorchBlock.getBurnTime()).toString())
                            .append(Component.translatable("info.classicraft.minutes")), true);
                }
                if (block instanceof RealSoulTorchBlock) {
                    player.displayClientMessage(Component.translatable("info.classicraft.burntime")
                            .append(blockState.getValue(RealSoulTorchBlock.getBurnTime()).toString())
                            .append(Component.translatable("info.classicraft.minutes")), true);
                }
            }
        }
        return super.useOn(pContext);
    }
}
