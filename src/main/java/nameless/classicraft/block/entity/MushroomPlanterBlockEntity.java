package nameless.classicraft.block.entity;

import nameless.classicraft.block.MushroomPlanterBlock;
import nameless.classicraft.init.ModBlockEntities;
import nameless.classicraft.util.sync.SyncDataManager;
import nameless.classicraft.util.sync.primitive.IntSyncData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import java.util.List;

/**
 * @author DustW
 */
public class MushroomPlanterBlockEntity extends RotAbleCropBlockEntity {
    public MushroomPlanterBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.MUSHROOM_PLANTER.get(), pWorldPosition, pBlockState);
    }

    @Override
    protected IntegerProperty age() {
        return MushroomPlanterBlock.GROW_STATE;
    }

    @Override
    protected boolean isMax() {
        return getBlockState().getValue(MushroomPlanterBlock.GROW_STATE) >= 3;
    }

    IntSyncData grow = new IntSyncData("grow", 0, true);
    IntSyncData maxGrow = new IntSyncData("max_grow", 0, true);

    @Override
    protected void registerSyncData(SyncDataManager manager) {
        manager.add(grow);
        manager.add(maxGrow);
    }

    @Override
    public void tick() {
        super.tick();

        BlockState state = getBlockState();

        if (maxGrow.get() != 0 && MushroomPlanterBlock.hasMushroom(state)) {
            grow.plus(1);

            if (grow.get() >= maxGrow.get() && !isMax()) {
                level.setBlockAndUpdate(getBlockPos(),
                        state.setValue(MushroomPlanterBlock.GROW_STATE, state.getValue(MushroomPlanterBlock.GROW_STATE) + 1));
            }
        }
    }

    public void refresh(BlockState state) {
        if (state.getValue(MushroomPlanterBlock.DIRT) == MushroomPlanterBlock.Dirt.MYCELIUM.ordinal()) {
            maxGrow.set(96000);
        } else {
            maxGrow.set(168000);
        }

        grow.set(0);
        level.setBlockAndUpdate(getBlockPos(), state.setValue(MushroomPlanterBlock.GROW_STATE, 1));
    }

    @Override
    public List<ItemStack> drops() {
        return List.of();
    }

    @Override
    protected int getRotTick(BlockState state) {
        return 168000;
    }

    @Override
    public void replaceOnRot(Level level) {
        super.replaceOnRot(level);
    }
}
