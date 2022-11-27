package nameless.classicraft.init;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;

public class ModConfigs {

    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec.BooleanValue hardcore;
    public static ForgeConfigSpec.BooleanValue waterBurnt;
    public static ForgeConfigSpec.BooleanValue replaceVanillaBlock;

    static {
        BUILDER.comment("天工开物模组配置文件");
        BUILDER.comment("通常");
        BUILDER.push("General");
        String desc;

        desc = "*困难*决定是否背包里的点燃的方块会熄灭";
        hardcore = BUILDER.comment(desc).define("hardcore",true);

        desc = "决定当你进入水中时是否你背包里点燃的火把会被熄灭";
        waterBurnt = BUILDER.comment(desc).define("water_burnt",true);

        desc = "决定是否将原版方块替换为模组方块生成";
        replaceVanillaBlock =  BUILDER.comment(desc).define("replaceVanillaBlock",true);

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
