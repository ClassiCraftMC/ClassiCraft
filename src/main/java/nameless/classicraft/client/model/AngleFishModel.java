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
public class AngleFishModel<T extends Entity> extends EntityModel<T> {

    public final ModelPart fish;
    public final ModelPart body_block;
    public final ModelPart tail;
    public final ModelPart left_fin;
    public final ModelPart right_fin;
    public final ModelPart bottom_fin;
    public final ModelPart top_fin;
    public final ModelPart cube_r1;
    public final ModelPart cube_r2;

    public AngleFishModel(ModelPart root) {
        this.fish = root.getChild("fish");
        this.body_block = fish.getChild("body_block");
        this.tail = fish.getChild("tail");
        this.left_fin = fish.getChild("left_fin");
        this.right_fin = fish.getChild("right_fin");
        this.bottom_fin = fish.getChild("bottom_fin");
        this.top_fin = fish.getChild("top_fin");
        this.cube_r1 = left_fin.getChild("cube_r1");
        this.cube_r2 = right_fin.getChild("cube_r2");
    }

    public static LayerDefinition create() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition fish = partdefinition.addOrReplaceChild("fish", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 0.0F));
        PartDefinition body_block = fish.addOrReplaceChild("body_block",
                CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -3.0F, -3.0F, 2.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition tail = fish.addOrReplaceChild("tail",
                CubeListBuilder.create().texOffs(15, 16).addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 4.0F));
        PartDefinition bottom_fin = fish.addOrReplaceChild("bottom_fin",
                CubeListBuilder.create().texOffs(12, 6).addBox(0.0F, -1.0F, -2.0F, 0.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 2.0F, 2.0F));
        PartDefinition top_fin = fish.addOrReplaceChild("top_fin",
                CubeListBuilder.create().texOffs(0, 13).addBox(0.0F, -1.0F, -3.0F, 0.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -3.0F, 3.0F));
        PartDefinition left_fin = fish.addOrReplaceChild("left_fin", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, -1.0F));
        PartDefinition cube_r1 = left_fin.addOrReplaceChild("cube_r1",
                CubeListBuilder.create().texOffs(12, 0).addBox(0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.2182F, 0.0F));
        PartDefinition right_fin = fish.addOrReplaceChild("right_fin", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.0F, -1.0F));
        PartDefinition cube_r2 = right_fin.addOrReplaceChild("cube_r2",
                CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.2182F, 0.0F));
        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green,
                               float blue, float alpha) {
        ImmutableList.of(this.fish).forEach((modelRenderer) -> {
            modelRenderer.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float degree = 1.0f;
        float speed = 3.0f;
        this.body_block.yRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 0.5F * limbSwingAmount;
        this.tail.yRot = Mth.cos(limbSwing * speed * 0.4F) * degree * -0.5F * limbSwingAmount;
        this.left_fin.zRot = Mth.cos(limbSwing * speed * 0.4F) * degree * -1.8F * limbSwingAmount;
        this.right_fin.zRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 1.8F * limbSwingAmount;
    }

    public void setRotationAngle(ModelPart modelPart, float x, float y, float z) {
        modelPart.xRot = x;
        modelPart.yRot = y;
        modelPart.zRot = z;
    }
}
