package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.concurrent.CompletableFuture;


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
        vanillaSimpleBlockWithItem(ModBlocks.INFESTED_MOSSY_COBBLESTONE, "mossy_cobblestone");
        vanillaSimpleBlockWithItem(ModBlocks.INFESTED_CHISELED_DEEPSLATE, "chiseled_deepslate");
        vanillaSimpleBlockWithItem(ModBlocks.INFESTED_COBBLED_DEEPSLATE, "cobbled_deepslate");
        vanillaSimpleBlockWithItem(ModBlocks.INFESTED_DEEPSLATE_BRICKS, "deepslate_bricks");
        vanillaSimpleBlockWithItem(ModBlocks.INFESTED_DEEPSLATE_TILES, "deepslate_tiles");
        sandstoneBlockWithItem(ModBlocks.CHISELED_QUARTZ_SANDSTONE, "classicraft:block/quartz_sandstone_bottom", "classicraft:block/quartz_sandstone_top", "classicraft:block/chiseled_quartz_sandstone");
        sandstoneBlockWithItem(ModBlocks.CHISELED_SOUL_SANDSTONE, "classicraft:block/soul_sandstone_bottom", "classicraft:block/soul_sandstone_top", "classicraft:block/chiseled_soul_sandstone");
        sandstoneBlockWithItem(ModBlocks.CUT_QUARTZ_SANDSTONE, "classicraft:block/quartz_sandstone_bottom", "classicraft:block/quartz_sandstone_top", "classicraft:block/cut_quartz_sandstone");
        sandstoneBlockWithItem(ModBlocks.CUT_SOUL_SANDSTONE, "classicraft:block/soul_sandstone_bottom", "classicraft:block/soul_sandstone_top", "classicraft:block/cut_soul_sandstone");
        sandstoneBlockWithItem(ModBlocks.SOUL_SANDSTONE, "classicraft:block/soul_sandstone_bottom", "classicraft:block/soul_sandstone_top", "classicraft:block/soul_sandstone");
        sandstoneBlockWithItem(ModBlocks.QUARTZ_SANDSTONE, "classicraft:block/quartz_sandstone_bottom", "classicraft:block/quartz_sandstone_top", "classicraft:block/quartz_sandstone");
        wallBlock(ModBlocks.POLISHED_ANDESITE_WALL, "polished_andesite","minecraft:block/polished_andesite");
        wallBlock(ModBlocks.POLISHED_DIORITE_WALL, "polished_diorite","minecraft:block/polished_diorite");
        wallBlock(ModBlocks.POLISHED_GRANITE_WALL, "polished_granite","minecraft:block/polished_granite");
        wallBlock(ModBlocks.STONE_WALL, "stone","minecraft:block/stone");
        wallBlock(ModBlocks.SMOOTH_STONE_WALL, "smooth_stone", "minecraft:block/smooth_stone");
        wallBlock(ModBlocks.MOSSY_BRICKS_WALL, "mossy_bricks", "classicraft:block/mossy_bricks");
        wallBlock(ModBlocks.CRACKED_BRICKS_WALL, "cracked_bricks", "classicraft:block/cracked_bricks");
        wallBlock(ModBlocks.CRACKED_STONE_BRICKS_WALL, "cracked_stone_bricks", "minecraft:block/cracked_stone_bricks");
        wallBlock(ModBlocks.DEEPSLATE_WALL, "deepslate","minecraft:block/deepslate");
        stairsBlock(ModBlocks.CRACKED_BRICKS_STAIRS, "cracked_bricks","classicraft:block/cracked_bricks");
        stairsBlock(ModBlocks.MOSSY_BRICKS_STAIRS, "mossy_bricks","classicraft:block/mossy_bricks");
        stairsBlock(ModBlocks.SMOOTH_STONE_STAIRS, "smooth_stone", "minecraft:block/smooth_stone");
        stairsBlock(ModBlocks.CRACKED_STONE_BRICKS_STAIRS, "cracked_stone_bricks","minecraft:block/cracked_stone_bricks");
        stairsBlock(ModBlocks.DEEPSLATE_STAIRS, "deepslate","minecraft:block/deepslate", "minecraft:block/deepslate_top", "minecraft:block/deepslate_top");
        slabBlock(ModBlocks.MOSSY_BRICKS_SLAB, "classicraft:block/mossy_bricks");
        slabBlock(ModBlocks.CRACKED_BRICKS_SLAB,"classicraft:block/cracked_bricks");
        slabBlock(ModBlocks.CRACKED_STONE_BRICKS_SLAB,"minecraft:block/cracked_stone_bricks");
        slabBlock(ModBlocks.DEEPSLATE_SLAB,"minecraft:block/deepslate", "minecraft:block/deepslate_top", "minecraft:block/deepslate_top");
        threeBuildBlocks(ModBlocks.CRACKED_DEEPSLATE_BRICKS_WALL, ModBlocks.CRACKED_DEEPSLATE_BRICKS_STAIRS,
                ModBlocks.CRACKED_DEEPSLATE_BRICKS_SLAB, "cracked_deepslate_bricks", "minecraft:block/cracked_deepslate_bricks");
    }

    protected void vanillaSimpleBlockWithItem(RegistryObject<Block> block, String textureName) {
        simpleBlock(block.get(), models().cubeAll(block.getId().toString(),
                new ResourceLocation("minecraft:block/" + textureName)));
        simpleBlockItem(block.get(), models().cubeAll(block.getId().toString(),
                new ResourceLocation("minecraft:block/" + textureName)));
    }

    protected void vanillaSimpleBlockWithItem(RegistryObject<Block> block, String bottom, String top, String other) {
        simpleBlock(block.get(), models().cube(block.getId().toString(),
                new ResourceLocation("minecraft:block/" + bottom),
                new ResourceLocation("minecraft:block/" + top),
                new ResourceLocation("minecraft:block/" + other),
                new ResourceLocation("minecraft:block/" + other),
                new ResourceLocation("minecraft:block/" + other),
                new ResourceLocation("minecraft:block/" + other)));
        simpleBlockItem(block.get(), models().cube(block.getId().toString(),
                new ResourceLocation("minecraft:block/" + bottom),
                new ResourceLocation("minecraft:block/" + top),
                new ResourceLocation("minecraft:block/" + other),
                new ResourceLocation("minecraft:block/" + other),
                new ResourceLocation("minecraft:block/" + other),
                new ResourceLocation("minecraft:block/" + other)));
    }

    protected void sandstoneBlockWithItem(RegistryObject<Block> block, String bottom, String top, String other) {
        simpleBlock(block.get(), models().cube(block.getId().toString(),
                new ResourceLocation(bottom),
                new ResourceLocation(top),
                new ResourceLocation(other),
                new ResourceLocation(other),
                new ResourceLocation(other),
                new ResourceLocation(other)));
        simpleBlockItem(block.get(), models().cube(block.getId().toString(),
                new ResourceLocation(bottom),
                new ResourceLocation(top),
                new ResourceLocation(other),
                new ResourceLocation(other),
                new ResourceLocation(other),
                new ResourceLocation(other))
                .texture("particle", other)
                .renderType("solid"));
    }

    protected void simpleBlockWithItem(RegistryObject<Block> block) {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }

    protected void threeBuildBlocks(RegistryObject<Block> wall, RegistryObject<Block> stairs, RegistryObject<Block> slab, String prefix, String texture) {
        wallBlock(wall, prefix, texture);
        stairsBlock(stairs, prefix, texture);
        slabBlock(slab, texture);
    }
    protected void wallBlock(RegistryObject<Block> block, String name, String texture) {
        wallBlock((WallBlock) block.get(), name, new ResourceLocation(texture));
    }

    protected void stairsBlock(RegistryObject<Block> block, String name, String texture) {
        stairsBlock((StairBlock) block.get(), name, new ResourceLocation(texture));
    }

    protected void slabBlock(RegistryObject<Block> block, String texture) {
        slabBlock((SlabBlock) block.get(),
                new ResourceLocation(texture),
                new ResourceLocation(texture));
    }

    protected void slabBlock(RegistryObject<Block> block, String doubleslab, String bottom, String top) {
        slabBlock((SlabBlock) block.get(),
                new ResourceLocation(doubleslab),
                new ResourceLocation(doubleslab),
                new ResourceLocation(bottom),
                new ResourceLocation(top));
    }

    protected void stairsBlock(RegistryObject<Block> block, String name, String side, String bottom, String top) {
        stairsBlock((StairBlock) block.get(), name,
                new ResourceLocation(side),
                new ResourceLocation(bottom),
                new ResourceLocation(top));
    }
}