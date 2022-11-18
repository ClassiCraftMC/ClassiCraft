package nameless.classicraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import nameless.classicraft.block.entity.RealisticCampfireBlockEntity;
import nameless.classicraft.block.realistic.RealisticCampFireBlock;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RealisticCampfireRenderer implements BlockEntityRenderer<RealisticCampfireBlockEntity> {

    private static final float SIZE = 0.375F;
    private final ItemRenderer itemRenderer;

    public RealisticCampfireRenderer(BlockEntityRendererProvider.Context pContext) {
        this.itemRenderer = pContext.getItemRenderer();
    }

    public void render(RealisticCampfireBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        Direction direction = pBlockEntity.getBlockState().getValue(RealisticCampFireBlock.FACING);
        NonNullList<ItemStack> nonnulllist = pBlockEntity.getItems();
        int i = (int)pBlockEntity.getBlockPos().asLong();

        for(int j = 0; j < nonnulllist.size(); ++j) {
            ItemStack itemstack = nonnulllist.get(j);
            if (itemstack != ItemStack.EMPTY) {
                pPoseStack.pushPose();
                pPoseStack.translate(0.5D, 0.44921875D, 0.5D);
                Direction direction1 = Direction.from2DDataValue((j + direction.get2DDataValue()) % 4);
                float f = -direction1.toYRot();
                pPoseStack.mulPose(Vector3f.YP.rotationDegrees(f));
                pPoseStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
                pPoseStack.translate(-0.3125D, -0.3125D, 0.0D);
                pPoseStack.scale(0.375F, 0.375F, 0.375F);
                this.itemRenderer.renderStatic(itemstack, ItemTransforms.TransformType.FIXED, pPackedLight, pPackedOverlay, pPoseStack, pBufferSource, i + j);
                pPoseStack.popPose();
            }
        }

    }
}