package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.recipe.WoodCutterRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;

public class ModRecipeTypes {

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, ClassiCraftMod.MODID);

    public static final RegistryObject<RecipeType<WoodCutterRecipe>> WOOD_CUTTER_RECIPE_RECIPE_TYPE =
            register("woodcutter_recipe_type");

    private static <T extends Recipe<?>> RegistryObject<RecipeType<T>> register(String name)
    {
        return RECIPE_TYPES.register(name.toLowerCase(Locale.ROOT), () -> new RecipeType<T>() {
            @Override
            public String toString() {
                return name;
            }
        });
    }
}
