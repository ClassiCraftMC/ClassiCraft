package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModEntityModelLayers {

    public static final ModelLayerLocation DEER = register("deer");
    public static final ModelLayerLocation BOAR = register("boar");
    public static final ModelLayerLocation LIONFISH = register("lionfish");

    private static ModelLayerLocation register(String p_171294_) {
        return register(p_171294_, "main");
    }

    private static ModelLayerLocation register(String p_171301_, String p_171302_) {
        return new ModelLayerLocation(new ResourceLocation(ClassiCraftMod.MODID, p_171301_), p_171302_);
    }
}
