package nameless.classicraft.event;

import nameless.classicraft.api.event.BlockDropEvent;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.util.EventUtils;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BlockEvents {

    @SubscribeEvent
    public static void blockDropItems(BlockDropEvent event) {
        if (!event.getPlayer().isCreative()) {
            EventUtils.blockdropAtOnce(event, ModBlocks.REAL_TORCH.get(), Items.STICK);
            EventUtils.blockdropAtOnce(event, ModBlocks.REAL_WALL_TORCH.get(), Items.STICK);
            EventUtils.blockdropAtOnce(event, ModBlocks.REAL_SOUL_TORCH.get(), Items.STICK);
            EventUtils.blockdropAtOnce(event, ModBlocks.REAL_SOUL_WALL_TORCH.get(), Items.STICK);
        }
    }
}
