package nameless.classicraft.datagen;

import nameless.classicraft.common.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class CCRecipeProvider extends RecipeProvider {

    public CCRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        stonecutterResultFromBase(pFinishedRecipeConsumer, ModItems.DOUGH.get(), Items.WHEAT, 3);
    }

    private static void ofSmeltingCoal(Item originItem, Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        SimpleCookingRecipeBuilder.smelting(Ingredient
                .of(originItem), Items.COAL,
                0.35F, 200)
                .unlockedBy("has_" + originItem.getDescriptionId(),
                        has(originItem)).save(pFinishedRecipeConsumer);
    }
}
