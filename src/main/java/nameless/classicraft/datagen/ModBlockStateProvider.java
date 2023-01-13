package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(DataGenerator generator, ExistingFileHelper exFileHelper) {
        super(generator.getPackOutput(), ClassiCraftMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.FLINT_BLOCK.get());
        simpleBlock(ModBlocks.QUARTZ_QUICKSAND.get());
        simpleBlock(ModBlocks.QUARTZ_SAND.get());
        simpleBlock(ModBlocks.QUARTZ_SANDSTONE_BRICKS.get());
        simpleBlock(ModBlocks.RED_SANDSTONE_BRICKS.get());
        simpleBlock(ModBlocks.SANDSTONE_BRICKS.get());
        simpleBlock(ModBlocks.MOSSY_BRICKS.get());
        wallBlock(ModBlocks.STONE_WALL, "stone","minecraft:block/stone");
        wallBlock(ModBlocks.SMOOTH_STONE_WALL, "smooth_stone", "minecraft:block/smooth_stone");
        wallBlock(ModBlocks.MOSSY_BRICKS_WALL, "mossy_bricks", "classicraft:block/mossy_bricks");
        stairsBlock(ModBlocks.MOSSY_BRICKS_STAIRS, "mossy_bricks","classicraft:block/mossy_bricks");
        stairsBlock(ModBlocks.SMOOTH_STONE_STAIRS, "smooth_stone", "minecraft:block/smooth_stone");
        slabBlock(ModBlocks.MOSSY_BRICKS_SLAB, "classicraft:block/mossy_bricks_slab", "classicraft:block/mossy_bricks");
    }

    void wallBlock(RegistryObject<Block> block, String name, String texture) {
        wallBlock((WallBlock) block.get(), name, new ResourceLocation(texture));
    }

    void stairsBlock(RegistryObject<Block> block, String name, String texture) {
        stairsBlock((StairBlock) block.get(), name, new ResourceLocation(texture));
    }

    void slabBlock(RegistryObject<Block> block, String half, String all) {
        slabBlock((SlabBlock) block.get(),
                new ResourceLocation(half),
                new ResourceLocation(all));
    }
}