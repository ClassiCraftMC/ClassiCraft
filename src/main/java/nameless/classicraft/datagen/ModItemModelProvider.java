package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

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
        slabInventory("mossy_bricks_slab", "classicraft:block/mossy_bricks");
        slabInventory("cracked_bricks_slab", "classicraft:block/cracked_bricks");
        slabInventory("cracked_stone_bricks_slab", "minecraft:block/cracked_stone_bricks");
        blockItem(ModBlocks.CHISELED_QUARTZ_SANDSTONE);
        blockItem(ModBlocks.CHISELED_SOUL_SANDSTONE);
        blockItem(ModBlocks.CUT_QUARTZ_SANDSTONE);
        blockItem(ModBlocks.CUT_SOUL_SANDSTONE);
        blockItem(ModBlocks.QUARTZ_SANDSTONE);
        blockItem(ModBlocks.SOUL_SANDSTONE);
    }

    void slabInventory(String prefix, String texture) {
        slab(prefix,
                new ResourceLocation(texture),
                new ResourceLocation(texture),
                new ResourceLocation(texture));
    }

    void stairsInventory(String prefix, String texture) {
        stairs(prefix + "_stairs",
                new ResourceLocation(texture),
                new ResourceLocation(texture),
                new ResourceLocation(texture));
    }

    void blockItem(RegistryObject<Block> block) {
        withExistingParent(block.getId().getPath(),
                modLoc("block/" + block.getId().getPath()));
    }
}