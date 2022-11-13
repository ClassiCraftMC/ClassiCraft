package nameless.classicraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.client.model.BullSharkModel;
import nameless.classicraft.entity.BullSharkEntity;
import nameless.classicraft.init.ModEntityModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class BullSharkRenderer<T extends BullSharkEntity> extends MobRenderer<T, BullSharkModel<T>> {

    public BullSharkRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BullSharkModel<>(pContext.bakeLayer(ModEntityModelLayers.BULLSHARK)), 0.2F);
    }

    @Override
    protected void setupRotations(T entityLiving, PoseStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStack, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3F * Mth.sin(0.6F * ageInTicks);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(f));
        if (!entityLiving.isInWater()) {
            matrixStack.translate((double)0.1F, (double)0.1F, (double)-0.1F);
            matrixStack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }
    }

    @Override
    public ResourceLocation getTextureLocation(BullSharkEntity pEntity) {
        return new ResourceLocation(ClassiCraftMod.MODID, "textures/entity/bull_shark.png");
    }
}
