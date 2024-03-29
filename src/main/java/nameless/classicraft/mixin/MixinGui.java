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

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModCapabilities;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.FakePlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class MixinGui extends GuiComponent {

    @Inject(method = "renderHotbar", at = @At("RETURN"))
    private void renderSanBar(float pPartialTick, PoseStack pPoseStack, CallbackInfo ci) {
        Player player = ((Gui) (Object) this).getCameraPlayer();
        player.getCapability(ModCapabilities.PLAYER_SAN_LEVEL).ifPresent(sanCapability -> {
            if (!player.isCreative() && !player.isSpectator() && !(player instanceof FakePlayer)) {
                int san = Mth.ceil(sanCapability.getSan());
                int barX = ((Gui) (Object) this).screenWidth / 2 - 91;
                int barY = ((Gui) (Object) this).screenHeight - 39;
                int sanPerStar = sanCapability.getMaxSan() / 10;
                int maxHealth = Mth.ceil((player.getAttributeValue(Attributes.MAX_HEALTH)
                        + player.getAbsorptionAmount()) / 2.0F / 10.0F);
                RenderSystem.setShaderTexture(0, new ResourceLocation(ClassiCraftMod.MOD_ID, "textures/gui/san_icons.png"));
                int x;

                for (x = 9; x >= 0; --x) {
                    int drawX = barX + x * 8;
                    int drawY = barY - (maxHealth - 1) * Math.max(10 - (maxHealth - 2), 3) - 10;
                    if (player.getArmorValue() > 0) {
                        drawY -= 10;
                    }
                    if (Mth.ceil(san) <= 2 * sanPerStar) {
                        drawY += ((Gui) (Object) this).random.nextInt(2);
                    }
                    this.blit(pPoseStack, drawX, drawY, 0, 0, 9, 9);
                    if (x * sanPerStar + 1 < san) {
                        this.blit(pPoseStack, drawX, drawY, 9, 0, 9, 9);
                    } else if (x * sanPerStar + 1 == san) {
                        this.blit(pPoseStack, drawX, drawY, 18, 0, 9, 9);
                    }
                }
            }
        });
    }
}
