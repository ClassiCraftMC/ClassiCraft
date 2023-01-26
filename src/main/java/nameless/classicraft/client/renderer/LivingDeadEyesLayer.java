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
package nameless.classicraft.client.renderer;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.entity.LivingDead;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class LivingDeadEyesLayer<T extends LivingDead, M extends HumanoidModel<T>> extends EyesLayer<T, M> {

    private static final RenderType LIVING_DEAD_EYES =
            RenderType.eyes(new ResourceLocation(ClassiCraftMod.MOD_ID, "textures/entity/living_dead/living_dead_e.png"));

    public LivingDeadEyesLayer(RenderLayerParent<T, M> pRenderer) {
        super(pRenderer);
    }

    @Override
    public @NotNull RenderType renderType() {
        return LIVING_DEAD_EYES;
    }
}
