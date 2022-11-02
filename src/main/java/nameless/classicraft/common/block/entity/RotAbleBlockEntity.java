package nameless.classicraft.common.block.entity;

import lombok.Getter;
import nameless.classicraft.api.common.block.entity.TickAble;
import nameless.classicraft.common.rot.RotBlocks;
import nameless.classicraft.common.rot.RotHolder;
import nameless.classicraft.common.rot.RotManager;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

/**
 * @author DustW
 */
public class RotAbleBlockEntity extends BasicBlockEntity implements TickAble {

    @Getter
    RotHolder holder;

    public RotAbleBlockEntity(BlockEntityType<?> pType, BlockPos pWorldPosition, BlockState pBlockState) {
        super(pType, pWorldPosition, pBlockState);

        float v = getRotTick(pBlockState);
        holder = new RotHolder(v, v);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);

        pTag.putFloat("rot", holder.getCurrent());
    }

    @Override
    public List<ItemStack> drops() {
        return List.of();
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);

        if (pTag.contains("rot")) {
            holder.setCurrent(pTag.getFloat("rot"));
        }
    }

    int timer;

    protected void replaceOnRot(Level level) {
        level.removeBlock(getBlockPos(), false);
    }

    @Override
    public void tick() {
        super.tick();

        if (getLevel() != null && !getLevel().isClientSide && timer++ > 20) {
            timer = 0;
            holder.setCurrent(Math.max(holder.getCurrent() - RotManager.INSTANCE.getFinalSpeed(getLevel(), getBlockPos(), getBlockState()), 0));

            if (holder.getCurrent() == 0) {
                replaceOnRot(getLevel());
            }
        }
    }

    protected int getRotTick(BlockState state) {
        return RotBlocks.MAP.getOrDefault(state.getBlock(), 0);
    }
}
