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
package nameless.classicraft.datagen.loot;

import nameless.classicraft.block.SandStoneBlock;
import nameless.classicraft.block.StoneLikeBlock;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import nameless.classicraft.item.PebbleItem;
import nameless.classicraft.util.Helpers;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetNbtFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModBlockLoot extends BlockLootSubProvider {
    private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.PIGLIN_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX).map(ItemLike::asItem).collect(Collectors.toSet());

    protected ModBlockLoot() {
        super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        add(ModBlocks.TWIGS.get(), createSingleItemTable(Items.STICK));

        addPebble(ModBlocks.ANDESITE_PEBBLE.get());
        addPebble(ModBlocks.COBBLESTONE_PEBBLE.get());
        addPebble(ModBlocks.DIORITE_PEBBLE.get());
        addPebble(ModBlocks.GRANITE_PEBBLE.get());
        addPebble(ModBlocks.RED_SANDSTONE_PEBBLE.get());
        addPebble(ModBlocks.SANDSTONE_PEBBLE.get());
        addPebble(ModBlocks.DEEPSLATE_PEBBLE.get());
        addPebble(ModBlocks.QUARTZ_SANDSTONE_PEBBLE.get());
        addPebble(ModBlocks.SOUL_SANDSTONE_PEBBLE.get());
        addPebble(ModBlocks.NETHERRACK_PEBBLE.get());
        addPebble(ModBlocks.END_STONE_PEBBLE.get());
        addPebble(ModBlocks.BLACKSTONE_PEBBLE.get());
        addPebble(ModBlocks.BASALT_PEBBLE.get());
        addVanillaPebble(ModBlocks.QUARTZ_PEBBLE.get(), Items.QUARTZ);
        addVanillaPebble(ModBlocks.FLINT.get(), Items.FLINT);
        addVanillaPebble(ModBlocks.PRISMARINE.get(), Items.PRISMARINE_SHARD);

        dropSelf(ModBlocks.CHARCOAL_BLOCK.get());
        dropSelf(ModBlocks.CACTUS_BALL.get());
        dropOther(ModBlocks.CACTUS_FRUIT.get(), Items.STICK);
        dropSelf(ModBlocks.QUICKSAND.get());
        dropSelf(ModBlocks.RED_QUICKSAND.get());
        dropSelf(ModBlocks.QUARTZ_QUICKSAND.get());
        dropSelf(ModBlocks.ROSE.get());
        dropSelf(ModBlocks.TALLOW_BLOCK.get());
        dropSelf(ModBlocks.NITER_BLOCK.get());
        dropSelf(ModBlocks.SULFUR_BLOCK.get());
        dropSelf(ModBlocks.THATCH.get());
        dropSelf(ModBlocks.DRIED_THATCH.get());
        addOreDrop(ModBlocks.DEEPSLATE_SULFUR_ORE.get(), ModItems.SULFUR.get(), 1, 2);
        addOreDrop(ModBlocks.NETHER_SULFUR_ORE.get(), ModItems.SULFUR.get(), 1, 2);
        addOreDrop(ModBlocks.SANDSTONE_NITER_ORE.get(), ModItems.NITER.get(), 1, 3);
        addOreDrop(ModBlocks.QUARTZ_SANDSTONE_NITER_ORE.get(), ModItems.NITER.get(), 1, 3);
        addOreDrop(ModBlocks.RED_SANDSTONE_NITER_ORE.get(), ModItems.NITER.get(), 1, 3);
        addOreDrop(ModBlocks.SOUL_SANDSTONE_NITER_ORE.get(), ModItems.NITER.get(), 1, 3);
        dropSelf(ModBlocks.QUARTZ_SAND.get());
        dropSelf(ModBlocks.SOUL_QUICKSAND.get());
        dropSelf(ModBlocks.FLINT_BLOCK.get());
        dropSelf(ModBlocks.CATTAIL.get());
        dropSelf(ModBlocks.REED.get());
        dropOther(ModBlocks.INFESTED_MOSSY_COBBLESTONE.get(), Items.MOSSY_COBBLESTONE);
        dropOther(ModBlocks.INFESTED_CHISELED_DEEPSLATE.get(), Items.CHISELED_DEEPSLATE);
        dropOther(ModBlocks.INFESTED_COBBLED_DEEPSLATE.get(), Items.COBBLED_DEEPSLATE);
        dropOther(ModBlocks.INFESTED_DEEPSLATE_TILES.get(), Items.DEEPSLATE_TILES);
        dropOther(ModBlocks.INFESTED_CRACKED_DEEPSLATE_BRICKS.get(), Items.CRACKED_DEEPSLATE_BRICKS);
        dropOther(ModBlocks.INFESTED_CRACKED_DEEPSLATE_TILES.get(), Items.CRACKED_DEEPSLATE_TILES);
        dropOther(ModBlocks.INFESTED_DEEPSLATE_BRICKS.get(), Items.DEEPSLATE_BRICKS);
        dropOther(ModBlocks.INFESTED_CHISELED_DEEPSLATE_TILES.get(), ModBlocks.CHISELED_DEEPSLATE_TILES.get());
        dropOther(ModBlocks.INFESTED_CHISELED_DEEPSLATE_BRICKS.get(), ModBlocks.CHISELED_DEEPSLATE_BRICKS.get());
        dropOther(ModBlocks.INFESTED_MOSSY_DEEPSLATE_BRICKS.get(), ModBlocks.MOSSY_DEEPSLATE_BRICKS.get());
        dropOther(ModBlocks.INFESTED_MOSSY_COBBLED_DEEPSLATE.get(), ModBlocks.MOSSY_COBBLED_DEEPSLATE.get());
        dropOther(ModBlocks.INFESTED_MOSSY_DEEPSLATE_TILES.get(), ModBlocks.MOSSY_DEEPSLATE_TILES.get());
        dropOther(ModBlocks.REAL_TORCH.get(), Items.STICK);
        dropOther(ModBlocks.REAL_WALL_TORCH.get(), Items.STICK);
        dropOther(ModBlocks.REAL_SOUL_TORCH.get(), Items.STICK);
        dropOther(ModBlocks.REAL_SOUL_WALL_TORCH.get(), Items.STICK);
        dropOther(ModBlocks.POTTED_ROSE.get(), ModBlocks.ROSE.get());
        Set<Block> blocks = Helpers.getBlocks();
        blocks.stream().filter(block -> block instanceof StairBlock)
                .forEach(this::dropSelf);
        blocks.stream().filter(block -> block instanceof WallBlock)
                .forEach(this::dropSelf);
        blocks.stream().filter(block -> block instanceof SlabBlock)
                .forEach(this::addSlabDrop);
        blocks.stream().filter(block -> block instanceof StoneLikeBlock)
                .forEach(this::dropSelf);
        blocks.stream().filter(block -> block instanceof SandStoneBlock)
                .forEach(this::dropSelf);
        blocks.stream().filter(block -> block instanceof CarpetBlock)
                .forEach(this::dropSelf);
        blocks.stream().filter(block -> block instanceof FenceBlock)
                .forEach(this::dropSelf);
        blocks.stream().filter(block -> block instanceof ButtonBlock)
                .forEach(this::dropSelf);
        blocks.stream().filter(block -> block instanceof PressurePlateBlock)
                .forEach(this::dropSelf);
        melonStyleDrop(Blocks.PUMPKIN, ModItems.PUMPKIN_SLICE.get());
        melonStyleDrop(ModBlocks.GLISTERING_MELON.get(), Items.GLISTERING_MELON_SLICE);
    }

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> p_249322_) {
        generate();

        map.forEach(p_249322_);
    }

    void addPebble(Block block) {
        add(block, createSingleItemTable(ModItems.PEBBLE.get())
                .apply(SetNbtFunction.setTag(PebbleItem.getTagFrom(block))));
    }

    void melonStyleDrop(Block block, Item item) {
        add(block, (melon) -> createSilkTouchDispatchTable(melon, this.applyExplosionDecay(melon,
                LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction
                                .setCount(UniformGenerator.between(3.0F, 7.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                        .apply(LimitCount.limitCount(IntRange.upperBound(9))))));
    }

    void addOreDrop(Block block, Item item, int min, int max) {
        add(block, (ore) -> createSilkTouchDispatchTable(block, this.applyExplosionDecay(block,
                LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
    }

    void addSlabDrop(Block block) {
     add(block, this::createSlabItemTable);
    }

    void addVanillaPebble(Block block,Item item) {
        add(block, createSingleItemTable(item)
                .apply(SetNbtFunction.setTag(PebbleItem.getTagFrom(block))));
    }

}
