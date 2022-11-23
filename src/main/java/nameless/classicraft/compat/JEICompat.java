package nameless.classicraft.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModRecipeTypes;
import nameless.classicraft.recipe.PolishRecipe;
import nameless.classicraft.recipe.StoneMortarRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEICompat implements IModPlugin {

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                StoneMortarRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new
                PolishRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<StoneMortarRecipe> recipesInfusing =
                recipeManager.getAllRecipesFor(StoneMortarRecipe.StoneMortarRecipeType.STONE_MORTAR);
        registration.addRecipes(StoneMortarRecipeCategory.STONE_MORTAR_RECIPE, recipesInfusing);
        List<PolishRecipe> polishInfusing =
                recipeManager.getAllRecipesFor(ModRecipeTypes.POLISH_RECIPE_TYPE.get());
        registration.addRecipes(PolishRecipeCategory.POLISH_RECIPE, polishInfusing);
    }

    @Override
    @NotNull
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(ClassiCraftMod.MODID, "jei_plugin");
    }
}
