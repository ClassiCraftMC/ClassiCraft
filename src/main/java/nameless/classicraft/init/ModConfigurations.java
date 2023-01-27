/*
 * ClassiCraft - ClassiCraftMC
 * Copyright (C) 2018-2022.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package nameless.classicraft.init;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;

public class ModConfigurations {

    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec.BooleanValue hardcore;
    public static ForgeConfigSpec.BooleanValue waterBurnt;
    public static ForgeConfigSpec.BooleanValue replaceVanillaBlock;
    public static ForgeConfigSpec.BooleanValue enableForcedGameRules;

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

        desc = "决定是否启用天工开物的强制游戏模式， 将提升游戏难度";
        enableForcedGameRules = BUILDER.comment(desc).define("enableForcedGameRules", true);

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
