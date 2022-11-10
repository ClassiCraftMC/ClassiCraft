package nameless.classicraft.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class DrownEffect extends MobEffect {

    public DrownEffect() {
        super(MobEffectCategory.HARMFUL, 13565951);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

    @Override
    public String getDescriptionId() {
        return "effect.classicraft.drown";
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity instanceof Player) {
            ItemStack heldStack = pLivingEntity.getItemInHand(pLivingEntity.getUsedItemHand());
            pLivingEntity.setItemInHand(pLivingEntity.getUsedItemHand(), ItemStack.EMPTY);
            heldStack.grow(1);
            pLivingEntity.makeStuckInBlock(Blocks.AIR.defaultBlockState(), new Vec3(0.25, 0.05, 0.25));
        } else {
            pLivingEntity.setNoActionTime(pAmplifier * 200);
            pLivingEntity.makeStuckInBlock(Blocks.AIR.defaultBlockState(), new Vec3(0.25, 0.05, 0.25));
        }
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }
}
