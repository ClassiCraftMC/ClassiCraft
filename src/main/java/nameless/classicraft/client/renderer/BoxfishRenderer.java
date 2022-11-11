package nameless.classicraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.client.model.BoxfishModel;
import nameless.classicraft.entity.BoxfishEntity;
import nameless.classicraft.init.ModEntityModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class BoxfishRenderer <T extends BoxfishEntity> extends MobRenderer<T, BoxfishModel<T>> {

    public BoxfishRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BoxfishModel<>(pContext.bakeLayer(ModEntityModelLayers.BOXFISH)), 0.2F);
    }

    @Override
    protected void setupRotations(T pEntityLiving, PoseStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        float f = 4.3F * Mth.sin(0.6F * pAgeInTicks);
        pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(f));
        if (!pEntityLiving.isInWater()) {
            pMatrixStack.translate((double)0.1F, (double)0.1F, (double)-0.1F);
            pMatrixStack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        if(pEntity.getVariant() == 1){
            return new ResourceLocation(ClassiCraftMod.MODID, "textures/entity/white_boxfish.png");
        }
        return new ResourceLocation(ClassiCraftMod.MODID, "textures/entity/yellow_boxfish.png");
    }
}