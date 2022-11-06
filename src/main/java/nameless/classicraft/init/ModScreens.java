package nameless.classicraft.init;

import nameless.classicraft.menu.StoneMortarBlockScreen;
import net.minecraft.client.gui.screens.MenuScreens;

public class ModScreens {

    public static void registerScreen() {
        MenuScreens.register(ModMenuTypes.STONE_MORTAR_BLOCK_CONTAINER.get(), StoneMortarBlockScreen::new);
    }

}
