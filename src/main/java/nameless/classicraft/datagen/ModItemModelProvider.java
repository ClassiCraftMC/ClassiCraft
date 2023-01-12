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
        stairs("smooth_stone_stairs",
                new ResourceLocation("minecraft:block/stone"),
                new ResourceLocation("minecraft:block/stone"),
                new ResourceLocation("minecraft:block/stone"));
        wallInventory("smooth_stone_wall",
                new ResourceLocation("minecraft:block/smooth_stone"));
        wallInventory("stone_wall",
                new ResourceLocation("minecraft:block/stone"));
        blockItem(ModBlocks.CHISELED_QUARTZ_SANDSTONE);
        blockItem(ModBlocks.CHISELED_SOUL_SANDSTONE);
        blockItem(ModBlocks.CUT_QUARTZ_SANDSTONE);
        blockItem(ModBlocks.CUT_SOUL_SANDSTONE);
        blockItem(ModBlocks.QUARTZ_SANDSTONE);
    }

    void blockItem(RegistryObject<Block> block) {
        withExistingParent(block.getId().getPath(),
                modLoc("block/" + block.getId().getPath()));
    }
}