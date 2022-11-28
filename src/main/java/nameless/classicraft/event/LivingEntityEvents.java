package nameless.classicraft.event;

import nameless.classicraft.init.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class LivingEntityEvents {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void addGoal(EntityJoinLevelEvent event) {
        Level level = event.getLevel();
        Entity entity = event.getEntity();
        if (!level.isClientSide) {
            if (entity instanceof Monster
                    && ((Monster) entity).getTarget() != null
                    && ((Monster) entity).getTarget() instanceof Player
                    && !(((Monster) entity).getTarget() instanceof FakePlayer)
                    && ((Monster) entity).getTarget().getMainHandItem().is(ModItems.SOUL_TORCH_LIT.get())) {
                ((Monster) entity).goalSelector
                        .addGoal(1 ,
                                new AvoidEntityGoal<>((PathfinderMob) entity, Player.class,
                                        1.0F,
                                        1.0D,
                                        1.0D));
            }
        }
    }
}
