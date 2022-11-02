package nameless.classicraft.mixin;

import nameless.classicraft.api.common.block.RotBlock;
import nameless.classicraft.common.block.entity.RotAbleBlockEntity;
import nameless.classicraft.common.capability.ModCapabilities;
import nameless.classicraft.common.rot.RotManager;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DustW
 */
@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class MixinBlockStateBase {
    @Shadow public abstract Block getBlock();

    @Inject(method = "onRemove", at = @At("RETURN"))
    private void onRemoveCC(Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving, CallbackInfo ci) {
        RotManager.INSTANCE.removeInfoByPos(pPos);

        if (getBlock() == Blocks.POTATOES) {
            Containers.dropItemStack(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(),
                    new ItemStack(Items.POISONOUS_POTATO, 2 + pLevel.random.nextInt(3)));
            return;
        }

        if (getBlock() instanceof RotBlock rb && rb.needDropSelf()) {
            ItemStack stack = new ItemStack(getBlock().asItem());

            RotAbleBlockEntity blockEntity = rb.getBlockEntity(pLevel, pPos);

            if (blockEntity != null) {
                stack.getCapability(ModCapabilities.ROT).ifPresent(rot ->
                        rot.getHolder().setCurrent(blockEntity.getHolder().getCurrent()));
            }

            Containers.dropItemStack(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), stack);
        }

    }

    @Inject(method = "getDrops", at = @At("RETURN"), cancellable = true)
    private void getDropsCC(LootContext.Builder pBuilder, CallbackInfoReturnable<List<ItemStack>> cir) {
        if (getBlock() == Blocks.POTATOES) {
            cir.setReturnValue(new ArrayList<>());
        }

        List<ItemStack> list = cir.getReturnValue();

        for (int i = 0; i < list.size(); i++) {
            ItemStack stack = list.get(i);

            if (stack.getItem() == getBlock().asItem() && stack.getCapability(ModCapabilities.ROT).isPresent()) {
                list.remove(i);
                break;
            }
        }

        cir.setReturnValue(list);
    }
}
