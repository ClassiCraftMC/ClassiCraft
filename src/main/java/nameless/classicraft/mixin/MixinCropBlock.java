package nameless.classicraft.mixin;

import nameless.classicraft.api.common.block.RotBlock;
import nameless.classicraft.api.common.block.entity.TickAble;
import nameless.classicraft.common.block.entity.ModBlockEntities;
import nameless.classicraft.common.block.entity.attach.crop.CarrotBlockEntity;
import nameless.classicraft.common.block.entity.attach.crop.PotatoBlockEntity;
import nameless.classicraft.common.block.entity.attach.crop.WheatBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author DustW
 */
@Mixin(CropBlock.class)
public class MixinCropBlock implements RotBlock, EntityBlock {

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        var self = (Block) (Object) this;

        if (self == Blocks.WHEAT) {
            return new WheatBlockEntity(pPos, pState);
        }
        else if (self == Blocks.CARROTS) {
            return new CarrotBlockEntity(pPos, pState);
        }
        else if (self == Blocks.POTATOES) {
            return new PotatoBlockEntity(pPos, pState);
        }

        return null;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pBlockEntityType == ModBlockEntities.WHEAT.get() ||
                pBlockEntityType == ModBlockEntities.CARROT.get() ||
                pBlockEntityType == ModBlockEntities.POTATO.get()
                ? TickAble.ticker() : null;
    }
}
