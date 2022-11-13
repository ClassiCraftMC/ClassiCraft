package nameless.classicraft.item;

import nameless.classicraft.init.ModBlocks;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.HashMap;
import java.util.Map;

public class AttachFoods {

    private final static Map<Item, FoodProperties> MAP = new HashMap<>();

    public static FoodProperties getFood(Item item) {
        initBaseMap();
        return MAP.get(item);
    }

    public static void initBaseMap() {
         addMap(Items.ROTTEN_FLESH, food(0, 0.0).build());
         addMap(Items.SUGAR_CANE, food(2, 1.2).build());
         addMap(Items.SUGAR, food(1, 0.6)
                        .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 80, 0), 1).build());
         addMap(Items.FERMENTED_SPIDER_EYE, food(4, 6.4)
                        .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 80, 0), 1).build());
         addMap(Items.TURTLE_EGG, food(2, 0.4).build());
         addMap(Items.EGG, food(2, 0.4).build());
         addMap(Items.BROWN_MUSHROOM, food(1, 0.2)
                        .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 300, 2), 1)
                        .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 300, 1), 1).build());
         addMap(Items.RED_MUSHROOM, food(1, 0.2)
                        .effect(() -> new MobEffectInstance(MobEffects.POISON, 1200, 3), 1)
                        .effect(() -> new MobEffectInstance(MobEffects.WITHER, 600, 0), 1).build());
         addMap(Items.CRIMSON_FUNGUS, food(1, 0.2)
                        .effect(() -> new MobEffectInstance(MobEffects.POISON, 1200, 3), 1)
                        .effect(() -> new MobEffectInstance(MobEffects.WITHER, 600, 0), 1).build());
         addMap(Items.WARPED_FUNGUS, food(1, 0.2)
                        .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 300, 2), 1)
                        .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 300, 0), 1).build());
         addMap(Items.GLISTERING_MELON_SLICE, food(4, 4.8).build());
         addMap(ModBlocks.GLISTERING_MELON.get().asItem(), food(12, 4.8).build());
         addMap(Items.MELON, food(6, 1.2).build());
         addMap(Items.PUMPKIN, food(4, 1.2).build());
         addMap(Items.CAKE, food(14, 2.8).build());
    }

    public static void addMap(Item item, FoodProperties foodData) {
        MAP.put(item, foodData);
    }

    static FoodProperties.Builder food(int n, double s) {
        return new FoodProperties.Builder().nutrition(n).saturationMod((float) (s / 2 / n));
    }
}
