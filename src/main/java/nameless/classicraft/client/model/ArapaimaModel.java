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
public class ArapaimaModel<T extends Entity> extends EntityModel<T> {

    private final ModelPart fishe;
    private final ModelPart body_root;
    private final ModelPart tail_root;
    private final ModelPart tail_2_block;
    private final ModelPart tail;
    private final ModelPart tail_fin;
    private final ModelPart left_fin;
    private final ModelPart right_fin;
    private final ModelPart cube_r1;
    private final ModelPart cube_r2;
    private final ModelPart cube_r3;
    private final ModelPart cube_r4;
    private final ModelPart body;
    private final ModelPart back_fins;

    public ArapaimaModel(ModelPart root) {
        this.fishe = root.getChild("fishe");
        this.body_root = fishe.getChild("body_root");
        this.tail_root = fishe.getChild("tail_root");
        this.tail_2_block = tail_root.getChild("tail_2_block");
        this.tail = tail_2_block.getChild("tail");
        this.tail_fin = tail.getChild("tail_fin");
        this.left_fin = body_root.getChild("left_fin");
        this.right_fin = body_root.getChild("right_fin");
        this.back_fins = body_root.getChild("back_fins");
        this.cube_r1 = left_fin.getChild("cube_r1");
        this.cube_r2 = right_fin.getChild("cube_r2");
        this.cube_r3 = back_fins.getChild("cube_r3");
        this.cube_r4 = back_fins.getChild("cube_r4");
        this.body = body_root.getChild("body");
    }

    public static LayerDefinition create() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition fishe = partdefinition.addOrReplaceChild("fishe", CubeListBuilder.create(), PartPose.offset(0.0F, 20.0F, 3.0F));

        PartDefinition body_root = fishe.addOrReplaceChild("body_root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 7.0F));

        PartDefinition left_fin = body_root.addOrReplaceChild("left_fin", CubeListBuilder.create(), PartPose.offset(4.0F, 3.0F, -21.0F));

        PartDefinition cube_r1 = left_fin.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(8, 8).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.1745F, 0.0F));

        PartDefinition right_fin = body_root.addOrReplaceChild("right_fin", CubeListBuilder.create(), PartPose.offset(-4.0F, 3.0F, -21.0F));

        PartDefinition cube_r2 = right_fin.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(8, 0).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.1745F, 0.0F));

        PartDefinition back_fins = body_root.addOrReplaceChild("back_fins", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -3.0F));

        PartDefinition cube_r3 = back_fins.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 8).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 3.0F, -2.0F, 0.0F, -0.1745F, 0.0F));

        PartDefinition cube_r4 = back_fins.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 3.0F, -2.0F, 0.0F, 0.1745F, 0.0F));

        PartDefinition body = body_root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -24.0F, 8.0F, 9.0F, 26.0F, new CubeDeformation(0.0F))
                .texOffs(0, 47).addBox(-4.0F, 0.0F, -31.0F, 8.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -3.0F));

        PartDefinition tail_root = fishe.addOrReplaceChild("tail_root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 7.0F));

        PartDefinition tail_2_block = tail_root.addOrReplaceChild("tail_2_block", CubeListBuilder.create().texOffs(40, 35).addBox(-4.0F, -5.0F, 0.0F, 8.0F, 9.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));

        PartDefinition tail = tail_2_block.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(42, 0).addBox(-4.0F, -4.0F, 0.0F, 8.0F, 8.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 23).addBox(0.0F, -10.0F, -4.0F, 0.0F, 6.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(0, 9).addBox(0.0F, 3.0F, -12.0F, 0.0F, 6.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 12.0F));

        PartDefinition tail_fin = tail.addOrReplaceChild("tail_fin", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -3.0F, 0.0F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 12.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float degree = 1.0f;
        float speed = 3.0f;
        this.body_root.yRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 0.3F * limbSwingAmount;
        this.tail_root.yRot = Mth.cos(limbSwing * speed * 0.4F) * degree * -0.2F * limbSwingAmount;
        this.tail.yRot = Mth.cos(limbSwing * speed * 0.4F) * degree * -0.2F * limbSwingAmount;
        this.left_fin.zRot = Mth.cos(limbSwing * speed * 0.4F) * degree * -1.2F * limbSwingAmount;
        this.right_fin.zRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 1.2F * limbSwingAmount;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.fishe).forEach((modelRenderer) -> {
            modelRenderer.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        });
    }
}