package nameless.classicraft.util;

import nameless.classicraft.api.san.ISanHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class SanUtils {

    public static boolean checkSanExist() {
        Player player = Minecraft.getInstance().player;
        assert player != null;
        if (player.isCreative()) {
            return true;
        } else {
            return ((ISanHandler) player).getSan() > 0;
        }
    }
}
