package nameless.classicraft.event;

import nameless.classicraft.api.event.ItemEntityTickEvent;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TestEvents {

    @SubscribeEvent
    public static void changeLooseRock(ItemEntityTickEvent event) {
        ItemEntity entity = event.getEntity();
        Block block = entity.getFeetBlockState().getBlock();
            if (entity.getItem().is(Items.BONE)) {
                ItemEntity newItem = new
                        ItemEntity(entity.getLevel(),
                        entity.getX(), entity.getY(), entity.getZ(),
                        Items.STONE_BRICKS.getDefaultInstance());
        }
    }
}
