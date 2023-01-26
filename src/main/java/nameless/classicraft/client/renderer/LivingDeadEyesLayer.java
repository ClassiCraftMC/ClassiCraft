package nameless.classicraft.client.renderer;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.entity.LivingDeadEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

/**
 * @author wdog5
 */
@OnlyIn(Dist.CLIENT)
public class LivingDeadEyesLayer<T extends LivingDeadEntity, M extends HumanoidModel<T>> extends EyesLayer<T, M> {

    private static final RenderType LIVING_DEAD_EYES =
            RenderType.eyes(new ResourceLocation(ClassiCraftMod.MOD_ID, "textures/entity/living_dead/living_dead_e.png"));

    public LivingDeadEyesLayer(RenderLayerParent<T, M> pRenderer) {
        super(pRenderer);
    }

    @Override
    public @NotNull RenderType renderType() {
        return LIVING_DEAD_EYES;
    }
}
