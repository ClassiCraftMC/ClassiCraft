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
package nameless.classicraft.effect;

import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class InstantMilkEffect extends InstantenousMobEffect {

    public InstantMilkEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFF_FFFFFF);
    }

    @Override
    public void applyInstantenousEffect(@Nullable Entity pSource, @Nullable Entity pIndirectSource, LivingEntity pLivingEntity, int pAmplifier, double pHealth) {
        Map<MobEffect, MobEffectInstance> activeEffectsMap = pLivingEntity.getActiveEffectsMap();

        activeEffectsMap.remove(this);

        Map<MobEffect, MobEffectInstance> result = new HashMap<>(activeEffectsMap);

        if (result.size() > 1) {
            Iterator<Map.Entry<MobEffect, MobEffectInstance>> iterator = result.entrySet().iterator();
            int i = 0;
            int random = pLivingEntity.getRandom().nextInt(result.size() - 1);

            while (iterator.hasNext()) {
                Map.Entry<MobEffect, MobEffectInstance> next = iterator.next();

                if (i++ == random) {
                    pLivingEntity.removeEffect(next.getKey());
                }
            }
        } else if (activeEffectsMap.size() > 0) {
            pLivingEntity.removeAllEffects();
        }
    }
}
