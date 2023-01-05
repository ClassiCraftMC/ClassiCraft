package nameless.classicraft.event;

import nameless.classicraft.api.event.ItemEntityTickEvent;
import nameless.classicraft.init.ModItems;
import nameless.classicraft.util.EventUtils;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ItemEntityTickEvents {

    @SubscribeEvent
    public static void onItemTicking(ItemEntityTickEvent event) {
        EventUtils.tickItemToUnlit(event, ModItems.TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemToUnlit(event, ModItems.SOUL_TORCH_LIT.get(), Items.STICK);
    }


    @SubscribeEvent
    public static void onTorchInRaining(ItemEntityTickEvent event) {
        EventUtils.tickItemInRaining(event, ModItems.TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemInRaining(event, ModItems.SOUL_TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemInRaining(event, ModItems.TORCH_UNLIT.get(), Items.STICK);
        EventUtils.tickItemInRaining(event, ModItems.SOUL_TORCH_UNLIT.get(), Items.STICK);
    }

    @SubscribeEvent
    public static void onItemInWater(ItemEntityTickEvent event) {
        EventUtils.tickItemInWater(event, ModItems.TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemInWater(event, ModItems.SOUL_TORCH_LIT.get(), Items.STICK);
        EventUtils.tickItemInWater(event, ModItems.TORCH_UNLIT.get(), Items.STICK);
        EventUtils.tickItemInWater(event, ModItems.SOUL_TORCH_UNLIT.get(), Items.STICK);
        EventUtils.tickItemInWater(event, Items.SPONGE, Items.WET_SPONGE);
    }
}
