package nameless.classicraft.rot;

import lombok.Getter;
import lombok.Setter;
import nameless.classicraft.capability.rot.EmptyRot;
import nameless.classicraft.init.ModCapabilities;
import nameless.classicraft.init.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * @author DustW
 */
public enum RotManager {
    INSTANCE;

    @Getter
    @Setter
    float baseSpeed = 1;

    Queue<Runnable> buffer = new ArrayDeque<>();

    List<Info> infos = new ArrayList<>();

    public void addInfo(Info info) {
        buffer.add(() -> infos.add(info));
    }

    public void removeInfo(Info info) {
        buffer.add(() -> infos.remove(info));
    }

    Map<BlockPos, Info> byPos = new HashMap<>();

    public void addInfoByPos(BlockPos pos, Info info) {
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

        SetAction IMPL = (inv, num, stack) -> {
            inv.extractItem(num, 64, false);
            inv.insertItem(num, stack, false);
        };
    }

    public record Info(List<IItemHandler> handlers,
                       @NotNull Supplier<World> level,
                       @NotNull Supplier<Vec3d> pos,
                       @Nullable ItemEntity entity,
                       @NotNull Supplier<BlockState> block,
                       SetAction action,
                       boolean needLevelNotNull,
                       BiFunction<ItemStack, Float, Float> onRotReduce) {
        static BiFunction<ItemStack, Float, Float> UNCHANGED =  (s, r) -> r;

        public static Info blockEntity(List<IItemHandler> handlers,
                                       Supplier<World> level,
                                       BlockPos pos,
                                       Supplier<BlockState> block) {
            return new Info(handlers, level, () -> new Vec3d(pos.getX(), pos.getY(), pos.getZ()),
                    null, block, SetAction.IMPL, true, UNCHANGED);
        }

        public static Info blockEntity(List<IItemHandler> handlers,
                                       Supplier<World> level,
                                       BlockPos pos,
                                       Supplier<BlockState> block,
                                       BiFunction<ItemStack, Float, Float> onRotReduce) {
            return new Info(handlers, level, () -> new Vec3d(pos.getX(), pos.getY(), pos.getZ()),
                    null, block, SetAction.IMPL, true, onRotReduce);
        }

        public static Info itemEntity(ItemEntity entity) {
            return new Info(List.of(new ItemStackHandler(NonNullList.of(ItemStack.EMPTY, entity.getStack()))),
                    entity::getWorld, entity::getPos, entity, () -> null, (h, n, s) -> entity.setStack(s), true, UNCHANGED);
        }

        public static Info playerInv(Inventory container, PlayerEntity player) {
            return new Info(List.of(new InvWrapper(container)), player::getWorld, player::getPos,
                    null, () -> null, SetAction.IMPL, true, UNCHANGED);
        }

        public static Info enderChest(Inventory container) {
            return new Info(List.of(new InvWrapper(container)), () -> null, () -> null, null,
                    Blocks.ENDER_CHEST::getDefaultState, SetAction.IMPL, false, UNCHANGED);
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
            Info info = infos.get(j);

            if (info.needLevelNotNull && info.level.get() == null) {
                continue;
            }

            float finalSpeed = getFinalSpeed(info.level.get(), info.pos.get(), info.block.get(), info.entity);

            for (IItemHandler handler : info.handlers) {
                for (int i = 0; i < handler.getSlots(); i++) {
                    ItemStack inSlot = handler.getStackInSlot(i);

                    int finalI = i;
                    inSlot.getCapability(ModCapabilities.ROT).ifPresent(rot -> {
                        if (rot instanceof EmptyRot || inSlot.isEmpty()) {
                            return;
                        }

                        Float finalValue = info.onRotReduce.apply(inSlot, finalSpeed);

                        if (finalValue > 0) {
                            float newValue = rot.getHolder().getCurrent() - finalValue;
                            rot.getHolder().setCurrent(Math.max(0, newValue));

                            if (rot.getHolder().getCurrent() <= 0) {
                                Item remain = ModItems.ROTTEN_FOOD.get();

                                String o = inSlot.getItem().getTranslationKey();

                                if (bowl.contains(o)) {
                                    remain = Items.BOWL;
                                }
                                else if (bottle.contains(o)) {
                                    remain = Items.GLASS_BOTTLE;
                                }

                                info.action.set(handler, finalI, new ItemStack(remain, inSlot.getCount()));
                            }
                        }

                        rot.setFinalSpeed(finalSpeed);
                    });
                }
            }
        }

        while (buffer.peek() != null) {
            buffer.poll().run();
        }
    }

    /** for BlockEntity */
    public float getFinalSpeed(World level, BlockPos pos, BlockState state) {
        return getFinalSpeed(level, new Vec3d(pos.getX(), pos.getY(), pos.getZ()), state, null);
    }

    public float getFinalSpeed(World level, Vec3d pos, BlockState state, ItemEntity entity) {
        return baseSpeed() *
                dimCoefficient(level) *
                biomeCoefficient(level, pos) *
                containerCoefficient(state) *
                drop(entity);
    }

    float baseSpeed() {
        return baseSpeed;
    }

    float dimCoefficient(World level) {
        if (level == null) {
            return 1;
        }

        if (level.dimension() == World.NETHER) {
            return 1.25F;
        }
        else if (level.dimension() == World.END) {
            return .75F;
        }

        return 1;
    }

    float biomeCoefficient(World level, Vec3d pos) {
        if (level == null || pos == null) {
            return 1;
        }

        if (level.dimension() == World.END) {
            return .75F;
        }
        else if (level.dimension() == World.NETHER) {
            return 1.25F;
        }

        Biome biome = level.getBiome(new BlockPos(pos)).value();

        if (biome.getTemperature() < .31) {
            return .75F;
        }

        if (biome.getTemperature() > .99) {
            return 1.25F;
        }

        return 1;
    }

    float containerCoefficient(BlockState container) {
        if (container == null) {
            return 1;
        }

        if (container.isOf(Blocks.ENDER_CHEST)) {
            return 1.25F;
        }
        else if (container.isOf(Blocks.SHULKER_BOX)) {
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
