package nameless.classicraft.init;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModFoodDatas {

    public static FoodProperties ROTTEN_FOOD =
            food(0, 0.0)
                    .effect(new MobEffectInstance(MobEffects.CONFUSION, 900, 2), 1)
                    .effect( new MobEffectInstance(MobEffects.HUNGER, 900, 1), 1)
                    .effect(new MobEffectInstance(MobEffects.WEAKNESS, 900, 0), 1)
                    .build();
    public static FoodProperties COOKED_EGG =  food(6, 9.6).build();
    public static FoodProperties DOUGH = food(6, 9.6).build();
    public static FoodProperties RICE = food(2, 0.4).build();
    public static FoodProperties RICE_HUSK = food(2, 0.4).build();
    public static FoodProperties NETHER_MUSHROOM_STEW = food(6, 7.2).build();
    public static FoodProperties CACTUS_FRUIT = food(2, 0.4).build();
    public static FoodProperties FLOUR = food(2, 0.4).build();

    protected static FoodProperties.Builder food(int n, double s) {
        return new FoodProperties.Builder().nutrition(n).saturationMod((float) (s / 2 / n));
    }
}
