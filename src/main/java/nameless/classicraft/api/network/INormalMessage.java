package nameless.classicraft.api.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * @author wdog5
 */
public interface INormalMessage {

    void toBytes(FriendlyByteBuf buf);

    void process(Supplier<NetworkEvent.Context> context);

}