package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.block.*;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS,
                            ClassiCraftMod.MOD_ID);

    public static final RegistryObject<Block> REAL_TORCH =
            register("real_torch", RealTorchBlock::new);
    public static final RegistryObject<Block> REAL_SOUL_TORCH =
            register("real_soul_torch", RealSoulTorchBlock::new);

    public static final RegistryObject<Block> REAL_WALL_TORCH =
            register("real_wall_torch", RealWallTorchBlock::new);
    public static final RegistryObject<Block> REAL_SOUL_WALL_TORCH =
            register("real_soul_wall_torch", RealSoulWallTorchBlock::new);

    public static final RegistryObject<Block> CHARCOAL_BLOCK =
            registerDefault("charcoal_block", CharCoalBlock::new);

    public static final RegistryObject<Block> CACTUS_BALL =
            registerDefault("cactus_ball", CactusBallBlock::new);

    public static final RegistryObject<Block> QUICKSAND =
            registerDefault("quicksand",
                    () -> new QuickSandBlock(14406560));

    public static final RegistryObject<Block> RED_QUICKSAND =
            registerDefault("red_quicksand",
                    () -> new QuickSandBlock(11098145));

    public static final RegistryObject<Block> QUARTZ_QUICKSAND =
            registerDefault("quartz_quicksand",
                    () -> new QuickSandBlock(14406560));

    public static final RegistryObject<Block> SOUL_QUICKSAND =
            registerDefault("soul_quicksand",
                    () -> new QuickSandBlock(11098145));

    public static final RegistryObject<Block> ROSE =
            registerDefault("rose", () -> new FlowerBlock(MobEffects.DIG_SPEED, 2
            , BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistryObject<Block> POTTED_ROSE =
            register("potted_rose", () -> new FlowerPotBlock(ROSE.get(),
                    BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));

    public static final RegistryObject<Block> TALLOW_BLOCK =
            registerDefault("tallow_block", TallowBlock::new);

    public static final RegistryObject<Block> MOSSY_BRICKS =
            registerDefault("mossy_bricks", StoneBricksBlock::new);

    public static final RegistryObject<Block> MOSSY_BRICKS_STAIRS =
            registerDefault("mossy_bricks_stairs", () ->
                    new StairBlock(MOSSY_BRICKS.get().defaultBlockState(),
                            BlockBehaviour.Properties
                            .of(Material.STONE).requiresCorrectToolForDrops()
                            .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> MOSSY_BRICKS_WALL =
            registerDefault("mossy_bricks_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(MOSSY_BRICKS.get())
                            .requiresCorrectToolForDrops()
                            .noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape()));

    public static final RegistryObject<Block> POLISHED_GRANITE_WALL =
            registerDefault("polished_granite_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(Blocks.POLISHED_GRANITE)
                            .requiresCorrectToolForDrops()
                            .noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape()));

    public static final RegistryObject<Block> POLISHED_DIORITE_WALL =
            registerDefault("polished_diorite_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(Blocks.POLISHED_DIORITE)
                            .requiresCorrectToolForDrops()
                            .noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape()));

    public static final RegistryObject<Block> POLISHED_ANDESITE_WALL =
            registerDefault("polished_andesite_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(Blocks.POLISHED_ANDESITE)
                            .requiresCorrectToolForDrops()
                            .noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape()));

    public static final RegistryObject<Block> MOSSY_BRICKS_SLAB =
            registerDefault("mossy_bricks_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties
                            .of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> CRACKED_BRICKS =
            registerDefault("cracked_bricks", StoneBricksBlock::new);

    public static final RegistryObject<Block> CRACKED_BRICKS_STAIRS =
            registerDefault("cracked_bricks_stairs", () ->
                    new StairBlock(CRACKED_BRICKS.get().defaultBlockState(),
                            BlockBehaviour.Properties
                            .of(Material.STONE).requiresCorrectToolForDrops()
                            .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> CRACKED_BRICKS_WALL =
            registerDefault("cracked_bricks_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(CRACKED_BRICKS.get())
                            .requiresCorrectToolForDrops()
                            .noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape()));

    public static final RegistryObject<Block> CRACKED_BRICKS_SLAB =
            registerDefault("cracked_bricks_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties
                            .of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> ANDESITE_PEBBLE =
            registerDefault("andesite_pebble", StonePebbleBlock::new);

    public static final RegistryObject<Block> COBBLESTONE_PEBBLE =
            registerDefault("cobblestone_pebble", StonePebbleBlock::new);

    public static final RegistryObject<Block> DIORITE_PEBBLE =
            registerDefault("diorite_pebble", StonePebbleBlock::new);

    public static final RegistryObject<Block> GRANITE_PEBBLE =
            registerDefault("granite_pebble", StonePebbleBlock::new);

    public static final RegistryObject<Block> RED_SANDSTONE_PEBBLE =
            registerDefault("red_sandstone_pebble", StonePebbleBlock::new);

    public static final RegistryObject<Block> SANDSTONE_PEBBLE =
            registerDefault("sandstone_pebble", StonePebbleBlock::new);

    public static final RegistryObject<Block> DEEPSLATE_PEBBLE =
            registerDefault("deepslate_pebble", StonePebbleBlock::new);

    public static final RegistryObject<Block> QUARTZ_SANDSTONE_PEBBLE =
            registerDefault("quartz_sandstone_pebble", StonePebbleBlock::new);

    public static final RegistryObject<Block> SOUL_SANDSTONE_PEBBLE =
            registerDefault("soul_sandstone_pebble", StonePebbleBlock::new);
    public static final RegistryObject<Block> NETHERRACK_PEBBLE =
            registerDefault("netherrack_pebble", StonePebbleBlock::new);
    public static final RegistryObject<Block> END_STONE_PEBBLE =
            registerDefault("end_stone_pebble", StonePebbleBlock::new);
    public static final RegistryObject<Block> BASALT_PEBBLE =
            registerDefault("basalt_pebble", StonePebbleBlock::new);
    public static final RegistryObject<Block> BLACKSTONE_PEBBLE =
            registerDefault("blackstone_pebble", StonePebbleBlock::new);

    public static final RegistryObject<Block> FLINT =
            register("flint", () -> new VanillaPickBlock(Items.FLINT));
    public static final RegistryObject<Block> PRISMARINE =
            register("prismarine", () -> new VanillaPickBlock(Items.PRISMARINE_SHARD));

    public static final RegistryObject<Block> TWIGS =
            register("twigs", TwigsBlock::new);

    public static final RegistryObject<Block> FLINT_BLOCK =
            registerDefault("flint_block", () ->
                    new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE)
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 3.0F)));

    public static final RegistryObject<Block> SULFUR_ORE =
            registerDefault("sulfur_ore", () ->
                    new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                            .strength(3.0F, 3.0F).requiresCorrectToolForDrops(),
                            UniformInt.of(0, 2)));

    public static final RegistryObject<Block> STONE_WALL =
            registerDefault("stone_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                            .requiresCorrectToolForDrops()
                            .noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape()));

    public static final RegistryObject<Block> SMOOTH_STONE_STAIRS =
            registerDefault("smooth_stone_stairs", () ->
                    new StairBlock(Blocks.SMOOTH_STONE.defaultBlockState(),
                            BlockBehaviour.Properties
                                    .of(Material.STONE)
                                    .requiresCorrectToolForDrops()
                                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> CRACKED_STONE_BRICKS_STAIRS =
            registerDefault("cracked_stone_bricks_stairs", () ->
                    new StairBlock(Blocks.CRACKED_STONE_BRICKS.defaultBlockState(),
                            BlockBehaviour.Properties
                                    .of(Material.STONE)
                                    .requiresCorrectToolForDrops()
                                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> CRACKED_STONE_BRICKS_WALL =
            registerDefault("cracked_stone_bricks_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(Blocks.CRACKED_STONE_BRICKS)
                            .requiresCorrectToolForDrops()
                            .noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape()));

    public static final RegistryObject<Block> CRACKED_STONE_BRICKS_SLAB =
            registerDefault("cracked_stone_bricks_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties
                            .of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> SMOOTH_STONE_WALL =
            registerDefault("smooth_stone_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)
                            .requiresCorrectToolForDrops()
                            .noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape()));

    public static final RegistryObject<Block> CHISELED_QUARTZ_SANDSTONE =
            registerDefault("chiseled_quartz_sandstone", SandStoneBlock::new);
    public static final RegistryObject<Block> CHISELED_SOUL_SANDSTONE =
            registerDefault("chiseled_soul_sandstone", SandStoneBlock::new);

    public static final RegistryObject<Block> CUT_QUARTZ_SANDSTONE =
            registerDefault("cut_quartz_sandstone", SandStoneBlock::new);

    public static final RegistryObject<Block> CUT_SOUL_SANDSTONE =
            registerDefault("cut_soul_sandstone", SandStoneBlock::new);

    public static final RegistryObject<Block> QUARTZ_SANDSTONE =
            registerDefault("quartz_sandstone", SandStoneBlock::new);

    public static final RegistryObject<Block> SOUL_SANDSTONE =
            registerDefault("soul_sandstone", SandStoneBlock::new);

    public static final RegistryObject<Block> QUARTZ_SANDSTONE_BRICKS =
            registerDefault("quartz_sandstone_bricks", StoneBricksBlock::new);

    public static final RegistryObject<Block> RED_SANDSTONE_BRICKS =
            registerDefault("red_sandstone_bricks", StoneBricksBlock::new);

    public static final RegistryObject<Block> SANDSTONE_BRICKS =
            registerDefault("sandstone_bricks", StoneBricksBlock::new);

    public static final RegistryObject<Block> SOUL_SANDSTONE_BRICKS =
            registerDefault("soul_sandstone_bricks", StoneBricksBlock::new);

    public static final RegistryObject<Block> QUARTZ_PEBBLE =
            registerDefault("quartz_pebble", () -> new VanillaPickBlock(Items.QUARTZ));

    public static final RegistryObject<Block> QUARTZ_SAND =
            registerDefault("quartz_sand",
                    () -> new SandBlock(14406560, BlockBehaviour.Properties.copy(Blocks.SAND)));

    public static final RegistryObject<Block> INFESTED_MOSSY_COBBLESTONE =
            registerDefault("infested_mossy_cobblestone", () ->
                    new InfestedBlock(Blocks.MOSSY_COBBLESTONE, BlockBehaviour.Properties.of(Material.CLAY)));

    public static final RegistryObject<Block> DEEPSLATE_WALL =
            registerDefault("deepslate_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.of(Material.STONE,
                            MaterialColor.DEEPSLATE).requiresCorrectToolForDrops()
                            .strength(3.0F, 6.0F)
                            .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> DEEPSLATE_SLAB =
            registerDefault("deepslate_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties
                            .of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> DEEPSLATE_STAIRS =
            registerDefault("deepslate_stairs", () ->
                    new StairBlock(Blocks.DEEPSLATE.defaultBlockState(),
                            BlockBehaviour.Properties
                                    .of(Material.STONE).requiresCorrectToolForDrops()
                                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> CRACKED_DEEPSLATE_BRICKS_WALL =
            registerDefault("cracked_deepslate_bricks_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.of(Material.STONE,
                                    MaterialColor.DEEPSLATE).requiresCorrectToolForDrops()
                            .strength(3.0F, 6.0F)
                            .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> CRACKED_DEEPSLATE_BRICKS_SLAB =
            registerDefault("cracked_deepslate_bricks_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties
                            .of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> CRACKED_DEEPSLATE_BRICKS_STAIRS =
            registerDefault("cracked_deepslate_bricks_stairs", () ->
                    new StairBlock(Blocks.CRACKED_DEEPSLATE_BRICKS.defaultBlockState(),
                            BlockBehaviour.Properties
                                    .of(Material.STONE).requiresCorrectToolForDrops()
                                    .strength(1.5F, 6.0F)));

    /**
     * Hanlde Default Register
     * @param name registry name
     * @param blockSupplier blocks
     * @param blockItemFactory items
     * @return new Instance
     * @param <T> sth extends Block
     */
    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory) {
        return register(ModBlocks.BLOCKS, ModItems.ITEMS, name, blockSupplier, blockItemFactory);
    }

    /**
     * Handle the default registry block
     * @param name registry name
     * @param blockSupplier blocks
     * @return new Instance
     * @param <T> sth extends Block
     */
    private static <T extends Block> RegistryObject<T> registerDefault(String name, Supplier<T> blockSupplier) {
        return register(name, blockSupplier, block -> new BlockItem(block, new Item.Properties()));
    }

    /**
     * used for registry default BlockItems
     * @param blocks Blocks' Instance
     * @param items Items' Instance
     * @param name BlockItems' registry name
     * @param blockSupplier supplier with Blocks
     * @param blockItemFactory ItemProperties
     * @return new RegistryObject<Block>
     * @param <T> sth extends Block
     */
    public static <T extends Block> RegistryObject<T> register(DeferredRegister<Block> blocks, DeferredRegister<Item> items, String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory) {
        final String actualName = name.toLowerCase(Locale.ROOT);
        final RegistryObject<T> block = blocks.register(actualName, blockSupplier);
        if (blockItemFactory != null) {
            items.register(actualName, () -> blockItemFactory.apply(block.get()));
        }
        return block;
    }

    /**
     * Used for registry Block
     * @param name Blocks' registry name
     * @param block Block Instance
     * @return new RegistryObject<Block>
     * @param <T> sth extends Block
     */
    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        return BLOCKS.register(name.toLowerCase(Locale.ROOT), block);
    }

}
