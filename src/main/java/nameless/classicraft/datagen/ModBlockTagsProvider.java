/*
 * ClassiCraft - ClassiCraftMC
 * Copyright (C) 2018-2022.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
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
        blocks.stream().filter(block -> block.defaultBlockState().getMaterial() == Material.GLASS)
                .forEach(this::pickaxeMineAble);
        blocks.stream().filter(block -> block.defaultBlockState().getMaterial() == Material.STONE)
                        .forEach(this::pickaxeMineAble);
        blocks.stream().filter(block -> block.defaultBlockState().getMaterial() == Material.GLASS)
                .forEach(this::pickaxeMineAble);
        blocks.stream().filter(block -> block.defaultBlockState().getMaterial() == Material.SAND)
                .forEach(this::shovelMineAble);
        blocks.stream().filter(block -> block.defaultBlockState().getMaterial() == Material.STONE)
                .forEach(this::needWoodTool);
        blocks.stream().filter(block -> block.defaultBlockState().getMaterial() == Material.GLASS)
                .forEach(this::needWoodTool);
        blocks.stream().filter(block -> block instanceof FenceBlock)
                .forEach(this::fenceTag);
        wallPost();
    }

    private void wallPost() {
        tag(BlockTags.WALL_POST_OVERRIDE).add(ModBlocks.REAL_TORCH.get());
        tag(BlockTags.WALL_POST_OVERRIDE).add(ModBlocks.REAL_SOUL_TORCH.get());
        tag(BlockTags.WALL_POST_OVERRIDE).add(ModBlocks.REAL_WALL_TORCH.get());
        tag(BlockTags.WALL_POST_OVERRIDE).add(ModBlocks.REAL_SOUL_WALL_TORCH.get());
    }

    private void fenceTag(Block fence) {
        tag(BlockTags.FENCES).add(fence);
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
