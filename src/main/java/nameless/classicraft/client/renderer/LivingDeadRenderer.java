package nameless.classicraft.client.renderer;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.entity.LivingDeadEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LivingDeadRenderer<T extends LivingDeadEntity> extends MobEntityRenderer<LivingDeadEntity, BipedEntityModel<LivingDeadEntity>> {

    private static final Identifier[] TEXTURES =
            new Identifier[LivingDeadEntity.MAX_VARIANTS];
    private static final Identifier DEFAULT_TEXTURES =
            new Identifier(ClassiCraftMod.MOD_ID, "textures/entity/living_dead/living_dead_1.png");

    public LivingDeadRenderer(EntityRendererFactory.Context arg, BipedEntityModel<LivingDeadEntity> arg2, float f) {
        super(arg, new BipedEntityModel<>(arg.getPart(EntityModelLayers.ZOMBIE)), 0.5F);
        this.addFeature(new ArmorFeatureRenderer<>(this, new BipedEntityModel<>(arg.getPart(EntityModelLayers.ZOMBIE_INNER_ARMOR)),
               new BipedEntityModel<>(arg.getPart(EntityModelLayers.PLAYER_OUTER_ARMOR))));
        this.addFeature(new LivingDeadEyesLayer<>(this));
    }

    @Override
    public Identifier getTexture(LivingDeadEntity entity) {
        int variant = entity.getVariant() + 1;
        if (TEXTURES[variant - 1] == null) {
            Identifier loc = new Identifier(ClassiCraftMod.MOD_ID, "textures/entity/living_dead/living_dead_" + variant + ".png");
            if (MinecraftClient.getInstance().getResourceManager().getResource(loc).isEmpty()) {
                ClassiCraftMod.LOGGER.warn("Found Unknown variant " + variant + ", using default");
                loc = DEFAULT_TEXTURES;
                return loc;
            }

            return TEXTURES[variant - 1] = loc;
        }

        return TEXTURES[variant - 1];
    }

}
