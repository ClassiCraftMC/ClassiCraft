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
        add(ModBlocks.REAL_TORCH.get(), "Real Torch");
        add(ModItems.DEBUG_TIME_STICK.get(), "Debug Burn Time Stick");
        add(ModItems.DEPTH_METER.get(), "Depth Meter");
        add(ModBlocks.QUICKSAND.get(), "Quicksand");
        add(ModBlocks.RED_QUICKSAND.get(), "Red Quicksand");
        add(ModBlocks.CHARCOAL_BLOCK.get(), "Charcoal Block");
        add(ModBlocks.CACTUS_BALL.get(), "Cactus ball");
        add(ModBlocks.ROSE.get(), "Rose");
        add(ModEntities.TROUT_ENTITY.get(), "Trout");
        add(ModEntities.OCEAN_SHARK_ENTITY.get(), "Ocean Shark");
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
        add(ModBlocks.FLINT.get(), "Flint");

        meta();
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
