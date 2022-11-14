package nameless.classicraft.mixin;

import nameless.classicraft.api.event.ProjectileHitEvent;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractHurtingProjectile.class)
public abstract class MixinAbstractHurtingProjectile extends Projectile {

    protected MixinAbstractHurtingProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Inject(method = "tick", at = @At(value = "HEAD", target = "Lnet/minecraft/world/entity/projectile/AbstractHurtingProjectile;checkInsideBlocks()V"))
    private void callProjectileHitEvent(CallbackInfo ci) {
        HitResult pResult = ProjectileUtil.getHitResult(this, this::canHitEntity);
        if (pResult.getType() != HitResult.Type.MISS) {
            if (pResult.getType() == HitResult.Type.MISS) {
                ci.cancel();
            }

            Block hitBlock = null;
            Direction hitFace = null;
            if (pResult.getType() == HitResult.Type.BLOCK){
                BlockHitResult blockhitresult = (BlockHitResult)pResult;
                hitFace = blockhitresult.getDirection();
                hitBlock = this.getBlockStateOn().getBlock();
            }

            Entity hitEntity = null;
            if (pResult.getType() == HitResult.Type.ENTITY) {
                hitEntity = this.getOwner();
            }

            ProjectileHitEvent event = new ProjectileHitEvent(((Projectile) (Object) this), hitEntity, hitBlock, hitFace);
            MinecraftForge.EVENT_BUS.post(event);
        }
    }
}