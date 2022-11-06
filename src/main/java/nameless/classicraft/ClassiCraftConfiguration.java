package nameless.classicraft;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;

public class ClassiCraftConfiguration {

    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec.IntValue torchBurnoutTime;
    public static ForgeConfigSpec.BooleanValue noRelightEnabled;


    static {
        BUILDER.comment("天工开物模组配置文件");
        BUILDER.push("General");

        String desc;

        desc = "火把熄灭前的时间，以分钟为单位。将其设置为负值将禁用火把熄灭.";
        torchBurnoutTime = BUILDER.comment(desc)
                .defineInRange("torchBurnoutTime", 60, -1, 2880);

        desc = "确定点燃的火把在熄灭后是否消失，而不是变成未点燃的火把.";
        noRelightEnabled = BUILDER.comment(desc)
                .define("torchNoRelight", false);

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
