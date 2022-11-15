package nameless.classicraft.event;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.api.event.PlayerRightClickBlockEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BlockEvents {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onCauldronInteract(PlayerRightClickBlockEvent event) {
        Block block = event.getBlock();
        Player player = event.getEntity();
        ItemStack itemStack = player.getMainHandItem();
        if (block.defaultBlockState().is(Blocks.CAULDRON)) {
            ClassiCraftMod.LOGGER.info("Test!");
            if (itemStack.is(Items.BLACK_WOOL)
                    && block.defaultBlockState().getValue(LayeredCauldronBlock.LEVEL) == 3) {
                itemStack.split(1);
                ItemStack newItem = new ItemStack(Items.WHITE_WOOL);
                player.getInventory().add(newItem);
                event.getLevel().setBlockAndUpdate(
                        event.getPos(),
                        block.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 2)
                );
                event.getLevel().playSound(null, event.getPos(), SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, event.getLevel().getRandom().nextFloat() * 0.1F + 0.9F);
            }
        }
    }
}
