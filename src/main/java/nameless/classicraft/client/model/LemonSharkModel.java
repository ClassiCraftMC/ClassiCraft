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
public class LemonSharkModel<T extends Entity> extends EntityModel<T> {

    private final ModelPart fish;
    private final ModelPart tail;
    private final ModelPart let_fin;
    private final ModelPart right_fin;

    public LemonSharkModel(ModelPart root) {
        this.fish = root.getChild("fish");
        this.tail = fish.getChild("tail");
        this.let_fin = fish.getChild("let_fin");
        this.right_fin = fish.getChild("right_fin");
    }

    public static LayerDefinition create() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition fish = partdefinition.addOrReplaceChild("fish", CubeListBuilder.create(), PartPose.offset(0.0F, 20.0F, 0.0F));

        PartDefinition body = fish.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -6.0F, -13.0F, 11.0F, 10.0F, 30.0F, new CubeDeformation(0.0F))
                .texOffs(21, 49).addBox(-1.0F, -12.0F, -7.0F, 1.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = fish.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -6.0F, 7.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(40, 49).addBox(-4.0F, -4.0F, -10.0F, 7.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -13.0F));

        PartDefinition tail = fish.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 15).addBox(-3.0F, -4.0F, 0.0F, 5.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 17.0F));

        PartDefinition cube_r1 = tail.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(53, 0).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 7.0F, -0.1309F, 0.0F, 0.0F));

        PartDefinition cube_r2 = tail.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 49).addBox(-1.0F, -6.0F, 0.0F, 1.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 6.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition right_fin = fish.addOrReplaceChild("right_fin", CubeListBuilder.create(), PartPose.offset(-6.0F, 3.0F, -4.0F));

        PartDefinition cube_r3 = right_fin.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 41).addBox(-9.0F, 0.0F, -1.0F, 9.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, 0.1309F, -0.1309F));

        PartDefinition let_fin = fish.addOrReplaceChild("let_fin", CubeListBuilder.create(), PartPose.offset(5.0F, 3.0F, -4.0F));

        PartDefinition cube_r4 = let_fin.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(31, 41).addBox(0.0F, 0.0F, -1.0F, 9.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, -0.1309F, 0.1309F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        float degree = 1.0f;
        float speed = 3.0f;
        this.fish.yRot = Mth.cos(f * speed * 0.4F) * degree * 0.5F * f1;
        this.tail.yRot = Mth.cos(f * speed * 0.4F) * degree * -0.5F * f1;
        this.let_fin.zRot = Mth.cos(f * speed * 0.4F) * degree * -1.8F * f1;
        this.right_fin.zRot = Mth.cos(f * speed * 0.4F) * degree * 1.8F * f1;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.fish).forEach((modelRenderer) -> {
            modelRenderer.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        });
    }
}