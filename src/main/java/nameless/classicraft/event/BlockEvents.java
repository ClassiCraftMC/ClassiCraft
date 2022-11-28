package nameless.classicraft.event;

import nameless.classicraft.api.event.BlockDropEvent;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.util.EventUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BlockEvents {

    @SubscribeEvent
    public static void grow(PlayerInteractEvent.RightClickBlock event) {
        BlockPos pos = event.getPos();
        Level level = event.getLevel();
        BlockState blockState = level.getBlockState(pos);
        Player player = event.getEntity();
        ItemStack itemStack = player.getMainHandItem();
        BlockState stateAbove = level.getBlockState(pos.above());
        if (blockState.is(Blocks.OAK_LOG)) {
            if (stateAbove.is(Blocks.OAK_SAPLING)) {
                if (itemStack.is(Items.BONE_MEAL)) {
                    level.setBlockAndUpdate(pos.above(), Blocks.OAK_LEAVES.defaultBlockState());
                }
            }
            if (stateAbove.is(Blocks.OAK_LEAVES)) {
                if (itemStack.is(Items.BONE_MEAL)) {
                    level.setBlockAndUpdate(pos.above().above(), Blocks.PLAYER_HEAD.defaultBlockState());
                }
            }
        }
    }

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
