package nameless.classicraft.block.entity;

import nameless.classicraft.init.ModBlockEntities;
import nameless.classicraft.menu.StoneMortarBlockMenu;
import nameless.classicraft.recipe.StoneMortarRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Optional;

public class StoneMortarBlockEntity extends BlockEntity implements MenuProvider {

    protected ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;

    public ItemStackHandler itemHandler = createHandler();

    private LazyOptional<IItemHandler> handler = LazyOptional.empty();

    public StoneMortarBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.STONE_MORTAR_BLOCK_ENTITY.get(), blockPos, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> StoneMortarBlockEntity.this.progress;
                    case 1 -> StoneMortarBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> StoneMortarBlockEntity.this.progress = value;
                    case 1 -> StoneMortarBlockEntity.this.maxProgress = value;
                    default -> {
                    }
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(2) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if (slot == 1) {
                    return stack.getItem() instanceof Item;
                }
                return true;
            }

            @Override
            public int getSlotLimit(int slot) {
                return 64;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!isItemValid(slot, stack)) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        handler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        handler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("StoneMortar", itemHandler.serializeNBT());
        tag.putInt("StoneMortarProgress", this.progress);
        super.saveAdditional(tag);
    }

    @Override
    public void load(@Nonnull CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("StoneMortar"));
        progress = nbt.getInt("StoneMortarProgress");
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("container.classicraft.stone_mortar_block");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new StoneMortarBlockMenu(id, inventory, this, this.data);
    }

    public void drop() {
        SimpleContainer container = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            container.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(level, worldPosition, container);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, StoneMortarBlockEntity entity) {
        if (level != null && !level.isClientSide()) {
            ItemStackHandler itemHandler = entity.itemHandler;
            SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());
            for (int i = 0; i < itemHandler.getSlots(); i++) {
                inv.setItem(i, itemHandler.getStackInSlot(i));
            }
            run(level, inv, entity, pos, state);
        }
    }

    private static void run(Level level, SimpleContainer inv, StoneMortarBlockEntity entity, BlockPos pos,
                            BlockState state) {
        Optional<StoneMortarRecipe> recipe =
                level.getRecipeManager().getRecipeFor(StoneMortarRecipe.StoneMortarRecipeType.STONE_MORTAR, inv,
                        level);
        if (recipe.isPresent() && canInsertItem(inv, recipe.get().getResultItem())) {
            entity.progress++;
            setChanged(level, pos, state);
            if (entity.progress >= entity.maxProgress) {
                craft(recipe.get(), entity, inv);
            }
        } else {
            entity.resetProgress();
        }
    }

    private static void craft(StoneMortarRecipe recipe, StoneMortarBlockEntity entity, SimpleContainer inv) {
        ItemStackHandler itemHandler = entity.itemHandler;
        ItemStack resultItem = recipe.getResultItem();
        if (canInsertItem(inv, resultItem)) {
            itemHandler.extractItem(0, 1, false);
            itemHandler.insertItem(1, resultItem, false);
            entity.resetProgress();
        }
    }

    private static boolean canInsertItem(SimpleContainer inventory, ItemStack stack) {
        return inventory.getItem(1).getCount() < 64 && (
                inventory.getItem(1).getItem() == stack.getItem() || inventory.getItem(1).isEmpty());
    }

    private void resetProgress() {
        this.progress = 0;
    }
}
