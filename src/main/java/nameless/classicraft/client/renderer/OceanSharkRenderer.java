package nameless.classicraft.client.renderer;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.client.model.OceanSharkModel;
import nameless.classicraft.entity.OceanSharkEntity;
import nameless.classicraft.init.ModEntityModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OceanSharkRenderer<T extends OceanSharkEntity> extends MobRenderer<T, OceanSharkModel<T>> {

    public OceanSharkRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new OceanSharkModel<>(pContext.bakeLayer(ModEntityModelLayers.OCEAN_SHARK)), 0.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return new ResourceLocation(ClassiCraftMod.MOD_ID, "textures/entity/ocean_shark.png");
    }
}