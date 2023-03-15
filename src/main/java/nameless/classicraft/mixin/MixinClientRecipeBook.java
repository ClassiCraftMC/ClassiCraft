package nameless.classicraft.mixin;

import net.minecraft.client.ClientRecipeBook;
import net.minecraft.world.item.crafting.Recipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientRecipeBook.class)
public class MixinClientRecipeBook {

    @Inject(method = "setupCollections", at = @At("HEAD"), cancellable = true)
    private void onSetup(Iterable<Recipe<?>> pRecipes, CallbackInfo ci) {
        ci.cancel();
    }
}
