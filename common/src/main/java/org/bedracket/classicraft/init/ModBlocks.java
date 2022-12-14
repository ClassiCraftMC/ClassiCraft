package org.bedracket.classicraft.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import org.bedracket.classicraft.ClassiCraftMod;
import org.bedracket.classicraft.block.RealSoulTorchBlock;
import org.bedracket.classicraft.block.RealSoulWallTorchBlock;
import org.bedracket.classicraft.block.RealTorchBlock;
import org.bedracket.classicraft.block.RealWallTorchBlock;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ClassiCraftMod.MOD_ID, Registries.BLOCK);

    public static final RegistrySupplier<Block> ROSE =
            registerDefault("rose", () -> new FlowerBlock(MobEffects.DIG_SPEED, 2
                    , BlockBehaviour.Properties.copy(Blocks.DANDELION)));

    public static final RegistrySupplier<Block> TALLOW_BLOCK =
            registerDefault("tallow_block", () ->
                    new SlimeBlock(BlockBehaviour.Properties
                            .of(Material.CLAY, MaterialColor.GRASS)
                            .friction(0.8F)
                            .sound(SoundType.SLIME_BLOCK).noOcclusion()));

    public static final RegistrySupplier<Block> MOSSY_BRICKS =
            registerDefault("mossy_bricks", () ->
                    new Block(BlockBehaviour.Properties
                            .of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(1.5F, 6.0F)));

    public static final RegistrySupplier<Block> MOSSY_BRICKS_STAIRS =
            registerDefault("mossy_bricks_stairs", () ->
                    new StairBlock(MOSSY_BRICKS.get().defaultBlockState(),
                            BlockBehaviour.Properties
                                    .of(Material.STONE)
                                    .requiresCorrectToolForDrops()
                                    .strength(1.5F, 6.0F)));

    public static final RegistrySupplier<Block> MOSSY_BRICKS_WALL =
            registerDefault("mossy_bricks_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(MOSSY_BRICKS.get())));

    public static final RegistrySupplier<Block> MOSSY_BRICKS_SLAB =
            registerDefault("mossy_bricks_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties
                            .of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(1.5F, 6.0F)));

    public static final RegistrySupplier<Block> CRACKED_BRICKS =
            registerDefault("cracked_bricks", () ->
                    new Block(BlockBehaviour.Properties
                            .of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(1.5F, 6.0F)));

    public static final RegistrySupplier<Block> CRACKED_BRICKS_STAIRS =
            registerDefault("cracked_bricks_stairs", () ->
                    new StairBlock(CRACKED_BRICKS.get().defaultBlockState(),
                            BlockBehaviour.Properties
                                    .of(Material.STONE)
                                    .requiresCorrectToolForDrops()
                                    .strength(1.5F, 6.0F)));

    public static final RegistrySupplier<Block> CRACKED_BRICKS_WALL =
            registerDefault("cracked_bricks_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(CRACKED_BRICKS.get())));

    public static final RegistrySupplier<Block> CRACKED_BRICKS_SLAB =
            registerDefault("cracked_bricks_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties
                            .of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(1.5F, 6.0F)));

    public static final RegistrySupplier<Block> SULFUR_ORE =
            registerDefault("sulfur_ore", () ->
                    new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 3.0F),
                            UniformInt.of(0, 2)));

    public static final RegistrySupplier<Block> CHARCOAL_BLOCK =
            registerDefault("charcoal_block", () ->
                    new DropExperienceBlock(BlockBehaviour
                            .Properties.of(Material.STONE,
                                    MaterialColor.COLOR_BLACK)
                            .requiresCorrectToolForDrops()
                            .strength(5.0F, 6.0F)));

    public static final RegistrySupplier<Block> REAL_TORCH =
            register("real_torch", RealTorchBlock::new);
    public static final RegistrySupplier<Block> REAL_SOUL_TORCH =
            register("real_soul_torch", RealSoulTorchBlock::new);

    public static final RegistrySupplier<Block> REAL_WALL_TORCH =
            register("real_wall_torch", RealWallTorchBlock::new);
    public static final RegistrySupplier<Block> REAL_SOUL_WALL_TORCH =
            register("real_soul_wall_torch", RealSoulWallTorchBlock::new);

    private static <T extends Block> RegistrySupplier<T> register(String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory) {
        return register(ModBlocks.BLOCKS, ModItems.ITEMS, name, blockSupplier, blockItemFactory);
    }

    @SuppressWarnings("UnstableApiUsage")
    private static <T extends Block> RegistrySupplier<T> registerDefault(String name, Supplier<T> blockSupplier) {
        return register(name, blockSupplier, block -> new BlockItem(block, new Item.Properties().arch$tab(ModCreativeModeTabs.COMMON)));
    }

    public static <T extends Block> RegistrySupplier<T> register(DeferredRegister<Block> blocks, DeferredRegister<Item> items, String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory) {
        final String actualName = name.toLowerCase(Locale.ROOT);
        final RegistrySupplier<T> block = blocks.register(actualName, blockSupplier);
        if (blockItemFactory != null) {
            items.register(actualName, () -> blockItemFactory.apply(block.get()));
        }
        return block;
    }

    private static <T extends Block> RegistrySupplier<T> register(String name, Supplier<T> block) {
        return BLOCKS.register(name.toLowerCase(Locale.ROOT), block);
    }
}
