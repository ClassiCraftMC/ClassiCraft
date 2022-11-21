package nameless.classicraft.block.entity;

import nameless.classicraft.api.block.entity.ItemStackContainer;
import nameless.classicraft.api.rot.RotReduceListener;
import nameless.classicraft.block.container.FridgeBlock;
import nameless.classicraft.init.ModBlockEntities;
import nameless.classicraft.client.menu.FridgeMenu;
import nameless.classicraft.util.ContainerUtils;
import nameless.classicraft.util.sync.SyncDataManager;
import nameless.classicraft.util.sync.object.ItemStackHandlerSyncData;
import nameless.classicraft.util.sync.primitive.FloatSyncData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author DustW
 */
public class FridgeBlockEntity extends BasicMenuBlockEntity implements ItemStackContainer, RotReduceListener {
    public final ItemStackHandlerSyncData items = new ItemStackHandlerSyncData("items", 27, true);
    public final FloatSyncData energy = new FloatSyncData("energy", 0F, true);

    public FridgeBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.FRIDGE.get(), pWorldPosition, pBlockState);
    }

    @Override
    public void tick() {
        super.tick();

        if (level != null) {
            boolean stateHasEnergy = getBlockState().getValue(FridgeBlock.HAS_ENERGY);

            if (stateHasEnergy && energy.get() < 0) {
                level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(FridgeBlock.HAS_ENERGY, false));
            } else if (!stateHasEnergy && energy.get() > 0) {
                level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(FridgeBlock.HAS_ENERGY, true));
            }
        }
    }

    @Override
    protected void registerSyncData(SyncDataManager manager) {
        manager.add(energy);
        manager.add(items);
    }

    @Override
    public List<ItemStack> drops() {
        return ContainerUtils.getItems(items.get());
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new FridgeMenu(pContainerId, pPlayerInventory, this);
    }

    @Override
    public ItemStackHandler getHandler() {
        return items.get();
    }

    @Override
    public float onRotReduce(ItemStack rotItem, float originRotValue) {
        if (FridgeBlock.isLit(getBlockState()) && energy.get() >= 1) {
            energy.set(energy.get() - 1);
            return 0;
        }
        else {
            return originRotValue;
        }
    }

    @NotNull
    @Override
    @SuppressWarnings("removal")
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, LazyOptional.of(items::get));
    }
}
