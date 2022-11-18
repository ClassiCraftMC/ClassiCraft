package nameless.classicraft.mixin.event;

import nameless.classicraft.api.event.PlayerRightClickBlockEvent;
import nameless.classicraft.api.event.ProjectileHitEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.class)
public class MixinBlockBehaviour {

    @Inject(method = "use", at = @At("HEAD"))
    private void callPlayerInteractBlockEvent(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit, CallbackInfoReturnable<InteractionResult> cir) {
        PlayerRightClickBlockEvent event = new PlayerRightClickBlockEvent(pState, pLevel, pPos, pPlayer, pHand, pHit);
        MinecraftForge.EVENT_BUS.post(event);
    }

    @Inject(method = "onProjectileHit", at =@At("HEAD"), cancellable = true)
    private void callProjectileHitEvent(Level pLevel, BlockState pState, BlockHitResult pResult, Projectile pProjectile, CallbackInfo ci) {
        if (pResult.getType() == HitResult.Type.MISS) {
            ci.cancel();
        }

        Block hitBlock = null;
        Direction hitFace = null;
        if (pResult.getType() == HitResult.Type.BLOCK){
            BlockHitResult blockhitresult = pResult;
            hitFace = blockhitresult.getDirection();
            hitBlock = pState.getBlock();
        }

        Entity hitEntity = null;
        if (pResult.getType() == HitResult.Type.ENTITY) {
            hitEntity = pProjectile.getOwner();
        }

        ProjectileHitEvent event = new ProjectileHitEvent(pProjectile, hitEntity, hitBlock, hitFace);
        MinecraftForge.EVENT_BUS.post(event);
    }

}
