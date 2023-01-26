package nameless.classicraft.mixin;

import nameless.classicraft.api.event.ItemEntityTickEvent;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author wdog5
 */
@Mixin(ItemEntity.class)
public class MixinItemEntity {

    @Inject(method = "tick", at = @At("TAIL"))
    private void callItemTickEvent(CallbackInfo ci) {
        ItemEntityTickEvent event = new ItemEntityTickEvent(((ItemEntity) (Object) this));
        MinecraftForge.EVENT_BUS.post(event);
    }
}
