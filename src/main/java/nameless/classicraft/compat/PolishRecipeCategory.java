package nameless.classicraft.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.recipe.PolishRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class PolishRecipeCategory implements IRecipeCategory<PolishRecipe> {

    public final static ResourceLocation LOCATION =
            new ResourceLocation(ClassiCraftMod.MODID, "polish");

    public static RecipeType<PolishRecipe> POLISH_RECIPE =
            new RecipeType<>(PolishRecipeCategory.LOCATION, PolishRecipe.class);

    public final static ResourceLocation TEXTURE =
            new ResourceLocation(ClassiCraftMod.MODID, "textures/gui/polish_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public PolishRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 170, 80);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
                new ItemStack(ModBlocks.STONE_LOOSE_ROCK.get()));
    }


    @Override
    public RecipeType<PolishRecipe> getRecipeType() {
        return POLISH_RECIPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("classicraft.polish_title");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder iRecipeLayoutBuilder, PolishRecipe polishRecipe, IFocusGroup iFocusGroup) {
        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT, 38, 30).addIngredients(polishRecipe.getIngredients().get(0));
        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT, 110, 30).addItemStack(polishRecipe.getResultItem());
    }
}
