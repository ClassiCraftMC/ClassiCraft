package nameless.classicraft.client.renderer;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.client.model.SleeperSharkModel;
import nameless.classicraft.entity.SleeperSharkEntity;
import nameless.classicraft.init.ModEntityModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SleeperSharkRenderer extends MobRenderer<SleeperSharkEntity, SleeperSharkModel<SleeperSharkEntity>> {

    public SleeperSharkRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SleeperSharkModel<>(pContext.bakeLayer(ModEntityModelLayers.SLEEPERSHARK)), 0.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(SleeperSharkEntity pEntity) {
        return new ResourceLocation(ClassiCraftMod.MODID, "textures/entity/pacific_sleeper_shark.png");
    }
}
