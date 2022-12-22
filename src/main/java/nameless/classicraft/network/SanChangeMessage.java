package nameless.classicraft.network;

import nameless.classicraft.api.network.INormalMessage;
import nameless.classicraft.api.san.ISanHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

public class SanChangeMessage implements INormalMessage {

    public SanChangeMessage(FriendlyByteBuf buf) {

    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {

    }

    @Override
    public void process(Supplier<NetworkEvent.Context> context) {
        ServerPlayer player = context.get().getSender();
        if (player != null) {
            SimpleNetworkHandler.CHANNEL.
                    send(PacketDistributor.PLAYER.with(() -> player),
                            new PlayerSanMessage(((ISanHandler) player).getSan(),
                                    ((ISanHandler) player).getMaxSan()));
        }
    }
}
