package nameless.classicraft.api.event;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraftforge.eventbus.api.Event;

public class MobInitGoalEvent extends Event {

    private final Mob mob;
    private final GoalSelector goalSelector;
    private final GoalSelector targetSelector;

    public MobInitGoalEvent(Mob mob, GoalSelector goalSelector, GoalSelector targetSelector) {
        this.mob = mob;
        this.goalSelector = goalSelector;
        this.targetSelector = targetSelector;
    }

    public Mob getMob() {
        return mob;
    }

    public GoalSelector getTargetSelector() {
        return targetSelector;
    }

    public GoalSelector getGoalSelector() {
        return goalSelector;
    }
}
