package nameless.classicraft.event;

import nameless.classicraft.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class LivingEntityEvents {

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        Level level = entity.getLevel();
        BlockPos pos = entity.getOnPos();
        if (entity instanceof Animal) {
            ItemEntity itemEntity = new ItemEntity(level,
                    pos.getX(), pos.getY(), pos.getZ(), ModItems.TALLOW.get().getDefaultInstance());
            level.addFreshEntity(itemEntity);
        }
    }
}
