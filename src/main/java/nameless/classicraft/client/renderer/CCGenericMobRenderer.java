package nameless.classicraft.client.renderer;

import nameless.classicraft.ClassiCraftHooks;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.Mob;
import net.minecraft.resources.ResourceLocation;

public class CCGenericMobRenderer<T extends Mob, M extends EntityModel<T>> extends MobRenderer<T, M> {

    private final ResourceLocation textureLoc;

    public CCGenericMobRenderer(EntityRendererProvider.Context manager, M model, float shadowSize, String textureName) {
        super(manager, model, shadowSize);

        if (textureName.startsWith("textures")) {
            textureLoc = new ResourceLocation(textureName);
        } else {
            textureLoc = ClassiCraftHooks.getModelTexture(textureName);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return textureLoc;
    }
}
