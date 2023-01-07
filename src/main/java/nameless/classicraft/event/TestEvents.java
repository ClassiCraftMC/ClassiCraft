package nameless.classicraft.event;

import nameless.classicraft.api.event.ItemEntityTickEvent;
import nameless.classicraft.util.EventUtils;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TestEvents {

    @SubscribeEvent
    public static void test(ItemEntityTickEvent event) {
        EventUtils.onHit(event);
    }

    @SubscribeEvent
    public static void test0(ItemTooltipEvent event) {
        EventUtils.appendTooltip(event);
    }
}
