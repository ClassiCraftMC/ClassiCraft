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

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.levelgen.LevelSurfaceFeature;
import nameless.classicraft.levelgen.ReplaceAllFeature;
import nameless.classicraft.levelgen.TwigSurfaceFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(ForgeRegistries.FEATURES, ClassiCraftMod.MOD_ID);

    public static final RegistryObject<ReplaceAllFeature> REPLACE_ALL_FEATURE =
            FEATURES.register("replace_all", () ->
                    new ReplaceAllFeature(NoneFeatureConfiguration.CODEC.stable()));

    public static final RegistryObject<LevelSurfaceFeature> LEVEL_SURFACE_FEATURE =
            FEATURES.register("level_surface", LevelSurfaceFeature::new);

    public static final RegistryObject<TwigSurfaceFeature> TWIG_SURFACE_FEATURE =
            FEATURES.register("twig_surface", TwigSurfaceFeature::new);
}
