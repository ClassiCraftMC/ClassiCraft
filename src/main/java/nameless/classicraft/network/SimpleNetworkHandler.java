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

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.api.network.INormalMessage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Function;

public final class SimpleNetworkHandler {

    public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(ClassiCraftMod.MOD_ID, "main"))
            .networkProtocolVersion(() -> ClassiCraftMod.NETWORK_VERSION)
            .serverAcceptedVersions(ClassiCraftMod.NETWORK_VERSION::equals)
            .clientAcceptedVersions(ClassiCraftMod.NETWORK_VERSION::equals).simpleChannel();

    public static void init() {
        int id = 0;
        registerMessage(id++, PlayerSanMessage.class, PlayerSanMessage::new);
        registerMessage(id++, SanChangeMessage.class, SanChangeMessage::new);
    }

    private static <T extends INormalMessage> void registerMessage(int index, Class<T> messageType, Function<FriendlyByteBuf, T> decoder) {
        CHANNEL.registerMessage(index, messageType, INormalMessage::toBytes, decoder, (message, context) -> {
            message.process(context);
            context.get().setPacketHandled(true);
        });
    }
}