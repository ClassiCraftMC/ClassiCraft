package nameless.classicraft.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SwineModel<T extends Entity> extends EntityModel<T> {

    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart leg4;

    public SwineModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
        this.leg4 = root.getChild("leg4");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(28, 8).addBox(-5.0F, -10.0F, -7.0F, 10.0F, 16.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition body2 = body.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(0, 32).addBox(-8.0F, -3.0F, -8.0F, 16.0F, 12.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 61).addBox(-7.0F, 0.0F, 2.0F, 14.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        PartDefinition body_sub_1 = body2.addOrReplaceChild("body_sub_1", CubeListBuilder.create().texOffs(0, 36).addBox(0.0F, -6.0F, -11.0F, 0.0F, 7.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition tail = body2.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 32).addBox(0.0F, -1.0F, -1.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 7.5F, -0.3927F, 0.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(16, 16).addBox(-2.0F, 0.0F, -9.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, -6.0F));

        PartDefinition bone2 = head.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(38, 54).addBox(-5.5F, -12.0F, -14.0F, 11.0F, 8.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(33, 69).addBox(-5.5F, -12.0F, -14.0F, 11.0F, 8.0F, 5.0F, new CubeDeformation(0.5F))
                .texOffs(42, 32).addBox(-2.5F, -7.0F, -17.0F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, 9.0F));

        PartDefinition bone8 = bone2.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(0, 32).addBox(-3.0F, -3.0F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, -11.0F, -9.5F, 0.1745F, -0.3927F, 0.0F));

        PartDefinition bone9 = bone2.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(0, 32).addBox(0.0F, -3.0F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -11.0F, -9.5F, 0.1745F, 0.3927F, 0.0F));

        PartDefinition bone = bone2.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(-3.5F, -5.5F, -14.5F));

        PartDefinition bone6 = bone.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(52, 38).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.25F, 0.0F, 0.0F, 0.7459F, 0.274F, 1.2859F));

        PartDefinition bone7 = bone.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(52, 38).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.75F, 0.0F, 0.0F, 0.7459F, -0.274F, -1.2859F));

        PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 18.0F, 7.0F));

        PartDefinition leg1_sub_0 = leg1.addOrReplaceChild("leg1_sub_0", CubeListBuilder.create().texOffs(24, 79).addBox(-6.0F, -8.0F, 5.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 6.0F, -7.0F));

        PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 18.0F, 7.0F));

        PartDefinition leg2_sub_0 = leg2.addOrReplaceChild("leg2_sub_0", CubeListBuilder.create().texOffs(24, 79).addBox(3.0F, -8.0F, 5.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 6.0F, -7.0F));

        PartDefinition leg3 = partdefinition.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 18.0F, -5.0F));

        PartDefinition leg3_sub_0 = leg3.addOrReplaceChild("leg3_sub_0", CubeListBuilder.create().texOffs(24, 79).addBox(-6.0F, -8.0F, -5.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 6.0F, 5.0F));

        PartDefinition leg4 = partdefinition.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 18.0F, -5.0F));

        PartDefinition leg4_sub_0 = leg4.addOrReplaceChild("leg4_sub_0", CubeListBuilder.create().texOffs(24, 79).addBox(3.0F, -8.0F, -5.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 6.0F, 5.0F));

        return LayerDefinition.create(meshdefinition, 128, 160);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
