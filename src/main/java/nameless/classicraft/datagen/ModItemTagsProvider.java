package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.util.ExtraUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(PackOutput p_255871_, CompletableFuture<HolderLookup.Provider> p_256035_, TagsProvider<Block> p_256467_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_255871_, p_256035_, p_256467_, ClassiCraftMod.MOD_ID, existingFileHelper);
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
    }

    void slabTag(Block slab) {
        tag(ItemTags.SLABS).add(slab.asItem());
    }

    void stairsTag(Block stairs) {
        tag(ItemTags.STAIRS).add(stairs.asItem());
    }

    void wallTag(Block wall) {
        tag(ItemTags.WALLS).add(wall.asItem());
    }

    void flowerTag(Block flower) {
        tag(ItemTags.FLOWERS).add(flower.asItem());
    }
}
