package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(DataGenerator generator, ExistingFileHelper exFileHelper) {
        super(generator.getPackOutput(), ClassiCraftMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.FLINT_BLOCK.get());
        simpleBlock(ModBlocks.QUARTZ_QUICKSAND.get());
        wallBlock((WallBlock) ModBlocks.STONE_WALL.get(), "stone", new ResourceLocation("minecraft:block/stone"));
        wallBlock((WallBlock) ModBlocks.SMOOTH_STONE_WALL.get(), "smooth_stone", new ResourceLocation("minecraft:block/smooth_stone"));
        stairsBlock((StairBlock) ModBlocks.SMOOTH_STONE_STAIRS.get(), "smooth_stone", new ResourceLocation("minecraft:block/smooth_stone"));
    }
}