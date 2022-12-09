package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeTypes {

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, ClassiCraftMod.MOD_ID);

    private static <R extends Recipe<?>> RegistryObject<RecipeType<R>> register(String name) {
        return RECIPE_TYPES.register(name, () -> new RecipeType<>() {
            @Override
            public String toString() {
                return name;
            }
        });
    }
}
