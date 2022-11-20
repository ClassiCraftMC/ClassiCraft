package nameless.classicraft.event;

import nameless.classicraft.api.event.LivingEatEvent;
import nameless.classicraft.api.event.MobInitGoalEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EventImpls {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void callMobInitGoalEvent(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        Level level = event.getLevel();
        if (level != null
                && !level.isClientSide
                && entity instanceof Mob) {
            MobInitGoalEvent mobInitGoalEvent =
                    new MobInitGoalEvent((Mob) entity,
                            ((Mob) entity).goalSelector,
                            ((Mob) entity).targetSelector);
            MinecraftForge.EVENT_BUS.post(mobInitGoalEvent);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void callLivingEatEvent(LivingEntityUseItemEvent.Finish event) {
        Level level = event.getEntity().getLevel();
        ItemStack food = event.getItem();
        LivingEntity entity = event.getEntity();
        LivingEatEvent eatEvent = new LivingEatEvent(entity, level, food);
        if (!food.isEdible()) {
            event.setCanceled(true);
        } else {
            MinecraftForge.EVENT_BUS.post(eatEvent);
        }
    }
}
