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

import nameless.classicraft.api.item.MetaItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemUtils.class)
public class MixinItemUtils {

    @Inject(method = "createFilledResult(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;Z)Lnet/minecraft/world/item/ItemStack;", at = @At("HEAD"), cancellable = true)
    private static void createFilledResultCC(ItemStack pEmptyStack, Player pPlayer, ItemStack pFilledStack, boolean pPreventDuplicates, CallbackInfoReturnable<ItemStack> cir) {
        boolean flag = pPlayer.getAbilities().instabuild;
        if (pPreventDuplicates && flag) {
            if (!pPlayer.getInventory().contains(pFilledStack)) {
                pPlayer.getInventory().add(pFilledStack);
            }

            cir.setReturnValue(pEmptyStack);
        } else {
            if (pEmptyStack.getItem() instanceof BucketItem && pFilledStack.getItem() instanceof BucketItem) {
                MetaItem.copyMeta(pEmptyStack, pFilledStack);
            }
            if (pEmptyStack.getItem() instanceof BucketItem && pFilledStack.getItem() instanceof MilkBucketItem) {
                MetaItem.copyMeta(pEmptyStack, pFilledStack);
            }
            if (pEmptyStack.getItem() instanceof BucketItem && pFilledStack.getItem() instanceof SolidBucketItem) {
                MetaItem.copyMeta(pEmptyStack, pFilledStack);
            }

            if (!flag) {
                pEmptyStack.shrink(1);
            }

            if (pEmptyStack.isEmpty()) {
                cir.setReturnValue(pFilledStack);
            } else {
                if (!pPlayer.getInventory().add(pFilledStack)) {
                    pPlayer.drop(pFilledStack, false);
                }

                cir.setReturnValue(pEmptyStack);
            }
        }
    }
}
