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

import nameless.classicraft.init.ModCapabilities;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

public class SanChangeMessage{

    public SanChangeMessage(FriendlyByteBuf buf) {

    }

    public static void buffer(SanChangeMessage message, FriendlyByteBuf buf) {

    }

    public static void handler(SanChangeMessage message, Supplier<NetworkEvent.Context> context) {
        ServerPlayer player = context.get().getSender();
        if (player != null) {
            player.getCapability(ModCapabilities.PLAYER_SAN_LEVEL).ifPresent(
                    capability -> {
                        SimpleNetworkHandler.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(()
                                        -> player),
                                new PlayerSanMessage(capability.getSan(),
                                        capability.getMaxSan()));
                    }
            );
            context.get().setPacketHandled(true);
        }
    }
}
