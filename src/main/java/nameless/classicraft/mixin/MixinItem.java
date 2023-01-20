package nameless.classicraft.mixin;

import nameless.classicraft.api.event.ItemTickInventoryEvent;
import nameless.classicraft.item.AttachFoods;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author DustW
 */
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

    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void callItemTickInventoryEvent(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected, CallbackInfo ci) {
        ItemTickInventoryEvent event = new ItemTickInventoryEvent(pStack.getItem(), pLevel, pEntity, pSlotId, pIsSelected);
        MinecraftForge.EVENT_BUS.post(event);
    }
}