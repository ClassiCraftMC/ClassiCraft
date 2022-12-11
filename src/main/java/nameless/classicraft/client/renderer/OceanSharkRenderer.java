package nameless.classicraft.client.renderer;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.client.model.OceanSharkModel;
import nameless.classicraft.entity.OceanSharkEntity;
import nameless.classicraft.init.ModEntityModelLayers;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OceanSharkRenderer<T extends OceanSharkEntity> extends MobEntityRenderer<T, OceanSharkModel<T>> {


    public OceanSharkRenderer(EntityRendererFactory.Context arg, OceanSharkModel<T> arg2, float f) {
        super(arg, new OceanSharkModel<>(arg.getPart(ModEntityModelLayers.OCEAN_SHARK)), 0.2F);
    }

    @Override
    public Identifier getTexture(T entity) {
        return new Identifier(ClassiCraftMod.MOD_ID, "textures/entity/ocean_shark.png");
    }
}