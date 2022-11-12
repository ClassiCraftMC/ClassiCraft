package nameless.classicraft;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;

public class ClassiCraftConfiguration {

    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec.IntValue torchBurnoutTime;
    public static ForgeConfigSpec.IntValue lanternBurnoutTime;
    public static ForgeConfigSpec.IntValue fireBowlBurnoutTime;
    public static ForgeConfigSpec.IntValue largeFireBowlBurnoutTime;
    public static ForgeConfigSpec.IntValue candleholderBurnoutTime;
    public static ForgeConfigSpec.IntValue wildRiceGenerateChance;
    public static ForgeConfigSpec.IntValue saltOreGenerateCount;
    public static ForgeConfigSpec.IntValue saltOreGenerateSize;
    public static ForgeConfigSpec.IntValue saltCaveGenerateChance;
    public static ForgeConfigSpec.BooleanValue noRelightEnabled;
    public static ForgeConfigSpec.BooleanValue noVanillaTorchPlace;
    public static ForgeConfigSpec.BooleanValue noVanillaLanternPlace;
    public static ForgeConfigSpec.IntValue woodenItemBurnTime;
    public static ForgeConfigSpec.BooleanValue torchCauseFire;
    public static ForgeConfigSpec.BooleanValue shutupExperimentalWarning;
    public static ForgeConfigSpec.BooleanValue removeSendFeedbackAndReportBugs;
    public static ForgeConfigSpec.BooleanValue hardcore;
    public static ForgeConfigSpec.BooleanValue waterBurnt;
    public static ForgeConfigSpec.BooleanValue turnToStickEnabled;


    static {
        BUILDER.comment("天工开物模组配置文件");
        BUILDER.push("general");

        String desc;
        desc = "决定是否火把熄灭会变成木棍";
        turnToStickEnabled = BUILDER.comment(desc).define("turnToStickEnabled",true);

        desc = "决定是否火把会导致易燃物燃烧(仅限点燃的火把)";
        torchCauseFire = BUILDER.comment(desc).define("torchCauseFire",false);

        desc = "*困难*决定是否背包里的点燃的方块会熄灭";
        hardcore = BUILDER.comment(desc).define("hardcore",true);

        desc = "决定当你进入水中时是否你背包里点燃的火把会被熄灭";
        waterBurnt = BUILDER.comment(desc).define("water_burnt",true);

        desc = "确定是否禁用提供反馈，报告漏洞选项";
        removeSendFeedbackAndReportBugs = BUILDER.comment(desc)
                .define("removeSendFeedbackAndReportBugs", true);

        desc = "确定是否禁止实验性设置警告";
        shutupExperimentalWarning = BUILDER.comment(desc)
                .define("shutupExperimentalWarning", true);

        desc = "设置木质物品树苗、树叶的燃烧时间";
        woodenItemBurnTime = BUILDER.comment(desc)
                .defineInRange("woodenItemBurnTime", 100, -1, 2880);

        desc = "烛台熄灭耗费的时间，以分钟为单位。将其设置为负值将禁用烛台熄灭.";
        candleholderBurnoutTime = BUILDER.comment(desc)
                .defineInRange("candleholderBurnoutTime", 60, -1, 100);

        desc = "大型火盆熄灭耗费的时间，以分钟为单位。将其设置为负值将禁用大型火盆熄灭.";
        largeFireBowlBurnoutTime = BUILDER.comment(desc)
                .defineInRange("largeFireBowlBurnoutTime", 90, -1, 100);

        desc = "火盆熄灭耗费的时间，以分钟为单位。将其设置为负值将禁用火盆熄灭.";
        fireBowlBurnoutTime = BUILDER.comment(desc)
                .defineInRange("fireBowlBurnoutTime", 90, -1, 100);

        desc = "灯笼熄灭耗费的时间，以分钟为单位。将其设置为负值将禁用灯笼熄灭.";
        lanternBurnoutTime = BUILDER.comment(desc)
                .defineInRange("lanternBurnoutTime", 80, -1, 100);

        desc = "火把熄灭耗费的时间，以分钟为单位。将其设置为负值将禁用火把熄灭.";
        torchBurnoutTime = BUILDER.comment(desc)
                .defineInRange("torchBurnoutTime", 2, -1, 100);

        desc = "确定点燃的火把在熄灭后是否消失，而不是变成未点燃的火把.";
        noRelightEnabled = BUILDER.comment(desc)
                .define("torchNoRelight", true);

        desc = "确定是否禁止生存模式放置原版火把（相关火把模组也将禁止放置火把）.";
        noVanillaTorchPlace = BUILDER.comment(desc)
                .define("noVanillaTorchPlace", true);

        desc = "确定是否禁止生存模式放置原版灯笼（相关灯笼模组也将禁止放置灯笼）.";
        noVanillaLanternPlace = BUILDER.comment(desc)
                .define("noVanillaLanternPlace", true);

        desc = "设置盐洞的生成率, 将其设置为负值将禁用盐洞生成.";
        saltCaveGenerateChance = BUILDER.comment(desc)
                .defineInRange("saltCaveGenerateChance", 26, -1, 100);

        desc = "设置区块中盐矿生成数值, 将其设置为负值将禁用区块中盐矿生成.";
        saltOreGenerateSize = BUILDER.comment(desc)
                .defineInRange("saltOreGenerateSize", 16, -1, 100);

        desc = "设置盐矿的生成数量, 将其设置为负值将禁用盐矿生成.";
        saltOreGenerateCount = BUILDER.comment(desc)
                .defineInRange("saltOreGenerateCount", 26, -1, 100);

        desc = "设置野生稻谷的生成率, 将其设置为负值将禁用野生稻谷生成.";
        wildRiceGenerateChance = BUILDER.comment(desc)
                .defineInRange("wildRiceGenerateChance", 10, -1, 100);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        spec.setConfig(configData);
    }
}
