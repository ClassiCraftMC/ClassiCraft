package nameless.classicraft.client.renderer;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.client.model.OceanSharkModel;
import nameless.classicraft.entity.OceanSharkEntity;
import nameless.classicraft.init.ModEntityModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class OceanSharkRenderer<T extends OceanSharkEntity> extends MobRenderer<T, OceanSharkModel<T>> {

    public OceanSharkRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new OceanSharkModel<>(pContext.bakeLayer(ModEntityModelLayers.OCEANSHARK)), 0.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return new ResourceLocation(ClassiCraftMod.MODID, "textures/entity/ocean_shark.png");
    }
}