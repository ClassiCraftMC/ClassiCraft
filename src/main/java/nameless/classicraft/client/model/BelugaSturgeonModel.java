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
public class BelugaSturgeonModel<T extends Entity> extends EntityModel<T> {

    private final ModelPart fish;
    private final ModelPart body;
    private final ModelPart tail_root;
    private final ModelPart tail_1_block;
    private final ModelPart tail_2_block;
    private final ModelPart cube_r5;
    private final ModelPart cube_r6;
    private final ModelPart cube_r7;
    private final ModelPart cube_r8;
    private final ModelPart head;
    private final ModelPart cube_r3;
    private final ModelPart cube_r4;
    private final ModelPart left_fin;
    private final ModelPart cube_r1;
    private final ModelPart right_fin;
    private final ModelPart cube_r2;

    public BelugaSturgeonModel(ModelPart root) {
        this.fish = root.getChild("fish");
        this.body = fish.getChild("body");
        this.tail_root = body.getChild("tail_root");
        this.tail_1_block = tail_root.getChild("tail_1_block");
        this.tail_2_block = tail_1_block.getChild("tail_2_block");
        this.cube_r5 = tail_2_block.getChild("cube_r5");
        this.cube_r6 = tail_2_block.getChild("cube_r6");
        this.cube_r7 = tail_2_block.getChild("cube_r7");
        this.cube_r8 = tail_2_block.getChild("cube_r8");
        this.head = body.getChild("head");
        this.cube_r3 = head.getChild("cube_r3");
        this.cube_r4 = head.getChild("cube_r4");
        this.left_fin = fish.getChild("left_fin");
        this.cube_r1 = left_fin.getChild("cube_r1");
        this.right_fin = fish.getChild("right_fin");
        this.cube_r2 = right_fin.getChild("cube_r2");
    }

    public static LayerDefinition create() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition fish = partdefinition.addOrReplaceChild("fish", CubeListBuilder.create(), PartPose.offset(0.0F, 18.0F, 0.0F));

        PartDefinition body = fish.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -10.0F, -15.0F, 13.0F, 17.0F, 30.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -7.0F));

        PartDefinition tail_root = body.addOrReplaceChild("tail_root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 15.0F));

        PartDefinition tail_1_block = tail_root.addOrReplaceChild("tail_1_block", CubeListBuilder.create().texOffs(0, 48).addBox(-7.0F, -11.0F, 0.0F, 13.0F, 17.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

        PartDefinition tail_2_block = tail_1_block.addOrReplaceChild("tail_2_block", CubeListBuilder.create().texOffs(57, 0).addBox(-5.0F, -6.0F, -1.0F, 9.0F, 11.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 17.0F));

        PartDefinition cube_r5 = tail_2_block.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 48).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 11.0F, -0.2618F, 0.0F, 0.0F));

        PartDefinition cube_r6 = tail_2_block.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -8.0F, 0.0F, 1.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 11.0F, 0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r7 = tail_2_block.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(15, 12).addBox(-1.0F, -6.0F, 0.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 2.0F, -0.1309F, 0.0F, 0.0F));

        PartDefinition cube_r8 = tail_2_block.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, 3.0F, 0.0873F, 0.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(61, 48).addBox(-6.0F, -7.0F, -8.0F, 11.0F, 12.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(12, 0).addBox(-2.0F, -7.0F, -10.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -15.0F));

        PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -4.0F, -9.0F, 0.0F, 0.0F, 0.3491F));

        PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -4.0F, -9.0F, 0.0F, 0.0F, -0.3491F));

        PartDefinition left_fin = fish.addOrReplaceChild("left_fin", CubeListBuilder.create(), PartPose.offset(6.0F, 3.0F, -18.0F));

        PartDefinition cube_r1 = left_fin.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(54, 78).addBox(0.0F, 0.0F, -2.0F, 8.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition right_fin = fish.addOrReplaceChild("right_fin", CubeListBuilder.create(), PartPose.offset(-7.0F, 3.0F, -18.0F));

        PartDefinition cube_r2 = right_fin.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(61, 69).addBox(-8.0F, 0.0F, -2.0F, 8.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2182F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        float degree = 1.0f;
        float speed = 3.0f;
        this.body.yRot = Mth.cos(f * speed * 0.4F) * degree * 0.3F * f1;
        this.tail_root.yRot = Mth.cos(f * speed * 0.4F) * degree * -0.2F * f1;
        this.left_fin.zRot = Mth.cos(f * speed * 0.4F) * degree * -1.2F * f1;
        this.right_fin.zRot = Mth.cos(f * speed * 0.4F) * degree * 1.2F * f1;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.fish).forEach((modelRenderer) -> {
            modelRenderer.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        });
    }
}