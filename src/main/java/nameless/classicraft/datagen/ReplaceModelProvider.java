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

public class ReplaceModelProvider extends ItemModelProvider {

    public ReplaceModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(),"minecraft", existingFileHelper);
    }

    @Override
    protected void registerModels() {
        getBuilder("potion")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("item/potion_overlay"))
                .texture("layer1", new ResourceLocation("item/potion"))
                .override()
                .predicate(new ResourceLocation("milk"), 1)
                .model(basicItem(new ResourceLocation(ClassiCraftMod.MOD_ID, "milk/milk_bottle")))
                .end();

        getBuilder("splash_potion")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("item/potion_overlay"))
                .texture("layer1", new ResourceLocation("item/splash_potion"))
                .override()
                .predicate(new ResourceLocation("milk"), 1)
                .model(basicItem(new ResourceLocation(ClassiCraftMod.MOD_ID, "milk/splash_milk_bottle")))
                .end();

        getBuilder("lingering_potion")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("item/potion_overlay"))
                .texture("layer1", new ResourceLocation("item/lingering_potion"))
                .override()
                .predicate(new ResourceLocation("milk"), 1)
                .model(basicItem(new ResourceLocation(ClassiCraftMod.MOD_ID, "milk/lingering_milk_bottle")))
                .end();
    }


    public ItemModelBuilder otherItem(Item item, String texture)
    {
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
                .texture("lowerBase", modLoc("item/" + item.getId().getPath()))
                .texture("crackedBase", modLoc("item/" + item.getId().getPath()))
                .texture("crackedLowerBase", modLoc("item/" + item.getId().getPath()));
    }
}