package nameless.classicraft.client.renderer;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.client.model.BelugaSturgeonModel;
import nameless.classicraft.entity.BelugaSturgeonEntity;
import nameless.classicraft.init.ModEntityModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BelugaSturgeonRenderer <T extends BelugaSturgeonEntity> extends MobRenderer<T, BelugaSturgeonModel<T>> {


    public BelugaSturgeonRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BelugaSturgeonModel<>(pContext.bakeLayer(ModEntityModelLayers.BELUGA_STURGEON)), 0.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return new ResourceLocation(ClassiCraftMod.MODID, "textures/entity/beluga_sturgeon.png");
    }
}
