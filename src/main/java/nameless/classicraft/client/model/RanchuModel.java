package nameless.classicraft.client.model;

import com.google.common.collect.ImmutableList;
import nameless.classicraft.entity.RanchuEntity;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;

@OnlyIn(Dist.CLIENT)
public class RanchuModel<T extends RanchuEntity> extends AgeableListModel<T> {
    public ModelPart ranchu;
    public ModelPart body;
    public ModelPart tailbase;
    public ModelPart head;
    public ModelPart backfinright;
    public ModelPart backfinleft;
    public ModelPart finright;
    public ModelPart finleft;

    public RanchuModel(ModelPart root) {
        this.ranchu = root.getChild("ranchu");
        this.body = this.ranchu.getChild("body");
        this.tailbase = this.body.getChild("tailbase");
        this.head = this.body.getChild("head");
        this.backfinright = this.tailbase.getChild("backfinright");
        this.backfinleft = this.tailbase.getChild("backfinleft");
        this.finright = this.body.getChild("finright");
        this.finleft = this.body.getChild("finleft");
    }

    public static LayerDefinition create() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition ranchu = partdefinition.addOrReplaceChild("ranchu", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = ranchu.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -6.0F, -2.0F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 11).addBox(-2.5F, -2.0F, -3.0F, 5.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 18).addBox(-2.0F, 2.0F, -2.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -2.0F));

        PartDefinition finleft = body.addOrReplaceChild("finleft", CubeListBuilder.create(), PartPose.offset(-2.5F, 0.0F, -0.5F));

        PartDefinition cube_r1 = finleft.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(16, 10).addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, -1.5708F));

        PartDefinition finright = body.addOrReplaceChild("finright", CubeListBuilder.create(), PartPose.offset(2.5F, 0.0F, -0.5F));

        PartDefinition cube_r2 = finright.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(20, 10).addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, 1.5708F, 0.0F, -1.5708F));

        PartDefinition tailbase = body.addOrReplaceChild("tailbase", CubeListBuilder.create().texOffs(20, 0).addBox(-2.0F, -5.5F, 3.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition backfinright = tailbase.addOrReplaceChild("backfinright", CubeListBuilder.create().texOffs(3, 13).addBox(0.0F, -5.0F, -0.5F, 0.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -3.0F, 6.0F));

        PartDefinition backfinleft = tailbase.addOrReplaceChild("backfinleft", CubeListBuilder.create().texOffs(3, 13).addBox(-1.0F, -5.0F, -0.5F, 0.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -3.0F, 6.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.5f;
        float degree = 0.8f;
        if (entityIn.isInWater()) {
            this.body.xRot = headPitch * ((float) Math.PI / 180F);
            this.body.yRot = netHeadYaw * ((float) Math.PI / 180F);
            this.tailbase.yRot = Mth.cos(-1.0F + limbSwing * speed * 0.5F) * degree * 1.5F * limbSwingAmount;
            this.body.yRot += Mth.cos(limbSwing * speed * 0.5F) * degree * 0.5F * limbSwingAmount;
            this.finright.xRot = Mth.cos(-2.0F + limbSwing * speed * 0.5F) * degree * 1.5F * limbSwingAmount;
            this.finleft.xRot = Mth.cos(-2.0F + limbSwing * speed * 0.5F) * degree * 1.5F * limbSwingAmount;
            this.backfinright.zRot = Mth.cos(1.0F + limbSwing * speed * 0.5F) * degree * 2.0F * limbSwingAmount;
            this.backfinleft.zRot = Mth.cos(1.0F + limbSwing * speed * 0.5F) * degree * -2.0F * limbSwingAmount;
        }
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return Collections.emptyList();
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.ranchu);
    }


    public void setRotateAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
