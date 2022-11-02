package nameless.classicraft.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class SaltItem extends Item {

    public static FoodProperties SALT =
            new FoodProperties.Builder()
                    .nutrition(0)
                    .saturationMod(0.0F)
                    .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 1200, 3), 1)
                    .build();

    public SaltItem() {
        super(new Item.Properties().tab(ClassiCraftTab.TAB).food(SALT));
    }

}
