package nameless.classicraft.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class RiverSharkModel<T extends Entity> extends EntityModel<T> {
    private final ModelPart shark;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart head;
    private final ModelPart right_fin;
    private final ModelPart left_fin;

    public RiverSharkModel(ModelPart root) {
        this.shark = root.getChild("shark");
        this.body = shark.getChild("body");
        this.tail = shark.getChild("tail");
        this.head = body.getChild("head");
        this.right_fin = shark.getChild("right_fin");
        this.left_fin = shark.getChild("left_fin");
    }

    public static LayerDefinition create() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition shark = partdefinition.addOrReplaceChild("shark", CubeListBuilder.create(), PartPose.offset(0.0F, 21.0F, 0.0F));

        PartDefinition right_fin = shark.addOrReplaceChild("right_fin", CubeListBuilder.create(), PartPose.offset(-2.0F, 1.0F, -3.0F));

        PartDefinition cube_r1 = right_fin.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(34, 35).addBox(-5.0F, 0.0F, -1.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition left_fin = shark.addOrReplaceChild("left_fin", CubeListBuilder.create(), PartPose.offset(3.0F, 1.0F, -3.0F));

        PartDefinition cube_r2 = left_fin.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(16, 35).addBox(0.0F, 0.0F, -1.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition body = shark.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -5.0F, -8.0F, 5.0F, 8.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, -4.0F, 0.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -5.0F, 1.0F, -0.0436F, 0.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(12, 22).addBox(-2.0F, -4.0F, -6.0F, 5.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 35).addBox(-2.0F, -4.0F, -3.0F, 5.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -8.0F));

        PartDefinition tail = shark.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(24, 0).addBox(-2.0F, -5.0F, 0.0F, 5.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(36, 22).addBox(-2.0F, -3.0F, 3.0F, 5.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(22, 22).addBox(-1.0F, -3.0F, 4.0F, 3.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 6.0F));

        PartDefinition cube_r4 = tail.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 9).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 5.0F, -0.1309F, 0.0F, 0.0F));

        PartDefinition cube_r5 = tail.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(33, 4).addBox(-2.0F, -1.0F, -1.0F, 1.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 1.0F, 12.0F, -0.4363F, 0.0F, 0.0F));

        PartDefinition cube_r6 = tail.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 22).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.0F, 12.0F, 0.5672F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        float degree = 1.0f;
        float speed = 3.0f;
        this.body.yRot = Mth.cos(f * speed * 0.4F) * degree * 0.5F * f1;
        this.tail.yRot = Mth.cos(f * speed * 0.4F) * degree * -0.5F * f1;
        this.head.yRot = Mth.cos(f * speed * 0.4F) * degree * -0.5F * f1;
        this.left_fin.zRot = Mth.cos(f * speed * 0.4F) * degree * -1.8F * f1;
        this.right_fin.zRot = Mth.cos(f * speed * 0.4F) * degree * 1.8F * f1;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.shark).forEach((modelRenderer) -> {
            modelRenderer.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        });
    }
}
