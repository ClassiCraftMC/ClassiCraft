package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.util.ExtraUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,  @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ClassiCraftMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        Set<Block> blocks = ExtraUtils.getBlocks();
        blocks.stream().filter(block -> block instanceof WallBlock)
                        .forEach(this::wallTag);
        blocks.stream().filter(block -> block instanceof StairBlock)
                        .forEach(this::stairsTag);
        blocks.stream().filter(block -> block instanceof SlabBlock)
                        .forEach(this::slabTag);
        blocks.stream().filter(block -> block instanceof FlowerBlock)
                .forEach(this::flowerTag);
        blocks.stream().filter(block -> block.defaultBlockState().getMaterial() == Material.STONE)
                        .forEach(this::pickaxeMineAble);
        blocks.stream().filter(block -> block.defaultBlockState().getMaterial() == Material.SAND)
                .forEach(this::shovelMineAble);
        addToolNeedsTags();
    }

    private void wallTag(Block wall) {
        tag(BlockTags.WALLS).add(wall);
    }

    private void addToolNeedsTags() {
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
        needWoodTool(ModBlocks.POLISHED_DIORITE_WALL);
        needWoodTool(ModBlocks.POLISHED_ANDESITE_WALL);
    }

    void shovelMineAble(Block block) {
        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(block);
    }

    void pickaxeMineAble(Block block) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block);
    }

    void flowerTag(Block flower) {
        tag(BlockTags.FLOWERS).add(flower);
    }

    void stairsTag(Block stairs) {
        tag(BlockTags.STAIRS).add(stairs);
    }

    void slabTag(Block slab) {
        tag(BlockTags.SLABS).add(slab);
    }

    void needWoodTool(RegistryObject<Block> block) {
        tag(Tags.Blocks.NEEDS_WOOD_TOOL).add(block.get());
    }
}
