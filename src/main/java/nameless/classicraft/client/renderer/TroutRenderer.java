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

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import nameless.classicraft.entity.Trout;
import nameless.classicraft.init.ModEntityModelLayers;
import nameless.classicraft.util.Helpers;
import net.minecraft.client.model.CodModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TroutRenderer<T extends Trout> extends MobRenderer<Trout, CodModel<Trout>> {

    public TroutRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new CodModel<>(pContext.bakeLayer(ModEntityModelLayers.TROUT)), 0.3F);
    }

    protected void setupRotations(Trout pEntityLiving, PoseStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        float f = 4.3F * Mth.sin(0.6F * pAgeInTicks);
        pMatrixStack.mulPose(Axis.YP.rotationDegrees(f));
        if (!pEntityLiving.isInWater()) {
            pMatrixStack.translate(0.1F, (double)0.1F, (double)-0.1F);
            pMatrixStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
        }

    }

    @Override
    public ResourceLocation getTextureLocation(Trout pEntity) {
        return Helpers.identifier("textures/entity/trout.png");
    }
}
