package nameless.classicraft.util;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 一些方法用于替换原版内容
 */
public class VanillaReplaceUtils {

    public static void pebblePlace(UseOnContext pContext, Item vanilla, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack stack = pContext.getItemInHand();
        if (stack.is(vanilla)) {
            cir.setReturnValue(InteractionResult.PASS);
        }
    }
}
