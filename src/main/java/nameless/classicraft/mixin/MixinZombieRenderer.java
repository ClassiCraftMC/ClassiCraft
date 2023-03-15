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
package nameless.classicraft.mixin;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.api.access.ZombieAccessor;
import nameless.classicraft.util.Helpers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieRenderer.class)
public class MixinZombieRenderer extends AbstractZombieRenderer<Zombie, ZombieModel<Zombie>> {

    private static final ResourceLocation DEFAULT_TEXTURES =
            Helpers.identifier("textures/entity/zombie/zombie1.png");

    protected MixinZombieRenderer(EntityRendererProvider.Context pContext, ZombieModel<Zombie> pModel, ZombieModel<Zombie> pInnerModel, ZombieModel<Zombie> pOuterModel) {
        super(pContext, pModel, pInnerModel, pOuterModel);
    }

    @Inject(method = "<init>(Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;)V", at = @At("RETURN"))
    private void initCC(EntityRendererProvider.Context p_174456_, CallbackInfo ci) {
        ((ZombieRenderer) (Object) this).addLayer(new EyesLayer<Zombie, ZombieModel<Zombie>>(((ZombieRenderer) (Object) this)) {
            @Override
            public RenderType renderType() {
                return RenderType.eyes(Helpers.identifier("textures/entity/zombie/zombie_e.png"));
            }
        });
    }

    @Override
    @NotNull
    public ResourceLocation getTextureLocation(Zombie pEntity) {
        int variant = ((ZombieAccessor) pEntity).getVariant() + 1;
        final ResourceLocation[] TEXTURES = new ResourceLocation[((ZombieAccessor) pEntity).getMaxVariants()];
        if (TEXTURES[variant - 1] == null) {
            ResourceLocation loc = Helpers.identifier("textures/entity/zombie/zombie" + variant + ".png");
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
