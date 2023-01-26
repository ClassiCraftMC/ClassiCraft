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
package nameless.classicraft.mixin;

import nameless.classicraft.api.item.CCItemStack;
import nameless.classicraft.init.ModCapabilities;
import nameless.classicraft.init.ModTags;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(ItemStack.class)
public abstract class MixinItemStack implements CCItemStack {

    @Inject(method = "overrideOtherStackedOnMe", cancellable = true, at = @At("HEAD"))
    private void overrideOtherStackedOnMeCC(ItemStack other, Slot pSlot, ClickAction pAction,
                                            Player pPlayer, SlotAccess pAccess, CallbackInfoReturnable<Boolean> cir) {
        if (other.isEmpty() || (((ItemStack) (Object) this).isEmpty() || (((ItemStack) (Object) this).getItem() != other.getItem() || !Objects.equals((((ItemStack) (Object) this).getTag()), other.getTag())))) {
            cir.setReturnValue(false);
            return;
        }

        other.getCapability(ModCapabilities.ROT).ifPresent(rotOther -> {
            int count = other.getCount();

            getCapability(ModCapabilities.ROT).ifPresent(rotSelf -> {
                ItemStack itemStack = ((ItemStack) (Object) this);
                int selfCount = (((ItemStack) (Object) this).getCount());
                int finalCount = Math.min(count, (((ItemStack) (Object) this).getMaxStackSize() - selfCount));
                var finalRot = (rotOther.getRotValue() - rotSelf.getRotValue()) * finalCount / (selfCount + finalCount);

                other.shrink(finalCount);
                itemStack.grow(finalCount);

                rotSelf.setRotValue(finalRot + rotSelf.getRotValue());

                cir.setReturnValue(true);
            });
        });
    }

    @Inject(method = "getHoverName", at = @At("RETURN"), cancellable = true)
    private void getHoverNameCC(CallbackInfoReturnable<Component> cir) {
        if (Screen.hasShiftDown()) {
            getCapability(ModCapabilities.ROT).ifPresent(rot -> {
                if (rot.getHolder().getMax() > 0 && (((ItemStack) (Object) this).getItem() != Items.ROTTEN_FLESH)) {
                    Component origin = cir.getReturnValue();
                    cir.setReturnValue(rot.getLevelName().append(origin)
                            .setStyle(Style.EMPTY.withItalic(false)
                                    .withColor(rot.getLevelNameColor())));
                }
            });
        }
    }

    @Inject(method = "getUseAnimation", at = @At("RETURN"), cancellable = true)
    public void getUseAnimationCC(CallbackInfoReturnable<UseAnim> cir) {
        if (((ItemStack) (Object) this).is(ModTags.Items.TAG_DRINKABLE)
                || ((ItemStack) (Object) this).is(ModTags.Items.TAG_THICK)){
            cir.setReturnValue(UseAnim.DRINK);
        }
    }

    @Inject(method = "getDrinkingSound", at = @At("RETURN"), cancellable = true)
    public void getDrinkingSoundCC(CallbackInfoReturnable<SoundEvent> cir) {
        if (((ItemStack) (Object) this).is(ModTags.Items.TAG_THICK)) {
            cir.setReturnValue(SoundEvents.HONEY_DRINK);
        }
    }
}
