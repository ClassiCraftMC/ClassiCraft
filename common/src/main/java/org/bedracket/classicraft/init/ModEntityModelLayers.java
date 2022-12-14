package org.bedracket.classicraft.init;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import org.bedracket.classicraft.ClassiCraftMod;

public class ModEntityModelLayers {

    public static final ModelLayerLocation TROUT = register("trout");
    public static final ModelLayerLocation OCEAN_SHARK = register("ocean_shark");

    private static ModelLayerLocation register(String name) {
        return register(name, "main");
    }

    private static ModelLayerLocation register(String name, String text) {
        return new ModelLayerLocation(new ResourceLocation(ClassiCraftMod.MOD_ID, name), text);
    }
}
