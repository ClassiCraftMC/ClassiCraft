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

import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

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
        nineBlockStorageRecipes(pWriter, RecipeCategory.BUILDING_BLOCKS, Items.QUARTZ, RecipeCategory.MISC, Items.QUARTZ_BLOCK);
        fourBlockStorageRecipes(pWriter, RecipeCategory.BUILDING_BLOCKS, Items.FLINT, RecipeCategory.MISC, ModBlocks.FLINT_BLOCK.get());
    }

    void cookRecipe(Consumer<FinishedRecipe> pWriter, Item ingredient, Item result) {
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

    void stair(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pStair, ItemLike pMaterial) {
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
