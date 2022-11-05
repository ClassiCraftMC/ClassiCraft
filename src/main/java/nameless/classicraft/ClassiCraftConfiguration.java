package nameless.classicraft;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClassiCraftConfiguration {

    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.IntValue WEAKNESS_EFFECT_AMPLIFIER;
    public static final ForgeConfigSpec.DoubleValue WATER_REDUCING_RATE;
    public static final ForgeConfigSpec.BooleanValue RESET_WATER_LEVEL_IN_DEATH;

    static {
        BUILDER.comment("天工开物模组配置文件");
        BUILDER.push("General");
        WEAKNESS_EFFECT_AMPLIFIER =
                BUILDER.comment("  ", "It is the weakness effect amplifier of the effect punishment when player's water level is too low. -1 means canceling this effect. Default:0")
                        .defineInRange("weaknessEffectAmplifier", 0, -1, 999999);

        WATER_REDUCING_RATE =
                BUILDER.comment(" ", "finalReducingValue = basicValue * waterReducingRate.(DoubleValue)", "Default:1.0")
                        .defineInRange("waterReducingRate", 1.0D, 0d, 1000D);

        RESET_WATER_LEVEL_IN_DEATH =
                BUILDER.comment(" ", "It decides if players' water level would reset in death.", "Default:true")
                        .define("resetWaterLevelInDeath", true);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
