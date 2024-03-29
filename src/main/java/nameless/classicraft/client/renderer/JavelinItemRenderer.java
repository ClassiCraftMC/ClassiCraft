package nameless.classicraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import nameless.classicraft.init.ModEntityModelLayers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.TridentModel;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class JavelinItemRenderer extends BlockEntityWithoutLevelRenderer {

    private final ResourceLocation textureLocation;
    private final TridentModel model;

    public JavelinItemRenderer(ResourceLocation textureLocation) {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        this.model = new TridentModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModEntityModelLayers.JAVELIN));
        this.textureLocation = textureLocation;
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext context, PoseStack poseStack, MultiBufferSource buffers, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.scale(1.0F, -1.0F, -1.0F);
        VertexConsumer buffer = ItemRenderer.getFoilBufferDirect(buffers, this.model.renderType(textureLocation), false, stack.hasFoil());
        model.renderToBuffer(poseStack, buffer, packedLight, packedOverlay, 1f, 1f, 1f, 1f);
        poseStack.popPose();
    }

}