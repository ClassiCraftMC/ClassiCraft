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
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class SharkSwimWithPlayerGoal extends Goal {

    private final AbstractShark shark;
    private final double speedModifier;
    @Nullable
    private Player player;

    public SharkSwimWithPlayerGoal(AbstractShark shark, double pSpeedModifier) {
        this.shark = shark;
        this.speedModifier = pSpeedModifier;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        this.player = this.shark.level.getNearestPlayer(AbstractShark.SWIM_WITH_PLAYER_TARGETING, this.shark);
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
