package nameless.classicraft.mixin;

import nameless.classicraft.api.event.MobInitGoalEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mob.class)
public abstract class MixinMob {

    @Inject(method = "<init>", at =@At("TAIL"))
    private void callMobInitGoalEvent(EntityType pEntityType, Level pLevel, CallbackInfo ci) {
        if (pLevel != null && !pLevel.isClientSide) {
            initGoal();
        }
    }

    private void initGoal() {
        MobInitGoalEvent event =
                new MobInitGoalEvent(((Mob) (Object) this),
                        ((Mob) (Object) this).goalSelector,
                        ((Mob) (Object) this).targetSelector);
        MinecraftForge.EVENT_BUS.post(event);
    }
}
