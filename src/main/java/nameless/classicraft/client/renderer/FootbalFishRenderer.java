package nameless.classicraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.client.model.FootballFishModel;
import nameless.classicraft.entity.FootballFishEntity;
import nameless.classicraft.init.ModEntityModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class FootbalFishRenderer<T extends FootballFishEntity> extends MobRenderer<T, FootballFishModel<T>> {

    public FootbalFishRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FootballFishModel<>(pContext.bakeLayer(ModEntityModelLayers.FOOTBALLFISH)), 0.2F);
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
    public ResourceLocation getTextureLocation(FootballFishEntity pEntity) {
        return new ResourceLocation(ClassiCraftMod.MODID, "textures/entity/anglefish.png");
    }
}

