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
import nameless.classicraft.glm.ReplaceDropModifier;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class ModGlobalModifierProvider extends GlobalLootModifierProvider {

    public ModGlobalModifierProvider(PackOutput output) {
        super(output, ClassiCraftMod.MOD_ID);
    }

    @Override
    protected void start() {
        replaceBlock(Blocks.STONE, ModItems.PEBBLE.get(), "cobblestone_pebble", 1, 4);
        replaceBlock(Blocks.PRISMARINE, Items.PRISMARINE_SHARD, "prismarine", 1, 4);
        replaceBlock(Blocks.CRIMSON_NYLIUM, ModItems.PEBBLE.get(), "netherrack_pebble", 1, 4);
        replaceBlock(Blocks.WARPED_NYLIUM, ModItems.PEBBLE.get(), "netherrack_pebble", 1, 4);
        pebble(Blocks.ANDESITE);
        pebble(Blocks.DIORITE);
        pebble(Blocks.RED_SANDSTONE);
        pebble(Blocks.SANDSTONE);
        pebble(Blocks.DEEPSLATE);
        pebble(Blocks.BASALT);
        pebble(Blocks.GRANITE);
        pebble(Blocks.BLACKSTONE);
        pebble(Blocks.QUARTZ_BLOCK);
        pebble(Blocks.NETHERRACK);
        pebble(Blocks.END_STONE);
        pebble(Blocks.BLACKSTONE);
        pebble(ModBlocks.QUARTZ_SANDSTONE.get());
        pebble(ModBlocks.SOUL_SANDSTONE.get());
        replaceBlock(Blocks.ROOTED_DIRT, Items.FLINT, 1, 5);
        replaceBlock(Blocks.COARSE_DIRT, Items.FLINT, 1, 5);
        replaceBlock(Blocks.MOSS_BLOCK, ModItems.MOSS_CLUMP.get(), "moss_drop", 1, 4);
        replaceWool();
        replaceBlock(Blocks.VINE, Items.AIR, 0);
        replaceBlock(Blocks.TWISTING_VINES, Items.AIR, 0);
        replaceBlock(Blocks.WEEPING_VINES, Items.AIR, 0);
        replaceSapling();
    }

    void pebble(Block block) {
        replaceBlock(block, ModItems.PEBBLE.get(),
                Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath() + "_pebble", 1, 4);
    }

    void replaceBlock(Block block, Item item, String meta, int min, int max) {
        add("block/" + Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath(),
                new ReplaceDropModifier(new LootItemCondition[]{
                        new LootItemBlockStatePropertyCondition.Builder(block).build()
                }, item, meta, min, max));
    }

    void replaceBlock(Block block, Item item, int count, int probability) {
        add("block/" + Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath(),
                new ReplaceDropModifier(new LootItemCondition[]{
                        new LootItemBlockStatePropertyCondition.Builder(block).build()
                }, item, "", probability, count, count));
    }

    void replaceBlock(Block block, Item item, String meta, int count) {
        add("block/" + Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath(),
                new ReplaceDropModifier(new LootItemCondition[]{
                        new LootItemBlockStatePropertyCondition.Builder(block).build()
                }, item, meta, count));
    }

    void replaceBlock(Block block, Item item, int count) {
        replaceBlock(block, item, "", count);
    }

    void replaceWool() {
        for (Block block : ForgeRegistries.BLOCKS) {
            if (block.getDescriptionId().contains("wool")
                    && block.getDescriptionId().contains("minecraft")) {
                replaceBlock(block, Items.STRING, "wool_drop", 1, 4);
            }
        }
    }

    void replaceSapling() {
        for (Block block : ForgeRegistries.BLOCKS) {
            if (block.getDescriptionId().contains("sapling")) {
                replaceBlock(block, Items.STICK, "sapling_drop", 1, 2);
            }
            if (block.getDescriptionId().contains("propagule")) {
                replaceBlock(block, Items.STICK, "propagule_drop", 1, 2);
            }
            if (block.getDescriptionId().contains("azalea")) {
                replaceBlock(block, Items.STICK, "azalea_drop", 1, 2);
            }
        }
    }
}
