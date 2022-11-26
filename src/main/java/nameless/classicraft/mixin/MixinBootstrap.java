package nameless.classicraft.mixin;

import io.netty.bootstrap.Bootstrap;
import nameless.classicraft.util.LightUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Bootstrap.class)
public class MixinBootstrap {

    @Inject(method = "<init>()V", at = @At(value = "TAIL", target = "Lnet/minecraft/world/level/block/ComposterBlock;bootStrap()V"))
    private static void addBootstrap(CallbackInfo ci) {
        LightUtils.bootstrapFuel();
    }
}
