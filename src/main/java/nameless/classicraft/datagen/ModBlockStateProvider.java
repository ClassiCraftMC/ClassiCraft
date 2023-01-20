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
import net.minecraftforge.registries.ForgeRegistries;
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
        simpleBlockWithItem(ModBlocks.SMOOTH_QUARTZ_SANDSTONE);
        vanillaSimpleBlockWithItem(ModBlocks.INFESTED_MOSSY_COBBLESTONE, "mossy_cobblestone");
        vanillaSimpleBlockWithItem(ModBlocks.INFESTED_CHISELED_DEEPSLATE, "chiseled_deepslate");
        vanillaSimpleBlockWithItem(ModBlocks.INFESTED_COBBLED_DEEPSLATE, "cobbled_deepslate");
        vanillaSimpleBlockWithItem(ModBlocks.INFESTED_DEEPSLATE_BRICKS, "deepslate_bricks");
        vanillaSimpleBlockWithItem(ModBlocks.INFESTED_DEEPSLATE_TILES, "deepslate_tiles");
        vanillaSimpleBlockWithItem(ModBlocks.INFESTED_CRACKED_DEEPSLATE_BRICKS, "cracked_deepslate_bricks");
        vanillaSimpleBlockWithItem(ModBlocks.INFESTED_CRACKED_DEEPSLATE_TILES, "cracked_deepslate_tiles");
        simpleBlockWithItem(ModBlocks.SMOOTH_SOUL_SANDSTONE);
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
        wallBlock(ModBlocks.DARK_PRISMARINE_WALL, "dark_prismarine","minecraft:block/dark_prismarine");
        wallBlock(ModBlocks.CUT_SOUL_SANDSTONE_WALL, "cut_soul_sandstone","classicraft:block/cut_soul_sandstone");
        wallBlock(ModBlocks.SOUL_SANDSTONE_WALL, "soul_sandstone", "classicraft:block/soul_sandstone");
        wallBlock(ModBlocks.CUT_SANDSTONE_WALL, "cut_sandstone", "minecraft:block/cut_sandstone");
        wallBlock(ModBlocks.SMOOTH_STONE_WALL, "smooth_stone", "minecraft:block/smooth_stone");
        wallBlock(ModBlocks.MOSSY_BRICKS_WALL, "mossy_bricks", "classicraft:block/mossy_bricks");
        wallBlock(ModBlocks.CRACKED_BRICKS_WALL, "cracked_bricks", "classicraft:block/cracked_bricks");
        wallBlock(ModBlocks.CRACKED_STONE_BRICKS_WALL, "cracked_stone_bricks", "minecraft:block/cracked_stone_bricks");
        wallBlock(ModBlocks.DEEPSLATE_WALL, "deepslate","minecraft:block/deepslate");
        wallBlock(ModBlocks.CUT_QUARTZ_SANDSTONE_WALL, "cut_quartz_sandstone", "classicraft:block/cut_quartz_sandstone");
        wallBlock(ModBlocks.PRISMARINE_BRICKS_WALL, "prismarine_bricks","minecraft:block/prismarine_bricks");
        wallBlock(ModBlocks.CUT_RED_SANDSTONE_WALL, "cut_red_sandstone","minecraft:block/cut_red_sandstone");
        wallBlock(ModBlocks.SMOOTH_SANDSTONE_WALL, "smooth_sandstone", "minecraft:block/sandstone_top");
        wallBlock(ModBlocks.SMOOTH_RED_SANDSTONE_WALL, "smooth_red_sandstone", "minecraft:block/red_sandstone_top");
        stairsBlock(ModBlocks.CRACKED_BRICKS_STAIRS, "cracked_bricks","classicraft:block/cracked_bricks");
        stairsBlock(ModBlocks.MOSSY_BRICKS_STAIRS, "mossy_bricks","classicraft:block/mossy_bricks");
        stairsBlock(ModBlocks.SMOOTH_STONE_STAIRS, "smooth_stone", "minecraft:block/smooth_stone");
        stairsBlock(ModBlocks.CRACKED_STONE_BRICKS_STAIRS, "cracked_stone_bricks","minecraft:block/cracked_stone_bricks");
        stairsBlock(ModBlocks.DEEPSLATE_STAIRS, "deepslate","minecraft:block/deepslate", "minecraft:block/deepslate_top", "minecraft:block/deepslate_top");
        stairsBlock(ModBlocks.CUT_SANDSTONE_STAIRS, "cut_sandstone","minecraft:block/cut_sandstone", "minecraft:block/sandstone_bottom", "minecraft:block/sandstone_top");
        stairsBlock(ModBlocks.CUT_RED_SANDSTONE_STAIRS, "cut_red_sandstone","minecraft:block/cut_red_sandstone", "minecraft:block/red_sandstone_bottom", "minecraft:block/red_sandstone_top");
        stairsBlock(ModBlocks.SOUL_SANDSTONE_STAIRS, "soul_sandstone","classicraft:block/soul_sandstone", "classicraft:block/soul_sandstone_bottom", "classicraft:block/soul_sandstone_top");
        stairsBlock(ModBlocks.CUT_SOUL_SANDSTONE_STAIRS, "cut_soul_sandstone","classicraft:block/cut_soul_sandstone", "classicraft:block/soul_sandstone_bottom", "classicraft:block/soul_sandstone_top");
        stairsBlock(ModBlocks.CUT_QUARTZ_SANDSTONE_STAIRS, "cut_quartz_sandstone","classicraft:block/cut_quartz_sandstone", "classicraft:block/quartz_sandstone_bottom", "classicraft:block/quartz_sandstone_top");
        slabBlock(ModBlocks.MOSSY_BRICKS_SLAB, "classicraft:block/mossy_bricks");
        slabBlock(ModBlocks.CRACKED_BRICKS_SLAB,"classicraft:block/cracked_bricks");
        slabBlock(ModBlocks.CRACKED_STONE_BRICKS_SLAB,"minecraft:block/cracked_stone_bricks");
        slabBlock(ModBlocks.DEEPSLATE_SLAB,"minecraft:block/deepslate", "minecraft:block/deepslate_top", "minecraft:block/deepslate_top");
        slabBlock(ModBlocks.SOUL_SANDSTONE_SLAB,"classicraft:block/soul_sandstone", "classicraft:block/soul_sandstone_bottom", "classicraft:block/soul_sandstone_top");
        slabBlock(ModBlocks.CUT_SOUL_SANDSTONE_SLAB,"classicraft:block/cut_soul_sandstone", "classicraft:block/soul_sandstone_bottom", "classicraft:block/soul_sandstone_top");
        slabBlock(ModBlocks.CUT_QUARTZ_SANDSTONE_SLAB,"classicraft:block/cut_quartz_sandstone", "classicraft:block/quartz_sandstone_bottom", "classicraft:block/quartz_sandstone_top");
        threeBuildBlocks(ModBlocks.CRACKED_DEEPSLATE_BRICKS_WALL, ModBlocks.CRACKED_DEEPSLATE_BRICKS_STAIRS,
                ModBlocks.CRACKED_DEEPSLATE_BRICKS_SLAB, "cracked_deepslate_bricks", "minecraft:block/cracked_deepslate_bricks");
        threeBuildBlocks(ModBlocks.SMOOTH_SOUL_SANDSTONE_WALL, ModBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS, ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB,
                "smooth_soul_sandstone", "classicraft:block/smooth_soul_sandstone");
        threeBuildBlocks(ModBlocks.CRACKED_DEEPSLATE_TILES_WALL, ModBlocks.CRACKED_DEEPSLATE_TILES_STAIRS, ModBlocks.CRACKED_DEEPSLATE_TILES_SLAB,
                "cracked_deepslate_tiles", "minecraft:block/cracked_deepslate_tiles");
        threeBuildBlocks(ModBlocks.SANDSTONE_BRICKS_WALL, ModBlocks.SANDSTONE_BRICKS_STAIRS, ModBlocks.SANDSTONE_BRICKS_SLAB,
                "sandstone_bricks", "classicraft:block/sandstone_bricks");
        threeBuildBlocks(ModBlocks.RED_SANDSTONE_BRICKS_WALL, ModBlocks.RED_SANDSTONE_BRICKS_STAIRS, ModBlocks.RED_SANDSTONE_BRICKS_SLAB,
                "red_sandstone_bricks", "classicraft:block/red_sandstone_bricks");
        threeBuildBlocks(ModBlocks.SOUL_SANDSTONE_BRICKS_WALL, ModBlocks.SOUL_SANDSTONE_BRICKS_STAIRS, ModBlocks.SOUL_SANDSTONE_BRICKS_SLAB,
                "soul_sandstone_bricks", "classicraft:block/soul_sandstone_bricks");
        threeBuildBlocks(ModBlocks.FLINT_BLOCK_WALL, ModBlocks.FLINT_BLOCK_STAIRS, ModBlocks.FLINT_BLOCK_SLAB,
                "flint_block", "classicraft:block/flint_block");
        threeBuildBlocks(ModBlocks.QUARTZ_SANDSTONE_BRICKS_WALL, ModBlocks.QUARTZ_SANDSTONE_BRICKS_STAIRS, ModBlocks.QUARTZ_SANDSTONE_BRICKS_SLAB,
                "quartz_sandstone_bricks", "classicraft:block/quartz_sandstone_bricks");
        threeBuildBlocks(ModBlocks.END_STONE_WALL, ModBlocks.END_STONE_STAIRS, ModBlocks.END_STONE_SLAB,
                "end_stone", "minecraft:block/end_stone");
        threeBuildBlocks(ModBlocks.NETHERRACK_WALL, ModBlocks.NETHERRACK_STAIRS, ModBlocks.NETHERRACK_SLAB,
                "netherrack", "minecraft:block/netherrack");
        threeBuildBlocks(ModBlocks.SMOOTH_QUARTZ_SANDSTONE_WALL, ModBlocks.SMOOTH_QUARTZ_SANDSTONE_STAIRS, ModBlocks.SMOOTH_QUARTZ_SANDSTONE_SLAB,
                "smooth_quartz_sandstone", "classicraft:block/smooth_quartz_sandstone");
        threeBuildBlocks(ModBlocks.SMOOTH_BASALT_WALL, ModBlocks.SMOOTH_BASALT_STAIRS, ModBlocks.SMOOTH_BASALT_SLAB,
                "smooth_basalt", "minecraft:block/smooth_basalt");
        threeBuildBlocks(ModBlocks.QUARTZ_BRICKS_WALL, ModBlocks.QUARTZ_BRICKS_STAIRS, ModBlocks.QUARTZ_BRICKS_SLAB,
                "quartz_bricks", "minecraft:block/quartz_bricks");
        twoBuildBlocks(ModBlocks.WHITE_WOOL_STAIRS, ModBlocks.WHITE_WOOL_SLAB,
                "white_wool", "minecraft:block/white_wool");
        twoBuildBlocks(ModBlocks.ORANGE_WOOL_STAIRS, ModBlocks.ORANGE_WOOL_SLAB,
                "orange_wool", "minecraft:block/orange_wool");
        twoBuildBlocks(ModBlocks.MAGENTA_WOOL_STAIRS, ModBlocks.MAGENTA_WOOL_SLAB,
                "magenta_wool", "minecraft:block/magenta_wool");
        twoBuildBlocks(ModBlocks.LIGHT_BLUE_WOOL_STAIRS, ModBlocks.LIGHT_BLUE_WOOL_SLAB,
                "light_blue_wool", "minecraft:block/light_blue_wool");
        twoBuildBlocks(ModBlocks.YELLOW_WOOL_STAIRS, ModBlocks.YELLOW_WOOL_SLAB,
                "yellow_wool", "minecraft:block/yellow_wool");
        twoBuildBlocks(ModBlocks.LIME_WOOL_STAIRS, ModBlocks.LIME_WOOL_SLAB,
                "lime_wool", "minecraft:block/lime_wool");
        twoBuildBlocks(ModBlocks.PINK_WOOL_STAIRS, ModBlocks.PINK_WOOL_SLAB,
                "pink_wool", "minecraft:block/pink_wool");
        twoBuildBlocks(ModBlocks.GRAY_WOOL_STAIRS, ModBlocks.GRAY_WOOL_SLAB,
                "gray_wool", "minecraft:block/gray_wool");
        twoBuildBlocks(ModBlocks.LIGHT_GRAY_WOOL_STAIRS, ModBlocks.LIGHT_GRAY_WOOL_SLAB,
                "light_gray_wool", "minecraft:block/light_gray_wool");
        twoBuildBlocks(ModBlocks.CYAN_WOOL_STAIRS, ModBlocks.CYAN_WOOL_SLAB,
                "cyan_wool", "minecraft:block/cyan_wool");
        twoBuildBlocks(ModBlocks.PURPLE_WOOL_STAIRS, ModBlocks.PURPLE_WOOL_SLAB,
                "purple_wool", "minecraft:block/purple_wool");
        twoBuildBlocks(ModBlocks.BLUE_WOOL_STAIRS, ModBlocks.BLUE_WOOL_SLAB,
                "blue_wool", "minecraft:block/blue_wool");
        twoBuildBlocks(ModBlocks.BROWN_WOOL_STAIRS, ModBlocks.BROWN_WOOL_SLAB,
                "brown_wool", "minecraft:block/brown_wool");
        twoBuildBlocks(ModBlocks.GREEN_WOOL_STAIRS, ModBlocks.GREEN_WOOL_SLAB,
                "green_wool", "minecraft:block/green_wool");
        twoBuildBlocks(ModBlocks.RED_WOOL_STAIRS, ModBlocks.RED_WOOL_SLAB,
                "red_wool", "minecraft:block/red_wool");
        twoBuildBlocks(ModBlocks.BLACK_WOOL_STAIRS, ModBlocks.BLACK_WOOL_SLAB,
                "black_wool", "minecraft:block/black_wool");
    }

    protected void twoBuildBlocks(RegistryObject<Block> stairs, RegistryObject<Block> slab, String prefix, String texture) {
        stairsBlock(stairs, prefix, texture);
        slabBlock(slab, texture);
    }


    protected void modSimpleBlockWithItem(RegistryObject<Block> block, String textureName) {
        simpleBlock(block.get(), models().cubeAll(block.getId().toString(),
                new ResourceLocation("classicraft:block/" + textureName)));
        simpleBlockItem(block.get(), models().cubeAll(block.getId().toString(),
                new ResourceLocation("classicraft:block/" + textureName)));
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

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

}