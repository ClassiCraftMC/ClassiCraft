package nameless.classicraft.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import nameless.classicraft.init.ModBlocks;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class StoneMortarRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation resourceLocation;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;
    private final FluidStack fluidStack;

    public StoneMortarRecipe(ResourceLocation resourceLocation, ItemStack output,
                             NonNullList<Ingredient> recipeItems, FluidStack fluidStack) {
        this.resourceLocation = resourceLocation;
        this.output = output;
        this.recipeItems = recipeItems;
        this.fluidStack = fluidStack;
    }

    @Override
    public boolean matches(@NotNull SimpleContainer container, @NotNull Level level) {
        if (level.isClientSide()) {
            return false;
        }
        return recipeItems.get(0).test(container.getItem(0));
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull SimpleContainer container) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.STONE_MORTAR_BLOCK.get());
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return resourceLocation;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeType.INSTANCE;
    }

    public FluidStack getFluid() {
        return fluidStack;
    }

    public static class ModRecipeType implements RecipeType<StoneMortarRecipe> {
        private ModRecipeType() {
        }

        public static final ModRecipeType INSTANCE = new ModRecipeType();
    }

    public static class Serializer implements RecipeSerializer<StoneMortarRecipe> {

        public static final Serializer INSTANCE = new Serializer();

        @Override
        public StoneMortarRecipe fromJson(ResourceLocation resourceLocation, JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }
            return new StoneMortarRecipe(resourceLocation, output, inputs, null);
        }

        @Nullable
        @Override
        public StoneMortarRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }
            ItemStack output = buffer.readItem();
            return new StoneMortarRecipe(resourceLocation, output, inputs, null);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, StoneMortarRecipe recipe) {
            buffer.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buffer);
            }
            buffer.writeItemStack(recipe.getResultItem(), false);
        }
    }
}
