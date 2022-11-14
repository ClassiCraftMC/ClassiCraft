package nameless.classicraft.client.renderer;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.client.model.SwineModel;
import nameless.classicraft.entity.SwineEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SwineRenderer<T extends SwineEntity> extends MobRenderer<T, SwineModel<T>> {

    public SwineRenderer(EntityRendererProvider.Context pContext, SwineModel<T> pModel, float pShadowRadius) {
        super(pContext, pModel, pShadowRadius);
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return new ResourceLocation(ClassiCraftMod.MODID, "textures/entity/swine.png");
    }
}
