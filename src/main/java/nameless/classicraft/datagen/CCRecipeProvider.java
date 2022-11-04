package nameless.classicraft.datagen;

import nameless.classicraft.common.item.ModItems;
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
        stonecutterResultFromBase(pFinishedRecipeConsumer, ModItems.RICE.get(), ModItems.RICE_HUSK.get());
        stonecutterResultFromBase(pFinishedRecipeConsumer, ModItems.FLOUR.get(), Items.WHEAT, 3);
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
