package nameless.classicraft.mixin;

import nameless.classicraft.api.event.LivingEatEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {

    @Inject(method = "addEatEffect", at = @At("HEAD"))
    private void callLivingEatEvent(ItemStack pFood, Level pLevel, LivingEntity pLivingEntity, CallbackInfo ci) {
        LivingEatEvent event = new LivingEatEvent(((LivingEntity) (Object) this), pLevel, pFood);
        if (pFood == null || !pFood.isEdible()) {
            event.setCanceled(true);
        }
        MinecraftForge.EVENT_BUS.post(event);
    }
}
