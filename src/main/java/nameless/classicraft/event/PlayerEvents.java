package nameless.classicraft.event;

import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import nameless.classicraft.util.EventUtils;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author wdog5
 */
@Mod.EventBusSubscriber
public class PlayerEvents {

    @SubscribeEvent
    public static void pebbleTool(PlayerInteractEvent.RightClickItem event) {
        EventUtils.pebbleToolByHandVanilla(event, Items.FLINT);
        EventUtils.pebbleToolByHandVanilla(event, Items.PRISMARINE_SHARD);
        EventUtils.pebbleToolByHandVanilla(event, Items.QUARTZ);
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
