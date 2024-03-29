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
package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.block.StainedGlassSlabBlock;
import nameless.classicraft.block.StainedGlassStairBlock;
import nameless.classicraft.block.StainedGlassWallBlock;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.util.Helpers;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(DataGenerator generator, ExistingFileHelper exFileHelper) {
        super(generator.getPackOutput(), ClassiCraftMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(ModBlocks.FLINT_BLOCK);
        simpleBlockWithItem(ModBlocks.QUARTZ_QUICKSAND);
        simpleBlockWithItem(ModBlocks.QUARTZ_SAND);
        simpleBlockWithItem(ModBlocks.QUARTZ_SANDSTONE_BRICKS);
        simpleBlockWithItem(ModBlocks.RED_SANDSTONE_BRICKS);
        simpleBlockWithItem(ModBlocks.SANDSTONE_BRICKS);
        simpleBlockWithItem(ModBlocks.MOSSY_BRICKS);
        simpleBlockWithItem(ModBlocks.SOUL_QUICKSAND);
        simpleBlockWithItem(ModBlocks.SOUL_SANDSTONE_BRICKS);
        simpleBlockWithItem(ModBlocks.CRACKED_BRICKS);
        simpleBlockWithItem(ModBlocks.SMOOTH_QUARTZ_SANDSTONE);
        simpleBlockWithItem(ModBlocks.ANDESITE_BRICKS);
        simpleBlockWithItem(ModBlocks.CRACKED_ANDESITE_BRICKS);
        simpleBlockWithItem(ModBlocks.MOSSY_ANDESITE_BRICKS);
        simpleBlockWithItem(ModBlocks.CHISELED_ANDESITE_BRICKS);
        simpleBlockWithItem(ModBlocks.GRANITE_BRICKS);
        simpleBlockWithItem(ModBlocks.CRACKED_GRANITE_BRICKS);
        simpleBlockWithItem(ModBlocks.MOSSY_GRANITE_BRICKS);
        simpleBlockWithItem(ModBlocks.CHISELED_GRANITE_BRICKS);
        simpleBlockWithItem(ModBlocks.DIORITE_BRICKS);
        simpleBlockWithItem(ModBlocks.CRACKED_DIORITE_BRICKS);
        simpleBlockWithItem(ModBlocks.MOSSY_DIORITE_BRICKS);
        simpleBlockWithItem(ModBlocks.CHISELED_DIORITE_BRICKS);
        simpleBlockWithItem(ModBlocks.MOSSY_DEEPSLATE_BRICKS);
        simpleBlockWithItem(ModBlocks.MOSSY_COBBLED_DEEPSLATE);
        simpleBlockWithItem(ModBlocks.MOSSY_DEEPSLATE_TILES);
        simpleBlockWithItem(ModBlocks.DEEPSLATE_SULFUR_ORE);
        simpleBlockWithItem(ModBlocks.NETHER_SULFUR_ORE);
        simpleBlockWithItem(ModBlocks.SULFUR_BLOCK);
        simpleBlockWithItem(ModBlocks.NITER_BLOCK);
        simpleBlockWithItem(ModBlocks.CHISELED_DEEPSLATE_BRICKS);
        simpleBlockWithItem(ModBlocks.CHISELED_DEEPSLATE_TILES);
        simpleBlockWithItem(ModBlocks.CHISELED_POLISHED_DEEPSLATE);
        vanillaSimpleBlockWithItem(ModBlocks.INFESTED_MOSSY_COBBLESTONE, "mossy_cobblestone");
        vanillaSimpleBlockWithItem(ModBlocks.INFESTED_CHISELED_DEEPSLATE, "chiseled_deepslate");
        vanillaSimpleBlockWithItem(ModBlocks.INFESTED_COBBLED_DEEPSLATE, "cobbled_deepslate");
        vanillaSimpleBlockWithItem(ModBlocks.INFESTED_DEEPSLATE_BRICKS, "deepslate_bricks");
        vanillaSimpleBlockWithItem(ModBlocks.INFESTED_DEEPSLATE_TILES, "deepslate_tiles");
        vanillaSimpleBlockWithItem(ModBlocks.INFESTED_CRACKED_DEEPSLATE_BRICKS, "cracked_deepslate_bricks");
        vanillaSimpleBlockWithItem(ModBlocks.INFESTED_CRACKED_DEEPSLATE_TILES, "cracked_deepslate_tiles");
        modSimpleBlockWithItem(ModBlocks.INFESTED_CHISELED_DEEPSLATE_BRICKS, "chiseled_deepslate_bricks");
        modSimpleBlockWithItem(ModBlocks.INFESTED_CHISELED_DEEPSLATE_TILES, "chiseled_deepslate_tiles");
        modSimpleBlockWithItem(ModBlocks.INFESTED_MOSSY_COBBLED_DEEPSLATE, "mossy_cobbled_deepslate");
        modSimpleBlockWithItem(ModBlocks.INFESTED_MOSSY_DEEPSLATE_TILES, "mossy_deepslate_tiles");
        modSimpleBlockWithItem(ModBlocks.INFESTED_MOSSY_DEEPSLATE_BRICKS, "mossy_deepslate_bricks");
        simpleBlockWithItem(ModBlocks.SMOOTH_SOUL_SANDSTONE);
        sandstoneBlockWithItem(ModBlocks.CHISELED_QUARTZ_SANDSTONE, "classicraft:block/quartz_sandstone_bottom", "classicraft:block/quartz_sandstone_top", "classicraft:block/chiseled_quartz_sandstone");
        sandstoneBlockWithItem(ModBlocks.CHISELED_SOUL_SANDSTONE, "classicraft:block/soul_sandstone_bottom", "classicraft:block/soul_sandstone_top", "classicraft:block/chiseled_soul_sandstone");
        sandstoneBlockWithItem(ModBlocks.CUT_QUARTZ_SANDSTONE, "classicraft:block/quartz_sandstone_bottom", "classicraft:block/quartz_sandstone_top", "classicraft:block/cut_quartz_sandstone");
        sandstoneBlockWithItem(ModBlocks.CUT_SOUL_SANDSTONE, "classicraft:block/soul_sandstone_bottom", "classicraft:block/soul_sandstone_top", "classicraft:block/cut_soul_sandstone");
        sandstoneBlockWithItem(ModBlocks.SOUL_SANDSTONE, "classicraft:block/soul_sandstone_bottom", "classicraft:block/soul_sandstone_top", "classicraft:block/soul_sandstone");
        sandstoneBlockWithItem(ModBlocks.QUARTZ_SANDSTONE, "classicraft:block/quartz_sandstone_bottom", "classicraft:block/quartz_sandstone_top", "classicraft:block/quartz_sandstone");
        wallBlock(ModBlocks.POLISHED_ANDESITE_WALL, "polished_andesite","minecraft:block/polished_andesite");
        wallBlock(ModBlocks.POLISHED_DIORITE_WALL, "polished_diorite","minecraft:block/polished_diorite");
        wallBlock(ModBlocks.POLISHED_GRANITE_WALL, "polished_granite","minecraft:block/polished_granite");
        wallBlock(ModBlocks.STONE_WALL, "stone","minecraft:block/stone");
        wallBlock(ModBlocks.QUARTZ_WALL, "quartz", "minecraft:block/quartz_block_side");
        wallBlock(ModBlocks.POLISHED_BASALT_WALL, "polished_basalt", "minecraft:block/polished_basalt_side");
        wallBlock(ModBlocks.QUARTZ_SANDSTONE_WALL, "quartz_sandstone","classicraft:block/quartz_sandstone");
        wallBlock(ModBlocks.DARK_PRISMARINE_WALL, "dark_prismarine","minecraft:block/dark_prismarine");
        wallBlock(ModBlocks.CUT_SOUL_SANDSTONE_WALL, "cut_soul_sandstone","classicraft:block/cut_soul_sandstone");
        wallBlock(ModBlocks.SOUL_SANDSTONE_WALL, "soul_sandstone", "classicraft:block/soul_sandstone");
        wallBlock(ModBlocks.CUT_SANDSTONE_WALL, "cut_sandstone", "minecraft:block/cut_sandstone");
        wallBlock(ModBlocks.SMOOTH_STONE_WALL, "smooth_stone", "minecraft:block/smooth_stone");
        wallBlock(ModBlocks.MOSSY_BRICKS_WALL, "mossy_bricks", "classicraft:block/mossy_bricks");
        wallBlock(ModBlocks.CRACKED_BRICKS_WALL, "cracked_bricks", "classicraft:block/cracked_bricks");
        wallBlock(ModBlocks.CRACKED_STONE_BRICKS_WALL, "cracked_stone_bricks", "minecraft:block/cracked_stone_bricks");
        wallBlock(ModBlocks.DEEPSLATE_WALL, "deepslate","minecraft:block/deepslate");
        wallBlock(ModBlocks.PURPUR_BLOCK_WALL, "purpur_block", "minecraft:block/purpur_block");
        wallBlock(ModBlocks.SMOOTH_QUARTZ_WALL, "smooth_quartz", "minecraft:block/quartz_block_bottom");
        wallBlock(ModBlocks.CUT_QUARTZ_SANDSTONE_WALL, "cut_quartz_sandstone", "classicraft:block/cut_quartz_sandstone");
        wallBlock(ModBlocks.PRISMARINE_BRICKS_WALL, "prismarine_bricks","minecraft:block/prismarine_bricks");
        wallBlock(ModBlocks.CUT_RED_SANDSTONE_WALL, "cut_red_sandstone","minecraft:block/cut_red_sandstone");
        wallBlock(ModBlocks.SMOOTH_SANDSTONE_WALL, "smooth_sandstone", "minecraft:block/sandstone_top");
        wallBlock(ModBlocks.SMOOTH_RED_SANDSTONE_WALL, "smooth_red_sandstone", "minecraft:block/red_sandstone_top");
        stairsBlock(ModBlocks.CRACKED_BRICKS_STAIRS, "cracked_bricks","classicraft:block/cracked_bricks");
        stairsBlock(ModBlocks.MOSSY_BRICKS_STAIRS, "mossy_bricks","classicraft:block/mossy_bricks");
        stairsBlock(ModBlocks.CRACKED_STONE_BRICKS_STAIRS, "cracked_stone_bricks","minecraft:block/cracked_stone_bricks");
        stairsBlock(ModBlocks.DEEPSLATE_STAIRS, "deepslate","minecraft:block/deepslate", "minecraft:block/deepslate_top", "minecraft:block/deepslate_top");
        stairsBlock(ModBlocks.CUT_SANDSTONE_STAIRS, "cut_sandstone","minecraft:block/cut_sandstone", "minecraft:block/sandstone_bottom", "minecraft:block/sandstone_top");
        stairsBlock(ModBlocks.CUT_RED_SANDSTONE_STAIRS, "cut_red_sandstone","minecraft:block/cut_red_sandstone", "minecraft:block/red_sandstone_bottom", "minecraft:block/red_sandstone_top");
        stairsBlock(ModBlocks.SOUL_SANDSTONE_STAIRS, "soul_sandstone","classicraft:block/soul_sandstone", "classicraft:block/soul_sandstone_bottom", "classicraft:block/soul_sandstone_top");
        stairsBlock(ModBlocks.CUT_SOUL_SANDSTONE_STAIRS, "cut_soul_sandstone","classicraft:block/cut_soul_sandstone", "classicraft:block/soul_sandstone_bottom", "classicraft:block/soul_sandstone_top");
        stairsBlock(ModBlocks.CUT_QUARTZ_SANDSTONE_STAIRS, "cut_quartz_sandstone","classicraft:block/cut_quartz_sandstone", "classicraft:block/quartz_sandstone_bottom", "classicraft:block/quartz_sandstone_top");
        stairsBlock(ModBlocks.QUARTZ_SANDSTONE_STAIRS, "quartz_sandstone","classicraft:block/quartz_sandstone", "classicraft:block/quartz_sandstone_bottom", "classicraft:block/quartz_sandstone_top");
        stairsBlock(ModBlocks.POLISHED_BASALT_STAIRS, "polished_basalt","minecraft:block/polished_basalt_side", "minecraft:block/polished_basalt_top", "minecraft:block/polished_basalt_top");
        slabBlock(ModBlocks.MOSSY_BRICKS_SLAB, "classicraft:block/mossy_bricks");
        slabBlock(ModBlocks.CRACKED_BRICKS_SLAB,"classicraft:block/cracked_bricks");
        slabBlock(ModBlocks.CRACKED_STONE_BRICKS_SLAB,"minecraft:block/cracked_stone_bricks");
        slabBlock(ModBlocks.DEEPSLATE_SLAB,"minecraft:block/deepslate", "minecraft:block/deepslate_top", "minecraft:block/deepslate_top");
        slabBlock(ModBlocks.POLISHED_BASALT_SLAB,"minecraft:block/polished_basalt", "minecraft:block/polished_basalt_side", "minecraft:block/polished_basalt_top", "minecraft:block/polished_basalt_top");
        slabBlock(ModBlocks.SOUL_SANDSTONE_SLAB,"classicraft:block/soul_sandstone", "classicraft:block/soul_sandstone_bottom", "classicraft:block/soul_sandstone_top");
        slabBlock(ModBlocks.CUT_SOUL_SANDSTONE_SLAB,"classicraft:block/cut_soul_sandstone", "classicraft:block/soul_sandstone_bottom", "classicraft:block/soul_sandstone_top");
        slabBlock(ModBlocks.CUT_QUARTZ_SANDSTONE_SLAB,"classicraft:block/cut_quartz_sandstone", "classicraft:block/quartz_sandstone_bottom", "classicraft:block/quartz_sandstone_top");
        slabBlock(ModBlocks.QUARTZ_SANDSTONE_SLAB,"classicraft:block/quartz_sandstone","classicraft:block/quartz_sandstone_bottom", "classicraft:block/quartz_sandstone_top");
        threeBuildBlocks(ModBlocks.CRACKED_DEEPSLATE_BRICKS_WALL, ModBlocks.CRACKED_DEEPSLATE_BRICKS_STAIRS,
                ModBlocks.CRACKED_DEEPSLATE_BRICKS_SLAB, "cracked_deepslate_bricks", "minecraft:block/cracked_deepslate_bricks");
        threeBuildBlocks(ModBlocks.SMOOTH_SOUL_SANDSTONE_WALL, ModBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS, ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB,
                "smooth_soul_sandstone", "classicraft:block/smooth_soul_sandstone");
        threeBuildBlocks(ModBlocks.CRACKED_DEEPSLATE_TILES_WALL, ModBlocks.CRACKED_DEEPSLATE_TILES_STAIRS, ModBlocks.CRACKED_DEEPSLATE_TILES_SLAB,
                "cracked_deepslate_tiles", "minecraft:block/cracked_deepslate_tiles");
        threeBuildBlocks(ModBlocks.SANDSTONE_BRICKS_WALL, ModBlocks.SANDSTONE_BRICKS_STAIRS, ModBlocks.SANDSTONE_BRICKS_SLAB,
                "sandstone_bricks", "classicraft:block/sandstone_bricks");
        threeBuildBlocks(ModBlocks.RED_SANDSTONE_BRICKS_WALL, ModBlocks.RED_SANDSTONE_BRICKS_STAIRS, ModBlocks.RED_SANDSTONE_BRICKS_SLAB,
                "red_sandstone_bricks", "classicraft:block/red_sandstone_bricks");
        threeBuildBlocks(ModBlocks.SOUL_SANDSTONE_BRICKS_WALL, ModBlocks.SOUL_SANDSTONE_BRICKS_STAIRS, ModBlocks.SOUL_SANDSTONE_BRICKS_SLAB,
                "soul_sandstone_bricks", "classicraft:block/soul_sandstone_bricks");
        threeBuildBlocks(ModBlocks.FLINT_BLOCK_WALL, ModBlocks.FLINT_BLOCK_STAIRS, ModBlocks.FLINT_BLOCK_SLAB,
                "flint_block", "classicraft:block/flint_block");
        threeBuildBlocks(ModBlocks.QUARTZ_SANDSTONE_BRICKS_WALL, ModBlocks.QUARTZ_SANDSTONE_BRICKS_STAIRS, ModBlocks.QUARTZ_SANDSTONE_BRICKS_SLAB,
                "quartz_sandstone_bricks", "classicraft:block/quartz_sandstone_bricks");
        threeBuildBlocks(ModBlocks.END_STONE_WALL, ModBlocks.END_STONE_STAIRS, ModBlocks.END_STONE_SLAB,
                "end_stone", "minecraft:block/end_stone");
        threeBuildBlocks(ModBlocks.NETHERRACK_WALL, ModBlocks.NETHERRACK_STAIRS, ModBlocks.NETHERRACK_SLAB,
                "netherrack", "minecraft:block/netherrack");
        threeBuildBlocks(ModBlocks.SMOOTH_QUARTZ_SANDSTONE_WALL, ModBlocks.SMOOTH_QUARTZ_SANDSTONE_STAIRS, ModBlocks.SMOOTH_QUARTZ_SANDSTONE_SLAB,
                "smooth_quartz_sandstone", "classicraft:block/smooth_quartz_sandstone");
        threeBuildBlocks(ModBlocks.SMOOTH_BASALT_WALL, ModBlocks.SMOOTH_BASALT_STAIRS, ModBlocks.SMOOTH_BASALT_SLAB,
                "smooth_basalt", "minecraft:block/smooth_basalt");
        threeBuildBlocks(ModBlocks.QUARTZ_BRICKS_WALL, ModBlocks.QUARTZ_BRICKS_STAIRS, ModBlocks.QUARTZ_BRICKS_SLAB,
                "quartz_bricks", "minecraft:block/quartz_bricks");
        threeBuildBlocks(ModBlocks.CRACKED_NETHER_BRICKS_WALL, ModBlocks.CRACKED_NETHER_BRICKS_STAIRS, ModBlocks.CRACKED_NETHER_BRICKS_SLAB,
                "cracked_nether_bricks", "minecraft:block/cracked_nether_bricks");
        threeBuildBlocks(ModBlocks.CRACKED_POLISHED_BLACKSTONE_BRICKS_WALL, ModBlocks.CRACKED_POLISHED_BLACKSTONE_BRICKS_STAIRS, ModBlocks.CRACKED_POLISHED_BLACKSTONE_BRICKS_SLAB,
                "cracked_polished_blackstone_bricks", "minecraft:block/cracked_polished_blackstone_bricks");
        twoBuildBlocks(ModBlocks.WHITE_WOOL_STAIRS, ModBlocks.WHITE_WOOL_SLAB,
                "white_wool", "minecraft:block/white_wool");
        twoBuildBlocks(ModBlocks.ORANGE_WOOL_STAIRS, ModBlocks.ORANGE_WOOL_SLAB,
                "orange_wool", "minecraft:block/orange_wool");
        twoBuildBlocks(ModBlocks.MAGENTA_WOOL_STAIRS, ModBlocks.MAGENTA_WOOL_SLAB,
                "magenta_wool", "minecraft:block/magenta_wool");
        twoBuildBlocks(ModBlocks.LIGHT_BLUE_WOOL_STAIRS, ModBlocks.LIGHT_BLUE_WOOL_SLAB,
                "light_blue_wool", "minecraft:block/light_blue_wool");
        twoBuildBlocks(ModBlocks.YELLOW_WOOL_STAIRS, ModBlocks.YELLOW_WOOL_SLAB,
                "yellow_wool", "minecraft:block/yellow_wool");
        twoBuildBlocks(ModBlocks.LIME_WOOL_STAIRS, ModBlocks.LIME_WOOL_SLAB,
                "lime_wool", "minecraft:block/lime_wool");
        twoBuildBlocks(ModBlocks.PINK_WOOL_STAIRS, ModBlocks.PINK_WOOL_SLAB,
                "pink_wool", "minecraft:block/pink_wool");
        twoBuildBlocks(ModBlocks.GRAY_WOOL_STAIRS, ModBlocks.GRAY_WOOL_SLAB,
                "gray_wool", "minecraft:block/gray_wool");
        twoBuildBlocks(ModBlocks.LIGHT_GRAY_WOOL_STAIRS, ModBlocks.LIGHT_GRAY_WOOL_SLAB,
                "light_gray_wool", "minecraft:block/light_gray_wool");
        twoBuildBlocks(ModBlocks.CYAN_WOOL_STAIRS, ModBlocks.CYAN_WOOL_SLAB,
                "cyan_wool", "minecraft:block/cyan_wool");
        twoBuildBlocks(ModBlocks.PURPLE_WOOL_STAIRS, ModBlocks.PURPLE_WOOL_SLAB,
                "purple_wool", "minecraft:block/purple_wool");
        twoBuildBlocks(ModBlocks.BLUE_WOOL_STAIRS, ModBlocks.BLUE_WOOL_SLAB,
                "blue_wool", "minecraft:block/blue_wool");
        twoBuildBlocks(ModBlocks.BROWN_WOOL_STAIRS, ModBlocks.BROWN_WOOL_SLAB,
                "brown_wool", "minecraft:block/brown_wool");
        twoBuildBlocks(ModBlocks.GREEN_WOOL_STAIRS, ModBlocks.GREEN_WOOL_SLAB,
                "green_wool", "minecraft:block/green_wool");
        twoBuildBlocks(ModBlocks.RED_WOOL_STAIRS, ModBlocks.RED_WOOL_SLAB,
                "red_wool", "minecraft:block/red_wool");
        twoBuildBlocks(ModBlocks.BLACK_WOOL_STAIRS, ModBlocks.BLACK_WOOL_SLAB,
                "black_wool", "minecraft:block/black_wool");
        fenceBlock(ModBlocks.CRIMSON_NETHER_BRICKS_FENCE, "minecraft:block/red_nether_bricks");
        glassStairsBlock(ModBlocks.WHITE_STAINED_GLASS_STAIRS, DyeColor.WHITE);
        glassStairsBlock(ModBlocks.ORANGE_STAINED_GLASS_STAIRS, DyeColor.ORANGE);
        glassStairsBlock(ModBlocks.MAGENTA_STAINED_GLASS_STAIRS, DyeColor.MAGENTA);
        glassStairsBlock(ModBlocks.LIGHT_BLUE_STAINED_GLASS_STAIRS, DyeColor.LIGHT_BLUE);
        glassStairsBlock(ModBlocks.YELLOW_STAINED_GLASS_STAIRS, DyeColor.YELLOW);
        glassStairsBlock(ModBlocks.LIME_STAINED_GLASS_STAIRS, DyeColor.LIME);
        glassStairsBlock(ModBlocks.PINK_STAINED_GLASS_STAIRS, DyeColor.PINK);
        glassStairsBlock(ModBlocks.GRAY_STAINED_GLASS_STAIRS, DyeColor.GRAY);
        glassStairsBlock(ModBlocks.LIGHT_GRAY_STAINED_GLASS_STAIRS, DyeColor.LIGHT_GRAY);
        glassStairsBlock(ModBlocks.CYAN_STAINED_GLASS_STAIRS, DyeColor.CYAN);
        glassStairsBlock(ModBlocks.PURPLE_STAINED_GLASS_STAIRS, DyeColor.PURPLE);
        glassStairsBlock(ModBlocks.BLUE_STAINED_GLASS_STAIRS, DyeColor.BLUE);
        glassStairsBlock(ModBlocks.BROWN_STAINED_GLASS_STAIRS, DyeColor.BROWN);
        glassStairsBlock(ModBlocks.GREEN_STAINED_GLASS_STAIRS, DyeColor.GREEN);
        glassStairsBlock(ModBlocks.RED_STAINED_GLASS_STAIRS, DyeColor.RED);
        glassStairsBlock(ModBlocks.BLACK_STAINED_GLASS_STAIRS, DyeColor.BLACK);
        glassSlabBlock(ModBlocks.WHITE_STAINED_GLASS_SLAB, DyeColor.WHITE);
        glassSlabBlock(ModBlocks.ORANGE_STAINED_GLASS_SLAB, DyeColor.ORANGE);
        glassSlabBlock(ModBlocks.MAGENTA_STAINED_GLASS_SLAB, DyeColor.MAGENTA);
        glassSlabBlock(ModBlocks.LIGHT_BLUE_STAINED_GLASS_SLAB, DyeColor.LIGHT_BLUE);
        glassSlabBlock(ModBlocks.YELLOW_STAINED_GLASS_SLAB, DyeColor.YELLOW);
        glassSlabBlock(ModBlocks.LIME_STAINED_GLASS_SLAB, DyeColor.LIME);
        glassSlabBlock(ModBlocks.PINK_STAINED_GLASS_SLAB, DyeColor.PINK);
        glassSlabBlock(ModBlocks.GRAY_STAINED_GLASS_SLAB, DyeColor.GRAY);
        glassSlabBlock(ModBlocks.LIGHT_GRAY_STAINED_GLASS_SLAB, DyeColor.LIGHT_GRAY);
        glassSlabBlock(ModBlocks.CYAN_STAINED_GLASS_SLAB, DyeColor.CYAN);
        glassSlabBlock(ModBlocks.PURPLE_STAINED_GLASS_SLAB, DyeColor.PURPLE);
        glassSlabBlock(ModBlocks.BLUE_STAINED_GLASS_SLAB, DyeColor.BLUE);
        glassSlabBlock(ModBlocks.BROWN_STAINED_GLASS_SLAB, DyeColor.BROWN);
        glassSlabBlock(ModBlocks.GREEN_STAINED_GLASS_SLAB, DyeColor.GREEN);
        glassSlabBlock(ModBlocks.RED_STAINED_GLASS_SLAB, DyeColor.RED);
        glassSlabBlock(ModBlocks.BLACK_STAINED_GLASS_SLAB, DyeColor.BLACK);
        threeBuildBlocks(ModBlocks.GLASS_WALL, ModBlocks.GLASS_STAIRS, ModBlocks.GLASS_SLAB,
                "glass", "minecraft:block/glass");
        threeBuildBlocks(ModBlocks.TINTED_GLASS_WALL, ModBlocks.TINTED_GLASS_STAIRS, ModBlocks.TINTED_GLASS_SLAB,
                "tinted_glass", "minecraft:block/tinted_glass");
        threeColorConcretes(ModBlocks.WHITE_CONCRETE_WALL, ModBlocks.WHITE_CONCRETE_STAIRS,
                ModBlocks.WHITE_CONCRETE_SLAB, DyeColor.WHITE);
        threeColorConcretes(ModBlocks.ORANGE_CONCRETE_WALL, ModBlocks.ORANGE_CONCRETE_STAIRS,
                ModBlocks.ORANGE_CONCRETE_SLAB, DyeColor.ORANGE);
        threeColorConcretes(ModBlocks.MAGENTA_CONCRETE_WALL, ModBlocks.MAGENTA_CONCRETE_STAIRS,
                ModBlocks.MAGENTA_CONCRETE_SLAB, DyeColor.MAGENTA);
        threeColorConcretes(ModBlocks.LIGHT_BLUE_CONCRETE_WALL, ModBlocks.LIGHT_BLUE_CONCRETE_STAIRS,
                ModBlocks.LIGHT_BLUE_CONCRETE_SLAB, DyeColor.LIGHT_BLUE);
        threeColorConcretes(ModBlocks.YELLOW_CONCRETE_WALL, ModBlocks.YELLOW_CONCRETE_STAIRS,
                ModBlocks.YELLOW_CONCRETE_SLAB, DyeColor.YELLOW);
        threeColorConcretes(ModBlocks.LIME_CONCRETE_WALL, ModBlocks.LIME_CONCRETE_STAIRS,
                ModBlocks.LIME_CONCRETE_SLAB, DyeColor.LIME);
        threeColorConcretes(ModBlocks.PINK_CONCRETE_WALL, ModBlocks.PINK_CONCRETE_STAIRS,
                ModBlocks.PINK_CONCRETE_SLAB, DyeColor.PINK);
        threeColorConcretes(ModBlocks.GRAY_CONCRETE_WALL, ModBlocks.GRAY_CONCRETE_STAIRS,
                ModBlocks.GRAY_CONCRETE_SLAB, DyeColor.GRAY);
        threeColorConcretes(ModBlocks.LIGHT_GRAY_CONCRETE_WALL, ModBlocks.LIGHT_GRAY_CONCRETE_STAIRS,
                ModBlocks.LIGHT_GRAY_CONCRETE_SLAB, DyeColor.LIGHT_GRAY);
        threeColorConcretes(ModBlocks.CYAN_CONCRETE_WALL, ModBlocks.CYAN_CONCRETE_STAIRS,
                ModBlocks.CYAN_CONCRETE_SLAB, DyeColor.CYAN);
        threeColorConcretes(ModBlocks.PURPLE_CONCRETE_WALL, ModBlocks.PURPLE_CONCRETE_STAIRS,
                ModBlocks.PURPLE_CONCRETE_SLAB, DyeColor.PURPLE);
        threeColorConcretes(ModBlocks.BLUE_CONCRETE_WALL, ModBlocks.BLUE_CONCRETE_STAIRS,
                ModBlocks.BLUE_CONCRETE_SLAB, DyeColor.BLUE);
        threeColorConcretes(ModBlocks.BROWN_CONCRETE_WALL, ModBlocks.BROWN_CONCRETE_STAIRS,
                ModBlocks.BROWN_CONCRETE_SLAB, DyeColor.BROWN);
        threeColorConcretes(ModBlocks.GREEN_CONCRETE_WALL, ModBlocks.GREEN_CONCRETE_STAIRS,
                ModBlocks.GREEN_CONCRETE_SLAB, DyeColor.GREEN);
        threeColorConcretes(ModBlocks.RED_CONCRETE_WALL, ModBlocks.RED_CONCRETE_STAIRS,
                ModBlocks.RED_CONCRETE_SLAB, DyeColor.RED);
        threeColorConcretes(ModBlocks.BLACK_CONCRETE_WALL, ModBlocks.BLACK_CONCRETE_STAIRS,
                ModBlocks.BLACK_CONCRETE_SLAB, DyeColor.BLACK);
        threeColorTerracottas(ModBlocks.WHITE_TERRACOTTA_WALL, ModBlocks.WHITE_TERRACOTTA_STAIRS,
                ModBlocks.WHITE_TERRACOTTA_SLAB, DyeColor.WHITE);
        threeColorTerracottas(ModBlocks.ORANGE_TERRACOTTA_WALL, ModBlocks.ORANGE_TERRACOTTA_STAIRS,
                ModBlocks.ORANGE_TERRACOTTA_SLAB, DyeColor.ORANGE);
        threeColorTerracottas(ModBlocks.MAGENTA_TERRACOTTA_WALL, ModBlocks.MAGENTA_TERRACOTTA_STAIRS,
                ModBlocks.MAGENTA_TERRACOTTA_SLAB, DyeColor.MAGENTA);
        threeColorTerracottas(ModBlocks.LIGHT_BLUE_TERRACOTTA_WALL, ModBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS,
                ModBlocks.LIGHT_BLUE_TERRACOTTA_SLAB, DyeColor.LIGHT_BLUE);
        threeColorTerracottas(ModBlocks.YELLOW_TERRACOTTA_WALL, ModBlocks.YELLOW_TERRACOTTA_STAIRS,
                ModBlocks.YELLOW_TERRACOTTA_SLAB, DyeColor.YELLOW);
        threeColorTerracottas(ModBlocks.LIME_TERRACOTTA_WALL, ModBlocks.LIME_TERRACOTTA_STAIRS,
                ModBlocks.LIME_TERRACOTTA_SLAB, DyeColor.LIME);
        threeColorTerracottas(ModBlocks.PINK_TERRACOTTA_WALL, ModBlocks.PINK_TERRACOTTA_STAIRS,
                ModBlocks.PINK_TERRACOTTA_SLAB, DyeColor.PINK);
        threeColorTerracottas(ModBlocks.GRAY_TERRACOTTA_WALL, ModBlocks.GRAY_TERRACOTTA_STAIRS,
                ModBlocks.GRAY_TERRACOTTA_SLAB, DyeColor.GRAY);
        threeColorTerracottas(ModBlocks.LIGHT_GRAY_TERRACOTTA_WALL, ModBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS,
                ModBlocks.LIGHT_GRAY_TERRACOTTA_SLAB, DyeColor.LIGHT_GRAY);
        threeColorTerracottas(ModBlocks.CYAN_TERRACOTTA_WALL, ModBlocks.CYAN_TERRACOTTA_STAIRS,
                ModBlocks.CYAN_TERRACOTTA_SLAB, DyeColor.CYAN);
        threeColorTerracottas(ModBlocks.PURPLE_TERRACOTTA_WALL, ModBlocks.PURPLE_TERRACOTTA_STAIRS,
                ModBlocks.PURPLE_TERRACOTTA_SLAB, DyeColor.PURPLE);
        threeColorTerracottas(ModBlocks.BLUE_TERRACOTTA_WALL, ModBlocks.BLUE_TERRACOTTA_STAIRS,
                ModBlocks.BLUE_TERRACOTTA_SLAB, DyeColor.BLUE);
        threeColorTerracottas(ModBlocks.BROWN_TERRACOTTA_WALL, ModBlocks.BROWN_TERRACOTTA_STAIRS,
                ModBlocks.BROWN_TERRACOTTA_SLAB, DyeColor.BROWN);
        threeColorTerracottas(ModBlocks.GREEN_TERRACOTTA_WALL, ModBlocks.GREEN_TERRACOTTA_STAIRS,
                ModBlocks.GREEN_TERRACOTTA_SLAB, DyeColor.GREEN);
        threeColorTerracottas(ModBlocks.RED_TERRACOTTA_WALL, ModBlocks.RED_TERRACOTTA_STAIRS,
                ModBlocks.RED_TERRACOTTA_SLAB, DyeColor.RED);
        threeColorTerracottas(ModBlocks.BLACK_TERRACOTTA_WALL, ModBlocks.BLACK_TERRACOTTA_STAIRS,
                ModBlocks.BLACK_TERRACOTTA_SLAB, DyeColor.BLACK);
        threeBuildBlocks(ModBlocks.ANDESITE_BRICKS_WALL, ModBlocks.ANDESITE_BRICKS_STAIRS,
                ModBlocks.ANDESITE_BRICKS_SLAB,
                "andesite_bricks", "classicraft:block/andesite_bricks");
        threeBuildBlocks(ModBlocks.CRACKED_ANDESITE_BRICKS_WALL, ModBlocks.CRACKED_ANDESITE_BRICKS_STAIRS,
                ModBlocks.CRACKED_ANDESITE_BRICKS_SLAB,
                "cracked_andesite_bricks", "classicraft:block/cracked_andesite_bricks");
        threeBuildBlocks(ModBlocks.MOSSY_ANDESITE_BRICKS_WALL, ModBlocks.MOSSY_ANDESITE_BRICKS_STAIRS,
                ModBlocks.MOSSY_ANDESITE_BRICKS_SLAB,
                "mossy_andesite_bricks", "classicraft:block/mossy_andesite_bricks");
        threeBuildBlocks(ModBlocks.GRANITE_BRICKS_WALL, ModBlocks.GRANITE_BRICKS_STAIRS,
                ModBlocks.GRANITE_BRICKS_SLAB,
                "granite_bricks", "classicraft:block/granite_bricks");
        threeBuildBlocks(ModBlocks.CRACKED_GRANITE_BRICKS_WALL, ModBlocks.CRACKED_GRANITE_BRICKS_STAIRS,
                ModBlocks.CRACKED_GRANITE_BRICKS_SLAB,
                "cracked_granite_bricks", "classicraft:block/cracked_granite_bricks");
        threeBuildBlocks(ModBlocks.MOSSY_GRANITE_BRICKS_WALL, ModBlocks.MOSSY_GRANITE_BRICKS_STAIRS,
                ModBlocks.MOSSY_GRANITE_BRICKS_SLAB,
                "mossy_granite_bricks", "classicraft:block/mossy_granite_bricks");
        threeBuildBlocks(ModBlocks.DIORITE_BRICKS_WALL, ModBlocks.DIORITE_BRICKS_STAIRS,
                ModBlocks.DIORITE_BRICKS_SLAB,
                "diorite_bricks", "classicraft:block/diorite_bricks");
        threeBuildBlocks(ModBlocks.CRACKED_DIORITE_BRICKS_WALL, ModBlocks.CRACKED_DIORITE_BRICKS_STAIRS,
                ModBlocks.CRACKED_DIORITE_BRICKS_SLAB,
                "cracked_diorite_bricks", "classicraft:block/cracked_diorite_bricks");
        threeBuildBlocks(ModBlocks.MOSSY_DIORITE_BRICKS_WALL, ModBlocks.MOSSY_DIORITE_BRICKS_STAIRS,
                ModBlocks.MOSSY_DIORITE_BRICKS_SLAB,
                "mossy_diorite_bricks", "classicraft:block/mossy_diorite_bricks");
        threeBuildBlocks(ModBlocks.MOSSY_COBBLED_DEEPSLATE_WALL, ModBlocks.MOSSY_COBBLED_DEEPSLATE_STAIRS,
                ModBlocks.MOSSY_COBBLED_DEEPSLATE_SLAB,
                "mossy_cobbled_deepslate", "classicraft:block/mossy_cobbled_deepslate");
        threeBuildBlocks(ModBlocks.MOSSY_DEEPSLATE_BRICKS_WALL, ModBlocks.MOSSY_DEEPSLATE_BRICKS_STAIRS,
                ModBlocks.MOSSY_DEEPSLATE_BRICKS_SLAB,
                "mossy_deepslate_bricks", "classicraft:block/mossy_deepslate_bricks");
        threeBuildBlocks(ModBlocks.MOSSY_DEEPSLATE_TILES_WALL, ModBlocks.MOSSY_DEEPSLATE_TILES_STAIRS,
                ModBlocks.MOSSY_DEEPSLATE_TILES_SLAB,
                "mossy_deepslate_tiles", "classicraft:block/mossy_deepslate_tiles");
        buttonBlock(ModBlocks.POLISHED_ANDESITE_BUTTON, "minecraft:block/polished_andesite");
        buttonBlock(ModBlocks.POLISHED_DIORITE_BUTTON, "minecraft:block/polished_diorite");
        buttonBlock(ModBlocks.POLISHED_GRANITE_BUTTON, "minecraft:block/polished_granite");
        buttonBlock(ModBlocks.SMOOTH_STONE_BUTTON, "minecraft:block/smooth_stone");
        buttonBlock(ModBlocks.BLACKSTONE_BUTTON, "minecraft:block/blackstone_top");
        buttonBlock(ModBlocks.DEEPSLATE_BUTTON, "minecraft:block/deepslate_top");
        buttonBlock(ModBlocks.POLISHED_DEEPSLATE_BUTTON, "minecraft:block/polished_deepslate");
        pressurePlateBlock(ModBlocks.POLISHED_ANDESITE_PRESSURE_PLATE, "minecraft:block/polished_andesite");
        pressurePlateBlock(ModBlocks.POLISHED_DIORITE_PRESSURE_PLATE, "minecraft:block/polished_diorite");
        pressurePlateBlock(ModBlocks.POLISHED_GRANITE_PRESSURE_PLATE, "minecraft:block/polished_granite");
        pressurePlateBlock(ModBlocks.SMOOTH_STONE_PRESSURE_PLATE, "minecraft:block/smooth_stone");
        pressurePlateBlock(ModBlocks.BLACKSTONE_PRESSURE_PLATE, "minecraft:block/blackstone_top");
        pressurePlateBlock(ModBlocks.DEEPSLATE_PRESSURE_PLATE, "minecraft:block/deepslate_top");
        pressurePlateBlock(ModBlocks.POLISHED_DEEPSLATE_PRESSURE_PLATE, "minecraft:block/polished_deepslate");
    }

    protected void pressurePlateBlock(RegistryObject<Block> block, String texture) {
        pressurePlateBlock((PressurePlateBlock) block.get(), new ResourceLocation(texture));
    }

    protected void buttonBlock(RegistryObject<Block> block, String texture) {
        buttonBlock((ButtonBlock) block.get(), new ResourceLocation(texture));
    }

    void carpetBlockWithItem(Block carpet, Block base) {
        simpleBlock(carpet, models().carpet(name(carpet), Helpers.identifier("block/" + name(base))));
        simpleBlockItem(carpet, models().carpet(name(carpet), Helpers.identifier("block/" + name(base))));
    }

    protected void threeColorTerracottas(RegistryObject<Block> wall, RegistryObject<Block> stairs, RegistryObject<Block> slab, DyeColor dyeColor) {
        threeBuildBlocks(wall, stairs, slab, dyeColor.getName() + "_terracotta", "minecraft:block/" + dyeColor.getName() + "_terracotta");
    }

    protected void threeColorConcretes(RegistryObject<Block> wall, RegistryObject<Block> stairs, RegistryObject<Block> slab, DyeColor dyeColor) {
        threeBuildBlocks(wall, stairs, slab, dyeColor.getName() + "_concrete", "minecraft:block/" + dyeColor.getName() + "_concrete");
    }

    protected void glassSlabBlock(RegistryObject<Block> slab, DyeColor dyeColor) {
        glassSlabBlock((StainedGlassSlabBlock) slab.get(),
                new ResourceLocation("minecraft:block/" + dyeColor.getName() + "_stained_glass"),
                new ResourceLocation("minecraft:block/" + dyeColor.getName() + "_stained_glass"),
                new ResourceLocation("minecraft:block/" + dyeColor.getName() + "_stained_glass"),
                new ResourceLocation("minecraft:block/" + dyeColor.getName() + "_stained_glass"));
    }

    protected void glassWallBlock(RegistryObject<Block> wall, DyeColor dyeColor) {
        glassWallBlock((StainedGlassWallBlock) wall.get(), new ResourceLocation("minecraft:block/" + dyeColor.getName() + "_stained_glass"));
    }

    protected void glassStairsBlock(RegistryObject<Block> stairs, DyeColor dyeColor) {
        glassStairsBlock((StainedGlassStairBlock) stairs.get(),
                name(stairs.get()),
                new ResourceLocation("minecraft:block/" + dyeColor.getName() + "_stained_glass"),
                new ResourceLocation("minecraft:block/" + dyeColor.getName() + "_stained_glass"),
                new ResourceLocation("minecraft:block/" + dyeColor.getName() + "_stained_glass"));
    }

    private void fenceBlock(RegistryObject<Block> block, String texture) {
        fenceBlock((FenceBlock) block.get(), new ResourceLocation(texture));
    }

    protected void twoBuildBlocks(RegistryObject<Block> stairs, RegistryObject<Block> slab, String prefix, String texture) {
        stairsBlock(stairs, prefix, texture);
        slabBlock(slab, texture);
    }

    protected void modSimpleBlockWithItem(RegistryObject<Block> block, String textureName) {
        simpleBlock(block.get(), models().cubeAll(block.getId().toString(),
                new ResourceLocation("classicraft:block/" + textureName)));
        simpleBlockItem(block.get(), models().cubeAll(block.getId().toString(),
                new ResourceLocation("classicraft:block/" + textureName)));
    }

    protected void vanillaSimpleBlockWithItem(RegistryObject<Block> block, String textureName) {
        simpleBlock(block.get(), models().cubeAll(block.getId().toString(),
                new ResourceLocation("minecraft:block/" + textureName)));
        simpleBlockItem(block.get(), models().cubeAll(block.getId().toString(),
                new ResourceLocation("minecraft:block/" + textureName)));
    }

    protected void vanillaSimpleBlockWithItem(RegistryObject<Block> block, String bottom, String top, String other) {
        simpleBlock(block.get(), models().cube(block.getId().toString(),
                new ResourceLocation("minecraft:block/" + bottom),
                new ResourceLocation("minecraft:block/" + top),
                new ResourceLocation("minecraft:block/" + other),
                new ResourceLocation("minecraft:block/" + other),
                new ResourceLocation("minecraft:block/" + other),
                new ResourceLocation("minecraft:block/" + other)));
        simpleBlockItem(block.get(), models().cube(block.getId().toString(),
                new ResourceLocation("minecraft:block/" + bottom),
                new ResourceLocation("minecraft:block/" + top),
                new ResourceLocation("minecraft:block/" + other),
                new ResourceLocation("minecraft:block/" + other),
                new ResourceLocation("minecraft:block/" + other),
                new ResourceLocation("minecraft:block/" + other)));
    }

    protected void sandstoneBlockWithItem(RegistryObject<Block> block, String bottom, String top, String other) {
        simpleBlock(block.get(), models().cube(block.getId().toString(),
                new ResourceLocation(bottom),
                new ResourceLocation(top),
                new ResourceLocation(other),
                new ResourceLocation(other),
                new ResourceLocation(other),
                new ResourceLocation(other)));
        simpleBlockItem(block.get(), models().cube(block.getId().toString(),
                new ResourceLocation(bottom),
                new ResourceLocation(top),
                new ResourceLocation(other),
                new ResourceLocation(other),
                new ResourceLocation(other),
                new ResourceLocation(other))
                .texture("particle", other)
                .renderType("solid"));
    }

    protected void simpleBlockWithItem(RegistryObject<Block> block) {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }

    protected void threeBuildBlocks(RegistryObject<Block> wall, RegistryObject<Block> stairs, RegistryObject<Block> slab, String prefix, String texture) {
        wallBlock(wall, prefix, texture);
        stairsBlock(stairs, prefix, texture);
        slabBlock(slab, texture);
    }

    protected void wallBlock(RegistryObject<Block> block, String name, String texture) {
        wallBlock((WallBlock) block.get(), name, new ResourceLocation(texture));
    }

    protected void stairsBlock(RegistryObject<Block> block, String name, String texture) {
        stairsBlock((StairBlock) block.get(), name, new ResourceLocation(texture));
    }

    protected void slabBlock(RegistryObject<Block> block, String texture) {
        slabBlock((SlabBlock) block.get(),
                new ResourceLocation(texture),
                new ResourceLocation(texture));
    }

    protected void slabBlock(RegistryObject<Block> block, String doubleslab, String bottom, String top) {
        slabBlock((SlabBlock) block.get(),
                new ResourceLocation(doubleslab),
                new ResourceLocation(doubleslab),
                new ResourceLocation(bottom),
                new ResourceLocation(top));
    }

    protected void slabBlock(RegistryObject<Block> block, String doubleslab, String side, String bottom, String top) {
        slabBlock((SlabBlock) block.get(),
                new ResourceLocation(doubleslab),
                new ResourceLocation(side),
                new ResourceLocation(bottom),
                new ResourceLocation(top));
    }

    protected void stairsBlock(RegistryObject<Block> block, String name, String side, String bottom, String top) {
        stairsBlock((StairBlock) block.get(), name,
                new ResourceLocation(side),
                new ResourceLocation(bottom),
                new ResourceLocation(top));
    }

    public void glassWallBlock(StainedGlassWallBlock block, ResourceLocation texture) {
        glassWallBlockInternal(block,  key(block).toString(), texture);
    }

    private void glassWallBlockInternal(StainedGlassWallBlock block, String baseName, ResourceLocation texture) {
        glassWallBlock(block, models().wallPost(baseName + "_post", texture),
                models().wallSide(baseName + "_side", texture),
                models().wallSideTall(baseName + "_side_tall", texture));
    }

    public void glassWallBlock(StainedGlassWallBlock block, ModelFile post, ModelFile side, ModelFile sideTall) {
        MultiPartBlockStateBuilder builder = getMultipartBuilder(block)
                .part().modelFile(post).addModel()
                .condition(WallBlock.UP, true).end();
        WALL_PROPS.entrySet().stream()
                .filter(e -> e.getKey().getAxis().isHorizontal())
                .forEach(e -> {
                    wallSidePart(builder, side, e, WallSide.LOW);
                    wallSidePart(builder, sideTall, e, WallSide.TALL);
                });
    }

    private void wallSidePart(MultiPartBlockStateBuilder builder, ModelFile model, Map.Entry<Direction, Property<WallSide>> entry, WallSide height) {
        builder.part()
                .modelFile(model)
                .rotationY((((int) entry.getKey().toYRot()) + 180) % 360)
                .uvLock(true)
                .addModel()
                .condition(entry.getValue(), height);
    }

    public void glassSlabBlock(StainedGlassSlabBlock block, ResourceLocation doubleslab, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
        glassSlabBlock(block, models().slab(name(block), side, bottom, top), models().slabTop(name(block) + "_top", side, bottom, top), models().getExistingFile(doubleslab));
    }

    public void glassSlabBlock(StainedGlassSlabBlock block, ModelFile bottom, ModelFile top, ModelFile doubleslab) {
        getVariantBuilder(block)
                .partialState().with(StainedGlassSlabBlock.TYPE, SlabType.BOTTOM).addModels(new ConfiguredModel(bottom))
                .partialState().with(StainedGlassSlabBlock.TYPE, SlabType.TOP).addModels(new ConfiguredModel(top))
                .partialState().with(StainedGlassSlabBlock.TYPE, SlabType.DOUBLE).addModels(new ConfiguredModel(doubleslab));
    }

    protected void glassStairsBlock(Block block, String name, String all) {
        glassStairsBlock((StainedGlassStairBlock) block,
                name,
                new ResourceLocation(all),
                new ResourceLocation(all),
                new ResourceLocation(all));
    }

    public void glassStairsBlock(StainedGlassStairBlock block, String name, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
        glassStairsBlockInternal(block, name + "_stairs", side, bottom, top);
    }

    private void glassStairsBlockInternal(StainedGlassStairBlock block, String baseName, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
        ModelFile stairs = models().stairs(baseName, side, bottom, top);
        ModelFile stairsInner = models().stairsInner(baseName + "_inner", side, bottom, top);
        ModelFile stairsOuter = models().stairsOuter(baseName + "_outer", side, bottom, top);
        glassStairsBlock(block, stairs, stairsInner, stairsOuter);
    }

    public void glassStairsBlock(StainedGlassStairBlock block, ModelFile stairs, ModelFile stairsInner, ModelFile stairsOuter) {
        getVariantBuilder(block)
                .forAllStatesExcept(state -> {
                    Direction facing = state.getValue(HorizontalDirectionalBlock.FACING);
                    Half half = state.getValue(BlockStateProperties.HALF);
                    StairsShape shape = state.getValue(BlockStateProperties.STAIRS_SHAPE);
                    int yRot = (int) facing.getClockWise().toYRot(); // Stairs model is rotated 90 degrees clockwise for some reason
                    if (shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT) {
                        yRot += 270; // Left facing stairs are rotated 90 degrees clockwise
                    }
                    if (shape != StairsShape.STRAIGHT && half == Half.TOP) {
                        yRot += 90; // Top stairs are rotated 90 degrees clockwise
                    }
                    yRot %= 360;
                    boolean uvlock = yRot != 0 || half == Half.TOP; // Don't set uvlock for states that have no rotation
                    return ConfiguredModel.builder()
                            .modelFile(shape == StairsShape.STRAIGHT ? stairs : shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT ? stairsInner : stairsOuter)
                            .rotationX(half == Half.BOTTOM ? 0 : 180)
                            .rotationY(yRot)
                            .uvLock(uvlock)
                            .build();
                }, StainedGlassStairBlock.WATERLOGGED);
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

}