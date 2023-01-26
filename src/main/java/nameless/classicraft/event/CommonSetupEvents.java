package nameless.classicraft.event;

import nameless.classicraft.network.SimpleNetworkHandler;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * @author wdog5
 */
@Mod.EventBusSubscriber
public class CommonSetupEvents {

    @SubscribeEvent
    public void setupCommon(FMLCommonSetupEvent event) {
        SimpleNetworkHandler.init();
    }
}
