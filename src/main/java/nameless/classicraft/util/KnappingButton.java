package nameless.classicraft.util;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.NotNull;

public class KnappingButton extends Button {

    private final ResourceLocation texture;
    private final SoundEvent sound;

    public KnappingButton(int pX, int pY, int pWidth, int pHeight, ResourceLocation texture, SoundEvent sound) {
        super(pX, pY, pWidth, pHeight, Component.empty(), pButton -> {});
        this.texture = texture;
        this.sound = sound;
    }

    @Override
    public void onPress() {
        if (active) {
            visible = false;
            playDownSound(Minecraft.getInstance().getSoundManager());
        }
    }

    @Override
    public void playDownSound(SoundManager handler) {
        handler.play(SimpleSoundInstance.forUI(sound, 1.0F));
    }

    @Override
    public void renderButton(@NotNull PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            RenderSystem.setShaderTexture(0, texture);
            isHovered = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;

            blit(matrixStack, x, y, 0, 0, 16, 16, 16, 16);
        }
    }
}
