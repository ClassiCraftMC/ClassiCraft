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
public class FootballFishModel<T extends Entity> extends EntityModel<T> {

    private final ModelPart body;
    private final ModelPart tailfin;
    private final ModelPart backfin;
    private final ModelPart backfin2;
    private final ModelPart lure;
    private final ModelPart jaw;
    private final ModelPart teeth;
    private final ModelPart rightfin;
    private final ModelPart leftfin;

    public FootballFishModel(ModelPart root) {
        this.body = root.getChild("body");
        this.tailfin = body.getChild("tailfin");
        this.backfin = tailfin.getChild("backfin");
        this.backfin2 = tailfin.getChild("backfin2");
        this.lure = body.getChild("lure");
        this.jaw = body.getChild("jaw");
        this.teeth = jaw.getChild("teeth");
        this.rightfin = body.getChild("rightfin");
        this.leftfin = body.getChild("leftfin");
    }

    public static LayerDefinition create() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -12.0F, -7.0F, 11.0F, 12.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition tailfin = body.addOrReplaceChild("tailfin", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -3.75F, 0.0F, 0.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.25F, 7.0F));

        PartDefinition backfin = tailfin.addOrReplaceChild("backfin", CubeListBuilder.create().texOffs(4, 0).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.75F, 0.0F));

        PartDefinition backfin2 = tailfin.addOrReplaceChild("backfin2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.25F, 0.0F));

        PartDefinition lure = body.addOrReplaceChild("lure", CubeListBuilder.create().texOffs(0, 14).addBox(0.0F, -7.0F, -10.0F, 0.0F, 7.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, -1.0F));

        PartDefinition jaw = body.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(24, 26).addBox(-5.0F, 0.0F, -3.0F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -6.0F));

        PartDefinition teeth = jaw.addOrReplaceChild("teeth", CubeListBuilder.create().texOffs(22, 32).addBox(-5.0F, -3.0F, -2.0F, 10.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));

        PartDefinition rightfin = body.addOrReplaceChild("rightfin", CubeListBuilder.create().texOffs(6, 30).addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -5.5F, 2.0F, 0.0F, -0.3054F, 0.0F));

        PartDefinition leftfin = body.addOrReplaceChild("leftfin", CubeListBuilder.create().texOffs(0, 30).addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, -5.5F, 2.0F, 0.0F, 0.3054F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        float degree = 1.0f;
        float speed = 3.0f;
        this.body.zRot = Mth.cos(f * speed * 0.4F) * degree * 0.3F * f1;
        this.body.yRot = Mth.cos(f * speed * 0.4F) * degree * 0.5F * f1;
        this.tailfin.yRot = Mth.cos(f * speed * 0.4F) * degree * -0.5F * f1;
        this.backfin.zRot = Mth.cos(f * speed * 0.4F) * degree * -1.8F * f1;
        this.backfin2.zRot = Mth.cos(f * speed * 0.4F) * degree * 1.8F * f1;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.body).forEach((modelRenderer) -> {
            modelRenderer.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        });
    }
}