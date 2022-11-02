package nameless.classicraft.common.capability;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import nameless.classicraft.ClassiCraft;
import nameless.classicraft.common.capability.rot.EmptyRot;
import nameless.classicraft.common.capability.rot.NormalRot;
import nameless.classicraft.common.capability.rot.RotCapabilityProvider;
import nameless.classicraft.common.item.ModItems;
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
public class CommonCapabilityEventListener {

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        if (event.getObject().is(ModItems.ROTTEN_FOOD.get())) {
            attach(event, "rot", new RotCapabilityProvider(LazyOptional.of(EmptyRot::new)));
        } else if (NormalRot.canUse(event.getObject())) {
            attach(event, "rot", new RotCapabilityProvider(LazyOptional.of(() -> new NormalRot(event.getObject()))));
        }
    }

    static void attach(AttachCapabilitiesEvent<?> event, String name, ICapabilityProvider provider) {
        event.addCapability(new ResourceLocation(ClassiCraft.MODID, name), provider);
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
