package nameless.classicraft.datagen;

import nameless.classicraft.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        fourCraftBlock(Items.COBBLESTONE, ModItems.PEBBLE.get());
    }

    void fourCraftBlock(Item result, Item material) {
        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.BUILDING_BLOCKS,
                        result).requires(material);
    }
}
