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
        add(ModBlocks.REAL_TORCH.get(), "火把");
        add(ModItems.DEBUG_TIME_STICK.get(), "燃烧时间检测棒");
        add(ModItems.DEPTH_METER.get(), "深度计");
        add(ModBlocks.QUICKSAND.get(), "流沙");
        add(ModBlocks.RED_QUICKSAND.get(), "红流沙");
        add(ModBlocks.CHARCOAL_BLOCK.get(), "木炭块");
        add(ModBlocks.CACTUS_BALL.get(), "仙人球");
        add(ModBlocks.ROSE.get(), "玫瑰");
        add(ModEntities.TROUT_ENTITY.get(), "鳟鱼");
        add(ModEntities.OCEAN_SHARK_ENTITY.get(), "海洋鲨鱼");
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

        meta();
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
