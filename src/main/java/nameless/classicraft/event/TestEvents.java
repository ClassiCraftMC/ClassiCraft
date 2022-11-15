package nameless.classicraft.event;

import nameless.classicraft.api.event.MobInitGoalEvent;
import nameless.classicraft.entity.goal.PanicSoulLanternGoal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;

public class TestEvents {

    public static void init() {
        final IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(TestEvents::testEvent);
    }

    public static void testEvent(MobInitGoalEvent event) {
        Mob mob = event.getMob();
        if (mob instanceof Sheep) {
            event.getGoalSelector().addGoal(3, new PanicSoulLanternGoal((PathfinderMob) mob, 1.25D));
        }
    }

}
