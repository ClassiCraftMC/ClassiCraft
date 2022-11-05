package nameless.classicraft;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClassiCraftConfiguration {

    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    static {
        String desc;
        BUILDER.comment("天工开物模组配置文件");
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
