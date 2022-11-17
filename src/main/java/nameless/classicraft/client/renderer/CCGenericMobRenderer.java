package nameless.classicraft.client.renderer;

import nameless.classicraft.ClassiCraftMod;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class CCGenericMobRenderer<T extends Mob, M extends EntityModel<T>> extends MobRenderer<T, M> {

    private final ResourceLocation textureLoc;

    public CCGenericMobRenderer(EntityRendererProvider.Context manager, M model, float shadowSize, String textureName) {
        super(manager, model, shadowSize);

        if (textureName.startsWith("textures")) {
            textureLoc = new ResourceLocation(textureName);
        } else {
            textureLoc = new ResourceLocation(ClassiCraftMod.MODID, "textures/entity/" + textureName);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return textureLoc;
    }
}
