package nameless.classicraft.entity.goal;

import nameless.classicraft.entity.BaskingSharkEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.phys.Vec3;

public class SharkSwimmingGoal extends RandomSwimmingGoal {

    public SharkSwimmingGoal(PathfinderMob p_25753_) {
        super(p_25753_, 1.35D, 30);
    }

    @Override
    public boolean canUse() {
        if (this.mob.isVehicle()) {
            return false;
        } else {
            if (!this.forceTrigger) {
                if (this.mob.getNoActionTime() >= 100) {
                    return false;
                }
                if (((BaskingSharkEntity) this.mob).isHungry()) {
                    if (this.mob.getRandom().nextInt(60) != 0) {
                        return false;
                    }
                } else {
                    if (this.mob.getRandom().nextInt(30) != 0) {
                        return false;
                    }
                }
            }

            Vec3 vec3d = this.getPosition();
            if (vec3d == null) {
                return false;
            } else {
                this.wantedX = vec3d.x;
                this.wantedY = vec3d.y;
                this.wantedZ = vec3d.z;
                this.forceTrigger = false;
                return true;
            }
        }
    }

}
