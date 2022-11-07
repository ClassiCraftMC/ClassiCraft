package nameless.classicraft.datagen;

import nameless.classicraft.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class CCRecipeProvider extends RecipeProvider {

    public CCRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        ShapelessRecipeBuilder.shapeless(ModItems.DOUGH.get(), 4)
                .requires(Items.WATER_BUCKET)
                .requires(ModItems.FLOUR.get())
                .unlockedBy("has_flour", has(ModItems.FLOUR.get()))
                .save(pFinishedRecipeConsumer);
       simpleSmelting(ModItems.DOUGH.get(), Items.BREAD, pFinishedRecipeConsumer);
       simpleSmelting(ModItems.RAW_PUMPKIN_PIE.get(), Items.PUMPKIN, pFinishedRecipeConsumer);
       simpleSmelting(ModItems.RAW_COOKIE.get(), Items.COOKIE, pFinishedRecipeConsumer);
       ShapedRecipeBuilder.shaped(ModItems.RAW_PUMPKIN_PIE.get(), 2)
                .define('K', Blocks.PUMPKIN)
                .define('S', Items.EGG)
                .define('#', Items.SUGAR)
                .define('L', ModItems.DOUGH.get())
                .pattern("KS")
                .pattern("#L")
                .unlockedBy("has_dough", has(ModItems.DOUGH.get())).save(pFinishedRecipeConsumer);
       ShapedRecipeBuilder.shaped(ModItems.RAW_COOKIE.get(), 8)
               .define('K', Items.SUGAR)
               .define('S', Items.COCOA_BEANS)
               .define('L', ModItems.DOUGH.get())
               .pattern(" K ")
               .pattern("LSL")
               .pattern(" K ")
               .unlockedBy("has_dough", has(ModItems.DOUGH.get())).save(pFinishedRecipeConsumer);

    }

    private static void simpleSmelting(Item material, Item finalItem, Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        SimpleCookingRecipeBuilder.smelting(Ingredient
                                .of(material), finalItem,
                        0.35F, 200)
                .unlockedBy("has_" + material.getDescriptionId(), has(material)).save(pFinishedRecipeConsumer);
    }

}
