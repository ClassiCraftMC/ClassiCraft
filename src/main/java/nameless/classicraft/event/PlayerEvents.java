package nameless.classicraft.event;

import nameless.classicraft.init.ModItems;
import nameless.classicraft.util.EventUtils;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerEvents {

    @SubscribeEvent
    public static void rightClickTorch(PlayerInteractEvent.RightClickItem event) {
        EventUtils.shiftRightTorch(event, ModItems.TORCH_LIT.get());
        EventUtils.shiftRightTorch(event, ModItems.SOUL_TORCH_LIT.get());
    }
}
