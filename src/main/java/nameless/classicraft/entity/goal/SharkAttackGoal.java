package nameless.classicraft.entity.goal;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class SharkAttackGoal extends MeleeAttackGoal {
    private final PathfinderMob mob;
    private int raiseArmTicks;

    public SharkAttackGoal(PathfinderMob mob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(mob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        this.mob = mob;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
        super.start();
        this.raiseArmTicks = 0;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
        super.stop();
        this.mob.setAggressive(false);
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
            super.tick();
            ++this.raiseArmTicks;
            if (this.raiseArmTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2) {
                this.mob.setAggressive(true);
            } else {
                this.mob.setAggressive(false);
            }
    }
}