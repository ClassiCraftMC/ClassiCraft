package nameless.classicraft.datagen;

import nameless.classicraft.init.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nullable;
import java.util.function.Consumer;

/**
 * @author wdog5
 */
public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        wall(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.STONE_WALL.get(), Items.STONE);
        stair(pWriter, ModBlocks.SMOOTH_STONE_STAIRS.get(), Items.SMOOTH_STONE);
        stair(pWriter, ModBlocks.MOSSY_BRICKS_STAIRS.get(), ModBlocks.MOSSY_BRICKS.get());
        stair(pWriter, ModBlocks.CRACKED_BRICKS_STAIRS.get(), ModBlocks.CRACKED_BRICKS.get());
        stair(pWriter, ModBlocks.CRACKED_STONE_BRICKS_STAIRS.get(), Blocks.CRACKED_STONE_BRICKS);
        nineBlockStorageRecipes(pWriter, RecipeCategory.BUILDING_BLOCKS, Items.QUARTZ, RecipeCategory.MISC, Items.QUARTZ_BLOCK);
        fourBlockStorageRecipes(pWriter, RecipeCategory.BUILDING_BLOCKS, Items.FLINT, RecipeCategory.MISC, ModBlocks.FLINT_BLOCK.get());
    }

    void stair(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pStair, ItemLike pMaterial) {
        stairBuilder(pStair, Ingredient.of(pMaterial)).unlockedBy(getHasName(pMaterial), has(pMaterial)).save(pFinishedRecipeConsumer);
    }

    protected static void fourBlockStorageRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeCategory pUnpackedCategory, ItemLike pUnpacked, RecipeCategory pPackedCategory, ItemLike pPacked) {
        fourBlockStorageRecipes(pFinishedRecipeConsumer, pUnpackedCategory, pUnpacked, pPackedCategory, pPacked, getSimpleRecipeName(pPacked), (String)null, getSimpleRecipeName(pUnpacked), (String)null);
    }

    protected static void fourBlockStorageRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeCategory pUnpackedCategory, ItemLike pUnpacked, RecipeCategory pPackedCategory, ItemLike pPacked, String pPackedName, @Nullable String pPackedGroup, String pUnpackedName, @Nullable String pUnpackedGroup) {
        ShapelessRecipeBuilder.shapeless(pUnpackedCategory, pUnpacked, 4).requires(pPacked).group(pUnpackedGroup).unlockedBy(getHasName(pPacked), has(pPacked)).save(pFinishedRecipeConsumer, new ResourceLocation(pUnpackedName));
        ShapedRecipeBuilder.shaped(pPackedCategory, pPacked).define('#', pUnpacked)
                .pattern("##")
                .pattern("##").group(pPackedGroup).unlockedBy(getHasName(pUnpacked), has(pUnpacked)).save(pFinishedRecipeConsumer, new ResourceLocation(pPackedName));
    }

}
