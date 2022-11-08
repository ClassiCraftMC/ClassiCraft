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
import nameless.classicraft.recipe.StoneMortarRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class StoneMortarRecipeCategory implements IRecipeCategory<StoneMortarRecipe> {

    public final static ResourceLocation LOCATION =
            new ResourceLocation(ClassiCraftMod.MODID, "stone_mortar_block");

    public static RecipeType<StoneMortarRecipe> STONE_MORTAR_RECIPE =
            new RecipeType<>(StoneMortarRecipeCategory.LOCATION, StoneMortarRecipe.class);

    public final static ResourceLocation TEXTURE =
            new ResourceLocation(ClassiCraftMod.MODID, "textures/gui/stone_mortar_block.png");

    private final IDrawable background;
    private final IDrawable icon;

    public StoneMortarRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 170, 80);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
                new ItemStack(ModBlocks.STONE_MORTAR_BLOCK.get()));
    }

    @Override
    public @NotNull RecipeType<StoneMortarRecipe> getRecipeType() {
        return STONE_MORTAR_RECIPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("block.classicraft.stone_mortar_block");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, StoneMortarRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 38, 30).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 110, 30).addItemStack(recipe.getResultItem());
    }
}