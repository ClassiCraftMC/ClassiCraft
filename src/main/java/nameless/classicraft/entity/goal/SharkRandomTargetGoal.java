package nameless.classicraft.entity.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class SharkRandomTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

    private final Mob mob;

    public SharkRandomTargetGoal(Mob pMob, Class<T> pTargetType, boolean pMustSee, @Nullable Predicate<LivingEntity> pTargetPredicate) {
        super(pMob, pTargetType, pMustSee);
        this.mob = pMob;
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        return super.canUse();
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean canContinueToUse() {
        return this.targetConditions != null ? this.targetConditions.test(this.mob, this.target) : super.canContinueToUse();
    }
}
