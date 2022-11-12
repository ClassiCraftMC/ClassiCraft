package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModEntityModelLayers {

    public static final ModelLayerLocation PERCH =  register("perch");
    public static final ModelLayerLocation DEER = register("deer");
    public static final ModelLayerLocation BOAR = register("boar");
    public static final ModelLayerLocation LIONFISH = register("lionfish");
    public static final ModelLayerLocation ANGELFISH = register("angelfish");
    public static final ModelLayerLocation BASKINGSHARK = register("basking_shark");
    public static final ModelLayerLocation FOXFACE_FISH = register("foxface_fish");
    public static final ModelLayerLocation FOOTBALLFISH = register("footballfish");
    public static final ModelLayerLocation ARAPAIMA = register("arapaima");
    public static final ModelLayerLocation BELUGA_STURGEON = register("beluga_sturgeon");
    public static final ModelLayerLocation BOXFISH = register("boxfish");
    public static final ModelLayerLocation OCEANSHARK = register("ocean_shark");

    public static final ModelLayerLocation RANCHU = new ModelLayerLocation(new ResourceLocation(ClassiCraftMod.MODID, "ranchu"), "ranchu");

    private static ModelLayerLocation register(String p_171294_) {
        return register(p_171294_, "main");
    }

    private static ModelLayerLocation register(String p_171301_, String p_171302_) {
        return new ModelLayerLocation(new ResourceLocation(ClassiCraftMod.MODID, p_171301_), p_171302_);
    }
}
