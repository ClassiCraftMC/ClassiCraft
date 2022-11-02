package nameless.classicraft.mixin;

import nameless.classicraft.common.item.AttachFoods;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author DustW
 */
@Mixin(Item.class)
public abstract class MixinItem {
    @Shadow public abstract Item asItem();

    @Inject(method = "getFoodProperties", cancellable = true, at = @At("RETURN"))
    private void getFoodPropertiesCC(CallbackInfoReturnable<FoodProperties> cir) {
        if (cir.getReturnValue() == null && AttachFoods.isAttach(asItem())) {
            cir.setReturnValue(AttachFoods.getFood(asItem()));
        }
    }

    @Inject(method = "isEdible", cancellable = true, at = @At("RETURN"))
    private void isEdibleCC(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(cir.getReturnValue() || AttachFoods.isAttach(asItem()));
    }
}
