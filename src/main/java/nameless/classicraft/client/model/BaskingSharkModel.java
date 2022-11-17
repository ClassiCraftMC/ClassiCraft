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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BaskingSharkModel<T extends Entity> extends EntityModel<T> {

    private final ModelPart main;
    private final ModelPart tail;
    private final ModelPart middle;

    public BaskingSharkModel(ModelPart root) {
        this.main = root.getChild("main");
        this.middle = main.getChild("middle");
        this.tail = middle.getChild("tail");
    }

    public static LayerDefinition create() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 0).addBox(-13.0F, -16.0F, -13.0F, 26.0F, 32.0F, 27.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 0.0F));

        PartDefinition head = main.addOrReplaceChild("head", CubeListBuilder.create().texOffs(82, 35).addBox(-10.0F, -14.0F, -25.0F, 21.0F, 10.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -12.0F));

        PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(78, 101).addBox(-10.0F, -10.0F, -16.0F, 21.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(79, 0).addBox(-10.0F, 8.0F, -16.0F, 21.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(113, 117).addBox(-10.0F, -10.0F, -2.0F, 21.0F, 20.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, -1.0F));

        PartDefinition cube_r1 = jaw.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(106, 16).addBox(1.0F, 8.0F, -15.0F, 16.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(0, 112).addBox(1.0F, -11.0F, -15.0F, 16.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, -1.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition middle = main.addOrReplaceChild("middle", CubeListBuilder.create().texOffs(0, 59).addBox(-10.0F, -13.0F, 0.0F, 20.0F, 27.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 14.0F));

        PartDefinition tail = middle.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(92, 69).addBox(-5.0F, -6.0F, 0.0F, 10.0F, 13.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 26.0F));

        PartDefinition backfin = tail.addOrReplaceChild("backfin", CubeListBuilder.create().texOffs(0, 128).addBox(-1.0F, -16.0F, 0.0F, 2.0F, 30.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 17.0F));

        PartDefinition tailbottomfin = tail.addOrReplaceChild("tailbottomfin", CubeListBuilder.create().texOffs(0, 59).addBox(-1.0F, 0.0F, -4.0F, 1.0F, 7.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.0F, 9.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition tailfintop = tail.addOrReplaceChild("tailfintop", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -7.0F, -4.0F, 1.0F, 7.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, 9.0F, -0.1745F, 0.0F, 0.0F));

        PartDefinition dorsalfin = middle.addOrReplaceChild("dorsalfin", CubeListBuilder.create().texOffs(26, 136).addBox(-1.0F, -13.0F, -7.0F, 2.0F, 13.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, 9.0F, -0.1745F, 0.0F, 0.0F));

        PartDefinition middlefin = middle.addOrReplaceChild("middlefin", CubeListBuilder.create().texOffs(92, 69).addBox(0.0F, 0.0F, -4.0F, 1.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 14.0F, 12.0F, 0.0F, 0.0F, -0.2182F));

        PartDefinition middlefin2 = middle.addOrReplaceChild("middlefin2", CubeListBuilder.create().texOffs(66, 61).addBox(-1.0F, 0.0F, -4.0F, 1.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 14.0F, 12.0F, 0.0F, 0.0F, 0.2182F));

        PartDefinition fin = main.addOrReplaceChild("fin", CubeListBuilder.create().texOffs(79, 117).addBox(-1.0F, 0.0F, -7.0F, 2.0F, 22.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, 15.0F, -1.0F, 0.0F, 0.0F, -0.2182F));

        PartDefinition fin2 = main.addOrReplaceChild("fin2", CubeListBuilder.create().texOffs(45, 113).addBox(-1.0F, 0.0F, -7.0F, 2.0F, 22.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, 15.0F, -1.0F, 0.0F, 0.0F, 0.2182F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setupAnim(T entity, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        float degree = 1.0f;
        float speed = 0.5f;
        this.main.yRot = Mth.cos(f * speed * 0.4F) * degree * 0.5F * f1;
        this.tail.yRot = Mth.cos(f * speed * 0.4F) * degree * -0.5F * f1;
        this.middle.yRot = Mth.cos(f * speed * 0.4F) * degree * -0.5F * f1;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.main).forEach((modelRenderer) -> {
            modelRenderer.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        });
    }
}