package nameless.classicraft.event;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.api.event.ProjectileHitEvent;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;

public class TestEvents {

    public static void init() {
        final IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(TestEvents::testEvent);
    }

    public static void testEvent(ProjectileHitEvent event) {
        Block block = event.getHitBlock();
        Projectile projectile = event.getEntity();
        if (projectile.isInPowderSnow) {

        }

    }

}
