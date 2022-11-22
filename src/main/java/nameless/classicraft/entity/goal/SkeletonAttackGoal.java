package nameless.classicraft.entity.goal;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.item.Items;

public class SkeletonAttackGoal extends MeleeAttackGoal {

    private final AbstractSkeleton skeleton;
    private int raiseArmTicks;

    public SkeletonAttackGoal(AbstractSkeleton skeleton) {
        super(skeleton, 1.2D, false);
        this.skeleton = skeleton;
    }

    public void start() {
        super.start();
        this.raiseArmTicks = 0;
    }

    public void stop() {
        super.stop();
        this.skeleton.setAggressive(false);
    }

    public void tick() {
        super.tick();
        ++this.raiseArmTicks;
        if (this.raiseArmTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2) {
            this.skeleton.setAggressive(true);
            this.skeleton.setItemSlot(EquipmentSlot.MAINHAND, Items.STONE_SWORD.getDefaultInstance());
        } else {
            this.skeleton.setAggressive(false);
        }

    }
}
