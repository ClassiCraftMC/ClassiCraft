package nameless.classicraft.event;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.api.event.ItemSpawnEvent;
import nameless.classicraft.init.ModItems;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;

public class TestEvents {

    public static void init() {
        final IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(TestEvents::testSpawnEvent);
    }

    public static void testSpawnEvent(ItemSpawnEvent event) {
        ItemEntity item = event.getEntity();
        if (item.getItem().is(ModItems.RAW_SALT.get())) {
            ClassiCraftMod.LOGGER.info("ItemSpawnEvent Test!");
        }
    }

}
