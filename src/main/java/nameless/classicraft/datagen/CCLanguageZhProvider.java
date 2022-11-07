package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

/**
 * 用于自动生成中文语言文件
 */
public class CCLanguageZhProvider extends LanguageProvider {

    public CCLanguageZhProvider(DataGenerator gen, String locale) {
        super(gen, ClassiCraftMod.MODID, locale);
    }

    /**
     * 用于添加翻译
     * 使用例子：
     * add(实例, "翻译名");
     */
    @Override
    protected void addTranslations() {
        add("itemGroup.classicraft.common", "天工开物丨通常");
        add("itemGroup.classicraft.decoration", "天工开物丨装饰");
        add("level.classicraft.fresh", "新鲜的");
        add("level.classicraft.stale", "陈旧的");
        add("level.classicraft.spoiled", "变质的");
        add("level.classicraft.rotten", "腐坏的");
        add("info.classicraft.rot", "%s天后腐烂");
        add("info.classicraft.rotting_speed", "腐烂速度：%s");
        add("container.classicraft.fridge", "冰桶");
        add(ModBlocks.FRIDGE.get(), "冰桶");
        add("container.classicraft.stone_mortar_block", "石臼");
        add(ModBlocks.GLISTERING_MELON.get(), "闪烁的金西瓜");
        add(ModBlocks.CACTUS_FRUIT.get(), "仙人掌果实");
        add(ModBlocks.MUSHROOM_PLANTER.get(), "蘑菇农场");
        add(ModItems.ROTTEN_FOOD.get(), "腐烂物");
        add(ModItems.COOKED_EGG.get(), "煎蛋");
        add(ModItems.NETHER_MUSHROOM_STEW.get(), "下界蘑菇汤");
        add(ModItems.SALT.get(), "盐");
        add(ModItems.DOUGH.get(), "生面团");
        add(ModItems.SALT_WATER_BOTTLE.get(), "盐水瓶");
        add(ModBlocks.UNLIT_LARGE_FIRE_BOWL.get(), "熄灭火炉");
        add(ModBlocks.UNLIT_LARGE_SOUL_FIRE_BOWL.get(), "熄灭大型灵魂火炉");
        add(ModItems.RICE.get(), "稻米");
        add(ModItems.RICE_HUSK.get(), "稻谷");
        add(ModItems.RICE_SEED.get(), "水稻种子");
        add(ModItems.DEER_SPAWN_EGG.get(), "生成蛋丨鹿");
        add(ModItems.BOAR_SPAWN_EGG.get(), "生成蛋丨野猪");
        add(ModItems.FLOUR.get(), "粗制面粉");
        add(ModBlocks.SALT_ORE.get(), "盐矿");
        add(ModBlocks.WILD_RICE.get(), "野生稻谷");
        add(ModBlocks.STONE_MORTAR_BLOCK.get(), "石臼");
        add(ModBlocks.TORCH.get(), "火把");
        add(ModBlocks.SOUL_TORCH.get(), "灵魂火把");
        add(ModBlocks.SALT_ROCK_BLOCK.get(), "粗盐块");
        add(ModBlocks.SALT_BLOCK.get(), "盐块");
        add(ModBlocks.LANTERN.get(), "灯笼");
        add(ModBlocks.SOUL_LANTERN.get(), "灵魂灯笼");
        add(ModItems.RAW_PUMPKIN_PIE.get(), "南瓜派面胚");
        add(ModItems.RAW_COOKIE.get(), "生曲奇面胚");
        add(ModBlocks.SALT_STALACTITE.get(), "盐晶石锥");
        add(ModBlocks.LARGE_SALT_BUD.get(), "大型盐晶芽");
        add(ModBlocks.MEDIUM_SALT_BUD.get(), "中型盐晶芽");
        add(ModBlocks.SMALL_SALT_BUD.get(), "小型盐晶芽");
        add("info.classicraft.stop_use_lantern", "生存模式下禁止放置原版灯笼，你可以在天工开物模组配置文件中更改.");
        add("info.classicraft.stop_use_torch", "生存模式下禁止放置原版火把，你可以在天工开物模组配置文件中更改.");
        add("block.classicraft.unlit_golden_white_candleholder", "熄灭的金白色烛台");
        add("block.classicraft.unlit_golden_orange_candleholder", "熄灭的金橙色烛台");
        add("block.classicraft.unlit_golden_magenta_candleholder", "熄灭的金红色烛台");
        add("block.classicraft.unlit_golden_light_blue_candleholder", "熄灭的金色浅蓝色烛台");
        add("block.classicraft.unlit_golden_yellow_candleholder", "熄灭的金黄色烛台");
        add("block.classicraft.unlit_golden_lime_candleholder", "熄灭的金石灰烛台");
        add("block.classicraft.unlit_golden_pink_candleholder", "熄灭的金粉色烛台");
        add("block.classicraft.unlit_golden_gray_candleholder", "熄灭的金灰色烛台");
        add("block.classicraft.unlit_golden_light_gray_candleholder", "金色浅灰色烛台");
        add("block.classicraft.unlit_golden_cyan_candleholder", "熄灭的金蓝色烛台");
        add("block.classicraft.unlit_golden_purple_candleholder", "熄灭的金紫色烛台");
        add("block.classicraft.unlit_golden_blue_candleholder", "熄灭的金蓝色烛台");
        add("block.classicraft.unlit_golden_brown_candleholder", "熄灭的金棕色烛台");
        add("block.classicraft.unlit_golden_green_candleholder", "熄灭的金绿色烛台");
        add("block.classicraft.unlit_golden_red_candleholder", "熄灭的金红色烛台");
        add("block.classicraft.unlit_golden_black_candleholder", "熄灭的金黑色烛台");
        add("block.classicraft.unlit_white_candleholder", "熄灭的白色烛台");
        add("block.classicraft.unlit_orange_candleholder", "熄灭的橙色烛台");
        add("block.classicraft.unlit_magenta_candleholder", "熄灭的洋红色烛台");
        add("block.classicraft.unlit_light_blue_candleholder", "熄灭的浅蓝色烛台");
        add("block.classicraft.unlit_yellow_candleholder", "熄灭的黄色烛台");
        add("block.classicraft.unlit_lime_candleholder", "熄灭的石灰色烛台");
        add("block.classicraft.unlit_pink_candleholder", "熄灭的粉红色烛台");
        add("block.classicraft.unlit_gray_candleholder", "熄灭的灰色烛台");
        add("block.classicraft.unlit_light_gray_candleholder", "熄灭的浅灰色烛台");
        add("block.classicraft.unlit_cyan_candleholder", "熄灭的青色烛台");
        add("block.classicraft.unlit_purple_candleholder", "熄灭的紫色烛台");
        add("block.classicraft.unlit_blue_candleholder", "熄灭的蓝色烛台");
        add("block.classicraft.unlit_brown_candleholder", "熄灭的棕色烛台");
        add("block.classicraft.unlit_green_candleholder", "熄灭的绿色烛台");
        add("block.classicraft.unlit_red_candleholder", "熄灭的红色烛台");
        add("block.classicraft.unlit_black_candleholder", "熄灭的黑色烛台");
        add("block.classicraft.unlit_copper_white_candleholder", "熄灭的铜白色烛台");
        add("block.classicraft.unlit_copper_orange_candleholder", "熄灭的铜橙色烛台");
        add("block.classicraft.unlit_copper_magenta_candleholder", "熄灭的铜洋红色烛台");
        add("block.classicraft.unlit_copper_light_blue_candleholder", "熄灭的铜浅蓝色烛台");
        add("block.classicraft.unlit_copper_yellow_candleholder", "熄灭的铜黄色烛台");
        add("block.classicraft.unlit_copper_lime_candleholder", "熄灭的铜石灰烛台");
        add("block.classicraft.unlit_copper_pink_candleholder", "熄灭的铜粉烛台");
        add("block.classicraft.unlit_copper_gray_candleholder", "熄灭的铜灰色烛台");
        add("block.classicraft.unlit_copper_light_gray_candleholder", "熄灭的铜浅灰色烛台");
        add("block.classicraft.unlit_copper_cyan_candleholder", "熄灭的铜青色烛台");
        add("block.classicraft.unlit_copper_purple_candleholder", "熄灭的铜紫色烛台");
        add("block.classicraft.unlit_copper_blue_candleholder", "熄灭的铜蓝色烛台");
        add("block.classicraft.unlit_copper_brown_candleholder", "熄灭的铜棕色烛台");
        add("block.classicraft.unlit_copper_green_candleholder", "熄灭的铜绿色烛台");
        add("block.classicraft.unlit_copper_red_candleholder", "熄灭的铜红色烛台");
        add("block.classicraft.unlit_copper_black_candleholder", "熄灭的铜黑烛台");
        add("block.classicraft.unlit_large_golden_white_candleholder", "熄灭的大金白色烛台");
        add("block.classicraft.unlit_large_golden_orange_candleholder", "熄灭的大金橙烛台");
        add("block.classicraft.unlit_large_golden_magenta_candleholder", "熄灭的大金品红色烛台");
        add("block.classicraft.unlit_large_golden_light_blue_candleholder", "熄灭的大金光蓝烛台");
        add("block.classicraft.unlit_large_golden_yellow_candleholder", "熄灭的大金黄色烛台");
        add("block.classicraft.unlit_large_golden_lime_candleholder", "熄灭的大金色石灰烛台");
        add("block.classicraft.unlit_large_golden_pink_candleholder", "熄灭的大金粉烛台");
        add("block.classicraft.unlit_large_golden_gray_candleholder", "熄灭的大金灰色烛台");
        add("block.classicraft.unlit_large_golden_light_gray_candleholder", "熄灭的大金色浅灰色烛台");
        add("block.classicraft.unlit_large_golden_cyan_candleholder", "熄灭的大金青色烛台");
        add("block.classicraft.unlit_large_golden_purple_candleholder", "熄灭的大金紫色烛台");
        add("block.classicraft.unlit_large_golden_blue_candleholder", "熄灭的大金蓝色烛台");
        add("block.classicraft.unlit_large_golden_brown_candleholder", "熄灭的大金棕色烛台");
        add("block.classicraft.unlit_large_golden_green_candleholder", "熄灭的大金绿色烛台");
        add("block.classicraft.unlit_large_golden_red_candleholder", "熄灭的大金红色烛台");
        add("block.classicraft.unlit_large_golden_black_candleholder", "熄灭的大金黑烛台");
        add("block.classicraft.unlit_large_white_candleholder", "熄灭的大白色烛台");
        add("block.classicraft.unlit_large_orange_candleholder", "熄灭的大橙色烛台");
        add("block.classicraft.unlit_large_magenta_candleholder", "熄灭的大洋红色烛台");
        add("block.classicraft.unlit_large_light_blue_candleholder", "熄灭的大淡蓝色烛台");
        add("block.classicraft.unlit_large_yellow_candleholder", "熄灭的大黄色烛台");
        add("block.classicraft.unlit_large_lime_candleholder", "熄灭的大石灰烛台");
        add("block.classicraft.unlit_large_pink_candleholder", "熄灭的大粉红色烛台");
        add("block.classicraft.unlit_large_gray_candleholder", "熄灭的大灰色烛台");
        add("block.classicraft.unlit_large_light_gray_candleholder", "熄灭的浅灰色烛台");
        add("block.classicraft.unlit_large_cyan_candleholder", "熄灭的大青色烛台");
        add("block.classicraft.unlit_large_purple_candleholder", "熄灭的大紫色烛台");
        add("block.classicraft.unlit_large_blue_candleholder", "熄灭的大蓝色烛台");
        add("block.classicraft.unlit_large_brown_candleholder", "熄灭的大棕色烛台");
        add("block.classicraft.unlit_large_green_candleholder", "熄灭的大绿色烛台");
        add("block.classicraft.unlit_large_red_candleholder", "熄灭的大红色烛台");
        add("block.classicraft.unlit_large_black_candleholder", "熄灭的大黑色烛台");
        add("block.classicraft.unlit_large_copper_white_candleholder", "熄灭的大铜白色烛台");
        add("block.classicraft.unlit_large_copper_orange_candleholder", "熄灭的大铜橙烛台");
        add("block.classicraft.unlit_large_copper_magenta_candleholder", "熄灭的大铜品红烛台");
        add("block.classicraft.unlit_large_copper_light_blue_candleholder", "熄灭的大铜浅蓝色烛台");
        add("block.classicraft.unlit_large_copper_yellow_candleholder", "熄灭的大铜黄色烛台");
        add("block.classicraft.unlit_large_copper_lime_candleholder", "熄灭的大铜石灰烛台");
        add("block.classicraft.unlit_large_copper_pink_candleholder", "熄灭的大铜粉烛台");
        add("block.classicraft.unlit_large_copper_gray_candleholder", "熄灭的大铜灰色烛台");
        add("block.classicraft.unlit_large_copper_light_gray_candleholder", "熄灭的大铜浅灰色烛台");
        add("block.classicraft.unlit_large_copper_cyan_candleholder", "熄灭的大铜青色烛台");
        add("block.classicraft.unlit_large_copper_purple_candleholder", "熄灭的大铜紫色烛台");
        add("block.classicraft.unlit_large_copper_blue_candleholder", "熄灭的大铜蓝色烛台");
        add("block.classicraft.unlit_large_copper_brown_candleholder", "熄灭的大铜棕色烛台");
        add("block.classicraft.unlit_large_copper_green_candleholder", "熄灭的大铜绿色烛台");
        add("block.classicraft.unlit_large_copper_red_candleholder", "熄灭的大铜红色烛台");
        add("block.classicraft.unlit_large_copper_black_candleholder", "熄灭的大铜黑色烛台");
    }
}
