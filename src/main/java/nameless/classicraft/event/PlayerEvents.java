package nameless.classicraft.event;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerEvents {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onHarvestCheck(PlayerEvent.HarvestCheck event)
    {
        BlockState blockState = event.getTargetBlock();
        if (blockState.is(Tags.Blocks.NEEDS_WOOD_TOOL)
                || blockState.is(Tags.Blocks.NEEDS_GOLD_TOOL)
                || blockState.is(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                || blockState.is(BlockTags.NEEDS_STONE_TOOL)
                || blockState.is(BlockTags.NEEDS_IRON_TOOL)
                || blockState.is(BlockTags.NEEDS_DIAMOND_TOOL)
                || blockState.is(BlockTags.LOGS)
                || blockState.is(BlockTags.PLANKS)
                || blockState.is(BlockTags.ACACIA_LOGS)
                || blockState.is(BlockTags.BIRCH_LOGS)
                || blockState.is(BlockTags.JUNGLE_LOGS)
                || blockState.is(BlockTags.DARK_OAK_LOGS)
                || blockState.is(BlockTags.MANGROVE_LOGS)
                || blockState.is(BlockTags.OAK_LOGS)
                || blockState.is(BlockTags.ACACIA_LOGS)) {
            event.setCanHarvest(false);
        }
    }
}
