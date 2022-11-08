package nameless.classicraft.mixin;

import nameless.classicraft.ClassiCraftConfiguration;
import net.minecraft.world.level.storage.PrimaryLevelData;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PrimaryLevelData.class)
public abstract class MixinPrimaryLevelData {

    /**
     * Prevents Forge from showing the experimental warning screen ever, rather than showing it once per world.
     */
    @Inject(method = "hasConfirmedExperimentalWarning", at = @At("HEAD"), cancellable = true, remap = false)
    private void ignoreExperimentalSettingsScreen(CallbackInfoReturnable<Boolean> cir)
    {
        if (ClassiCraftConfiguration.shutupExperimentalWarning.get()) {
            cir.setReturnValue(true);
        }
    }
}