package nameless.classicraft.client;

import nameless.classicraft.ClassiCraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModEntityModelLayers {

    public static final ModelLayerLocation DEER = register("deer");

    private static ModelLayerLocation register(String p_171294_) {
        return register(p_171294_, "main");
    }

    private static ModelLayerLocation register(String p_171301_, String p_171302_) {
        return new ModelLayerLocation(new ResourceLocation(ClassiCraft.MODID, p_171301_), p_171302_);
    }
}
