package nameless.classicraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.client.model.SleeperSharkModel;
import nameless.classicraft.entity.SleeperSharkEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class SleeperSharkEyesRenderer<T extends SleeperSharkEntity, M extends SleeperSharkModel<T>> extends RenderLayer<T, M> {

    private static final RenderType SLEEPERSHARKEYE = RenderType.eyes(new ResourceLocation(ClassiCraftMod.MODID, "textures/entity/pacific_sleeper_shark_eyes_layer.png"));

    public SleeperSharkEyesRenderer(RenderLayerParent<T, M> pRenderer) {
        super(pRenderer);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        VertexConsumer ivertexbuilder = pBuffer.getBuffer(this.getRenderType());
        if (pLivingEntity.level.getMaxLocalRawBrightness(pLivingEntity.blockPosition()) < 11) {
            this.getParentModel().renderToBuffer(pPoseStack, ivertexbuilder,  2000, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    public RenderType getRenderType() {
        return SLEEPERSHARKEYE;
    }
}
