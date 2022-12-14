package org.bedracket.classicraft.init;

import net.minecraft.client.renderer.item.CompassItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import org.bedracket.classicraft.item.DepthMeterItem;

public class ModModelPredicates {

    public static void registerModelPredicates() {
        ItemProperties.register(ModItems.DEPTH_METER.get(),
                new ResourceLocation("angle"),
                new CompassItemPropertyFunction((level, stack, pEntity)
                        -> DepthMeterItem.isLodestoneCompass(stack) ?
                        DepthMeterItem.getLodestonePosition(stack.getOrCreateTag())
                        : DepthMeterItem.getSpawnPosition(level)));
    }
}
