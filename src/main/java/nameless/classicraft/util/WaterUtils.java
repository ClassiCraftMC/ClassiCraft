package nameless.classicraft.util;

import nameless.classicraft.capability.ModCapabilities;
import nameless.classicraft.init.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Random;

public class WaterUtils {

    public static void drinkWaterBlock(Player player) {
        Level world = player.level;
        player.getCapability(ModCapabilities.PLAYER_WATER_LEVEL).ifPresent(data -> {
            data.addWaterLevel(player, 1);
            world.playSound(player, new BlockPos(player.getPosition(0f)), SoundEvents.GENERIC_DRINK, SoundSource.PLAYERS, 0.4f, 1.0f);
            if (!world.isClientSide()) {
                Random random = new Random();
                double d1 = random.nextDouble();
                double d2 = random.nextDouble();
                if (d1 <= 0.05D) player.addEffect(new MobEffectInstance(MobEffects.POISON, 300, 0));
                if (d2 <= 0.8D) player.addEffect(new MobEffectInstance(ModEffects.THIRST.get(), 900, 0));
            }
        });
    }

}
