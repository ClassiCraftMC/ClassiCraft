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

import nameless.classicraft.api.event.ItemTickInventoryEvent;
import nameless.classicraft.item.AttachFoods;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class MixinItem {

    @Inject(method = "getFoodProperties", cancellable = true, at = @At("RETURN"))
    private void getFoodPropertiesCC(CallbackInfoReturnable<FoodProperties> cir) {
        if (cir.getReturnValue() == null && AttachFoods.isAttach(((Item) (Object) this))) {
            cir.setReturnValue(AttachFoods.getFood(((Item) (Object) this)));
        }
    }

    @Inject(method = "isEdible", cancellable = true, at = @At("RETURN"))
    private void isEdibleCC(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(cir.getReturnValue() || AttachFoods.isAttach(((Item) (Object) this)));
    }

    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void callItemTickInventoryEvent(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected, CallbackInfo ci) {
        ItemTickInventoryEvent event = new ItemTickInventoryEvent(pStack, pLevel, pEntity, pSlotId, pIsSelected);
        MinecraftForge.EVENT_BUS.post(event);
    }
}