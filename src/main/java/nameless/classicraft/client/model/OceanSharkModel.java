package nameless.classicraft.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

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

    public static LayerDefinition create() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(32, 46).addBox(-4.0F, -6.0F, -20.5F, 8.0F, 9.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(45, 0).addBox(-3.5F, 3.0F, -18.5F, 7.0F, 3.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(64, 93).addBox(-4.0F, -3.0F, -3.0F, 8.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, -6.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -10.0F, -7.5F, 12.0F, 14.0F, 21.0F, new CubeDeformation(0.0F))
                .texOffs(86, 93).addBox(-4.0F, -7.0F, 0.0F, 8.0F, 7.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(64, 106).addBox(-1.0F, -2.0F, -10.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, -3.0F));

        PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 35).addBox(-4.0F, -7.0F, -0.5F, 8.0F, 11.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(54, 23).addBox(-2.5F, -7.0F, 15.5F, 5.0F, 8.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(64, 112).addBox(-2.0F, -2.5F, -1.0F, 4.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.5F, 11.0F));

        PartDefinition body_fin = tail.addOrReplaceChild("body_fin", CubeListBuilder.create().texOffs(20, 63).addBox(-1.0F, -19.25F, -5.0F, 2.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.5F, -11.0F, -0.2618F, 0.0F, 0.0F));

        PartDefinition left_tail_fin = tail.addOrReplaceChild("left_tail_fin", CubeListBuilder.create().texOffs(32, 35).addBox(-0.25F, 8.0F, 17.0F, 1.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, -13.0F, 0.2182F, 0.0F, -0.8727F));

        PartDefinition right_tail_fin = tail.addOrReplaceChild("right_tail_fin", CubeListBuilder.create().texOffs(0, 35).addBox(-0.75F, 8.0F, 17.0F, 1.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, -13.0F, 0.2182F, 0.0F, 0.8727F));

        PartDefinition mid_tail_fin = tail.addOrReplaceChild("mid_tail_fin", CubeListBuilder.create().texOffs(45, 0).addBox(-0.5F, -19.5F, 14.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.5F, -11.0F, -0.2618F, 0.0F, 0.0F));

        PartDefinition left_body_fin = tail.addOrReplaceChild("left_body_fin", CubeListBuilder.create().texOffs(0, 62).addBox(0.0F, 5.75F, 1.0F, 2.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.5F, -17.0F, 0.2182F, 0.0F, -1.309F));

        PartDefinition right_body_fin = tail.addOrReplaceChild("right_body_fin", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 5.75F, 1.0F, 2.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.5F, -17.0F, 0.2182F, 0.0F, 1.309F));

        PartDefinition right_fin = partdefinition.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(112, 113).addBox(-0.5F, -4.0F, 0.0F, 1.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 24.0F, -2.0F));

        PartDefinition left_fin = partdefinition.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(112, 113).addBox(-0.5F, -4.0F, 0.0F, 1.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 24.0F, -2.0F));

        PartDefinition back_fin = partdefinition.addOrReplaceChild("back_fin", CubeListBuilder.create().texOffs(115, 93).addBox(-0.5F, 0.0F, 8.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, -5.0F));

        PartDefinition tail_fin = partdefinition.addOrReplaceChild("tail_fin", CubeListBuilder.create().texOffs(83, 113).addBox(-5.0F, -0.5F, -1.0F, 10.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.5F, 20.0F));

        PartDefinition back_tail_fin = tail_fin.addOrReplaceChild("back_tail_fin", CubeListBuilder.create().texOffs(56, 71).addBox(-1.0F, -34.5F, 26.25F, 2.0F, 14.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.5F, -20.0F, -0.4363F, 0.0F, 0.0F));

        PartDefinition back_lower_tail_fin = tail_fin.addOrReplaceChild("back_lower_tail_fin", CubeListBuilder.create().texOffs(40, 71).addBox(-1.0F, 10.25F, 30.75F, 2.0F, 14.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.5F, -20.0F, 0.4363F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float degree = 1.0f;
        float speed = 3.0f;
        this.body.yRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 0.3F * limbSwingAmount;
        this.tail.yRot = Mth.cos(limbSwing * speed * 0.4F) * degree * -0.2F * limbSwingAmount;
        this.tail.yRot = Mth.cos(limbSwing * speed * 0.4F) * degree * -0.2F * limbSwingAmount;
        this.left_fin.zRot = Mth.cos(limbSwing * speed * 0.4F) * degree * -1.2F * limbSwingAmount;
        this.right_fin.zRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 1.2F * limbSwingAmount;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        right_fin.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        left_fin.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        back_fin.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        tail_fin.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
