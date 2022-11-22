package nameless.classicraft.compat;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import nameless.classicraft.recipe.StoneMortarRecipe;
import nameless.classicraft.recipe.WoodCutterRecipe;
import net.minecraft.network.chat.Component;

public class WoodcutterRecipeCategory implements IRecipeCategory<WoodCutterRecipe> {

    @Override
    public RecipeType<WoodCutterRecipe> getRecipeType() {
        return null;
    }

    @Override
    public Component getTitle() {
        return null;
    }

    @Override
    public IDrawable getBackground() {
        return null;
    }

    @Override
    public IDrawable getIcon() {
        return null;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder iRecipeLayoutBuilder, WoodCutterRecipe woodCutterRecipe, IFocusGroup iFocusGroup) {

    }
}
