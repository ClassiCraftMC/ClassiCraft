package nameless.classicraft.datagen;

import nameless.classicraft.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class CCRecipeProvider extends RecipeProvider {

    public CCRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        ShapelessRecipeBuilder.shapeless(ModItems.DOUGH.get(), 4)
                .requires(Items.WATER_BUCKET)
                .requires(ModItems.FLOUR.get())
                .unlockedBy("has_flour", has(ModItems.FLOUR.get()))
                .save(pFinishedRecipeConsumer);
        SimpleCookingRecipeBuilder.smelting(Ingredient
                .of(ModItems.DOUGH.get()), Items.BREAD,
                0.35F, 200)
                .unlockedBy("has_dough", has(ModItems.DOUGH.get())).save(pFinishedRecipeConsumer);

    }

}
