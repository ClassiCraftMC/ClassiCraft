package nameless.classicraft.mixin;

import nameless.classicraft.util.VanillaReplaceUtils;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class MixinItem {

    @Inject(method = "useOn", at = @At("HEAD"))
    private void useOnCC(UseOnContext pContext, CallbackInfoReturnable<InteractionResult> cir) {
        VanillaReplaceUtils.pebblePlace(pContext, Items.FLINT, cir);
        VanillaReplaceUtils.pebblePlace(pContext, Items.PRISMARINE_SHARD, cir);
    }
}
