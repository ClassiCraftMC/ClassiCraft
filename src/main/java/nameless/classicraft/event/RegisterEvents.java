package nameless.classicraft.event;

import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModCreativeModeTabs;
import nameless.classicraft.init.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber
public class RegisterEvents {

    @SubscribeEvent
    public static void registerCreativeModeTab(CreativeModeTabEvent.BuildContents event) {
        for (RegistryObject<Item> items : ModItems.ITEMS.getEntries()) {
            event.registerSimple(ModCreativeModeTabs.COMMON, items.get());
        }
        for (RegistryObject<Block> blocks : ModBlocks.BLOCKS.getEntries()) {
            event.registerSimple(ModCreativeModeTabs.COMMON, blocks.get());
        }
    }
}
