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

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CompassItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DepthMeterItem extends CompassItem {

    public DepthMeterItem() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        int height = pPlayer.getOnPos().getY();
        if (height > 257 && height < 350) {
            pPlayer.displayClientMessage(Component.translatable("info.classicraft.height")
                    .append(String.valueOf(height)).append(Component.translatable("info.classicraft.depth.sky_land")), true);
        }
        if (height > 193 && height < 256) {
            pPlayer.displayClientMessage(Component.translatable("info.classicraft.height")
                    .append(String.valueOf(height)).append(Component.translatable("info.classicraft.depth.sky")), true);
        }
        if (height > 181 && height < 192) {
            pPlayer.displayClientMessage(Component.translatable("info.classicraft.height")
                    .append(String.valueOf(height))
                    .append(Component.translatable("info.classicraft.depth.cloud")), true);
        }
        if (height > 117 && height < 180) {
            pPlayer.displayClientMessage(Component.translatable("info.classicraft.height")
                    .append(String.valueOf(height))
                    .append(Component.translatable("info.classicraft.depth.base_cloud")), true);
        }
        if (height == 63) {
            pPlayer.displayClientMessage(Component.translatable("info.classicraft.height")
                    .append(String.valueOf(height))
                    .append(Component.translatable("info.classicraft.depth.sea_level")), true);
        }
        if (height > 52 && height < 62 || height > 64 && height < 116) {
            pPlayer.displayClientMessage(Component.translatable("info.classicraft.height")
                    .append(String.valueOf(height))
                    .append(Component.translatable("info.classicraft.depth.surface")), true);
        }
        if (height > 4 && height < 51) {
            pPlayer.displayClientMessage(Component.translatable("info.classicraft.height")
                    .append(String.valueOf(height))
                    .append(Component.translatable("info.classicraft.depth.underground")), true);
        }
        if (height > -60 && height < 3) {
            pPlayer.displayClientMessage(Component.translatable("info.classicraft.height")
                    .append(String.valueOf(height))
                    .append(Component.translatable("info.classicraft.depth.deep_underground")), true);
        }
        if (height > -64 && height < -61) {
            pPlayer.displayClientMessage(Component.translatable("info.classicraft.height")
                    .append(String.valueOf(height))
                    .append(Component.translatable("info.classicraft.depth.bedrock")), true);
        }
        if (height > 320 || height < -64) {
            pPlayer.displayClientMessage(Component.translatable("info.classicraft.height")
                    .append(String.valueOf(height))
                    .append(Component.translatable("info.classicraft.depth.void")), true);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public String getDescriptionId(ItemStack pStack) {
        return isLodestoneCompass(pStack) ? "item.classicraft.depth_meter" : super.getDescriptionId(pStack);
    }
}
