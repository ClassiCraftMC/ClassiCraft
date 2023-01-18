package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModEntities;
import nameless.classicraft.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProviderZh extends LanguageProvider {

    public ModLanguageProviderZh(DataGenerator gen) {
        super(gen.getPackOutput(), ClassiCraftMod.MOD_ID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        add(ModItems.TORCH_LIT.get(), "点燃的火把");
        add(ModItems.TORCH_UNLIT.get(), "熄灭的火把");
        add(ModItems.SOUL_TORCH_UNLIT.get(), "熄灭的灵魂火把");
        add(ModBlocks.REAL_TORCH.get(), "火把");
        add(ModItems.DEBUG_TIME_STICK.get(), "燃烧时间检测棒");
        add(ModItems.DEPTH_METER.get(), "深度计");
        add(ModBlocks.QUICKSAND.get(), "流沙");
        add(ModBlocks.RED_QUICKSAND.get(), "红沙流沙");
        add(ModBlocks.CHARCOAL_BLOCK.get(), "木炭块");
        add(ModBlocks.CACTUS_BALL.get(), "仙人球");
        add(ModBlocks.ROSE.get(), "玫瑰");
        add(ModItems.TROUT_SPAWN_EGG.get(), "鳟鱼刷怪蛋");
        add(ModItems.TROUT_BUCKET.get(), "鳟鱼桶");
        add(ModItems.TROUT.get(), "鳟鱼");
        add(ModItems.COOKED_TROUT.get(), "熟鳟鱼");
        add(ModItems.TALLOW.get(), "油脂");
        add(ModBlocks.TALLOW_BLOCK.get(), "油脂块");
        add("info.classicraft.burntime", "燃烧时间");
        add("info.classicraft.minutes", "分钟");
        add("info.classicraft.depth.sky_land", "的天域");
        add("info.classicraft.depth.sky", "的高空");
        add("info.classicraft.depth.cloud", "的云层");
        add("info.classicraft.depth.base_cloud", "的低空");
        add("info.classicraft.depth.sea_level", "的海平面");
        add("info.classicraft.depth.surface", "的地表");
        add("info.classicraft.depth.underground", "的地底");
        add("info.classicraft.depth.deep_underground", "的深层地底");
        add("info.classicraft.depth.bedrock", "的基岩层");
        add("info.classicraft.depth.void", "的虚空");
        add("info.classicraft.height", "当前位于高度");
        add("level.classicraft.fresh", "新鲜的");
        add("level.classicraft.stale", "陈旧的");
        add("level.classicraft.spoiled", "变质的");
        add("level.classicraft.rotten", "腐坏的");
        add("info.classicraft.rot", "%s天后腐烂");
        add("info.classicraft.rotting_speed", "腐烂速度：%s");
        add("info.classicraft.shift_press", "按下shift键显示更多信息");
        add("info.classicraft.food.nutrition", "营养价值");
        add("info.classicraft.food.saturation", "恢复价值");
        add(ModItems.OCEAN_SHARK_SPAWN_EGG.get(), "海洋鲨鱼生成蛋");
        add(ModItems.LIVING_DEAD_SPAWN_EGG.get(), "活死人生成蛋");
        add(ModItems.COOKED_EGG.get(), "煎蛋");
        add(ModItems.NETHER_MUSHROOM_STEW.get(), "下界蘑菇汤");
        add(ModItems.ROTTEN_FOOD.get(), "腐烂的食物");
        add(ModBlocks.MOSSY_BRICKS.get(), "苔红砖块");
        add(ModBlocks.MOSSY_BRICKS_STAIRS.get(), "苔红砖楼梯");
        add(ModBlocks.MOSSY_BRICKS_WALL.get(), "苔红砖墙");
        add(ModBlocks.MOSSY_BRICKS_SLAB.get(), "苔红台阶");
        add(ModBlocks.CRACKED_BRICKS.get(), "裂纹红砖块");
        add(ModBlocks.CRACKED_BRICKS_STAIRS.get(), "裂纹红砖楼梯");
        add(ModBlocks.CRACKED_BRICKS_WALL.get(), "裂纹红砖墙");
        add(ModBlocks.CRACKED_BRICKS_SLAB.get(), "裂纹红砖台阶");
        add(ModBlocks.SULFUR_ORE.get(), "硫磺矿");
        add(ModItems.SULFUR.get(), "硫磺");
        add(ModBlocks.SMOOTH_STONE_WALL.get(), "平滑石头墙");
        add(ModBlocks.SMOOTH_STONE_STAIRS.get(), "平滑石头楼梯");
        add(ModBlocks.STONE_WALL.get(), "石头墙");
        add(ModBlocks.FLINT_BLOCK.get(), "燧石块");
        add(ModBlocks.CHISELED_SOUL_SANDSTONE.get(), "雕纹灵魂砂岩");
        add(ModBlocks.CHISELED_QUARTZ_SANDSTONE.get(), "雕纹石英砂岩");
        add(ModBlocks.CUT_SOUL_SANDSTONE.get(), "切制灵魂砂岩");
        add(ModBlocks.CUT_QUARTZ_SANDSTONE.get(), "切制石英砂岩");
        add(ModBlocks.QUARTZ_QUICKSAND.get(), "石英沙流沙");
        add(ModBlocks.QUARTZ_SAND.get(), "石英沙");
        add(ModBlocks.QUARTZ_SANDSTONE.get(), "石英砂岩");
        add(ModBlocks.QUARTZ_SANDSTONE_BRICKS.get(), "石英砂岩砖");
        add(ModBlocks.RED_SANDSTONE_BRICKS.get(), "红砂岩砖");
        add(ModBlocks.SANDSTONE_BRICKS.get(), "砂岩砖");
        add(ModBlocks.SOUL_SANDSTONE.get(), "灵魂砂岩");
        add(ModBlocks.SOUL_QUICKSAND.get(), "灵魂沙流沙");
        add(ModBlocks.SOUL_SANDSTONE_BRICKS.get(), "灵魂岩砖");
        add(ModBlocks.CRACKED_STONE_BRICKS_SLAB.get(), "裂纹石砖台阶");
        add(ModBlocks.CRACKED_STONE_BRICKS_STAIRS.get(), "裂纹石砖楼梯");
        add(ModBlocks.CRACKED_STONE_BRICKS_WALL.get(), "裂纹石砖墙");
        add(ModBlocks.POLISHED_GRANITE_WALL.get(), "磨制花岗岩墙");
        add(ModBlocks.POLISHED_ANDESITE_WALL.get(), "磨制安山岩墙");
        add(ModBlocks.POLISHED_DIORITE_WALL.get(), "磨制闪长岩墙");
        add(ModBlocks.INFESTED_MOSSY_COBBLESTONE.get(), "被虫蚀的苔石");
        add(ModBlocks.DEEPSLATE_WALL.get(), "深板岩墙");
        add(ModBlocks.DEEPSLATE_STAIRS.get(), "深板岩楼梯");
        add(ModBlocks.DEEPSLATE_SLAB.get(), "深板岩台阶");
        add(ModBlocks.CRACKED_DEEPSLATE_BRICKS_WALL.get(), "裂纹深板岩墙");
        add(ModBlocks.CRACKED_DEEPSLATE_BRICKS_STAIRS.get(), "裂纹深板岩楼梯");
        add(ModBlocks.CRACKED_DEEPSLATE_BRICKS_SLAB.get(), "裂纹深板岩台阶");
        add(ModBlocks.INFESTED_CHISELED_DEEPSLATE.get(), "被虫蚀的雕纹深板岩");
        add(ModBlocks.INFESTED_COBBLED_DEEPSLATE.get(), "被虫蚀的深板岩圆石");
        add(ModBlocks.INFESTED_DEEPSLATE_BRICKS.get(), "被虫蚀的深板岩砖");
        add(ModBlocks.INFESTED_DEEPSLATE_TILES.get(), "被虫蚀的深板岩瓦");
        add(ModBlocks.SMOOTH_SANDSTONE_WALL.get(), "平滑砂岩墙");
        add(ModBlocks.CUT_SANDSTONE_WALL.get(), "切制砂岩墙");
        add(ModBlocks.CUT_SANDSTONE_STAIRS.get(), "切制砂岩楼梯");
        add(ModBlocks.SMOOTH_RED_SANDSTONE_WALL.get(), "平滑红砂岩墙");
        add(ModBlocks.CUT_RED_SANDSTONE_STAIRS.get(), "切制红砂岩楼梯");
        add(ModBlocks.CUT_RED_SANDSTONE_WALL.get(), "切制红砂岩墙");
        add(ModBlocks.SOUL_SANDSTONE_SLAB.get(), "灵魂砂岩台阶");
        add(ModBlocks.SOUL_SANDSTONE_STAIRS.get(), "灵魂砂岩楼梯");
        add(ModBlocks.SOUL_SANDSTONE_WALL.get(), "灵魂砂岩墙");
        add(ModBlocks.SMOOTH_SOUL_SANDSTONE.get(), "平滑灵魂砂岩");
        add(ModBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS.get(), "平滑灵魂砂岩楼梯");
        add(ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB.get(), "平滑灵魂砂岩台阶");
        add(ModBlocks.SMOOTH_SOUL_SANDSTONE_WALL.get(), "平滑灵魂砂岩墙");
        add(ModBlocks.CUT_SOUL_SANDSTONE_WALL.get(), "切制灵魂砂岩墙");
        add(ModBlocks.CUT_SOUL_SANDSTONE_STAIRS.get(), "切制灵魂砂岩楼梯");
        add("itemGroup.classicraft.common", "天工开物|通常");
        add("itemGroup.classicraft.building_blocks", "天工开物|建筑方块");
        add("itemGroup.classicraft.material", "天工开物|材料");
        add("itemGroup.classicraft.natural_blocks", "天工开物|自然方块");

        pebbleBlock();
        meta();
        entity();
    }

    private void pebbleBlock() {
        add(ModBlocks.FLINT.get(), "燧石石块");
        add(ModBlocks.ANDESITE_PEBBLE.get(), "安山岩石块");
        add(ModBlocks.COBBLESTONE_PEBBLE.get(), "圆石石块");
        add(ModBlocks.DIORITE_PEBBLE.get(), "闪长岩石块");
        add(ModBlocks.GRANITE_PEBBLE.get(), "花岗岩石块");
        add(ModBlocks.RED_SANDSTONE_PEBBLE.get(), "红砂岩石块");
        add(ModBlocks.SANDSTONE_PEBBLE.get(), "砂岩石块");
        add(ModBlocks.DEEPSLATE_PEBBLE.get(), "深板岩石块");
        add(ModBlocks.QUARTZ_SANDSTONE_PEBBLE.get(), "石英砂岩石块");
        add(ModBlocks.SOUL_SANDSTONE_PEBBLE.get(), "灵魂砂岩石块");
        add(ModBlocks.NETHERRACK_PEBBLE.get(), "下界岩石块");
        add(ModBlocks.END_STONE_PEBBLE.get(), "末地岩石块");
        add(ModBlocks.BASALT_PEBBLE.get(), "玄武岩石块");
        add(ModBlocks.BLACKSTONE_PEBBLE.get(), "黑石石块");
        add(ModBlocks.PRISMARINE.get(), "海晶岩石块");
        add(ModBlocks.QUARTZ_PEBBLE.get(), "石英石块");
    }

    private void entity() {
        add(ModEntities.OCEAN_SHARK_ENTITY.get(), "海洋鲨鱼");
        add(ModEntities.TROUT_ENTITY.get(), "鳟鱼");
        add(ModEntities.LIVING_DEAD.get(), "活死人");
    }

    private void meta() {
        add("item.classicraft.adze.cobblestone_adze", "圆石刨铲器");
        add("item.classicraft.adze.deepslate_adze", "深板岩刨铲器");
        add("item.classicraft.adze.flint_adze", "燧石刨铲器");
        add("item.classicraft.adze.blackstone_adze", "黑石刨铲器");
        add("item.classicraft.adze.quartz_adze", "石英刨铲器");
        add("item.classicraft.awl.cobblestone_awl", "圆石锥形器");
        add("item.classicraft.awl.deepslate_awl", "深板岩锥形器");
        add("item.classicraft.awl.flint_awl", "燧石锥形器");
        add("item.classicraft.awl.blackstone_awl", "黑石锥形器");
        add("item.classicraft.awl.quartz_awl", "石英锥形器");
        add("item.classicraft.chopper.cobblestone_chopper", "圆石砍砸器");
        add("item.classicraft.chopper.deepslate_chopper", "深板岩砍砸器");
        add("item.classicraft.chopper.flint_chopper", "燧石砍砸器");
        add("item.classicraft.chopper.blackstone_chopper", "黑石砍砸器");
        add("item.classicraft.chopper.quartz_chopper", "石英砍砸器");
        add("item.classicraft.pebble.andesite_pebble", "安山岩石块");
        add("item.classicraft.pebble.basalt_pebble", "玄武岩石块");
        add("item.classicraft.pebble.blackstone_pebble", "黑石石块");
        add("item.classicraft.pebble.cobblestone_pebble", "圆石石块");
        add("item.classicraft.pebble.deepslate_pebble", "深板岩石块");
        add("item.classicraft.pebble.diorite_pebble", "闪长岩石块");
        add("item.classicraft.pebble.granite_pebble", "花岗岩石块");
        add("item.classicraft.pebble.red_sandstone_pebble", "红砂岩石块");
        add("item.classicraft.pebble.end_stone_pebble", "末地石石块");
        add("item.classicraft.pebble.netherrack_pebble", "下界岩石块");
        add("item.classicraft.pebble.quartz_sandstone_pebble", "石英砂岩石块");
        add("item.classicraft.pebble.soul_sandstone_pebble", "灵魂砂岩石块");
        add("item.classicraft.pebble.sandstone_pebble", "砂岩石块");
        add("item.classicraft.point.cobblestone_point", "圆石尖状器");
        add("item.classicraft.point.deepslate_point", "深板岩尖状器");
        add("item.classicraft.point.flint_point", "燧石尖状器");
        add("item.classicraft.point.blackstone_point", "黑石尖状器");
        add("item.classicraft.point.quartz_point", "石英尖状器");
        add("item.classicraft.scraper.cobblestone_scraper", "圆石刮削器");
        add("item.classicraft.scraper.deepslate_scraper", "深板岩刮削器");
        add("item.classicraft.scraper.flint_scraper", "燧石刮削器");
        add("item.classicraft.scraper.blackstone_scraper", "黑石刮削器");
        add("item.classicraft.scraper.quartz_scraper", "石英刮削器");
    }
}
