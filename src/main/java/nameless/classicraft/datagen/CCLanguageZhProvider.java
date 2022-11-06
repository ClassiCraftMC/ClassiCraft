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
        add(ModBlocks.CACTUS_FRUIT.get(), "仙人掌果");
        add(ModItems.ROTTEN_FOOD.get(), "腐烂物");
        add(ModItems.COOKED_EGG.get(), "煎蛋");
        add(ModItems.NETHER_MUSHROOM_STEW.get(), "下界蘑菇汤");
        add(ModItems.SALT.get(), "盐");
        add(ModItems.DOUGH.get(), "生面团");
        add(ModItems.SALT_WATER_BOTTLE.get(), "盐水瓶");
        add(ModBlocks.UNLIT_TORCH.get(), "熄灭火把");
        add(ModBlocks.UNLIT_SOUL_TORCH.get(), "熄灭灵魂火把");
        add(ModBlocks.UNLIT_LANTERN.get(), "熄灭灯笼");
        add(ModBlocks.UNLIT_SOUL_LANTERN.get(), "熄灭灵魂灯笼");
        add(ModBlocks.UNLIT_FIRE_BOWL.get(), "熄灭火盆");
        add(ModBlocks.UNLIT_SOUL_FIRE_BOWL.get(), "熄灭灵魂火盆");
        add(ModBlocks.UNLIT_LARGE_FIRE_BOWL.get(), "熄灭火炉");
        add(ModBlocks.UNLIT_LARGE_SOUL_FIRE_BOWL.get(), "熄灭大型灵魂火炉");
        add(ModItems.RICE.get(), "稻米");
        add(ModItems.RICE_HUSK.get(), "稻谷");
        add(ModItems.RICE_SEED.get(), "水稻种子");
        add(ModItems.DEER_SPAWN_EGG.get(), "生成蛋丨鹿");
        add(ModItems.FLOUR.get(), "粗制面粉");
        add(ModBlocks.SALT_ORE.get(), "盐矿");
        add(ModBlocks.WILD_RICE.get(), "野生稻谷");
        add(ModBlocks.STONE_MORTAR_BLOCK.get(), "石臼");
        add("block.classicraft.unlit_golden_white_candleholder", "Unlit Golden White Candleholder");
        add("block.classicraft.unlit_golden_orange_candleholder", "Unlit Golden Orange Candleholder");
        add("block.classicraft.unlit_golden_magenta_candleholder", "Unlit Golden Magenta Candleholder");
        add("block.classicraft.unlit_golden_light_blue_candleholder", "Unlit Golden Light Blue Candleholder");
        add("block.classicraft.unlit_golden_yellow_candleholder", "Unlit Golden Yellow Candleholder");
        add("block.classicraft.unlit_golden_lime_candleholder", "Unlit Golden Lime Candleholder");
        add("block.classicraft.unlit_golden_pink_candleholder", "Unlit Golden Pink Candleholder");
        add("block.classicraft.unlit_golden_gray_candleholder", "Unlit Golden Gray Candleholder");
        add("block.classicraft.unlit_golden_light_gray_candleholder", "Unlit Golden Light Gray Candleholder");
        add("block.classicraft.unlit_golden_cyan_candleholder", "Unlit Golden Cyan Candleholder");
        add("block.classicraft.unlit_golden_purple_candleholder", "Unlit Golden Purple Candleholder");
        add("block.classicraft.unlit_golden_blue_candleholder", "Unlit Golden Blue Candleholder");
        add("block.classicraft.unlit_golden_brown_candleholder", "Unlit Golden Brown Candleholder");
        add("block.classicraft.unlit_golden_green_candleholder", "Unlit Golden Green Candleholder");
        add("block.classicraft.unlit_golden_red_candleholder", "Unlit Golden Red Candleholder");
        add("block.classicraft.unlit_golden_black_candleholder", "Unlit Golden Black Candleholder");
        add("block.classicraft.unlit_white_candleholder", "Unlit White Candleholder");
        add("block.classicraft.unlit_orange_candleholder", "Unlit Orange Candleholder");
        add("block.classicraft.unlit_magenta_candleholder", "Unlit Magenta Candleholder");
        add("block.classicraft.unlit_light_blue_candleholder", "Unlit Light Blue Candleholder");
        add("block.classicraft.unlit_yellow_candleholder", "Unlit Yellow Candleholder");
        add("block.classicraft.unlit_lime_candleholder", "Unlit Lime Candleholder");
        add("block.classicraft.unlit_pink_candleholder", "Unlit Pink Candleholder");
        add("block.classicraft.unlit_gray_candleholder", "Unlit Gray Candleholder");
        add("block.classicraft.unlit_light_gray_candleholder", "Unlit Light Gray Candleholder");
        add("block.classicraft.unlit_cyan_candleholder", "Unlit Cyan Candleholder");
        add("block.classicraft.unlit_purple_candleholder", "Unlit Purple Candleholder");
        add("block.classicraft.unlit_blue_candleholder", "Unlit Blue Candleholder");
        add("block.classicraft.unlit_brown_candleholder", "Unlit Brown Candleholder");
        add("block.classicraft.unlit_green_candleholder", "Unlit Green Candleholder");
        add("block.classicraft.unlit_red_candleholder", "Unlit Red Candleholder");
        add("block.classicraft.unlit_black_candleholder", "Unlit Black Candleholder");
        add("block.classicraft.unlit_copper_white_candleholder", "Unlit Copper White Candleholder");
        add("block.classicraft.unlit_copper_orange_candleholder", "Unlit Copper Orange Candleholder");
        add("block.classicraft.unlit_copper_magenta_candleholder", "Unlit Copper Magenta Candleholder");
        add("block.classicraft.unlit_copper_light_blue_candleholder", "Unlit Copper Light Blue Candleholder");
        add("block.classicraft.unlit_copper_yellow_candleholder", "Unlit Copper Yellow Candleholder");
        add("block.classicraft.unlit_copper_lime_candleholder", "Unlit Copper Lime Candleholder");
        add("block.classicraft.unlit_copper_pink_candleholder", "Unlit Copper Pink Candleholder");
        add("block.classicraft.unlit_copper_gray_candleholder", "Unlit Copper Gray Candleholder");
        add("block.classicraft.unlit_copper_light_gray_candleholder", "Unlit Copper Light Gray Candleholder");
        add("block.classicraft.unlit_copper_cyan_candleholder", "Unlit Copper Cyan Candleholder");
        add("block.classicraft.unlit_copper_purple_candleholder", "Unlit Copper Purple Candleholder");
        add("block.classicraft.unlit_copper_blue_candleholder", "Unlit Copper Blue Candleholder");
        add("block.classicraft.unlit_copper_brown_candleholder", "Unlit Copper Brown Candleholder");
        add("block.classicraft.unlit_copper_green_candleholder", "Unlit Copper Green Candleholder");
        add("block.classicraft.unlit_copper_red_candleholder", "Unlit Copper Red Candleholder");
        add("block.classicraft.unlit_copper_black_candleholder", "Unlit Copper Black Candleholder");
        add("block.classicraft.unlit_large_golden_white_candleholder", "Unlit Lagre Golden White Candleholder");
        add("block.classicraft.unlit_large_golden_orange_candleholder", "Unlit Lagre Golden Orange Candleholder");
        add("block.classicraft.unlit_large_golden_magenta_candleholder", "Unlit Lagre Golden Magenta Candleholder");
        add("block.classicraft.unlit_large_golden_light_blue_candleholder", "Unlit Lagre Golden Light Blue Candleholder");
        add("block.classicraft.unlit_large_golden_yellow_candleholder", "Unlit Lagre Golden Yellow Candleholder");
        add("block.classicraft.unlit_large_golden_lime_candleholder", "Unlit Lagre Golden Lime Candleholder");
        add("block.classicraft.unlit_large_golden_pink_candleholder", "Unlit Lagre Golden Pink Candleholder");
        add("block.classicraft.unlit_large_golden_gray_candleholder", "Unlit Lagre Golden Gray Candleholder");
        add("block.classicraft.unlit_large_golden_light_gray_candleholder", "Unlit Lagre Golden Light Gray Candleholder");
        add("block.classicraft.unlit_large_golden_cyan_candleholder", "Unlit Lagre Golden Cyan Candleholder");
        add("block.classicraft.unlit_large_golden_purple_candleholder", "Unlit Lagre Golden Purple Candleholder");
        add("block.classicraft.unlit_large_golden_blue_candleholder", "Unlit Lagre Golden Blue Candleholder");
        add("block.classicraft.unlit_large_golden_brown_candleholder", "Unlit Lagre Golden Brown Candleholder");
        add("block.classicraft.unlit_large_golden_green_candleholder", "Unlit Lagre Golden Green Candleholder");
        add("block.classicraft.unlit_large_golden_red_candleholder", "Unlit Lagre Golden Red Candleholder");
        add("block.classicraft.unlit_large_golden_black_candleholder", "Unlit Lagre Golden Black Candleholder");
        add("block.classicraft.unlit_large_white_candleholder", "Unlit Lagre White Candleholder");
        add("block.classicraft.unlit_large_orange_candleholder", "Unlit Lagre Orange Candleholder");
        add("block.classicraft.unlit_large_magenta_candleholder", "Unlit Lagre Magenta Candleholder");
        add("block.classicraft.unlit_large_light_blue_candleholder", "Unlit Lagre Light Blue Candleholder");
        add("block.classicraft.unlit_large_yellow_candleholder", "Unlit Lagre Yellow Candleholder");
        add("block.classicraft.unlit_large_lime_candleholder", "Unlit Lagre Lime Candleholder");
        add("block.classicraft.unlit_large_pink_candleholder", "Unlit Lagre Pink Candleholder");
        add("block.classicraft.unlit_large_gray_candleholder", "Unlit Lagre Gray Candleholder");
        add("block.classicraft.unlit_large_light_gray_candleholder", "Unlit Lagre Light Gray Candleholder");
        add("block.classicraft.unlit_large_cyan_candleholder", "Unlit Lagre Cyan Candleholder");
        add("block.classicraft.unlit_large_purple_candleholder", "Unlit Lagre Purple Candleholder");
        add("block.classicraft.unlit_large_blue_candleholder", "Unlit Lagre Blue Candleholder");
        add("block.classicraft.unlit_large_brown_candleholder", "Unlit Lagre Brown Candleholder");
        add("block.classicraft.unlit_large_green_candleholder", "Unlit Lagre Green Candleholder");
        add("block.classicraft.unlit_large_red_candleholder", "Unlit Lagre Red Candleholder");
        add("block.classicraft.unlit_large_black_candleholder", "Unlit Lagre Black Candleholder");
        add("block.classicraft.unlit_large_copper_white_candleholder", "Unlit Lagre Copper White Candleholder");
        add("block.classicraft.unlit_large_copper_orange_candleholder", "Unlit Lagre Copper Orange Candleholder");
        add("block.classicraft.unlit_large_copper_magenta_candleholder", "Unlit Lagre Copper Magenta Candleholder");
        add("block.classicraft.unlit_large_copper_light_blue_candleholder", "Unlit Lagre Copper Light Blue Candleholder");
        add("block.classicraft.unlit_large_copper_yellow_candleholder", "Unlit Lagre Copper Yellow Candleholder");
        add("block.classicraft.unlit_large_copper_lime_candleholder", "Unlit Lagre Copper Lime Candleholder");
        add("block.classicraft.unlit_large_copper_pink_candleholder", "Unlit Lagre Copper Pink Candleholder");
        add("block.classicraft.unlit_large_copper_gray_candleholder", "Unlit Lagre Copper Gray Candleholder");
        add("block.classicraft.unlit_large_copper_light_gray_candleholder", "Unlit Lagre Copper Light Gray Candleholder");
        add("block.classicraft.unlit_large_copper_cyan_candleholder", "Unlit Lagre Copper Cyan Candleholder");
        add("block.classicraft.unlit_large_copper_purple_candleholder", "Unlit Lagre Copper Purple Candleholder");
        add("block.classicraft.unlit_large_copper_blue_candleholder", "Unlit Lagre Copper Blue Candleholder");
        add("block.classicraft.unlit_large_copper_brown_candleholder", "Unlit Lagre Copper Brown Candleholder");
        add("block.classicraft.unlit_large_copper_green_candleholder", "Unlit Lagre Copper Green Candleholder");
        add("block.classicraft.unlit_large_copper_red_candleholder", "Unlit Lagre Copper Red Candleholder");
        add("block.classicraft.unlit_large_copper_black_candleholder", "Unlit Lagre Copper Black Candleholder");
    }
}
