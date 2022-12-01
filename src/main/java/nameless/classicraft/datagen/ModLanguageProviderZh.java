package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModEntities;
import nameless.classicraft.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProviderZh extends LanguageProvider {

    public ModLanguageProviderZh(DataGenerator gen) {
        super(gen, ClassiCraftMod.MOD_ID, "zh_cn");
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
    }
}
