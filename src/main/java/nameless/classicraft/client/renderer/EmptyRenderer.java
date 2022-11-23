package nameless.classicraft.client.renderer;

import nameless.classicraft.entity.SoulEmptyEntity;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class EmptyRenderer extends EntityRenderer<SoulEmptyEntity>
{
    public EmptyRenderer(EntityRendererProvider.Context ctx)
    {
        super(ctx);
    }

    @Override
    public boolean shouldRender(SoulEmptyEntity entity, Frustum camera, double camX, double camY, double camZ)
    {
        return false;
    }

    @Override
    public ResourceLocation getTextureLocation(SoulEmptyEntity entity)
    {
        return null;
    }
}
