package nameless.classicraft.client.renderer;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.client.model.SwineModel;
import nameless.classicraft.entity.SwineEntity;
import nameless.classicraft.init.ModEntityModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SwineRenderer<T extends SwineEntity> extends MobRenderer<T, SwineModel<T>> {

    public SwineRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SwineModel<>(pContext.bakeLayer(ModEntityModelLayers.SWINE)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return new ResourceLocation(ClassiCraftMod.MODID, "textures/entity/swine.png");
    }
}
