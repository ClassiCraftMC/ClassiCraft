package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.util.ExtraUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
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
        blocks.stream().filter(block -> block.defaultBlockState().getMaterial() == Material.STONE)
                .forEach(this::needWoodTool);
    }

    private void wallTag(Block wall) {
        tag(BlockTags.WALLS).add(wall);
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

    void needWoodTool(Block block) {
        tag(Tags.Blocks.NEEDS_WOOD_TOOL).add(block);
    }
}
