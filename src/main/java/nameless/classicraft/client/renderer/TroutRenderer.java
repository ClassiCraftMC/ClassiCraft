package nameless.classicraft.client.renderer;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.entity.TroutEntity;
import nameless.classicraft.init.ModEntityModelLayers;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CodEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TroutRenderer<T extends TroutEntity> extends MobEntityRenderer<TroutEntity, CodEntityModel<TroutEntity>> {

    public TroutRenderer(EntityRendererFactory.Context arg, CodEntityModel<TroutEntity> arg2, float f) {
        super(arg, new CodEntityModel<>(arg.getPart(ModEntityModelLayers.TROUT)), 0.3F);
    }


    protected void setupTransforms(TroutEntity arg, MatrixStack arg2, float f, float g, float h) {
        super.setupTransforms(arg, arg2, f, g, h);
        float i = 4.3F * MathHelper.sin(0.6F * f);
        arg2.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(i));
        if (!arg.isTouchingWater()) {
            arg2.translate(0.1F, 0.1F, -0.1F);
            arg2.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90.0F));
        }
    }

    @Override
    public Identifier getTexture(TroutEntity entity) {
        return new Identifier(ClassiCraftMod.MOD_ID, "textures/entity/trout.png");
    }
}
