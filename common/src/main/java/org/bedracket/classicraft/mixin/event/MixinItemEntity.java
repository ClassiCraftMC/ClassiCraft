package org.bedracket.classicraft.mixin.event;

import net.minecraft.world.entity.item.ItemEntity;
import org.bedracket.event.entity.item.ItemTickEvent;
import org.bedracket.eventbus.BedRacket;
import org.bedracket.eventbus.EventException;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public class MixinItemEntity {

    @Inject(method = "tick", at = @At("TAIL"))
    private void callItemTickEvent(CallbackInfo ci) throws EventException {
        BedRacket.EVENT_BUS.post(ItemTickEvent.class, new ItemTickEvent(((ItemEntity) (Object) this)));
    }
}
