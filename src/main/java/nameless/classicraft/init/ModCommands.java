/*
 * ClassiCraft - ClassiCraftMC
 * Copyright (C) 2018-2022.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package nameless.classicraft.init;

import com.mojang.brigadier.arguments.FloatArgumentType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.api.san.ISanHandler;
import nameless.classicraft.capability.rot.AbstractRot;
import nameless.classicraft.rot.RotManager;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

@Mod.EventBusSubscriber
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModCommands {
    @SubscribeEvent
    public static void onServerStaring(RegisterCommandsEvent event) {
        var dispatcher = event.getDispatcher();

        String valueKey = "value";
        String percentKey = "percent";

        dispatcher.register(
                literal(ClassiCraftMod.MOD_ID).then(
                        literal("rot").then(
                                literal(valueKey).then(
                                        argument(valueKey, FloatArgumentType.floatArg())
                                                .requires(commandSource -> commandSource.hasPermission(2))
                                                .executes(c -> {
                                                    CommandSourceStack source = c.getSource();
                                                    ServerPlayer player = source.getPlayerOrException();
                                                    ItemStack stack = player.getMainHandItem();
                                                    stack.getCapability(ModCapabilities.ROT).ifPresent(rot -> {
                                                        float value = c.getArgument(valueKey, Float.class);
                                                        rot.setRotValue(value);
                                                        source.sendSuccess(successMsg(rot), false);
                                                    });
                                                    return 1;
                                                })
                                )
                        ).then(
                                literal(percentKey).then(
                                        argument(percentKey, FloatArgumentType.floatArg())
                                                .requires(commandSource -> commandSource.hasPermission(2))
                                                .executes(c -> {
                                                    CommandSourceStack source = c.getSource();
                                                    ServerPlayer player = source.getPlayerOrException();
                                                    ItemStack stack = player.getMainHandItem();
                                                    stack.getCapability(ModCapabilities.ROT).ifPresent(rot -> {
                                                        float percent = c.getArgument(percentKey, Float.class) / 100;
                                                        rot.setRotValue(rot.getHolder().getMax() * percent);
                                                        source.sendSuccess(successMsg(rot), false);
                                                    });
                                                    return 1;
                                                })
                                )
                        )
                ).then(
                        literal("baseSpeed").then(
                                argument("speed", FloatArgumentType.floatArg())
                                        .requires(source -> source.hasPermission(2))
                                        .executes(c -> {
                                            float speed = c.getArgument("speed", Float.class);
                                            RotManager.INSTANCE.setBaseSpeed(speed);
                                            c.getSource().sendSuccess(
                                                    Component.translatable("设置成功，当前为: %.1f".formatted(speed)),
                                                    false);
                                            return 1;
                                        })
                        )
                )
        );

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

    static Component successMsg(AbstractRot rot) {
        float current = rot.getRotValue();
        float max = rot.getHolder().getMax();

        return Component.translatable("设置成功，当前: %.1f / %.1f (%.1f%%)".formatted(current, max, current * 100 / max));
    }
}