package nameless.classicraft.api.network;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public interface INormalMessage {

    void toBytes(ByteBuf buf);

    void process(Supplier<NetworkEvent.Context> context);

}