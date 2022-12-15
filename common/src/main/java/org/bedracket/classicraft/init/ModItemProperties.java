package org.bedracket.classicraft.init;

import dev.architectury.registry.item.ItemPropertiesRegistry;
import net.minecraft.client.renderer.item.CompassItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import org.bedracket.classicraft.item.DepthMeterItem;

public class ModItemProperties {

    public static void registerItemProperties() {
        ItemPropertiesRegistry.register(ModItems.DEPTH_METER.get(),
                new ResourceLocation("angle"),
                new CompassItemPropertyFunction((level, stack, pEntity)
                        -> DepthMeterItem.isLodestoneCompass(stack) ?
                        DepthMeterItem.getLodestonePosition(stack.getOrCreateTag())
                        : DepthMeterItem.getSpawnPosition(level)));
    }
}
