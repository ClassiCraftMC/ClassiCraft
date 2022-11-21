package nameless.classicraft.recipe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class WoodCutterRecipe extends SingleItemRecipe {

    public WoodCutterRecipe(ResourceLocation pId, String pGroup, Ingredient pIngredient, ItemStack pResult) {
        super(RecipeType.STONECUTTING, RecipeSerializer.STONECUTTER, pId, pGroup, pIngredient, pResult);
    }

    public boolean matches(Container pInv, Level pLevel) {
        return this.ingredient.test(pInv.getItem(0));
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(Blocks.STONECUTTER);
    }

}
