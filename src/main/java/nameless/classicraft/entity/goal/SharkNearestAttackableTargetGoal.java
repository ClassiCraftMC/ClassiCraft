package nameless.classicraft.entity.goal;

import nameless.classicraft.entity.BaskingSharkEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

public class SharkNearestAttackableTargetGoal extends NearestAttackableTargetGoal {

    public SharkNearestAttackableTargetGoal(Mob pMob, Class pTargetType, boolean pMustSee, boolean pMustReach) {
        super(pMob, pTargetType, pMustSee, pMustReach);
    }

    @Override
    public boolean canUse() {
        return ((BaskingSharkEntity) this.mob).isHungry() && super.canUse();
    }
}