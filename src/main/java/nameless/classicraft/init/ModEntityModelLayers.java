package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModEntityModelLayers {

    public static final ModelLayerLocation TROUT = register("trout");

    private static ModelLayerLocation register(String name) {
        return register(name, "main");
    }

    private static ModelLayerLocation register(String name, String text) {
        return new ModelLayerLocation(new ResourceLocation(ClassiCraftMod.MOD_ID, name), text);
    }
}
