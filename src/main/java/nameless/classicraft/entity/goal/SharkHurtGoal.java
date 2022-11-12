package nameless.classicraft.entity.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class SharkHurtGoal extends MeleeAttackGoal {

    public SharkHurtGoal(PathfinderMob mob) {
        super(mob, 1.0D, true);
    }

    public boolean canContinueToUse() {

        if (this.mob.getRandom().nextInt(100) == 0) {
            this.mob.setTarget((LivingEntity)null);
            return false;
        } else {
            return super.canContinueToUse();
        }
    }

    protected double getAttackReachSqr(LivingEntity pAttackTarget) {
        return (double)(4.0F + pAttackTarget.getBbWidth());
    }
}
