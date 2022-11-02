package nameless.classicraft.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import nameless.classicraft.common.capability.ModCapabilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author DustW
 */
@Mixin(ItemRenderer.class)
public class MixinItemRenderer {
    @Shadow public float blitOffset;

    @Inject(method = "renderGuiItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V",
            at = @At("HEAD"))
    private void renderGuiItemDecorationsCC(Font font, ItemStack stack, int x, int y, String text, CallbackInfo ci) {
        LocalPlayer player = Minecraft.getInstance().player;

        if (!stack.isEmpty() && player != null && (player.containerMenu == null || stack != player.containerMenu.getCarried())) {
            stack.getCapability(ModCapabilities.ROT).ifPresent(rot -> {
                if (rot.getHolder().getCurrent() < rot.getHolder().getMax() && rot.getHolder().getMax() > 0) {
                    PoseStack poseStack = new PoseStack();
                    poseStack.translate(0, 0, blitOffset + 100);
                    Matrix4f m4 = poseStack.last().pose();

                    int color = switch (rot.getLevel()) {
                        case STALE -> 0xE0DDB12A;
                        case FRESH -> 0xE0476E1E;
                        case SPOILED -> 0xE0CC8A17;
                        case ROT -> 0xE0890F01;
                    };

                    int texLen = 16;
                    int eTexLen = (int) (16 * (1 - rot.getHolder().percent()));

                    int minX = x;
                    int maxX = x + texLen;
                    int minY = y + texLen;
                    int maxY = y + eTexLen;

                    if (minX < maxX) {
                        int i = minX;
                        minX = maxX;
                        maxX = i;
                    }

                    if (minY < maxY) {
                        int j = minY;
                        minY = maxY;
                        maxY = j;
                    }

                    float f3 = (color >> 24 & 255) / 255.0F;
                    float f = (color >> 16 & 255) / 255.0F;
                    float f1 = (color >> 8 & 255) / 255.0F;
                    float f2 = (color & 255) / 255.0F;
                    Tesselator tesselator = Tesselator.getInstance();
                    BufferBuilder builder = tesselator.getBuilder();
                    RenderSystem.enableBlend();
                    RenderSystem.disableTexture();
                    RenderSystem.blendFuncSeparate(
                            GlStateManager.SourceFactor.SRC_ALPHA,
                            GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
                            GlStateManager.SourceFactor.SRC_ALPHA,
                            GlStateManager.DestFactor.DST_ALPHA);
                    RenderSystem.setShader(GameRenderer::getPositionColorShader);
                    builder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
                    builder.vertex(m4, minX, maxY, 0.0F).color(f, f1, f2, f3).endVertex();
                    builder.vertex(m4, maxX, maxY, 0.0F).color(f, f1, f2, f3).endVertex();
                    builder.vertex(m4, maxX, minY, 0.0F).color(f, f1, f2, f3).endVertex();
                    builder.vertex(m4, minX, minY, 0.0F).color(f, f1, f2, f3).endVertex();
                    tesselator.end();
                    RenderSystem.enableTexture();
                    RenderSystem.disableBlend();
                }
            });
        }
    }
}
