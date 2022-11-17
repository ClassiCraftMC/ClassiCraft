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
public class FoxFaceFishModel<T extends Entity> extends EntityModel<T> {

    private final ModelPart fishe;
    private final ModelPart tail;
    private final ModelPart left_fin;
    private final ModelPart right_fin;

    public FoxFaceFishModel(ModelPart root) {
        this.fishe = root.getChild("fishe");
        this.left_fin = fishe.getChild("left_fin");
        this.right_fin = fishe.getChild("right_fin");
        this.tail = fishe.getChild("tail");
    }

    public static LayerDefinition create() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition fishe = partdefinition.addOrReplaceChild("fishe", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 0.0F));

        PartDefinition tail = fishe.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

        PartDefinition body = fishe.addOrReplaceChild("body", CubeListBuilder.create().texOffs(13, 0).addBox(0.0F, 2.0F, 1.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 14).addBox(0.0F, -4.0F, -1.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.0F, -2.0F, -4.0F, 2.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(0.0F, 0.0F, -5.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

        PartDefinition left_fin = fishe.addOrReplaceChild("left_fin", CubeListBuilder.create(), PartPose.offset(1.0F, 1.0F, -2.0F));

        PartDefinition cube_r1 = left_fin.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(7, 14).addBox(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.5236F, 0.0F));

        PartDefinition right_fin = fishe.addOrReplaceChild("right_fin", CubeListBuilder.create(), PartPose.offset(-1.0F, 1.0F, -2.0F));

        PartDefinition cube_r2 = right_fin.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 14).addBox(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

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
}