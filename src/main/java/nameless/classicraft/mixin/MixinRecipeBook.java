package nameless.classicraft.mixin;

import net.minecraft.stats.RecipeBook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RecipeBook.class)
public class MixinRecipeBook {

    @Inject(method = "copyOverData", at = @At("HEAD"), cancellable = true)
    private void onCopy(RecipeBook pOther, CallbackInfo ci) {
        ci.cancel();
    }
}