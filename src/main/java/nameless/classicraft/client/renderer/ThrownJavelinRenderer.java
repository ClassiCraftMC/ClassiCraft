package nameless.classicraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import nameless.classicraft.client.model.JavelinModel;
import nameless.classicraft.entity.ThrownJavelin;
import nameless.classicraft.init.ModEntityModelLayers;
import nameless.classicraft.item.JavelinItem;
import nameless.classicraft.util.Helpers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ThrownJavelinRenderer extends EntityRenderer<ThrownJavelin> {

    public static final ResourceLocation DEFAULT_TEXTURE =
            Helpers.identifier("textures/entity/projectiles/stone_javelin.png");

    private final JavelinModel model;

    public ThrownJavelinRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new JavelinModel(context.bakeLayer(ModEntityModelLayers.JAVELIN));
    }

    @Override
    public void render(ThrownJavelin javelin, float ageInTicks, float pitch, PoseStack poseStack, MultiBufferSource buffers, int light) {
        poseStack.pushPose();

        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(pitch, javelin.yRotO, javelin.getYRot()) - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(pitch, javelin.xRotO, javelin.getXRot()) + 90.0F));

        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(buffers, this.model.renderType(this.getTextureLocation(javelin)), false, javelin.isEnchantGlowing());
        this.model.renderToBuffer(poseStack, vertexconsumer, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        poseStack.popPose();
        super.render(javelin, ageInTicks, pitch, poseStack, buffers, light);
    }

    @Override
    public ResourceLocation getTextureLocation(ThrownJavelin entity) {
        return entity.getItem().getItem() instanceof JavelinItem javelin ? javelin.getTextureLocation() : DEFAULT_TEXTURE;
    }
}
