package nameless.classicraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.entity.TroutEntity;
import nameless.classicraft.init.ModEntityModelLayers;
import net.minecraft.client.model.CodModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TroutRenderer<T extends TroutEntity> extends MobRenderer<TroutEntity, CodModel<TroutEntity>> {

    public TroutRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new CodModel<>(pContext.bakeLayer(ModEntityModelLayers.TROUT)), 0.3F);
    }

    protected void setupRotations(TroutEntity pEntityLiving, PoseStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        float f = 4.3F * Mth.sin(0.6F * pAgeInTicks);
        pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(f));
        if (!pEntityLiving.isInWater()) {
            pMatrixStack.translate(0.1F, (double)0.1F, (double)-0.1F);
            pMatrixStack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }

    }
    @Override
    public ResourceLocation getTextureLocation(TroutEntity pEntity) {
        return new ResourceLocation(ClassiCraftMod.MOD_ID, "textures/entity/trout.png");
    }
}
