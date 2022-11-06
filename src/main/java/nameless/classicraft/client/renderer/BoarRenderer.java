package nameless.classicraft.client.renderer;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.client.model.BoarModel;
import nameless.classicraft.entity.BoarEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

//old renderer used to use PigModel, had to change it because of the texture size change
public class BoarRenderer extends MobRenderer<BoarEntity, BoarModel<BoarEntity>> {

    public BoarRenderer(EntityRendererProvider.Context manager, BoarModel<BoarEntity> model) {
        super(manager, model, 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(BoarEntity entity) {
        return new ResourceLocation(ClassiCraftMod.MODID, "textures/model/wildboar.png");
    }
}
