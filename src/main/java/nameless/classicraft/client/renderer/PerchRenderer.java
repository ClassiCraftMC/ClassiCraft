package nameless.classicraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.client.model.PerchModel;
import nameless.classicraft.entity.PerchEntity;
import nameless.classicraft.init.ModEntityModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class PerchRenderer extends MobRenderer<PerchEntity, PerchModel<PerchEntity>> {

    public PerchRenderer(EntityRendererProvider.Context context) {
        super(context, new PerchModel<>(context.bakeLayer(ModEntityModelLayers.PERCH)), 0.3F);
    }

    public ResourceLocation getTextureLocation(PerchEntity entity) {
        return new ResourceLocation(ClassiCraftMod.MODID, "textures/entity/perch.png");
    }

    protected void setupRotations(PerchEntity entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3F * Mth.sin(0.6F * ageInTicks);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f));
        if (!entityLiving.isInWater()) {
            matrixStackIn.translate(0.1F, 0.1F, -0.1F);
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }
    }
}