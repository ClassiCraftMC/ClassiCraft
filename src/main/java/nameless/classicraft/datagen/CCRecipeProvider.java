package nameless.classicraft.datagen;

import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
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
       simpleSmelting(ModItems.DOUGH.get(), Items.BREAD, pFinishedRecipeConsumer);
       simpleSmelting(ModItems.RAW_PUMPKIN_PIE.get(), Items.PUMPKIN, pFinishedRecipeConsumer);
       simpleSmelting(ModItems.RAW_COOKIE.get(), Items.COOKIE, pFinishedRecipeConsumer);
       simpleSmelting(ModItems.RAW_CAKE.get(), Items.CAKE, pFinishedRecipeConsumer);
       simpleSmelting(ModItems.LIONFISH.get(), ModItems.COOKED_LIONFISH.get(), pFinishedRecipeConsumer);
       simpleSmelting(ModItems.PERCH.get(), ModItems.COOKED_PERCH.get(), pFinishedRecipeConsumer);
       simpleSmelting(ModItems.TROUT.get(), ModItems.COOKED_TROUT.get(), pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(ModItems.DOUGH.get(), 4)
                .requires(Items.WATER_BUCKET)
                .requires(ModItems.FLOUR.get())
                .unlockedBy("has_flour", has(ModItems.FLOUR.get()))
                .save(pFinishedRecipeConsumer);

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
       ShapedRecipeBuilder.shaped(ModItems.RAW_CAKE.get(), 2)
               .define('K', Items.SUGAR)
               .define('S', Items.MILK_BUCKET)
               .define('P', Items.EGG)
               .define('L', ModItems.FLOUR.get())
               .pattern("SSS")
               .pattern("KPK")
               .pattern("LLL")
               .unlockedBy("has_flour", has(ModItems.FLOUR.get())).save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(ModBlocks.CAMPFIRE.get(), 1)
                .define('#', Items.STICK)
                .define('L', Items.COBBLESTONE)
                .define('O', Ingredient.of(
                        Items.ACACIA_LOG,
                        Items.BIRCH_LOG,
                        Items.DARK_OAK_LOG,
                        Items.JUNGLE_LOG,
                        Items.SPRUCE_LOG,
                        Items.OAK_LOG,
                        Items.MANGROVE_LOG,
                        Items.STRIPPED_ACACIA_LOG,
                        Items.STRIPPED_BIRCH_LOG,
                        Items.STRIPPED_DARK_OAK_LOG,
                        Items.STRIPPED_JUNGLE_LOG,
                        Items.STRIPPED_SPRUCE_LOG,
                        Items.STRIPPED_OAK_LOG,
                        Items.STRIPPED_MANGROVE_LOG))
                .pattern(" # ")
                .pattern("# #")
                .pattern("OLO")
                .unlockedBy("has_stick", has(Items.STICK))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(Blocks.HONEYCOMB_BLOCK, 1)
                .define('K', Items.HONEYCOMB)
                .pattern("KKK")
                .pattern("KKK")
                .pattern("KKK")
                .unlockedBy("has_honeycomb", has(Items.HONEYCOMB)).save(pFinishedRecipeConsumer);


        /**
         ShapedRecipeBuilder.shaped(Blocks.SOUL_CAMPFIRE, 1)
                 .define('#', Items.STICK)
                 .define('X', Ingredient.of(Items.COAL, Items.CHARCOAL))
                 .define('O', Ingredient.of(
                         Items.ACACIA_LOG,
                         Items.BIRCH_LOG,
                         Items.DARK_OAK_LOG,
                         Items.JUNGLE_LOG,
                         Items.SPRUCE_LOG,
                         Items.OAK_LOG,
                         Items.MANGROVE_LOG,
                         Items.STRIPPED_ACACIA_LOG,
                         Items.STRIPPED_BIRCH_LOG,
                         Items.STRIPPED_DARK_OAK_LOG,
                         Items.STRIPPED_JUNGLE_LOG,
                         Items.STRIPPED_SPRUCE_LOG,
                         Items.STRIPPED_OAK_LOG,
                         Items.STRIPPED_MANGROVE_LOG))
                 .define('L', Items.SOUL_SAND)
                 .pattern(" # ")
                 .pattern("#X#")
                 .pattern("OLO")
                 .unlockedBy("has_stick", has(Items.STICK))
                 .unlockedBy("has_soul_sand", has(ItemTags.SOUL_FIRE_BASE_BLOCKS))
                 .save(pFinishedRecipeConsumer);*/
    }

    private static void simpleSmelting(Item material, Item finalItem, Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        SimpleCookingRecipeBuilder.smelting(Ingredient
                                .of(material), finalItem,
                        0.35F, 200)
                .unlockedBy("has_" + material.getDescriptionId(), has(material)).save(pFinishedRecipeConsumer);
    }

}
