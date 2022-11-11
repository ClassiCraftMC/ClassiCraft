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
public class BoxfishModel<T extends Entity> extends EntityModel<T> {

    private final ModelPart fishe;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart right_fin;
    private final ModelPart cube_r1;
    private final ModelPart left_fin;
    private final ModelPart cube_r2;

    public BoxfishModel(ModelPart root) {
        this.fishe = root.getChild("fishe");
        this.body = fishe.getChild("body");
        this.tail = fishe.getChild("tail");
        this.right_fin = fishe.getChild("right_fin");
        this.cube_r1 = right_fin.getChild("cube_r1");
        this.left_fin = fishe.getChild("left_fin");
        this.cube_r2 = left_fin.getChild("cube_r2");
    }

    public static LayerDefinition create() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition fishe = partdefinition.addOrReplaceChild("fishe", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 0.0F));

        PartDefinition body = fishe.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.0F, -4.0F, 4.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(4, 4).addBox(0.0F, 0.0F, -5.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(14, 13).addBox(0.0F, -5.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition tail = fishe.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 13).addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

        PartDefinition right_fin = fishe.addOrReplaceChild("right_fin", CubeListBuilder.create(), PartPose.offset(-2.0F, 1.0F, -2.0F));

        PartDefinition cube_r1 = right_fin.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(9, 13).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.1745F, 0.0F));

        PartDefinition left_fin = fishe.addOrReplaceChild("left_fin", CubeListBuilder.create(), PartPose.offset(2.0F, 1.0F, -2.0F));

        PartDefinition cube_r2 = left_fin.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.1745F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(T entity, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        float degree = 1.0f;
        float speed = 3.0f;
        this.fishe.yRot = Mth.cos(f * speed * 0.4F) * degree * 0.5F * f1;
        this.tail.yRot = Mth.cos(f * speed * 0.4F) * degree * -0.5F * f1;
        this.left_fin.zRot = Mth.cos(f * speed * 0.4F) * degree * -1.8F * f1;
        this.right_fin.zRot = Mth.cos(f * speed * 0.4F) * degree * 1.8F * f1;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.fishe).forEach((modelRenderer) -> {
            modelRenderer.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        });
    }

    public void setRotationAngle(ModelPart modelPart, float x, float y, float z) {
        modelPart.xRot = x;
        modelPart.yRot = y;
        modelPart.zRot = z;
    }
}