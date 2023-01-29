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

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import nameless.classicraft.init.ModItems;
import nameless.classicraft.util.Helpers;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModAdvancementProvider implements ForgeAdvancementProvider.AdvancementGenerator {

    private static final Item[] MOSSY_OFF_SCRAPING_TOOLS =
            new Item[]{Items.WOODEN_AXE,
                    Items.GOLDEN_AXE,
                    Items.STONE_AXE,
                    Items.IRON_AXE,
                    Items.DIAMOND_AXE,
                    Items.NETHERITE_AXE};

    public static final Supplier<BiMap<Block, Block>> MOSSY_ABLES =
            Suppliers.memoize(() ->
                    ImmutableBiMap.<Block, Block>builder()
                            .put(Blocks.COBBLESTONE, Blocks.MOSSY_COBBLESTONE)
                            .put(Blocks.COBBLESTONE_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB)
                            .put(Blocks.COBBLESTONE_STAIRS, Blocks.MOSSY_COBBLESTONE_STAIRS)
                            .put(Blocks.COBBLESTONE_WALL, Blocks.MOSSY_COBBLESTONE_WALL)
                            .put(Blocks.STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS)
                            .put(Blocks.STONE_BRICK_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB)
                            .put(Blocks.STONE_BRICK_STAIRS, Blocks.MOSSY_STONE_BRICK_STAIRS)
                            .put(Blocks.STONE_BRICK_WALL, Blocks.MOSSY_STONE_BRICK_WALL)
                            .build());

    public static final Supplier<BiMap<Block, Block>> MOSSY_OFF_BY_BLOCK =
            Suppliers.memoize(() -> MOSSY_ABLES.get().inverse());

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {
        Advancement OBTAIN_PEBBLE =
                Advancement.Builder.advancement().display(ModItems.MATERIAL.get(),
                        Component.translatable("advancements.classicraft.obtain_pebble.title"),
                        Component.translatable("advancements.classicraft.obtain_pebble.description"),
                        new ResourceLocation("textures/gui/advancements/backgrounds/stone.png"),
                        FrameType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("has_pebble",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PEBBLE.get()))
                .save(saver, Helpers.identifier("obtain_pebble"), existingFileHelper);
        Advancement MOSSY_ON = Advancement.Builder.advancement().parent(OBTAIN_PEBBLE)
                .display(ModItems.MOSS_CLUMP.get(),
                                Component.translatable("advancements.classicraft.mossy_on.title"),
                                Component.translatable("advancements.classicraft.mossy_on.description"),
                                new ResourceLocation("textures/gui/advancements/backgrounds/stone.png"),
                                FrameType.TASK,
                                true,
                                true,
                                false)
                        .addCriterion("mossy_on",
                                ItemInteractWithBlockTrigger.TriggerInstance.
                                        itemUsedOnBlock(LocationPredicate.Builder.location()
                                                        .setBlock(BlockPredicate.Builder.block()
                                                                .of(MOSSY_ABLES.get().keySet()).build()),
                                        ItemPredicate.Builder.item().of(ModItems.MOSS_CLUMP.get())))
                        .save(saver, Helpers.identifier("building_blocks/mossy_on"), existingFileHelper);
        Advancement MOSSY_OFF = Advancement.Builder.advancement()
                .parent(MOSSY_ON).display(Items.STONE_AXE,
                        Component.translatable("advancements.classicraft.mossy_off.title"),
                        Component.translatable("advancements.classicraft.mossy_off.description"),
                        new ResourceLocation("textures/gui/advancements/backgrounds/stone.png"),
                        FrameType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("mossy_off",
                        ItemInteractWithBlockTrigger.TriggerInstance.
                                itemUsedOnBlock(LocationPredicate.Builder.location()
                                                .setBlock(BlockPredicate.Builder.block()
                                                        .of(MOSSY_OFF_BY_BLOCK.get().keySet()).build()),
                                        ItemPredicate.Builder.item().of(MOSSY_OFF_SCRAPING_TOOLS)))
                .save(saver, Helpers.identifier("building_blocks/mossy_off"), existingFileHelper);
    }
}
