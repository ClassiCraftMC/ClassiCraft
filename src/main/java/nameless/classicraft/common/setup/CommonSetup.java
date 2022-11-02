package nameless.classicraft.common.setup;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import nameless.classicraft.common.item.ModItems;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * @author DustW
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetup {
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        boolean hasRottenFood = ComposterBlock.COMPOSTABLES.containsKey(ModItems.ROTTEN_FOOD.get());

        if (!hasRottenFood) {
            ComposterBlock.COMPOSTABLES.put(ModItems.ROTTEN_FOOD.get(), .3F);
        }
    }
}
