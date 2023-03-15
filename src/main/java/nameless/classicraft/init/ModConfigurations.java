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
    public static ForgeConfigSpec.BooleanValue ignoreExperimentalWorldGenWarning;
    public static ForgeConfigSpec.BooleanValue noBlockDropsWithoutTools;
    public static ForgeConfigSpec.BooleanValue enableMilking;
    public static ForgeConfigSpec.BooleanValue enableEntityObtaining;

    static {
        BUILDER.comment("ClassiCraft Configurations");
        BUILDER.comment("Common");
        BUILDER.push("General");
        String desc;

        desc = "*Difficult*Decide the lit block can be burn out by some facts in inventory";
        hardcore = BUILDER.comment(desc).define("hardcore",true);

        desc = "Decide the lit block can be burn out in water";
        waterBurnt = BUILDER.comment(desc).define("water_burnt",true);

        desc = "Decide to replace the vanilla block to mod block in world gen";
        replaceVanillaBlock =  BUILDER.comment(desc).define("replaceVanillaBlock",true);

        desc = "Decide to enable ClassiCraft Forced GameRules...It will enforced the difficulty";
        enableForcedGameRules = BUILDER.comment(desc).define("enableForcedGameRules", true);

        desc = "Decide to ignore the experimental world gen info";
        ignoreExperimentalWorldGenWarning =
                BUILDER.comment(desc).define("ignoreExperimentalWorldGenWarning", true);

        desc = "Decide to make block no drops without using correct tools.";
        noBlockDropsWithoutTools =
                BUILDER.comment(desc).define("noBlockDropsWithoutTools", true);

        desc = "Decide to enable buckets can be milking of this mod added items";
        enableMilking =
                BUILDER.comment(desc).define("enableMilking", true);

        desc = "Decide to enable entity obtaining of mod buckets";
        enableEntityObtaining =
                BUILDER.comment(desc).define("enableEntityObtaining", true);

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
