package org.bedracket.classicraft.init;

import org.bedracket.classicraft.config.Config;

public class ModConfigs {

    @Config(category = "general", key = "hardcore", comment = "*困难*决定是否背包里的点燃的方块会熄灭")
    public static boolean hardcore = true;

    @Config(category = "general", key = "waterBurnt", comment = "决定当你进入水中时是否你背包里点燃的火把会被熄灭")
    public static boolean waterBurnt = true;
}
