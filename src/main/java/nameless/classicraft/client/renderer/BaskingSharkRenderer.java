package nameless.classicraft.client.renderer;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.client.model.BaskingSharkModel;
import nameless.classicraft.entity.BaskingSharkEntity;
import nameless.classicraft.init.ModEntityModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BaskingSharkRenderer<T extends BaskingSharkEntity> extends MobRenderer<T, BaskingSharkModel<T>> {

    public BaskingSharkRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BaskingSharkModel<>(pContext.bakeLayer(ModEntityModelLayers.BASKINGSHARK)), 0.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return new ResourceLocation(ClassiCraftMod.MODID, "textures/entity/basking_shark.png");
    }
}