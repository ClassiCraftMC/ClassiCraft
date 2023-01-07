package nameless.classicraft.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.api.san.ISanHandler;
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
        ISanHandler handler = (ISanHandler) player;
        if (player != null
                && !player.isCreative()
                && !player.isSpectator()
                && !(player instanceof FakePlayer)) {
            int san = Mth.ceil(handler.getSan());
            int barX = ((Gui) (Object) this).screenWidth / 2 - 91;
            int barY = ((Gui) (Object) this).screenHeight - 39;
            int sanPerStar = (int) handler.getMaxSan() / 10;
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
    }
}
