package nameless.classicraft.mixin;

import nameless.classicraft.common.capability.ModCapabilities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author DustW
 */
@Mixin(BlockItem.class)
public abstract class MixinBlockItem extends Item {
    public MixinBlockItem(Properties pProperties) {
        super(pProperties);
    }

    @Inject(method = "getBlockEntityData", at = @At("RETURN"), cancellable = true)
    private static void getBlockEntityDataCC(ItemStack stack, CallbackInfoReturnable<CompoundTag> cir) {
        CompoundTag returnValue = cir.getReturnValue();

        if (returnValue == null) {
            returnValue = new CompoundTag();
        }

        CompoundTag finalReturnValue = returnValue;
        stack.getCapability(ModCapabilities.ROT).ifPresent(rot -> {
            finalReturnValue.putFloat("rot", rot.getRotValue());
            cir.setReturnValue(finalReturnValue);
        });
    }

}
