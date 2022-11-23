package nameless.classicraft.init;

import nameless.classicraft.client.screen.FridgeScreen;
import nameless.classicraft.client.screen.PolishScreen;
import nameless.classicraft.client.screen.StoneMortarBlockScreen;
import nameless.classicraft.client.screen.WoodcutterScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModScreens {

    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(ModMenuTypes.FRIDGE.get(), FridgeScreen::new);
            MenuScreens.register(ModMenuTypes.STONE_MORTAR_BLOCK_CONTAINER.get(), StoneMortarBlockScreen::new);
            MenuScreens.register(ModMenuTypes.WOODCUTTER_CONTAINER.get(), WoodcutterScreen::new);
            MenuScreens.register(ModMenuTypes.POLISH_CONTAINER.get(), PolishScreen::new);
        });
    }
}
