package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

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
        slabInventory("cut_quartz_sandstone_slab", "classicraft:block/quartz_sandstone_bottom", "classicraft:block/quartz_sandstone_top", "classicraft:block/cut_quartz_sandstone");
        slabInventory("soul_sandstone_slab", "classicraft:block/soul_sandstone_bottom", "classicraft:block/soul_sandstone_top", "classicraft:block/soul_sandstone");
        slabInventory("cracked_stone_bricks_slab", "minecraft:block/cracked_stone_bricks");
        slabInventory("deepslate_slab", "minecraft:block/deepslate_top", "minecraft:block/deepslate_top", "minecraft:block/deepslate");
        slabInventory("cracked_deepslate_bricks_slab", "minecraft:block/cracked_deepslate_bricks");
        slabInventory("cut_soul_sandstone_slab", "classicraft:block/soul_sandstone_bottom", "classicraft:block/soul_sandstone_top", "classicraft:block/cut_soul_sandstone");
        slabInventory("smooth_soul_sandstone_slab", "classicraft:block/soul_sandstone_bottom", "classicraft:block/soul_sandstone_top", "classicraft:block/smooth_soul_sandstone");
        basicItem(ModItems.MATERIAL.get());
        basicItem(ModItems.DEBUG_BURN_TIME_STICK.get());
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
        woolTwoBlockItems();
    }

    void woolTwoBlockItems() {
        for (String material : List.of("_wool")) {
            for (DyeColor dyeColor : DyeColor.values()) {
                twoBuildBlockItems(dyeColor + material, "minecraft:block/" + dyeColor + material);
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
}