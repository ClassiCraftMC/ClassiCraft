package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
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
        needStoneTool(ModBlocks.MOSSY_BRICKS_WALL);
        needStoneTool(ModBlocks.CRACKED_BRICKS_WALL);
        needStoneTool(ModBlocks.STONE_WALL);
        needStoneTool(ModBlocks.SMOOTH_STONE_WALL);
        needStoneTool(ModBlocks.MOSSY_BRICKS);
        needStoneTool(ModBlocks.MOSSY_BRICKS_STAIRS);
        needStoneTool(ModBlocks.MOSSY_BRICKS_SLAB);
        needStoneTool(ModBlocks.CRACKED_BRICKS);
        needStoneTool(ModBlocks.CRACKED_BRICKS_STAIRS);
        needStoneTool(ModBlocks.CRACKED_BRICKS_SLAB);
        needStoneTool(ModBlocks.FLINT_BLOCK);
        needStoneTool(ModBlocks.SULFUR_ORE);
        needStoneTool(ModBlocks.SMOOTH_STONE_STAIRS);
        needStoneTool(ModBlocks.CHISELED_QUARTZ_SANDSTONE);
        needStoneTool(ModBlocks.CHISELED_SOUL_SANDSTONE);
        needStoneTool(ModBlocks.CUT_QUARTZ_SANDSTONE);
        needStoneTool(ModBlocks.CUT_SOUL_SANDSTONE);
        needStoneTool(ModBlocks.QUARTZ_SANDSTONE);
        needStoneTool(ModBlocks.QUARTZ_SANDSTONE_BRICKS);
        needStoneTool(ModBlocks.RED_SANDSTONE_BRICKS);
        needStoneTool(ModBlocks.SANDSTONE_BRICKS);
        needStoneTool(ModBlocks.CHARCOAL_BLOCK);
        needStoneTool(ModBlocks.QUICKSAND);
        needStoneTool(ModBlocks.RED_QUICKSAND);
        needStoneTool(ModBlocks.QUARTZ_QUICKSAND);
        needStoneTool(ModBlocks.TALLOW_BLOCK);
        needStoneTool(ModBlocks.SOUL_QUICKSAND);
        needStoneTool(ModBlocks.SOUL_SANDSTONE);
        needStoneTool(ModBlocks.SOUL_SANDSTONE_BRICKS);
    }

    void wallTag(RegistryObject<Block> wall) {
        tag(BlockTags.WALLS).add(wall.get());
    }

    void needStoneTool(RegistryObject<Block> block) {
        tag(BlockTags.NEEDS_STONE_TOOL).add(block.get());
    }
}
