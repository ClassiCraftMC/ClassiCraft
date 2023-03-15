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
import nameless.classicraft.init.ModTags;
import nameless.classicraft.util.Helpers;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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
        Set<Block> blocks = Helpers.getBlocks();
        blocks.stream().filter(block -> block instanceof WallBlock)
                        .forEach(this::wallTag);
        blocks.stream().filter(block -> block instanceof StairBlock)
                .forEach(this::stairsTag);
        blocks.stream().filter(block -> block instanceof SlabBlock)
                .forEach(this::slabTag);
        blocks.stream().filter(block -> block instanceof FlowerBlock)
                .forEach(this::flowerTag);
        blocks.stream().filter(block -> block instanceof FenceBlock)
                        .forEach(this::fenceTag);
        blocks.stream().filter(block -> block instanceof ButtonBlock)
                .forEach(this::button);
        addVanillaPebbleTags();
        addDrinkableTags();
        soulFires();
    }

    private void soulFires() {
        soulFire(ModBlocks.SOUL_QUICKSAND.get());
        soulFire(ModBlocks.SOUL_SANDSTONE.get());
        soulFire(ModBlocks.SOUL_SANDSTONE_BRICKS.get());
        soulFire(ModBlocks.SOUL_SANDSTONE_NITER_ORE.get());
        soulFire(ModBlocks.SMOOTH_SOUL_SANDSTONE.get());
        soulFire(ModBlocks.CHISELED_SOUL_SANDSTONE.get());
        soulFire(ModBlocks.CUT_SOUL_SANDSTONE.get());
    }

    private void button(Block button) {
        tag(ItemTags.BUTTONS).add(button.asItem());
    }

    private void soulFire(Block soul) {
        tag(ItemTags.SOUL_FIRE_BASE_BLOCKS).add(soul.asItem());
    }

    void fenceTag(Block fence) {
        tag(ItemTags.FENCES).add(fence.asItem());
    }

    void addDrinkableTags() {
        drinkableTag(Items.MUSHROOM_STEW);
        drinkableTag(Items.BEETROOT_SOUP);
        drinkableTag(Items.SUSPICIOUS_STEW);
        thickTag(Items.MUSHROOM_STEM);
        thickTag(Items.SUSPICIOUS_STEW);
    }

    void thickTag(Item item) {
        tag(ModTags.Items.TAG_THICK).add(item).replace(false);
    }

    void drinkableTag(Item item) {
        tag(ModTags.Items.TAG_DRINKABLE).add(item).replace(false);
    }

    void addVanillaPebbleTags() {
        vanillaPebbleTag(Items.FLINT);
        vanillaPebbleTag(Items.QUARTZ);
        vanillaPebbleTag(Items.PRISMARINE_SHARD);
    }

    void vanillaPebbleTag(Item item) {
        tag(ModTags.Items.VANILLA_PEBBLES).add(item).replace(false);
    }

    void slabTag(Block slab) {
        tag(ItemTags.SLABS).add(slab.asItem()).replace(false);
    }

    void stairsTag(Block stairs) {
        tag(ItemTags.STAIRS).add(stairs.asItem()).replace(false);
    }

    void wallTag(Block wall) {
        tag(ItemTags.WALLS).add(wall.asItem()).replace(false);
    }

    void flowerTag(Block flower) {
        tag(ItemTags.FLOWERS).add(flower.asItem()).replace(false);
    }
}
