package nameless.classicraft.util;

import nameless.classicraft.api.san.ISanHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

public class SanUtils {

    public static boolean checkSanExist() {
        PlayerEntity player = MinecraftClient.getInstance().player;
        assert player != null;
        if (player.isCreative()) {
            return true;
        } else {
            return ((ISanHandler) player).getSan() > 0;
        }
    }
}
