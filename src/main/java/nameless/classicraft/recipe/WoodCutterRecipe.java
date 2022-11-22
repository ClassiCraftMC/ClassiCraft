package nameless.classicraft.recipe;

import com.google.gson.JsonObject;
import nameless.classicraft.init.ModRecipeSerializers;
import nameless.classicraft.init.ModRecipeTypes;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class WoodCutterRecipe extends SingleItemRecipe {

    public WoodCutterRecipe(ResourceLocation pId, String pGroup, Ingredient pIngredient, ItemStack pResult) {
        super(ModRecipeTypes.WOOD_CUTTER_RECIPE_RECIPE_TYPE.get(), ModRecipeSerializers.WOODCUTTER_TYPE.get(), pId, pGroup, pIngredient, pResult);
    }

    public boolean matches(Container pInv, Level pLevel) {
        return this.ingredient.test(pInv.getItem(0));
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(Blocks.STONECUTTER);
    }

    public Ingredient getIngredient() {
        return this.ingredient;
    }

    public static class Serializer<T extends WoodCutterRecipe> implements RecipeSerializer<T> {
        final WoodCutterRecipe.Serializer.SingleItemMaker<T> factory;

        public Serializer(WoodCutterRecipe.Serializer.SingleItemMaker<T> pFactory) {
            this.factory = pFactory;
        }

        public T fromJson(ResourceLocation pRecipeId, JsonObject pJson) {
            String s = GsonHelper.getAsString(pJson, "group", "");
            Ingredient ingredient;
            if (GsonHelper.isArrayNode(pJson, "ingredient")) {
                ingredient = Ingredient.fromJson(GsonHelper.getAsJsonArray(pJson, "ingredient"));
            } else {
                ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(pJson, "ingredient"));
            }

            String s1 = GsonHelper.getAsString(pJson, "result");
            int i = GsonHelper.getAsInt(pJson, "count");
            ItemStack itemstack = new ItemStack(Registry.ITEM.get(new ResourceLocation(s1)), i);
            return this.factory.create(pRecipeId, s, ingredient, itemstack);
        }

        public T fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            String s = pBuffer.readUtf();
            Ingredient ingredient = Ingredient.fromNetwork(pBuffer);
            ItemStack itemstack = pBuffer.readItem();
            return this.factory.create(pRecipeId, s, ingredient, itemstack);
        }

        public void toNetwork(FriendlyByteBuf pBuffer, T pRecipe) {
            pBuffer.writeUtf(pRecipe.getGroup());
            pRecipe.getIngredient().toNetwork(pBuffer);
            pBuffer.writeItem(pRecipe.getResultItem());
        }

        public interface SingleItemMaker<T extends WoodCutterRecipe> {
            T create(ResourceLocation pId, String pGroup, Ingredient pIngredient, ItemStack pResult);
        }
    }

}
