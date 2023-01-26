/*
 * ClassiCraft - ClassiCraftMC
 * Copyright (C) 2018-2022.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package nameless.classicraft.entity.ai.goal;

import nameless.classicraft.entity.AbstractShark;
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
                if (((AbstractShark) this.mob).isHungry()) {
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
