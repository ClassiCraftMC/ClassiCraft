/*
 * ClassiCraft - ClassiCraftMC
 * Copyright (C) 2018-2022.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.block.*;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
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
            registerDefault("rose", () -> new FlowerBlock(() -> MobEffects.DIG_SPEED, 2
            , BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistryObject<Block> POTTED_ROSE =
            register("potted_rose", () -> new FlowerPotBlock(null, () -> ROSE.get(),
                    BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));

    public static final RegistryObject<Block> TALLOW_BLOCK =
            registerDefault("tallow_block", TallowBlock::new);

    public static final RegistryObject<Block> MOSSY_BRICKS =
            registerDefault("mossy_bricks", () ->
                    new StoneLikeBlock(SoundType.STONE, MaterialColor.COLOR_GREEN));

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
                    new StoneLikeBlock(SoundType.STONE, MaterialColor.COLOR_RED));

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

    public static final RegistryObject<Block> DEEPSLATE_SULFUR_ORE =
            registerDefault("deepslate_sulfur_ore", () ->
                    new DropExperienceBlock(BlockBehaviour.Properties.of(Material.METAL)
                            .strength(2.0F, 3.0F).requiresCorrectToolForDrops()
                            .sound(SoundType.DEEPSLATE),
                            UniformInt.of(1, 5)));

    public static final RegistryObject<Block> NETHER_SULFUR_ORE =
            registerDefault("nether_sulfur_ore", () ->
                    new DropExperienceBlock(BlockBehaviour.Properties.of(Material.METAL)
                            .strength(2.0F, 3.0F).requiresCorrectToolForDrops()
                            .sound(SoundType.DEEPSLATE),
                            UniformInt.of(1, 5)));

    public static final RegistryObject<Block> STONE_WALL =
            registerDefault("stone_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                            .requiresCorrectToolForDrops()
                            .noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape()));

    public static final RegistryObject<Block> SMOOTH_STONE_STAIRS =
            registerDefault("smooth_stone_stairs", () ->
                    new StairBlock(Blocks.SMOOTH_STONE::defaultBlockState,
                            BlockBehaviour.Properties
                                    .of(Material.STONE)
                                    .requiresCorrectToolForDrops()
                                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> CRACKED_STONE_BRICKS_STAIRS =
            registerDefault("cracked_stone_bricks_stairs", () ->
                    new StairBlock(Blocks.CRACKED_STONE_BRICKS::defaultBlockState,
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
                    new StoneLikeBlock(SoundType.STONE, MaterialColor.QUARTZ));

    public static final RegistryObject<Block> RED_SANDSTONE_BRICKS =
            registerDefault("red_sandstone_bricks", () ->
                    new StoneLikeBlock(SoundType.STONE, MaterialColor.FIRE));

    public static final RegistryObject<Block> SANDSTONE_BRICKS =
            registerDefault("sandstone_bricks", () ->
                    new StoneLikeBlock(SoundType.STONE, MaterialColor.SAND));

    public static final RegistryObject<Block> SOUL_SANDSTONE_BRICKS =
            registerDefault("soul_sandstone_bricks", () ->
                    new StoneLikeBlock(SoundType.STONE, MaterialColor.COLOR_BROWN));

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
                    new StairBlock(Blocks.DEEPSLATE::defaultBlockState,
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
                    new StairBlock(Blocks.CRACKED_DEEPSLATE_BRICKS::defaultBlockState,
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
                    new StairBlock(Blocks.CUT_RED_SANDSTONE::defaultBlockState,
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
                            ofSandStone(MaterialColor.QUARTZ)));

    public static final RegistryObject<Block> QUARTZ_SANDSTONE_SLAB =
            registerDefault("quartz_sandstone_slab", () ->
                    new SlabBlock(ofSandStone(MaterialColor.QUARTZ)));

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

    public static final RegistryObject<Block> CRIMSON_NYLIUM_WALL =
            registerDefault("crimson_nylium_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(Blocks.CRIMSON_NYLIUM)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.NYLIUM)
                            .noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape()));

    public static final RegistryObject<Block> CRIMSON_NYLIUM_SLAB =
            registerDefault("crimson_nylium_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties.of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(1.25F, 4.2F)
                            .sound(SoundType.NYLIUM)));

    public static final RegistryObject<Block> CRIMSON_NYLIUM_STAIRS =
            registerDefault("crimson_nylium_stairs", () ->
                    new StairBlock(Blocks.CRIMSON_NYLIUM::defaultBlockState,
                            BlockBehaviour.Properties.of(Material.STONE)
                                    .requiresCorrectToolForDrops()
                                    .strength(1.25F, 4.2F)
                                    .sound(SoundType.NYLIUM)));

    public static final RegistryObject<Block> WARPED_NYLIUM_WALL =
            registerDefault("warped_nylium_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_NYLIUM)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.NYLIUM)
                            .noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape()));

    public static final RegistryObject<Block> WARPED_NYLIUM_SLAB =
            registerDefault("warped_nylium_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties.of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(1.25F, 4.2F)
                            .sound(SoundType.NYLIUM)));

    public static final RegistryObject<Block> WARPED_NYLIUM_STAIRS =
            registerDefault("warped_nylium_stairs", () ->
                    new StairBlock(Blocks.WARPED_NYLIUM::defaultBlockState,
                            BlockBehaviour.Properties.of(Material.STONE)
                                    .requiresCorrectToolForDrops()
                                    .strength(1.25F, 4.2F)
                                    .sound(SoundType.NYLIUM)));

    public static final RegistryObject<Block> QUARTZ_WALL =
            registerDefault("quartz_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)
                            .noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape()));

    public static final RegistryObject<Block> CRACKED_NETHER_BRICKS_WALL =
            registerDefault("cracked_nether_bricks_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(Blocks.CRACKED_NETHER_BRICKS)
                            .requiresCorrectToolForDrops()
                            .noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape()));

    public static final RegistryObject<Block> CRACKED_NETHER_BRICKS_SLAB =
            registerDefault("cracked_nether_bricks_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties.of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(1.25F, 4.2F)));

    public static final RegistryObject<Block> CRACKED_NETHER_BRICKS_STAIRS =
            registerDefault("cracked_nether_bricks_stairs", () ->
                    new StairBlock(Blocks.CRACKED_NETHER_BRICKS::defaultBlockState,
                            BlockBehaviour.Properties.of(Material.STONE)
                                    .requiresCorrectToolForDrops()
                                    .strength(1.25F, 4.2F)));

    public static final RegistryObject<Block> CRIMSON_NETHER_BRICKS_FENCE =
            registerDefault("crimson_nether_bricks_fence", () ->
                    new FenceBlock(BlockBehaviour.Properties.of(Material.STONE,
                            MaterialColor.NETHER).requiresCorrectToolForDrops()
                            .strength(2.0F, 6.0F)
                            .sound(SoundType.NETHER_BRICKS)));

    public static final RegistryObject<Block> PURPUR_BLOCK_WALL =
            registerDefault("purpur_block_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(Blocks.PURPUR_BLOCK)
                            .requiresCorrectToolForDrops()
                            .noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape()));

    public static final RegistryObject<Block> SMOOTH_QUARTZ_WALL =
            registerDefault("smooth_quartz_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_QUARTZ)
                            .requiresCorrectToolForDrops()
                            .noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape()));

    public static final RegistryObject<Block> CRACKED_POLISHED_BLACKSTONE_BRICKS_WALL =
            registerDefault("cracked_polished_blackstone_bricks_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.copy(Blocks.CRACKED_NETHER_BRICKS)
                            .requiresCorrectToolForDrops()
                            .noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape()));

    public static final RegistryObject<Block> CRACKED_POLISHED_BLACKSTONE_BRICKS_SLAB =
            registerDefault("cracked_polished_blackstone_bricks_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties.of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(1.25F, 4.2F)));

    public static final RegistryObject<Block> CRACKED_POLISHED_BLACKSTONE_BRICKS_STAIRS =
            registerDefault("cracked_polished_blackstone_bricks_stairs", () ->
                    new StairBlock(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS::defaultBlockState,
                            BlockBehaviour.Properties.of(Material.STONE)
                                    .requiresCorrectToolForDrops()
                                    .strength(1.25F, 4.2F)));

    public static final RegistryObject<Block> WHITE_STAINED_GLASS_SLAB =
            registerDefault("white_stained_glass_slab", () ->
                    new StainedGlassSlabBlock(DyeColor.WHITE));

    public static final RegistryObject<Block> ORANGE_STAINED_GLASS_SLAB =
            registerDefault("orange_stained_glass_slab", () ->
                    new StainedGlassSlabBlock(DyeColor.ORANGE));

    public static final RegistryObject<Block> MAGENTA_STAINED_GLASS_SLAB =
            registerDefault("magenta_stained_glass_slab", () ->
                    new StainedGlassSlabBlock(DyeColor.MAGENTA));

    public static final RegistryObject<Block> LIGHT_BLUE_STAINED_GLASS_SLAB =
            registerDefault("light_blue_stained_glass_slab", () ->
                    new StainedGlassSlabBlock(DyeColor.LIGHT_BLUE));

    public static final RegistryObject<Block> YELLOW_STAINED_GLASS_SLAB =
            registerDefault("yellow_stained_glass_slab", () ->
                    new StainedGlassSlabBlock(DyeColor.YELLOW));

    public static final RegistryObject<Block> LIME_STAINED_GLASS_SLAB =
            registerDefault("lime_stained_glass_slab", () ->
                    new StainedGlassSlabBlock(DyeColor.LIME));

    public static final RegistryObject<Block> PINK_STAINED_GLASS_SLAB =
            registerDefault("pink_stained_glass_slab", () ->
                    new StainedGlassSlabBlock(DyeColor.PINK));

    public static final RegistryObject<Block> GRAY_STAINED_GLASS_SLAB =
            registerDefault("gray_stained_glass_slab", () ->
                    new StainedGlassSlabBlock(DyeColor.GRAY));

    public static final RegistryObject<Block> LIGHT_GRAY_STAINED_GLASS_SLAB =
            registerDefault("light_gray_stained_glass_slab", () ->
                    new StainedGlassSlabBlock(DyeColor.LIGHT_GRAY));

    public static final RegistryObject<Block> CYAN_STAINED_GLASS_SLAB =
            registerDefault("cyan_stained_glass_slab", () ->
                    new StainedGlassSlabBlock(DyeColor.CYAN));

    public static final RegistryObject<Block> PURPLE_STAINED_GLASS_SLAB =
            registerDefault("purple_stained_glass_slab", () ->
                    new StainedGlassSlabBlock(DyeColor.PURPLE));

    public static final RegistryObject<Block> BLUE_STAINED_GLASS_SLAB =
            registerDefault("blue_stained_glass_slab", () ->
                    new StainedGlassSlabBlock(DyeColor.BLUE));

    public static final RegistryObject<Block> BROWN_STAINED_GLASS_SLAB =
            registerDefault("brown_stained_glass_slab", () ->
                    new StainedGlassSlabBlock(DyeColor.BROWN));

    public static final RegistryObject<Block> GREEN_STAINED_GLASS_SLAB =
            registerDefault("green_stained_glass_slab", () ->
                    new StainedGlassSlabBlock(DyeColor.GREEN));

    public static final RegistryObject<Block> RED_STAINED_GLASS_SLAB =
            registerDefault("red_stained_glass_slab", () ->
                    new StainedGlassSlabBlock(DyeColor.RED));

    public static final RegistryObject<Block> BLACK_STAINED_GLASS_SLAB =
            registerDefault("black_stained_glass_slab", () ->
                    new StainedGlassSlabBlock(DyeColor.BLACK));

    public static final RegistryObject<Block> WHITE_STAINED_GLASS_STAIRS =
            registerDefault("white_stained_glass_stairs", () ->
                    new StainedGlassStairBlock(DyeColor.WHITE,
                            Blocks.WHITE_STAINED_GLASS::defaultBlockState));

    public static final RegistryObject<Block> ORANGE_STAINED_GLASS_STAIRS =
            registerDefault("orange_stained_glass_stairs", () ->
                    new StainedGlassStairBlock(DyeColor.ORANGE,
                            Blocks.ORANGE_STAINED_GLASS::defaultBlockState));

    public static final RegistryObject<Block> MAGENTA_STAINED_GLASS_STAIRS =
            registerDefault("magenta_stained_glass_stairs", () ->
                    new StainedGlassStairBlock(DyeColor.MAGENTA,
                            Blocks.MAGENTA_STAINED_GLASS::defaultBlockState));

    public static final RegistryObject<Block> LIGHT_BLUE_STAINED_GLASS_STAIRS =
            registerDefault("light_blue_stained_glass_stairs", () ->
                    new StainedGlassStairBlock(DyeColor.LIGHT_BLUE,
                            Blocks.LIGHT_BLUE_STAINED_GLASS::defaultBlockState));

    public static final RegistryObject<Block> YELLOW_STAINED_GLASS_STAIRS =
            registerDefault("yellow_stained_glass_stairs", () ->
                    new StainedGlassStairBlock(DyeColor.YELLOW,
                            Blocks.YELLOW_STAINED_GLASS::defaultBlockState));

    public static final RegistryObject<Block> LIME_STAINED_GLASS_STAIRS =
            registerDefault("lime_stained_glass_stairs", () ->
                    new StainedGlassStairBlock(DyeColor.LIME,
                            Blocks.LIME_STAINED_GLASS::defaultBlockState));

    public static final RegistryObject<Block> PINK_STAINED_GLASS_STAIRS =
            registerDefault("pink_stained_glass_stairs", () ->
                    new StainedGlassStairBlock(DyeColor.PINK,
                            Blocks.PINK_STAINED_GLASS::defaultBlockState));

    public static final RegistryObject<Block> GRAY_STAINED_GLASS_STAIRS =
            registerDefault("gray_stained_glass_stairs", () ->
                    new StainedGlassStairBlock(DyeColor.GRAY,
                            Blocks.GRAY_STAINED_GLASS::defaultBlockState));

    public static final RegistryObject<Block> LIGHT_GRAY_STAINED_GLASS_STAIRS =
            registerDefault("light_gray_stained_glass_stairs", () ->
                    new StainedGlassStairBlock(DyeColor.LIGHT_GRAY,
                            Blocks.LIGHT_GRAY_STAINED_GLASS::defaultBlockState));

    public static final RegistryObject<Block> CYAN_STAINED_GLASS_STAIRS =
            registerDefault("cyan_stained_glass_stairs", () ->
                    new StainedGlassStairBlock(DyeColor.CYAN,
                            Blocks.CYAN_STAINED_GLASS::defaultBlockState));

    public static final RegistryObject<Block> PURPLE_STAINED_GLASS_STAIRS =
            registerDefault("purple_stained_glass_stairs", () ->
                    new StainedGlassStairBlock(DyeColor.PURPLE,
                            Blocks.PURPLE_STAINED_GLASS::defaultBlockState));

    public static final RegistryObject<Block> BLUE_STAINED_GLASS_STAIRS =
            registerDefault("blue_stained_glass_stairs", () ->
                    new StainedGlassStairBlock(DyeColor.BLUE,
                            Blocks.BLUE_STAINED_GLASS::defaultBlockState));

    public static final RegistryObject<Block> BROWN_STAINED_GLASS_STAIRS =
            registerDefault("brown_stained_glass_stairs", () ->
                    new StainedGlassStairBlock(DyeColor.BROWN,
                            Blocks.BROWN_STAINED_GLASS::defaultBlockState));

    public static final RegistryObject<Block> GREEN_STAINED_GLASS_STAIRS =
            registerDefault("green_stained_glass_stairs", () ->
                    new StainedGlassStairBlock(DyeColor.GREEN,
                            Blocks.GREEN_STAINED_GLASS::defaultBlockState));

    public static final RegistryObject<Block> RED_STAINED_GLASS_STAIRS =
            registerDefault("red_stained_glass_stairs", () ->
                    new StainedGlassStairBlock(DyeColor.RED,
                            Blocks.RED_STAINED_GLASS::defaultBlockState));

    public static final RegistryObject<Block> BLACK_STAINED_GLASS_STAIRS =
            registerDefault("black_stained_glass_stairs", () ->
                    new StainedGlassStairBlock(DyeColor.BLACK,
                            Blocks.BLACK_STAINED_GLASS::defaultBlockState));

    public static final RegistryObject<Block> GLASS_STAIRS =
            registerDefault("glass_stairs", () ->
                    new StainedGlassStairBlock(DyeColor.WHITE,
                            Blocks.GLASS::defaultBlockState));

    public static final RegistryObject<Block> GLASS_SLAB =
            registerDefault("glass_slab", () ->
                    new StainedGlassSlabBlock(DyeColor.WHITE));

    public static final RegistryObject<Block> GLASS_WALL =
            registerDefault("glass_wall", () ->
                    new StainedGlassWallBlock(DyeColor.WHITE));

    public static final RegistryObject<Block> TINTED_GLASS_STAIRS =
            registerDefault("tinted_glass_stairs", () ->
                    new TintedGlassStairBlock(Blocks.TINTED_GLASS::defaultBlockState));

    public static final RegistryObject<Block> TINTED_GLASS_SLAB =
            registerDefault("tinted_glass_slab", TintedGlassSlabBlock::new);

    public static final RegistryObject<Block> TINTED_GLASS_WALL =
            registerDefault("tinted_glass_wall", TintedGlassWallBlock::new);

    public static final RegistryObject<Block> WHITE_CONCRETE_STAIRS =
            registerDefault("white_concrete_stairs", () ->
                    new StairBlock(Blocks.WHITE_CONCRETE::defaultBlockState,
                            ofConcrete(DyeColor.WHITE)));

    public static final RegistryObject<Block> WHITE_CONCRETE_SLAB =
            registerDefault("white_concrete_slab", () ->
                    new SlabBlock(ofConcrete(DyeColor.WHITE)));

    public static final RegistryObject<Block> WHITE_CONCRETE_WALL =
            registerDefault("white_concrete_wall", () ->
                    new WallBlock(ofConcrete(DyeColor.WHITE)));

    public static final RegistryObject<Block> ORANGE_CONCRETE_STAIRS =
            registerDefault("orange_concrete_stairs", () ->
                    new StairBlock(Blocks.ORANGE_CONCRETE::defaultBlockState,
                            ofConcrete(DyeColor.ORANGE)));

    public static final RegistryObject<Block> ORANGE_CONCRETE_SLAB =
            registerDefault("orange_concrete_slab", () ->
                    new SlabBlock(ofConcrete(DyeColor.ORANGE)));

    public static final RegistryObject<Block> ORANGE_CONCRETE_WALL =
            registerDefault("orange_concrete_wall", () ->
                    new WallBlock(ofConcrete(DyeColor.ORANGE)));

    public static final RegistryObject<Block> MAGENTA_CONCRETE_STAIRS =
            registerDefault("magenta_concrete_stairs", () ->
                    new StairBlock(Blocks.MAGENTA_CONCRETE::defaultBlockState,
                            ofConcrete(DyeColor.MAGENTA)));

    public static final RegistryObject<Block> MAGENTA_CONCRETE_SLAB =
            registerDefault("magenta_concrete_slab", () ->
                    new SlabBlock(ofConcrete(DyeColor.MAGENTA)));

    public static final RegistryObject<Block> MAGENTA_CONCRETE_WALL =
            registerDefault("magenta_concrete_wall", () ->
                    new WallBlock(ofConcrete(DyeColor.MAGENTA)));

    public static final RegistryObject<Block> LIGHT_BLUE_CONCRETE_STAIRS =
            registerDefault("light_blue_concrete_stairs", () ->
                    new StairBlock(Blocks.LIGHT_BLUE_CONCRETE::defaultBlockState,
                            ofConcrete(DyeColor.LIGHT_BLUE)));

    public static final RegistryObject<Block> LIGHT_BLUE_CONCRETE_SLAB =
            registerDefault("light_blue_concrete_slab", () ->
                    new SlabBlock(ofConcrete(DyeColor.LIGHT_BLUE)));

    public static final RegistryObject<Block> LIGHT_BLUE_CONCRETE_WALL =
            registerDefault("light_blue_concrete_wall", () ->
                    new WallBlock(ofConcrete(DyeColor.LIGHT_BLUE)));

    public static final RegistryObject<Block> YELLOW_CONCRETE_STAIRS =
            registerDefault("yellow_concrete_stairs", () ->
                    new StairBlock(Blocks.YELLOW_CONCRETE::defaultBlockState,
                            ofConcrete(DyeColor.YELLOW)));

    public static final RegistryObject<Block> YELLOW_CONCRETE_SLAB =
            registerDefault("yellow_concrete_slab", () ->
                    new SlabBlock(ofConcrete(DyeColor.YELLOW)));

    public static final RegistryObject<Block> YELLOW_CONCRETE_WALL =
            registerDefault("yellow_concrete_wall", () ->
                    new WallBlock(ofConcrete(DyeColor.YELLOW)));

    public static final RegistryObject<Block> LIME_CONCRETE_STAIRS =
            registerDefault("lime_concrete_stairs", () ->
                    new StairBlock(Blocks.LIME_CONCRETE::defaultBlockState,
                            ofConcrete(DyeColor.MAGENTA)));

    public static final RegistryObject<Block> LIME_CONCRETE_SLAB =
            registerDefault("lime_concrete_slab", () ->
                    new SlabBlock(ofConcrete(DyeColor.LIME)));

    public static final RegistryObject<Block> LIME_CONCRETE_WALL =
            registerDefault("lime_concrete_wall", () ->
                    new WallBlock(ofConcrete(DyeColor.LIME)));

    public static final RegistryObject<Block> PINK_CONCRETE_STAIRS =
            registerDefault("pink_concrete_stairs", () ->
                    new StairBlock(Blocks.PINK_CONCRETE::defaultBlockState,
                            ofConcrete(DyeColor.PINK)));

    public static final RegistryObject<Block> PINK_CONCRETE_SLAB =
            registerDefault("pink_concrete_slab", () ->
                    new SlabBlock(ofConcrete(DyeColor.PINK)));

    public static final RegistryObject<Block> PINK_CONCRETE_WALL =
            registerDefault("pink_concrete_wall", () ->
                    new WallBlock(ofConcrete(DyeColor.PINK)));

    public static final RegistryObject<Block> GRAY_CONCRETE_STAIRS =
            registerDefault("gray_concrete_stairs", () ->
                    new StairBlock(Blocks.GRAY_CONCRETE::defaultBlockState,
                            ofConcrete(DyeColor.GRAY)));

    public static final RegistryObject<Block> GRAY_CONCRETE_SLAB =
            registerDefault("gray_concrete_slab", () ->
                    new SlabBlock(ofConcrete(DyeColor.GRAY)));

    public static final RegistryObject<Block> GRAY_CONCRETE_WALL =
            registerDefault("gray_concrete_wall", () ->
                    new WallBlock(ofConcrete(DyeColor.GRAY)));

    public static final RegistryObject<Block> LIGHT_GRAY_CONCRETE_STAIRS =
            registerDefault("light_gray_concrete_stairs", () ->
                    new StairBlock(Blocks.LIGHT_GRAY_CONCRETE::defaultBlockState,
                            ofConcrete(DyeColor.LIGHT_GRAY)));

    public static final RegistryObject<Block> LIGHT_GRAY_CONCRETE_SLAB =
            registerDefault("light_gray_concrete_slab", () ->
                    new SlabBlock(ofConcrete(DyeColor.LIGHT_GRAY)));

    public static final RegistryObject<Block> LIGHT_GRAY_CONCRETE_WALL =
            registerDefault("light_gray_concrete_wall", () ->
                    new WallBlock(ofConcrete(DyeColor.LIGHT_GRAY)));

    public static final RegistryObject<Block> CYAN_CONCRETE_STAIRS =
            registerDefault("cyan_concrete_stairs", () ->
                    new StairBlock(Blocks.CYAN_CONCRETE::defaultBlockState,
                            ofConcrete(DyeColor.CYAN)));

    public static final RegistryObject<Block> CYAN_CONCRETE_SLAB =
            registerDefault("cyan_concrete_slab", () ->
                    new SlabBlock(ofConcrete(DyeColor.CYAN)));

    public static final RegistryObject<Block> CYAN_CONCRETE_WALL =
            registerDefault("cyan_concrete_wall", () ->
                    new WallBlock(ofConcrete(DyeColor.CYAN)));

    public static final RegistryObject<Block> PURPLE_CONCRETE_STAIRS =
            registerDefault("purple_concrete_stairs", () ->
                    new StairBlock(Blocks.PURPLE_CONCRETE::defaultBlockState,
                            ofConcrete(DyeColor.PURPLE)));

    public static final RegistryObject<Block> PURPLE_CONCRETE_SLAB =
            registerDefault("purple_concrete_slab", () ->
                    new SlabBlock(ofConcrete(DyeColor.PURPLE)));

    public static final RegistryObject<Block> PURPLE_CONCRETE_WALL =
            registerDefault("purple_concrete_wall", () ->
                    new WallBlock(ofConcrete(DyeColor.PURPLE)));

    public static final RegistryObject<Block> BLUE_CONCRETE_STAIRS =
            registerDefault("blue_concrete_stairs", () ->
                    new StairBlock(Blocks.BLUE_CONCRETE::defaultBlockState,
                            ofConcrete(DyeColor.BLUE)));

    public static final RegistryObject<Block> BLUE_CONCRETE_SLAB =
            registerDefault("blue_concrete_slab", () ->
                    new SlabBlock(ofConcrete(DyeColor.BLUE)));

    public static final RegistryObject<Block> BLUE_CONCRETE_WALL =
            registerDefault("blue_concrete_wall", () ->
                    new WallBlock(ofConcrete(DyeColor.BLUE)));

    public static final RegistryObject<Block> BROWN_CONCRETE_STAIRS =
            registerDefault("brown_concrete_stairs", () ->
                    new StairBlock(Blocks.BROWN_CONCRETE::defaultBlockState,
                            ofConcrete(DyeColor.BROWN)));

    public static final RegistryObject<Block> BROWN_CONCRETE_SLAB =
            registerDefault("brown_concrete_slab", () ->
                    new SlabBlock(ofConcrete(DyeColor.BROWN)));

    public static final RegistryObject<Block> BROWN_CONCRETE_WALL =
            registerDefault("brown_concrete_wall", () ->
                    new WallBlock(ofConcrete(DyeColor.BROWN)));

    public static final RegistryObject<Block> GREEN_CONCRETE_STAIRS =
            registerDefault("green_concrete_stairs", () ->
                    new StairBlock(Blocks.GREEN_CONCRETE::defaultBlockState,
                            ofConcrete(DyeColor.GREEN)));

    public static final RegistryObject<Block> GREEN_CONCRETE_SLAB =
            registerDefault("green_concrete_slab", () ->
                    new SlabBlock(ofConcrete(DyeColor.GREEN)));

    public static final RegistryObject<Block> GREEN_CONCRETE_WALL =
            registerDefault("green_concrete_wall", () ->
                    new WallBlock(ofConcrete(DyeColor.GREEN)));

    public static final RegistryObject<Block> RED_CONCRETE_STAIRS =
            registerDefault("red_concrete_stairs", () ->
                    new StairBlock(Blocks.RED_CONCRETE::defaultBlockState,
                            ofConcrete(DyeColor.RED)));

    public static final RegistryObject<Block> RED_CONCRETE_SLAB =
            registerDefault("red_concrete_slab", () ->
                    new SlabBlock(ofConcrete(DyeColor.RED)));

    public static final RegistryObject<Block> RED_CONCRETE_WALL =
            registerDefault("red_concrete_wall", () ->
                    new WallBlock(ofConcrete(DyeColor.RED)));

    public static final RegistryObject<Block> BLACK_CONCRETE_STAIRS =
            registerDefault("black_concrete_stairs", () ->
                    new StairBlock(Blocks.BLACK_CONCRETE::defaultBlockState,
                            ofConcrete(DyeColor.BLACK)));

    public static final RegistryObject<Block> BLACK_CONCRETE_SLAB =
            registerDefault("black_concrete_slab", () ->
                    new SlabBlock(ofConcrete(DyeColor.BLACK)));

    public static final RegistryObject<Block> BLACK_CONCRETE_WALL =
            registerDefault("black_concrete_wall", () ->
                    new WallBlock(ofConcrete(DyeColor.BLACK)));

    public static final RegistryObject<Block> GLISTERING_MELON =
            registerDefault("glistering_melon", () ->
                    new Block(BlockBehaviour.Properties.of(Material.VEGETABLE,
                    MaterialColor.TERRACOTTA_YELLOW)
                    .strength(1.0F)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> WHITE_TERRACOTTA_STAIRS =
            registerDefault("white_terracotta_stairs", () ->
                    new StairBlock(Blocks.WHITE_TERRACOTTA::defaultBlockState,
                            ofTerracotta(MaterialColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<Block> WHITE_TERRACOTTA_SLAB =
            registerDefault("white_terracotta_slab", () ->
                    new SlabBlock(ofTerracotta(MaterialColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<Block> WHITE_TERRACOTTA_WALL =
            registerDefault("white_terracotta_wall", () ->
                    new WallBlock(ofTerracotta(MaterialColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<Block> ORANGE_TERRACOTTA_STAIRS =
            registerDefault("orange_terracotta_stairs", () ->
                    new StairBlock(Blocks.ORANGE_TERRACOTTA::defaultBlockState,
                            ofTerracotta(MaterialColor.TERRACOTTA_ORANGE)));

    public static final RegistryObject<Block> ORANGE_TERRACOTTA_SLAB =
            registerDefault("orange_terracotta_slab", () ->
                    new SlabBlock(ofTerracotta(MaterialColor.TERRACOTTA_ORANGE)));

    public static final RegistryObject<Block> ORANGE_TERRACOTTA_WALL =
            registerDefault("orange_terracotta_wall", () ->
                    new WallBlock(ofTerracotta(MaterialColor.TERRACOTTA_ORANGE)));

    public static final RegistryObject<Block> MAGENTA_TERRACOTTA_STAIRS =
            registerDefault("magenta_terracotta_stairs", () ->
                    new StairBlock(Blocks.MAGENTA_TERRACOTTA::defaultBlockState,
                            ofTerracotta(MaterialColor.TERRACOTTA_MAGENTA)));

    public static final RegistryObject<Block> MAGENTA_TERRACOTTA_SLAB =
            registerDefault("magenta_terracotta_slab", () ->
                    new SlabBlock(ofTerracotta(MaterialColor.TERRACOTTA_MAGENTA)));

    public static final RegistryObject<Block> MAGENTA_TERRACOTTA_WALL =
            registerDefault("magenta_terracotta_wall", () ->
                    new WallBlock(ofTerracotta(MaterialColor.TERRACOTTA_MAGENTA)));

    public static final RegistryObject<Block> LIGHT_BLUE_TERRACOTTA_STAIRS =
            registerDefault("light_blue_terracotta_stairs", () ->
                    new StairBlock(Blocks.LIGHT_BLUE_TERRACOTTA::defaultBlockState,
                            ofTerracotta(MaterialColor.TERRACOTTA_LIGHT_BLUE)));

    public static final RegistryObject<Block> LIGHT_BLUE_TERRACOTTA_SLAB =
            registerDefault("light_blue_terracotta_slab", () ->
                    new SlabBlock(ofTerracotta(MaterialColor.TERRACOTTA_LIGHT_BLUE)));

    public static final RegistryObject<Block> LIGHT_BLUE_TERRACOTTA_WALL =
            registerDefault("light_blue_terracotta_wall", () ->
                    new WallBlock(ofTerracotta(MaterialColor.TERRACOTTA_LIGHT_BLUE)));

    public static final RegistryObject<Block> YELLOW_TERRACOTTA_STAIRS =
            registerDefault("yellow_terracotta_stairs", () ->
                    new StairBlock(Blocks.YELLOW_TERRACOTTA::defaultBlockState,
                            ofTerracotta(MaterialColor.TERRACOTTA_YELLOW)));

    public static final RegistryObject<Block> YELLOW_TERRACOTTA_SLAB =
            registerDefault("yellow_terracotta_slab", () ->
                    new SlabBlock(ofTerracotta(MaterialColor.TERRACOTTA_YELLOW)));

    public static final RegistryObject<Block> YELLOW_TERRACOTTA_WALL =
            registerDefault("yellow_terracotta_wall", () ->
                    new WallBlock(ofTerracotta(MaterialColor.TERRACOTTA_YELLOW)));

    public static final RegistryObject<Block> LIME_TERRACOTTA_STAIRS =
            registerDefault("lime_terracotta_stairs", () ->
                    new StairBlock(Blocks.LIME_TERRACOTTA::defaultBlockState,
                            ofTerracotta(MaterialColor.TERRACOTTA_LIGHT_GREEN)));

    public static final RegistryObject<Block> LIME_TERRACOTTA_SLAB =
            registerDefault("lime_terracotta_slab", () ->
                    new SlabBlock(ofTerracotta(MaterialColor.TERRACOTTA_LIGHT_GREEN)));

    public static final RegistryObject<Block> LIME_TERRACOTTA_WALL =
            registerDefault("lime_terracotta_wall", () ->
                    new WallBlock(ofTerracotta(MaterialColor.TERRACOTTA_LIGHT_GREEN)));

    public static final RegistryObject<Block> PINK_TERRACOTTA_STAIRS =
            registerDefault("pink_terracotta_stairs", () ->
                    new StairBlock(Blocks.PINK_TERRACOTTA::defaultBlockState,
                            ofTerracotta(MaterialColor.TERRACOTTA_PINK)));

    public static final RegistryObject<Block> PINK_TERRACOTTA_SLAB =
            registerDefault("pink_terracotta_slab", () ->
                    new SlabBlock(ofTerracotta(MaterialColor.TERRACOTTA_PINK)));

    public static final RegistryObject<Block> PINK_TERRACOTTA_WALL =
            registerDefault("pink_terracotta_wall", () ->
                    new WallBlock(ofTerracotta(MaterialColor.TERRACOTTA_PINK)));

    public static final RegistryObject<Block> GRAY_TERRACOTTA_STAIRS =
            registerDefault("gray_terracotta_stairs", () ->
                    new StairBlock(Blocks.GRAY_TERRACOTTA::defaultBlockState,
                            ofTerracotta(MaterialColor.TERRACOTTA_GRAY)));

    public static final RegistryObject<Block> GRAY_TERRACOTTA_SLAB =
            registerDefault("gray_terracotta_slab", () ->
                    new SlabBlock(ofTerracotta(MaterialColor.TERRACOTTA_GRAY)));

    public static final RegistryObject<Block> GRAY_TERRACOTTA_WALL =
            registerDefault("gray_terracotta_wall", () ->
                    new WallBlock(ofTerracotta(MaterialColor.TERRACOTTA_GRAY)));

    public static final RegistryObject<Block> LIGHT_GRAY_TERRACOTTA_STAIRS =
            registerDefault("light_gray_terracotta_stairs", () ->
                    new StairBlock(Blocks.LIGHT_GRAY_TERRACOTTA::defaultBlockState,
                            ofTerracotta(MaterialColor.TERRACOTTA_LIGHT_GRAY)));

    public static final RegistryObject<Block> LIGHT_GRAY_TERRACOTTA_SLAB =
            registerDefault("light_gray_terracotta_slab", () ->
                    new SlabBlock(ofTerracotta(MaterialColor.TERRACOTTA_LIGHT_GRAY)));

    public static final RegistryObject<Block> LIGHT_GRAY_TERRACOTTA_WALL =
            registerDefault("light_gray_terracotta_wall", () ->
                    new WallBlock(ofTerracotta(MaterialColor.TERRACOTTA_LIGHT_GRAY)));

    public static final RegistryObject<Block> CYAN_TERRACOTTA_STAIRS =
            registerDefault("cyan_terracotta_stairs", () ->
                    new StairBlock(Blocks.CYAN_TERRACOTTA::defaultBlockState,
                            ofTerracotta(MaterialColor.TERRACOTTA_CYAN)));

    public static final RegistryObject<Block> CYAN_TERRACOTTA_SLAB =
            registerDefault("cyan_terracotta_slab", () ->
                    new SlabBlock(ofTerracotta(MaterialColor.TERRACOTTA_CYAN)));

    public static final RegistryObject<Block> CYAN_TERRACOTTA_WALL =
            registerDefault("cyan_terracotta_wall", () ->
                    new WallBlock(ofTerracotta(MaterialColor.TERRACOTTA_CYAN)));

    public static final RegistryObject<Block> PURPLE_TERRACOTTA_STAIRS =
            registerDefault("purple_terracotta_stairs", () ->
                    new StairBlock(Blocks.PURPLE_TERRACOTTA::defaultBlockState,
                            ofTerracotta(MaterialColor.TERRACOTTA_PURPLE)));

    public static final RegistryObject<Block> PURPLE_TERRACOTTA_SLAB =
            registerDefault("purple_terracotta_slab", () ->
                    new SlabBlock(ofTerracotta(MaterialColor.TERRACOTTA_PURPLE)));

    public static final RegistryObject<Block> PURPLE_TERRACOTTA_WALL =
            registerDefault("purple_terracotta_wall", () ->
                    new WallBlock(ofTerracotta(MaterialColor.TERRACOTTA_PURPLE)));

    public static final RegistryObject<Block> BLUE_TERRACOTTA_STAIRS =
            registerDefault("blue_terracotta_stairs", () ->
                    new StairBlock(Blocks.BLUE_TERRACOTTA::defaultBlockState,
                            ofTerracotta(MaterialColor.TERRACOTTA_BLUE)));

    public static final RegistryObject<Block> BLUE_TERRACOTTA_SLAB =
            registerDefault("blue_terracotta_slab", () ->
                    new SlabBlock(ofTerracotta(MaterialColor.TERRACOTTA_BLUE)));

    public static final RegistryObject<Block> BLUE_TERRACOTTA_WALL =
            registerDefault("blue_terracotta_wall", () ->
                    new WallBlock(ofTerracotta(MaterialColor.TERRACOTTA_BLUE)));

    public static final RegistryObject<Block> BROWN_TERRACOTTA_STAIRS =
            registerDefault("brown_terracotta_stairs", () ->
                    new StairBlock(Blocks.BROWN_TERRACOTTA::defaultBlockState,
                            ofTerracotta(MaterialColor.TERRACOTTA_BROWN)));

    public static final RegistryObject<Block> BROWN_TERRACOTTA_SLAB =
            registerDefault("brown_terracotta_slab", () ->
                    new SlabBlock(ofTerracotta(MaterialColor.TERRACOTTA_BROWN)));

    public static final RegistryObject<Block> BROWN_TERRACOTTA_WALL =
            registerDefault("brown_terracotta_wall", () ->
                    new WallBlock(ofTerracotta(MaterialColor.TERRACOTTA_BROWN)));

    public static final RegistryObject<Block> GREEN_TERRACOTTA_STAIRS =
            registerDefault("green_terracotta_stairs", () ->
                    new StairBlock(Blocks.GREEN_TERRACOTTA::defaultBlockState,
                            ofTerracotta(MaterialColor.TERRACOTTA_GREEN)));

    public static final RegistryObject<Block> GREEN_TERRACOTTA_SLAB =
            registerDefault("green_terracotta_slab", () ->
                    new SlabBlock(ofTerracotta(MaterialColor.TERRACOTTA_GREEN)));

    public static final RegistryObject<Block> GREEN_TERRACOTTA_WALL =
            registerDefault("green_terracotta_wall", () ->
                    new WallBlock(ofTerracotta(MaterialColor.TERRACOTTA_GREEN)));

    public static final RegistryObject<Block> RED_TERRACOTTA_STAIRS =
            registerDefault("red_terracotta_stairs", () ->
                    new StairBlock(Blocks.RED_TERRACOTTA::defaultBlockState,
                            ofTerracotta(MaterialColor.TERRACOTTA_RED)));

    public static final RegistryObject<Block> RED_TERRACOTTA_SLAB =
            registerDefault("red_terracotta_slab", () ->
                    new SlabBlock(ofTerracotta(MaterialColor.TERRACOTTA_RED)));

    public static final RegistryObject<Block> RED_TERRACOTTA_WALL =
            registerDefault("red_terracotta_wall", () ->
                    new WallBlock(ofTerracotta(MaterialColor.TERRACOTTA_RED)));

    public static final RegistryObject<Block> BLACK_TERRACOTTA_STAIRS =
            registerDefault("black_terracotta_stairs", () ->
                    new StairBlock(Blocks.BLACK_TERRACOTTA::defaultBlockState,
                            ofTerracotta(MaterialColor.TERRACOTTA_BLACK)));

    public static final RegistryObject<Block> BLACK_TERRACOTTA_SLAB =
            registerDefault("black_terracotta_slab", () ->
                    new SlabBlock(ofTerracotta(MaterialColor.TERRACOTTA_BLACK)));

    public static final RegistryObject<Block> BLACK_TERRACOTTA_WALL =
            registerDefault("black_terracotta_wall", () ->
                    new WallBlock(ofTerracotta(MaterialColor.TERRACOTTA_BLACK)));

    public static final RegistryObject<Block> ANDESITE_BRICKS =
            registerDefault("andesite_bricks", () ->
                    new StoneLikeBlock(SoundType.STONE, MaterialColor.STONE));

    public static final RegistryObject<Block> ANDESITE_BRICKS_STAIRS =
            registerDefault("andesite_bricks_stairs", () ->
                    new StairBlock(ANDESITE_BRICKS.get()::defaultBlockState,
                            ofStone()));

    public static final RegistryObject<Block> ANDESITE_BRICKS_SLAB =
            registerDefault("andesite_bricks_slab", () ->
                    new SlabBlock(ofStone()));

    public static final RegistryObject<Block> ANDESITE_BRICKS_WALL =
            registerDefault("andesite_bricks_wall", () ->
                    new WallBlock(ofStone()));

    public static final RegistryObject<Block> CRACKED_ANDESITE_BRICKS =
            registerDefault("cracked_andesite_bricks", () ->
                    new StoneLikeBlock(SoundType.STONE, MaterialColor.STONE));

    public static final RegistryObject<Block> CRACKED_ANDESITE_BRICKS_STAIRS =
            registerDefault("cracked_andesite_bricks_stairs", () ->
                    new StairBlock(CRACKED_ANDESITE_BRICKS.get()::defaultBlockState,
                            ofStone()));

    public static final RegistryObject<Block> CRACKED_ANDESITE_BRICKS_SLAB =
            registerDefault("cracked_andesite_bricks_slab", () ->
                    new SlabBlock(ofStone()));

    public static final RegistryObject<Block> CRACKED_ANDESITE_BRICKS_WALL =
            registerDefault("cracked_andesite_bricks_wall", () ->
                    new WallBlock(ofStone()));

    public static final RegistryObject<Block> MOSSY_ANDESITE_BRICKS =
            registerDefault("mossy_andesite_bricks", () ->
                    new StoneLikeBlock(SoundType.STONE, MaterialColor.STONE));

    public static final RegistryObject<Block> MOSSY_ANDESITE_BRICKS_STAIRS =
            registerDefault("mossy_andesite_bricks_stairs", () ->
                    new StairBlock(MOSSY_ANDESITE_BRICKS.get()::defaultBlockState,
                            ofStone()));

    public static final RegistryObject<Block> MOSSY_ANDESITE_BRICKS_SLAB =
            registerDefault("mossy_andesite_bricks_slab", () ->
                    new SlabBlock(ofStone()));

    public static final RegistryObject<Block> MOSSY_ANDESITE_BRICKS_WALL =
            registerDefault("mossy_andesite_bricks_wall", () ->
                    new WallBlock(ofStone()));

    public static final RegistryObject<Block> CHISELED_ANDESITE_BRICKS =
            registerDefault("chiseled_andesite_bricks", () ->
                    new StoneLikeBlock(SoundType.STONE, MaterialColor.STONE));

    public static final RegistryObject<Block> GRANITE_BRICKS =
            registerDefault("granite_bricks", () ->
                    new StoneLikeBlock(SoundType.STONE, MaterialColor.STONE));

    public static final RegistryObject<Block> CRACKED_GRANITE_BRICKS =
            registerDefault("cracked_granite_bricks", () ->
                    new StoneLikeBlock(SoundType.STONE, MaterialColor.STONE));

    public static final RegistryObject<Block> MOSSY_GRANITE_BRICKS =
            registerDefault("mossy_granite_bricks", () ->
                    new StoneLikeBlock(SoundType.STONE, MaterialColor.STONE));

    public static final RegistryObject<Block> CHISELED_GRANITE_BRICKS =
            registerDefault("chiseled_granite_bricks", () ->
                    new StoneLikeBlock(SoundType.STONE, MaterialColor.STONE));

    public static final RegistryObject<Block> GRANITE_BRICKS_STAIRS =
            registerDefault("granite_bricks_stairs", () ->
                    new StairBlock(GRANITE_BRICKS.get()::defaultBlockState,
                            ofStone()));

    public static final RegistryObject<Block> GRANITE_BRICKS_SLAB =
            registerDefault("granite_bricks_slab", () ->
                    new SlabBlock(ofStone()));

    public static final RegistryObject<Block> GRANITE_BRICKS_WALL =
            registerDefault("granite_bricks_wall", () ->
                    new WallBlock(ofStone()));

    public static final RegistryObject<Block> CRACKED_GRANITE_BRICKS_STAIRS =
            registerDefault("cracked_granite_bricks_stairs", () ->
                    new StairBlock(CRACKED_GRANITE_BRICKS.get()::defaultBlockState,
                            ofStone()));

    public static final RegistryObject<Block> CRACKED_GRANITE_BRICKS_SLAB =
            registerDefault("cracked_granite_bricks_slab", () ->
                    new SlabBlock(ofStone()));

    public static final RegistryObject<Block> CRACKED_GRANITE_BRICKS_WALL =
            registerDefault("cracked_granite_bricks_wall", () ->
                    new WallBlock(ofStone()));

    public static final RegistryObject<Block> MOSSY_GRANITE_BRICKS_STAIRS =
            registerDefault("mossy_granite_bricks_stairs", () ->
                    new StairBlock(MOSSY_GRANITE_BRICKS.get()::defaultBlockState,
                            ofStone()));

    public static final RegistryObject<Block> MOSSY_GRANITE_BRICKS_SLAB =
            registerDefault("mossy_granite_bricks_slab", () ->
                    new SlabBlock(ofStone()));

    public static final RegistryObject<Block> MOSSY_GRANITE_BRICKS_WALL =
            registerDefault("mossy_granite_bricks_wall", () ->
                    new WallBlock(ofStone()));

    public static final RegistryObject<Block> DIORITE_BRICKS =
            registerDefault("diorite_bricks", () ->
                    new StoneLikeBlock(SoundType.STONE, MaterialColor.STONE));

    public static final RegistryObject<Block> CRACKED_DIORITE_BRICKS =
            registerDefault("cracked_diorite_bricks", () ->
                    new StoneLikeBlock(SoundType.STONE, MaterialColor.STONE));

    public static final RegistryObject<Block> MOSSY_DIORITE_BRICKS =
            registerDefault("mossy_diorite_bricks", () ->
                    new StoneLikeBlock(SoundType.STONE, MaterialColor.STONE));

    public static final RegistryObject<Block> CHISELED_DIORITE_BRICKS =
            registerDefault("chiseled_diorite_bricks", () ->
                    new StoneLikeBlock(SoundType.STONE, MaterialColor.STONE));

    public static final RegistryObject<Block> DIORITE_BRICKS_STAIRS =
            registerDefault("diorite_bricks_stairs", () ->
                    new StairBlock(DIORITE_BRICKS.get()::defaultBlockState,
                            ofStone()));

    public static final RegistryObject<Block> DIORITE_BRICKS_SLAB =
            registerDefault("diorite_bricks_slab", () ->
                    new SlabBlock(ofStone()));

    public static final RegistryObject<Block> DIORITE_BRICKS_WALL =
            registerDefault("diorite_bricks_wall", () ->
                    new WallBlock(ofStone()));

    public static final RegistryObject<Block> CRACKED_DIORITE_BRICKS_STAIRS =
            registerDefault("cracked_diorite_bricks_stairs", () ->
                    new StairBlock(CRACKED_DIORITE_BRICKS.get()::defaultBlockState,
                            ofStone()));

    public static final RegistryObject<Block> CRACKED_DIORITE_BRICKS_SLAB =
            registerDefault("cracked_diorite_bricks_slab", () ->
                    new SlabBlock(ofStone()));

    public static final RegistryObject<Block> CRACKED_DIORITE_BRICKS_WALL =
            registerDefault("cracked_diorite_bricks_wall", () ->
                    new WallBlock(ofStone()));

    public static final RegistryObject<Block> MOSSY_DIORITE_BRICKS_STAIRS =
            registerDefault("mossy_diorite_bricks_stairs", () ->
                    new StairBlock(MOSSY_DIORITE_BRICKS.get()::defaultBlockState,
                            ofStone()));

    public static final RegistryObject<Block> MOSSY_DIORITE_BRICKS_SLAB =
            registerDefault("mossy_diorite_bricks_slab", () ->
                    new SlabBlock(ofStone()));

    public static final RegistryObject<Block> MOSSY_DIORITE_BRICKS_WALL =
            registerDefault("mossy_diorite_bricks_wall", () ->
                    new WallBlock(ofStone()));

    public static final RegistryObject<Block> MOSSY_COBBLED_DEEPSLATE =
            registerDefault("mossy_cobbled_deepslate", () ->
                    new StoneLikeBlock(SoundType.DEEPSLATE, MaterialColor.DEEPSLATE));

    public static final RegistryObject<Block> MOSSY_DEEPSLATE_BRICKS =
            registerDefault("mossy_deepslate_bricks", () ->
                    new StoneLikeBlock(SoundType.DEEPSLATE, MaterialColor.DEEPSLATE));

    public static final RegistryObject<Block> MOSSY_DEEPSLATE_TILES =
            registerDefault("mossy_deepslate_tiles", () ->
                    new StoneLikeBlock(SoundType.DEEPSLATE, MaterialColor.DEEPSLATE));

    public static final RegistryObject<Block> MOSSY_DEEPSLATE_BRICKS_STAIRS =
            registerDefault("mossy_deepslate_bricks_stairs", () ->
                    new StairBlock(MOSSY_DEEPSLATE_BRICKS.get()::defaultBlockState,
                            ofStone().sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> MOSSY_DEEPSLATE_BRICKS_SLAB =
            registerDefault("mossy_deepslate_bricks_slab", () ->
                    new SlabBlock(ofStone().sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> MOSSY_DEEPSLATE_BRICKS_WALL =
            registerDefault("mossy_deepslate_bricks_wall", () ->
                    new WallBlock(ofStone().sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> MOSSY_COBBLED_DEEPSLATE_STAIRS =
            registerDefault("mossy_cobbled_deepslate_stairs", () ->
                    new StairBlock(MOSSY_COBBLED_DEEPSLATE.get()::defaultBlockState,
                            ofStone().sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> MOSSY_COBBLED_DEEPSLATE_SLAB =
            registerDefault("mossy_cobbled_deepslate_slab", () ->
                    new SlabBlock(ofStone().sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> MOSSY_COBBLED_DEEPSLATE_WALL =
            registerDefault("mossy_cobbled_deepslate_wall", () ->
                    new WallBlock(ofStone().sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> MOSSY_DEEPSLATE_TILES_STAIRS =
            registerDefault("mossy_deepslate_tiles_stairs", () ->
                    new StairBlock(MOSSY_DEEPSLATE_TILES.get()::defaultBlockState,
                            ofStone().sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> MOSSY_DEEPSLATE_TILES_SLAB =
            registerDefault("mossy_deepslate_tiles_slab", () ->
                    new SlabBlock(ofStone().sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> MOSSY_DEEPSLATE_TILES_WALL =
            registerDefault("mossy_deepslate_tiles_wall", () ->
                    new WallBlock(ofStone().sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> CACTUS_FRUIT =
            register("cactus_fruit", CactusFruitBlock::new);

    public static final RegistryObject<Block> SULFUR_BLOCK =
            registerDefault("sulfur_block", () ->
                    new Block(BlockBehaviour.Properties.of(Material.METAL)
                            .strength(2.0F, 3.0F).requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)));

    public static final RegistryObject<Block> NITER_BLOCK =
            registerDefault("niter_block", () ->
                    new Block(BlockBehaviour.Properties.of(Material.METAL)
                            .strength(2.0F, 3.0F).requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)));

    public static final RegistryObject<Block> SANDSTONE_NITER_ORE =
            registerDefault("sandstone_niter_ore", () ->
                    new DropExperienceBlock(BlockBehaviour.Properties.of(Material.METAL)
                            .strength(2.0F, 3.0F).requiresCorrectToolForDrops()
                            .sound(SoundType.STONE),
                            UniformInt.of(1, 5)));

    public static final RegistryObject<Block> QUARTZ_SANDSTONE_NITER_ORE =
            registerDefault("quartz_sandstone_niter_ore", () ->
                    new DropExperienceBlock(BlockBehaviour.Properties.of(Material.METAL)
                            .strength(2.0F, 3.0F).requiresCorrectToolForDrops()
                            .sound(SoundType.STONE),
                            UniformInt.of(1, 5)));

    public static final RegistryObject<Block> RED_SANDSTONE_NITER_ORE =
            registerDefault("red_sandstone_niter_ore", () ->
                    new DropExperienceBlock(BlockBehaviour.Properties.of(Material.METAL)
                            .strength(2.0F, 3.0F).requiresCorrectToolForDrops()
                            .sound(SoundType.STONE),
                            UniformInt.of(1, 5)));

    public static final RegistryObject<Block> SOUL_SANDSTONE_NITER_ORE =
            registerDefault("soul_sandstone_niter_ore", () ->
                    new DropExperienceBlock(BlockBehaviour.Properties.of(Material.METAL)
                            .strength(2.0F, 3.0F).requiresCorrectToolForDrops()
                            .sound(SoundType.STONE),
                            UniformInt.of(1, 5)));

    public static final RegistryObject<Block> CATTAIL =
            registerDefault("cattail", CattailBlock::new);

    public static final RegistryObject<Block> THATCH =
            registerDefault("thatch", ThatchBlock::new);

    public static final RegistryObject<Block> DRIED_THATCH =
            registerDefault("dried_thatch", DriedThatchBlock::new);

    public static final RegistryObject<Block> THATCH_CARPET =
            registerDefault("thatch_carpet", ThatchCarpetBlock::new);

    public static final RegistryObject<Block> DRIED_THATCH_CARPET =
            registerDefault("dried_thatch_carpet", DriedThatchCarpetBlock::new);

    public static final RegistryObject<Block> THATCH_SLAB =
            registerDefault("thatch_slab", ThatchSlabBlock::new);

    public static final RegistryObject<Block> DRIED_THATCH_SLAB =
            registerDefault("dried_thatch_slab", DriedThatchSlabBlock::new);

    public static final RegistryObject<Block> THATCH_STAIRS =
            registerDefault("thatch_stairs", ThatchStairBlock::new);

    public static final RegistryObject<Block> DRIED_THATCH_STAIRS =
            registerDefault("dried_thatch_stairs", DriedThatchStairBlock::new);

    public static final RegistryObject<Block> REED =
            registerDefault("reed", ReedBlock::new);

    public static final RegistryObject<Block> POLISHED_ANDESITE_BUTTON =
            registerDefault("polished_andesite_button", ModBlocks::stoneButton);

    public static final RegistryObject<Block> POLISHED_DIORITE_BUTTON =
            registerDefault("polished_diorite_button", ModBlocks::stoneButton);

    public static final RegistryObject<Block> SMOOTH_STONE_BUTTON =
            registerDefault("smooth_stone_button", ModBlocks::stoneButton);
    public static final RegistryObject<Block> BLACKSTONE_BUTTON =
            registerDefault("blackstone_button", ModBlocks::stoneButton);

    public static final RegistryObject<Block> POLISHED_GRANITE_BUTTON =
            registerDefault("polished_granite_button", ModBlocks::stoneButton);

    public static final RegistryObject<Block> DEEPSLATE_BUTTON =
            registerDefault("deepslate_button", ModBlocks::stoneButton);
    public static final RegistryObject<Block> POLISHED_DEEPSLATE_BUTTON =
            registerDefault("polished_deepslate_button", ModBlocks::stoneButton);
    public static final RegistryObject<Block> DEEPSLATE_PRESSURE_PLATE =
            registerDefault("deepslate_pressure_plate", () ->
                    pressurePlate(SoundType.DEEPSLATE));

    public static final RegistryObject<Block>  POLISHED_DEEPSLATE_PRESSURE_PLATE =
            registerDefault("polished_deepslate_pressure_plate", () ->
                    pressurePlate(SoundType.DEEPSLATE));

    public static final RegistryObject<Block> POLISHED_ANDESITE_PRESSURE_PLATE =
            registerDefault("polished_andesite_pressure_plate",  () ->
                    pressurePlate(SoundType.STONE));
    public static final RegistryObject<Block> POLISHED_DIORITE_PRESSURE_PLATE =
            registerDefault("polished_diorite_pressure_plate",  () ->
                    pressurePlate(SoundType.STONE));

    public static final RegistryObject<Block> POLISHED_GRANITE_PRESSURE_PLATE =
            registerDefault("polished_granite_pressure_plate",  () ->
                    pressurePlate(SoundType.STONE));
    public static final RegistryObject<Block> SMOOTH_STONE_PRESSURE_PLATE =
            registerDefault("smooth_stone_pressure_plate",  () ->
                    pressurePlate(SoundType.STONE));
    public static final RegistryObject<Block> BLACKSTONE_PRESSURE_PLATE =
            registerDefault("blackstone_pressure_plate",  () ->
                    pressurePlate(SoundType.STONE));

    public static final RegistryObject<Block> CHISELED_DEEPSLATE_BRICKS =
            registerDefault("chiseled_deepslate_bricks", () ->
                    new StoneLikeBlock(SoundType.DEEPSLATE, MaterialColor.DEEPSLATE));

    public static final RegistryObject<Block> CHISELED_DEEPSLATE_TILES =
            registerDefault("chiseled_deepslate_tiles", () ->
                    new StoneLikeBlock(SoundType.DEEPSLATE, MaterialColor.DEEPSLATE));

    public static final RegistryObject<Block> CHISELED_POLISHED_DEEPSLATE =
            registerDefault("chiseled_polished_deepslate", () ->
                    new StoneLikeBlock(SoundType.DEEPSLATE, MaterialColor.DEEPSLATE));

    public static final RegistryObject<Block> INFESTED_CHISELED_DEEPSLATE_TILES =
            registerDefault("infested_chiseled_deepslate_tiles", () ->
                    new InfestedBlock(ModBlocks.CHISELED_DEEPSLATE_TILES.get(),
                            BlockBehaviour.Properties.of(Material.CLAY)
                                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> INFESTED_CHISELED_DEEPSLATE_BRICKS =
            registerDefault("infested_chiseled_deepslate_bricks", () ->
                    new InfestedBlock(ModBlocks.CHISELED_DEEPSLATE_BRICKS.get(),
                            BlockBehaviour.Properties.of(Material.CLAY)
                                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> INFESTED_MOSSY_COBBLED_DEEPSLATE =
            registerDefault("infested_mossy_cobbled_deepslate", () ->
                    new InfestedBlock(ModBlocks.MOSSY_COBBLED_DEEPSLATE.get(),
                            BlockBehaviour.Properties.of(Material.CLAY)
                                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> INFESTED_MOSSY_DEEPSLATE_BRICKS =
            registerDefault("infested_mossy_deepslate_bricks", () ->
                    new InfestedBlock(ModBlocks.MOSSY_DEEPSLATE_BRICKS.get(),
                            BlockBehaviour.Properties.of(Material.CLAY)
                                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> INFESTED_MOSSY_DEEPSLATE_TILES =
            registerDefault("infested_mossy_deepslate_tiles", () ->
                    new InfestedBlock(ModBlocks.MOSSY_DEEPSLATE_TILES.get(),
                            BlockBehaviour.Properties.of(Material.CLAY)
                                    .sound(SoundType.DEEPSLATE)));

    private static PressurePlateBlock pressurePlate(SoundType soundType) {
        return new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS,
                BlockBehaviour.Properties.of(Material.STONE)
                .requiresCorrectToolForDrops().noCollission().strength(0.5F)
                        .sound(soundType), BlockSetType.STONE);
    }

    private static ButtonBlock stoneButton() {
        return new ButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F),
                BlockSetType.STONE, 20, false);
    }

    private static BlockBehaviour.Properties ofStone() {
        return BlockBehaviour.Properties
                .of(Material.STONE).requiresCorrectToolForDrops()
                .sound(SoundType.STONE)
                .strength(1.5F, 6.0F);
    }

    private static BlockBehaviour.Properties ofTerracotta(MaterialColor color) {
        return BlockBehaviour.Properties.of(Material.STONE, color)
                .requiresCorrectToolForDrops().strength(1.25F, 4.2F);
    }

    private static BlockBehaviour.Properties ofConcrete(DyeColor dyeColor) {
        return BlockBehaviour.Properties.of(Material.STONE, dyeColor)
                .requiresCorrectToolForDrops().strength(1.8F);
    }
    private static BlockBehaviour.Properties ofSandStone(MaterialColor color) {
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
