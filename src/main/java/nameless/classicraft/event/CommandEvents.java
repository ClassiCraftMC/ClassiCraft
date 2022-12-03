package nameless.classicraft.event;

import com.mojang.brigadier.arguments.FloatArgumentType;
import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.api.san.ISanHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CommandEvents {

    @SubscribeEvent
    public static void onServerStaring(RegisterCommandsEvent event) {
        var dispatcher = event.getDispatcher();

        dispatcher.register(Commands.literal(ClassiCraftMod.MOD_ID).then(
                Commands.literal("addSan").then(
                        Commands.argument("san", FloatArgumentType.floatArg())
                                .requires(source -> source.hasPermission(2))
                                .executes(c -> {
                                            float san = c.getArgument("san", Float.class);
                                            Player player = Minecraft.getInstance().player;
                                            if (player != null) {
                                                ((ISanHandler) player).regenSan(san);
                                                c.getSource().sendSuccess(
                                                        Component.translatable("设置成功，当前为: %.1f".formatted(san)),
                                                        false);
                                            }
                                            return 1;
                                        })
                )
        ));
    }
}
