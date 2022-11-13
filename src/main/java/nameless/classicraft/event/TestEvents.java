package nameless.classicraft.event;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.api.event.LivingEatEvent;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;

public class TestEvents {

    public static void init() {
        final IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(TestEvents::testEvent);
    }

    public static void testEvent(LivingEatEvent event) {
        if (event.getFood().is(Items.CARROT)) {
            ClassiCraftMod.LOGGER.info("LivingEatEvent test successfully!");
        }
    }

}
