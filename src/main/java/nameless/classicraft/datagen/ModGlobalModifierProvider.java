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
    public ModGlobalModifierProvider(PackOutput output, String modid) {
        super(output, modid);
    }

    @Override
    protected void start() {
        replaceBlock(Blocks.STONE, ModItems.PEBBLE.get(), "cobblestone_pebble", 1, 4);
        pebble(Blocks.ANDESITE);
        pebble(Blocks.DIORITE);
        pebble(Blocks.RED_SANDSTONE);
        pebble(Blocks.SANDSTONE);
        pebble(Blocks.DEEPSLATE);
        pebble(Blocks.COBBLED_DEEPSLATE);
        pebble(Blocks.BASALT);
        pebble(Blocks.GRANITE);
        pebble(Blocks.BLACKSTONE);
        pebble(Blocks.QUARTZ_BLOCK);
        pebble(Blocks.SOUL_SAND);
        pebble(Blocks.NETHERRACK);
        pebble(Blocks.END_STONE);
        pebble(Blocks.PRISMARINE);
        pebble(Blocks.COBBLESTONE);
        pebble(Blocks.BLACKSTONE);
        pebble(ModBlocks.QUARTZ_SANDSTONE.get());
        replaceBlock(Blocks.ROOTED_DIRT, Items.FLINT, 1);

        replaceBlock(Blocks.COARSE_DIRT, Items.FLINT, 1);
    }

    void pebble(Block block) {
        replaceBlock(block, ModItems.PEBBLE.get(),
                ForgeRegistries.BLOCKS.getKey(block).getPath() + "_pebble", 1, 4);
    }

    void replaceBlock(Block block, Item item, String meta, int min, int max) {
        add("block/" + Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath(),
                new ReplaceDropModifier(new LootItemCondition[]{
                        new LootItemBlockStatePropertyCondition.Builder(block).build()
                }, item, meta, min, max));
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
}
