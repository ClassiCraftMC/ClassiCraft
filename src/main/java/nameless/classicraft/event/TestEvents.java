package nameless.classicraft.event;

import nameless.classicraft.api.event.ItemEntityTickEvent;
import nameless.classicraft.util.EventUtils;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TestEvents {

    @SubscribeEvent
    public static void test(ItemEntityTickEvent event) {
        EventUtils.onHit(event);
    }

    @SubscribeEvent
    public static void test0(ItemTooltipEvent event) {
        EventUtils.appendTooltip(event);
    }
}
