package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModEntityModelLayers {

    public static final EntityModelLayer TROUT = register("trout");
    public static final EntityModelLayer OCEAN_SHARK = register("ocean_shark");

    private static EntityModelLayer register(String name) {
        return register(name, "main");
    }

    private static EntityModelLayer register(String name, String text) {
        return new EntityModelLayer(new Identifier(ClassiCraftMod.MOD_ID, name), text);
    }
}
