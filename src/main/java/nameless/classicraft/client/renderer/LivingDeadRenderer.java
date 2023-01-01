package nameless.classicraft.client.renderer;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.entity.LivingDeadEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LivingDeadRenderer<T extends LivingDeadEntity> extends MobRenderer<LivingDeadEntity, HumanoidModel<LivingDeadEntity>> {

    private static final ResourceLocation[] TEXTURES =
            new ResourceLocation[LivingDeadEntity.MAX_VARIANTS];
    private static final ResourceLocation DEFAULT_TEXTURES =
            new ResourceLocation(ClassiCraftMod.MOD_ID, "textures/entity/living_dead/zombie1.png");

    public LivingDeadRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new HumanoidModel<>(pContext.bakeLayer(ModelLayers.ZOMBIE)), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel<>(pContext.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new HumanoidModel<>(pContext.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR))));
        this.addLayer(new LivingDeadEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(LivingDeadEntity pEntity) {
        int variant = pEntity.getVariant() + 1;
        if (TEXTURES[variant - 1] == null) {
            ResourceLocation loc = new ResourceLocation(ClassiCraftMod.MOD_ID, "textures/entity/living_dead/zombie" + variant + ".png");
            if (Minecraft.getInstance().getResourceManager().getResource(loc).isEmpty()) {
                ClassiCraftMod.LOGGER.warn("Found Unknown variant " + variant + ", using default");
                loc = DEFAULT_TEXTURES;
                return loc;
            }

            return TEXTURES[variant - 1] = loc;
        }

        return TEXTURES[variant - 1];
    }

}
