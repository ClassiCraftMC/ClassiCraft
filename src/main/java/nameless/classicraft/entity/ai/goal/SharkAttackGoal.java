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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class SharkAttackGoal extends MeleeAttackGoal {

    public SharkAttackGoal(PathfinderMob creature, double speedIn, boolean useLongMemory) {
        super(creature, speedIn, useLongMemory);
    }

    @Override
    public boolean canContinueToUse() {
        return super.canContinueToUse() && mob.isInWater();
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
        double d0 = this.getAttackReachSqr(enemy);
        if (distToEnemySqr <= d0 && this.getTicksUntilNextAttack() <= 0) {
            this.resetAttackCooldown();
            ((AbstractShark) this.mob).attack(enemy);
            ((AbstractShark) this.mob).setHungry(false);
            ((AbstractShark) this.mob).setTimeTillHungry(mob.getRandom().nextInt(300) + 300);
        }
    }

}
