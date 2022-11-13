package nameless.classicraft.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BullSharkModel<T extends Entity> extends EntityModel<T> {

    private final ModelPart shark;
    private final ModelPart tail;
    private final ModelPart left_fin;
    private final ModelPart right_fin;

    public BullSharkModel(ModelPart root) {
        this.shark = root.getChild("shark");
        this.tail = shark.getChild("tail");
        this.left_fin = shark.getChild("left_fin");
        this.right_fin = shark.getChild("right_fin");
    }

    public static LayerDefinition create() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition shark = partdefinition.addOrReplaceChild("shark", CubeListBuilder.create(), PartPose.offset(0.0F, 15.0F, 0.0F));

        PartDefinition head = shark.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 46).addBox(-7.0F, -4.0F, -8.0F, 15.0F, 10.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(62, 0).addBox(-7.0F, -4.0F, -13.0F, 15.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, -11.0F));

        PartDefinition body = shark.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -12.0F, -11.0F, 17.0F, 18.0F, 27.0F, new CubeDeformation(0.0F))
                .texOffs(70, 63).addBox(0.0F, -20.0F, -4.0F, 1.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 0.0F));

        PartDefinition tail = shark.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 65).addBox(-5.0F, -7.0F, 1.0F, 11.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 15.0F));

        PartDefinition cube_r1 = tail.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(35, 72).addBox(0.0F, 0.0F, 0.0F, 1.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 7.0F, -0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r2 = tail.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -7.0F, 0.0F, 1.0F, 7.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.0F, 7.0F, 0.1309F, 0.0F, 0.0F));

        PartDefinition left_fin = shark.addOrReplaceChild("left_fin", CubeListBuilder.create(), PartPose.offset(9.0F, 5.0F, -4.0F));

        PartDefinition cube_r3 = left_fin.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(36, 59).addBox(0.0F, 0.0F, -1.0F, 10.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, -0.0873F, 0.0873F));

        PartDefinition right_fin = shark.addOrReplaceChild("right_fin", CubeListBuilder.create(), PartPose.offset(-8.0F, 5.0F, -4.0F));

        PartDefinition cube_r4 = right_fin.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(47, 46).addBox(-10.0F, 0.0F, -1.0F, 10.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, 0.0873F, -0.0873F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        float degree = 1.0f;
        float speed = 3.0f;
        this.shark.yRot = Mth.cos(f * speed * 0.4F) * degree * 0.5F * f1;
        this.tail.yRot = Mth.cos(f * speed * 0.4F) * degree * -0.5F * f1;
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