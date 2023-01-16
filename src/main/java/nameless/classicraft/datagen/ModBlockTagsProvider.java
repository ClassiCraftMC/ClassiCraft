package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,  @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ClassiCraftMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        wallTag(ModBlocks.MOSSY_BRICKS_WALL);
        wallTag(ModBlocks.CRACKED_BRICKS_WALL);
        wallTag(ModBlocks.STONE_WALL);
        wallTag(ModBlocks.SMOOTH_STONE_WALL);
        wallTag(ModBlocks.CRACKED_STONE_BRICKS_WALL);
        wallTag(ModBlocks.POLISHED_GRANITE_WALL);
        needWoodTool(ModBlocks.MOSSY_BRICKS_WALL);
        needWoodTool(ModBlocks.CRACKED_BRICKS_WALL);
        needWoodTool(ModBlocks.STONE_WALL);
        needWoodTool(ModBlocks.SMOOTH_STONE_WALL);
        needWoodTool(ModBlocks.MOSSY_BRICKS);
        needWoodTool(ModBlocks.MOSSY_BRICKS_STAIRS);
        needWoodTool(ModBlocks.MOSSY_BRICKS_SLAB);
        needWoodTool(ModBlocks.CRACKED_BRICKS);
        needWoodTool(ModBlocks.CRACKED_BRICKS_STAIRS);
        needWoodTool(ModBlocks.CRACKED_BRICKS_SLAB);
        needWoodTool(ModBlocks.FLINT_BLOCK);
        needWoodTool(ModBlocks.SULFUR_ORE);
        needWoodTool(ModBlocks.SMOOTH_STONE_STAIRS);
        needWoodTool(ModBlocks.CHISELED_QUARTZ_SANDSTONE);
        needWoodTool(ModBlocks.CHISELED_SOUL_SANDSTONE);
        needWoodTool(ModBlocks.CUT_QUARTZ_SANDSTONE);
        needWoodTool(ModBlocks.CUT_SOUL_SANDSTONE);
        needWoodTool(ModBlocks.QUARTZ_SANDSTONE);
        needWoodTool(ModBlocks.QUARTZ_SANDSTONE_BRICKS);
        needWoodTool(ModBlocks.RED_SANDSTONE_BRICKS);
        needWoodTool(ModBlocks.SANDSTONE_BRICKS);
        needWoodTool(ModBlocks.CHARCOAL_BLOCK);
        needWoodTool(ModBlocks.QUICKSAND);
        needWoodTool(ModBlocks.RED_QUICKSAND);
        needWoodTool(ModBlocks.QUARTZ_QUICKSAND);
        needWoodTool(ModBlocks.TALLOW_BLOCK);
        needWoodTool(ModBlocks.SOUL_QUICKSAND);
        needWoodTool(ModBlocks.SOUL_SANDSTONE);
        needWoodTool(ModBlocks.SOUL_SANDSTONE_BRICKS);
        needWoodTool(ModBlocks.CRACKED_STONE_BRICKS_STAIRS);
        needWoodTool(ModBlocks.CRACKED_STONE_BRICKS_SLAB);
        needWoodTool(ModBlocks.CRACKED_STONE_BRICKS_WALL);
        needWoodTool(ModBlocks.POLISHED_GRANITE_WALL);
        needWoodTool(ModBlocks.INFESTED_MOSSY_COBBLESTONE);
    }

    void wallTag(RegistryObject<Block> wall) {
        tag(BlockTags.WALLS).add(wall.get());
    }

    void needWoodTool(RegistryObject<Block> block) {
        tag(Tags.Blocks.NEEDS_WOOD_TOOL).add(block.get());
    }
}
