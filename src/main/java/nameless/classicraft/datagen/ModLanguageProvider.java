package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModEntities;
import nameless.classicraft.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(DataGenerator gen) {
        super(gen.getPackOutput(), ClassiCraftMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(ModItems.TORCH_LIT.get(), "Lit Torch");
        add(ModItems.TORCH_UNLIT.get(), "Unlit Torch");
        add(ModItems.SOUL_TORCH_UNLIT.get(), "Unlit Soul Torch");
        add(ModBlocks.REAL_TORCH.get(), "Real Torch");
        add(ModItems.DEBUG_TIME_STICK.get(), "Debug Burn Time Stick");
        add(ModItems.DEPTH_METER.get(), "Depth Meter");
        add(ModBlocks.QUICKSAND.get(), "Quicksand");
        add(ModBlocks.RED_QUICKSAND.get(), "Red Quicksand");
        add(ModBlocks.CHARCOAL_BLOCK.get(), "Charcoal Block");
        add(ModBlocks.CACTUS_BALL.get(), "Cactus ball");
        add(ModBlocks.ROSE.get(), "Rose");
        add(ModItems.TROUT_SPAWN_EGG.get(), "Trout Spawn Egg");
        add(ModItems.TROUT.get(), "Trout");
        add(ModItems.COOKED_TROUT.get(), "Cooked Trout");
        add(ModItems.TALLOW.get(), "Tallow");
        add(ModBlocks.TALLOW_BLOCK.get(), "Tallow Block");
        add(ModItems.TROUT_BUCKET.get(), "Trout Bucket");
        add("info.classicraft.burntime", "BurnTime");
        add("info.classicraft.minutes", "minutes");
        add("info.classicraft.depth.sky_land", "'s sky land");
        add("info.classicraft.depth.sky", "'s sky");
        add("info.classicraft.depth.cloud", "'s cloud");
        add("info.classicraft.depth.base_cloud", "'s base cloud");
        add("info.classicraft.depth.sea_level", "'s sea level");
        add("info.classicraft.depth.surface", "'s surface");
        add("info.classicraft.depth.underground", "'s sea level");
        add("info.classicraft.depth.deep_underground", "'s deep underground");
        add("info.classicraft.depth.bedrock", "'s bedrock");
        add("info.classicraft.depth.void", "'s void");
        add("info.classicraft.height", "At now is in the height ");
        add("level.classicraft.fresh", "Fresh ");
        add("level.classicraft.stale", "Stale ");
        add("level.classicraft.spoiled", "Spoiled ");
        add("level.classicraft.rotten", "Rotten ");
        add("info.classicraft.rot", "It will rotten after %s day");
        add("info.classicraft.rotting_speed", "Rotting Speed: %s");
        add("info.classicraft.shift_press", "Press Shift key to show more info");
        add("info.classicraft.food.nutrition", "Nutrition");
        add("info.classicraft.food.saturation", "Saturation");
        add(ModItems.OCEAN_SHARK_SPAWN_EGG.get(), "Ocean Shark Spawn Egg");
        add(ModItems.LIVING_DEAD_SPAWN_EGG.get(), "Living Dead Spawn Egg");
        add(ModItems.COOKED_EGG.get(), "Cooked Egg");
        add(ModItems.NETHER_MUSHROOM_STEW.get(), "Nether Mushroom Stew");
        add(ModItems.ROTTEN_FOOD.get(), "Rotten Food");
        add(ModBlocks.MOSSY_BRICKS.get(), "Mossy Bricks");
        add(ModBlocks.MOSSY_BRICKS_STAIRS.get(), "Mossy Bricks Stairs");
        add(ModBlocks.MOSSY_BRICKS_WALL.get(), "Mossy Bricks Wall");
        add(ModBlocks.MOSSY_BRICKS_SLAB.get(), "Mossy Bricks Slab");
        add(ModBlocks.CRACKED_BRICKS.get(), "Cracked Bricks");
        add(ModBlocks.CRACKED_BRICKS_STAIRS.get(), "Cracked Bricks Stairs");
        add(ModBlocks.CRACKED_BRICKS_WALL.get(), "Cracked Bricks Wall");
        add(ModBlocks.CRACKED_BRICKS_SLAB.get(), "Cracked Bricks Slab");
        add(ModBlocks.SULFUR_ORE.get(), "Sulfur Ore");
        add(ModItems.SULFUR.get(), "Sulfur");
        add(ModBlocks.SMOOTH_STONE_WALL.get(), "Smooth Stone Wall");
        add(ModBlocks.SMOOTH_STONE_STAIRS.get(), "Smooth Stone Stairs");
        add(ModBlocks.STONE_WALL.get(), "Stone Wall");
        add(ModBlocks.FLINT_BLOCK.get(), "Flint Block");
        add(ModBlocks.CHISELED_SOUL_SANDSTONE.get(), "Chiseled Soul SandStone");
        add(ModBlocks.CHISELED_QUARTZ_SANDSTONE.get(), "Chiseled Quartz SandStone");
        add(ModBlocks.CUT_SOUL_SANDSTONE.get(), "Cut Soul SandStone");
        add(ModBlocks.CUT_QUARTZ_SANDSTONE.get(), "Cut Quartz SandStone");
        add(ModBlocks.QUARTZ_QUICKSAND.get(), "Quartz QuickSand");
        add(ModBlocks.QUARTZ_SAND.get(), "Quartz Sand");
        add(ModBlocks.QUARTZ_SANDSTONE.get(), "Quartz SandStone");
        add(ModBlocks.QUARTZ_SANDSTONE_BRICKS.get(), "Quartz SandStone Bricks");
        add(ModBlocks.RED_SANDSTONE_BRICKS.get(), "Red SandStone Bricks");
        add(ModBlocks.SANDSTONE_BRICKS.get(), "SandStone Bricks");
        add(ModBlocks.SOUL_SANDSTONE.get(), "Soul SandStone");
        add(ModBlocks.SOUL_QUICKSAND.get(), "Soul QuickSand");
        add(ModBlocks.SOUL_SANDSTONE_BRICKS.get(), "Soul SandStone Bricks");
        add(ModBlocks.CRACKED_STONE_BRICKS_SLAB.get(), "Cracked Stone Bricks Slab");
        add(ModBlocks.CRACKED_STONE_BRICKS_STAIRS.get(), "Cracked Stone Bricks Stairs");
        add(ModBlocks.CRACKED_STONE_BRICKS_WALL.get(), "Cracked Stone Bricks Wall");
        add(ModBlocks.POLISHED_GRANITE_WALL.get(), "Polished Granite Wall");
        add(ModBlocks.POLISHED_ANDESITE_WALL.get(), "Polished Andesite Wall");
        add(ModBlocks.POLISHED_DIORITE_WALL.get(), "Polished Diorite Wall");
        add(ModBlocks.INFESTED_MOSSY_COBBLESTONE.get(), "Infested Mossy CobbleStone");
        add(ModBlocks.DEEPSLATE_WALL.get(), "Deepslate Wall");
        add(ModBlocks.DEEPSLATE_STAIRS.get(), "Deepslate Stairs");
        add(ModBlocks.DEEPSLATE_SLAB.get(), "Deepslate Slab");
        add(ModBlocks.CRACKED_DEEPSLATE_BRICKS_WALL.get(), "Cracked Deepslate Bricks Wall");
        add(ModBlocks.CRACKED_DEEPSLATE_BRICKS_STAIRS.get(), "Cracked Deepslate Bricks Stairs");
        add(ModBlocks.CRACKED_DEEPSLATE_BRICKS_SLAB.get(), "Cracked Deepslate Bricks Slab");
        add(ModBlocks.INFESTED_CHISELED_DEEPSLATE.get(), "Infested Chiseled DeepSlate");
        add(ModBlocks.INFESTED_COBBLED_DEEPSLATE.get(), "Infested Cobbled DeepSlate");
        add(ModBlocks.INFESTED_DEEPSLATE_BRICKS.get(), "Infested DeepSlate Bricks");
        add(ModBlocks.INFESTED_DEEPSLATE_TILES.get(), "Infested DeepSlate Tiles");
        add("itemGroup.classicraft.common", "ClassiCraft|Common");
        add("itemGroup.classicraft.building_blocks", "ClassiCraft|Building Blocks");
        add("itemGroup.classicraft.material", "ClassiCraft|Material");
        add("itemGroup.classicraft.natural_blocks", "ClassiCraft|Natural Blocks");

        pebbleBlock();
        meta();
    }

    private void pebbleBlock() {
        add(ModBlocks.FLINT.get(), "Flint Pebble");
        add(ModBlocks.ANDESITE_PEBBLE.get(), "Andesite Pebble");
        add(ModBlocks.COBBLESTONE_PEBBLE.get(), "CobbleStone Pebble");
        add(ModBlocks.DIORITE_PEBBLE.get(), "Diorite Pebble");
        add(ModBlocks.GRANITE_PEBBLE.get(), "Granite Pebble");
        add(ModBlocks.RED_SANDSTONE_PEBBLE.get(), "Red SandStone Pebble");
        add(ModBlocks.SANDSTONE_PEBBLE.get(), "SandStone Pebble");
        add(ModBlocks.DEEPSLATE_PEBBLE.get(), "Deepslate Pebble");
        add(ModBlocks.QUARTZ_SANDSTONE_PEBBLE.get(), "Quartz SandStone Pebble");
        add(ModBlocks.SOUL_SANDSTONE_PEBBLE.get(), "Soul SandStone Pebble");
        add(ModBlocks.NETHERRACK_PEBBLE.get(), "Nether Rack Pebble");
        add(ModBlocks.END_STONE_PEBBLE.get(), "End Stone Pebble");
        add(ModBlocks.BASALT_PEBBLE.get(), "Basalt Pebble");
        add(ModBlocks.BLACKSTONE_PEBBLE.get(), "BlackStone Pebble");
        add(ModBlocks.PRISMARINE.get(), "Prismarine Pebble");
        add(ModBlocks.QUARTZ_PEBBLE.get(), "Quartz Pebble");
        entity();
    }

    private void entity() {
        add(ModEntities.OCEAN_SHARK_ENTITY.get(), "海洋鲨鱼");
        add(ModEntities.TROUT_ENTITY.get(), "鳟鱼");
        add(ModEntities.LIVING_DEAD.get(), "活死人");
    }

    private void meta() {
        add("item.classicraft.adze.cobblestone_adze", "Cobblestone Adze");
        add("item.classicraft.adze.deepslate_adze", "Deepslate Adze");
        add("item.classicraft.adze.flint_adze", "Flint Adze");
        add("item.classicraft.adze.blackstone_adze", "Blackstone Adze");
        add("item.classicraft.adze.quartz_adze", "Quartz Adze");
        add("item.classicraft.awl.cobblestone_awl", "Cobblestone Awl");
        add("item.classicraft.awl.deepslate_awl", "Deepslate Awl");
        add("item.classicraft.awl.flint_awl", "Flint Awl");
        add("item.classicraft.awl.blackstone_awl", "Blackstone Awl");
        add("item.classicraft.awl.quartz_awl", "Quartz Awl");
        add("item.classicraft.chopper.cobblestone_chopper", "Cobblestone Chopper");
        add("item.classicraft.chopper.deepslate_chopper", "Deepslate Chopper");
        add("item.classicraft.chopper.flint_chopper", "Flint Chopper");
        add("item.classicraft.chopper.blackstone_chopper", "Blackstone Chopper");
        add("item.classicraft.chopper.quartz_chopper", "Quartz Chopper");
        add("item.classicraft.pebble.andesite_pebble", "Andesite Pebble");
        add("item.classicraft.pebble.basalt_pebble", "Basalt Pebble");
        add("item.classicraft.pebble.blackstone_pebble", "Blackstone Pebble");
        add("item.classicraft.pebble.cobblestone_pebble", "Cobblestone Pebble");
        add("item.classicraft.pebble.deepslate_pebble", "Deepslate Pebble");
        add("item.classicraft.pebble.diorite_pebble", "Diorite Pebble");
        add("item.classicraft.pebble.granite_pebble", "Granite Pebble");
        add("item.classicraft.pebble.red_sandstone_pebble", "Red Sandstone Pebble");
        add("item.classicraft.pebble.end_stone_pebble", "End Stone Pebble");
        add("item.classicraft.pebble.netherrack_pebble", "Netherrack Pebble");
        add("item.classicraft.pebble.quartz_sandstone_pebble", "Quartz Sandstone Pebble");
        add("item.classicraft.pebble.soul_sandstone_pebble", "Soul Sandstone Pebble");
        add("item.classicraft.pebble.sandstone_pebble", "Sandstone Pebble");
        add("item.classicraft.point.cobblestone_point", "Cobblestone Point");
        add("item.classicraft.point.deepslate_point", "Deepslate Point");
        add("item.classicraft.point.flint_point", "Flint Point");
        add("item.classicraft.point.blackstone_point", "Blackstone Point");
        add("item.classicraft.point.quartz_point", "Quartz Point");
        add("item.classicraft.scraper.cobblestone_scraper", "Cobblestone Scraper");
        add("item.classicraft.scraper.deepslate_scraper", "Deepslate Scraper");
        add("item.classicraft.scraper.flint_scraper", "Flint Scraper");
        add("item.classicraft.scraper.blackstone_scraper", "Blackstone Scraper");
        add("item.classicraft.scraper.quartz_scraper", "Quartz Scraper");
    }
}
