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
package nameless.classicraft.item;

import com.google.common.collect.ImmutableMap;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.Map;

public class AttachFoods {

    static final Map<Item, FoodProperties> MAP = ImmutableMap.<Item, FoodProperties>builder()
            .put(Items.ROTTEN_FLESH, food(0, 0.0).build())
            .put(ModItems.ROTTEN_FOOD.get(), food(0, 0.0)
                    .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 900, 2), 1)
                    .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 900, 1), 1)
                    .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 900, 0), 1).build())
            .put(Items.SUGAR_CANE, food(2, 1.2).build())
            .put(Items.SUGAR, food(1, 0.6)
                    .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 80, 0), 1).build())
            .put(Items.FERMENTED_SPIDER_EYE, food(4, 6.4)
                    .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 80, 0), 1).build())
            .put(Items.EGG, food(2, 0.4).build())
            .put(Items.TURTLE_EGG, food(2, 0.4).build())
            .put(Items.BROWN_MUSHROOM, food(1, 0.2)
                    .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 300, 2), 1)
                    .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 300, 1), 1).build())
            .put(Items.RED_MUSHROOM, food(1, 0.2)
                    .effect(() -> new MobEffectInstance(MobEffects.POISON, 1200, 3), 1)
                    .effect(() -> new MobEffectInstance(MobEffects.WITHER, 600, 0), 1).build())
            .put(Items.CRIMSON_FUNGUS, food(1, 0.2)
                    .effect(() -> new MobEffectInstance(MobEffects.POISON, 1200, 3), 1)
                    .effect(() -> new MobEffectInstance(MobEffects.WITHER, 600, 0), 1).build())
            .put(Items.WARPED_FUNGUS, food(1, 0.2)
                    .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 300, 2), 1)
                    .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 300, 0), 1).build())
            .put(Items.GLISTERING_MELON_SLICE, food(4, 4.8).build())
            .put(ModBlocks.GLISTERING_MELON.get().asItem(), food(12, 4.8).build())
            .put(ModItems.COOKED_EGG.get(), food(6, 9.6).build())
            .put(ModItems.NETHER_MUSHROOM_STEW.get(), food(6, 7.2).build())
            .put(Items.MELON, food(6, 1.2).build())
            .put(Items.PUMPKIN, food(4, 1.2).build())
            .put(ModItems.CACTUS_FRUIT.get(), food(2, 0.4).build())
            .put(Items.CAKE, food(14, 2.8).build()).build();

    public static boolean isAttach(Item item) {
        return MAP.containsKey(item);
    }

    public static FoodProperties getFood(Item item) {
        return MAP.get(item);
    }

    static FoodProperties.Builder food(int n, double s) {
        return new FoodProperties.Builder().nutrition(n).saturationMod((float) (s / 2 / n));
    }
}