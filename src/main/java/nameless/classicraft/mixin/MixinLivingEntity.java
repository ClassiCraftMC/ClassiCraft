package nameless.classicraft.mixin;

import nameless.classicraft.api.event.LivingEatEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {

    @Inject(method = "eat", at = @At("TAIL"))
    private void callLivingEatEvent(Level pLevel, ItemStack pFood, CallbackInfoReturnable<ItemStack> cir) {
        LivingEatEvent event = new LivingEatEvent(((LivingEntity) (Object) this), pLevel, pFood);
        if (pFood == null || !pFood.isEdible()) {
            event.setCanceled(true);
        }
        MinecraftForge.EVENT_BUS.post(event);
    }
}
