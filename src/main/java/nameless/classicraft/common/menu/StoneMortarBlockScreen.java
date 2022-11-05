package nameless.classicraft.common.menu;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import nameless.classicraft.ClassiCraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class StoneMortarBlockScreen extends AbstractContainerScreen<StoneMortarBlockMenu> {

    private static final ResourceLocation GUI = new ResourceLocation(ClassiCraft.MODID,
            "textures/gui/stone_mortar_block.png");

    public StoneMortarBlockScreen(StoneMortarBlockMenu screenContainer, Inventory inv,
                                  Component titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public void render(@Nonnull PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(@NotNull PoseStack poseStack, float partialTicks, int x, int y) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, GUI);

        int i = this.leftPos;
        int j = this.topPos;
        int k = (this.width - this.getXSize()) / 2;
        int l = (this.height - this.getYSize()) / 2;
        this.blit(poseStack, i, j, -1, -2, this.imageWidth, this.imageHeight);
        if (menu.allow()) {
            this.blit(poseStack, k + 74, l + 32, 176, 1, menu.getScaledProgress(), 20);
        }
    }
}
