package nameless.classicraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.client.model.ArapaimaModel;
import nameless.classicraft.entity.ArapaimaEntity;
import nameless.classicraft.init.ModEntityModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ArapaimaRenderer <T extends ArapaimaEntity> extends MobRenderer<T, ArapaimaModel<T>> {

    public ArapaimaRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ArapaimaModel<>(pContext.bakeLayer(ModEntityModelLayers.ARAPAIMA)), 0.2F);
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
    public ResourceLocation getTextureLocation(T pEntity) {
        return new ResourceLocation(ClassiCraftMod.MODID, "textures/entity/arapaima.png");
    }
}
