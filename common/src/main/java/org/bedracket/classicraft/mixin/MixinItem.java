package org.bedracket.classicraft.mixin;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import org.bedracket.classicraft.init.AttachFoods;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
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
}
