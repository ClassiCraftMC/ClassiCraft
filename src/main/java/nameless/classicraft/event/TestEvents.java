package nameless.classicraft.event;

import nameless.classicraft.api.event.ItemTickInventoryEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TestEvents {

    @SubscribeEvent
    public static void test(ItemTickInventoryEvent event) {
        Item item = event.getItem();
        if (item.getDefaultInstance().is(Items.COD)) {
        }
    }

    @SubscribeEvent
    public static void test0(PlayerInteractEvent.LeftClickEmpty event) {

    }
}
