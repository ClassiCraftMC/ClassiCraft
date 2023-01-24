package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.*;
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
        wallBlock(ModBlocks.QUARTZ_WALL, "quartz", "minecraft:block/quartz_block_side");
        wallBlock(ModBlocks.WARPED_NYLIUM_WALL, "warped_nylium", "minecraft:block/warped_nylium");
        wallBlock(ModBlocks.CRIMSON_NYLIUM_WALL, "crimson_nylium", "minecraft:block/crimson_nylium");
        wallBlock(ModBlocks.POLISHED_BASALT_WALL, "polished_basalt", "minecraft:block/polished_basalt_side");
        wallBlock(ModBlocks.QUARTZ_SANDSTONE_WALL, "quartz_sandstone","classicraft:block/quartz_sandstone");
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
        stairsBlock(ModBlocks.QUARTZ_SANDSTONE_STAIRS, "quartz_sandstone","classicraft:block/quartz_sandstone", "classicraft:block/quartz_sandstone_bottom", "classicraft:block/quartz_sandstone_top");
        stairsBlock(ModBlocks.POLISHED_BASALT_STAIRS, "polished_basalt","minecraft:block/polished_basalt_side", "minecraft:block/polished_basalt_top", "minecraft:block/polished_basalt_top");
        stairsBlock(ModBlocks.CRIMSON_NYLIUM_STAIRS, "crimson_nylium","minecraft:block/crimson_nylium_side", "minecraft:block/netherrack", "minecraft:block/crimson_nylium");
        stairsBlock(ModBlocks.WARPED_NYLIUM_STAIRS, "warped_nylium","minecraft:block/warped_nylium_side", "minecraft:block/netherrack", "minecraft:block/warped_nylium");
        slabBlock(ModBlocks.MOSSY_BRICKS_SLAB, "classicraft:block/mossy_bricks");
        slabBlock(ModBlocks.CRACKED_BRICKS_SLAB,"classicraft:block/cracked_bricks");
        slabBlock(ModBlocks.CRACKED_STONE_BRICKS_SLAB,"minecraft:block/cracked_stone_bricks");
        slabBlock(ModBlocks.DEEPSLATE_SLAB,"minecraft:block/deepslate", "minecraft:block/deepslate_top", "minecraft:block/deepslate_top");
        slabBlock(ModBlocks.CRIMSON_NYLIUM_SLAB,"minecraft:block/crimson_nylium", "minecraft:block/crimson_nylium_side", "minecraft:block/netherrack", "minecraft:block/crimson_nylium");
        slabBlock(ModBlocks.WARPED_NYLIUM_SLAB,"minecraft:block/warped_nylium", "minecraft:block/warped_nylium_side", "minecraft:block/netherrack", "minecraft:block/warped_nylium");
        slabBlock(ModBlocks.POLISHED_BASALT_SLAB,"minecraft:block/polished_basalt", "minecraft:block/polished_basalt_side", "minecraft:block/polished_basalt_top", "minecraft:block/polished_basalt_top");
        slabBlock(ModBlocks.SOUL_SANDSTONE_SLAB,"classicraft:block/soul_sandstone", "classicraft:block/soul_sandstone_bottom", "classicraft:block/soul_sandstone_top");
        slabBlock(ModBlocks.CUT_SOUL_SANDSTONE_SLAB,"classicraft:block/cut_soul_sandstone", "classicraft:block/soul_sandstone_bottom", "classicraft:block/soul_sandstone_top");
        slabBlock(ModBlocks.CUT_QUARTZ_SANDSTONE_SLAB,"classicraft:block/cut_quartz_sandstone", "classicraft:block/quartz_sandstone_bottom", "classicraft:block/quartz_sandstone_top");
        slabBlock(ModBlocks.QUARTZ_SANDSTONE_SLAB,"classicraft:block/quartz_sandstone","classicraft:block/quartz_sandstone_bottom", "classicraft:block/quartz_sandstone_top");
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

    protected void slabBlock(RegistryObject<Block> block, String doubleslab, String side, String bottom, String top) {
        slabBlock((SlabBlock) block.get(),
                new ResourceLocation(doubleslab),
                new ResourceLocation(side),
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

    private void pebbleModels(String prefix, String texture) {
        models().withExistingParent(prefix + "_pebble", "block")
                .texture("0", texture)
                .texture("particle", texture)
                .element()
                .from(3F, (float) -0.7, 4)
                .to(8F, (float) 0.3, 7)
                .shade(false)
                .rotation()
                .angle((float) -22.5)
                .axis(Direction.Axis.Y)
                .origin(3F, (float) -2.7, 5)
                .end().face(Direction.NORTH)
                .uvs(11, 13, 16, 16)
                .texture("#0")
                .cullface(Direction.UP)
                .end()
                .face(Direction.EAST)
                .uvs(13, 9, 16, 12)
                .texture("#0")
                .cullface(Direction.UP)
                .end()
                .face(Direction.SOUTH)
                .uvs(1, 12, 6, 15)
                .texture("#0")
                .cullface(Direction.UP)
                .end()
                .face(Direction.WEST)
                .uvs(11, 3, 14, 6)
                .texture("#0")
                .cullface(Direction.UP)
                .end()
                .face(Direction.UP)
                .uvs(7, 0, 12, 3)
                .texture("#0")
                .cullface(Direction.UP)
                .end()
                .from(5.5F, (float) -0.2, 6)
                .to(9.5F, (float) 0.8, 10)
                .shade(false)
                .rotation()
                .angle(22.5F)
                .axis(Direction.Axis.Y)
                .origin(7.5F, (float) -0.2, 8)
                .end().face(Direction.NORTH)
                .uvs(5, 13, 9, 14)
                .texture("#0")
                .cullface(Direction.UP)
                .end()
                .face(Direction.EAST)
                .uvs(0, 11, 4, 12)
                .texture("#0")
                .cullface(Direction.UP)
                .end()
                .face(Direction.SOUTH)
                .uvs(0, 11, 4, 12)
                .texture("#0")
                .cullface(Direction.UP)
                .end()
                .face(Direction.WEST)
                .uvs(7, 11, 11, 12)
                .texture("#0")
                .cullface(Direction.UP)
                .end()
                .face(Direction.UP)
                .uvs(7, 0, 11, 4)
                .texture("#0")
                .cullface(Direction.UP)
                .end();

        models().withExistingParent(prefix + "_pebble_1", "block")
                .texture("0", texture)
                .texture("particle", texture)
                .element()
                .from(5.5F, (float) -0.2, 6)
                .to(9.5F, (float) 0.8, 10)
                .rotation()
                .angle(22.5F)
                .axis(Direction.Axis.Y)
                .origin(7.5F, (float) -0.2, 8)
                .end().face(Direction.NORTH)
                .uvs(6, 0, 10, 1)
                .texture("#0")
                .end()
                .face(Direction.EAST)
                .uvs(6, 1, 10, 2)
                .texture("#0")
                .end()
                .face(Direction.SOUTH)
                .uvs(6, 3, 10, 4)
                .texture("#0")
                .end()
                .face(Direction.WEST)
                .uvs(6, 2, 10, 3)
                .texture("#0")
                .end()
                .face(Direction.UP)
                .uvs(6, 4, 10, 8)
                .end()
                .face(Direction.DOWN)
                .uvs(6, 4, 10, 8)
                .texture("#0")
                .end()
                .from(3F, (float) -0.7, 4)
                .to(8F, (float) 0.3, 7)
                .rotation()
                .angle(-22.5F)
                .axis(Direction.Axis.Y)
                .origin(3F, (float) -2.7, 5)
                .end().face(Direction.NORTH)
                .uvs(6, 2, 11, 3)
                .texture("#0")
                .end()
                .face(Direction.EAST)
                .uvs(8, 1, 11, 2)
                .texture("#0")
                .end()
                .face(Direction.SOUTH)
                .uvs(6, 4, 11, 5)
                .texture("#0")
                .end()
                .face(Direction.WEST)
                .uvs(8, 3, 11, 4)
                .texture("#0")
                .end()
                .face(Direction.UP)
                .uvs(6, 5, 11, 8)
                .texture("#0")
                .end()
                .face(Direction.DOWN)
                .uvs(6, 5, 11, 8)
                .texture("#0")
                .end();

         models().withExistingParent(prefix + "_pebble_2", "block")
                .texture("0", texture)
                .texture("particle", texture)
                .element()
                .from(4F, (float) -0.6, 5)
                .to(9F, (float) 0.4, 9)
                .rotation()
                .angle((float) 22.5)
                .axis(Direction.Axis.Y)
                .origin(4F, (float) -2.6, 7)
                .end().face(Direction.NORTH)
                .uvs(3, 3, 8, 4)
                .texture("#0")
                .end()
                .face(Direction.EAST)
                .uvs(4, 4, 8, 5)
                .texture("#0")
                .end()
                .face(Direction.SOUTH)
                .uvs(3, 5, 8, 6)
                .texture("#0")
                .end()
                .face(Direction.WEST)
                .uvs(4, 2, 8, 3)
                .texture("#0")
                .end()
                .face(Direction.UP)
                .uvs(3, 6, 8, 10)
                .end()
                .face(Direction.DOWN)
                .uvs(3, 6, 8, 10)
                .texture("#0")
                .end()
                .from(7.5F, (float) -0.2, 4)
                .to(10.5F, (float) 0.8, 7)
                .rotation()
                .angle(-22.5F)
                .axis(Direction.Axis.Y)
                .origin(8.5F, (float) -0.2, 5)
                .end().face(Direction.NORTH)
                .uvs(5, 5, 8, 6)
                .texture("#0")
                .end()
                .face(Direction.EAST)
                .uvs(5, 2, 8, 3)
                .texture("#0")
                .end()
                .face(Direction.SOUTH)
                .uvs(5, 3, 8, 4)
                .texture("#0")
                .end()
                .face(Direction.WEST)
                .uvs(5, 4, 8, 5)
                .texture("#0")
                .end()
                .face(Direction.UP)
                .uvs(5, 6, 8, 9)
                .texture("#0")
                .end()
                .face(Direction.DOWN)
                .uvs(5, 6, 8, 9)
                .texture("#0")
                .end();

        models().withExistingParent(prefix + "_pebble_3", "block")
                .texture("0", texture)
                .texture("particle", texture)
                .element()
                .from(11.5F, (float) -0.2, 2)
                .to(14.5F, (float) 0.8, 5)
                .rotation()
                .angle((float) 0)
                .axis(Direction.Axis.Y)
                .origin(12.5F, (float) 15.8, 4)
                .end().face(Direction.NORTH)
                .uvs(0, 0, 3, 1)
                .texture("#0")
                .end()
                .face(Direction.EAST)
                .uvs(0, 0, 3, 1)
                .texture("#0")
                .end()
                .face(Direction.SOUTH)
                .uvs(0, 0, 3, 1)
                .texture("#0")
                .end()
                .face(Direction.WEST)
                .uvs(0, 0, 3, 1)
                .texture("#0")
                .end()
                .face(Direction.UP)
                .uvs(0, 0, 3, 3)
                .end()
                .face(Direction.DOWN)
                .uvs(0, 0, 3, 3)
                .texture("#0")
                .end();

        models().withExistingParent(prefix + "_pebble_4", "block")
                .texture("0", texture)
                .texture("particle", texture)
                .element()
                .from(9.5F, (float) -0.2, 8)
                .to(13.5F, (float) 0.8, 12)
                .rotation()
                .angle((float) -45)
                .axis(Direction.Axis.Y)
                .origin(10.5F, (float) -0.2, 8)
                .end().face(Direction.NORTH)
                .uvs(11, 15, 15, 16)
                .texture("#0")
                .end()
                .face(Direction.EAST)
                .uvs(6, 11, 10, 12)
                .texture("#0")
                .end()
                .face(Direction.SOUTH)
                .uvs(12, 10, 16, 11)
                .texture("#0")
                .end()
                .face(Direction.WEST)
                .uvs(6, 12, 10, 13)
                .texture("#0")
                .end()
                .face(Direction.UP)
                .uvs(10, 3, 14, 7)
                .end()
                .face(Direction.DOWN)
                .uvs(7, 0, 11, 4)
                .texture("#0")
                .end()
                .from(8F, (float) -0.7, 7)
                .to(13F, (float) 0.3, 10)
                .rotation()
                .angle(-22.5F)
                .axis(Direction.Axis.Y)
                .origin(3, 0, 0)
                .end().face(Direction.NORTH)
                .uvs(11, 15, 16, 16)
                .texture("#0")
                .end()
                .face(Direction.EAST)
                .uvs(1, 13, 4, 14)
                .texture("#0")
                .end()
                .face(Direction.SOUTH)
                .uvs(6, 12, 11, 13)
                .texture("#0")
                .end()
                .face(Direction.WEST)
                .uvs(5, 5, 8, 6)
                .texture("#0")
                .end()
                .face(Direction.UP)
                .uvs(3, 6, 8, 9)
                .texture("#0")
                .end()
                .face(Direction.DOWN)
                .uvs(3, 6, 8, 9)
                .texture("#0")
                .end();

        models().withExistingParent(prefix + "_pebble_5", "block")
                .texture("0", texture)
                .texture("particle", texture)
                .element()
                .from(6F, (float) -0.6, 10)
                .to(11F, (float) 0.4, 14)
                .rotation()
                .angle(22.5F)
                .axis(Direction.Axis.Y)
                .origin(2, 0, 2)
                .end().face(Direction.NORTH)
                .uvs(5, 4, 10, 5)
                .texture("#0")
                .end()
                .face(Direction.EAST)
                .uvs(6, 2, 10, 3)
                .texture("#0")
                .end()
                .face(Direction.SOUTH)
                .uvs(5, 3, 10, 4)
                .texture("#0")
                .end()
                .face(Direction.WEST)
                .uvs(6, 1, 10, 2)
                .texture("#0")
                .end()
                .face(Direction.UP)
                .uvs(5, 5, 10, 9)
                .end()
                .face(Direction.DOWN)
                .uvs(5, 5, 10, 9)
                .texture("#0")
                .end()
                .from(10.5F, (float) -0.2, 9)
                .to(13.5F, (float) 0.8, 12)
                .rotation()
                .angle(-22.5F)
                .axis(Direction.Axis.Y)
                .origin(2, 0, 2)
                .end().face(Direction.NORTH)
                .uvs(5, 8, 8, 9)
                .texture("#0")
                .end()
                .face(Direction.EAST)
                .uvs(5, 5, 8, 6)
                .texture("#0")
                .end()
                .face(Direction.SOUTH)
                .uvs(5, 6, 8, 7)
                .texture("#0")
                .end()
                .face(Direction.WEST)
                .uvs(5, 7, 8, 8)
                .texture("#0")
                .end()
                .face(Direction.UP)
                .uvs(5, 9, 8, 12)
                .texture("#0")
                .end()
                .face(Direction.DOWN)
                .uvs(5, 9, 8, 12)
                .texture("#0")
                .end();

        models().withExistingParent(prefix + "_pebble_6", "block")
                .texture("0", texture)
                .texture("particle", texture)
                .element()
                .from(10.5F, (float) -0.2, 2)
                .to(13.5F, (float) 0.8, 5)
                .rotation()
                .angle((float) 45)
                .axis(Direction.Axis.Y)
                .origin(12.5F, (float) 15.8, 4)
                .end().face(Direction.NORTH)
                .uvs(12, 15, 15, 16)
                .texture("#0")
                .end()
                .face(Direction.EAST)
                .uvs(13, 10, 16, 11)
                .texture("#0")
                .end()
                .face(Direction.SOUTH)
                .uvs(6, 12, 9, 13)
                .texture("#0")
                .end()
                .face(Direction.WEST)
                .uvs(7, 1, 10, 2)
                .texture("#0")
                .end()
                .face(Direction.UP)
                .uvs(11, 3, 14, 6)
                .end()
                .face(Direction.DOWN)
                .uvs(8, 7, 11, 10)
                .texture("#0")
                .end();
    }

}