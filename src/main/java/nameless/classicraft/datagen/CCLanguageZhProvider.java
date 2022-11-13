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
        add(ModBlocks.LARGE_FIRE_BOWL.get(), "大型火炉");
        add(ModBlocks.LARGE_SOUL_FIRE_BOWL.get(), "大型灵魂火炉");
        add(ModItems.RICE.get(), "稻米");
        add(ModItems.RICE_HUSK.get(), "稻谷");
        add(ModItems.RICE_SEED.get(), "水稻种子");
        add(ModItems.DEER_SPAWN_EGG.get(), "鹿刷怪蛋");
        add(ModItems.BOAR_SPAWN_EGG.get(), "野猪刷怪蛋");
        add(ModItems.FLOUR.get(), "粗制面粉");
        add(ModBlocks.SALT_ORE.get(), "盐矿");
        add(ModBlocks.WILD_RICE.get(), "野生稻谷");
        add(ModBlocks.STONE_MORTAR_BLOCK.get(), "石臼");
        add(ModBlocks.TORCH.get(), "火把");
        add(ModBlocks.SOUL_TORCH.get(), "灵魂火把");
        add("item.classicraft.lit_soul_torch", "点燃的灵魂火把");
        add("item.classicraft.lit_torch", "点燃的火把");
        add(ModBlocks.SALT_ROCK_BLOCK.get(), "粗盐块");
        add(ModBlocks.SALT_BLOCK.get(), "盐块");
        add(ModBlocks.LANTERN.get(), "灯笼");
        add(ModBlocks.SOUL_LANTERN.get(), "灵魂灯笼");
        add(ModItems.RAW_PUMPKIN_PIE.get(), "南瓜派面胚");
        add(ModItems.RAW_CAKE.get(), "生蛋糕面胚");
        add(ModItems.RAW_COOKIE.get(), "生曲奇面胚");
        add(ModBlocks.SALT_STALACTITE.get(), "盐晶石锥");
        add(ModBlocks.LARGE_SALT_BUD.get(), "大型盐晶芽");
        add(ModBlocks.MEDIUM_SALT_BUD.get(), "中型盐晶芽");
        add(ModBlocks.SMALL_SALT_BUD.get(), "小型盐晶芽");
        add(ModBlocks.SALT_CLUSTER.get(), "盐晶簇");
        add(ModItems.LIONFISH_BUCKET.get(), "狮子鱼桶");
        add(ModItems.LIONFISH.get(), "狮子鱼");
        add(ModItems.LIONFISH_SPAWN_EGG.get(), "狮子鱼刷怪蛋");
        add(ModItems.COOKED_LIONFISH.get(), "熟狮子鱼");
        add(ModItems.PERCH_BUCKET.get(), "鲈鱼桶");
        add(ModItems.PERCH.get(), "鲈鱼");
        add(ModItems.RANCHU_SPAWN_EGG.get(), "兰寿鱼刷怪蛋");
        add(ModItems.PERCH_SPAWN_EGG.get(), "鲈鱼刷怪蛋");
        add(ModItems.RANCHU_BUCKET.get(), "兰寿鱼桶");
        add(ModItems.COOKED_PERCH.get(), "熟鲈鱼");
        add(ModItems.RAW_SALT.get(), "粗盐");
        add(ModItems.BOXFISH_BUCKET.get(), "搏鱼桶");
        add(ModItems.BOXFISH_SPAWN_EGG.get(), "搏鱼刷怪蛋");
        add(ModItems.ANGLEFISH_BUCKET.get(), "方角鱼桶");
        add(ModItems.ANGLEFISH_SPAWN_EGG.get(), "方角鱼刷怪蛋");
        add(ModItems.FOXFACE_FISH_BUCKET.get(), "狐脸鱼桶");
        add(ModItems.FOXFACE_FISH_SPAWN_EGG.get(), "狐脸鱼刷怪蛋");
        add(ModItems.FOOTBALLFISH_BUCKET.get(), "足球鱼桶");
        add(ModItems.FOOTBALLFISH_SPAWN_EGG.get(), "足球鱼刷怪蛋");
        add(ModItems.RANCHU.get(), "兰寿鱼");
        add(ModItems.ANGLEFISH.get(), "方角鱼");
        add(ModItems.FOOTBALLFISH.get(), "足球鱼");
        add(ModItems.BASKING_SHARK_SPAWN_EGG.get(), "姥鲨刷怪蛋");
        add(ModItems.OCEAN_SHARK_SPAWN_EGG.get(), "海洋鲨鱼刷怪蛋");
        add(ModItems.ARAPAIMA.get(), "巨滑舌鱼");
        add(ModItems.ARAPAIMA_SPAWN_EGG.get(), "巨滑舌鱼刷怪蛋");
        add(ModItems.ARAPAIMA_BUCKET.get(), "巨滑舌鱼桶");
        add(ModItems.BELUGA_STURGEON_SPAWN_EGG.get(), "欧洲鳇刷怪蛋");
        add(ModBlocks.AZALEA.get(), "映山红");
        add(ModBlocks.CHRYSANTHEMUM.get(), "菊花");
        add(ModBlocks.PEONY.get(), "牡丹");
        add(ModBlocks.ROSE.get(), "玫瑰");
        add(ModItems.BULL_SHARK_SPAWN_EGG.get(), "牛鲨刷怪蛋");
        add(ModItems.LEMON_SHARK_SPAWN_EGG.get(), "短吻基齿鲨刷怪蛋");
        add(ModItems.SLEEPER_SHARK_SPAWN_EGG.get(), "睡鲨刷怪蛋");
        add(ModItems.TROUT_SPAWN_EGG.get(), "鳟鱼刷怪蛋");
        add(ModItems.COOKED_TROUT.get(), "熟鳟鱼");
        add(ModItems.TROUT.get(), "鳟鱼");
        add(ModItems.TROUT_BUCKET.get(), "鳟鱼桶");
        add("info.classicraft.stop_use_lantern", "生存模式下放置原版灯笼将替换为模组灯笼，你可以在天工开物模组配置文件中更改.");
        add("info.classicraft.stop_use_torch", "生存模式下放置原版火把将替换为模组火把，你可以在天工开物模组配置文件中更改.");
    }
}
