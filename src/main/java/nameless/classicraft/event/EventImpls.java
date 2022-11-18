package nameless.classicraft.event;

import nameless.classicraft.api.event.MobInitGoalEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
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
}
