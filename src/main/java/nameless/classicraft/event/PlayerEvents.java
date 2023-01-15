package nameless.classicraft.event;

import nameless.classicraft.block.StonePebbleBlock;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import nameless.classicraft.init.ModTags;
import nameless.classicraft.util.EventUtils;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerEvents {

    @SubscribeEvent
    public static void pebbleTool(PlayerInteractEvent.RightClickItem event) {
        EventUtils.pebbleToolByHandVanilla(event, Items.FLINT, "flint");
        EventUtils.pebbleToolByHandVanilla(event, Items.PRISMARINE_SHARD, "prismarine");
    }

    @SubscribeEvent
    public static void polishStone(PlayerInteractEvent.LeftClickBlock event) {
        Player player = event.getEntity();
        Block block = player.getBlockStateOn().getBlock();
        ItemStack itemStack = player.getMainHandItem();
        if (block instanceof StonePebbleBlock) {
            if (itemStack.is(ModTags.Items.STONE_PEBBLES)) {

            }
        }
    }

    @SubscribeEvent
    public static void rightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        EventUtils.litItem(event, ModBlocks.REAL_TORCH.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(event, ModBlocks.REAL_SOUL_TORCH.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(event, ModBlocks.REAL_WALL_TORCH.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(event, ModBlocks.REAL_SOUL_WALL_TORCH.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(event, ModBlocks.REAL_TORCH.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(event, ModBlocks.REAL_SOUL_TORCH.get(), ModItems.SOUL_TORCH_UNLIT.get(), ModItems.SOUL_TORCH_LIT.get());
        EventUtils.litItem(event, ModBlocks.REAL_WALL_TORCH.get(), ModItems.SOUL_TORCH_UNLIT.get(), ModItems.SOUL_TORCH_LIT.get());
        EventUtils.litItem(event, ModBlocks.REAL_SOUL_WALL_TORCH.get(), ModItems.SOUL_TORCH_UNLIT.get(), ModItems.SOUL_TORCH_LIT.get());
        EventUtils.litBlock(event, ModBlocks.REAL_TORCH.get(), Items.SOUL_SOIL, ModBlocks.REAL_SOUL_TORCH.get());
    }

    @SubscribeEvent
    public static void rightClickItem(PlayerInteractEvent.RightClickItem event) {
        EventUtils.shiftRightItem(event, ModItems.TORCH_LIT.get(), Items.STICK.getDefaultInstance());
        EventUtils.shiftRightItem(event, ModItems.SOUL_TORCH_LIT.get(), Items.STICK.getDefaultInstance());
       /**
        EventUtils.litItem(event, Items.FLINT_AND_STEEL, ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(event, Items.FLINT_AND_STEEL, ModItems.SOUL_TORCH_UNLIT.get(), ModItems.SOUL_TORCH_LIT.get());
        */
        EventUtils.litItem(event, ModItems.TORCH_LIT.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(event, ModItems.SOUL_TORCH_LIT.get(), ModItems.SOUL_TORCH_UNLIT.get(), ModItems.SOUL_TORCH_LIT.get());
        EventUtils.litItem(event, ModItems.SOUL_TORCH_LIT.get(), ModItems.TORCH_UNLIT.get(), ModItems.TORCH_LIT.get());
        EventUtils.litItem(event, ModItems.TORCH_LIT.get(), ModItems.SOUL_TORCH_UNLIT.get(), ModItems.SOUL_TORCH_LIT.get());
        EventUtils.litItem(event, Items.SOUL_SOIL.asItem(), ModItems.TORCH_UNLIT.get(), ModItems.SOUL_TORCH_UNLIT.get());
        EventUtils.litItem(event, Items.SOUL_SOIL.asItem(), ModItems.TORCH_LIT.get(), ModItems.SOUL_TORCH_LIT.get());
    }
}
