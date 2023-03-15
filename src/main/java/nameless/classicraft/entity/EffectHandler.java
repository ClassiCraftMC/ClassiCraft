package nameless.classicraft.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import nameless.classicraft.init.ModEffects;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EffectHandler {
    @SubscribeEvent
    public static void onEvent(MobEffectEvent.Added event) {
        if (event.getEffectInstance().getEffect() == ModEffects.MILK.get()) {
            ModEffects.MILK.get().applyInstantenousEffect(event.getEffectSource(), null,
                    event.getEntity(), 1, 1);
        }
    }
}
