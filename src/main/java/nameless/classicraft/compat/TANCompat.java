package nameless.classicraft.compat;

import nameless.classicraft.api.event.LivingEatEvent;
import nameless.classicraft.init.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import toughasnails.api.potion.TANEffects;

@Mod.EventBusSubscriber
public class TANCompat {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void initTANCompat(LivingEatEvent event) {
        ItemStack food = event.getFood();
        LivingEntity entity = event.getEntity();
        if (ModList.get().isLoaded("toughasnails")) {
            if (food.is(ModItems.RAW_SALT.get()) || food.is(ModItems.SALT.get())) {
                entity.addEffect(new MobEffectInstance(TANEffects.THIRST.get(), 100, 1), entity);
            }
            event.setCanceled(true);
        }
    }
}
