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