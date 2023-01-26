package nameless.classicraft.event;

import nameless.classicraft.api.event.BlockDropEvent;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.util.EventUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DeadBushBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author wdog5
 */
@Mod.EventBusSubscriber
public class BlockEvents {

    @SubscribeEvent
    public static void pebbleTool(PlayerInteractEvent.RightClickBlock event) {
        EventUtils.pebbleToolByStoneVanilla(event, Items.FLINT);
        EventUtils.pebbleToolByStoneVanilla(event, Items.PRISMARINE_SHARD);
        EventUtils.pebbleToolByStoneVanilla(event, Items.QUARTZ);
    }

    @SubscribeEvent
    public static void putPebbleVanilla(PlayerInteractEvent.RightClickBlock event) {
        EventUtils.putPebbleBlock(event, Items.FLINT, ModBlocks.FLINT.get());
        EventUtils.putPebbleBlock(event, Items.PRISMARINE_SHARD, ModBlocks.PRISMARINE.get());
        EventUtils.putPebbleBlock(event, Items.QUARTZ, ModBlocks.QUARTZ_PEBBLE.get());
    }

    @SubscribeEvent
    public static void blockDropItems(BlockDropEvent event) {
        Block block = event.getState().getBlock();
        if (!event.getPlayer().isCreative()) {
            if (block instanceof LeavesBlock
                    || block instanceof DeadBushBlock) {
                EventUtils.blockdropRandom(event, block, Items.STICK);
            }
        }
    }
}
