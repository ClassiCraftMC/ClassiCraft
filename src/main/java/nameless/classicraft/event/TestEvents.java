package nameless.classicraft.event;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.api.event.PlayerRightClickBlockEvent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;

public class TestEvents {

    public static void init() {
        final IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(TestEvents::testEvent);
    }

    public static void testEvent(PlayerRightClickBlockEvent event) {
        Block block = event.getBlock();
        if (block.defaultBlockState().is(Blocks.IRON_BLOCK) && event.getEntity().getItemInHand(event.getHand()).isEmpty()) {
            ClassiCraftMod.LOGGER.info("PlayerInteractBlockEvent test successfully!");
        }
    }

}
