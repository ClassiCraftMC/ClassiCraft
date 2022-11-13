package nameless.classicraft.client.renderer;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.client.model.BullSharkModel;
import nameless.classicraft.entity.BullSharkEntity;
import nameless.classicraft.init.ModEntityModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BullSharkRenderer<T extends BullSharkEntity> extends MobRenderer<T, BullSharkModel<T>> {

    public BullSharkRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BullSharkModel<>(pContext.bakeLayer(ModEntityModelLayers.BULLSHARK)), 0.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(BullSharkEntity pEntity) {
        return new ResourceLocation(ClassiCraftMod.MODID, "textures/entity/bull_shark.png");
    }
}
