package nameless.classicraft.client.model;

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
public class ButterflyModel<T extends Entity> extends EntityModel<T> {

    private final ModelPart body;
    private final ModelPart rightwing;
    private final ModelPart leftwing;
    private final ModelPart antennae;

    public ButterflyModel(ModelPart root) {
        this.body = root.getChild("body");
        setRotation(body, 1.570796F, 0F, 0F);
        this.rightwing = root.getChild("rightwing");
        setRotation(rightwing, 0F, 0F, 0F);
        this.leftwing = root.getChild("leftwing");
        setRotation(leftwing, 0F, 0F, 0F);
        this.antennae = root.getChild("antennae");
        setRotation(antennae, 0F, 0F, 0F);
    }

    public static LayerDefinition create() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -2.0F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 21.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition rightwing = partdefinition.addOrReplaceChild("rightwing", CubeListBuilder.create().texOffs(0, 6).addBox(-6.0F, 0.0F, -4.0F, 6.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.5F, 2.0F));

        PartDefinition leftwing = partdefinition.addOrReplaceChild("leftwing", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, 0.0F, -4.0F, 6.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 21.5F, 2.0F));

        PartDefinition antennae = partdefinition.addOrReplaceChild("antennae", CubeListBuilder.create().texOffs(4, 0).addBox(-1.0F, 0.0F, -2.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.5F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightwing.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftwing.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        antennae.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelPart model, float x, float y, float z)
    {
        model.xRot = x;
        model.y = y;
        model.zRot = z;
    }

    @Override
    public void setupAnim(T pState, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.rightwing.x = -1.5F;
        this.leftwing.x = 1.5F;
        rightwing.zRot = -(Mth.cos(pLimbSwing * 1.7F) * (float)Math.PI * 0.2F);
        leftwing.zRot= Mth.cos(pLimbSwing * 1.7F) * (float)Math.PI * 0.2F;
    }

}