package nameless.classicraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.client.model.FoxFaceFishModel;
import nameless.classicraft.entity.FoxFaceFishEntity;
import nameless.classicraft.init.ModEntityModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class FoxFaceFishRenderer <T extends FoxFaceFishEntity> extends MobRenderer<T, FoxFaceFishModel<T>> {

    public FoxFaceFishRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FoxFaceFishModel<>(pContext.bakeLayer(ModEntityModelLayers.FOXFACE_FISH)), 0.2F);
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
    public ResourceLocation getTextureLocation(FoxFaceFishEntity pEntity) {
        if(pEntity.getVariant() == 1){
            return new ResourceLocation(ClassiCraftMod.MODID, "textures/entity/black_foxface_fish.png");
        }
        if(pEntity.getVariant() == 2) {
            return new ResourceLocation(ClassiCraftMod.MODID, "textures/entity/white_foxface_fish.png");
        }
        return new ResourceLocation(ClassiCraftMod.MODID, "textures/entity/foxface_fish.png");
    }
}

