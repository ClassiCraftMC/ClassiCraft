package nameless.classicraft.init;

import com.google.gson.JsonObject;
import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.recipe.StoneMortarRecipe;
import nameless.classicraft.recipe.WoodCutterRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.item.crafting.StonecutterRecipe;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.function.Supplier;

public class ModRecipeTypes {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ClassiCraftMod.MODID);

    public static final RegistryObject<RecipeSerializer<StoneMortarRecipe>> STONE_MORTAR_TYPE =
            register("stone_mortar_type", () -> StoneMortarRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<WoodCutterRecipe>> WOODCUTTER_TYPE =
            register("woodcutter_type", () -> new SingleItemRecipe.Serializer<>(WoodCutterRecipe::new));

    private static <T extends RecipeSerializer<?>> RegistryObject<T> register(String name, Supplier<T> recipeSerializer)
    {
        return RECIPE_SERIALIZERS.register(name.toLowerCase(Locale.ROOT), recipeSerializer);
    }

}
