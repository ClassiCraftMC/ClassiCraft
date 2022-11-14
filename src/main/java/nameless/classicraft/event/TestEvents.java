package nameless.classicraft.event;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.api.event.ProjectileHitEvent;
import net.minecraft.core.Direction;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;

public class TestEvents {

    public static void init() {
        final IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(TestEvents::testEvent);
    }

    public static void testEvent(ProjectileHitEvent event) {
        if (event.getHitBlockFace() == Direction.UP) {
            ClassiCraftMod.LOGGER.info("ProjectileHitEvent test successfully!");
        }
    }

}
