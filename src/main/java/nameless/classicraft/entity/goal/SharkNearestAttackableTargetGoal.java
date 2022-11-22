package nameless.classicraft.entity.goal;

import nameless.classicraft.entity.AbstractSharkEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

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
