package nameless.classicraft.entity.goal;

import nameless.classicraft.entity.BaskingSharkEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class SharkAttackGoal extends MeleeAttackGoal {

    public SharkAttackGoal(PathfinderMob creature, double speedIn, boolean useLongMemory) {
        super(creature, speedIn, useLongMemory);
    }

    @Override
    public boolean canContinueToUse() {
        return super.canContinueToUse() && mob.isInWater();
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
        double d0 = this.getAttackReachSqr(enemy);
        if (distToEnemySqr <= d0 && this.getTicksUntilNextAttack() <= 0) {
            this.resetAttackCooldown();
            ((BaskingSharkEntity) this.mob).attack(enemy);
            ((BaskingSharkEntity) this.mob).setHungry(false);
            ((BaskingSharkEntity) this.mob).setTimeTillHungry(mob.getRandom().nextInt(300) + 300);
        }
    }

}
