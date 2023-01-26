package nameless.classicraft.entity.ai.goal;

import nameless.classicraft.entity.AbstractSharkEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;
import java.util.EnumSet;

/**
 * @author wdog5
 */
public class SharkSwimWithPlayerGoal extends Goal {

    private final AbstractSharkEntity shark;
    private final double speedModifier;
    @Nullable
    private Player player;

    public SharkSwimWithPlayerGoal(AbstractSharkEntity shark, double pSpeedModifier) {
        this.shark = shark;
        this.speedModifier = pSpeedModifier;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        this.player = this.shark.level.getNearestPlayer(AbstractSharkEntity.SWIM_WITH_PLAYER_TARGETING, this.shark);
        if (this.player == null) {
            return false;
        } else {
            return this.player.isSwimming() && this.shark.getTarget() != this.player;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean canContinueToUse() {
        return this.player != null && this.player.isSwimming() && this.shark.distanceToSqr(this.player) < 256.0D;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
        this.player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 100), this.shark);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
        this.player = null;
        this.shark.getNavigation().stop();
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        this.shark.getLookControl().setLookAt(this.player, (float)(this.shark.getMaxHeadYRot() + 20), (float)this.shark.getMaxHeadXRot());
        if (this.shark.distanceToSqr(this.player) < 6.25D) {
            this.shark.getNavigation().stop();
        } else {
            this.shark.getNavigation().moveTo(this.player, this.speedModifier);
        }

        if (this.player.isSwimming() && this.player.level.random.nextInt(6) == 0) {
            this.player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 100), this.shark);
        }

    }
}
