package org.bedracket.classicraft.mixin.event;

import net.minecraft.world.entity.item.ItemEntity;
import org.bedracket.classicraft.api.event.ItemTickEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public class MixinItemEntity {

    @Inject(method = "tick", at = @At("RETURN"))
    private void callItemTickEvent(CallbackInfo ci) {
        ItemTickEvent.ITEM_ENTITY_POST.invoker().tick(((ItemEntity) (Object) this));
    }
}
