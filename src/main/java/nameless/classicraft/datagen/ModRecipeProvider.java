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

import nameless.classicraft.api.item.MetaItem;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import nameless.classicraft.util.Helpers;
import net.minecraft.Util;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.StrictNBTIngredient;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        heatRecipe(pWriter, Items.EGG, ModItems.COOKED_EGG.get());
        heatRecipe(pWriter, Items.ROTTEN_FLESH, Items.LEATHER);
        heatRecipe(pWriter, Items.STONE_BRICKS, Items.CRACKED_STONE_BRICKS);
        heatRecipe(pWriter, Items.MOSSY_COBBLESTONE, Items.STONE);
        heatRecipe(pWriter, Blocks.BRICKS, ModBlocks.CRACKED_BRICKS.get());
        heatRecipe(pWriter, ModItems.UNFIRED_CLAY_BOWL.get(), ModItems.CERAMIC_BOWL.get());
        heatRecipe(pWriter, ModItems.UNFIRED_CLAY_FLOWER_POT.get(), Items.FLOWER_POT);
        heatRecipe(pWriter, ModItems.UNFIRED_CLAY_BRICK.get(), Items.BRICK);
        heatRecipe(pWriter, ModBlocks.DIORITE_BRICKS.get(), ModBlocks.CRACKED_DIORITE_BRICKS.get());
        heatRecipe(pWriter, ModBlocks.ANDESITE_BRICKS.get(), ModBlocks.CRACKED_ANDESITE_BRICKS.get());
        heatRecipe(pWriter, ModBlocks.GRANITE_BRICKS.get(), ModBlocks.CRACKED_GRANITE_BRICKS.get());
        heatRecipe(pWriter, ModBlocks.ANDESITE_BRICKS_SLAB.get(), ModBlocks.CRACKED_ANDESITE_BRICKS_SLAB.get());
        heatRecipe(pWriter, ModBlocks.ANDESITE_BRICKS_STAIRS.get(), ModBlocks.CRACKED_ANDESITE_BRICKS_STAIRS.get());
        heatRecipe(pWriter, ModBlocks.ANDESITE_BRICKS_WALL.get(), ModBlocks.CRACKED_ANDESITE_BRICKS_WALL.get());
        heatRecipe(pWriter, ModBlocks.GRANITE_BRICKS_SLAB.get(), ModBlocks.CRACKED_GRANITE_BRICKS_SLAB.get());
        heatRecipe(pWriter, ModBlocks.GRANITE_BRICKS_STAIRS.get(), ModBlocks.CRACKED_GRANITE_BRICKS_STAIRS.get());
        heatRecipe(pWriter, ModBlocks.GRANITE_BRICKS_WALL.get(), ModBlocks.CRACKED_GRANITE_BRICKS_WALL.get());
        heatRecipe(pWriter, ModBlocks.DIORITE_BRICKS_SLAB.get(), ModBlocks.CRACKED_DIORITE_BRICKS_SLAB.get());
        heatRecipe(pWriter, ModBlocks.DIORITE_BRICKS_STAIRS.get(), ModBlocks.CRACKED_DIORITE_BRICKS_STAIRS.get());
        heatRecipe(pWriter, ModBlocks.DIORITE_BRICKS_WALL.get(), ModBlocks.CRACKED_DIORITE_BRICKS_WALL.get());
        heatRecipe(pWriter, Items.STONE_BRICK_SLAB, ModBlocks.CRACKED_STONE_BRICKS_SLAB.get());
        heatRecipe(pWriter, Items.STONE_BRICK_STAIRS, ModBlocks.CRACKED_STONE_BRICKS_STAIRS.get());
        heatRecipe(pWriter, Items.STONE_BRICK_WALL, ModBlocks.CRACKED_STONE_BRICKS_WALL.get());
        heatRecipe(pWriter, Items.BRICK_SLAB, ModBlocks.CRACKED_BRICKS_SLAB.get());
        heatRecipe(pWriter, Items.BRICK_STAIRS, ModBlocks.CRACKED_BRICKS_STAIRS.get());
        heatRecipe(pWriter, Items.BRICK_WALL, ModBlocks.CRACKED_BRICKS_WALL.get());
        heatRecipe(pWriter, ModBlocks.MOSSY_ANDESITE_BRICKS.get(), ModBlocks.ANDESITE_BRICKS.get());
        heatRecipe(pWriter, ModBlocks.MOSSY_ANDESITE_BRICKS_SLAB.get(), ModBlocks.ANDESITE_BRICKS_SLAB.get());
        heatRecipe(pWriter, ModBlocks.MOSSY_ANDESITE_BRICKS_STAIRS.get(), ModBlocks.ANDESITE_BRICKS_STAIRS.get());
        heatRecipe(pWriter, ModBlocks.MOSSY_ANDESITE_BRICKS_WALL.get(), ModBlocks.ANDESITE_BRICKS_WALL.get());
        heatRecipe(pWriter, ModBlocks.MOSSY_GRANITE_BRICKS.get(), ModBlocks.GRANITE_BRICKS.get());
        heatRecipe(pWriter, ModBlocks.MOSSY_GRANITE_BRICKS_SLAB.get(), ModBlocks.GRANITE_BRICKS_SLAB.get());
        heatRecipe(pWriter, ModBlocks.MOSSY_GRANITE_BRICKS_STAIRS.get(), ModBlocks.GRANITE_BRICKS_STAIRS.get());
        heatRecipe(pWriter, ModBlocks.MOSSY_GRANITE_BRICKS_WALL.get(), ModBlocks.GRANITE_BRICKS_WALL.get());
        heatRecipe(pWriter, ModBlocks.MOSSY_DIORITE_BRICKS.get(), ModBlocks.DIORITE_BRICKS.get());
        heatRecipe(pWriter, ModBlocks.MOSSY_DIORITE_BRICKS_SLAB.get(), ModBlocks.DIORITE_BRICKS_SLAB.get());
        heatRecipe(pWriter, ModBlocks.MOSSY_DIORITE_BRICKS_STAIRS.get(), ModBlocks.DIORITE_BRICKS_STAIRS.get());
        heatRecipe(pWriter, ModBlocks.MOSSY_DIORITE_BRICKS_WALL.get(), ModBlocks.DIORITE_BRICKS_WALL.get());
        heatRecipe(pWriter, ModBlocks.MOSSY_BRICKS.get(), Blocks.BRICKS);
        heatRecipe(pWriter, ModBlocks.MOSSY_BRICKS_SLAB.get(), Blocks.BRICK_SLAB);
        heatRecipe(pWriter, ModBlocks.MOSSY_BRICKS_STAIRS.get(), Blocks.BRICK_STAIRS);
        heatRecipe(pWriter, ModBlocks.MOSSY_BRICKS_WALL.get(), Blocks.BRICK_WALL);
        heatRecipe(pWriter, Blocks.MOSSY_STONE_BRICKS, Blocks.STONE_BRICKS);
        heatRecipe(pWriter, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.STONE_BRICK_SLAB);
        heatRecipe(pWriter, Blocks.MOSSY_STONE_BRICK_STAIRS, Blocks.STONE_BRICK_STAIRS);
        heatRecipe(pWriter, Blocks.MOSSY_STONE_BRICK_WALL, Blocks.STONE_BRICK_WALL);
        heatRecipe(pWriter, ModItems.TROUT.get(), ModItems.COOKED_TROUT.get());
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS,
                ModItems.TORCH_UNLIT.get(), 4).define('#', Items.STICK)
                .define('X',
                        Ingredient.of(Items.COAL, Items.CHARCOAL))
                .pattern("X")
                .pattern("#")
                .unlockedBy("has_stone_pickaxe",
                        has(Items.STONE_PICKAXE)).save(pWriter,
                        Helpers.identifier(RecipeCategory.DECORATIONS.getFolderName())
                        + "/" + getItemName(ModItems.TORCH_UNLIT.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS,
                ModItems.SOUL_TORCH_UNLIT.get(), 4).define('X',
                Ingredient.of(Items.COAL, Items.CHARCOAL))
                .define('#', Items.STICK)
                .define('S', ItemTags.SOUL_FIRE_BASE_BLOCKS)
                .pattern("X")
                .pattern("#")
                .pattern("S")
                .unlockedBy("has_soul_sand",
                        has(ItemTags.SOUL_FIRE_BASE_BLOCKS)).save(pWriter,
                        Helpers.identifier(RecipeCategory.DECORATIONS.getFolderName())
                                + "/" + getItemName(ModItems.SOUL_TORCH_UNLIT.get()));

        nbtPebbleRecipe(pWriter, Blocks.ANDESITE, "andesite_pebble");
        nbtPebbleRecipe(pWriter, Blocks.BASALT, "basalt_pebble");
        nbtPebbleRecipe(pWriter, Blocks.BLACKSTONE, "blackstone_pebble");
        nbtPebbleRecipe(pWriter, Blocks.COBBLED_DEEPSLATE, "deepslate_pebble");
        nbtPebbleRecipe(pWriter, Blocks.COBBLESTONE, "cobblestone_pebble");
        nbtPebbleRecipe(pWriter, Blocks.DIORITE, "diorite_pebble");
        nbtPebbleRecipe(pWriter, Blocks.END_STONE, "end_stone_pebble");
        nbtPebbleRecipe(pWriter, Blocks.GRANITE, "granite_pebble");
        nbtPebbleRecipe(pWriter, Blocks.NETHERRACK, "netherrack_pebble");
        nbtPebbleRecipe(pWriter, ModBlocks.QUARTZ_SANDSTONE.get(), "quartz_sandstone_pebble");
        nbtPebbleRecipe(pWriter, Blocks.RED_SANDSTONE, "red_sandstone_pebble");
        nbtPebbleRecipe(pWriter, Blocks.SANDSTONE, "sandstone_pebble");
        nbtPebbleRecipe(pWriter, ModBlocks.SOUL_SANDSTONE.get(), "soul_sandstone_pebble");
        nbtPebbleButton(pWriter, Items.STONE_BUTTON, "cobblestone_pebble");
        nbtPebbleButton(pWriter, Items.POLISHED_BLACKSTONE_BUTTON, "blackstone_pebble");
        modNineBlockStorageRecipes(pWriter, RecipeCategory.BUILDING_BLOCKS, Items.QUARTZ, RecipeCategory.MISC, Items.QUARTZ_BLOCK);
        fourBlockStorageRecipes(pWriter, RecipeCategory.BUILDING_BLOCKS, Items.FLINT, RecipeCategory.MISC, ModBlocks.FLINT_BLOCK.get());
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                        Blocks.MOSS_BLOCK).define('#', ModItems.MOSS_CLUMP.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_" + ModItems.MOSS_CLUMP.get(), has(ModItems.MOSS_CLUMP.get()))
                .save(pWriter, Helpers.identifier(RecipeCategory.MISC.getFolderName()) + "/" + getItemName(Blocks.MOSS_BLOCK));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                        ModItems.UNFIRED_CLAY_BRICK.get())
                .define('#', Items.CLAY_BALL)
                .pattern("##")
                .unlockedBy("has_" + Items.CLAY_BALL, has(Items.CLAY_BALL))
                .save(pWriter, Helpers.identifier(RecipeCategory.MISC.getFolderName()) + "/" + getItemName(ModItems.UNFIRED_CLAY_BRICK.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                        ModItems.UNFIRED_CLAY_BOWL.get())
                .define('#', Items.CLAY_BALL)
                .pattern("# #")
                .pattern(" # ")
                .unlockedBy("has_" + Items.CLAY_BALL, has(Items.CLAY_BALL))
                .save(pWriter, Helpers.identifier(RecipeCategory.MISC.getFolderName()) + "/" + getItemName(ModItems.UNFIRED_CLAY_BOWL.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                        ModItems.UNFIRED_CLAY_FLOWER_POT.get())
                .define('#', Items.CLAY_BALL)
                .pattern("# #")
                .pattern("###")
                .unlockedBy("has_" + Items.CLAY_BALL, has(Items.CLAY_BALL))
                .save(pWriter, Helpers.identifier(RecipeCategory.MISC.getFolderName()) + "/" + getItemName(ModItems.UNFIRED_CLAY_FLOWER_POT.get()));
        singleStoneCutting(pWriter, Items.COBBLESTONE_SLAB, Items.COBBLESTONE_STAIRS);
        singleStoneCutting(pWriter, Items.GRANITE_SLAB, Items.GRANITE_STAIRS);
        singleStoneCutting(pWriter, Items.DIORITE_SLAB, Items.DIORITE_STAIRS);
        singleStoneCutting(pWriter, Items.ANDESITE_SLAB, Items.ANDESITE_STAIRS);
        singleStoneCutting(pWriter, Items.RED_SANDSTONE_SLAB, Items.RED_SANDSTONE_STAIRS);
        singleStoneCutting(pWriter, Items.DEEPSLATE_BRICK_SLAB, Items.DEEPSLATE_BRICK_STAIRS);
        singleStoneCutting(pWriter, Items.DEEPSLATE_TILE_SLAB, Items.DEEPSLATE_TILE_STAIRS);
        singleStoneCutting(pWriter, Items.COBBLED_DEEPSLATE_SLAB, Items.COBBLED_DEEPSLATE_STAIRS);
        singleStoneCutting(pWriter, Items.POLISHED_DEEPSLATE_SLAB, Items.POLISHED_DEEPSLATE_STAIRS);
        singleStoneCutting(pWriter, Items.SANDSTONE_SLAB, Items.SANDSTONE_STAIRS);
        singleStoneCutting(pWriter, Items.QUARTZ_SLAB, Items.QUARTZ_STAIRS);
        singleStoneCutting(pWriter, Items.SMOOTH_QUARTZ_SLAB, Items.SMOOTH_QUARTZ_STAIRS);
        singleStoneCutting(pWriter, Items.STONE_SLAB, Items.STONE_STAIRS);
        singleStoneCutting(pWriter, Items.MOSSY_STONE_BRICK_SLAB, Items.MOSSY_STONE_BRICK_STAIRS);
        singleStoneCutting(pWriter, Items.MOSSY_COBBLESTONE_SLAB, Items.MOSSY_COBBLESTONE_STAIRS);
        singleStoneCutting(pWriter, Items.SMOOTH_STONE_SLAB, ModBlocks.SMOOTH_STONE_STAIRS.get());
        singleStoneCutting(pWriter, Items.BRICK_SLAB, Items.BRICK_STAIRS);
        singleStoneCutting(pWriter, Items.POLISHED_ANDESITE_SLAB, Items.POLISHED_ANDESITE_STAIRS);
        singleStoneCutting(pWriter, Items.STONE_BRICK_SLAB, Items.STONE_BRICK_STAIRS);
        singleStoneCutting(pWriter, Items.POLISHED_GRANITE_SLAB, Items.POLISHED_GRANITE_STAIRS);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.STONE_WALL.get(), Blocks.STONE, 2);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.STONE_PRESSURE_PLATE, Blocks.STONE, 4);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.SMOOTH_STONE, Blocks.STONE, 1);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.COBBLESTONE_WALL, Blocks.COBBLESTONE, 2);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.STONE_BRICK_WALL, Blocks.STONE_BRICKS, 2);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.DIORITE_WALL, Blocks.DIORITE, 2);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.SMOOTH_STONE_STAIRS.get(), Blocks.SMOOTH_STONE, 1);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.SMOOTH_STONE_WALL.get(), Blocks.SMOOTH_STONE, 2);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.MOSSY_COBBLESTONE_WALL, Blocks.MOSSY_COBBLESTONE, 2);
        modStoneCutting(pWriter, ModBlocks.CRACKED_STONE_BRICKS_SLAB.get(),
                ModBlocks.CRACKED_STONE_BRICKS_STAIRS.get(),
                ModBlocks.CRACKED_STONE_BRICKS_WALL.get(),
                Items.CRACKED_STONE_BRICKS);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.MOSSY_STONE_BRICK_WALL, Blocks.MOSSY_STONE_BRICKS, 2);
        modStoneCutting(pWriter,
                ModBlocks.GRANITE_BRICKS_SLAB.get(), ModBlocks.GRANITE_BRICKS_STAIRS.get(),
                ModBlocks.GRANITE_BRICKS_WALL.get(), ModBlocks.GRANITE_BRICKS.get());
        modStoneCutting(pWriter,
                ModBlocks.MOSSY_GRANITE_BRICKS_SLAB.get(), ModBlocks.MOSSY_GRANITE_BRICKS_STAIRS.get(),
                ModBlocks.MOSSY_GRANITE_BRICKS_WALL.get(), ModBlocks.MOSSY_GRANITE_BRICKS.get());
        modStoneCutting(pWriter,
                ModBlocks.CRACKED_GRANITE_BRICKS_SLAB.get(), ModBlocks.CRACKED_GRANITE_BRICKS_STAIRS.get(),
                ModBlocks.CRACKED_GRANITE_BRICKS_WALL.get(), ModBlocks.CRACKED_GRANITE_BRICKS.get());
        modStoneCutting(pWriter,
                ModBlocks.CRACKED_BRICKS_SLAB.get(), ModBlocks.CRACKED_BRICKS_STAIRS.get(),
                ModBlocks.CRACKED_BRICKS_WALL.get(), ModBlocks.CRACKED_BRICKS.get());
        modStoneCutting(pWriter,
                ModBlocks.MOSSY_BRICKS_SLAB.get(), ModBlocks.MOSSY_BRICKS_STAIRS.get(),
                ModBlocks.MOSSY_BRICKS_WALL.get(), ModBlocks.MOSSY_BRICKS.get());
        modStoneCutting(pWriter,
                Blocks.POLISHED_DIORITE_SLAB, Blocks.POLISHED_DIORITE_STAIRS,
                ModBlocks.POLISHED_DIORITE_WALL.get(), Blocks.POLISHED_DIORITE);
        modStoneCutting(pWriter,
                ModBlocks.DIORITE_BRICKS_SLAB.get(), ModBlocks.DIORITE_BRICKS_STAIRS.get(),
                ModBlocks.DIORITE_BRICKS_WALL.get(), ModBlocks.DIORITE_BRICKS.get());
        modStoneCutting(pWriter,
                ModBlocks.CRACKED_DIORITE_BRICKS_SLAB.get(), ModBlocks.CRACKED_DIORITE_BRICKS_STAIRS.get(),
                ModBlocks.CRACKED_DIORITE_BRICKS_WALL.get(), ModBlocks.CRACKED_DIORITE_BRICKS.get());
        modStoneCutting(pWriter,
                ModBlocks.MOSSY_DIORITE_BRICKS_SLAB.get(), ModBlocks.MOSSY_DIORITE_BRICKS_STAIRS.get(),
                ModBlocks.MOSSY_DIORITE_BRICKS_WALL.get(), ModBlocks.MOSSY_DIORITE_BRICKS.get());
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.BRICK_WALL, Blocks.BRICKS, 2);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.GRANITE_WALL, Blocks.GRANITE, 2);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.POLISHED_GRANITE_WALL.get(), Blocks.POLISHED_GRANITE, 2);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.POLISHED_ANDESITE_WALL.get(), Blocks.POLISHED_ANDESITE, 2);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.ANDESITE_WALL, Blocks.ANDESITE, 2);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.POLISHED_GRANITE_SLAB, Blocks.POLISHED_GRANITE, 2);
        modBricksCutting(pWriter, ModBlocks.DIORITE_BRICKS.get(), Items.DIORITE);
        modBricksCutting(pWriter, ModBlocks.CHISELED_DIORITE_BRICKS.get(), ModBlocks.DIORITE_BRICKS.get());
        modBricksCutting(pWriter, ModBlocks.GRANITE_BRICKS.get(), Blocks.GRANITE);
        modBricksCutting(pWriter, ModBlocks.CHISELED_GRANITE_BRICKS.get(), ModBlocks.GRANITE_BRICKS.get());
        modBricksCutting(pWriter, ModBlocks.ANDESITE_BRICKS.get(), Blocks.ANDESITE);
        modBricksCutting(pWriter, ModBlocks.CHISELED_ANDESITE_BRICKS.get(), ModBlocks.ANDESITE_BRICKS.get());
        modStoneCutting(pWriter,
                ModBlocks.ANDESITE_BRICKS_SLAB.get(), ModBlocks.ANDESITE_BRICKS_STAIRS.get(),
                ModBlocks.ANDESITE_BRICKS_WALL.get(), ModBlocks.ANDESITE_BRICKS.get());
        modStoneCutting(pWriter,
                ModBlocks.CRACKED_ANDESITE_BRICKS_SLAB.get(), ModBlocks.CRACKED_ANDESITE_BRICKS_STAIRS.get(),
                ModBlocks.CRACKED_ANDESITE_BRICKS_WALL.get(), ModBlocks.CRACKED_ANDESITE_BRICKS.get());
        modStoneCutting(pWriter,
                ModBlocks.MOSSY_ANDESITE_BRICKS_SLAB.get(), ModBlocks.MOSSY_ANDESITE_BRICKS_STAIRS.get(),
                ModBlocks.MOSSY_ANDESITE_BRICKS_WALL.get(), ModBlocks.MOSSY_ANDESITE_BRICKS.get());
        modNineBlockStorageRecipes(pWriter, RecipeCategory.BUILDING_BLOCKS, ModItems.SULFUR.get(),
                RecipeCategory.MISC, ModBlocks.SULFUR_BLOCK.get());
        modNineBlockStorageRecipes(pWriter, RecipeCategory.BUILDING_BLOCKS, ModItems.NITER.get(),
                RecipeCategory.MISC, ModBlocks.NITER_BLOCK.get());
        modNineBlockStorageRecipes(pWriter, RecipeCategory.BUILDING_BLOCKS, ModItems.TALLOW.get(),
                RecipeCategory.MISC, ModBlocks.TALLOW_BLOCK.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,
                Items.GUNPOWDER, 3)
                .requires(Items.CHARCOAL, 4)
                .requires(ModItems.SULFUR.get(), 2)
                .requires(ModItems.NITER.get(), 3)
                .unlockedBy("has_" + Items.CHARCOAL,
                        has(Items.CHARCOAL)).save(pWriter,
                        Helpers.identifier(RecipeCategory.MISC.getFolderName()) + "/"  + getItemName(Items.GUNPOWDER));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.STRING)
                .define('#', ModBlocks.CATTAIL.get())
                .pattern("##")
                .unlockedBy("has_" + ModBlocks.CATTAIL.get(), has(ModBlocks.CATTAIL.get()))
                .save(pWriter, Helpers.identifier(RecipeCategory.MISC.getFolderName()) + "/" + getItemName(Items.STRING) + "_from_" +  getItemName(ModBlocks.CATTAIL.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.PAPER)
                .define('#', ModBlocks.CATTAIL.get())
                .pattern("###")
                .unlockedBy("has_" + ModBlocks.CATTAIL.get(), has(ModBlocks.CATTAIL.get()))
                .save(pWriter, Helpers.identifier(RecipeCategory.MISC.getFolderName()) + "/" + getItemName(Items.PAPER) + "_from_" +  getItemName(ModBlocks.CATTAIL.get()));
        modNineBlockStorageRecipes(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CATTAIL.get(),
                RecipeCategory.MISC, ModBlocks.THATCH.get());
        modNineBlockStorageRecipes(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.REED.get(),
                RecipeCategory.MISC, ModBlocks.THATCH.get());
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.STRING)
                .define('#', Items.VINE)
                .pattern("###")
                .unlockedBy("has_" + Items.VINE, has(Items.VINE))
                .save(pWriter, Helpers.identifier(RecipeCategory.MISC.getFolderName()) + "/" + getItemName(Items.STRING) + "_from_" +  getItemName(Items.VINE));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.STRING)
                .define('#', ModBlocks.REED.get())
                .pattern("##")
                .unlockedBy("has_" + ModBlocks.REED.get(), has(ModBlocks.REED.get()))
                .save(pWriter, Helpers.identifier(RecipeCategory.MISC.getFolderName()) + "/" + getItemName(Items.STRING) + "_from_" +  getItemName(ModBlocks.REED.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.PAPER)
                .define('#', ModBlocks.REED.get())
                .pattern("###")
                .unlockedBy("has_" + ModBlocks.REED.get(), has(ModBlocks.REED.get()))
                .save(pWriter, Helpers.identifier(RecipeCategory.MISC.getFolderName()) + "/" + getItemName(Items.PAPER) + "_from_" +  getItemName(ModBlocks.REED.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.STRING)
                .define('#', Items.TWISTING_VINES)
                .pattern("###")
                .unlockedBy("has_" + Items.TWISTING_VINES, has(Items.TWISTING_VINES))
                .save(pWriter, Helpers.identifier(RecipeCategory.MISC.getFolderName()) + "/" + getItemName(Items.STRING) + "_from_" +  getItemName(Items.TWISTING_VINES));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.STRING)
                .define('#', Items.WEEPING_VINES)
                .pattern("###")
                .unlockedBy("has_" + Items.WEEPING_VINES, has(Items.WEEPING_VINES))
                .save(pWriter, Helpers.identifier(RecipeCategory.MISC.getFolderName()) + "/" + getItemName(Items.STRING) + "_from_" +  getItemName(Items.WEEPING_VINES));
        nbtPickAxe(pWriter, "cobblestone_awl");
        nbtPickAxe(pWriter, "deepslate_awl");
        nbtPickAxe(pWriter, "flint_awl");
        nbtPickAxe(pWriter, "blackstone_awl");
        nbtPickAxe(pWriter, "quartz_awl");
        nbtShovel(pWriter, "cobblestone_adze");
        nbtShovel(pWriter, "deepslate_adze");
        nbtShovel(pWriter, "flint_adze");
        nbtShovel(pWriter, "blackstone_adze");
        nbtShovel(pWriter, "quartz_adze");
        nbtAxe(pWriter, "cobblestone_chopper");
        nbtAxe(pWriter, "deepslate_chopper");
        nbtAxe(pWriter, "flint_chopper");
        nbtAxe(pWriter, "blackstone_chopper");
        nbtAxe(pWriter, "quartz_chopper");
        nbtDagger(pWriter, "cobblestone_scraper");
        nbtDagger(pWriter, "deepslate_scraper");
        nbtDagger(pWriter, "flint_scraper");
        nbtDagger(pWriter, "blackstone_scraper");
        nbtDagger(pWriter, "quartz_scraper");
        nbtHammer(pWriter, "cobblestone_pebble");
        nbtHammer(pWriter, "deepslate_pebble");
        nbtHammer(pWriter, "flint_pebble");
        nbtHammer(pWriter, "blackstone_pebble");
        nbtHammer(pWriter, Items.QUARTZ, "quartz_pebble");
        nbtJavelin(pWriter, "cobblestone_point");
        nbtJavelin(pWriter, "deepslate_point");
        nbtJavelin(pWriter, "flint_point");
        nbtJavelin(pWriter, "blackstone_point");
        nbtJavelin(pWriter, "quartz_point");
        stoneButton(pWriter, Blocks.STONE, Items.STONE_BUTTON);
        stoneButton(pWriter, Blocks.SMOOTH_STONE, ModBlocks.SMOOTH_STONE_BUTTON.get());
        stoneButton(pWriter, Blocks.POLISHED_ANDESITE, ModBlocks.POLISHED_ANDESITE_BUTTON.get());
        stoneButton(pWriter, Blocks.POLISHED_DIORITE, ModBlocks.POLISHED_DIORITE_BUTTON.get());
        stoneButton(pWriter, Blocks.POLISHED_GRANITE, ModBlocks.POLISHED_GRANITE_BUTTON.get());
        stoneButton(pWriter, Blocks.POLISHED_BLACKSTONE, ModBlocks.BLACKSTONE_BUTTON.get());
        stonePressurePlate(pWriter, Blocks.POLISHED_ANDESITE, ModBlocks.POLISHED_ANDESITE_PRESSURE_PLATE.get());
        stonePressurePlate(pWriter, Blocks.POLISHED_DIORITE, ModBlocks.POLISHED_DIORITE_PRESSURE_PLATE.get());
        stonePressurePlate(pWriter, Blocks.POLISHED_GRANITE, ModBlocks.POLISHED_GRANITE_PRESSURE_PLATE.get());
        stonePressurePlate(pWriter, Blocks.POLISHED_BLACKSTONE, ModBlocks.BLACKSTONE_PRESSURE_PLATE.get());
        stonePressurePlate(pWriter, Blocks.SMOOTH_STONE, ModBlocks.SMOOTH_STONE_PRESSURE_PLATE.get());
        singleStoneCutting(pWriter, ModBlocks.SMOOTH_STONE_BUTTON.get(), Blocks.STONE_BUTTON);
        singleStoneCutting(pWriter, ModBlocks.SMOOTH_STONE_PRESSURE_PLATE.get(), Blocks.STONE_PRESSURE_PLATE);
        singleStoneCutting(pWriter,Blocks.POLISHED_BLACKSTONE_BUTTON, ModBlocks.BLACKSTONE_BUTTON.get());
        singleStoneCutting(pWriter, Blocks.POLISHED_BLACKSTONE_PRESSURE_PLATE, ModBlocks.BLACKSTONE_PRESSURE_PLATE.get());
        heatRecipe(pWriter, Blocks.MOSSY_COBBLESTONE_STAIRS, Blocks.STONE_STAIRS);
        heatRecipe(pWriter, Blocks.MOSSY_COBBLESTONE_SLAB, Blocks.STONE_SLAB);
        heatRecipe(pWriter, Blocks.MOSSY_COBBLESTONE_WALL, ModBlocks.STONE_WALL.get());
        singleStoneCutting(pWriter, Blocks.STONE_STAIRS, Blocks.COBBLESTONE_STAIRS);
        singleStoneCutting(pWriter, Blocks.STONE_SLAB, Blocks.COBBLESTONE_SLAB);
        singleStoneCutting(pWriter, ModBlocks.STONE_WALL.get(), Blocks.COBBLESTONE_WALL);
        singleStoneCutting(pWriter, Blocks.SMOOTH_STONE_SLAB, Blocks.STONE_SLAB);
        singleStoneCutting(pWriter, ModBlocks.SMOOTH_STONE_STAIRS.get(), Blocks.STONE_STAIRS);
        singleStoneCutting(pWriter, ModBlocks.SMOOTH_STONE_WALL.get(),  ModBlocks.STONE_WALL.get());
        singleStoneCutting(pWriter, Blocks.POLISHED_ANDESITE_SLAB, Blocks.ANDESITE_SLAB);
        singleStoneCutting(pWriter, Blocks.POLISHED_ANDESITE_STAIRS, Blocks.ANDESITE_STAIRS);
        singleStoneCutting(pWriter, ModBlocks.POLISHED_ANDESITE_WALL.get(), Blocks.ANDESITE_WALL);
        singleStoneCutting(pWriter, Blocks.POLISHED_GRANITE_SLAB, Blocks.GRANITE_SLAB);
        singleStoneCutting(pWriter, Blocks.POLISHED_GRANITE_STAIRS, Blocks.GRANITE_STAIRS);
        singleStoneCutting(pWriter, ModBlocks.POLISHED_GRANITE_WALL.get(), Blocks.GRANITE_WALL);
        singleStoneCutting(pWriter, Blocks.POLISHED_DIORITE_SLAB, Blocks.DIORITE_SLAB);
        singleStoneCutting(pWriter, Blocks.POLISHED_DIORITE_STAIRS, Blocks.DIORITE_STAIRS);
        singleStoneCutting(pWriter, ModBlocks.POLISHED_DIORITE_WALL.get(), Blocks.DIORITE_WALL);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.COBBLED_DEEPSLATE_WALL, Blocks.COBBLED_DEEPSLATE, 2);
        heatRecipe(pWriter, Blocks.COBBLED_DEEPSLATE_STAIRS, ModBlocks.DEEPSLATE_STAIRS.get());
        heatRecipe(pWriter, Blocks.COBBLED_DEEPSLATE_SLAB, ModBlocks.DEEPSLATE_SLAB.get());
        heatRecipe(pWriter, Blocks.COBBLED_DEEPSLATE_WALL, ModBlocks.DEEPSLATE_WALL.get());
        modStoneCutting(pWriter, ModBlocks.DEEPSLATE_SLAB.get(), ModBlocks.DEEPSLATE_STAIRS.get(),
                ModBlocks.DEEPSLATE_WALL.get(), Blocks.DEEPSLATE);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.CHISELED_DEEPSLATE, Blocks.DEEPSLATE, 1);
        heatRecipe(pWriter, ModBlocks.MOSSY_COBBLED_DEEPSLATE.get(), Blocks.COBBLED_DEEPSLATE);
        heatRecipe(pWriter, ModBlocks.MOSSY_COBBLED_DEEPSLATE_SLAB.get(), Blocks.COBBLED_DEEPSLATE_SLAB);
        heatRecipe(pWriter, ModBlocks.MOSSY_COBBLED_DEEPSLATE_STAIRS.get(), Blocks.COBBLED_DEEPSLATE_STAIRS);
        heatRecipe(pWriter, ModBlocks.MOSSY_COBBLED_DEEPSLATE_WALL.get(), Blocks.COBBLED_DEEPSLATE_WALL);
        modStoneCutting(pWriter, ModBlocks.MOSSY_COBBLED_DEEPSLATE_SLAB.get(),
                ModBlocks.MOSSY_COBBLED_DEEPSLATE_STAIRS.get(),
                ModBlocks.MOSSY_COBBLED_DEEPSLATE_WALL.get(),
                ModBlocks.MOSSY_COBBLED_DEEPSLATE.get());
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.POLISHED_DEEPSLATE, Blocks.DEEPSLATE, 1);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.DEEPSLATE_BRICK_WALL, Blocks.DEEPSLATE_BRICKS, 2);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.DEEPSLATE_BRICKS, Blocks.DEEPSLATE, 1);
        heatRecipe(pWriter, ModBlocks.MOSSY_DEEPSLATE_BRICKS.get(), Blocks.DEEPSLATE_BRICKS);
        heatRecipe(pWriter, ModBlocks.MOSSY_DEEPSLATE_BRICKS_SLAB.get(), Blocks.DEEPSLATE_BRICK_SLAB);
        heatRecipe(pWriter, ModBlocks.MOSSY_DEEPSLATE_BRICKS_STAIRS.get(), Blocks.DEEPSLATE_BRICK_STAIRS);
        heatRecipe(pWriter, ModBlocks.MOSSY_DEEPSLATE_BRICKS_WALL.get(), Blocks.DEEPSLATE_BRICK_WALL);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.DEEPSLATE_TILE_WALL, Blocks.DEEPSLATE_TILES, 2);
        modStoneCutting(pWriter,
                ModBlocks.CRACKED_DEEPSLATE_BRICKS_SLAB.get(), ModBlocks.CRACKED_DEEPSLATE_BRICKS_STAIRS.get(),
                ModBlocks.CRACKED_DEEPSLATE_BRICKS_WALL.get(), Blocks.CRACKED_DEEPSLATE_BRICKS);
        heatRecipe(pWriter, ModBlocks.CRACKED_DEEPSLATE_BRICKS_WALL.get(), ModBlocks.CRACKED_DEEPSLATE_BRICKS_WALL.get());
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.DEEPSLATE_TILES, Blocks.DEEPSLATE, 1);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.POLISHED_DEEPSLATE_WALL, Blocks.POLISHED_DEEPSLATE, 2);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.POLISHED_DEEPSLATE_SLAB, Blocks.COBBLED_DEEPSLATE_SLAB, 1);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.POLISHED_DEEPSLATE_STAIRS, Blocks.COBBLED_DEEPSLATE_STAIRS, 1);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.POLISHED_DEEPSLATE_WALL, Blocks.COBBLED_DEEPSLATE_WALL, 1);
        heatRecipe(pWriter, Blocks.DEEPSLATE_BRICK_SLAB, ModBlocks.CRACKED_DEEPSLATE_BRICKS_SLAB.get());
        heatRecipe(pWriter, Blocks.DEEPSLATE_BRICK_STAIRS, ModBlocks.CRACKED_DEEPSLATE_BRICKS_STAIRS.get());
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.DEEPSLATE_TILE_STAIRS, Blocks.DEEPSLATE_TILES, 2);
        heatRecipe(pWriter, ModBlocks.MOSSY_DEEPSLATE_TILES.get(), Blocks.DEEPSLATE_TILES);
        heatRecipe(pWriter, ModBlocks.MOSSY_DEEPSLATE_TILES_STAIRS.get(), Blocks.DEEPSLATE_TILE_STAIRS);
        heatRecipe(pWriter, ModBlocks.MOSSY_DEEPSLATE_TILES_SLAB.get(), Blocks.DEEPSLATE_TILE_SLAB);
        heatRecipe(pWriter, ModBlocks.MOSSY_DEEPSLATE_TILES_WALL.get(), Blocks.DEEPSLATE_TILE_WALL);
        modStoneCutting(pWriter,
                ModBlocks.MOSSY_DEEPSLATE_BRICKS_SLAB.get(),
                ModBlocks.MOSSY_DEEPSLATE_BRICKS_STAIRS.get(),
                ModBlocks.MOSSY_DEEPSLATE_BRICKS_WALL.get(),
                ModBlocks.MOSSY_DEEPSLATE_BRICKS.get());
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.CHISELED_POLISHED_DEEPSLATE.get(), Blocks.POLISHED_DEEPSLATE, 1);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.CHISELED_DEEPSLATE_TILES.get(), Blocks.DEEPSLATE_TILES, 1);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.CHISELED_DEEPSLATE_BRICKS.get(), Blocks.DEEPSLATE_BRICKS, 1);
        modStoneCutting(pWriter,
                ModBlocks.CRACKED_DEEPSLATE_TILES_SLAB.get(),
                ModBlocks.CRACKED_DEEPSLATE_TILES_STAIRS.get(),
                ModBlocks.CRACKED_DEEPSLATE_TILES_WALL.get(),
                Blocks.CRACKED_DEEPSLATE_TILES);
        heatRecipe(pWriter, Blocks.DEEPSLATE_TILE_STAIRS, ModBlocks.CRACKED_DEEPSLATE_TILES_STAIRS.get());
        heatRecipe(pWriter, Blocks.DEEPSLATE_TILE_SLAB, ModBlocks.CRACKED_DEEPSLATE_TILES_SLAB.get());
        heatRecipe(pWriter, Blocks.DEEPSLATE_TILE_WALL, ModBlocks.CRACKED_DEEPSLATE_TILES_WALL.get());
        stoneButton(pWriter, Blocks.DEEPSLATE, ModBlocks.DEEPSLATE_BUTTON.get());
        stoneButton(pWriter, Blocks.POLISHED_DEEPSLATE, ModBlocks.POLISHED_DEEPSLATE_BUTTON.get());
        stonePressurePlate(pWriter, Blocks.DEEPSLATE, ModBlocks.DEEPSLATE_PRESSURE_PLATE.get());
        stonePressurePlate(pWriter, Blocks.POLISHED_DEEPSLATE, ModBlocks.POLISHED_DEEPSLATE_PRESSURE_PLATE.get());
    }

    protected void stonePressurePlate(Consumer<FinishedRecipe> pWriter, ItemLike stoneLike, ItemLike pressurePlate) {
        SingleItemRecipeBuilder
                .stonecutting(Ingredient.of(stoneLike), RecipeCategory.REDSTONE,
                        pressurePlate, 4)
                .unlockedBy("has_" + stoneLike,
                        has(stoneLike)).save(pWriter,
                        Helpers.identifier(RecipeCategory.REDSTONE.getFolderName()) + "/"  + getItemName(pressurePlate) + "_from_" + getItemName(stoneLike) + "_stonecutting");
    }

    protected void stoneButton(Consumer<FinishedRecipe> pWriter, ItemLike stoneLike, ItemLike button) {
        SingleItemRecipeBuilder
                .stonecutting(Ingredient.of(stoneLike), RecipeCategory.REDSTONE,
                        button, 4)
                .unlockedBy("has_" + stoneLike,
                        has(stoneLike)).save(pWriter,
                        Helpers.identifier(RecipeCategory.REDSTONE.getFolderName()) + "/"  + getItemName(button) + "_from_" + getItemName(stoneLike) + "_stonecutting");
    }

    protected void nbtJavelin(Consumer<FinishedRecipe> pWriter, String meta) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,
                        ModItems.STONE_JAVELIN.get()).define('#',
                        StrictNBTIngredient.of(Util.make(() ->
                                {
                                    ItemStack stack = ModItems.POINT.get().getDefaultInstance();
                                    MetaItem.setMeta(stack, meta);
                                    return stack;
                                }
                        )))
                .define('Z', Items.STICK)
                .define('S', Items.STRING)
                .pattern(" S#")
                .pattern(" Z ")
                .pattern("Z  ")
                .unlockedBy("has_" + ModItems.POINT.get(), has(ModItems.POINT.get()))
                .save(pWriter, Helpers.identifier(RecipeCategory.TOOLS.getFolderName()) + "/"  + getItemName(ModItems.STONE_JAVELIN.get()) + "_from_" + meta);
    }

    protected void nbtHammer(Consumer<FinishedRecipe> pWriter, Item vanilla, String meta) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,
                        ModItems.STONE_HAMMER.get()).define('#',
                        StrictNBTIngredient.of(Util.make(() ->
                                {
                                    ItemStack stack = vanilla.getDefaultInstance();
                                    MetaItem.setMeta(stack, meta);
                                    return stack;
                                }
                        )))
                .define('Z', Items.STICK)
                .define('S', Items.STRING)
                .pattern("S#")
                .pattern("Z ")
                .unlockedBy("has_" + ModItems.PEBBLE.get(), has(ModItems.PEBBLE.get()))
                .save(pWriter, Helpers.identifier(RecipeCategory.TOOLS.getFolderName()) + "/"  + getItemName(ModItems.STONE_HAMMER.get()) + "_from_" + meta);
    }

    protected void nbtHammer(Consumer<FinishedRecipe> pWriter, String meta) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,
                        ModItems.STONE_HAMMER.get()).define('#',
                        StrictNBTIngredient.of(Util.make(() ->
                                {
                                    ItemStack stack = ModItems.PEBBLE.get().getDefaultInstance();
                                    MetaItem.setMeta(stack, meta);
                                    return stack;
                                }
                        )))
                .define('Z', Items.STICK)
                .define('S', Items.STRING)
                .pattern("S#")
                .pattern("Z ")
                .unlockedBy("has_" + ModItems.PEBBLE.get(), has(ModItems.PEBBLE.get()))
                .save(pWriter, Helpers.identifier(RecipeCategory.TOOLS.getFolderName()) + "/"  + getItemName(ModItems.STONE_HAMMER.get()) + "_from_" + meta);
    }

    protected void nbtDagger(Consumer<FinishedRecipe> pWriter, String meta) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,
                        ModItems.STONE_DAGGER.get()).define('#',
                        StrictNBTIngredient.of(Util.make(() ->
                                {
                                    ItemStack stack = ModItems.SCRAPER.get().getDefaultInstance();
                                    MetaItem.setMeta(stack, meta);
                                    return stack;
                                }
                        )))
                .define('Z', Items.STICK)
                .define('S', Items.STRING)
                .pattern("S#")
                .pattern("Z ")
                .unlockedBy("has_" + ModItems.SCRAPER.get(), has(ModItems.SCRAPER.get()))
                .save(pWriter, Helpers.identifier(RecipeCategory.TOOLS.getFolderName()) + "/"  + getItemName(ModItems.STONE_DAGGER.get()) + "_from_" + meta);
    }

    protected void nbtAxe(Consumer<FinishedRecipe> pWriter, String meta) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,
                        Items.STONE_AXE).define('#',
                        StrictNBTIngredient.of(Util.make(() ->
                                {
                                    ItemStack stack = ModItems.CHOPPER.get().getDefaultInstance();
                                    MetaItem.setMeta(stack, meta);
                                    return stack;
                                }
                        )))
                .define('Z', Items.STICK)
                .define('S', Items.STRING)
                .pattern("S#")
                .pattern("Z ")
                .unlockedBy("has_" + ModItems.CHOPPER.get(), has(ModItems.CHOPPER.get()))
                .save(pWriter, Helpers.identifier(RecipeCategory.TOOLS.getFolderName()) + "/"  + getItemName(Items.STONE_AXE) + "_from_" + meta);
    }

    protected void nbtPickAxe(Consumer<FinishedRecipe> pWriter, String meta) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,
                        Items.STONE_PICKAXE).define('#',
                        StrictNBTIngredient.of(Util.make(() ->
                                {
                                    ItemStack stack = ModItems.AWL.get().getDefaultInstance();
                                    MetaItem.setMeta(stack, meta);
                                    return stack;
                                }
                        )))
                .define('Z', Items.STICK)
                .define('S', Items.STRING)
                .pattern("S#")
                .pattern("Z ")
                .unlockedBy("has_" + ModItems.AWL.get(), has(ModItems.AWL.get()))
                .save(pWriter, Helpers.identifier(RecipeCategory.TOOLS.getFolderName()) + "/"  + getItemName(Items.STONE_PICKAXE) + "_from_" + meta);
    }

    protected void nbtShovel(Consumer<FinishedRecipe> pWriter, String meta) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,
                        Items.STONE_SHOVEL).define('#',
                        StrictNBTIngredient.of(Util.make(() ->
                                {
                                    ItemStack stack = ModItems.ADZE.get().getDefaultInstance();
                                    MetaItem.setMeta(stack, meta);
                                    return stack;
                                }
                        )))
                .define('Z', Items.STICK)
                .define('S', Items.STRING)
                .pattern("S#")
                .pattern("Z ")
                .unlockedBy("has_" + ModItems.ADZE.get(), has(ModItems.ADZE.get()))
                .save(pWriter, Helpers.identifier(RecipeCategory.TOOLS.getFolderName()) + "/"  + getItemName(Items.STONE_SHOVEL) + "_from_" + meta);
    }

    protected void modBricksCutting(Consumer<FinishedRecipe> pWriter, ItemLike brick, ItemLike material) {
       stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS, brick, material, 1);
    }

    protected void modStoneCutting(Consumer<FinishedRecipe> pWriter, ItemLike slab, ItemLike stair, ItemLike wall, ItemLike material) {
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS, slab, material, 2);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS, stair, material, 1);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS, wall, material, 2);
        singleStoneCutting(pWriter, slab, stair);
    }

    protected void singleStoneCutting(Consumer<FinishedRecipe> pWriter, ItemLike result, ItemLike material) {
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS, result, material, 1);
    }

    protected void nbtPebbleButton(Consumer<FinishedRecipe> pWriter, ItemLike pResult, String meta) {
        SingleItemRecipeBuilder
                .stonecutting(StrictNBTIngredient
                                .of(Util.make(() -> {
                                    ItemStack stack = ModItems.PEBBLE.get().getDefaultInstance();
                                    MetaItem.setMeta(stack, meta);
                                    return stack;
                        })), RecipeCategory.REDSTONE,
                        pResult, 1)
                .unlockedBy("has_" + ModItems.PEBBLE.get(),
                        has(ModItems.PEBBLE.get())).save(pWriter,
                        Helpers.identifier(RecipeCategory.REDSTONE.getFolderName()) + "/"  + getItemName(pResult) + "_from_" + meta + "_stonecutting");
    }

    protected void nbtPebbleRecipe(Consumer<FinishedRecipe> pWriter, Block block, String meta) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,
                        block).define('#',
                        StrictNBTIngredient.of(Util.make(() ->
                                {
                                    ItemStack stack = ModItems.PEBBLE.get().getDefaultInstance();
                                    MetaItem.setMeta(stack, meta);
                                    return stack;
                                }
                               )))
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_" + ModItems.PEBBLE.get(), has(ModItems.PEBBLE.get()))
                .save(pWriter, Helpers.identifier(RecipeCategory.BUILDING_BLOCKS.getFolderName()) + "/"  + getItemName(block) + "_from_" + "pebble");
    }

    protected void heatRecipe(Consumer<FinishedRecipe> pWriter, ItemLike ingredient, ItemLike result) {
        SimpleCookingRecipeBuilder.smelting
                (Ingredient.of(ingredient),
                        RecipeCategory.FOOD,
                        result,
                        0.35F, 200)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pWriter,Helpers.identifier("heat/"  + getItemName(result) + "_from_" + "smelting"));
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient),
                RecipeCategory.FOOD, result,
                0.35F, 200)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pWriter,Helpers.identifier("heat/"  + getItemName(result) + "_from_" + "smoking"));
    }

    protected static void modNineBlockStorageRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeCategory pUnpackedCategory, ItemLike pUnpacked, RecipeCategory pPackedCategory, ItemLike pPacked) {
        modNineBlockStorageRecipes(pFinishedRecipeConsumer, pUnpackedCategory, pUnpacked, pPackedCategory, pPacked, getSimpleRecipeName(pPacked), null, getSimpleRecipeName(pUnpacked), null);
    }

    protected static void modNineBlockStorageRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeCategory pUnpackedCategory, ItemLike pUnpacked, RecipeCategory pPackedCategory, ItemLike pPacked, String pPackedName, @Nullable String pPackedGroup, String pUnpackedName, @Nullable String pUnpackedGroup) {
        ShapelessRecipeBuilder.shapeless(pUnpackedCategory, pUnpacked, 9)
                .requires(pPacked).group(pUnpackedGroup)
                .unlockedBy(getHasName(pPacked), has(pPacked))
                .save(pFinishedRecipeConsumer, Helpers.identifier(pUnpackedCategory.getFolderName() + "/" + pUnpackedName + "_from_" + pPacked.asItem()));
        ShapedRecipeBuilder.shaped(pPackedCategory, pPacked)
                .define('#', pUnpacked)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .group(pPackedGroup)
                .unlockedBy(getHasName(pUnpacked), has(pUnpacked))
                .save(pFinishedRecipeConsumer, Helpers.identifier(pPackedCategory.getFolderName() + "/" + pPackedName + "_from_" + pUnpacked.asItem()));
    }

    protected static void fourBlockStorageRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeCategory pUnpackedCategory, ItemLike pUnpacked, RecipeCategory pPackedCategory, ItemLike pPacked) {
        fourBlockStorageRecipes(pFinishedRecipeConsumer,
                pUnpackedCategory, pUnpacked, pPackedCategory,
                pPacked, getSimpleRecipeName(pPacked), null,
                getSimpleRecipeName(pUnpacked), null);
    }

    protected static void fourBlockStorageRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeCategory pUnpackedCategory, ItemLike pUnpacked, RecipeCategory pPackedCategory, ItemLike pPacked, String pPackedName, @Nullable String pPackedGroup, String pUnpackedName, @Nullable String pUnpackedGroup) {
        ShapelessRecipeBuilder.shapeless(pUnpackedCategory, pUnpacked, 4)
                .requires(pPacked).group(pUnpackedGroup)
                .unlockedBy(getHasName(pPacked), has(pPacked))
                .save(pFinishedRecipeConsumer, Helpers.identifier(pUnpackedCategory.getFolderName() + "/" + pUnpackedName));
        ShapedRecipeBuilder.shaped(pPackedCategory, pPacked).define('#', pUnpacked)
                .pattern("##")
                .pattern("##").group(pPackedGroup).unlockedBy(getHasName(pUnpacked), has(pUnpacked))
                .save(pFinishedRecipeConsumer, Helpers.identifier(pPackedCategory.getFolderName() + "/"  + pPackedName));
    }

    protected static void stonecutterResultFrom(Consumer<FinishedRecipe> pWriter, RecipeCategory pCategory, ItemLike pResult, ItemLike pMaterial, int pResultCount) {
        SingleItemRecipeBuilder
                .stonecutting(Ingredient.of(pMaterial), pCategory,
                        pResult, pResultCount)
                .unlockedBy(getHasName(pMaterial),
                        has(pMaterial)).save(pWriter,
                        Helpers.identifier(pCategory.getFolderName() + "/"  + getConversionRecipeName(pResult, pMaterial) + "_stonecutting"));
    }
}
