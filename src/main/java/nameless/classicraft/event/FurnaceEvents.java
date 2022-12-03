package nameless.classicraft.event;

import nameless.classicraft.init.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class FurnaceEvents {

    @SubscribeEvent
    public static void setFuelValue(FurnaceFuelBurnTimeEvent event) {
        ItemStack itemStack = event.getItemStack();
        if (itemStack.is(ModItems.TALLOW.get())) {
            event.setBurnTime(20);
        }
    }
}
