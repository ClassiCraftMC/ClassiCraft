package nameless.classicraft.common.damp;

import lombok.Getter;
import lombok.Setter;
import nameless.classicraft.common.capability.ModCapabilities;
import nameless.classicraft.common.capability.damp.EmptyDamp;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public enum DampManager {
    INSTANCE;

    @Getter
    @Setter
    float baseSpeed = 1;

    Queue<Runnable> buffer = new ArrayDeque<>();

    List<DampManager.Info> infos = new ArrayList<>();

    public void addInfo(DampManager.Info info) {
        buffer.add(() -> infos.add(info));
    }

    public void removeInfo(DampManager.Info info) {
        buffer.add(() -> infos.remove(info));
    }

    Map<BlockPos, DampManager.Info> byPos = new HashMap<>();

    public void addInfoByPos(BlockPos pos, DampManager.Info info) {
        buffer.add(() -> {
            byPos.put(pos, info);
            infos.add(info);
        });
    }

    public void removeInfoByPos(BlockPos pos) {
        buffer.add(() -> infos.remove(byPos.remove(pos)));
    }

    @FunctionalInterface
    public interface SetAction {
        void set(IItemHandler handler, int slot, ItemStack stack);

        DampManager.SetAction IMPL = (inv, num, stack) -> {
            inv.extractItem(num, 64, false);
            inv.insertItem(num, stack, false);
        };
    }

    public record Info(List<IItemHandler> handlers,
                       @NotNull Supplier<Level> level,
                       @NotNull Supplier<Vec3> pos,
                       @Nullable ItemEntity entity,
                       @NotNull Supplier<BlockState> block,
                       DampManager.SetAction action,
                       boolean needLevelNotNull,
                       BiFunction<ItemStack, Float, Float> onDampReduce) {
        static BiFunction<ItemStack, Float, Float> UNCHANGED =  (s, r) -> r;

        public static DampManager.Info blockEntity(List<IItemHandler> handlers,
                                                  Supplier<Level> level,
                                                  BlockPos pos,
                                                  Supplier<BlockState> block) {
            return new DampManager.Info(handlers, level, () -> new Vec3(pos.getX(), pos.getY(), pos.getZ()),
                    null, block, DampManager.SetAction.IMPL, true, UNCHANGED);
        }

        public static DampManager.Info blockEntity(List<IItemHandler> handlers,
                                                  Supplier<Level> level,
                                                  BlockPos pos,
                                                  Supplier<BlockState> block,
                                                  BiFunction<ItemStack, Float, Float> onDampReduce) {
            return new DampManager.Info(handlers, level, () -> new Vec3(pos.getX(), pos.getY(), pos.getZ()),
                    null, block, DampManager.SetAction.IMPL, true, onDampReduce);
        }

        public static DampManager.Info itemEntity(ItemEntity entity) {
            return new DampManager.Info(List.of(new ItemStackHandler(NonNullList.of(ItemStack.EMPTY, entity.getItem()))),
                    entity::getLevel, entity::position, entity, () -> null, (h, n, s) -> entity.setItem(s), true, UNCHANGED);
        }

        public static DampManager.Info playerInv(Container container, Player player) {
            return new DampManager.Info(List.of(new InvWrapper(container)), player::getLevel, player::position,
                    null, () -> null, DampManager.SetAction.IMPL, true, UNCHANGED);
        }

        public static DampManager.Info enderChest(Container container) {
            return new DampManager.Info(List.of(new InvWrapper(container)), () -> null, () -> null, null,
                    Blocks.ENDER_CHEST::defaultBlockState, DampManager.SetAction.IMPL, false, UNCHANGED);
        }
    }

    List<String> bowl = List.of(
            "minecraft:rabbit_stew",
            "minecraft:suspicious_stew",
            "minecraft:mushroom_stew",
            "minecraft:beetroot_soup",
            "classicraft:nether_mushroom_stew"
    );

    List<String> bottle = List.of(
            "minecraft:honey_bottle"
    );

    public void second() {
        for (int j = 0; j < infos.size(); j++) {
            DampManager.Info info = infos.get(j);

            if (info.needLevelNotNull && info.level.get() == null) {
                continue;
            }

            float finalSpeed = getFinalSpeed(info.level.get(), info.pos.get(), info.block.get(), info.entity);

            for (IItemHandler handler : info.handlers) {
                for (int i = 0; i < handler.getSlots(); i++) {
                    ItemStack inSlot = handler.getStackInSlot(i);

                    int finalI = i;
                    inSlot.getCapability(ModCapabilities.DAMP).ifPresent(damp -> {
                        if (damp instanceof EmptyDamp || inSlot.isEmpty()) {
                            return;
                        }

                        Float finalValue = info.onDampReduce.apply(inSlot, finalSpeed);

                        if (finalValue > 0) {
                            float newValue = damp.getHolder().getCurrent() - finalValue;
                            damp.getHolder().setCurrent(Math.max(0, newValue));
                        }

                        damp.setFinalSpeed(finalSpeed);
                    });
                }
            }
        }

        while (buffer.peek() != null) {
            buffer.poll().run();
        }
    }

    /** for BlockEntity */
    public float getFinalSpeed(Level level, BlockPos pos, BlockState state) {
        return getFinalSpeed(level, new Vec3(pos.getX(), pos.getY(), pos.getZ()), state, null);
    }

    public float getFinalSpeed(Level level, Vec3 pos, BlockState state, ItemEntity entity) {
        return baseSpeed() *
                dimCoefficient(level) *
                biomeCoefficient(level, pos) *
                containerCoefficient(state) *
                drop(entity);
    }

    float baseSpeed() {
        return baseSpeed;
    }

    float dimCoefficient(Level level) {
        if (level == null) {
            return 1;
        }

        if (level.dimension() == Level.NETHER) {
            return 1.25F;
        }
        else if (level.dimension() == Level.END) {
            return .75F;
        }

        return 1;
    }

    float biomeCoefficient(Level level, Vec3 pos) {
        if (level == null || pos == null) {
            return 1;
        }

        if (level.dimension() == Level.END) {
            return .75F;
        }
        else if (level.dimension() == Level.NETHER) {
            return 1.25F;
        }

        Biome biome = level.getBiome(new BlockPos(pos)).value();

        if (biome.getBaseTemperature() < .31) {
            return .75F;
        }

        if (biome.getBaseTemperature() > .99) {
            return 1.25F;
        }

        return 1;
    }

    float containerCoefficient(BlockState container) {
        if (container == null) {
            return 1;
        }

        if (container.is(Blocks.ENDER_CHEST)) {
            return 1.25F;
        }
        else if (container.is(Blocks.SHULKER_BOX)) {
            return .75F;
        }

        return 1;
    }

    float drop(ItemEntity entity) {
        if (entity != null) {
            return 1.5F;
        }

        return 1;
    }
}
