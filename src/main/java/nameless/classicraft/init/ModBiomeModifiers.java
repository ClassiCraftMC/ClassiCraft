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

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.levelgen.ReplaceAllFeatureModifier;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBiomeModifiers {

    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, ClassiCraftMod.MOD_ID);

    public static final RegistryObject<Codec<ReplaceAllFeatureModifier>> REPLACE_ALL_CODEC =
            BIOME_MODIFIER_SERIALIZERS.register("replace_all",
                    () -> RecordCodecBuilder.create(builder ->
                            builder.group(PlacedFeature.CODEC.fieldOf("feature")
                                    .forGetter(ReplaceAllFeatureModifier::feature))
                                    .apply(builder, ReplaceAllFeatureModifier::new)));
}
