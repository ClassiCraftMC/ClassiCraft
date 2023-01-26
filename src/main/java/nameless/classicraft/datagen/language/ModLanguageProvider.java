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
package nameless.classicraft.datagen.language;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModEntities;
import nameless.classicraft.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(DataGenerator gen) {
        super(gen.getPackOutput(), ClassiCraftMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(ModItems.TORCH_LIT.get(), "Lit Torch");
        add(ModItems.TORCH_UNLIT.get(), "Unlit Torch");
        add(ModItems.SOUL_TORCH_UNLIT.get(), "Unlit Soul Torch");
        add(ModBlocks.REAL_TORCH.get(), "Real Torch");
        add(ModItems.DEBUG_BURN_TIME_STICK.get(), "Debug Burn Time Stick");
        add(ModItems.DEPTH_METER.get(), "Depth Meter");
        add(ModBlocks.QUICKSAND.get(), "Quicksand");
        add(ModBlocks.RED_QUICKSAND.get(), "Red Quicksand");
        add(ModBlocks.CHARCOAL_BLOCK.get(), "Charcoal Block");
        add(ModBlocks.CACTUS_BALL.get(), "Cactus ball");
        add(ModBlocks.ROSE.get(), "Rose");
        add(ModItems.TROUT_SPAWN_EGG.get(), "Trout Spawn Egg");
        add(ModItems.TROUT.get(), "Trout");
        add(ModItems.COOKED_TROUT.get(), "Cooked Trout");
        add(ModItems.TALLOW.get(), "Tallow");
        add(ModBlocks.TALLOW_BLOCK.get(), "Tallow Block");
        add(ModItems.TROUT_BUCKET.get(), "Trout Bucket");
        add("info.classicraft.burntime", "BurnTime");
        add("info.classicraft.minutes", "minutes");
        add("info.classicraft.depth.sky_land", "'s sky land");
        add("info.classicraft.depth.sky", "'s sky");
        add("info.classicraft.depth.cloud", "'s cloud");
        add("info.classicraft.depth.base_cloud", "'s base cloud");
        add("info.classicraft.depth.sea_level", "'s sea level");
        add("info.classicraft.depth.surface", "'s surface");
        add("info.classicraft.depth.underground", "'s sea level");
        add("info.classicraft.depth.deep_underground", "'s deep underground");
        add("info.classicraft.depth.bedrock", "'s bedrock");
        add("info.classicraft.depth.void", "'s void");
        add("info.classicraft.height", "At now is in the height ");
        add("level.classicraft.fresh", "Fresh ");
        add("level.classicraft.stale", "Stale ");
        add("level.classicraft.spoiled", "Spoiled ");
        add("level.classicraft.rotten", "Rotten ");
        add("info.classicraft.rot", "It will rotten after %s day");
        add("info.classicraft.rotting_speed", "Rotting Speed: %s");
        add("info.classicraft.shift_press", "Press Shift key to show more info");
        add("info.classicraft.food.nutrition", "Nutrition");
        add("info.classicraft.food.saturation", "Saturation");
        add(ModItems.OCEAN_SHARK_SPAWN_EGG.get(), "Ocean Shark Spawn Egg");
        add(ModItems.LIVING_DEAD_SPAWN_EGG.get(), "Living Dead Spawn Egg");
        add(ModItems.COOKED_EGG.get(), "Cooked Egg");
        add(ModItems.NETHER_MUSHROOM_STEW.get(), "Nether Mushroom Stew");
        add(ModItems.ROTTEN_FOOD.get(), "Rotten Food");
        add(ModBlocks.MOSSY_BRICKS.get(), "Mossy Bricks");
        add(ModBlocks.MOSSY_BRICKS_STAIRS.get(), "Mossy Bricks Stairs");
        add(ModBlocks.MOSSY_BRICKS_WALL.get(), "Mossy Bricks Wall");
        add(ModBlocks.MOSSY_BRICKS_SLAB.get(), "Mossy Bricks Slab");
        add(ModBlocks.CRACKED_BRICKS.get(), "Cracked Bricks");
        add(ModBlocks.CRACKED_BRICKS_STAIRS.get(), "Cracked Bricks Stairs");
        add(ModBlocks.CRACKED_BRICKS_WALL.get(), "Cracked Bricks Wall");
        add(ModBlocks.CRACKED_BRICKS_SLAB.get(), "Cracked Bricks Slab");
        add(ModBlocks.SULFUR_ORE.get(), "Sulfur Ore");
        add(ModItems.SULFUR.get(), "Sulfur");
        add(ModBlocks.SMOOTH_STONE_WALL.get(), "Smooth Stone Wall");
        add(ModBlocks.SMOOTH_STONE_STAIRS.get(), "Smooth Stone Stairs");
        add(ModBlocks.STONE_WALL.get(), "Stone Wall");
        add(ModBlocks.FLINT_BLOCK.get(), "Flint Block");
        add(ModBlocks.CHISELED_SOUL_SANDSTONE.get(), "Chiseled Soul SandStone");
        add(ModBlocks.CHISELED_QUARTZ_SANDSTONE.get(), "Chiseled Quartz SandStone");
        add(ModBlocks.CUT_SOUL_SANDSTONE.get(), "Cut Soul SandStone");
        add(ModBlocks.CUT_QUARTZ_SANDSTONE.get(), "Cut Quartz SandStone");
        add(ModBlocks.QUARTZ_QUICKSAND.get(), "Quartz QuickSand");
        add(ModBlocks.QUARTZ_SAND.get(), "Quartz Sand");
        add(ModBlocks.QUARTZ_SANDSTONE.get(), "Quartz SandStone");
        add(ModBlocks.QUARTZ_SANDSTONE_BRICKS.get(), "Quartz SandStone Bricks");
        add(ModBlocks.RED_SANDSTONE_BRICKS.get(), "Red SandStone Bricks");
        add(ModBlocks.SANDSTONE_BRICKS.get(), "SandStone Bricks");
        add(ModBlocks.SOUL_SANDSTONE.get(), "Soul SandStone");
        add(ModBlocks.SOUL_QUICKSAND.get(), "Soul QuickSand");
        add(ModBlocks.SOUL_SANDSTONE_BRICKS.get(), "Soul SandStone Bricks");
        add(ModBlocks.CRACKED_STONE_BRICKS_SLAB.get(), "Cracked Stone Bricks Slab");
        add(ModBlocks.CRACKED_STONE_BRICKS_STAIRS.get(), "Cracked Stone Bricks Stairs");
        add(ModBlocks.CRACKED_STONE_BRICKS_WALL.get(), "Cracked Stone Bricks Wall");
        add(ModBlocks.POLISHED_GRANITE_WALL.get(), "Polished Granite Wall");
        add(ModBlocks.POLISHED_ANDESITE_WALL.get(), "Polished Andesite Wall");
        add(ModBlocks.POLISHED_DIORITE_WALL.get(), "Polished Diorite Wall");
        add(ModBlocks.INFESTED_MOSSY_COBBLESTONE.get(), "Infested Mossy CobbleStone");
        add(ModBlocks.DEEPSLATE_WALL.get(), "Deepslate Wall");
        add(ModBlocks.DEEPSLATE_STAIRS.get(), "Deepslate Stairs");
        add(ModBlocks.DEEPSLATE_SLAB.get(), "Deepslate Slab");
        add(ModBlocks.CRACKED_DEEPSLATE_BRICKS_WALL.get(), "Cracked Deepslate Bricks Wall");
        add(ModBlocks.CRACKED_DEEPSLATE_BRICKS_STAIRS.get(), "Cracked Deepslate Bricks Stairs");
        add(ModBlocks.CRACKED_DEEPSLATE_BRICKS_SLAB.get(), "Cracked Deepslate Bricks Slab");
        add(ModBlocks.INFESTED_CHISELED_DEEPSLATE.get(), "Infested Chiseled DeepSlate");
        add(ModBlocks.INFESTED_COBBLED_DEEPSLATE.get(), "Infested Cobbled DeepSlate");
        add(ModBlocks.INFESTED_DEEPSLATE_BRICKS.get(), "Infested DeepSlate Bricks");
        add(ModBlocks.INFESTED_DEEPSLATE_TILES.get(), "Infested DeepSlate Tiles");
        add(ModBlocks.SMOOTH_SANDSTONE_WALL.get(), "Smooth SandStone Wall");
        add(ModBlocks.CUT_SANDSTONE_WALL.get(), "Cut SandStone Wall");
        add(ModBlocks.CUT_SANDSTONE_STAIRS.get(), "Cut SandStone Stairs");
        add(ModBlocks.SMOOTH_RED_SANDSTONE_WALL.get(), "Smooth Red Sandstone Wall");
        add(ModBlocks.CUT_RED_SANDSTONE_STAIRS.get(), "Cut Red SandStone Stairs");
        add(ModBlocks.CUT_RED_SANDSTONE_WALL.get(), "Cut Res SandStone Wall");
        add(ModBlocks.SOUL_SANDSTONE_SLAB.get(), "Soul SandStone Slab");
        add(ModBlocks.SOUL_SANDSTONE_STAIRS.get(), "Soul SandStone Stairs");
        add(ModBlocks.SOUL_SANDSTONE_WALL.get(), "Soul SandStone Wall");
        add(ModBlocks.SMOOTH_SOUL_SANDSTONE.get(), "Smooth Soul SandStone");
        add(ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB.get(), "Smooth Soul SandStone Slab");
        add(ModBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS.get(), "Smooth Soul SandStone Stairs");
        add(ModBlocks.SMOOTH_SOUL_SANDSTONE_WALL.get(), "Smooth Soul SandStone Wall");
        add(ModBlocks.CUT_SOUL_SANDSTONE_WALL.get(), "Cut Soul SandStone Wall");
        add(ModBlocks.CUT_SOUL_SANDSTONE_STAIRS.get(), "Cut Soul SandStone Stairs");
        add(ModBlocks.END_STONE_SLAB.get(), "End Stone Slab");
        add(ModBlocks.END_STONE_STAIRS.get(), "End Stone Stairs");
        add(ModBlocks.END_STONE_WALL.get(), "End Stone Wall");
        add(ModBlocks.SANDSTONE_BRICKS_SLAB.get(), "SandStone Bricks Slab");
        add(ModBlocks.SANDSTONE_BRICKS_STAIRS.get(), "SandStone Bricks Stairs");
        add(ModBlocks.SANDSTONE_BRICKS_WALL.get(), "SandStone Bricks Wall");
        add(ModBlocks.QUARTZ_SANDSTONE_BRICKS_SLAB.get(), "Quartz SandStone Bricks Slab");
        add(ModBlocks.QUARTZ_SANDSTONE_BRICKS_STAIRS.get(), "Quartz SandStone Bricks Stairs");
        add(ModBlocks.QUARTZ_SANDSTONE_BRICKS_WALL.get(), "Quartz SandStone Bricks Wall");
        add(ModBlocks.SMOOTH_QUARTZ_SANDSTONE_SLAB.get(), "Smooth Quartz SandStone Slab");
        add(ModBlocks.SMOOTH_QUARTZ_SANDSTONE_STAIRS.get(), "Smooth Quartz SandStone Stairs");
        add(ModBlocks.SMOOTH_QUARTZ_SANDSTONE_WALL.get(), "Smooth Quartz SandStone Wall");
        add(ModBlocks.NETHERRACK_SLAB.get(), "Netherrack Slab");
        add(ModBlocks.NETHERRACK_STAIRS.get(), "Netherrack Stairs");
        add(ModBlocks.NETHERRACK_WALL.get(), "Netherrack Wall");
        add(ModBlocks.RED_SANDSTONE_BRICKS_SLAB.get(), "Red SandStone Bricks Slab");
        add(ModBlocks.RED_SANDSTONE_BRICKS_STAIRS.get(), "Red SandStone Bricks Stairs");
        add(ModBlocks.RED_SANDSTONE_BRICKS_WALL.get(), "Red SandStone Bricks Wall");
        add(ModBlocks.INFESTED_CRACKED_DEEPSLATE_BRICKS.get(), "Infested Cracked Deepslate Bricks");
        add(ModBlocks.INFESTED_CRACKED_DEEPSLATE_TILES.get(), "Infested Cracked Deepslate Tiles");
        add(ModBlocks.PRISMARINE_BRICKS_WALL.get(), "Prismarine Bricks Wall");
        add(ModBlocks.DARK_PRISMARINE_WALL.get(), "Dark Prismarine Wall");
        add(ModBlocks.CRACKED_DEEPSLATE_TILES_WALL.get(), "Cracked Deepslate Tiles Wall");
        add(ModBlocks.CRACKED_DEEPSLATE_TILES_STAIRS.get(), "Cracked Deepslate Tiles Stairs");
        add(ModBlocks.CRACKED_DEEPSLATE_TILES_SLAB.get(), "Cracked Deepslate Tiles Slab");
        add(ModBlocks.SMOOTH_BASALT_SLAB.get(), "Smooth Basalt Slab");
        add(ModBlocks.SMOOTH_BASALT_STAIRS.get(), "Smooth Basalt Stairs");
        add(ModBlocks.SMOOTH_BASALT_WALL.get(), "Smooth Basalt Wall");
        add(ModBlocks.FLINT_BLOCK_SLAB.get(), "Flint Block Slab");
        add(ModBlocks.FLINT_BLOCK_STAIRS.get(), "Flint Block Stairs");
        add(ModBlocks.FLINT_BLOCK_WALL.get(), "Flint Block Wall");
        add(ModBlocks.SOUL_SANDSTONE_BRICKS_SLAB.get(), "Soul SandStone Brick Slab");
        add(ModBlocks.SOUL_SANDSTONE_BRICKS_STAIRS.get(), "Soul SandStone Brick Stairs");
        add(ModBlocks.SOUL_SANDSTONE_BRICKS_WALL.get(), "Soul SandStone Brick Wall");
        add(ModBlocks.CUT_QUARTZ_SANDSTONE_SLAB.get(), "Cut Quartz SandStone Slab");
        add(ModBlocks.CUT_QUARTZ_SANDSTONE_STAIRS.get(), "Cut Quartz SandStone Stairs");
        add(ModBlocks.CUT_QUARTZ_SANDSTONE_WALL.get(), "Cut Quartz SandStone Wall");
        add(ModBlocks.QUARTZ_BRICKS_WALL.get(), "Quartz Bricks Wall");
        add(ModBlocks.QUARTZ_BRICKS_SLAB.get(), "Quartz Bricks Slab");
        add(ModBlocks.QUARTZ_BRICKS_STAIRS.get(), "Quartz Bricks Stairs");
        add("itemGroup.classicraft.common", "ClassiCraft|Common");
        add("itemGroup.classicraft.building_blocks", "ClassiCraft|Building Blocks");
        add("itemGroup.classicraft.material", "ClassiCraft|Material");
        add("itemGroup.classicraft.natural_blocks", "ClassiCraft|Natural Blocks");
        add(ModBlocks.WHITE_WOOL_STAIRS.get(), "White Wool Stairs");
        add(ModBlocks.WHITE_WOOL_SLAB.get(), "White Wool Slab");
        add(ModBlocks.ORANGE_WOOL_STAIRS.get(), "Orange Wool Stairs");
        add(ModBlocks.ORANGE_WOOL_SLAB.get(), "Orange Wool Slab");
        add(ModBlocks.MAGENTA_WOOL_STAIRS.get(), "Magenta Wool Stairs");
        add(ModBlocks.MAGENTA_WOOL_SLAB.get(), "Magenta Wool Slab");
        add(ModBlocks.LIGHT_BLUE_WOOL_STAIRS.get(), "Light Blue Wool Stairs");
        add(ModBlocks.LIGHT_BLUE_WOOL_SLAB.get(), "Light Blue Wool Slab");
        add(ModBlocks.YELLOW_WOOL_STAIRS.get(), "Yellow Wool Stairs");
        add(ModBlocks.YELLOW_WOOL_SLAB.get(), "Yellow Wool Slab");
        add(ModBlocks.LIME_WOOL_STAIRS.get(), "Lime Wool Stairs");
        add(ModBlocks.LIME_WOOL_SLAB.get(), "Lime Wool Slab");
        add(ModBlocks.PINK_WOOL_STAIRS.get(), "Pink Wool Stairs");
        add(ModBlocks.PINK_WOOL_SLAB.get(), "Pink Wool Slab");
        add(ModBlocks.GRAY_WOOL_STAIRS.get(), "Gray Wool Stairs");
        add(ModBlocks.GRAY_WOOL_SLAB.get(), "Gray Wool Slab");
        add(ModBlocks.LIGHT_GRAY_WOOL_STAIRS.get(), "Light Gray Wool Stairs");
        add(ModBlocks.LIGHT_GRAY_WOOL_SLAB.get(), "Light Gray Wool Slab");
        add(ModBlocks.CYAN_WOOL_STAIRS.get(), "Cyan Wool Stairs");
        add(ModBlocks.CYAN_WOOL_SLAB.get(), "Cyan Wool Slab");
        add(ModBlocks.PURPLE_WOOL_STAIRS.get(), "Purple Wool Stairs");
        add(ModBlocks.PURPLE_WOOL_SLAB.get(), "Purple Wool Slab");
        add(ModBlocks.BLUE_WOOL_STAIRS.get(), "Blue Wool Stairs");
        add(ModBlocks.BLUE_WOOL_SLAB.get(), "Blue Wool Slab");
        add(ModBlocks.BROWN_WOOL_STAIRS.get(), "Brown Wool Stairs");
        add(ModBlocks.BROWN_WOOL_SLAB.get(), "Brown Wool Slab");
        add(ModBlocks.GREEN_WOOL_STAIRS.get(), "Green Wool Stairs");
        add(ModBlocks.GREEN_WOOL_SLAB.get(), "Green Wool Slab");
        add(ModBlocks.RED_WOOL_STAIRS.get(), "Red Wool Stairs");
        add(ModBlocks.RED_WOOL_SLAB.get(), "Red Wool Slab");
        add(ModBlocks.BLACK_WOOL_STAIRS.get(), "Black Wool Stairs");
        add(ModBlocks.BLACK_WOOL_SLAB.get(), "Black Wool Slab");
        add(ModBlocks.QUARTZ_SANDSTONE_WALL.get(), "Quartz SandStone Wall");
        add(ModBlocks.QUARTZ_SANDSTONE_STAIRS.get(), "Quartz SandStone Stairs");
        add(ModBlocks.QUARTZ_SANDSTONE_SLAB.get(), "Quartz SandStone Slab");
        add(ModBlocks.POLISHED_BASALT_SLAB.get(), "Polished Basalt Slab");
        add(ModBlocks.POLISHED_BASALT_STAIRS.get(), "Polished Basalt Stairs");
        add(ModBlocks.POLISHED_BASALT_WALL.get(), "Polished Basalt Wall");
        add(ModBlocks.CRIMSON_NYLIUM_SLAB.get(), "Crimson Nylium Slab");
        add(ModBlocks.CRIMSON_NYLIUM_STAIRS.get(), "Crimson Nylium Stairs");
        add(ModBlocks.CRIMSON_NYLIUM_WALL.get(), "Crimson Nylium Wall");
        add(ModBlocks.WARPED_NYLIUM_SLAB.get(), "Warped Nylium Slab");
        add(ModBlocks.WARPED_NYLIUM_STAIRS.get(), "Warped Nylium Stairs");
        add(ModBlocks.WARPED_NYLIUM_WALL.get(), "Warped Nylium Wall");
        add(ModBlocks.CRACKED_NETHER_BRICKS_SLAB.get(), "Cracked Nether Bricks Slab");
        add(ModBlocks.CRACKED_NETHER_BRICKS_STAIRS.get(), "Cracked Nether Bricks Stairs");
        add(ModBlocks.CRACKED_NETHER_BRICKS_WALL.get(), "Cracked Nether Bricks Wall");
        add(ModBlocks.CRIMSON_NETHER_BRICKS_FENCE.get(), "Crimson Nether Bricks Fence");
        add(ModBlocks.QUARTZ_WALL.get(), "Quartz Wall");
        add(ModBlocks.PURPUR_BLOCK_WALL.get(), "Purpur Block Wall");
        add(ModBlocks.SMOOTH_QUARTZ_WALL.get(), "Smooth Quartz Wall");
        add(ModBlocks.CRACKED_POLISHED_BLACKSTONE_BRICKS_STAIRS.get(), "Cracked Polished BlackStone Bricks Stairs");
        add(ModBlocks.CRACKED_POLISHED_BLACKSTONE_BRICKS_SLAB.get(), "Cracked Polished BlackStone Bricks Slab");
        add(ModBlocks.CRACKED_POLISHED_BLACKSTONE_BRICKS_WALL.get(), "Cracked Polished BlackStone Bricks Wall");
        add(ModBlocks.CUT_SOUL_SANDSTONE_SLAB.get(), "Cut Soul SandStone Slab");
        add(ModBlocks.SMOOTH_QUARTZ_SANDSTONE.get(), "Smooth Quartz SandStone");
        add(ModItems.PHOBOS_OUTPOST_DISC.get(), "§bMusic Disc");
        add("item.classicraft.phobos_outpost_disc.desc", "Scott Lloyd Shelly - Phobos Outpost");
        add(ModItems.DRAGON_FISH_DISC.get(), "§bMusic Disc");
        add("item.classicraft.dragon_fish_disc.desc", "C418 - Dragon Fish");
        add(ModBlocks.WHITE_STAINED_GLASS_STAIRS.get(), "White Stained Glass Stairs");
        add(ModBlocks.WHITE_STAINED_GLASS_SLAB.get(), "White Stained Glass Slab");
        add(ModBlocks.WHITE_STAINED_GLASS_WALL.get(), "White Stained Glass Wall");
        add(ModBlocks.ORANGE_STAINED_GLASS_STAIRS.get(), "Orange Stained Glass Stairs");
        add(ModBlocks.ORANGE_STAINED_GLASS_SLAB.get(), "Orange Stained Glass Slab");
        add(ModBlocks.ORANGE_STAINED_GLASS_WALL.get(), "Orange Stained Glass Wall");
        add(ModBlocks.MAGENTA_STAINED_GLASS_STAIRS.get(), "Magenta Stained Glass Stairs");
        add(ModBlocks.MAGENTA_STAINED_GLASS_SLAB.get(), "Magenta Stained Glass Slab");
        add(ModBlocks.MAGENTA_STAINED_GLASS_WALL.get(), "Magenta Stained Glass Wall");
        add(ModBlocks.LIGHT_BLUE_STAINED_GLASS_STAIRS.get(), "Light Blue Stained Glass Stairs");
        add(ModBlocks.LIGHT_BLUE_STAINED_GLASS_SLAB.get(), "Light Blue Stained Glass Slab");
        add(ModBlocks.LIGHT_BLUE_STAINED_GLASS_WALL.get(), "Light Blue Stained Glass Wall");
        add(ModBlocks.YELLOW_STAINED_GLASS_STAIRS.get(), "Yellow Stained Glass Stairs");
        add(ModBlocks.YELLOW_STAINED_GLASS_SLAB.get(), "Yellow Stained Glass Slab");
        add(ModBlocks.YELLOW_STAINED_GLASS_WALL.get(), "Yellow Stained Glass Wall");
        add(ModBlocks.LIME_STAINED_GLASS_STAIRS.get(), "Lime Stained Glass Stairs");
        add(ModBlocks.LIME_STAINED_GLASS_SLAB.get(), "Lime Stained Glass Slab");
        add(ModBlocks.LIME_STAINED_GLASS_WALL.get(), "Lime Stained Glass Wall");
        add(ModBlocks.PINK_STAINED_GLASS_STAIRS.get(), "Pink Stained Glass Stairs");
        add(ModBlocks.PINK_STAINED_GLASS_SLAB.get(), "Pink Stained Glass Slab");
        add(ModBlocks.PINK_STAINED_GLASS_WALL.get(), "Pink Stained Glass Wall");
        add(ModBlocks.GRAY_STAINED_GLASS_STAIRS.get(), "Gray Stained Glass Stairs");
        add(ModBlocks.GRAY_STAINED_GLASS_SLAB.get(), "Gray Stained Glass Slab");
        add(ModBlocks.GRAY_STAINED_GLASS_WALL.get(), "Gray Stained Glass Wall");
        add(ModBlocks.LIGHT_GRAY_STAINED_GLASS_STAIRS.get(), "Light Gray Stained Glass Stairs");
        add(ModBlocks.LIGHT_GRAY_STAINED_GLASS_SLAB.get(), "Light Gray Stained Glass Slab");
        add(ModBlocks.LIGHT_GRAY_STAINED_GLASS_WALL.get(), "Light Gray Stained Glass Wall");
        add(ModBlocks.CYAN_STAINED_GLASS_STAIRS.get(), "Cyan Stained Glass Stairs");
        add(ModBlocks.CYAN_STAINED_GLASS_SLAB.get(), "Cyan Stained Glass Slab");
        add(ModBlocks.CYAN_STAINED_GLASS_WALL.get(), "Cyan Stained Glass Wall");
        add(ModBlocks.PURPLE_STAINED_GLASS_STAIRS.get(), "Purple Stained Glass Stairs");
        add(ModBlocks.PURPLE_STAINED_GLASS_SLAB.get(), "Purple Stained Glass Slab");
        add(ModBlocks.PURPLE_STAINED_GLASS_WALL.get(), "Purple Stained Glass Wall");
        add(ModBlocks.BLUE_STAINED_GLASS_STAIRS.get(), "Blue Stained Glass Stairs");
        add(ModBlocks.BLUE_STAINED_GLASS_SLAB.get(), "Blue Stained Glass Slab");
        add(ModBlocks.BLUE_STAINED_GLASS_WALL.get(), "Blue Stained Glass Wall");
        add(ModBlocks.BROWN_STAINED_GLASS_STAIRS.get(), "Brown Stained Glass Stairs");
        add(ModBlocks.BROWN_STAINED_GLASS_SLAB.get(), "Brown Stained Glass Slab");
        add(ModBlocks.BROWN_STAINED_GLASS_WALL.get(), "Brown Stained Glass Wall");
        add(ModBlocks.GREEN_STAINED_GLASS_STAIRS.get(), "Green Stained Glass Stairs");
        add(ModBlocks.GREEN_STAINED_GLASS_SLAB.get(), "Green Stained Glass Slab");
        add(ModBlocks.GREEN_STAINED_GLASS_WALL.get(), "Green Stained Glass Wall");
        add(ModBlocks.RED_STAINED_GLASS_STAIRS.get(), "Red Stained Glass Stairs");
        add(ModBlocks.RED_STAINED_GLASS_SLAB.get(), "Red Stained Glass Slab");
        add(ModBlocks.RED_STAINED_GLASS_WALL.get(), "Red Stained Glass Wall");
        add(ModBlocks.BLACK_STAINED_GLASS_STAIRS.get(), "Black Stained Glass Stairs");
        add(ModBlocks.BLACK_STAINED_GLASS_SLAB.get(), "Black Stained Glass Slab");
        add(ModBlocks.BLACK_STAINED_GLASS_WALL.get(), "Black Stained Glass Wall");
        add(ModBlocks.GLASS_SLAB.get(), "Glass Slab");
        add(ModBlocks.GLASS_STAIRS.get(), "Glass Stairs");
        add(ModBlocks.GLASS_WALL.get(), "Glass Wall");
        add(ModBlocks.TINTED_GLASS_SLAB.get(), "Tinted Glass Slab");
        add(ModBlocks.TINTED_GLASS_STAIRS.get(), "Tinted Glass Stairs");
        add(ModBlocks.TINTED_GLASS_WALL.get(), "Tinted Glass Wall");
        add(ModBlocks.GLISTERING_MELON.get(), "Glistering Melon");

        pebbleBlock();
        meta();
        entity();
        vanilla();
    }

    private void pebbleBlock() {
        add(ModBlocks.FLINT.get(), "Flint Pebble");
        add(ModBlocks.ANDESITE_PEBBLE.get(), "Andesite Pebble");
        add(ModBlocks.COBBLESTONE_PEBBLE.get(), "CobbleStone Pebble");
        add(ModBlocks.DIORITE_PEBBLE.get(), "Diorite Pebble");
        add(ModBlocks.GRANITE_PEBBLE.get(), "Granite Pebble");
        add(ModBlocks.RED_SANDSTONE_PEBBLE.get(), "Red SandStone Pebble");
        add(ModBlocks.SANDSTONE_PEBBLE.get(), "SandStone Pebble");
        add(ModBlocks.DEEPSLATE_PEBBLE.get(), "Deepslate Pebble");
        add(ModBlocks.QUARTZ_SANDSTONE_PEBBLE.get(), "Quartz SandStone Pebble");
        add(ModBlocks.SOUL_SANDSTONE_PEBBLE.get(), "Soul SandStone Pebble");
        add(ModBlocks.NETHERRACK_PEBBLE.get(), "Nether Rack Pebble");
        add(ModBlocks.END_STONE_PEBBLE.get(), "End Stone Pebble");
        add(ModBlocks.BASALT_PEBBLE.get(), "Basalt Pebble");
        add(ModBlocks.BLACKSTONE_PEBBLE.get(), "BlackStone Pebble");
        add(ModBlocks.PRISMARINE.get(), "Prismarine Pebble");
        add(ModBlocks.QUARTZ_PEBBLE.get(), "Quartz Pebble");
    }

    private void entity() {
        add(ModEntities.OCEAN_SHARK_ENTITY.get(), "Ocean Shark");
        add(ModEntities.TROUT_ENTITY.get(), "Trout");
        add(ModEntities.LIVING_DEAD.get(), "Living Dead");
    }

    private void vanilla() {
        add(Blocks.NETHER_WART_BLOCK, "Crimson Wart Block");
        add(Blocks.NETHER_SPROUTS, "Crimson Sprouts");
        add(Items.NETHER_WART, "Crimson Wart");
        add(Blocks.RED_NETHER_BRICKS, "Crimson Nether Bricks");
        add(Blocks.RED_NETHER_BRICK_STAIRS, "Crimson Nether Brick Stairs");
        add(Blocks.RED_NETHER_BRICK_SLAB, "Crimson Nether Brick Slab");
        add(Blocks.RED_NETHER_BRICK_WALL, "Crimson Nether Brick Wall");
    }

    private void meta() {
        add("item.classicraft.adze.cobblestone_adze", "Cobblestone Adze");
        add("item.classicraft.adze.deepslate_adze", "Deepslate Adze");
        add("item.classicraft.adze.flint_adze", "Flint Adze");
        add("item.classicraft.adze.blackstone_adze", "Blackstone Adze");
        add("item.classicraft.adze.quartz_adze", "Quartz Adze");
        add("item.classicraft.awl.cobblestone_awl", "Cobblestone Awl");
        add("item.classicraft.awl.deepslate_awl", "Deepslate Awl");
        add("item.classicraft.awl.flint_awl", "Flint Awl");
        add("item.classicraft.awl.blackstone_awl", "Blackstone Awl");
        add("item.classicraft.awl.quartz_awl", "Quartz Awl");
        add("item.classicraft.chopper.cobblestone_chopper", "Cobblestone Chopper");
        add("item.classicraft.chopper.deepslate_chopper", "Deepslate Chopper");
        add("item.classicraft.chopper.flint_chopper", "Flint Chopper");
        add("item.classicraft.chopper.blackstone_chopper", "Blackstone Chopper");
        add("item.classicraft.chopper.quartz_chopper", "Quartz Chopper");
        add("item.classicraft.pebble.andesite_pebble", "Andesite Pebble");
        add("item.classicraft.pebble.basalt_pebble", "Basalt Pebble");
        add("item.classicraft.pebble.blackstone_pebble", "Blackstone Pebble");
        add("item.classicraft.pebble.cobblestone_pebble", "Cobblestone Pebble");
        add("item.classicraft.pebble.deepslate_pebble", "Deepslate Pebble");
        add("item.classicraft.pebble.diorite_pebble", "Diorite Pebble");
        add("item.classicraft.pebble.granite_pebble", "Granite Pebble");
        add("item.classicraft.pebble.red_sandstone_pebble", "Red Sandstone Pebble");
        add("item.classicraft.pebble.end_stone_pebble", "End Stone Pebble");
        add("item.classicraft.pebble.netherrack_pebble", "Netherrack Pebble");
        add("item.classicraft.pebble.quartz_sandstone_pebble", "Quartz Sandstone Pebble");
        add("item.classicraft.pebble.soul_sandstone_pebble", "Soul Sandstone Pebble");
        add("item.classicraft.pebble.sandstone_pebble", "Sandstone Pebble");
        add("item.classicraft.point.cobblestone_point", "Cobblestone Point");
        add("item.classicraft.point.deepslate_point", "Deepslate Point");
        add("item.classicraft.point.flint_point", "Flint Point");
        add("item.classicraft.point.blackstone_point", "Blackstone Point");
        add("item.classicraft.point.quartz_point", "Quartz Point");
        add("item.classicraft.scraper.cobblestone_scraper", "Cobblestone Scraper");
        add("item.classicraft.scraper.deepslate_scraper", "Deepslate Scraper");
        add("item.classicraft.scraper.flint_scraper", "Flint Scraper");
        add("item.classicraft.scraper.blackstone_scraper", "Blackstone Scraper");
        add("item.classicraft.scraper.quartz_scraper", "Quartz Scraper");
    }
}
