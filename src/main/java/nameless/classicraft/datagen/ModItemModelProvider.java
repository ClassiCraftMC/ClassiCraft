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
import nameless.classicraft.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Objects;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), ClassiCraftMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        stairsInventory("smooth_stone", "minecraft:block/smooth_stone");
        stairsInventory("mossy_bricks", "classicraft:block/mossy_bricks");
        stairsInventory("cracked_bricks", "classicraft:block/cracked_bricks");
        stairsInventory("cracked_stone_bricks", "minecraft:block/cracked_stone_bricks");
        stairsInventory("deepslate", "minecraft:block/deepslate");
        stairsInventory("cut_sandstone", "minecraft:block/cut_sandstone", "minecraft:block/sandstone_bottom", "minecraft:block/sandstone_top");
        stairsInventory("cut_red_sandstone", "minecraft:block/cut_red_sandstone", "minecraft:block/red_sandstone_bottom", "minecraft:block/red_sandstone_top");
        stairsInventory("cracked_deepslate_bricks", "minecraft:block/cracked_deepslate_bricks");
        stairsInventory("soul_sandstone", "classicraft:block/soul_sandstone", "classicraft:block/soul_sandstone_bottom", "classicraft:block/soul_sandstone_top");
        stairsInventory("cut_soul_sandstone", "classicraft:block/cut_soul_sandstone", "classicraft:block/soul_sandstone_bottom", "classicraft:block/soul_sandstone_top");
        stairsInventory("smooth_soul_sandstone", "classicraft:block/smooth_soul_sandstone");
        stairsInventory("cut_quartz_sandstone", "classicraft:block/cut_quartz_sandstone", "classicraft:block/quartz_sandstone_bottom", "classicraft:block/quartz_sandstone_top");
        stairsInventory("quartz_sandstone","classicraft:block/quartz_sandstone", "classicraft:block/quartz_sandstone_bottom", "classicraft:block/quartz_sandstone_top");
        stairsInventory("polished_basalt", "minecraft:block/polished_basalt_side", "minecraft:block/polished_basalt_top", "minecraft:block/polished_basalt_top");
        wallInventory("smooth_soul_sandstone_wall",
                new ResourceLocation("classicraft:block/soul_sandstone_top"));
        wallInventory("smooth_stone_wall",
                new ResourceLocation("minecraft:block/smooth_stone"));
        wallInventory("stone_wall",
                new ResourceLocation("minecraft:block/stone"));
        wallInventory("mossy_bricks_wall",
                new ResourceLocation("classicraft:block/mossy_bricks"));
        wallInventory("cracked_bricks_wall",
                new ResourceLocation("classicraft:block/cracked_bricks"));
        wallInventory("cracked_stone_bricks_wall",
                new ResourceLocation("minecraft:block/cracked_stone_bricks"));
        wallInventory("polished_granite_wall",
                new ResourceLocation("minecraft:block/polished_granite"));
        wallInventory("polished_andesite_wall",
                new ResourceLocation("minecraft:block/polished_andesite"));
        wallInventory("polished_diorite_wall",
                new ResourceLocation("minecraft:block/polished_diorite"));
        wallInventory("smooth_stone_wall",
                new ResourceLocation("minecraft:block/smooth_stone"));
        wallInventory("cracked_deepslate_bricks_wall",
                new ResourceLocation("minecraft:block/cracked_deepslate_bricks"));
        wallInventory("deepslate_wall",
                new ResourceLocation("minecraft:block/deepslate"));
        wallInventory("purpur_block", "minecraft:block/purpur_block");
        wallInventory("smooth_quartz", "minecraft:block/quartz_block_bottom");
        wallInventory("quartz", "minecraft:block/quartz_block_side");
        wallInventory("polished_basalt", "minecraft:block/polished_basalt_side");
        wallInventory("quartz_sandstone","classicraft:block/quartz_sandstone");
        wallInventory("soul_sandstone", "classicraft:block/soul_sandstone");
        wallInventory("cut_quartz_sandstone", "classicraft:block/cut_quartz_sandstone");
        wallInventory("smooth_soul_sandstone", "classicraft:block/soul_sandstone_top");
        wallInventory("cut_soul_sandstone", "classicraft:block/cut_soul_sandstone");
        wallInventory("cut_red_sandstone", "minecraft:block/cut_red_sandstone");
        wallInventory("smooth_sandstone", "minecraft:block/sandstone_top");
        wallInventory("smooth_red_sandstone", "minecraft:block/red_sandstone_top");
        wallInventory("cut_sandstone", "minecraft:block/cut_sandstone");
        wallInventory("dark_prismarine","minecraft:block/dark_prismarine");
        wallInventory("prismarine_bricks", "minecraft:block/prismarine_bricks");
        slabInventory("mossy_bricks_slab", "classicraft:block/mossy_bricks");
        slabInventory("cracked_bricks_slab", "classicraft:block/cracked_bricks");
        slabInventory("quartz_sandstone_slab", "classicraft:block/quartz_sandstone_bottom", "classicraft:block/quartz_sandstone_top", "classicraft:block/quartz_sandstone");
        slabInventory("cut_quartz_sandstone_slab", "classicraft:block/quartz_sandstone_bottom", "classicraft:block/quartz_sandstone_top", "classicraft:block/cut_quartz_sandstone");
        slabInventory("soul_sandstone_slab", "classicraft:block/soul_sandstone_bottom", "classicraft:block/soul_sandstone_top", "classicraft:block/soul_sandstone");
        slabInventory("cracked_stone_bricks_slab", "minecraft:block/cracked_stone_bricks");
        slabInventory("deepslate_slab", "minecraft:block/deepslate_top", "minecraft:block/deepslate_top", "minecraft:block/deepslate");
        slabInventory("cracked_deepslate_bricks_slab", "minecraft:block/cracked_deepslate_bricks");
        slabInventory("cut_soul_sandstone_slab", "classicraft:block/soul_sandstone_bottom", "classicraft:block/soul_sandstone_top", "classicraft:block/cut_soul_sandstone");
        slabInventory("smooth_soul_sandstone_slab", "classicraft:block/soul_sandstone_bottom", "classicraft:block/soul_sandstone_top", "classicraft:block/smooth_soul_sandstone");
        slabInventory("polished_basalt_slab", "minecraft:block/polished_basalt_top", "minecraft:block/polished_basalt_top", "minecraft:block/polished_basalt_side");
        basicItem(ModItems.MATERIAL.get());
        basicItem(ModItems.DEBUG_BURN_TIME_STICK.get());
        basicItem(ModItems.PUMPKIN_SLICE.get());
        basicItem(ModItems.MOSS_CLUMP.get());
        basicItem(ModItems.CERAMIC_BOWL.get());
        basicItem(ModItems.UNFIRED_CLAY_BRICK.get());
        basicItem(ModItems.UNFIRED_CLAY_BOWL.get());
        basicItem(ModItems.UNFIRED_CLAY_FLOWER_POT.get());
        basicItem(ModItems.PLAINTIVE_SOUL.get());
        basicItem(ModItems.SHIVER_BONE.get());
        basicItem(ModItems.WITHER_BONE.get());
        basicItem(ModItems.FURIOUS_SOUL.get());
        basicItem(ModItems.CAREFREE_SOUL.get());
        basicItem(ModItems.TERRIFIED_SOUL.get());
        basicItem(ModItems.NITER.get());
        toolItem(ModItems.STONE_HAMMER.get());
        toolItem(ModItems.WOODEN_HAMMER.get());
        toolItem(ModItems.IRON_HAMMER.get());
        toolItem(ModItems.GOLDEN_HAMMER.get());
        toolItem(ModItems.DIAMOND_HAMMER.get());
        toolItem(ModItems.NETHERITE_HAMMER.get());
        threeBuildBlockItems("cracked_deepslate_tiles", "minecraft:block/cracked_deepslate_tiles");
        threeBuildBlockItems("sandstone_bricks", "classicraft:block/sandstone_bricks");
        threeBuildBlockItems("red_sandstone_bricks", "classicraft:block/red_sandstone_bricks");
        threeBuildBlockItems("soul_sandstone_bricks", "classicraft:block/soul_sandstone_bricks");
        threeBuildBlockItems("flint_block", "classicraft:block/flint_block");
        threeBuildBlockItems("quartz_sandstone_bricks", "classicraft:block/quartz_sandstone_bricks");
        threeBuildBlockItems("end_stone", "minecraft:block/end_stone");
        threeBuildBlockItems("netherrack", "minecraft:block/netherrack");
        threeBuildBlockItems("smooth_quartz_sandstone", "classicraft:block/smooth_quartz_sandstone");
        threeBuildBlockItems("smooth_basalt", "minecraft:block/smooth_basalt");
        threeBuildBlockItems("quartz_bricks", "minecraft:block/quartz_bricks");
        threeBuildBlockItems("cracked_nether_bricks", "minecraft:block/cracked_nether_bricks");
        threeBuildBlockItems("cracked_polished_blackstone_bricks", "minecraft:block/cracked_polished_blackstone_bricks");
        fenceInventory("crimson_nether_bricks","minecraft:block/red_nether_bricks");
        threeBuildBlockItems("glass", "minecraft:block/glass");
        woolTwoBlockItems();
        glassThreeBlockItems();
        otherItem(ModItems.PHOBOS_OUTPOST_DISC.get(), "classicraft:item/music_disc_blood_moon");
        otherItem(ModItems.DRAGON_FISH_DISC.get(), "classicraft:item/music_disc_dragon_fish");
        threeBuildBlockItems("tinted_glass", "minecraft:block/tinted_glass");
        threeBuildBlockItems("andesite_bricks", "classicraft:block/andesite_bricks");
        threeBuildBlockItems("cracked_andesite_bricks", "classicraft:block/cracked_andesite_bricks");
        threeBuildBlockItems("mossy_andesite_bricks", "classicraft:block/mossy_andesite_bricks");
        concreteThreeBlockItems();
        blockItem(ModBlocks.GLISTERING_MELON);
        terracottaThreeBlockItems();
        threeBuildBlockItems("granite_bricks", "classicraft:block/granite_bricks");
        threeBuildBlockItems("cracked_granite_bricks", "classicraft:block/cracked_granite_bricks");
        threeBuildBlockItems("mossy_granite_bricks", "classicraft:block/mossy_granite_bricks");
        threeBuildBlockItems("diorite_bricks", "classicraft:block/diorite_bricks");
        threeBuildBlockItems("cracked_diorite_bricks", "classicraft:block/cracked_diorite_bricks");
        threeBuildBlockItems("mossy_diorite_bricks", "classicraft:block/mossy_diorite_bricks");
        threeBuildBlockItems("mossy_cobbled_deepslate", "classicraft:block/mossy_cobbled_deepslate");
        threeBuildBlockItems("mossy_deepslate_bricks", "classicraft:block/mossy_deepslate_bricks");
        threeBuildBlockItems("mossy_deepslate_tiles", "classicraft:block/mossy_deepslate_tiles");
        buttonInventory(ModBlocks.POLISHED_ANDESITE_BUTTON, "minecraft:block/polished_andesite");
        buttonInventory(ModBlocks.POLISHED_DIORITE_BUTTON, "minecraft:block/polished_diorite");
        buttonInventory(ModBlocks.POLISHED_GRANITE_BUTTON, "minecraft:block/polished_granite");
        buttonInventory(ModBlocks.SMOOTH_STONE_BUTTON, "minecraft:block/smooth_stone");
        buttonInventory(ModBlocks.BLACKSTONE_BUTTON, "minecraft:block/blackstone_top");
        buttonInventory(ModBlocks.DEEPSLATE_BUTTON, "minecraft:block/deepslate_top");
        buttonInventory(ModBlocks.POLISHED_DEEPSLATE_BUTTON, "minecraft:block/polished_deepslate");
        pressurePlateInventory(ModBlocks.POLISHED_ANDESITE_PRESSURE_PLATE, "minecraft:block/polished_andesite");
        pressurePlateInventory(ModBlocks.POLISHED_DIORITE_PRESSURE_PLATE, "minecraft:block/polished_diorite");
        pressurePlateInventory(ModBlocks.POLISHED_GRANITE_PRESSURE_PLATE, "minecraft:block/polished_granite");
        pressurePlateInventory(ModBlocks.SMOOTH_STONE_PRESSURE_PLATE, "minecraft:block/smooth_stone");
        pressurePlateInventory(ModBlocks.BLACKSTONE_PRESSURE_PLATE, "minecraft:block/blackstone_top");
        pressurePlateInventory(ModBlocks.DEEPSLATE_PRESSURE_PLATE, "minecraft:block/deepslate_top");
        pressurePlateInventory(ModBlocks.POLISHED_DEEPSLATE_PRESSURE_PLATE, "minecraft:block/polished_deepslate");
    }

    private void pressurePlateInventory(RegistryObject<Block> block, String texture) {
        pressurePlate(block.getId().toString(), new ResourceLocation(texture));
    }

    private void buttonInventory(RegistryObject<Block> block, String texture) {
        buttonInventory(block.getId().toString(), new ResourceLocation(texture));
    }

    public ItemModelBuilder otherItem(Item item, String texture) {
        return getBuilder(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(texture));
    }

    void fenceInventory(String prefix, String texture) {
        fenceInventory(prefix + "_fence", new ResourceLocation(texture));
    }

    void woolTwoBlockItems() {
        for (String material : List.of("_wool")) {
            for (DyeColor dyeColor : DyeColor.values()) {
                twoBuildBlockItems(dyeColor + material, "minecraft:block/" + dyeColor + material);
            }
        }
    }

    void glassThreeBlockItems() {
        for (String material : List.of("_stained_glass")) {
            for (DyeColor dyeColor : DyeColor.values()) {
                twoBuildBlockItems(dyeColor.getName() + material, "minecraft:block/" + dyeColor.getName() + material);
            }
        }
    }

    void terracottaThreeBlockItems() {
        for (String material : List.of("_terracotta")) {
            for (DyeColor dyeColor : DyeColor.values()) {
                threeBuildBlockItems(dyeColor.getName() + material, "minecraft:block/" + dyeColor.getName() + material);
            }
        }
    }

    void concreteThreeBlockItems() {
        for (String material : List.of("_concrete")) {
            for (DyeColor dyeColor : DyeColor.values()) {
                threeBuildBlockItems(dyeColor.getName() + material, "minecraft:block/" + dyeColor.getName() + material);
            }
        }
    }

    void twoBuildBlockItems(String name, String texture) {
        stairsInventory(name, texture);
        slabInventory(name + "_slab", texture);
    }

    void threeBuildBlockItems(String name, String texture) {
        wallInventory(name, texture);
        stairsInventory(name, texture);
        slabInventory(name + "_slab", texture);
    }

    void wallInventory(String name, String texture) {
        singleTexture(name + "_wall", new ResourceLocation(BLOCK_FOLDER + "/wall_inventory"),
                "wall", new ResourceLocation(texture));
    }

    void slabInventory(String prefix, String texture) {
        slab(prefix,
                new ResourceLocation(texture),
                new ResourceLocation(texture),
                new ResourceLocation(texture));
    }

    void slabInventory(String prefix, String bottom, String top, String side) {
        slab(prefix,
                new ResourceLocation(side),
                new ResourceLocation(bottom),
                new ResourceLocation(top));
    }

    void stairsInventory(String prefix, String texture) {
        stairs(prefix + "_stairs",
                new ResourceLocation(texture),
                new ResourceLocation(texture),
                new ResourceLocation(texture));
    }

    void stairsInventory(String prefix, String side, String bottom, String top) {
        stairs(prefix + "_stairs",
                new ResourceLocation(side),
                new ResourceLocation(bottom),
                new ResourceLocation(top));
    }

    void blockItem(RegistryObject<Block> block) {
        withExistingParent(block.getId().getPath(),
                modLoc("block/" + block.getId().getPath()));
    }

    void bucketLibItem(RegistryObject<Item> item) {
        withExistingParent(item.getId().getPath(),
                new ResourceLocation("bucketlib:item/universal_bucket"))
                .texture("base", modLoc("item/" + item.getId().getPath()))
                .texture("lowerBase", modLoc("item/" + "lower_" + item.getId().getPath()))
                .texture("crackedBase", modLoc("item/" + item.getId().getPath()))
                .texture("crackedLowerBase", modLoc("item/" + "lower_" + item.getId().getPath()));
    }

    public ItemModelBuilder toolItem(Item item) {
        return toolItem(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)));
    }

    public ItemModelBuilder toolItem(ResourceLocation item) {
        return getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                .texture("layer0", new ResourceLocation(item.getNamespace(), "item/" + item.getPath()));
    }
}