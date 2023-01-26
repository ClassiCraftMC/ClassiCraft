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
package nameless.classicraft.network;

import nameless.classicraft.api.network.INormalMessage;
import nameless.classicraft.api.san.ISanHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PlayerSanMessage implements INormalMessage {

    float san;
    float maxSan;

    public PlayerSanMessage(float san, float maxSan) {
        this.san = san;
        this.maxSan = maxSan;
    }

    public PlayerSanMessage(FriendlyByteBuf buf) {
        san = buf.readFloat();
        maxSan = buf.readFloat();
    }


    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFloat(san);
        buf.writeFloat(maxSan);
    }

    @Override
    public void process(Supplier<NetworkEvent.Context> context) {
        if (context.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                context.get().enqueueWork(() -> ((ISanHandler) player).setSan(san));
                context.get().enqueueWork(() -> ((ISanHandler) player).setMaxSan(maxSan));
            }
        }
    }
}
