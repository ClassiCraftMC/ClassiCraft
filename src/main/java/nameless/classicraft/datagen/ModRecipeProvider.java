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
import nameless.classicraft.util.SafeTag;
import net.minecraft.Util;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.PartialNBTIngredient;
import net.minecraftforge.common.crafting.StrictNBTIngredient;

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
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS,
                ModItems.TORCH_UNLIT.get(), 4).define('#', Items.STICK)
                .define('X',
                        Ingredient.of(Items.COAL, Items.CHARCOAL))
                .pattern("X")
                .pattern("#")
                .unlockedBy("has_stone_pickaxe",
                        has(Items.STONE_PICKAXE)).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS,
                ModItems.SOUL_TORCH_UNLIT.get(), 4).define('X',
                Ingredient.of(Items.COAL, Items.CHARCOAL))
                .define('#', Items.STICK)
                .define('S', ItemTags.SOUL_FIRE_BASE_BLOCKS)
                .pattern("X")
                .pattern("#")
                .pattern("S")
                .unlockedBy("has_soul_sand",
                        has(ItemTags.SOUL_FIRE_BASE_BLOCKS)).save(pWriter);

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
        nineBlockStorageRecipes(pWriter, RecipeCategory.BUILDING_BLOCKS, Items.QUARTZ, RecipeCategory.MISC, Items.QUARTZ_BLOCK);
        fourBlockStorageRecipes(pWriter, RecipeCategory.BUILDING_BLOCKS, Items.FLINT, RecipeCategory.MISC, ModBlocks.FLINT_BLOCK.get());
    }

    protected void nbtPebbleRecipe(Consumer<FinishedRecipe> pWriter, Block block, String meta) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,
                        block).define('#',
                        StrictNBTIngredient.of(Util.make(() ->
                                {
                                    ItemStack stack = ModItems.PEBBLE.get().getDefaultInstance();
                                    CompoundTag nbt = new CompoundTag();
                                    assert stack.getTag() != null;
                                    MetaItem.setMeta(stack, meta);
                                    return stack;
                                }
                               )))
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_" + ModItems.PEBBLE.get(), has(ModItems.PEBBLE.get()))
                .save(pWriter, getItemName(block) + "_from_" + "pebble");
    }

    protected void cookRecipe(Consumer<FinishedRecipe> pWriter, Item ingredient, Item result) {
        SimpleCookingRecipeBuilder.smelting
                (Ingredient.of(ingredient),
                        RecipeCategory.FOOD,
                        result,
                        0.35F, 200)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pWriter,getItemName(result) + "_from_" + "smelting");
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient),
                RecipeCategory.FOOD, result,
                        0.35F, 200)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pWriter,getItemName(result) + "_from_" + "campfire");
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient),
                RecipeCategory.FOOD, result,
                0.35F, 200)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pWriter,getItemName(result) + "_from_" + "smoking");
    }

    protected void stair(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pStair, ItemLike pMaterial) {
        stairBuilder(pStair, Ingredient.of(pMaterial)).unlockedBy(getHasName(pMaterial), has(pMaterial)).save(pFinishedRecipeConsumer);
    }

    protected static void fourBlockStorageRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeCategory pUnpackedCategory, ItemLike pUnpacked, RecipeCategory pPackedCategory, ItemLike pPacked) {
        fourBlockStorageRecipes(pFinishedRecipeConsumer, pUnpackedCategory, pUnpacked, pPackedCategory, pPacked, getSimpleRecipeName(pPacked), (String)null, getSimpleRecipeName(pUnpacked), (String)null);
    }

    protected static void fourBlockStorageRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeCategory pUnpackedCategory, ItemLike pUnpacked, RecipeCategory pPackedCategory, ItemLike pPacked, String pPackedName, @Nullable String pPackedGroup, String pUnpackedName, @Nullable String pUnpackedGroup) {
        ShapelessRecipeBuilder.shapeless(pUnpackedCategory, pUnpacked, 4).requires(pPacked).group(pUnpackedGroup).unlockedBy(getHasName(pPacked), has(pPacked)).save(pFinishedRecipeConsumer, new ResourceLocation(pUnpackedName));
        ShapedRecipeBuilder.shaped(pPackedCategory, pPacked).define('#', pUnpacked)
                .pattern("##")
                .pattern("##").group(pPackedGroup).unlockedBy(getHasName(pUnpacked), has(pUnpacked)).save(pFinishedRecipeConsumer, new ResourceLocation(pPackedName));
    }

}
