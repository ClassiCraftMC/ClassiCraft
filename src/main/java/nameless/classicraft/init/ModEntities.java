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
import nameless.classicraft.entity.LivingDead;
import nameless.classicraft.entity.OceanShark;
import nameless.classicraft.entity.Trout;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,
                    ClassiCraftMod.MOD_ID);

    public static final RegistryObject<EntityType<Trout>> TROUT_ENTITY =
            register("trout", EntityType.Builder.of(Trout::new, MobCategory.WATER_AMBIENT)
                    .sized(0.5F, 0.3F)
                    .clientTrackingRange(4));

    public static final RegistryObject<EntityType<LivingDead>> LIVING_DEAD =
            register("living_dead", EntityType.Builder.<LivingDead>of(LivingDead::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F)
                    .clientTrackingRange(8));

    public static final RegistryObject<EntityType<OceanShark>> OCEAN_SHARK_ENTITY =
            register("ocean_shark", EntityType.Builder.of(OceanShark::new, MobCategory.WATER_AMBIENT)
                    .sized(1.4f,1.1f)
                    .clientTrackingRange(10));

    public static <E extends Entity> RegistryObject<EntityType<E>> register(String name, EntityType.Builder<E> builder) {
        return register(name, builder, true);
    }

    public static <E extends Entity> RegistryObject<EntityType<E>> register(String name, EntityType.Builder<E> builder, boolean serialize) {
        final String id = name.toLowerCase(Locale.ROOT);
        return ENTITIES.register(id, () -> {
            if (!serialize) builder.noSave();
            return builder.build(ClassiCraftMod.MOD_ID + ":" + id);
        });
    }
}
