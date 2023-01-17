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
        simpleBlockWithItem(ModBlocks.FLINT_BLOCK);
        simpleBlockWithItem(ModBlocks.QUARTZ_QUICKSAND);
        simpleBlockWithItem(ModBlocks.QUARTZ_SAND);
        simpleBlockWithItem(ModBlocks.QUARTZ_SANDSTONE_BRICKS);
        simpleBlockWithItem(ModBlocks.RED_SANDSTONE_BRICKS);
        simpleBlockWithItem(ModBlocks.SANDSTONE_BRICKS);
        simpleBlockWithItem(ModBlocks.MOSSY_BRICKS);
        simpleBlockWithItem(ModBlocks.SOUL_QUICKSAND);
        simpleBlockWithItem(ModBlocks.SOUL_SANDSTONE_BRICKS);
        simpleBlockWithItem(ModBlocks.CRACKED_BRICKS);
        simpleBlockWithItem(ModBlocks.INFESTED_MOSSY_COBBLESTONE);
        wallBlock(ModBlocks.POLISHED_ANDESITE_WALL, "polished_andesite","minecraft:block/polished_andesite");
        wallBlock(ModBlocks.POLISHED_DIORITE_WALL, "polished_diorite","minecraft:block/polished_diorite");
        wallBlock(ModBlocks.POLISHED_GRANITE_WALL, "polished_granite","minecraft:block/polished_granite");
        wallBlock(ModBlocks.STONE_WALL, "stone","minecraft:block/stone");
        wallBlock(ModBlocks.SMOOTH_STONE_WALL, "smooth_stone", "minecraft:block/smooth_stone");
        wallBlock(ModBlocks.MOSSY_BRICKS_WALL, "mossy_bricks", "classicraft:block/mossy_bricks");
        wallBlock(ModBlocks.CRACKED_BRICKS_WALL, "cracked_bricks", "classicraft:block/cracked_bricks");
        wallBlock(ModBlocks.CRACKED_STONE_BRICKS_WALL, "cracked_stone_bricks", "minecraft:block/cracked_stone_bricks");
        stairsBlock(ModBlocks.CRACKED_BRICKS_STAIRS, "cracked_bricks","classicraft:block/cracked_bricks");
        stairsBlock(ModBlocks.MOSSY_BRICKS_STAIRS, "mossy_bricks","classicraft:block/mossy_bricks");
        stairsBlock(ModBlocks.SMOOTH_STONE_STAIRS, "smooth_stone", "minecraft:block/smooth_stone");
        stairsBlock(ModBlocks.CRACKED_STONE_BRICKS_STAIRS, "cracked_stone_bricks","minecraft:block/cracked_stone_bricks");
        slabBlock(ModBlocks.MOSSY_BRICKS_SLAB, "classicraft:block/mossy_bricks");
        slabBlock(ModBlocks.CRACKED_BRICKS_SLAB,"classicraft:block/cracked_bricks");
        slabBlock(ModBlocks.CRACKED_STONE_BRICKS_SLAB,"minecraft:block/cracked_stone_bricks");
    }

    void simpleBlockWithItem(RegistryObject<Block> block) {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }

    void wallBlock(RegistryObject<Block> block, String name, String texture) {
        wallBlock((WallBlock) block.get(), name, new ResourceLocation(texture));
    }

    void stairsBlock(RegistryObject<Block> block, String name, String texture) {
        stairsBlock((StairBlock) block.get(), name, new ResourceLocation(texture));
    }

    void slabBlock(RegistryObject<Block> block, String texture) {
        slabBlock((SlabBlock) block.get(),
                new ResourceLocation(texture),
                new ResourceLocation(texture));
    }
}