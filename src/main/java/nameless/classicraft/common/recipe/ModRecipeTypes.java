package nameless.classicraft.common.recipe;

import nameless.classicraft.ClassiCraft;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeTypes {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ClassiCraft.MODID);

    public static final RegistryObject<RecipeSerializer<StoneMortarRecipe>> STONE_MORTAR_TYPE
            = RECIPE_SERIALIZER.register("stone_mortar_type", () -> StoneMortarRecipe.Serializer.INSTANCE);
}
