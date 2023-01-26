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
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LivingDeadRenderer<T extends LivingDead> extends MobRenderer<LivingDead, HumanoidModel<LivingDead>> {

    private static final ResourceLocation[] TEXTURES =
            new ResourceLocation[LivingDead.MAX_VARIANTS];
    private static final ResourceLocation DEFAULT_TEXTURES =
            new ResourceLocation(ClassiCraftMod.MOD_ID, "textures/entity/living_dead/zombie1.png");

    public LivingDeadRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new HumanoidModel<>(pContext.bakeLayer(ModelLayers.ZOMBIE)), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel<>(pContext.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new HumanoidModel<>(pContext.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR))));
        this.addLayer(new LivingDeadEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(LivingDead pEntity) {
        int variant = pEntity.getVariant() + 1;
        if (TEXTURES[variant - 1] == null) {
            ResourceLocation loc = new ResourceLocation(ClassiCraftMod.MOD_ID, "textures/entity/living_dead/zombie" + variant + ".png");
            if (Minecraft.getInstance().getResourceManager().getResource(loc).isEmpty()) {
                ClassiCraftMod.LOGGER.warn("Found Unknown variant " + variant + ", using default");
                loc = DEFAULT_TEXTURES;
                return loc;
            }

            return TEXTURES[variant - 1] = loc;
        }

        return TEXTURES[variant - 1];
    }

}
