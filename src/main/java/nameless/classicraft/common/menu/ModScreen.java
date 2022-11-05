package nameless.classicraft.common.menu;

import net.minecraft.client.gui.screens.MenuScreens;

public class ModScreen {

    public static void registerScreen() {
        MenuScreens.register(ModMenuTypes.STONE_MORTAR_BLOCK_CONTAINER.get(), StoneMortarBlockScreen::new);
    }
}
