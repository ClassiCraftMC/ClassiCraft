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
import net.minecraft.advancements.critereon.NbtPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.PartialNBTIngredient;
import net.minecraftforge.common.crafting.StrictNBTIngredient;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        cookRecipe(pWriter, Items.EGG, ModItems.COOKED_EGG.get());
        cookRecipe(pWriter, Items.ROTTEN_FLESH, Items.LEATHER);
        cookRecipe(pWriter, Items.STONE_BRICKS, Items.CRACKED_STONE_BRICKS);
        cookRecipe(pWriter, Items.MOSSY_COBBLESTONE, Items.STONE);
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
                .unlockedBy("has_" + ModItems.MOSS_CLUMP.get(), has(ModItems.PEBBLE.get()))
                .save(pWriter, Helpers.identifier(RecipeCategory.MISC.getFolderName()) + "/" + getItemName(Blocks.MOSS_BLOCK));
        stairToSlab(pWriter, Items.COBBLESTONE_SLAB, Items.COBBLESTONE_STAIRS);
        stairToSlab(pWriter, Items.GRANITE_SLAB, Items.GRANITE_STAIRS);
        stairToSlab(pWriter, Items.DIORITE_SLAB, Items.DIORITE_STAIRS);
        stairToSlab(pWriter, Items.ANDESITE_SLAB, Items.ANDESITE_STAIRS);
        stairToSlab(pWriter, Items.RED_SANDSTONE_SLAB, Items.RED_SANDSTONE_STAIRS);
        stairToSlab(pWriter, Items.DEEPSLATE_BRICK_SLAB, Items.DEEPSLATE_BRICK_STAIRS);
        stairToSlab(pWriter, Items.DEEPSLATE_TILE_SLAB, Items.DEEPSLATE_TILE_STAIRS);
        stairToSlab(pWriter, Items.COBBLED_DEEPSLATE_SLAB, Items.COBBLED_DEEPSLATE_STAIRS);
        stairToSlab(pWriter, Items.POLISHED_DEEPSLATE_SLAB, Items.POLISHED_DEEPSLATE_STAIRS);
        stairToSlab(pWriter, Items.SANDSTONE_SLAB, Items.SANDSTONE_STAIRS);
        stairToSlab(pWriter, Items.QUARTZ_SLAB, Items.QUARTZ_STAIRS);
        stairToSlab(pWriter, Items.SMOOTH_QUARTZ_SLAB, Items.SMOOTH_QUARTZ_STAIRS);
        stairToSlab(pWriter, Items.STONE_SLAB, Items.STONE_STAIRS);
        stairToSlab(pWriter, ModBlocks.CRACKED_STONE_BRICKS_SLAB.get(),
                ModBlocks.CRACKED_STONE_BRICKS_STAIRS.get());
        stairToSlab(pWriter, Items.MOSSY_STONE_BRICK_SLAB, Items.MOSSY_STONE_BRICK_STAIRS);
        stairToSlab(pWriter, Items.MOSSY_COBBLESTONE_SLAB, Items.MOSSY_COBBLESTONE_STAIRS);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.STONE_WALL.get(), Blocks.STONE, 2);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.STONE_PRESSURE_PLATE, Blocks.STONE, 4);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.SMOOTH_STONE, Blocks.STONE, 1);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.COBBLESTONE_WALL, Blocks.COBBLESTONE, 2);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.CHISELED_STONE_BRICKS, Blocks.STONE_BRICKS, 1);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS,
                Blocks.STONE_BRICK_WALL, Blocks.STONE_BRICKS, 2);
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
    }

    protected void modStoneCutting(Consumer<FinishedRecipe> pWriter, ItemLike slab, ItemLike stair, ItemLike wall, ItemLike material) {
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS, slab, material, 2);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS, stair, material, 1);
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS, wall, material, 2);
    }

    protected void stairToSlab(Consumer<FinishedRecipe> pWriter, ItemLike slab, ItemLike material) {
        stonecutterResultFrom(pWriter, RecipeCategory.BUILDING_BLOCKS, slab, material, 1);
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

    protected void cookRecipe(Consumer<FinishedRecipe> pWriter, Item ingredient, Item result) {
        SimpleCookingRecipeBuilder.smelting
                (Ingredient.of(ingredient),
                        RecipeCategory.FOOD,
                        result,
                        0.35F, 200)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pWriter,Helpers.identifier(RecipeCategory.FOOD.getFolderName()) + "/"  + getItemName(result) + "_from_" + "smelting");
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient),
                RecipeCategory.FOOD, result,
                        0.35F, 200)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pWriter,Helpers.identifier(RecipeCategory.FOOD.getFolderName()) + "/"  + getItemName(result) + "_from_" + "campfire");
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient),
                RecipeCategory.FOOD, result,
                0.35F, 200)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pWriter,Helpers.identifier(RecipeCategory.FOOD.getFolderName()) + "/"  + getItemName(result) + "_from_" + "smoking");
    }

    protected static void modNineBlockStorageRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeCategory pUnpackedCategory, ItemLike pUnpacked, RecipeCategory pPackedCategory, ItemLike pPacked) {
        modNineBlockStorageRecipes(pFinishedRecipeConsumer, pUnpackedCategory, pUnpacked, pPackedCategory, pPacked, getSimpleRecipeName(pPacked), null, getSimpleRecipeName(pUnpacked), null);
    }

    protected static void modNineBlockStorageRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeCategory pUnpackedCategory, ItemLike pUnpacked, RecipeCategory pPackedCategory, ItemLike pPacked, String pPackedName, @Nullable String pPackedGroup, String pUnpackedName, @Nullable String pUnpackedGroup) {
        ShapelessRecipeBuilder.shapeless(pUnpackedCategory, pUnpacked, 9)
                .requires(pPacked).group(pUnpackedGroup)
                .unlockedBy(getHasName(pPacked), has(pPacked))
                .save(pFinishedRecipeConsumer, Helpers.identifier(pUnpackedCategory.getFolderName() + "/" + pUnpackedName));
        ShapedRecipeBuilder.shaped(pPackedCategory, pPacked)
                .define('#', pUnpacked)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .group(pPackedGroup)
                .unlockedBy(getHasName(pUnpacked), has(pUnpacked))
                .save(pFinishedRecipeConsumer, Helpers.identifier(pPackedCategory.getFolderName() + "/" + pPackedName));
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
