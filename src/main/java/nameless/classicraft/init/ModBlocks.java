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
            registerDefault("mossy_bricks", () ->
                    new StoneBricksBlock(SoundType.STONE, MaterialColor.COLOR_GREEN));

    public static final RegistryObject<Block> MOSSY_BRICKS_STAIRS =
            registerDefault("mossy_bricks_stairs", () ->
                    new StairBlock(() -> MOSSY_BRICKS.get().defaultBlockState(),
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
            registerDefault("cracked_bricks", () ->
                    new StoneBricksBlock(SoundType.STONE, MaterialColor.COLOR_RED));

    public static final RegistryObject<Block> CRACKED_BRICKS_STAIRS =
            registerDefault("cracked_bricks_stairs", () ->
                    new StairBlock(() -> CRACKED_BRICKS.get().defaultBlockState(),
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
                    new StairBlock(() -> Blocks.SMOOTH_STONE.defaultBlockState(),
                            BlockBehaviour.Properties
                                    .of(Material.STONE)
                                    .requiresCorrectToolForDrops()
                                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> CRACKED_STONE_BRICKS_STAIRS =
            registerDefault("cracked_stone_bricks_stairs", () ->
                    new StairBlock(() -> Blocks.CRACKED_STONE_BRICKS.defaultBlockState(),
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
            registerDefault("chiseled_quartz_sandstone", () ->
                    new SandStoneBlock(SoundType.STONE, MaterialColor.QUARTZ));
    public static final RegistryObject<Block> CHISELED_SOUL_SANDSTONE =
            registerDefault("chiseled_soul_sandstone",  () ->
                    new SandStoneBlock(SoundType.STONE, MaterialColor.COLOR_BROWN));

    public static final RegistryObject<Block> CUT_QUARTZ_SANDSTONE =
            registerDefault("cut_quartz_sandstone",  () ->
                    new SandStoneBlock(SoundType.STONE, MaterialColor.QUARTZ));

    public static final RegistryObject<Block> CUT_SOUL_SANDSTONE =
            registerDefault("cut_soul_sandstone",  () ->
                    new SandStoneBlock(SoundType.STONE, MaterialColor.COLOR_BROWN));

    public static final RegistryObject<Block> QUARTZ_SANDSTONE =
            registerDefault("quartz_sandstone", () ->
                    new SandStoneBlock(SoundType.STONE, MaterialColor.QUARTZ));

    public static final RegistryObject<Block> SOUL_SANDSTONE =
            registerDefault("soul_sandstone",  () ->
                    new SandStoneBlock(SoundType.STONE, MaterialColor.COLOR_BROWN));

    public static final RegistryObject<Block> QUARTZ_SANDSTONE_BRICKS =
            registerDefault("quartz_sandstone_bricks", () ->
                    new StoneBricksBlock(SoundType.STONE, MaterialColor.QUARTZ));

    public static final RegistryObject<Block> RED_SANDSTONE_BRICKS =
            registerDefault("red_sandstone_bricks", () ->
                    new StoneBricksBlock(SoundType.STONE, MaterialColor.FIRE));

    public static final RegistryObject<Block> SANDSTONE_BRICKS =
            registerDefault("sandstone_bricks", () ->
                    new StoneBricksBlock(SoundType.STONE, MaterialColor.SAND));

    public static final RegistryObject<Block> SOUL_SANDSTONE_BRICKS =
            registerDefault("soul_sandstone_bricks", () ->
                    new StoneBricksBlock(SoundType.STONE, MaterialColor.COLOR_BROWN));

    public static final RegistryObject<Block> QUARTZ_PEBBLE =
            register("quartz_pebble", () -> new VanillaPickBlock(Items.QUARTZ));

    public static final RegistryObject<Block> QUARTZ_SAND =
            registerDefault("quartz_sand",
                    () -> new SandBlock(14406560, BlockBehaviour.Properties.copy(Blocks.SAND)));

    public static final RegistryObject<Block> INFESTED_MOSSY_COBBLESTONE =
            registerDefault("infested_mossy_cobblestone", () ->
                    new InfestedBlock(Blocks.MOSSY_COBBLESTONE, BlockBehaviour.Properties.of(Material.CLAY)));

    public static final RegistryObject<Block> INFESTED_CHISELED_DEEPSLATE =
            registerDefault("infested_chiseled_deepslate", () ->
                    new InfestedBlock(Blocks.CHISELED_DEEPSLATE, BlockBehaviour.Properties.of(Material.CLAY).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> INFESTED_COBBLED_DEEPSLATE =
            registerDefault("infested_cobbled_deepslate", () ->
                    new InfestedBlock(Blocks.COBBLED_DEEPSLATE, BlockBehaviour.Properties.of(Material.CLAY).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> INFESTED_DEEPSLATE_BRICKS =
            registerDefault("infested_deepslate_bricks", () ->
                    new InfestedBlock(Blocks.DEEPSLATE_BRICKS, BlockBehaviour.Properties.of(Material.CLAY).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> INFESTED_DEEPSLATE_TILES =
            registerDefault("infested_deepslate_tiles", () ->
                    new InfestedBlock(Blocks.DEEPSLATE_TILES, BlockBehaviour.Properties.of(Material.CLAY).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> INFESTED_CRACKED_DEEPSLATE_BRICKS =
            registerDefault("infested_cracked_deepslate_bricks", () ->
                    new InfestedBlock(Blocks.CRACKED_DEEPSLATE_BRICKS, BlockBehaviour.Properties.of(Material.CLAY).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> INFESTED_CRACKED_DEEPSLATE_TILES =
            registerDefault("infested_cracked_deepslate_tiles", () ->
                    new InfestedBlock(Blocks.CRACKED_DEEPSLATE_TILES, BlockBehaviour.Properties.of(Material.CLAY).sound(SoundType.DEEPSLATE)));


    public static final RegistryObject<Block> DEEPSLATE_WALL =
            registerDefault("deepslate_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.of(Material.STONE,
                            MaterialColor.DEEPSLATE)
                            .sound(SoundType.DEEPSLATE)
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 6.0F)
                            .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> DEEPSLATE_SLAB =
            registerDefault("deepslate_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties
                            .of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.DEEPSLATE)
                            .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> DEEPSLATE_STAIRS =
            registerDefault("deepslate_stairs", () ->
                    new StairBlock(() -> Blocks.DEEPSLATE.defaultBlockState(),
                            BlockBehaviour.Properties
                                    .of(Material.STONE).requiresCorrectToolForDrops()
                                    .sound(SoundType.DEEPSLATE)
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
                    new StairBlock(() -> Blocks.CRACKED_DEEPSLATE_BRICKS.defaultBlockState(),
                            BlockBehaviour.Properties
                                    .of(Material.STONE).requiresCorrectToolForDrops()
                                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> SMOOTH_SANDSTONE_WALL =
            registerDefault("smooth_sandstone_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_SANDSTONE)
                            .requiresCorrectToolForDrops()
                            .noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape()));

    public static final RegistryObject<Block> CUT_SANDSTONE_STAIRS =
            registerDefault("cut_sandstone_stairs", () ->
                    new StairBlock(() -> Blocks.CUT_SANDSTONE.defaultBlockState(),
                            BlockBehaviour.Properties
                                    .of(Material.STONE).requiresCorrectToolForDrops()
                                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> CUT_RED_SANDSTONE_STAIRS =
            registerDefault("cut_red_sandstone_stairs", () ->
                    new StairBlock(() -> Blocks.CUT_RED_SANDSTONE.defaultBlockState(),
                            BlockBehaviour.Properties
                                    .of(Material.STONE).requiresCorrectToolForDrops()
                                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> CUT_SANDSTONE_WALL =
            registerDefault("cut_sandstone_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(Blocks.CUT_SANDSTONE)
                            .requiresCorrectToolForDrops()
                            .noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape()));

    public static final RegistryObject<Block> CUT_RED_SANDSTONE_WALL =
            registerDefault("cut_red_sandstone_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(Blocks.CUT_RED_SANDSTONE)
                            .requiresCorrectToolForDrops()
                            .noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape()));

    public static final RegistryObject<Block> SMOOTH_RED_SANDSTONE_WALL =
            registerDefault("smooth_red_sandstone_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_RED_SANDSTONE)
                            .requiresCorrectToolForDrops()
                            .noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape()));

    public static final RegistryObject<Block> SOUL_SANDSTONE_SLAB =
            registerDefault("soul_sandstone_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties
                            .copy(SOUL_SANDSTONE.get())
                            .requiresCorrectToolForDrops()
                            .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> SOUL_SANDSTONE_STAIRS =
            registerDefault("soul_sandstone_stairs", () ->
                    new StairBlock(() -> SOUL_SANDSTONE.get().defaultBlockState(),
                            BlockBehaviour.Properties
                                    .of(Material.STONE).requiresCorrectToolForDrops()
                                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> SOUL_SANDSTONE_WALL =
            registerDefault("soul_sandstone_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(SOUL_SANDSTONE.get())
                            .requiresCorrectToolForDrops()
                            .noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape()));

    public static final RegistryObject<Block> SMOOTH_SOUL_SANDSTONE =
            registerDefault("smooth_soul_sandstone",  () ->
                    new SandStoneBlock(SoundType.STONE, MaterialColor.COLOR_BROWN));

    public static final RegistryObject<Block> SMOOTH_SOUL_SANDSTONE_WALL =
            registerDefault("smooth_soul_sandstone_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(SMOOTH_SOUL_SANDSTONE.get())
                            .requiresCorrectToolForDrops()
                            .noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape()));

    public static final RegistryObject<Block> SMOOTH_SOUL_SANDSTONE_SLAB =
            registerDefault("smooth_soul_sandstone_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties
                            .copy(SMOOTH_SOUL_SANDSTONE.get())
                            .requiresCorrectToolForDrops()
                            .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> SMOOTH_SOUL_SANDSTONE_STAIRS =
            registerDefault("smooth_soul_sandstone_stairs", () ->
                    new StairBlock(() -> SMOOTH_SOUL_SANDSTONE.get().defaultBlockState(),
                            BlockBehaviour.Properties
                                    .of(Material.STONE).requiresCorrectToolForDrops()
                                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> CUT_SOUL_SANDSTONE_WALL =
            registerDefault("cut_soul_sandstone_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(CUT_SOUL_SANDSTONE.get())
                            .requiresCorrectToolForDrops()
                            .noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape()));

    public static final RegistryObject<Block> CUT_SOUL_SANDSTONE_STAIRS =
            registerDefault("cut_soul_sandstone_stairs", () ->
                    new StairBlock(() -> CUT_SOUL_SANDSTONE.get().defaultBlockState(),
                            BlockBehaviour.Properties
                                    .of(Material.STONE).requiresCorrectToolForDrops()
                                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> CUT_SOUL_SANDSTONE_SLAB =
            registerDefault("cut_soul_sandstone_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties
                            .copy(CUT_SOUL_SANDSTONE.get())
                            .requiresCorrectToolForDrops()
                            .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> CRACKED_DEEPSLATE_TILES_WALL =
            registerDefault("cracked_deepslate_tiles_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.of(Material.STONE,
                                    MaterialColor.DEEPSLATE)
                            .sound(SoundType.DEEPSLATE)
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 6.0F)
                            .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> CRACKED_DEEPSLATE_TILES_SLAB =
            registerDefault("cracked_deepslate_tiles_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties
                            .of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.DEEPSLATE)
                            .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> CRACKED_DEEPSLATE_TILES_STAIRS =
            registerDefault("cracked_deepslate_tiles_stairs", () ->
                    new StairBlock(Blocks.CRACKED_DEEPSLATE_TILES::defaultBlockState,
                            BlockBehaviour.Properties
                                    .of(Material.STONE).requiresCorrectToolForDrops()
                                    .sound(SoundType.DEEPSLATE)
                                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> SANDSTONE_BRICKS_WALL =
            registerDefault("sandstone_bricks_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.of(Material.STONE,
                                    MaterialColor.COLOR_BROWN)
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 6.0F)));

    public static final RegistryObject<Block> SANDSTONE_BRICKS_SLAB =
            registerDefault("sandstone_bricks_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties
                            .of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> SANDSTONE_BRICKS_STAIRS =
            registerDefault("sandstone_bricks_stairs", () ->
                    new StairBlock(() -> SANDSTONE_BRICKS.get().defaultBlockState(),
                            BlockBehaviour.Properties
                                    .of(Material.STONE).requiresCorrectToolForDrops()
                                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> RED_SANDSTONE_BRICKS_WALL =
            registerDefault("red_sandstone_bricks_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.of(Material.STONE,
                                    MaterialColor.FIRE)
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 6.0F)));

    public static final RegistryObject<Block> RED_SANDSTONE_BRICKS_SLAB =
            registerDefault("red_sandstone_bricks_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties
                            .of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> RED_SANDSTONE_BRICKS_STAIRS =
            registerDefault("red_sandstone_bricks_stairs", () ->
                    new StairBlock(() -> RED_SANDSTONE_BRICKS.get().defaultBlockState(),
                            BlockBehaviour.Properties
                                    .of(Material.STONE).requiresCorrectToolForDrops()
                                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> SOUL_SANDSTONE_BRICKS_WALL =
            registerDefault("soul_sandstone_bricks_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.of(Material.STONE,
                                    MaterialColor.COLOR_BROWN)
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 6.0F)));

    public static final RegistryObject<Block> SOUL_SANDSTONE_BRICKS_SLAB =
            registerDefault("soul_sandstone_bricks_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties
                            .of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> SOUL_SANDSTONE_BRICKS_STAIRS =
            registerDefault("soul_sandstone_bricks_stairs", () ->
                    new StairBlock(() -> SOUL_SANDSTONE_BRICKS.get().defaultBlockState(),
                            BlockBehaviour.Properties
                                    .of(Material.STONE).requiresCorrectToolForDrops()
                                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> FLINT_BLOCK_WALL =
            registerDefault("flint_block_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.of(Material.STONE,
                                    MaterialColor.COLOR_BLACK)
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 6.0F)));

    public static final RegistryObject<Block> FLINT_BLOCK_SLAB =
            registerDefault("flint_block_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties
                            .of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> FLINT_BLOCK_STAIRS =
            registerDefault("flint_block_stairs", () ->
                    new StairBlock(() -> FLINT_BLOCK.get().defaultBlockState(),
                            BlockBehaviour.Properties
                                    .of(Material.STONE).requiresCorrectToolForDrops()
                                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> QUARTZ_SANDSTONE_BRICKS_WALL =
            registerDefault("quartz_sandstone_bricks_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.of(Material.STONE,
                                    MaterialColor.QUARTZ)
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 6.0F)
                            .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> QUARTZ_SANDSTONE_BRICKS_SLAB =
            registerDefault("quartz_sandstone_bricks_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties
                            .of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> QUARTZ_SANDSTONE_BRICKS_STAIRS =
            registerDefault("quartz_sandstone_bricks_stairs", () ->
                    new StairBlock(() -> QUARTZ_SANDSTONE_BRICKS.get().defaultBlockState(),
                            BlockBehaviour.Properties
                                    .of(Material.STONE).requiresCorrectToolForDrops()
                                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> PRISMARINE_BRICKS_WALL =
            registerDefault("prismarine_bricks_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(Blocks.PRISMARINE_BRICKS)
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 6.0F)));

    public static final RegistryObject<Block> END_STONE_WALL =
            registerDefault("end_stone_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE)
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 6.0F)));


    public static final RegistryObject<Block> END_STONE_SLAB =
            registerDefault("end_stone_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties
                            .of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> END_STONE_STAIRS =
            registerDefault("end_stone_stairs", () ->
                    new StairBlock(() -> Blocks.END_STONE.defaultBlockState(),
                            BlockBehaviour.Properties
                                    .of(Material.STONE).requiresCorrectToolForDrops()
                                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> NETHERRACK_WALL =
            registerDefault("netherrack_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(Blocks.NETHERRACK)
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 6.0F)));

    public static final RegistryObject<Block> NETHERRACK_SLAB =
            registerDefault("netherrack_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties
                            .of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> NETHERRACK_STAIRS =
            registerDefault("netherrack_stairs", () ->
                    new StairBlock(() -> Blocks.NETHERRACK.defaultBlockState(),
                            BlockBehaviour.Properties
                                    .of(Material.STONE).requiresCorrectToolForDrops()
                                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> DARK_PRISMARINE_WALL =
            registerDefault("dark_prismarine_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(Blocks.DARK_PRISMARINE)
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 6.0F)));

    public static final RegistryObject<Block> SMOOTH_QUARTZ_SANDSTONE =
            registerDefault("smooth_quartz_sandstone", () ->
                    new SandStoneBlock(SoundType.STONE, MaterialColor.QUARTZ));

    public static final RegistryObject<Block> SMOOTH_QUARTZ_SANDSTONE_SLAB =
            registerDefault("smooth_quartz_sandstone_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties
                            .of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> SMOOTH_QUARTZ_SANDSTONE_STAIRS =
            registerDefault("smooth_quartz_sandstone_stairs", () ->
                    new StairBlock(() -> SMOOTH_QUARTZ_SANDSTONE.get().defaultBlockState(),
                            BlockBehaviour.Properties
                                    .of(Material.STONE).requiresCorrectToolForDrops()
                                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> SMOOTH_QUARTZ_SANDSTONE_WALL =
            registerDefault("smooth_quartz_sandstone_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(SMOOTH_QUARTZ_SANDSTONE.get())
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 6.0F)));

    public static final RegistryObject<Block> SMOOTH_BASALT_SLAB =
            registerDefault("smooth_basalt_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties
                            .of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> SMOOTH_BASALT_STAIRS =
            registerDefault("smooth_basalt_stairs", () ->
                    new StairBlock(Blocks.SMOOTH_BASALT::defaultBlockState,
                            BlockBehaviour.Properties
                                    .of(Material.STONE).requiresCorrectToolForDrops()
                                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> SMOOTH_BASALT_WALL =
            registerDefault("smooth_basalt_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_BASALT)
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 6.0F)));

    public static final RegistryObject<Block> CUT_QUARTZ_SANDSTONE_WALL =
            registerDefault("cut_quartz_sandstone_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(CUT_QUARTZ_SANDSTONE.get())
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 6.0F)));

    public static final RegistryObject<Block> CUT_QUARTZ_SANDSTONE_STAIRS =
            registerDefault("cut_quartz_sandstone_stairs", () ->
                    new StairBlock(() -> CUT_QUARTZ_SANDSTONE.get().defaultBlockState(),
                            BlockBehaviour.Properties
                                    .of(Material.STONE).requiresCorrectToolForDrops()
                                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> CUT_QUARTZ_SANDSTONE_SLAB =
            registerDefault("cut_quartz_sandstone_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties
                            .of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> QUARTZ_BRICKS_SLAB =
            registerDefault("quartz_bricks_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties
                            .of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> QUARTZ_BRICKS_STAIRS =
            registerDefault("quartz_bricks_stairs", () ->
                    new StairBlock(Blocks.QUARTZ_BRICKS::defaultBlockState,
                            BlockBehaviour.Properties
                                    .of(Material.STONE).requiresCorrectToolForDrops()
                                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> QUARTZ_BRICKS_WALL =
            registerDefault("quartz_bricks_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BRICKS)
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 6.0F)));

    public static final RegistryObject<Block> WHITE_WOOL_SLAB =
            registerDefault("white_wool_slab", () ->
                    new SlabBlock(offWool(MaterialColor.SNOW)));

    public static final RegistryObject<Block> WHITE_WOOL_STAIRS =
            registerDefault("white_wool_stairs", () ->
                    new StairBlock(Blocks.WHITE_WOOL::defaultBlockState,
                            offWool(MaterialColor.SNOW)));

    public static final RegistryObject<Block> ORANGE_WOOL_SLAB =
            registerDefault("orange_wool_slab", () ->
                    new SlabBlock(offWool(MaterialColor.COLOR_ORANGE)));

    public static final RegistryObject<Block> ORANGE_WOOL_STAIRS =
            registerDefault("orange_wool_stairs", () ->
                    new StairBlock(Blocks.ORANGE_WOOL::defaultBlockState,
                            offWool(MaterialColor.COLOR_ORANGE)));

    public static final RegistryObject<Block> MAGENTA_WOOL_SLAB =
            registerDefault("magenta_wool_slab", () ->
                    new SlabBlock(offWool(MaterialColor.COLOR_MAGENTA)));

    public static final RegistryObject<Block> MAGENTA_WOOL_STAIRS =
            registerDefault("magenta_wool_stairs", () ->
                    new StairBlock(Blocks.MAGENTA_WOOL::defaultBlockState,
                            offWool(MaterialColor.COLOR_MAGENTA)));

    public static final RegistryObject<Block> LIGHT_BLUE_WOOL_SLAB =
            registerDefault("light_blue_wool_slab", () ->
                    new SlabBlock(offWool(MaterialColor.COLOR_LIGHT_BLUE)));

    public static final RegistryObject<Block> LIGHT_BLUE_WOOL_STAIRS =
            registerDefault("light_blue_wool_stairs", () ->
                    new StairBlock(Blocks.LIGHT_BLUE_WOOL::defaultBlockState,
                            offWool(MaterialColor.COLOR_LIGHT_BLUE)));

    public static final RegistryObject<Block> YELLOW_WOOL_SLAB =
            registerDefault("yellow_wool_slab", () ->
                    new SlabBlock(offWool(MaterialColor.COLOR_YELLOW)));

    public static final RegistryObject<Block> YELLOW_WOOL_STAIRS =
            registerDefault("yellow_wool_stairs", () ->
                    new StairBlock(Blocks.YELLOW_WOOL::defaultBlockState,
                            offWool(MaterialColor.COLOR_YELLOW)));

    public static final RegistryObject<Block> LIME_WOOL_SLAB =
            registerDefault("lime_wool_slab", () ->
                    new SlabBlock(offWool(MaterialColor.COLOR_LIGHT_GREEN)));

    public static final RegistryObject<Block> LIME_WOOL_STAIRS =
            registerDefault("lime_wool_stairs", () ->
                    new StairBlock(Blocks.LIME_WOOL::defaultBlockState,
                            offWool(MaterialColor.COLOR_LIGHT_GREEN)));

    public static final RegistryObject<Block> PINK_WOOL_SLAB =
            registerDefault("pink_wool_slab", () ->
                    new SlabBlock(offWool(MaterialColor.COLOR_PINK)));

    public static final RegistryObject<Block> PINK_WOOL_STAIRS =
            registerDefault("pink_wool_stairs", () ->
                    new StairBlock(Blocks.PINK_WOOL::defaultBlockState,
                            offWool(MaterialColor.COLOR_PINK)));

    public static final RegistryObject<Block> GRAY_WOOL_SLAB =
            registerDefault("gray_wool_slab", () ->
                    new SlabBlock(offWool(MaterialColor.COLOR_GRAY)));

    public static final RegistryObject<Block> GRAY_WOOL_STAIRS =
            registerDefault("gray_wool_stairs", () ->
                    new StairBlock(Blocks.GRAY_WOOL::defaultBlockState,
                            offWool(MaterialColor.COLOR_GRAY)));

    public static final RegistryObject<Block> LIGHT_GRAY_WOOL_SLAB =
            registerDefault("light_gray_wool_slab", () ->
                    new SlabBlock(offWool(MaterialColor.COLOR_LIGHT_GRAY)));

    public static final RegistryObject<Block> LIGHT_GRAY_WOOL_STAIRS =
            registerDefault("light_gray_wool_stairs", () ->
                    new StairBlock(Blocks.LIGHT_GRAY_WOOL::defaultBlockState,
                            offWool(MaterialColor.COLOR_LIGHT_GRAY)));

    public static final RegistryObject<Block> CYAN_WOOL_SLAB =
            registerDefault("cyan_wool_slab", () ->
                    new SlabBlock(offWool(MaterialColor.COLOR_CYAN)));

    public static final RegistryObject<Block> CYAN_WOOL_STAIRS =
            registerDefault("cyan_wool_stairs", () ->
                    new StairBlock(Blocks.CYAN_WOOL::defaultBlockState,
                            offWool(MaterialColor.COLOR_CYAN)));

    public static final RegistryObject<Block> PURPLE_WOOL_SLAB =
            registerDefault("purple_wool_slab", () ->
                    new SlabBlock(offWool(MaterialColor.COLOR_PURPLE)));

    public static final RegistryObject<Block> PURPLE_WOOL_STAIRS =
            registerDefault("purple_wool_stairs", () ->
                    new StairBlock(Blocks.PURPLE_WOOL::defaultBlockState,
                            offWool(MaterialColor.COLOR_PURPLE)));

    public static final RegistryObject<Block> BLUE_WOOL_SLAB =
            registerDefault("blue_wool_slab", () ->
                    new SlabBlock(offWool(MaterialColor.COLOR_BLUE)));

    public static final RegistryObject<Block> BLUE_WOOL_STAIRS =
            registerDefault("blue_wool_stairs", () ->
                    new StairBlock(Blocks.BLUE_WOOL::defaultBlockState,
                            offWool(MaterialColor.COLOR_BLUE)));

    public static final RegistryObject<Block> BROWN_WOOL_SLAB =
            registerDefault("brown_wool_slab", () ->
                    new SlabBlock(offWool(MaterialColor.COLOR_BROWN)));

    public static final RegistryObject<Block> BROWN_WOOL_STAIRS =
            registerDefault("brown_wool_stairs", () ->
                    new StairBlock(Blocks.BROWN_WOOL::defaultBlockState,
                            offWool(MaterialColor.COLOR_BROWN)));

    public static final RegistryObject<Block> GREEN_WOOL_SLAB =
            registerDefault("green_wool_slab", () ->
                    new SlabBlock(offWool(MaterialColor.COLOR_GREEN)));

    public static final RegistryObject<Block> GREEN_WOOL_STAIRS =
            registerDefault("green_wool_stairs", () ->
                    new StairBlock(Blocks.GREEN_WOOL::defaultBlockState,
                            offWool(MaterialColor.COLOR_GREEN)));

    public static final RegistryObject<Block> RED_WOOL_SLAB =
            registerDefault("red_wool_slab", () ->
                    new SlabBlock(offWool(MaterialColor.COLOR_RED)));

    public static final RegistryObject<Block> RED_WOOL_STAIRS =
            registerDefault("red_wool_stairs", () ->
                    new StairBlock(Blocks.RED_WOOL::defaultBlockState,
                            offWool(MaterialColor.COLOR_RED)));

    public static final RegistryObject<Block> BLACK_WOOL_SLAB =
            registerDefault("black_wool_slab", () ->
                    new SlabBlock(offWool(MaterialColor.COLOR_BLACK)));

    public static final RegistryObject<Block> BLACK_WOOL_STAIRS =
            registerDefault("black_wool_stairs", () ->
                    new StairBlock(Blocks.BLACK_WOOL::defaultBlockState,
                            offWool(MaterialColor.COLOR_BLACK)));

    public static final RegistryObject<Block> QUARTZ_SANDSTONE_STAIRS =
            registerDefault("quartz_sandstone_stairs", () ->
                    new StairBlock(() -> QUARTZ_SANDSTONE.get().defaultBlockState(),
                            offSandStone(MaterialColor.QUARTZ)));

    public static final RegistryObject<Block> QUARTZ_SANDSTONE_SLAB =
            registerDefault("quartz_sandstone_slab", () ->
                    new SlabBlock(offSandStone(MaterialColor.QUARTZ)));

    public static final RegistryObject<Block> QUARTZ_SANDSTONE_WALL =
            registerDefault("quartz_sandstone_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(QUARTZ_SANDSTONE.get())
                            .requiresCorrectToolForDrops()
                            .noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape()));

    public static final RegistryObject<Block> POLISHED_BASALT_WALL =
            registerDefault("polished_basalt_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(Blocks.POLISHED_BASALT)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.BASALT)
                            .noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape()));

    public static final RegistryObject<Block> POLISHED_BASALT_SLAB =
            registerDefault("polished_basalt_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties.of(Material.STONE,
                            MaterialColor.COLOR_BLACK)
                            .requiresCorrectToolForDrops()
                            .strength(1.25F, 4.2F)
                            .sound(SoundType.BASALT)));

    public static final RegistryObject<Block> POLISHED_BASALT_STAIRS =
            registerDefault("polished_basalt_stairs", () ->
                    new StairBlock(Blocks.POLISHED_BASALT::defaultBlockState,
                            BlockBehaviour.Properties.of(Material.STONE,
                                            MaterialColor.COLOR_BLACK)
                                    .requiresCorrectToolForDrops()
                                    .strength(1.25F, 4.2F)
                                    .sound(SoundType.BASALT)));

    private static BlockBehaviour.Properties offSandStone(MaterialColor color) {
        return BlockBehaviour.Properties.of(Material.STONE
                ).sound(SoundType.STONE)
                .color(color).requiresCorrectToolForDrops()
                .strength(0.8F);
    }

    private static BlockBehaviour.Properties offWool(MaterialColor color) {
        return BlockBehaviour.Properties.of(Material.WOOL, color).strength(0.8F).sound(SoundType.WOOL);
    }

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
