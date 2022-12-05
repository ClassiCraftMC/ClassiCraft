package nameless.classicraft.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.capability.rot.EmptyRot;
import nameless.classicraft.capability.rot.NormalRot;
import nameless.classicraft.capability.rot.RotCapabilityProvider;
import nameless.classicraft.init.ModCapabilities;
import nameless.classicraft.init.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CapabilityEvents {

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        if (event.getObject().is(ModItems.ROTTEN_FOOD.get())) {
            attach(event, "rot", new RotCapabilityProvider(LazyOptional.of(EmptyRot::new)));
        } else if (NormalRot.canUse(event.getObject())) {
            attach(event, "rot", new RotCapabilityProvider(LazyOptional.of(() -> new NormalRot(event.getObject()))));
        }
    }

    static void attach(AttachCapabilitiesEvent<?> event, String name, ICapabilityProvider provider) {
        event.addCapability(new ResourceLocation(ClassiCraftMod.MOD_ID, name), provider);
    }

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        event.getItemStack().getCapability(ModCapabilities.ROT).ifPresent(rot -> {
            if (rot.isHasExMsg() && !event.getItemStack().is(Items.ROTTEN_FLESH)) {
                event.getToolTip().addAll(rot.getMsg());
            }
        });
    }
}
