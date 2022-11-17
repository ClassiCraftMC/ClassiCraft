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
public class SleeperSharkModel<T extends Entity> extends EntityModel<T> {

    private final ModelPart chark;
    public final ModelPart left_fin;
    public final ModelPart right_fin;
    public final ModelPart body_root;
    public final ModelPart tail;

    public SleeperSharkModel(ModelPart root) {
        this.chark = root.getChild("chark");
        this.body_root = chark.getChild("body_root");
        this.left_fin = body_root.getChild("left_fin");
        this.right_fin = body_root.getChild("right_fin");
        this.tail = chark.getChild("tail");
    }

    public static LayerDefinition create() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition chark = partdefinition.addOrReplaceChild("chark", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body_root = chark.addOrReplaceChild("body_root", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, 0.0F));

        PartDefinition body = body_root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -10.0F, -25.0F, 17.0F, 15.0F, 53.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));

        PartDefinition down_fin = body_root.addOrReplaceChild("down_fin", CubeListBuilder.create().texOffs(33, 21).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, 22.0F));

        PartDefinition up_fin = body_root.addOrReplaceChild("up_fin", CubeListBuilder.create().texOffs(36, 34).addBox(-1.0F, -3.0F, -2.0F, 1.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 18.0F));

        PartDefinition right_fin = body_root.addOrReplaceChild("right_fin", CubeListBuilder.create(), PartPose.offset(-9.0F, 6.0F, -13.0F));

        PartDefinition cube_r1 = right_fin.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 41).addBox(-10.0F, 0.0F, -2.0F, 10.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.0873F, -0.1745F));

        PartDefinition left_fin = body_root.addOrReplaceChild("left_fin", CubeListBuilder.create(), PartPose.offset(8.0F, 6.0F, -13.0F));

        PartDefinition cube_r2 = left_fin.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 69).addBox(0.0F, 0.0F, -2.0F, 10.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0873F, 0.1745F));

        PartDefinition head = body_root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -4.0F, -10.0F, 13.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -25.0F));

        PartDefinition tail = chark.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 21).addBox(-6.0F, -5.0F, -1.0F, 11.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 28.0F));

        PartDefinition cube_r3 = tail.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(53, 69).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 7.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 9.0F, -0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r4 = tail.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(28, 69).addBox(-1.0F, -11.0F, -1.0F, 1.0F, 12.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 9.0F, 0.0873F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setupAnim(T entity, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        float degree = 1.0f;
        float speed = 3.0f;
        this.body_root.yRot = Mth.cos(f * speed * 0.4F) * degree * 0.5F * f1;
        this.tail.yRot = Mth.cos(f * speed * 0.4F) * degree * -0.5F * f1;
        this.left_fin.zRot = Mth.cos(f * speed * 0.4F) * degree * -1.8F * f1;
        this.right_fin.zRot = Mth.cos(f * speed * 0.4F) * degree * 1.8F * f1;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.chark).forEach((modelRenderer) -> {
            modelRenderer.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        });
    }
}