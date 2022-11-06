package nameless.classicraft.init;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemPropertiesRegistry {
    @SubscribeEvent
    public static void onEvent(FMLClientSetupEvent event) {
        ItemProperties.register(ModBlocks.MUSHROOM_PLANTER.get().asItem(), new ResourceLocation("dirt"),
                (pStack, pLevel, pEntity, pSeed) -> {
                    CompoundTag tag = pStack.getTag();
                    if (tag != null)
                        return tag.getInt("dirt");
                    return 0;
                });

        ItemProperties.register(ModBlocks.MUSHROOM_PLANTER.get().asItem(), new ResourceLocation("wood"),
                (pStack, pLevel, pEntity, pSeed) -> {
                    CompoundTag tag = pStack.getTag();
                    if (tag != null)
                        return tag.getInt("wood");
                    return 0;
                });
    }
}
