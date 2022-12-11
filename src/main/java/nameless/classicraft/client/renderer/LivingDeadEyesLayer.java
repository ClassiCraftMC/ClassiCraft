package nameless.classicraft.client.renderer;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.entity.LivingDeadEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.util.Identifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LivingDeadEyesLayer<T extends LivingDeadEntity, M extends BipedEntityModel<T>> extends EyesFeatureRenderer<T, M> {

    private static final RenderLayer LIVING_DEAD_EYES =
            RenderLayer.getEyes(new Identifier(ClassiCraftMod.MOD_ID, "textures/entity/living_dead/living_dead_e.png"));

    public LivingDeadEyesLayer(FeatureRendererContext<T, M> arg) {
        super(arg);
    }

    @Override
    public RenderLayer getEyesTexture() {
        return LIVING_DEAD_EYES;
    }
}
