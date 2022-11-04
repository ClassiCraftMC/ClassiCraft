package nameless.classicraft.datagen;

import nameless.classicraft.common.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class CCRecipeProvider extends RecipeProvider {

    public CCRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        stonecutterResultFromBase(pFinishedRecipeConsumer, ModItems.DOUGH.get(), Items.WHEAT, 3);
        stonecutterResultFromBase(pFinishedRecipeConsumer, ModItems.RICE_HUSK.get(), ModItems.RICE.get());
    }

}
