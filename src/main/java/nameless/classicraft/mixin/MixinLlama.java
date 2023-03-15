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

import nameless.classicraft.init.ModBlocks;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Llama.class)
public class MixinLlama {

    private static final Ingredient CC_HORSE_FOOD_ITEMS =
            Ingredient.of(ModBlocks.THATCH.get(), ModBlocks.DRIED_THATCH.get());

    @Inject(method = "isFood", at = @At("RETURN"), cancellable = true)
    private void cc_added_isFood(ItemStack pStack, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(((Llama) (Object) this).FOOD_ITEMS.test(pStack)
                        || CC_HORSE_FOOD_ITEMS.test(pStack));
    }
}
