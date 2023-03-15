package nameless.classicraft.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public class JavelinModel extends Model {

    private final ModelPart bb_main;

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(17, 9).addBox(-0.5F, -27.0F, -0.5F, 1.0F, 22.0F, 1.0F, new CubeDeformation(0.01F))
                .texOffs(18, 0).addBox(-1.5F, -26.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 0).addBox(-0.5F, -26.0F, -1.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 0).addBox(0.5F, -26.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 0).addBox(-0.5F, -26.0F, 0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition pole_r1 = bb_main.addOrReplaceChild("pole_r1", CubeListBuilder.create().texOffs(11, 2).addBox(-0.01F, -7.5F, -6.5F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(13, 2).addBox(-0.01F, -6.5F, -7.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(14, 1).addBox(-0.01F, -9.5F, -9.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(13, 1).addBox(-0.01F, -8.5F, -8.5F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -18.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition pole_r2 = bb_main.addOrReplaceChild("pole_r2", CubeListBuilder.create().texOffs(13, 3).addBox(-7.5F, -6.5F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(10, 3).addBox(-9.5F, -9.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(11, 4).addBox(-6.5F, -7.5F, 0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(11, 3).addBox(-8.5F, -8.5F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -18.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    public JavelinModel(ModelPart root) {
        super(RenderType::entitySolid);
        this.bb_main = root.getChild("bb_main");
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.bb_main.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
