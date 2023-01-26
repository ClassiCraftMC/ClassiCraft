package nameless.classicraft.entity.ai.goal;

import nameless.classicraft.entity.AbstractSharkEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

/**
 * @author wdog5
 */
@SuppressWarnings("unchecked")
public class SharkNearestAttackableTargetGoal extends NearestAttackableTargetGoal {

    public SharkNearestAttackableTargetGoal(Mob pMob, Class pTargetType, boolean pMustSee, boolean pMustReach) {
        super(pMob, pTargetType, pMustSee, pMustReach);
    }

    @Override
    public boolean canUse() {
        return ((AbstractSharkEntity) this.mob).isHungry() && super.canUse();
    }
}
