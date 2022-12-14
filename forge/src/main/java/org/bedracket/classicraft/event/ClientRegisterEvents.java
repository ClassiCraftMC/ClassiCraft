package org.bedracket.classicraft.event;

import net.minecraft.client.renderer.item.CompassItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.bedracket.classicraft.ClassiCraftMod;
import org.bedracket.classicraft.init.ModItems;
import org.bedracket.classicraft.item.DepthMeterItem;

@Mod.EventBusSubscriber(modid = ClassiCraftMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegisterEvents {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void registerItemOverrides(FMLClientSetupEvent event) {
        ItemProperties.register(ModItems.DEPTH_METER.get(),
                new ResourceLocation("angle"),
                new CompassItemPropertyFunction((level, stack, pEntity)
                        -> DepthMeterItem.isLodestoneCompass(stack) ?
                        DepthMeterItem.getLodestonePosition(stack.getOrCreateTag())
                        : DepthMeterItem.getSpawnPosition(level)));
    }
}
