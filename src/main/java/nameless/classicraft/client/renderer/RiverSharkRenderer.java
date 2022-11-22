package nameless.classicraft.client.renderer;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.client.model.RiverSharkModel;
import nameless.classicraft.entity.RiverSharkEntity;
import nameless.classicraft.init.ModEntityModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RiverSharkRenderer<T extends RiverSharkEntity> extends MobRenderer<T, RiverSharkModel<T>> {

    public RiverSharkRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new RiverSharkModel<>(pContext.bakeLayer(ModEntityModelLayers.RIVER_SHARK)), 0.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return new ResourceLocation(ClassiCraftMod.MODID, "textures/entity/river_shark.png");
    }
}
