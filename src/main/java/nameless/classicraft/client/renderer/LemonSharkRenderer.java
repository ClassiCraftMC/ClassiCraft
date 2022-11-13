package nameless.classicraft.client.renderer;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.client.model.LemonSharkModel;
import nameless.classicraft.entity.LemonSharkEntity;
import nameless.classicraft.init.ModEntityModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LemonSharkRenderer<T extends LemonSharkEntity> extends MobRenderer<T, LemonSharkModel<T>> {

    public LemonSharkRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new LemonSharkModel<>(pContext.bakeLayer(ModEntityModelLayers.LEMONSHARK)), 0.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(LemonSharkEntity pEntity) {
        return new ResourceLocation(ClassiCraftMod.MODID, "textures/entity/lemon_shark.png");
    }
}
