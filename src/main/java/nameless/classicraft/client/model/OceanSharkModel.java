package nameless.classicraft.client.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class OceanSharkModel<T extends Entity> extends EntityModel<T> {

    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart right_fin;
    private final ModelPart left_fin;
    private final ModelPart back_fin;
    private final ModelPart tail_fin;

    public OceanSharkModel(ModelPart root) {
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.tail = root.getChild("tail");
        this.right_fin = root.getChild("right_fin");
        this.left_fin = root.getChild("left_fin");
        this.back_fin = root.getChild("back_fin");
        this.tail_fin = root.getChild("tail_fin");
    }

    public static TexturedModelData create() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();

        ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create().uv(32, 46).cuboid(-4.0F, -6.0F, -20.5F, 8.0F, 9.0F, 16.0F, new Dilation(0.0F))
                .uv(45, 0).cuboid(-3.5F, 3.0F, -18.5F, 7.0F, 3.0F, 14.0F, new Dilation(0.0F))
                .uv(64, 93).cuboid(-4.0F, -3.0F, -3.0F, 8.0F, 7.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 20.0F, -6.0F));

        ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -10.0F, -7.5F, 12.0F, 14.0F, 21.0F, new Dilation(0.0F))
                .uv(86, 93).cuboid(-4.0F, -7.0F, 0.0F, 8.0F, 7.0F, 13.0F, new Dilation(0.0F))
                .uv(64, 106).cuboid(-1.0F, -2.0F, -10.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, -3.0F));

        ModelPartData tail = partdefinition.addChild("tail", ModelPartBuilder.create().uv(0, 35).cuboid(-4.0F, -7.0F, -0.5F, 8.0F, 11.0F, 16.0F, new Dilation(0.0F))
                .uv(54, 23).cuboid(-2.5F, -7.0F, 15.5F, 5.0F, 8.0F, 12.0F, new Dilation(0.0F))
                .uv(64, 112).cuboid(-2.0F, -2.5F, -1.0F, 4.0F, 5.0F, 11.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 21.5F, 11.0F));

        ModelPartData body_fin = tail.addChild("body_fin", ModelPartBuilder.create().uv(20, 63).cuboid(-1.0F, -19.25F, -5.0F, 2.0F, 12.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.5F, -11.0F, -0.2618F, 0.0F, 0.0F));

        ModelPartData left_tail_fin = tail.addChild("left_tail_fin", ModelPartBuilder.create().uv(32, 35).cuboid(-0.25F, 8.0F, 17.0F, 1.0F, 8.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.5F, -13.0F, 0.2182F, 0.0F, -0.8727F));

        ModelPartData right_tail_fin = tail.addChild("right_tail_fin", ModelPartBuilder.create().uv(0, 35).cuboid(-0.75F, 8.0F, 17.0F, 1.0F, 8.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.5F, -13.0F, 0.2182F, 0.0F, 0.8727F));

        ModelPartData mid_tail_fin = tail.addChild("mid_tail_fin", ModelPartBuilder.create().uv(45, 0).cuboid(-0.5F, -19.5F, 14.0F, 1.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.5F, -11.0F, -0.2618F, 0.0F, 0.0F));

        ModelPartData left_body_fin = tail.addChild("left_body_fin", ModelPartBuilder.create().uv(0, 62).cuboid(0.0F, 5.75F, 1.0F, 2.0F, 12.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.5F, -17.0F, 0.2182F, 0.0F, -1.309F));

        ModelPartData right_body_fin = tail.addChild("right_body_fin", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, 5.75F, 1.0F, 2.0F, 12.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.5F, -17.0F, 0.2182F, 0.0F, 1.309F));

        ModelPartData right_fin = partdefinition.addChild("right_fin", ModelPartBuilder.create().uv(112, 113).cuboid(-0.5F, -4.0F, 0.0F, 1.0F, 4.0F, 7.0F, new Dilation(0.0F)), ModelTransform.rotation(-4.5F, 24.0F, -2.0F));

        ModelPartData left_fin = partdefinition.addChild("left_fin", ModelPartBuilder.create().uv(112, 113).cuboid(-0.5F, -4.0F, 0.0F, 1.0F, 4.0F, 7.0F, new Dilation(0.0F)), ModelTransform.rotation(4.5F, 24.0F, -2.0F));

        ModelPartData back_fin = partdefinition.addChild("back_fin", ModelPartBuilder.create().uv(115, 93).cuboid(-0.5F, 0.0F, 8.0F, 1.0F, 4.0F, 5.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, 13.0F, -5.0F));

        ModelPartData tail_fin = partdefinition.addChild("tail_fin", ModelPartBuilder.create().uv(83, 113).cuboid(-5.0F, -0.5F, -1.0F, 10.0F, 1.0F, 6.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, 21.5F, 20.0F));

        ModelPartData back_tail_fin = tail_fin.addChild("back_tail_fin", ModelPartBuilder.create().uv(56, 71).cuboid(-1.0F, -34.5F, 26.25F, 2.0F, 14.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.5F, -20.0F, -0.4363F, 0.0F, 0.0F));

        ModelPartData back_lower_tail_fin = tail_fin.addChild("back_lower_tail_fin", ModelPartBuilder.create().uv(40, 71).cuboid(-1.0F, 10.25F, 30.75F, 2.0F, 14.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.5F, -20.0F, 0.4363F, 0.0F, 0.0F));

        return TexturedModelData.of(meshdefinition, 128, 128);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        float f = 1.0F;
        float g = 1.0F;
        if (!entity.isTouchingWater()) {
            f = 1.3F;
            g = 1.7F;
        }

        this.tail.yaw = -f * 0.25F * MathHelper.sin(g * 0.6F * animationProgress);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        head.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        body.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        tail.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        right_fin.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        left_fin.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        back_fin.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        tail_fin.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }

}
