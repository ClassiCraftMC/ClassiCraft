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
package nameless.classicraft.datagen.levelgen;

import nameless.classicraft.util.Helpers;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModBiomesProvider {

    public static final ResourceKey<Biome> QUARTZ_MID_LAND = register("quartz_mid_land");

    public static void biomes(BootstapContext<Biome> context) {
        HolderGetter<PlacedFeature> holder = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> holder_1 = context.lookup(Registries.CONFIGURED_CARVER);
        context.register(QUARTZ_MID_LAND, endMidlands(holder, holder_1));
    }

    private static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registries.BIOME, Helpers.identifier(name));
    }

    public static Biome endMidlands(HolderGetter<PlacedFeature> p_255719_, HolderGetter<ConfiguredWorldCarver<?>> p_255751_) {
        BiomeGenerationSettings.Builder biomegenerationsettings$builder =
                new BiomeGenerationSettings.Builder(p_255719_, p_255751_);
        return baseEndBiome(biomegenerationsettings$builder);
    }

    private static Biome baseEndBiome(BiomeGenerationSettings.Builder pGenerationSettings) {
        MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.endSpawns(mobspawnsettings$builder);
        return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.NONE).temperature(0.5F).downfall(0.5F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(10518688).skyColor(0).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobspawnsettings$builder.build()).generationSettings(pGenerationSettings.build()).build();
    }
}
