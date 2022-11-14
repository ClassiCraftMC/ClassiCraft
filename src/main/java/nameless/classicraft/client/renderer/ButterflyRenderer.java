package nameless.classicraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.client.model.ButterflyModel;
import nameless.classicraft.entity.ButterflyEntity;
import nameless.classicraft.init.ModEntityModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ButterflyRenderer <T extends ButterflyEntity> extends MobRenderer<T, ButterflyModel<T>> {

    private static final ResourceLocation ORANGE = new ResourceLocation(ClassiCraftMod.MODID, "textures/entity/butterfly/orange.png");
    private static final ResourceLocation BLUE = new ResourceLocation(ClassiCraftMod.MODID, "textures/entity/butterfly/blue.png");
    private static final ResourceLocation PURPLE = new ResourceLocation(ClassiCraftMod.MODID, "textures/entity/butterfly/purple.png");
    private static final ResourceLocation WHITE = new ResourceLocation(ClassiCraftMod.MODID, "textures/entity/butterfly/white.png");
    private static final ResourceLocation YELLOW = new ResourceLocation(ClassiCraftMod.MODID, "textures/entity/butterfly/yellow.png");

    public ButterflyRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ButterflyModel<>(pContext.bakeLayer(ModEntityModelLayers.BUTTERFLY)), 0.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return switch (pEntity.getVariant()) {
            case 1 -> PURPLE;
            case 2 -> BLUE;
            case 3 -> WHITE;
            case 4 -> YELLOW;
            default -> ORANGE;
        };
    }

    @Override
    protected void setupRotations(T entityLiving, PoseStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
        float f = -(Mth.cos(ageInTicks * 1.7F) * (float)Math.PI * 0.2F);
        float f1 = Mth.cos(ageInTicks * 1.7F) * (float)Math.PI * 0.2F;
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(f));
    }
}
