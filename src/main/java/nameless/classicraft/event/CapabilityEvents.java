/*
 * ClassiCraft - ClassiCraftMC
 * Copyright (C) 2018-2022.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
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

@Mod.EventBusSubscriber(modid = ClassiCraftMod.MOD_ID)
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
