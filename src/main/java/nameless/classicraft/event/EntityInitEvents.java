package nameless.classicraft.event;

import nameless.classicraft.entity.LivingDeadEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * @author wdog5
 */
@Mod.EventBusSubscriber
public class EntityInitEvents {

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(LivingDeadEntity::init);
    }
}
